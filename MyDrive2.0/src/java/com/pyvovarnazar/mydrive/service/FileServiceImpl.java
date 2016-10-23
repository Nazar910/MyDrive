package com.pyvovarnazar.mydrive.service;

import com.pyvovarnazar.mydrive.dao.FileDao;
import com.pyvovarnazar.mydrive.model.File;
import com.pyvovarnazar.mydrive.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;

    @Override
    public void save(File file) {
        fileDao.save(file);
    }

    @Override
    public File findFileById(long id) {
        return fileDao.findById(id);
    }


    @Override
    public Set<File> findByUser(User user) {
        return fileDao.findByUser(user);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        fileDao.deleteById(id);
    }
}
