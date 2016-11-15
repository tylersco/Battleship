package com.csci4448.MediaManagementSystem.model.review;

import com.csci4448.MediaManagementSystem.model.media.Media;
import com.csci4448.MediaManagementSystem.model.media.MediaDAO;
import com.csci4448.MediaManagementSystem.model.user.User;
import com.csci4448.MediaManagementSystem.model.user.UserDAO;

public interface ReviewInterface {

    int getReviewID();
    int getRatingValue();
    String getReviewText();

}
