package com.csci4448.MediaManagementSystem.controller;

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
        // TODO: Properly set the user and whatnot, for now this generic user will work
        activeUser = new User();

        storeRequest();
    }

    public void createAccountRequest() {
        display.setState(new CreateAccountPanel(this));
    }

    public void createAccountCancelRequest() {
        display.setState(new LoginPanel(this));
    }

    public void createAccountSubmitRequest(String firstName, String lastName, String username, String email, String password) {
        // TODO: Properly set the user and whatnot, for now this generic user will work
        activeUser = new User();

        storeRequest();
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
        display.setState(new IndividualMediaPanel(this, null));
    }

    public void addFundsRequest() {

    }

    public User getUser() { return activeUser; }
    public void setUser(User user) { activeUser = user; }
    public boolean hasUser() { return activeUser != null; }
}
