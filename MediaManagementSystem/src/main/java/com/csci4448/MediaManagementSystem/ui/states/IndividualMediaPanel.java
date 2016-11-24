package com.csci4448.MediaManagementSystem.ui.states;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.components.*;
import com.csci4448.MediaManagementSystem.ui.components.TextPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IndividualMediaPanel extends MainContentPanel implements ActionListener {

    private int mediaId;

    private Font buttonFont = new Font("Helvetice Neue", Font.PLAIN, 15);
    private Color defaultColor = new Color(75, 75, 75, 180);
    private Color enteredColor = new Color(75, 75, 75);
    private Color selectedColor = new Color(75, 75, 75);

    private MediaImage image;
    private TextPane titleText;
    private TextPane descriptionText;
    private TextButton addReviewButton;
    private TextButton actionButton;

    private TextButton adminButton; // Will only appear if the logged in user is an admin
    private TextButton saveEditsButton; // Will only appear if the logged in user is an admin AND is editing details
    private TextButton cancelEditsButton; // Will only appear if the logged in user is an admin AND is editing details
    private JPanel reviews;

    private boolean isAdminEditing = false;

    public IndividualMediaPanel(MainController controller, int mediaId, String title, String imagePath, String description, String action) {
        super(controller);
        //ToDo: potentially convert this class to use mediaListing
        this.mediaId = mediaId;

        JLayeredPane content = getContent();

        titleText = new TextPane(title, new Font("Helvetice Neue", Font.PLAIN, 35), new Color(75, 75, 75));
        titleText.setSize(500, (int)titleText.getPreferredSize().getHeight());
        titleText.setLocation(375, 25);
        content.add(titleText);

        image = new MediaImage(imagePath);
        image.loadMediaImage(325, 456);
        image.setLocation(15, 15);
        content.add(image, 0);

        descriptionText = new TextPane(description, new Font("Helvetice Neue", Font.PLAIN, 18), new Color(75, 75, 75, 200));
        descriptionText.setLineWrap(true);
        descriptionText.setSize(350, 250);
        descriptionText.setLocation(400, 85);
        content.add(descriptionText);

        actionButton = new TextButton(this, action, buttonFont, defaultColor, enteredColor, selectedColor);
        actionButton.setSize(actionButton.getPreferredSize());
        actionButton.setLocation(700, 345);
        content.add(actionButton);

        addReviewButton = new TextButton(this, "Add Review +", buttonFont, defaultColor, enteredColor, selectedColor);
        addReviewButton.setSize(addReviewButton.getPreferredSize());
        addReviewButton.setLocation(800, 500);
        content.add(addReviewButton);

        adminButton = new TextButton(this, "Edit Details", buttonFont, defaultColor, enteredColor, selectedColor);
        adminButton.setSize(adminButton.getPreferredSize());
        adminButton.setLocation(25, 475);
        content.add(adminButton);
        if (!controller.isAdmin())
            adminButton.setVisible(false);

        saveEditsButton = new TextButton(this, "Save Changes", buttonFont, defaultColor, enteredColor, selectedColor);
        saveEditsButton.setSize(saveEditsButton.getPreferredSize());
        saveEditsButton.setLocation(25, 475);
        content.add(saveEditsButton);
        saveEditsButton.setVisible(false);

        cancelEditsButton = new TextButton(this, "Cancel Changes", buttonFont, defaultColor, enteredColor, selectedColor);
        cancelEditsButton.setSize(cancelEditsButton.getPreferredSize());
        cancelEditsButton.setLocation(145, 475);
        content.add(cancelEditsButton);
        cancelEditsButton.setVisible(false);

        //ToDo: add reviews to content (take in arraylist of the reviews)

        content.setSize(935, 550);
        updateContentSize();
    }

    //ToDo: make private, constructor should take reviews
    public void addReviews(ArrayList<ReviewPanel> reviews) {
        JLayeredPane content = getContent();
        for (int i = 0; i < reviews.size(); i++) {
            ReviewPanel review = reviews.get(i);
            review.setSize(900, 65);
            review.setLocation(15, 525 + (i * 65) + (i * 5));
            content.add(review);
        }
        content.setSize(935, reviews.size()*70 + 550);
        updateContentSize();
    }

    private void prepareAdminEditing() {
        titleText.setEditable(true);
        descriptionText.setEditable(true);
        adminButton.setVisible(false);
        saveEditsButton.setVisible(true);
        cancelEditsButton.setVisible(true);
    }

    private void finishAdminEditing(boolean save) {
        titleText.setEditable(false);
        descriptionText.setEditable(false);
        adminButton.setVisible(true);
        saveEditsButton.setVisible(false);
        cancelEditsButton.setVisible(false);

        if (save) {
            // TODO: As we add more editable fields, make sure to save the changes to those as well

            // ToDo: This is currently commented out. The MediaDAO2 is in progress and should have a method for editing media
            //Media media = getController().getMediaDAO().getActiveMedia();
            //media.setTitle(titleText.getText());
            //media.setDescription(descriptionText.getText());

            //getController().getMediaDAO().saveMediaChangesToDatabase();
        }
        else {
            // TODO: As we add more editable fields, make sure to revert the changes to those as well

            //Media media = getController().getMediaDAO().getActiveMedia();
            //titleText.setText(media.getTitle());
            //descriptionText.setText(media.getDescription());
        }
    }

    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();

        if (component.equals(adminButton)) {
            isAdminEditing = true;
            prepareAdminEditing();
        }
        else if (component.equals(saveEditsButton)) {
            // TODO: Ask user if they want to commit the changes
            isAdminEditing = false;
            finishAdminEditing(true);
        }
        else if (component.equals(cancelEditsButton)) {
            // TODO: Ask user if they want to cancel the changes
            isAdminEditing = false;
            finishAdminEditing(false);
        }
        else if (component.equals(addReviewButton)) {
            getController().reviewMediaRequest(mediaId);
        }
        else if (component.equals(actionButton)) {
            getController().individualMediaActionRequest(mediaId);
        }

    }
}
