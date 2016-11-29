package com.csci4448.MediaManagementSystem.ui.states;

import com.csci4448.MediaManagementSystem.controller.*;

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
        if (activeState != null) {
            activeState.onDeactivate(controller, this);
            remove(activeState.getStateView());
        }

        activeState = state;
        if (activeState != null) {
            activeState.onActivate(controller, this);
            add(activeState.getStateView());
            setVisible(true);
        }

    }

    public DisplayState getActiveState() { return activeState; }


}
