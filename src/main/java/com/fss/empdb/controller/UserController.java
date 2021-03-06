package com.fss.empdb.controller;

import com.fss.empdb.constants.EmpdbConstants;
import com.fss.empdb.domain.ChangeUserPassword;
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

    @PreAuthorize("hasAnyAuthority('ADD_USER')")
    @PostMapping("/add")
    public String addUserByAdmin(@RequestBody User user) {
            String responseMessage = usersService.addUserByAdmin(user);
            return responseMessage;
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginByUser(@RequestBody User user) {
            return ResponseEntity.ok().body(usersService.loginByUser(user));
    }

    @GetMapping("/forgotPassword/{userId}")
    public String forgotPassword(@PathVariable(value = "userId") String userId) throws MessagingException {
        User getUserDetails = usersService.userById(userId);
        String responseMessage = usersService.forgotPasswordMail(getUserDetails, EmpdbConstants.FORGOT_PWD,EmpdbConstants.MAIL_BODY);
        return responseMessage;
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllPermissions() {
        return ResponseEntity.ok().body(usersService.getAllPermissions());
    }

    @GetMapping("/{id}/permissions")
    public ResponseEntity<User> getPermissionsById(@PathVariable(value = "id") String userId) {
        return ResponseEntity.ok().body(usersService.getPermissionsById(userId));
    }

    @PostMapping(value = "/changePassword", produces = "application/json")
    public String changePassword(@RequestBody ChangeUserPassword changeUserPassword) throws Exception  {
        String responseMessage = usersService.changePassword(changeUserPassword);
        return responseMessage;
    }

}

