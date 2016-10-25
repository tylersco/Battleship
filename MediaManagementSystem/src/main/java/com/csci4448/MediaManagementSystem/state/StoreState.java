package com.csci4448.MediaManagementSystem.state;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.Display;

public class StoreState implements DisplayState {


    public StoreState() {

    }

    public void onActivate(MainController controller, Display display) {
        display.ensureMainLayout();
    }

    public void onDeactivate(MainController controller, Display display) {

    }
}
