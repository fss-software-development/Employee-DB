package com.fss.empdb.service;

import com.fss.empdb.constants.EmpdbConstants;
import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.Employee;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.text.RandomStringGenerator;
import com.fss.empdb.domain.User;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.UserRepository;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;

@Service
@Log4j2
public class UsersService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    public User userById(Long userId) {
        return userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.DATA_NOT_FOUND + userId));
    }

    public static String sha256Hash(String originalPassword) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(
                originalPassword.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encodedHash);
    }

    public void forgetPasswordMail(User userDetails,String subject, String body) throws MessagingException {
        try {
            StringBuilder mailBody = new StringBuilder();
            mailBody.append(body);
            String to= userDetails.getEmail();
            String generatedPassword = generateRandomSpecialCharacters(8);
            mailBody.append(generatedPassword);
            String encryptPwd = sha256Hash(generatedPassword) ;
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
            log.error("forgetPasswordMail Exception :"+ex.toString());
        }
    }

    public String generateRandomSpecialCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(LETTERS, DIGITS)
                .build();
        return pwdGenerator.generate(length);
    }

    public List<User> getAllPermissions() {

        return userRepository.findAll();
    }

    public User getPermissionsById(Long userId) {
        return userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.DATA_NOT_FOUND + userId));
    }
}
