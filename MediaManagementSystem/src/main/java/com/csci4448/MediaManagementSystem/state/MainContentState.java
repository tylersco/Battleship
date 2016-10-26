package com.csci4448.MediaManagementSystem.state;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.*;

import java.awt.*;

public class MainContentState implements DisplayState {

    MainContentPanel mainContentPanel;

    public void onActivate(MainController controller, Display display) {
        display.setSize(950, 650);
        display.setMinimumSize(new Dimension(950, 425));
        display.setResizable(true);
        display.setLocationRelativeTo(null);

        mainContentPanel = new MainContentPanel(controller);
        display.add(mainContentPanel);
        display.setVisible(true);
    }

    public void update(Update update) {
        //ToDo: add basic methods in MainContentPanel to handle changing panels, or add setState to MainContentPanel and have it set a StoreState, LibraryState, etc. just like the display does
        switch (update) {
            case DISPLAYSTORE:

            case DISPLAYLIBRARY:

            case DISPLAYADMIN:

            default:
                break;
        }
    }

    public void onDeactivate(MainController controller, Display display) {
        display.remove(mainContentPanel);
    }
}
