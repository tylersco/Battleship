package com.csci4448.MediaManagementSystem.model.review;

import com.csci4448.MediaManagementSystem.model.GenericDAO;

public interface ReviewDAO extends GenericDAO<Review, Integer> {

    int addReview(String textReview, int rating, int userID, int mediaID);
    Review getReview(int reviewID);
    boolean userAlreadyReviewed(String username, int mediaID);

}
