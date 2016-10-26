package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;

import javax.swing.*;

public class AdminEditPanel extends MainContentPanel {

    private JPanel view;

    public AdminEditPanel(MainController controller) {
        super(controller);
        view = getContent();
        //Todo: add all needed components to JPanel view

        //This adds the panel that should contain all your components for the admin panel
        updateContentSize();
    }
}
