package com.exam.examportal.service;

import com.exam.examportal.entities.Role;
import com.exam.examportal.entities.User;

public interface UserService {
    public User createUser(User user, Role role) throws Exception;

    public User getUser(String userName);
}
