package com.csci4448.MediaManagementSystem.controller;

import com.csci4448.MediaManagementSystem.model.*;
import com.csci4448.MediaManagementSystem.ui.*;

public class MainController {

    private Display display;
    private User activeUser;

    public MainController() {
        display = new Display(this);
        display.displayLogin();
    }

    public static void main(String[] args) {
        MainController c = new MainController();

    }

    public void loginRequest() {
        display.displayLogin();
    }

    public void loginSubmitRequest(String username, String password) {
        //ToDo: check if valid input, if not inform display
        display.removeLogin();
        //ToDo: add user from login, not a new user
        activeUser = new User();
        display.initializeMainLayout();
        display.displayStore();
    }

    public void createAccountRequest() {
        display.removeLogin();
        display.displayCreateAccount();
    }

    public void createAccountCancelRequest() {
        display.removeCreateAccount();
        display.displayLogin();
    }

    public void createAccountSubmitRequest(String firstName, String lastName, String username, String email, String password) {
        //ToDo: check if valid input, if not inform display
        display.removeCreateAccount();
        activeUser = new User();
        display.initializeMainLayout();
    }

    public void storeRequest() {

    }

    public void libraryRequest() {

    }

    public void addFundsRequest() {

    }

    public User getUser() { return activeUser; }
    public void setUser(User user) { activeUser = user; }
    public boolean hasUser() { return activeUser != null; }
}
