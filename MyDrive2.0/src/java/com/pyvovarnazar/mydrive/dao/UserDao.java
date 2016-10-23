package com.pyvovarnazar.mydrive.dao;

import com.pyvovarnazar.mydrive.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}