package com.csci4448.MediaManagementSystem.controller;

import com.csci4448.MediaManagementSystem.model.user.*;
import com.csci4448.MediaManagementSystem.ui.*;
import com.csci4448.MediaManagementSystem.ui.components.MediaListing;

public class MainController {

    private Display display;
    private UserDAO userDAO;

    public MainController() {
        display = new Display(this);
        display.setState(new LoginPanel(this));
        userDAO = new UserDAO();
    }

    public static void main(String[] args) {
        MainController c = new MainController();
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

    public void createAccountCancelRequest() {
        display.setState(new LoginPanel(this));
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
        GridMediaPanel store = new GridMediaPanel(this, 215, 250, 15, 35);
        //ToDo: populate store with media in db
        for (int i = 0; i < 100; i++) {
            store.addMediaListing(new MediaListing());
        }
        display.setState(store);
    }

    public void libraryRequest() {

    }

    public void adminRequest() {
        display.setState(new AdminEditPanel(this));
    }

    public void addFundsRequest() {

    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
    public boolean hasActiveUser() { return userDAO.activeUserSet(); }
}
