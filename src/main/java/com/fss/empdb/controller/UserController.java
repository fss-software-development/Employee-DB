package com.fss.empdb.controller;

import com.fss.empdb.domain.User;
import com.fss.empdb.repository.UserRepository;
import com.fss.empdb.service.UsersService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /*@Autowired
    private AuthenticationManager authenticationManager;*/

    //private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UsersService usersService;
    final String secretKey = "ZnNzZW1wbG95ZWVkYg";
    //String originalString = "ZW1wbG95ZWVEQg";

    @PostMapping("/add")
    public String addUserByAdmin(@RequestBody User user) {
            try
            {
                String pwd = user.getUserPassword();
                String encryptPwd = usersService.encrypt(pwd, secretKey) ;
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

    @PostMapping("/authenticate")
    public String loginByUser(@RequestBody User user) {
        try {
            log.info("Inside Login");
            boolean isPasswordMatch;
            String pwd = user.getUserPassword();
            User getUserDetails = usersService.userById(user.getUserId());
            String decryptedPwd = usersService.decrypt(getUserDetails.getUserPassword(), secretKey) ;
            isPasswordMatch = pwd.equals(decryptedPwd);
            if (isPasswordMatch == true){

            } else {
                return "Failure";
            }
            return "Success";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Failure";
        }
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

