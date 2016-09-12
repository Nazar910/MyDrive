package com.pyvovarnazar;

import java.util.List;

/**
 * Created by pyvov on 12.09.2016.
 */
public interface FileDAO {
    List<File> list();
    List<File> listTrash();
    List<File> list(String pattern);
    void add(User user);
    void delete(long id);
    void remove(long id, int value);
    void cleanTrash();
    byte[] getFile(long id);
    public long[] getCheckboxes();
}
