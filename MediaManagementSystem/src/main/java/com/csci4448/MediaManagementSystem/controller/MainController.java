package com.csci4448.MediaManagementSystem.controller;

import com.csci4448.MediaManagementSystem.model.media.*;
import com.csci4448.MediaManagementSystem.model.review.ReviewDAO;
import com.csci4448.MediaManagementSystem.model.review.ReviewDAOImpl;
import com.csci4448.MediaManagementSystem.model.review.Review;
import com.csci4448.MediaManagementSystem.model.user.UserDAO;
import com.csci4448.MediaManagementSystem.model.user.UserDAOImpl;
import com.csci4448.MediaManagementSystem.model.user.User;
import com.csci4448.MediaManagementSystem.ui.design.Confirmation;
import com.csci4448.MediaManagementSystem.ui.states.*;
import com.csci4448.MediaManagementSystem.ui.components.*;

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
                display.getActiveState().setPopUpWindow(new ErrorWindow(this, "Server Unavailable", "ok"));
            }
            storeRequest();
        }
        else {
            display.getActiveState().setPopUpWindow(new ErrorWindow(this, "Invalid Information", "ok"));

        }

    }

    public void logoutRequest() {
        display.getActiveState().setPopUpWindow(new ConfirmationWindow(this, Confirmation.LOGOUT));
    }

    public void createAccountRequest() {
        display.setState(new CreateAccountPanel(this));
    }

    public void createAccountSubmitRequest(String firstName, String lastName, String username, String email, String password) {

        if (userDAO.userExists(username, password)) {
            display.getActiveState().setPopUpWindow(new ErrorWindow(this, "Username already taken", "ok"));
        }
        else {
            int res = userDAO.addUser(username, password, email, firstName, lastName);
            if (res == -1) {
                display.getActiveState().setPopUpWindow(new ErrorWindow(this, "Server Error (Creating User)", "ok"));
            }
            else {
                User user = userDAO.getUser(res);
                if (user != null)
                    activeUser = user;
                else {
                    display.getActiveState().setPopUpWindow(new ErrorWindow(this, "Server Unavailable", "ok"));
                }
                storeRequest();
            }
        }

    }

    public void storeRequest() {
        GridMediaPanel store = new GridMediaPanel(this, 215, 327, 15, 35);
        store.getMenuPanel().getStoreButton().setIsSelected(true);

        List<Media> ownedMedia = userDAO.getPersonalInventory(activeUser.getUsername());
        List<Media> mediaRecords = SystemInventory.getSystemInventory().getAllMedia();

        for (Media media : mediaRecords) {
            boolean found = false;
            for (Media mediaOwn : ownedMedia) {
                if (mediaOwn.getMediaID() == media.getMediaID())
                    found = true;
            }

            if (!found) {
                MediaListing listing = new MediaListing(this, media.getMediaID(), media.getImage(), media.getTitle(), "$ " + media.getPrice());
                store.add(listing);
            }

        }

        display.setState(store);
    }

    public void libraryRequest() {
        GridMediaPanel library = new GridMediaPanel(this, 215, 327, 15, 35);
        library.getMenuPanel().getLibraryButton().setIsSelected(true);

        UserDAOImpl userDAO = new UserDAOImpl();

        List<Media> personalInventory = userDAO.getPersonalInventory(activeUser.getUsername());

        for (Media media: personalInventory) {
            String info;
            if (media.getIsRentable()) {
                info = "Rented";
            } else {
                info = "Owned";
            }
            MediaListing listing = new MediaListing(this, media.getMediaID(), media.getImage(), media.getTitle(), info);
            library.add(listing);
        }
        display.setState(library);
    }

    public void searchRequest(String search) {
        //ToDo: implement this
    }

    // Passing in a non-null MediaInfo sets the panel up for editing existing media.
    // Passing in a null MediaInfo sets up the panel with a blank template for new media.
    public void adminRequest(MediaInfo info) {
        AdminEditPanel admin = new AdminEditPanel(this, info);
        admin.getMenuPanel().getAdminButton().setIsSelected(true);

        display.setState(admin);
    }

    public void addFundsRequest() {
        DisplayState state = display.getActiveState();
        if (state instanceof MainContentPanel) {
            state.setPopUpWindow(new AddFundsPanel(this));
        }
    }

    public void addFundsCancelRequest() {
        DisplayState state = display.getActiveState();
        if (state instanceof MainContentPanel) {
            state.setPopUpWindow(null);
        }
    }

    public void addFundsSubmitRequest(int amount) {

        userDAO.increaseAccountBalance(activeUser.getUsername(), amount);

        refreshActiveUser();

        DisplayState state = display.getActiveState();
        if (state instanceof MainContentPanel) {
            state.setPopUpWindow(null);
        }
        storeRequest();
    }

    public void individualMediaRequest(int mediaId) {
        Media media = mediaDAO.getMedia(mediaId);

        if (media == null) {
            display.getActiveState().setPopUpWindow(new ErrorWindow(this, "Media Unavailable", "ok"));
            return;
        }

        activeMedia = media;

        boolean owned = isMediaOwned();

        IndividualMediaPanel indMedia = new IndividualMediaPanel(this);

        MediaInfo mediaInfo = MediaInfo.createFromMedia(activeMedia);
        indMedia.populateMedia(mediaInfo, owned);

        List<Review> reviews = new ArrayList<Review>(activeMedia.getReviews());
        ArrayList<ReviewPanel> rs = new ArrayList<ReviewPanel>();

        for (Review r : reviews) {
            rs.add(new ReviewPanel(r.getUser().getUsername(), r.getReviewText(), r.getRatingValue()));
        }

        indMedia.populateReviews(rs);

        display.setState(indMedia);
    }

    public void individualMediaActionRequest(int mediaId) {
        Media media = mediaDAO.getMedia(mediaId);

        if (media == null) {
            display.getActiveState().setPopUpWindow(new ErrorWindow(this, "Media Unavailable", "ok"));
            return;
        }

        activeMedia = media;

        boolean owned = isMediaOwned();

        Confirmation confirmationType;
        if (activeMedia.getIsRentable() && owned)
            confirmationType = Confirmation.RETURNMEDIA;
        else if (activeMedia.getIsRentable())
            confirmationType = Confirmation.RENTMEDIA;
        else if (!activeMedia.getIsRentable() && owned)
            confirmationType = Confirmation.SELLMEDIA;
        else
            confirmationType = Confirmation.BUYMEDIA;

        DisplayState state = display.getActiveState();
        if (state instanceof IndividualMediaPanel) {
            state.setPopUpWindow(new ConfirmationWindow(this, confirmationType));
        }
    }

    private void buyOrRentRequestErrorHandle(int res) {
        if (res == -4) {
            //Throw error to UI saying that there was a system error
            display.getActiveState().setPopUpWindow(new ErrorWindow(this, "System Error", "ok"));
        } else if (res == -3) {
            //Throw error to UI saying that the media is not correct purchasable type
            display.getActiveState().setPopUpWindow(new ErrorWindow(this, "Media has invalid purchase type", "ok"));
        } else if (res == -2) {
            //Throw error to UI saying that the media is out of stock
            display.getActiveState().setPopUpWindow(new ErrorWindow(this, "Currently out of stock", "ok"));
            // This may change if we implement the waitlist functionality
        } else if (res == -1) {
            //Throw error to UI saying that the user doesn't have enough money in account balance
            display.getActiveState().setPopUpWindow(new ErrorWindow(this, "Insufficient account balance", "ok"));
        }
    }

    private void sellOrReturnRequestErrorHandle(int res) {
        if (res == -3) {
            //Throw error to UI saying that there was a system error
            display.getActiveState().setPopUpWindow(new ErrorWindow(this, "System Error", "ok"));
        } else if (res == -2) {
            //Throw error to UI saying that the media is not correct purchasable type
            display.getActiveState().setPopUpWindow(new ErrorWindow(this, "Media has invalid return type", "ok"));
        } else if (res == -1) {
            //Throw error to UI saying that the media is not currently owned/rented by the user
            display.getActiveState().setPopUpWindow(new ErrorWindow(this, "Item can not be found in your personal inventory", "ok"));
        }
    }

    public void confirmationRequest(boolean isConfirmed, Confirmation confirmationType) {

        if (!isConfirmed) {
            DisplayState state = display.getActiveState();
            // ToDo: The logout confirmation window wouldn't let the user choose the cancel option
            // ToDo: This is the change made to fix this issue. Somebody confirm that this is what we want
            //if (state instanceof IndividualMediaPanel) {
            //    state.setPopUpWindow(null);
            //}
            state.setPopUpWindow(null);
        } else {

            if (confirmationType == Confirmation.RETURNMEDIA || confirmationType == Confirmation.RENTMEDIA || confirmationType == Confirmation.SELLMEDIA || confirmationType == Confirmation.BUYMEDIA) {
                boolean owned = isMediaOwned();
                if (activeMedia.getIsRentable() && owned) {
                    int res = SystemInventory.getSystemInventory().returnMedia(activeUser.getUsername(), activeMedia.getMediaID());
                    sellOrReturnRequestErrorHandle(res);
                } else if (activeMedia.getIsRentable()) {
                    int res = SystemInventory.getSystemInventory().rentMedia(activeUser.getUsername(), activeMedia.getMediaID());
                    buyOrRentRequestErrorHandle(res);
                } else if (!activeMedia.getIsRentable() && owned) {
                    int res = SystemInventory.getSystemInventory().sellMedia(activeUser.getUsername(), activeMedia.getMediaID());
                    sellOrReturnRequestErrorHandle(res);
                } else {
                    int res = SystemInventory.getSystemInventory().buyMedia(activeUser.getUsername(), activeMedia.getMediaID());
                    buyOrRentRequestErrorHandle(res);
                }
                refreshActiveUser();
                refreshActiveMedia();
                libraryRequest();
            } else if (confirmationType == Confirmation.LOGOUT) {
                activeUser = null;
                activeMedia = null;
                loginRequest();
            }
        }
    }

    public void errorThrowRequest(String msg, String close) {
        display.getActiveState().setPopUpWindow(new ErrorWindow(this, msg, close));
    }

    public void errorHandledRequest() {
        display.getActiveState().setPopUpWindow(null);
    }

    public void reviewMediaRequest(int mediaId) {

        if (reviewDAO.userAlreadyReviewed(activeUser.getUsername(), mediaId)) {
            display.getActiveState().setPopUpWindow(new ErrorWindow(this, "You have already reviewed this item", "ok"));
        } else {
            DisplayState state = display.getActiveState();
            if (state instanceof MainContentPanel) {
                state.setPopUpWindow(new EditReviewPanel(this, mediaId));
            }
        }
    }

    public void reviewMediaCancelRequest() {
        DisplayState state = display.getActiveState();
        if (state instanceof MainContentPanel) {
            state.setPopUpWindow(null);
        }
    }

    public void reviewMediaSubmitRequest(int mediaId, String reviewText, int rating) {
        reviewDAO.addReview(reviewText, rating, activeUser.getUserID(), mediaId);
        refreshActiveUser();
        refreshActiveMedia();

        DisplayState state = display.getActiveState();
        if (state instanceof MainContentPanel) {
            state.setPopUpWindow(null);
            individualMediaRequest(mediaId);
        }
    }

    private boolean isMediaOwned() {
        for (Media userMedia : activeUser.getPersonalInventory()) {
            if (userMedia.getMediaID() == activeMedia.getMediaID()) {
                return true;
            }
        }
        return false;
    }

    public User getUser() {
        return activeUser;
    }

    public boolean hasActiveUser() {
        return activeUser != null;
    }

    public void refreshActiveUser() {
        activeUser = userDAO.getUser(activeUser.getUsername());
    }

    public boolean isAdmin() {
        return activeUser != null && activeUser.getIsAdmin();
    }

    public MediaDAO getMediaDAO() {
        return mediaDAO;
    }

    public Media getMedia() {
        return activeMedia;
    }

    public boolean hasActiveMedia() {
        return activeMedia != null;
    }

    public void refreshActiveMedia() {
        activeMedia = mediaDAO.getMedia(activeMedia.getMediaID());
    }

    public ReviewDAO getReviewDAO() {
        return reviewDAO;
    }

    public boolean hasActiveReview() {
        return activeReview != null;
    }

    public void refreshActiveReview() {
        activeReview = reviewDAO.getReview(activeReview.getReviewID());
    }

}
