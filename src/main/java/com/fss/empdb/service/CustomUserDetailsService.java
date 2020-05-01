/*
package com.fss.empdb.service;

import com.fss.empdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.fss.empdb.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
*/
/*@Service("userDetailsService")*//*

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Optional<User> optionalUsers = usersRepository.findByName(username);
       User user = userRepository.findByName(username);
        System.out.println("username ::"+username);
        CustomUserDetails userDetails = null;
        if (user != null) {
            userDetails = new CustomUserDetails();
            userDetails.setUser(user);
        } else {
            throw new UsernameNotFoundException("User not exist with name : " + username);
        }
        return userDetails;
    }
}
*/
