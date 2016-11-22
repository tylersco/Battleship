package com.csci4448.MediaManagementSystem.controller;

import com.csci4448.MediaManagementSystem.model.media.MediaDAO;
import com.csci4448.MediaManagementSystem.model.media.MediaDAOImpl;
import com.csci4448.MediaManagementSystem.model.media.Media;
import com.csci4448.MediaManagementSystem.model.review.ReviewDAO;
import com.csci4448.MediaManagementSystem.model.review.ReviewDAOImpl;
import com.csci4448.MediaManagementSystem.model.review.Review;
import com.csci4448.MediaManagementSystem.model.user.UserDAO;
import com.csci4448.MediaManagementSystem.model.user.UserDAOImpl;
import com.csci4448.MediaManagementSystem.model.user.User;
import com.csci4448.MediaManagementSystem.ui.*;
import com.csci4448.MediaManagementSystem.ui.components.*;
import com.csci4448.MediaManagementSystem.model.media.SystemInventory;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private Display display;
    private UserDAO userDAO;
    private MediaDAO mediaDAO;
    private ReviewDAO reviewDAO;

    private User activeUser;
    private Media activeMedia;
    private Review activeReview;

    public MainController() {
        userDAO = new UserDAOImpl();
        mediaDAO = new MediaDAOImpl();
        reviewDAO = new ReviewDAOImpl();
        display = new Display(this);
        loginRequest();
    }

    public void loginRequest() {
        display.setState(new LoginPanel(this));
    }

    public void loginSubmitRequest(String username, String password) {

        if (userDAO.userExists(username, password)) {
            User user = userDAO.getUser(username);
            if (user != null)
                activeUser = user;
            else {
                //ToDo: Database error, couldn't retrieve user, throw error in UI
            }
            storeRequest();
        }
        else {
            //Todo: Pass error message to UI saying that either username or password was incorrect

        }

    }

    public void createAccountRequest() {
        display.setState(new CreateAccountPanel(this));
    }

    public void createAccountSubmitRequest(String firstName, String lastName, String username, String email, String password) {

        if (userDAO.userExists(username, password)) {
            //ToDo: Send error message in UI that user already exists with that username
        }
        else {
            int res = userDAO.addUser(username, password, email, firstName, lastName);
            if (res == -1) {
                //ToDo: Error creating user, send error message to UI
            }
            else {
                User user = userDAO.getUser(res);
                if (user != null)
                    activeUser = user;
                else {
                    //ToDo: Database error, couldn't retrieve user, throw error in UI
                }
                storeRequest();
            }
        }

    }

    public void storeRequest() {
        GridMediaPanel store = new GridMediaPanel(this, 215, 327, 15, 35);
        store.getMenuPanel().getStoreButton().setIsSelected(true);

        List<Media> mediaRecords = SystemInventory.getSystemInventory().getAllMedia();

        for(Media media: mediaRecords) {
            MediaListing listing = new MediaListing(this, media.getMediaID(), media.getImage(), media.getTitle(), media.getPrice());
            store.add(listing);
        }

        display.setState(store);

    }

    public void libraryRequest() {
        GridMediaPanel library = new GridMediaPanel(this, 215, 327, 15, 35);
        library.getMenuPanel().getLibraryButton().setIsSelected(true);

        UserDAOImpl userDAO = new UserDAOImpl();

        List<Media> personalInventory = userDAO.getPersonalInventory(activeUser.getUsername());

        for(Media media: personalInventory) {
            //ToDo: modify MediaListing so it displays owned/rented instead of price
            MediaListing listing = new MediaListing(this, media.getMediaID(), media.getImage(), media.getTitle(), media.getPrice());
            library.add(listing);
        }
        display.setState(library);
    }

    public void adminRequest() {
        // ToDo: Implement this
        /* display.setState(new IndividualMediaPanel(this, null)); */
    }

    public void addFundsRequest() {
        AddFundsPanel funds = new AddFundsPanel(this);
        display.setState(funds);
    }

    public void addFundsSubmitRequest() {
        // ToDo: Implement this
    }

    public void individualMediaRequest(int mediaId) {
        Media media = mediaDAO.getMedia(mediaId);

        if (media == null) {
            //ToDo: Database error, couldn't fetch media, throw error in UI
            return;
        }

        activeMedia = media;

        //ToDo: "Buy 5.00" needs to be replaced with the particular media's action (buy, rent, sell, return)
        IndividualMediaPanel indMedia = new IndividualMediaPanel(this, activeMedia.getMediaID(), activeMedia.getTitle(), activeMedia.getImage(), activeMedia.getDescription(), "Buy $5.00");

        //ToDo: take reviews from media and pass them in on indMedia creation
        ReviewPanel r = new ReviewPanel("Name1", "this is what i have to say", 5);
        ReviewPanel r2 = new ReviewPanel("Name2", "this is what i have to say", 5);
        ReviewPanel r3 = new ReviewPanel("Name3", "this is what i have to say", 5);
        ArrayList<ReviewPanel> rs = new ArrayList<ReviewPanel>();
        rs.add(r);
        rs.add(r2);
        rs.add(r3);
        indMedia.addReviews(rs);

        display.setState(indMedia);
    }

    public void individualMediaActionRequest(int mediaId) {
        //ToDo: request when user clicks buy, rent, return, or sell
    }

    public void reviewMediaRequest(int mediaId) {
        //ToDo: check to see if user is allowed to add a review
    }

    public void reviewMediaSubmitRequest(int mediaId, String reviewText, int rating) {
        //ToDo: add review to media and db
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public boolean hasActiveUser() {
        return activeUser != null;
    }

    public boolean isAdmin() {
        return activeUser != null && activeUser.getIsAdmin();
    }

    public MediaDAO getMediaDAO() {
        return mediaDAO;
    }

    public boolean hasActiveMedia() {
        return activeMedia != null;
    }

    public ReviewDAO getReviewDAO() {
        return reviewDAO;
    }

    public boolean hasActiveReview() {
        return activeReview != null;
    }

}
