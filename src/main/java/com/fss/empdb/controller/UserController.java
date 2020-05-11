package com.fss.empdb.controller;

import com.fss.empdb.constants.EmpdbConstants;
import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.Employee;
import com.fss.empdb.domain.User;
import com.fss.empdb.repository.UserRepository;
import com.fss.empdb.service.UsersService;
import com.fss.empdb.util.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    //private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UsersService usersService;

    @PostMapping("/add")
    public String addUserByAdmin(@RequestBody User user) {
        try
        {
            String pwd = user.getUserPassword();
            String encryptPwd = usersService.sha256Hash(pwd) ;
            /*String encryptPwd = usersService.encrypt(pwd, secretKey) ;*/
            user.setUserPassword(encryptPwd);
            userRepository.save(user);
            return "User added successfully....";
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
            e.printStackTrace();
            return "Exception";
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginByUser(@RequestBody User user) {
        try {
            log.info("Inside Login");
            boolean isPasswordMatch;
            String pwd = user.getUserPassword();
            User getUserDetails = usersService.userById(user.getUserId());
            String encryptPwd = usersService.sha256Hash(pwd) ;
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
                return ResponseEntity.ok(user);
            }else{
                return ResponseEntity.ok(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @PostMapping("/forget-password")
    public void forgetPassword(@RequestBody User user) throws MessagingException {
        System.out.println("Inside forget password");
        User getUserDetails = usersService.userById(user.getUserId());
        usersService.forgetPasswordMail(getUserDetails, EmpdbConstants.FORGOT_PWD,ErrorConstants.MAIL_BODY);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllPermissions() {
        return ResponseEntity.ok().body(usersService.getAllPermissions());
    }

    @GetMapping("/{id}/permissions")
    public ResponseEntity<User> getPermissionsById(@PathVariable(value = "id") Long userId) {
        return ResponseEntity.ok().body(usersService.getPermissionsById(userId));
    }

    @GetMapping("/home")
    public String addUserByAdmin() {
        return "Hello";
    }

    /*@PostMapping("/add")
    public String addUserByAdmin(@RequestBody User user) {
        try {
            String pwd = user.getUserPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encryptPwd = passwordEncoder.encode(pwd);
            user.setUserPassword(encryptPwd);
            userRepository.save(user);
            return "User added successfully....";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Exception";
        }
    }*/


    /*@PostMapping("/login")
    public Boolean loginByUser(@RequestBody User user) {
        try {
            String pwd = user.getUserPassword();
            User getProj = usersService.userById(user.getUserId());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            boolean isPasswordMatch = encoder.matches(pwd, getProj.getUserPassword());
            return isPasswordMatch;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }*/
}

