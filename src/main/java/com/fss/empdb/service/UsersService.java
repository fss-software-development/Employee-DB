package com.fss.empdb.service;

import com.fss.empdb.constants.ErrorConstants;
import com.fss.empdb.domain.Project;
import com.fss.empdb.domain.User;
import com.fss.empdb.exception.ResourceNotFoundException;
import com.fss.empdb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    UserRepository userRepository;

    public User userById(Long userId) {
        return userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.DATA_NOT_FOUND + userId));
    }
}
