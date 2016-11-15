package com.csci4448.MediaManagementSystem.model.user;

public interface UserInterface {

    int getUserID();
    String getUsername();
    String getEmail();
    String getFirstName();
    String getLastName();
    boolean getIsAdmin();
    int getAccountBalance();

}
