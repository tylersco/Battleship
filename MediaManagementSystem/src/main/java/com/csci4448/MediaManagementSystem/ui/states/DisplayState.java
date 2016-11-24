package com.csci4448.MediaManagementSystem.ui.states;

import com.csci4448.MediaManagementSystem.controller.MainController;

public interface DisplayState {
    // Called when the state is set as the active state
    void onActivate(MainController controller, Display display);

    // Called when the state is no longer the active state
    void onDeactivate(MainController controller, Display display);
}
