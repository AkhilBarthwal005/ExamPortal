package com.exam.examportal.controller;

import com.exam.examportal.entities.Role;
import com.exam.examportal.entities.User;
import com.exam.examportal.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        Set<User> users = new HashSet<>();
        users.add(user);

        Role role = new Role();
        role.setRoleId(2);
        role.setRoleName("NORMAL");
        role.setUser(users);

        user.setRole(role);
        return userService.createUser(user,role);
    }

    @GetMapping("/{userName}")
    public User getUser(@PathVariable String userName){
       return userService.getUser(userName);
    }
}
