package com.csci4448.MediaManagementSystem;

import com.csci4448.MediaManagementSystem.model.media.Media;
import com.csci4448.MediaManagementSystem.model.media.MediaDAOImpl;
import com.csci4448.MediaManagementSystem.model.media.SystemInventory;
import com.csci4448.MediaManagementSystem.model.review.Review;
import com.csci4448.MediaManagementSystem.model.review.ReviewDAOImpl;
import com.csci4448.MediaManagementSystem.model.user.User;
import com.csci4448.MediaManagementSystem.model.user.UserDAOImpl;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Set;

public class DB_Test_Driver {

    public static void main(String[] args) {

        /*
        NOTE: If test doesn't successfully complete, be sure to delete review and user entries from database before running again
         */

        MediaDAOImpl mediaDAO = new MediaDAOImpl();
        UserDAOImpl userDAO = new UserDAOImpl();
        ReviewDAOImpl reviewDAO = new ReviewDAOImpl();

        // The input test account balance
        int testAccountBalance = 25;

        String testMovie = "The Accountant";
        String testUser = "dSmith";
        // List of reviews
        ArrayList<String> testReviews = new ArrayList<String>();

        testReviews.add("What a boring title.");


        // Create a record for a user, media, and review in the database
        int userID = userDAO.addUser(testUser, "password","dsmith@colorado.edu", "dan", "smith");
        int mediaID = mediaDAO.addMedia("admin", testMovie, "a really great movie", "Movie", "Thriller", 12, 20, 3, true);
        int reviewID = reviewDAO.addReview(testReviews.get(0), 1, userID, mediaID);

        // Check to see if user, media, and review were added successfully
        if(userID == -1){
            System.out.println("Adding user was unsuccessful");
            return;
        }

        if(mediaID == -1){
            System.out.println("Adding media was unsuccessful");
            return;
        }

        if(reviewID == -1){
            System.out.println("Adding review was unsuccessful");
            return;
        }

        User user = userDAO.getUser(userID);


        // Test setAccountBalance method
        user.setAccountBalance(testAccountBalance);

        System.out.println();
        System.out.println("User account balance test:");
        if(user.getAccountBalance() == testAccountBalance){
            System.out.println("Passed");
        }
        else{
            System.out.println("Failed");
        }
        System.out.println();


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
        if(media.getTitle().equals(testMovie)){
            System.out.println("Passed");
        }
        else{
            System.out.println("Failed");
        }
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

        System.out.println("User connected to review record test:");
        if(user.getUsername().equals(testUser)){
            System.out.println("Passed");
        }
        else{
            System.out.println("Failed");
        }
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


        System.out.println("Test successfully completed.");
    }

}
