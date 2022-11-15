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
        User existUser = userRepository.findByUserName(user.getUserName());
        if(existUser != null){
            System.out.println("User not found Exception.");
            throw new Exception("User not found Exception.");
        }
        Role newUserRole = roleRepository.save(role);

        return userRepository.save(user);
    }
}
