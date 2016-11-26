package com.csci4448.MediaManagementSystem;

import com.csci4448.MediaManagementSystem.model.media.Media;
import com.csci4448.MediaManagementSystem.model.media.MediaDAOImpl;
import com.csci4448.MediaManagementSystem.model.review.Review;
import com.csci4448.MediaManagementSystem.model.review.ReviewDAOImpl;
import com.csci4448.MediaManagementSystem.model.user.User;
import com.csci4448.MediaManagementSystem.model.user.UserDAOImpl;

import java.util.Set;

public class DB_Test_Driver {

    public static void main(String[] args) {

        MediaDAOImpl mediaDAO = new MediaDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();
        ReviewDAOImpl reviewDAO = new ReviewDAOImpl();

        // Create a record for a user, media, and review in the database
        int userID = userDAO.addUser("cjr", "password","cjr@colorado.edu", "chris", "james");
        int mediaID = mediaDAO.addMedia("admin", "Looper", "a really great movie", "movie", "thriller", 12, 20, 3, true);
        int reviewID = reviewDAO.addReview("Just a really great movie", 5, userID, mediaID);

        User user = userDAO.getUser(userID);

        // Test setAccountBalance method
        user.setAccountBalance(15);

        System.out.println("User account balance:");
        System.out.println(user.getAccountBalance());

        System.out.println("MediaID: ");
        System.out.println(mediaID);


        // Get the media object we created and check that the review object is connected through the foreign key
        Media media = mediaDAO.getMedia(mediaID);
        Set<Review> reviews = media.getReviews();

        System.out.println();
        System.out.println("Reviews connected to media record:");

        for(Review r: reviews)
            System.out.println(r.toString());
        System.out.println();

        // Get the review object we created and check that the media object is connected
        Review review = reviewDAO.getReview(reviewID);
        media = review.getMedia();

        System.out.println("Media connected to review record:");
        System.out.println(media.toString());
        System.out.println();

        // Get the user object we created and check that the review object is connected
        reviews = user.getReviews();

        System.out.println("Reviews connected to user record:");

        for(Review r: reviews)
            System.out.println(r.toString());
        System.out.println();

        // Get the review object we created and check that the user object is connected
        review = reviewDAO.getReview(reviewID);
        user = review.getUser();

        System.out.println("User connected to review record:");
        System.out.println(user.toString());
        System.out.println();

        // many-to-many between media and user
        media.addUserOwner(user);
        user.addPersonalMedia(media);

        System.out.println("Users connected to media record:");
        System.out.println(user.toString());
        System.out.println();
        System.out.println("Medias connected to user record:");
        System.out.println(media.toString());
        System.out.println();

        // Delete all created records from the database

        // NOTE: We do not need to delete the User, since this will not occur in the system
        reviewDAO.delete(review);
        mediaDAO.deleteMedia(mediaID, "admin");

    }

}
