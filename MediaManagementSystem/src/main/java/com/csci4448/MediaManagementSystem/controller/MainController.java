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
        DisplayState state = display.getActiveState();
        if (state instanceof MainContentPanel) {
            ((MainContentPanel) state).setPopUpWindow(new AddFundsPanel(this));
        }
    }

    public void addFundsCancelRequest() {
        DisplayState state = display.getActiveState();
        if (state instanceof MainContentPanel) {
            ((MainContentPanel) state).removePopUpWindow();
        }
    }

    public void addFundsSubmitRequest(double amount) {
        //ToDo: add funds to user
        DisplayState state = display.getActiveState();
        if (state instanceof MainContentPanel) {
            ((MainContentPanel) state).removePopUpWindow();
        }
    }

    public void individualMediaRequest(int mediaId) {
        Media media = mediaDAO.getMedia(mediaId);

        if (media == null) {
            //ToDo: Database error, couldn't fetch media, throw error in UI
            return;
        }

        activeMedia = media;

        String mediaAction = "";
        if (activeMedia.getIsRentable() && activeUser.getPersonalInventory().contains(activeMedia))
            mediaAction = "Return Media";
        else if (activeMedia.getIsRentable())
            mediaAction = "Rent $" + media.getPrice();
        else if (!activeMedia.getIsRentable() && activeUser.getPersonalInventory().contains(activeMedia))
            mediaAction = "Sell $" + activeMedia.getSellPrice();
        else
            mediaAction = "Buy $" + activeMedia.getPrice();

        IndividualMediaPanel indMedia = new IndividualMediaPanel(this, activeMedia.getMediaID(), activeMedia.getTitle(), activeMedia.getImage(), activeMedia.getDescription(), mediaAction);

        List<Review> reviews = new ArrayList<Review>(activeMedia.getReviews());
        ArrayList<ReviewPanel> rs = new ArrayList<ReviewPanel>();

        for (Review r : reviews) {
            rs.add(new ReviewPanel(r.getUser().getUsername(), r.getReviewText(), r.getRatingValue()));
        }

        indMedia.addReviews(rs);

        display.setState(indMedia);
    }

    public void individualMediaActionRequest(int mediaId) {
        Media media = mediaDAO.getMedia(mediaId);

        if (media == null) {
            // ToDo: Database error, couldn't fetch media, throw error in UI
            return;
        }

        activeMedia = media;

        String mediaAction = "";
        if (activeMedia.getIsRentable() && activeUser.getPersonalInventory().contains(activeMedia))
            mediaAction = "return";
        else if (activeMedia.getIsRentable())
            mediaAction = "rent";
        else if (!activeMedia.getIsRentable() && activeUser.getPersonalInventory().contains(activeMedia))
            mediaAction = "sell";
        else
            mediaAction = "buy";

        //This is how you add a confirmation window. To remove it, do the same thing but call removePopUpWindow instead
        DisplayState state = display.getActiveState();
        if (state instanceof IndividualMediaPanel) {
            ((MainContentPanel) state).setPopUpWindow(new ConfirmationWindow(this, "Are you sure you want to " + mediaAction + " this?"));
        }
    }

    private void buyOrRentRequestErrorHandle(int res) {
        if (res == -4) {
            // ToDo: Throw error to UI saying that there was a system error
        } else if (res == -3) {
            // ToDo: Throw error to UI saying that the media is not rentable
        } else if (res == -2) {
            // ToDo: Throw error to UI saying that the media is out of stock
            // This may change if we implement the waitlist functionality
        } else if (res == -1) {
            // ToDo: Throw error to UI saying that the user doesn't have enough money in account balance
        }
    }

    public void confirmationRequest(boolean isConfirmed) {
        //ToDo: handle return from confirmation.  true means the user confirmed, false means the user didn't(canceled)
        if (!isConfirmed) {
            DisplayState state = display.getActiveState();
            if (state instanceof IndividualMediaPanel) {
                ((MainContentPanel) state).removePopUpWindow();
            }
        } else {
            if (activeMedia.getIsRentable() && activeUser.getPersonalInventory().contains(activeMedia)) {
                // ToDo: Implement return media in system inventory
            } else if (activeMedia.getIsRentable()) {
                int res = SystemInventory.getSystemInventory().rentMedia(activeUser.getUsername(), activeMedia.getMediaID());
                buyOrRentRequestErrorHandle(res);
            } else if (!activeMedia.getIsRentable() && activeUser.getPersonalInventory().contains(activeMedia)) {
                // ToDo: Implement sell media in system inventory
            } else {
                int res = SystemInventory.getSystemInventory().buyMedia(activeUser.getUsername(), activeMedia.getMediaID());
                buyOrRentRequestErrorHandle(res);
            }
        }
    }

    public void reviewMediaRequest(int mediaId) {
        DisplayState state = display.getActiveState();
        if (state instanceof MainContentPanel) {
            ((MainContentPanel) state).setPopUpWindow(new EditReviewPanel(this,  mediaId));
        }
    }

    public void reviewMediaCancelRequest() {
        DisplayState state = display.getActiveState();
        if (state instanceof MainContentPanel) {
            ((MainContentPanel) state).removePopUpWindow();
        }
    }

    public void reviewMediaSubmitRequest(int mediaId, String reviewText, int rating) {
        //ToDo: add review to media and db
        DisplayState state = display.getActiveState();
        if (state instanceof MainContentPanel) {
            ((MainContentPanel) state).removePopUpWindow();
            individualMediaRequest(mediaId);
        }
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
