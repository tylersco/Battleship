package com.csci4448.MediaManagementSystem.controller;

import com.csci4448.MediaManagementSystem.model.*;
import com.csci4448.MediaManagementSystem.ui.*;

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
        display.setState(new GridMediaPanel(this, 10, 10, 10, 10));
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
