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
        // TODO: Properly set the user and whatnot, for now this generic user will work
        //if () {
        //    display.getActiveState().update(Update.INVALIDINPUT);
        //}
        activeUser = new User();

        display.setState(new MainContentState());
    }

    public void createAccountRequest() {
        display.setState(new CreateAccountState());
    }

    public void createAccountCancelRequest() {
        display.setState(new LoginState());
    }

    public void createAccountSubmitRequest(String firstName, String lastName, String username, String email, String password) {
        // TODO: Properly set the user and whatnot, for now this generic user will work
        activeUser = new User();

        storeRequest();
    }

    public void storeRequest() {
        DisplayState state = display.getActiveState();
        if (state instanceof MainContentState) {
            state.update(Update.DISPLAYSTORE);
        } else {
            //error as MainContentPanel is not currently displayed
        }
    }

    public void libraryRequest() {
        DisplayState state = display.getActiveState();
        if (state instanceof MainContentState) {
            state.update(Update.DISPLAYLIBRARY);
        } else {
            //error as MainContentPanel is not currently displayed
        }
    }

    public void adminRequest() {
        DisplayState state = display.getActiveState();
        if (state instanceof MainContentState) {
            state.update(Update.DISPLAYADMIN);

        } else {
            //error as MainContentPanel is not currently displayed
        }
    }

    public void addFundsRequest() {
        DisplayState state = display.getActiveState();
        if (state instanceof MainContentState) {
            state.update(Update.DISPLAYADDFUNDS);
        } else {
            //error as MainContentPanel is not currently displayed
        }
    }

    public User getUser() { return activeUser; }
    public void setUser(User user) { activeUser = user; }
    public boolean hasUser() { return activeUser != null; }
}
