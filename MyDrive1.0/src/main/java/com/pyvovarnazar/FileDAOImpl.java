package com.pyvovarnazar;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by pyvov on 12.09.2016.
 */
public class FileDAOImpl implements FileDAO {
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<File> list() {
        return null;
    }

    @Override
    public List<File> listTrash() {
        return null;
    }

    @Override
    public List<File> list(String pattern) {
        return null;
    }

    @Override
    public void add(User user) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void remove(long id, int value) {

    }

    @Override
    public void cleanTrash() {

    }

    @Override
    public byte[] getFile(long id) {
        return new byte[0];
    }

    @Override
    public long[] getCheckboxes() {
        return new long[0];
    }
}
