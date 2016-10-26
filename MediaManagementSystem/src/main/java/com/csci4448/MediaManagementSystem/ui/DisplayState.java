package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;

public interface DisplayState {
    // Called when the state is set as the active state
    void onActivate(MainController controller, Display display);

    // Called inorder to update the current state
    //ToDo: change string parameter to some input/update class that can hold values and info for updating
    void update(Update update);

    // Called when the state is no longer the active state
    void onDeactivate(MainController controller, Display display);
}
