package com.csci4448.MediaManagementSystem.model.user;

import com.csci4448.MediaManagementSystem.model.media.MediaDAO;
import com.csci4448.MediaManagementSystem.model.review.ReviewDAO;

import java.util.Set;

public interface UserInterface {

    int getUserID();
    String getUsername();
    String getEmail();
    String getFirstName();
    String getLastName();
    boolean getIsAdmin();
    int getAccountBalance();

}
