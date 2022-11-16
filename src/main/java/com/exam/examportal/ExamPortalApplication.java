package com.exam.examportal;

import com.exam.examportal.entities.Role;
import com.exam.examportal.entities.User;
import com.exam.examportal.service.UserService;
import com.exam.examportal.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamPortalApplication implements CommandLineRunner {

    @Autowired
    private UserServiceImpl userService;

    public static void main(String[] args) throws Exception {

        SpringApplication.run(ExamPortalApplication.class, args);

    }


    // Command line runner interface provide user this below method and when ever our project run the code which is inside this method automatically.
    @Override
    public void run(String... args) throws Exception {
//        User user = new User();
//        user.setEmail("newuser@gmail.com");
//        user.setEnabled(true);
//        user.setUserName("NewUser");
//        user.setPassword("NewUser");
//        user.setPhone("4567898765");
//        user.setFirstName("New");
//        user.setLastName("User");
//
//
//        Role role = new Role();
//        role.setRoleId(1);
//        role.setRoleName("ADMIN");
//        Set<User> users = new HashSet<>();
//        users.add(user);
//        role.setUser(users);
//
//        user.setRole(role);
//
//        User user1 = userService.createUser(user, role);
//        System.out.println(user1);
    }
}
