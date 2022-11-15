package com.exam.examportal.repository;

import com.exam.examportal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByUserName(String username);
}
