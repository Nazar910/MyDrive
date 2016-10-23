package com.pyvovarnazar.mydrive.dao;

import com.pyvovarnazar.mydrive.model.File;
import com.pyvovarnazar.mydrive.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Created by pyvov on 11.10.2016.
 */
public interface FileDao extends JpaRepository<File, Long> {
    File findById(long id);
    Set<File> findByUser(User user);
    void deleteById(long id);
}
