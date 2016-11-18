package com.csci4448.MediaManagementSystem;



import com.csci4448.MediaManagementSystem.model.media.Media;
import com.csci4448.MediaManagementSystem.model.media.MediaDAOImpl;
import com.csci4448.MediaManagementSystem.model.review.Review;
import com.csci4448.MediaManagementSystem.model.review.ReviewDAOImpl;
import com.csci4448.MediaManagementSystem.model.user.UserDAOImpl;

import java.util.Iterator;
import java.util.Set;

public class DB_Test_Driver {

    public static void main(String[] args) {

        MediaDAOImpl mediaDAO = new MediaDAOImpl();

        UserDAOImpl userDAO = new UserDAOImpl();

        ReviewDAOImpl reviewDAO = new ReviewDAOImpl();

        int userID = userDAO.addUser("cjr", "password","cjr@colorado.edu", "chris", "james");


        int mediaID = mediaDAO.addMedia("admin", "Looper", "a really great movie", "movie", "thriller", 12, 20, 3, true);

        userDAO.increaseAccountBalance("cjr", 15);

        int reviewID = reviewDAO.addReview("Just a really great movie", 5, userID, mediaID);

        Media media = mediaDAO.getMedia(mediaID);


        Set<Review> reviews = media.getReviews();

        System.out.println(media.getTitle());

        System.out.println(reviews.size());

        //returning an empty list, need to make sure media object is tied to its reviews

        for(Review r: reviews)
            System.out.println(r.toString());

    }


}
