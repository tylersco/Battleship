package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.model.media.Media;
import com.csci4448.MediaManagementSystem.model.media.MediaInfo;
import com.csci4448.MediaManagementSystem.ui.components.MediaImage;
import com.csci4448.MediaManagementSystem.ui.components.TextArea;
import com.csci4448.MediaManagementSystem.ui.components.TextButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndividualMediaPanel extends MainContentPanel implements ActionListener {

    private final String DEFAULT_IMAGE_PATH = "src/main/resources/test.png";

    private Font buttonFont = new Font("Helvetice Neue", Font.PLAIN, 15);
    private Color defaultColor = new Color(75, 75, 75, 180);
    private Color enteredColor = new Color(75, 75, 75);
    private Color selectedColor = new Color(75, 75, 75);

    private MediaImage image;
    private TextArea titleText;
    private TextArea descriptionText;
    private TextButton adminButton; // Will only appear if the logged in user is an admin
    private TextButton saveEditsButton; // Will only appear if the logged in user is an admin AND is editing details
    private TextButton cancelEditsButton; // Will only appear if the logged in user is an admin AND is editing details
    private TextButton actionButton;
    private JPanel reviews;

    private boolean isAdminEditing = false;

    // Saved media information (for reverting during admin edits)
    // TODO: Maybe use a Command pattern for easy undo commands
    private MediaInfo savedMediaInfo = MediaInfo.createDefault();

    public IndividualMediaPanel(MainController controller) {
        super(controller);

        JPanel content = getContent();

        titleText = new TextArea(savedMediaInfo.getTitle(), new Font("Helvetice Neue", Font.PLAIN, 35), new Color(75, 75, 75));
        titleText.setSize(500, (int)titleText.getPreferredSize().getHeight());
        titleText.setLocation(375, 25);
        content.add(titleText);

        image = new MediaImage(DEFAULT_IMAGE_PATH);
        image.loadMediaImage(325, 456);
        image.setLocation(15, 15);
        content.add(image);

        descriptionText = new TextArea(savedMediaInfo.getDescription(), new Font("Helvetice Neue", Font.PLAIN, 18), new Color(75, 75, 75, 200));
        descriptionText.setLineWrap(true);
        descriptionText.setSize(350, 250);
        descriptionText.setLocation(400, 85);
        content.add(descriptionText);

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

        content.setSize(935, 550);
        updateContentSize();
    }

    public void populateMedia(MediaInfo info) {
        savedMediaInfo = info;

        titleText.setText(info.getTitle());
        descriptionText.setText(info.getDescription());
        image.setImagePath(info.getImage());
        image.loadMediaImage(325, 456);
    }

    public void populateReviews() {

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

        if (save) { // Save the changes to the database
            // TODO: As we add more editable fields, make sure to save the changes to those as well
            savedMediaInfo = MediaInfo.createFromInfo(
                    savedMediaInfo.getMediaID(), titleText.getText(), descriptionText.getText(),
                    image.getImagePath(), savedMediaInfo.getType(), savedMediaInfo.getGenre(),
                    savedMediaInfo.getPrice(), savedMediaInfo.getSellPrice(), savedMediaInfo.getInventoryCount(),
                    savedMediaInfo.getIsRentable()
            );

            // TODO: Use the MediaDAO object to save the changes
            System.out.println("MEDIA: Changes to media have been saved.");
        }
        else { // Revert all of the changes
            // TODO: As we add more editable fields, make sure to revert the changes to those as well
            titleText.setText(savedMediaInfo.getTitle());
            descriptionText.setText(savedMediaInfo.getDescription());
            image.setImagePath(savedMediaInfo.getImage());
            image.loadMediaImage(325, 456);
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
    }
}
