package com.csci4448.MediaManagementSystem.ui.states;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.components.ErrorWindow;

import javax.swing.*;
import java.awt.*;

public interface DisplayState {
    // Called when the state is set as the active state
    void onActivate(MainController controller, Display display);

    //The view that the display will show when the state is activated
    JComponent getStateView();

    //
    void setPopUpWindow(JComponent errorWindow);

    // Called when the state is no longer the active state
    void onDeactivate(MainController controller, Display display);
}
