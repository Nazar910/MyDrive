package com.pyvovarnazar.mydrive.service;

import com.pyvovarnazar.mydrive.model.File;
import com.pyvovarnazar.mydrive.model.User;

import java.util.Set;

/**
 * Created by pyvov on 11.10.2016.
 */
public interface FileService {
    void save(File file);

    File findFileById(long id);

    Set<File> findByUser(User user);

    void deleteById(long id);
}
