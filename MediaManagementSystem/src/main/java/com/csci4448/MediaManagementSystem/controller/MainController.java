package com.csci4448.MediaManagementSystem.controller;

import com.csci4448.MediaManagementSystem.model.user.*;
import com.csci4448.MediaManagementSystem.ui.*;
import com.csci4448.MediaManagementSystem.ui.components.*;

public class MainController {

    private Display display;
    private UserDAO userDAO;

    public MainController() {
        display = new Display(this);
        loginRequest();
        userDAO = new UserDAO();
    }

    public static void main(String[] args) {
        MainController c = new MainController();
    }

    public void loginRequest() {
        display.setState(new LoginPanel(this));
    }

    public void loginSubmitRequest(String username, String password) {

        if (userDAO.userExists(username, password)) {
            userDAO.setActiveUser(username, password);
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
            int res = userDAO.addUser(username, password, email, firstName, lastName, false);
            if (res == -1) {
                //ToDo: Error creating user, send error message to UI
            }
            else {
                userDAO.setActiveUser(username, password);
                storeRequest();
            }
        }

    }

    public void storeRequest() {
        GridMediaPanel store = new GridMediaPanel(this, 215, 327, 15, 35);
        store.getMenuPanel().getStoreButton().setIsSelected(true);
        //ToDo: populate store with media in db
        for (int i = 0; i < 20; i++) {
            MediaListing listing = new MediaListing(this, 1234, "src/main/resources/test.png", "Title", 3.99);
            store.add(listing);
        }
        display.setState(store);
    }

    public void libraryRequest() {
        GridMediaPanel library = new GridMediaPanel(this, 215, 327, 15, 35);
        library.getMenuPanel().getLibraryButton().setIsSelected(true);
        //ToDo: populate library with media in users inventory
        for (int i = 0; i < 3; i++) {
            //ToDo: modify MediaListing so it displays owned/rented instead of price
            MediaListing listing = new MediaListing(this, 1234, "src/main/resources/test.png", "Title", 3.99);
            library.add(listing);
        }
        display.setState(library);
    }

    public void adminRequest() {
        display.setState(new AdminEditPanel(this));
    }

    public void addFundsRequest() {

    }

    public void individualMediaRequest(int mediaId) {
        //ToDo: get info for particular media, mediaId, and add info to panel
        IndividualMediaPanel indMedia = new IndividualMediaPanel(this, mediaId, "src/main/resources/test.png", "Title", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque turpis turpis, gravida sed neque nec, ultrices sodales est. Etiam ornare, erat non ullamcorper tempus, ligula sem tincidunt justo, ac rutrum magna felis sit amet nibh. Praesent imperdiet facilisis fermentum. Mauris porta finibus arcu, sit amet venenatis ipsum lacinia sed. Duis feugiat ante eu lobortis mollis.");
        display.setState(indMedia);
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
    public boolean hasActiveUser() { return userDAO.activeUserSet(); }
}
