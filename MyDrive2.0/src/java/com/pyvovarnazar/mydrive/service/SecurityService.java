package com.pyvovarnazar.mydrive.service;

/**
 * Created by pyvov on 11.10.2016.
 */
public interface SecurityService {
    String findLoggedUsername();

    void autoLogin(String username, String password);
}
