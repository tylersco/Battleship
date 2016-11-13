package com.csci4448.MediaManagementSystem.model.user;

import com.csci4448.MediaManagementSystem.model.media.Media;
import com.csci4448.MediaManagementSystem.model.review.Review;

import java.util.Set;

public interface UserInterface {

    int getUserID();
    String getUsername();
    String getEmail();
    String getFirstName();
    String getLastName();
    boolean getIsAdmin();
    int getAccountBalance();
    Set<Review> getReviews();
    Set<Media> getPersonalInventory();

}
