package com.pyvovarnazar;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pyvov on 12.09.2016.
 */
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    private String username;
    private String email;
    private String phone;
    private String password;



    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<File> files = new ArrayList<File>();

    public User(String userName, String password, String email, String phone) {
        this.username = userName;
        this.email = email;
        this.phone = phone;
        this.password=password;
    }

    public User() {

    }
    public void addFile(File file){
        files.add(file);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }
}
