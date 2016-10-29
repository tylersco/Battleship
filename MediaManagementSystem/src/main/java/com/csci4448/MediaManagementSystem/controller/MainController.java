package com.csci4448.MediaManagementSystem.controller;

import com.csci4448.MediaManagementSystem.services.*;
import com.csci4448.MediaManagementSystem.model.*;
import com.csci4448.MediaManagementSystem.ui.*;
import com.csci4448.MediaManagementSystem.ui.components.MediaListing;

public class MainController {

    private Display display;
    private User activeUser;

    public MainController() {
        display = new Display(this);
        display.setState(new LoginPanel(this));
    }

    public static void main(String[] args) {
        MainController c = new MainController();
    }

    public void loginSubmitRequest(String username, String password) {

        UserService userService = new UserServiceImpl();
        User user = userService.getUser(username, password);

        if (user != null) {
            activeUser = user;
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

        UserService userService = new UserServiceImpl();
        User existingUser = userService.getUser(username, password);

        if (existingUser != null) {
            //ToDo: Send error message in UI that user already exists with that username
        }
        else {
            int res = userService.addUser(username, password, email, firstName, lastName, false);
            if (res == -1) {
                //ToDo: Error creating user, send error message to UI
            }
            else {
                activeUser = userService.getUser(username, password);
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

    public User getUser() { return activeUser; }
    public void setUser(User user) { activeUser = user; }
    public boolean hasUser() { return activeUser != null; }
}
