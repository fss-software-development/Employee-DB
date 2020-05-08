package com.fss.empdb.service;

import com.fss.empdb.constants.EmpdbConstants;
import com.fss.empdb.constants.ErrorConstants;
import org.apache.commons.text.RandomStringGenerator;
import com.fss.empdb.domain.Project;
import com.fss.empdb.domain.User;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.UserRepository;
import org.apache.commons.text.TextRandomProvider;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Service
public class UsersService {

    @Autowired
    UserRepository userRepository;

    private static SecretKeySpec secretKey;
    private static byte[] key;

    @Autowired
    UsersService usersService;

    @Autowired
    private JavaMailSender javaMailSender;

    public User userById(Long userId) {
        return userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.DATA_NOT_FOUND + userId));
    }

    public static String sha256Hash(String originalPassword) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(
                originalPassword.getBytes(StandardCharsets.UTF_8));
        String hash = Base64.getEncoder().encodeToString(encodedhash);
        return hash;
    }

    public static void setKey(String myKey)
    {

        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public void forgetPasswordMail(User userDetails,String subject, String body) throws MessagingException {
        try {
            StringBuilder mailBody = new StringBuilder();
            mailBody.append(body);
            String to= userDetails.getEmail();
            String generatedPassword = generateRandomSpecialCharacters(8);
            mailBody.append(generatedPassword);
            String encryptPwd = usersService.sha256Hash(generatedPassword) ;
            userDetails.setUserPassword(encryptPwd);
            userDetails.setIsResetRequired(EmpdbConstants.IS_RESET_REQUIRED);
            userRepository.save(userDetails);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(String.valueOf(mailBody), true);
            javaMailSender.send(message);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public String generateRandomSpecialCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(LETTERS, DIGITS)
                .build();
        return pwdGenerator.generate(length);
    }

}
