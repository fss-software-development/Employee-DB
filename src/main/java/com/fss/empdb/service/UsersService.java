package com.fss.empdb.service;

import com.fss.empdb.constants.EmpdbConstants;
import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.*;
import com.fss.empdb.util.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.text.RandomStringGenerator;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.UserRepository;
import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.criteria.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Log4j2
public class UsersService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private JwtUtil jwtTokenUtil;

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

   /* public List<User> getPermissionsById(User user) {
        log.info("User Search Service" + user);
        return userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!(user.getUserId()!=null)) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("userId"), user.getUserId())));
                }
               *//* log.info("User Search Service ----------role " + user.getUserRoleId());
                if (user.getUserRoleId() != null) {
                    log.info("User Search Service ----------role1 " + user.getUserRoleId());
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("userRoleId"), user.getUserRoleId())));
                }*//*

                *//*if (user.getUserRole() != null) {
                    log.info("User Search Service ----------role1 " + user.getUserRole());
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("userRoleId"), user.getUserRole())));
                }*//*
                *//*if (user.getUserRoleId()  != null) {
                    Join<User, UserPermission> phoneJoin = root.join("userPermission");
                    predicates.add(phoneJoin.in(user.getUserRoleId()));

                }*//*

                if (user.getUserRole()  != null) {
                    Join<User, UserRole> phoneJoin = root.join("userRole");
                    predicates.add(phoneJoin.in(user.getUserRole()));

                }

                log.info("Search filter Size :" + predicates.size());
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }*/

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
            isPasswordMatch =encryptPwd.equals(getUserDetails.getUserPassword());
            System.out.println("encryptPwd ::"+encryptPwd);
           /* String decryptedPwd = usersService.decrypt(getUserDetails.getUserPassword(), secretKey) ;
            isPasswordMatch = pwd.equals(decryptedPwd);*/
            if (isPasswordMatch == true) {
                user.setUserPassword(getUserDetails.getUserPassword());
            /*final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(getUserDetails.getUserName());*/
                final String jwt = jwtTokenUtil.generateToken(user);
                user.setUserJwt(jwt);
                log.info("Jwt Token :" + jwt);
                return user;
            }else{
                return user;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
