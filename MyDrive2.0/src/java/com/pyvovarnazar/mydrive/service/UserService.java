package com.pyvovarnazar.mydrive.service;

import com.pyvovarnazar.mydrive.model.User;

/**
 * Created by pyvov on 11.10.2016.
 */
public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
