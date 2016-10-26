package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.*;
import com.csci4448.MediaManagementSystem.model.*;
import com.csci4448.MediaManagementSystem.state.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.*;

public class Display extends JFrame {

    private MainController controller;

    private DisplayState activeState;

    public Display(MainController controller) {
        super("Media");
        this.controller = controller;
        this.activeState = null;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setState(DisplayState state) {
        if (activeState != null)
            activeState.onDeactivate(controller, this);

        activeState = state;
        if (activeState != null)
            activeState.onActivate(controller, this);
    }

    public DisplayState getActiveState() { return activeState; }


}
