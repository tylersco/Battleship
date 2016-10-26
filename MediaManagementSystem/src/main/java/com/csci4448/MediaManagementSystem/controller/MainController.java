package com.csci4448.MediaManagementSystem.controller;

import com.csci4448.MediaManagementSystem.model.*;
import com.csci4448.MediaManagementSystem.state.*;
import com.csci4448.MediaManagementSystem.ui.*;

public class MainController {

    private Display display;
    private User activeUser;

    public MainController() {
        display = new Display(this);
        display.setState(new LoginState());
    }

    public static void main(String[] args) {
        MainController c = new MainController();
    }

    public void loginSubmitRequest(String username, String password) {
        //ToDo: check if valid input, if not inform display
        //display.removeLogin();
        //ToDo: add user from login, not a new user
        //activeUser = new User();
        //display.initializeMainLayout();
        //storeRequest();

        // TODO: Properly set the user and whatnot, for now this generic user will work
        activeUser = new User();

        storeRequest();
    }

    public void createAccountRequest() {
        display.setState(new CreateAccountState());
    }

    public void createAccountCancelRequest() {
        display.setState(new LoginState());
    }

    public void createAccountSubmitRequest(String firstName, String lastName, String username, String email, String password) {
        //ToDo: check if valid input, if not inform display
        //display.removeCreateAccount();
        //activeUser = new User();
        //display.initializeMainLayout();

        // TODO: Properly set the user and whatnot, for now this generic user will work
        activeUser = new User();

        storeRequest();
    }

    public void storeRequest() {
        display.setState(new StoreState());
    }

    public void libraryRequest() {

    }

    public void adminRequest() {

    }

    public void addFundsRequest() {

    }

    public User getUser() { return activeUser; }
    public void setUser(User user) { activeUser = user; }
    public boolean hasUser() { return activeUser != null; }
}
