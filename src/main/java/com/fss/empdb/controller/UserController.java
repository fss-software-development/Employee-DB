package com.fss.empdb.controller;

import com.fss.empdb.constants.EmpdbConstants;
import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.Employee;
import com.fss.empdb.domain.EmployeeSearchCriteria;
import com.fss.empdb.domain.User;
import com.fss.empdb.repository.UserRepository;
import com.fss.empdb.service.UsersService;
import com.fss.empdb.util.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyAuthority('ADD_EMPLOYEE')")
    @PostMapping("/add")
    public String addUserByAdmin(@RequestBody User user) {
            String responseMessage = usersService.addUserByAdmin(user);
            return responseMessage;
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginByUser(@RequestBody User user) {
            return ResponseEntity.ok().body(usersService.loginByUser(user));
    }

    @PostMapping("/forget-password")
    public void forgetPassword(@RequestBody User user) throws MessagingException {
        User getUserDetails = usersService.userById(user.getUserId());
        usersService.forgetPasswordMail(getUserDetails, EmpdbConstants.FORGOT_PWD,EmpdbConstants.MAIL_BODY);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllPermissions() {
        return ResponseEntity.ok().body(usersService.getAllPermissions());
    }

    @GetMapping("/permissions/{id}")
    public ResponseEntity<User> getPermissionsByIdTest(@PathVariable(value = "id") String userId) {
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

