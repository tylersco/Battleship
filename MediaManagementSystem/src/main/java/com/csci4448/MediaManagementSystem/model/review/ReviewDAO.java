package com.csci4448.MediaManagementSystem.model.review;

public interface ReviewDAO {

    int addReview(String textReview, int rating, int userID, int mediaID);
    Review getReview(int reviewID);
    boolean userAlreadyReviewed(String username, int mediaID);

}
