package com.csci4448.MediaManagementSystem.services;

public interface UserService {
    int addUser(String username, String password, String email, String firstName, String lastName, Boolean isAdmin);
    void deleteUser(int userID);
    void listUsers();
}
