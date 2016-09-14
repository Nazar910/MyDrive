package com.pyvovarnazar;

import java.util.List;

/**
 * Created by pyvov on 12.09.2016.
 */
public interface DAO {

    List<File> listTrash();
    List<File> list(String pattern);
    void add(User user);
    void add(File file);
    List<File> getFiles(User user);
    void deleteFile(long id);
    void remove(long id, int value);
    void cleanTrash();
    byte[] getFile(long id);
    public long[] getCheckboxes();
    public User signIn(String username);
    List<User> userList();
    List<File> fileList();
}
