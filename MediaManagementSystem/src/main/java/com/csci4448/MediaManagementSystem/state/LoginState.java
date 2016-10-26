package com.csci4448.MediaManagementSystem.state;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.Display;
import com.csci4448.MediaManagementSystem.ui.LoginPanel;

public class LoginState implements DisplayState {

    private LoginPanel loginPanel;

    public LoginState() {
        this.loginPanel = null;
    }

    public void onActivate(MainController controller, Display display) {
        display.setSize(350, 300);
        display.setResizable(false);
        display.setLocationRelativeTo(null);

        loginPanel = new LoginPanel(controller);
        display.add(loginPanel);

        display.setVisible(true);
    }

    public void update(Update update) {
        switch (update) {
            case INVALIDINPUT:
                //ToDo: notify of invalid input
            default:
                break;
        }
    }

    public void onDeactivate(MainController controller, Display display) {
        display.remove(loginPanel);
    }

    public LoginPanel getLoginPanel() { return loginPanel; }
}
