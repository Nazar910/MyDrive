package com.pyvovarnazar;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by pyvov on 12.09.2016.
 */
public class DAOImpl implements DAO {
    @Autowired
    private EntityManager entityManager;


    @Override
    public List<File> listTrash() {
        return null;
    }

    @Override
    public List<User> userList() {
        Query query = entityManager.createQuery("select u from User u");
        return (List<User>) query.getResultList();
    }

    @Override
    public List<File> fileList() {
        Query query = entityManager.createQuery("select f from File f");
        return (List<File>) query.getResultList();
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
    public void deleteFile(long id) {
        try {
            entityManager.getTransaction().begin();
            File file = entityManager.find(File.class, id);
            User user = entityManager.find(User.class, file.getUser().getId());
            entityManager.remove(file);
            entityManager.refresh(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }

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

    @Override
    public List<File> getFiles(User user) {
        Query query = entityManager.createQuery("select f from File f where f.user = :user");
        query.setParameter("user", user);
        return (List<File>) query.getResultList();
    }

    @Override
    public void add(File file) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(file);
            entityManager.getTransaction().commit();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public User signIn(String username) {
        Query query;
        if(!username.contains("@")) {
            query = entityManager.createQuery("select u from User u where u.username = :username");
            query.setParameter("username", username);
        }
        else{
            query = entityManager.createQuery("select u from User u where u.email = :email");
            query.setParameter("email", username);
        }

        return ((List<User>) query.getResultList()).get(0);
    }
}
