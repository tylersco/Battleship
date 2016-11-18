package com.csci4448.MediaManagementSystem.model.review;

import com.csci4448.MediaManagementSystem.model.GenericDAOImpl;
import com.csci4448.MediaManagementSystem.model.media.Media;
import com.csci4448.MediaManagementSystem.model.media.MediaDAO;
import com.csci4448.MediaManagementSystem.model.media.MediaDAOImpl;
import com.csci4448.MediaManagementSystem.model.user.User;
import com.csci4448.MediaManagementSystem.model.user.UserDAO;
import com.csci4448.MediaManagementSystem.model.user.UserDAOImpl;

public class ReviewDAOImpl
        extends GenericDAOImpl<Review, Integer>
        implements ReviewDAO {

    public int addReview(String textReview, int rating, int userID, int mediaID) {
        /*
        Add a review to the Review table.

        Returns: -1 if unsuccessful, the ID of the created review if successful
         */
        
        UserDAO userDAO = new UserDAOImpl();
        MediaDAO mediaDAO = new MediaDAOImpl();
        
        User user = null;
        Media media = null;
        Integer reviewID = -1;

        user = userDAO.getUser(userID);

        if (user == null)
            return reviewID;

        media = mediaDAO.getMedia(mediaID);

        if (media == null)
            return reviewID;

        Review review = new Review();

        if (rating < 1 || rating > 5)
            return reviewID;

        review.setRatingValue(rating);

        if (textReview == null || textReview.equals(""))
            return reviewID;

        review.setReviewText(textReview);
        review.setUser(user);
        review.setMedia(media);

        reviewID = create(review);


        if (reviewID == null)
            return -1;
        else
            return reviewID;
    }
    
    public Review getReview(int reviewID) {
        /*
        Query the Review table for a specific review.

        Returns: null if unsuccessful, the specifc User object if successful
         */

        return retrieve(reviewID);
    }

}
