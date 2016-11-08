package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.components.MediaImage;
import com.csci4448.MediaManagementSystem.ui.components.TextArea;
import com.csci4448.MediaManagementSystem.ui.components.TextButton;

import javax.swing.*;
import java.awt.*;

public class IndividualMediaPanel extends MainContentPanel {

    private MediaImage image;
    private TextArea titleText;
    private TextArea descriptionText;
    private TextButton actionButton;
    private JPanel reviews;

    public IndividualMediaPanel(MainController controller, int mediaId, String imagePath, String title, String description) {
        super(controller);

        JPanel content = getContent();

        titleText = new TextArea(title, new Font("Helvetice Neue", Font.PLAIN, 35), new Color(75, 75, 75));
        titleText.setSize(titleText.getPreferredSize());
        titleText.setLocation(375, 25);
        content.add(titleText);

        image = new MediaImage(imagePath);
        image.loadMediaImage(325, 456);
        image.setLocation(15, 15);
        content.add(image);

        descriptionText = new TextArea(description, new Font("Helvetice Neue", Font.PLAIN, 18), new Color(75, 75, 75, 200));
        descriptionText.setLineWrap(true);
        descriptionText.setSize(350, 250);
        descriptionText.setLocation(400, 85);
        content.add(descriptionText);

        //ToDo: add reviews to content (take in arraylist of the reviews)

        content.setSize(935, 550);
        updateContentSize();
    }

    private void addReview() {

    }
}
