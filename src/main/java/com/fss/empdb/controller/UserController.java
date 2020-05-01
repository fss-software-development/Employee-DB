/*
package com.fss.empdb.controller;

import com.fss.empdb.domain.Project;
import com.fss.empdb.domain.User;
import com.fss.empdb.repository.ProjectRepository;
import com.fss.empdb.repository.UserRepository;
import com.fss.empdb.service.ProjectService;
import com.fss.empdb.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UsersService usersService;


    @PostMapping("/add")
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
    }


    @PostMapping("/login")
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
    }
}
*/
