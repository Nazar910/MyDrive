package com.pyvovarnazar.mydrive.service;

import com.pyvovarnazar.mydrive.dao.FileDao;
import com.pyvovarnazar.mydrive.dao.RoleDao;
import com.pyvovarnazar.mydrive.dao.UserDao;
import com.pyvovarnazar.mydrive.model.File;
import com.pyvovarnazar.mydrive.model.Role;
import com.pyvovarnazar.mydrive.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        Set<File> files = new HashSet<>();
        files.add(fileDao.getOne(1L));
        user.setFiles(files);
        userDao.save(user);

    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
