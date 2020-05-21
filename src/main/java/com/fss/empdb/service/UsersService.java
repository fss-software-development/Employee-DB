package com.fss.empdb.service;

import com.fss.empdb.constants.EmpdbConstants;
import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.*;
import com.fss.empdb.util.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.text.RandomStringGenerator;
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
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
@Log4j2
public class UsersService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    EmpdbProperties empdbProperties;

    public User userById(String userId) {
        return userRepository.findByUserId(userId);
    }

    public static String sha256Hash(String originalPassword) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(
                originalPassword.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encodedHash);
    }

    public String forgotPasswordMail(User userDetails,String subject, String body) throws MessagingException {
        try {
            StringBuilder mailBody = new StringBuilder();
            mailBody.append(body);
            String to= userDetails.getEmail();
            String generatedPassword = generateSecureRandomPassword();
            mailBody.append(generatedPassword);
            String encryptPwd = sha256Hash(generatedPassword) ;
            userDetails.setUserPassword(encryptPwd);
            userDetails.setIsResetRequired(EmpdbConstants.IS_RESET_REQUIRED);
            userRepository.save(userDetails);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
            helper.setSubject(subject);
            helper.setFrom(empdbProperties.getSpring().getMail().getUsername());
            helper.setTo(to);
            helper.setText(String.valueOf(mailBody), true);
            javaMailSender.send(message);
            log.info("Execution has finished");
            return "Auto generated password sent successfully";
        }catch(Exception ex){
            log.error("Forgot Password Mail Exception :"+ex.toString());
            return "Forgot Password Mail Exception";
        }
    }

    public List<User> getAllPermissions() {

        return userRepository.findAll();
    }

    public User getPermissionsById(String userId) {
        return userRepository.findByUserId(userId);
    }

    public String addUserByAdmin(User user) {
        try
        {
            String pwd = user.getUserPassword();
            String encryptPwd = sha256Hash(pwd) ;
            /*String encryptPwd = usersService.encrypt(pwd, secretKey) ;*/
            user.setUserPassword(encryptPwd);
            userRepository.save(user);
            return "User added successfully....";
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
            e.printStackTrace();
            return "Exception Occurred";
        }
    }

    public User loginByUser(User user) {
        try {
            boolean isPasswordMatch;
            String pwd = user.getUserPassword();
            User getUserDetails = userById(user.getUserId());
            String encryptPwd = sha256Hash(pwd) ;
            System.out.println("encryptPwd ::"+encryptPwd);
            isPasswordMatch =encryptPwd.equals(getUserDetails.getUserPassword());
           /* String decryptedPwd = usersService.decrypt(getUserDetails.getUserPassword(), secretKey) ;
            isPasswordMatch = pwd.equals(decryptedPwd);*/
            if (isPasswordMatch == true) {
                user.setUserPassword(getUserDetails.getUserPassword());
            /*final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(getUserDetails.getUserName());*/
                final String jwt = jwtTokenUtil.generateToken(user);
                user.setUserJwt(jwt);
                user.setIsResetRequired(getUserDetails.getIsResetRequired());
                log.info("Jwt Token :" + jwt);
                return user;
            }else{
                return user;
            }
        } catch (Exception ex) {
            log.info("Exception during login");
            ex.printStackTrace();
            return null;
        }
    }

    public String changePassword(ChangeUserPassword user) throws Exception {
        log.info("Inside change password2");
        String responseMsg = "";
        if(!(user.getUserNewPassword().equals(user.getUserConfirmPassword()))){
            responseMsg ="New password & confirm password should be same ";
         return responseMsg;
        }
        User fetchUser = userRepository.findByUserId(user.getUserId());
        String enteredEncryptPwd = sha256Hash(user.getUserPassword());
        if(fetchUser!=null){
            if((fetchUser.getUserPassword()).equals(enteredEncryptPwd)){
                String newPwd = user.getUserConfirmPassword();
                String encryptPwd = sha256Hash(newPwd);
                fetchUser.setUserPassword(encryptPwd);
                fetchUser.setIsResetRequired(ErrorConstants.RESET_NOT_REQUIRED);
                userRepository.save(fetchUser);
                return "Password changed successfully....";
            }
            return "Old password is incorrect";
        }
        return responseMsg;
    }

    public static String generateSecureRandomPassword() {
        Stream<Character> pwdStream = Stream.concat(getRandomNumbers(2),
                Stream.concat(getRandomSpecialChars(2),
                        Stream.concat(getRandomAlphabets(2, true), getRandomAlphabets(4, false))));
        List<Character> charList = pwdStream.collect(Collectors.toList());
        Collections.shuffle(charList);
        String password = charList.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
        return password;
    }

    public static Stream<Character> getRandomSpecialChars(int count) {
        Random random = new SecureRandom();
        IntStream specialChars = random.ints(count, 33, 45);
        return specialChars.mapToObj(data -> (char) data);
    }

    public static Stream<Character> getRandomNumbers(int count) {
        Random random = new SecureRandom();
        IntStream numChars = random.ints(count, 48, 57);
        return numChars.mapToObj(data -> (char) data);
    }

    public static Stream<Character> getRandomAlphabets(int count,boolean flag) {
        Random random = new SecureRandom();
        IntStream alphaChars = null;
        if(flag)
            alphaChars = random.ints(count, 65, 90);
        else
            alphaChars = random.ints(count, 97, 122);
        return alphaChars.mapToObj(data -> (char) data);
    }
}
