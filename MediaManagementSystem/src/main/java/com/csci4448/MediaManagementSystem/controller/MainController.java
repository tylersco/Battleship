package com.csci4448.MediaManagementSystem.controller;

import com.csci4448.MediaManagementSystem.model.media.*;
import com.csci4448.MediaManagementSystem.model.review.ReviewDAO;
import com.csci4448.MediaManagementSystem.model.review.ReviewDAOImpl;
import com.csci4448.MediaManagementSystem.model.review.Review;
import com.csci4448.MediaManagementSystem.model.user.UserDAO;
import com.csci4448.MediaManagementSystem.model.user.UserDAOImpl;
import com.csci4448.MediaManagementSystem.model.user.User;
import com.csci4448.MediaManagementSystem.ui.states.*;
import com.csci4448.MediaManagementSystem.ui.components.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
                display.getActiveState().setErrorWindow(new ErrorWindow(this, "Server Unavailable", "ok"));
            }
            storeRequest();
        }
        else {
            display.getActiveState().setErrorWindow(new ErrorWindow(this, "Invalid Information", "ok"));

        }

    }

    public void createAccountRequest() {
        display.setState(new CreateAccountPanel(this));
    }

    public void createAccountSubmitRequest(String firstName, String lastName, String username, String email, String password) {

        if (userDAO.userExists(username, password)) {
            display.getActiveState().setErrorWindow(new ErrorWindow(this, "Username already taken", "ok"));
        }
        else {
            int res = userDAO.addUser(username, password, email, firstName, lastName);
            if (res == -1) {
                display.getActiveState().setErrorWindow(new ErrorWindow(this, "Server Error (Creating User)", "ok"));
            }
            else {
                User user = userDAO.getUser(res);
                if (user != null)
                    activeUser = user;
                else {
                    display.getActiveState().setErrorWindow(new ErrorWindow(this, "Server Unavailable", "ok"));
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

    public void addFundsSubmitRequest(int amount) {
        userDAO.increaseAccountBalance(activeUser.getUsername(), amount);

        DisplayState state = display.getActiveState();
        if (state instanceof MainContentPanel) {
            ((MainContentPanel) state).removePopUpWindow();
        }
    }

    public void individualMediaRequest(int mediaId) {
        Media media = mediaDAO.getMedia(mediaId);

        if (media == null) {
            display.getActiveState().setErrorWindow(new ErrorWindow(this, "Media Unavailable", "ok"));
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

        IndividualMediaPanel indMedia = new IndividualMediaPanel(this, mediaAction);

        indMedia.populateMedia(MediaInfo.createFromMedia(activeMedia));

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
            display.getActiveState().setErrorWindow(new ErrorWindow(this, "Media Unavailable", "ok"));
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
            ((MainContentPanel) state).setPopUpWindow(new ConfirmationWindow(this, "Are you sure you want to " + mediaAction + " this?", "Yes", "No"));
        }
    }

    private void buyOrRentRequestErrorHandle(int res) {
        if (res == -4) {
            //Throw error to UI saying that there was a system error
            display.getActiveState().setErrorWindow(new ErrorWindow(this, "System Error", "ok"));
        } else if (res == -3) {
            //Throw error to UI saying that the media is not correct purchasable type
            display.getActiveState().setErrorWindow(new ErrorWindow(this, "Media has invalid purchase type", "ok"));
        } else if (res == -2) {
            //Throw error to UI saying that the media is out of stock
            display.getActiveState().setErrorWindow(new ErrorWindow(this, "Currently out of stock", "ok"));
            // This may change if we implement the waitlist functionality
        } else if (res == -1) {
            //Throw error to UI saying that the user doesn't have enough money in account balance
            display.getActiveState().setErrorWindow(new ErrorWindow(this, "Insufficient account balance", "ok"));
        }
    }

    private void sellOrReturnRequestErrorHandle(int res) {
        if (res == -3) {
            //Throw error to UI saying that there was a system error
            display.getActiveState().setErrorWindow(new ErrorWindow(this, "System Error", "ok"));
        } else if (res == -2) {
            //Throw error to UI saying that the media is not correct purchasable type
            display.getActiveState().setErrorWindow(new ErrorWindow(this, "Media has invalid return type", "ok"));
        } else if (res == -1) {
            //Throw error to UI saying that the media is not currently owned/rented by the user
            display.getActiveState().setErrorWindow(new ErrorWindow(this, "Item can not be found in your personal inventory", "ok"));
        }
    }

    public void confirmationRequest(boolean isConfirmed) {

        if (!isConfirmed) {
            DisplayState state = display.getActiveState();
            if (state instanceof IndividualMediaPanel) {
                ((MainContentPanel) state).removePopUpWindow();
            }
        } else {
            if (activeMedia.getIsRentable() && activeUser.getPersonalInventory().contains(activeMedia)) {
                int res = SystemInventory.getSystemInventory().returnMedia(activeUser.getUsername(), activeMedia.getMediaID());
                sellOrReturnRequestErrorHandle(res);
            } else if (activeMedia.getIsRentable()) {
                int res = SystemInventory.getSystemInventory().rentMedia(activeUser.getUsername(), activeMedia.getMediaID());
                buyOrRentRequestErrorHandle(res);
            } else if (!activeMedia.getIsRentable() && activeUser.getPersonalInventory().contains(activeMedia)) {
                int res = SystemInventory.getSystemInventory().sellMedia(activeUser.getUsername(), activeMedia.getMediaID());
                sellOrReturnRequestErrorHandle(res);
            } else {
                int res = SystemInventory.getSystemInventory().buyMedia(activeUser.getUsername(), activeMedia.getMediaID());
                buyOrRentRequestErrorHandle(res);
            }
        }
    }

    public void errorThrowRequest(String msg, String close) {
        display.getActiveState().setErrorWindow(new ErrorWindow(this, msg, close));
    }

    public void errorHandledRequest() {
        display.getActiveState().setErrorWindow(null);
    }

    public void reviewMediaRequest(int mediaId) {
        //ToDo: potentially move to the model as a method
        boolean userAlreadyReviewedMedia = false;
        Set<Review> reviews = activeMedia.getReviews();
        for (Review r : reviews) {
            if (r.getUser().getUserID() == activeUser.getUserID()) {
                userAlreadyReviewedMedia = true;
                break;
            }
        }

        if (userAlreadyReviewedMedia) {
            display.getActiveState().setErrorWindow(new ErrorWindow(this, "You have already reviewed this item", "ok"));
        } else {
            DisplayState state = display.getActiveState();
            if (state instanceof MainContentPanel) {
                ((MainContentPanel) state).setPopUpWindow(new EditReviewPanel(this, mediaId));
            }
        }
    }

    public void reviewMediaCancelRequest() {
        DisplayState state = display.getActiveState();
        if (state instanceof MainContentPanel) {
            ((MainContentPanel) state).removePopUpWindow();
        }
    }

    public void reviewMediaSubmitRequest(int mediaId, String reviewText, int rating) {
        reviewDAO.addReview(reviewText, rating, activeUser.getUserID(), mediaId);

        DisplayState state = display.getActiveState();
        if (state instanceof MainContentPanel) {
            ((MainContentPanel) state).removePopUpWindow();
            individualMediaRequest(mediaId);
        }
    }

    public User getUser() {
        return activeUser;
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
