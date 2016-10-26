package com.csci4448.MediaManagementSystem.state;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.CreateAccountPanel;
import com.csci4448.MediaManagementSystem.ui.Display;

public class CreateAccountState implements DisplayState {

    private CreateAccountPanel createAccountPanel;

    public CreateAccountState() {
        this.createAccountPanel = null;
    }

    public void onActivate(MainController controller, Display display) {
        display.invalidateMainLayout();
        display.setSize(350, 500);
        display.setResizable(false);
        display.setLocationRelativeTo(null);

        createAccountPanel = new CreateAccountPanel(controller);
        display.add(createAccountPanel);

        display.setVisible(true);
    }

    public void onDeactivate(MainController controller, Display display) {
        display.remove(createAccountPanel);
    }

    public CreateAccountPanel getCreateAccountPanel() { return createAccountPanel; }
}
