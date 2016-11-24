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

public class IndividualMediaPanel extends MainContentPanel {

    private final String DEFAULT_IMAGE_PATH = "src/main/resources/test.png";

    private Font buttonFont = new Font("Helvetice Neue", Font.PLAIN, 15);
    private Color defaultColor = new Color(75, 75, 75, 180);
    private Color enteredColor = new Color(75, 75, 75);
    private Color selectedColor = new Color(75, 75, 75);

    private MediaImage image;
    private TextArea titleText;
    private TextArea descriptionText;
    private TextArea typeText;
    private TextArea genreLabel;
    private TextArea genreText;
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

        typeText = new TextArea(savedMediaInfo.getType(), new Font("Helvetice Neue", Font.ITALIC, 22), new Color(75, 75, 75));
        typeText.setSize(500, (int)typeText.getPreferredSize().getHeight());
        typeText.setLocation(380, 65);
        content.add(typeText);

        genreLabel = new TextArea("Genre:", new Font("Helvetice Neue", Font.ITALIC, 18), new Color(75, 75, 75));
        genreLabel.setSize(genreLabel.getPreferredSize());
        genreLabel.setLocation(380, 92);
        content.add(genreLabel);

        genreText = new TextArea(savedMediaInfo.getGenre(), new Font("Helvetice Neue", Font.ITALIC, 18), new Color(75, 75, 75));
        genreText.setSize(500, (int)genreText.getPreferredSize().getHeight());
        genreText.setLocation((int)genreLabel.getPreferredSize().getWidth() + 390, 92);
        content.add(genreText);

        descriptionText = new TextArea(savedMediaInfo.getDescription(), new Font("Helvetice Neue", Font.PLAIN, 18), new Color(75, 75, 75, 200));
        descriptionText.setLineWrap(true);
        descriptionText.setSize(350, 250);
        descriptionText.setLocation(400, 130);
        content.add(descriptionText);

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
}
