package com.csci4448.MediaManagementSystem.state;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.Display;
import com.csci4448.MediaManagementSystem.ui.GridMediaPanel;
import com.csci4448.MediaManagementSystem.ui.MediaListing;

import javax.swing.*;
import java.awt.*;

public class StoreState implements DisplayState {

    // Suggestion: don't store this here, a singleton or other controller for the store contents would be much better
    GridMediaPanel mediaPanel;

    public StoreState() {
        this.mediaPanel = null;
    }

    public void onActivate(MainController controller, Display display) {
        display.ensureMainLayout();

        JPanel mainPanel = display.getMainWindowContent();

        mediaPanel = new GridMediaPanel(controller, 215, 250, 15, 35);
        mediaPanel.setLocation(15, 10);
        for(int i = 0; i < 100; i++) {
            mediaPanel.addMediaListing(new MediaListing());
        }

        mainPanel.add(mediaPanel);
        mainPanel.setPreferredSize(new Dimension(935, mediaPanel.getHeight()));
    }

    public void onDeactivate(MainController controller, Display display) {
        display.getMainWindowContent().remove(mediaPanel);
    }

    public GridMediaPanel getMediaPanel() { return mediaPanel; }
}
