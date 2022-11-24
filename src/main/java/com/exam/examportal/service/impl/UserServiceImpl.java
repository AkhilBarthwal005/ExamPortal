package com.exam.examportal.service.impl;

import com.exam.examportal.entities.Role;
import com.exam.examportal.entities.User;
import com.exam.examportal.repository.RoleRepository;
import com.exam.examportal.repository.UserRepository;
import com.exam.examportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Role role) throws Exception {
        // checking if user is already exist or not.
        User existUser = userRepository.findByUsername(user.getUsername());
        if(existUser != null){
            System.out.println("User found Exception.");
            throw new Exception("User found Exception.");
        }

        return userRepository.save(user);
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByUsername(userName);
    }
}
