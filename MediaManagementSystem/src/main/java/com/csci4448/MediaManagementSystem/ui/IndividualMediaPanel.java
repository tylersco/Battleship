package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.components.MediaImage;
import com.csci4448.MediaManagementSystem.ui.components.TextArea;
import com.csci4448.MediaManagementSystem.ui.components.TextButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndividualMediaPanel extends MainContentPanel implements ActionListener {

    private Font buttonFont = new Font("Helvetice Neue", Font.PLAIN, 15);
    private Color defaultColor = new Color(75, 75, 75, 180);
    private Color enteredColor = new Color(75, 75, 75);
    private Color selectedColor = new Color(75, 75, 75);

    private MediaImage image;
    private TextArea titleText;
    private TextArea descriptionText;
    private TextButton adminButton; // Will only appear if the logged in user is an admin
    private TextButton cancelEditsButton; // Will only appear of the logged in user is an admin AND is editing details
    private TextButton actionButton;
    private JPanel reviews;

    private boolean isAdminEditing = false;

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

        adminButton = new TextButton(this, "Edit Details", buttonFont, defaultColor, enteredColor, selectedColor);
        adminButton.setSize(adminButton.getPreferredSize());
        adminButton.setLocation(25, 475);
        content.add(adminButton);
        if (!controller.getUserDAO().isAdmin())
            adminButton.setVisible(false);

        cancelEditsButton = new TextButton(this, "Cancel Changes", buttonFont, defaultColor, enteredColor, selectedColor);
        cancelEditsButton.setSize(cancelEditsButton.getPreferredSize());
        cancelEditsButton.setLocation(75, 475);
        content.add(cancelEditsButton);
        cancelEditsButton.setVisible(false);

        //ToDo: add reviews to content (take in arraylist of the reviews)

        content.setSize(935, 550);
        updateContentSize();
    }

    private void addReview() {

    }

    private void prepareAdminEditing() {

    }

    private void finishAdminEditing(boolean save) {

    }

    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();

        if (component.equals(adminButton)) {
            isAdminEditing = !isAdminEditing;

            if (isAdminEditing) prepareAdminEditing();
            else finishAdminEditing(true);
        }
        else if (component.equals(cancelEditsButton)) {
            finishAdminEditing(false);
        }
    }
}
