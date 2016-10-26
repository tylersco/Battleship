package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.model.Media;
import com.csci4448.MediaManagementSystem.ui.components.EnterTextField;
import com.csci4448.MediaManagementSystem.ui.components.MediaListing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndividualMediaPanel extends MainContentPanel implements ActionListener {

    private static Font sTitleFont = null;
    private static Font sDefaultFont = null;
    private static Color sDarkColor = new Color(75, 75, 75);
    private static Color sLightColor = new Color(75, 75, 75, 140);

    private Media media;
    private JPanel view;

    private MediaListing image;
    private EnterTextField title;
    private EnterTextField description;

    public IndividualMediaPanel(MainController controller, Media media) {
        super(controller);
        this.media = media;
        this.view = getContent();

        if (sTitleFont == null)
            sTitleFont = new Font("Helvetice Neue", Font.PLAIN, 25);
        if (sDefaultFont == null)
            sDefaultFont = new Font("Helvetice Neue", Font.PLAIN, 12);

        populatePanel();
    }

    private void populatePanel() {
        image = new MediaListing();
        image.setSize(215, 250);
        image.setLocation(50, 50);
        view.add(image);

        Dimension size;
        title = new EnterTextField(this, "Title", sTitleFont, sDarkColor, sLightColor, false, false);
        size = title.getPreferredSize();
        title.setSize(size);
        title.setLocation(300, 100);
        view.add(title);

        description = new EnterTextField(this, "Description", sDefaultFont, sDarkColor, sLightColor, false, false);
        size = description.getPreferredSize();
        description.setSize(size);
        description.setLocation(320, 130);
        view.add(description);

        view.setSize(500, 500);
        updateContentSize();
    }

    public void actionPerformed(ActionEvent event) {

    }
}
