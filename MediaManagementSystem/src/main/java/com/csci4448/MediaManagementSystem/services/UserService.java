package com.csci4448.MediaManagementSystem.services;

import com.csci4448.MediaManagementSystem.model.User;

import java.util.Iterator;

public interface UserService {

    int addUser(String username, String password, String email, String firstName, String lastName, Boolean isAdmin);
    void deleteUser(int userID);
    Iterator listUsers();
    User getUser(String username, String password);

}

