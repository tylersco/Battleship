package com.csci4448.MediaManagementSystem.ui.states;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.model.media.Media;
import com.csci4448.MediaManagementSystem.model.media.MediaInfo;
import com.csci4448.MediaManagementSystem.ui.components.BorderedButton;
import com.csci4448.MediaManagementSystem.ui.components.EnterTextField;
import com.csci4448.MediaManagementSystem.ui.components.TextButton;
import com.csci4448.MediaManagementSystem.ui.components.TextPane;
import com.csci4448.MediaManagementSystem.ui.design.Style;
import com.csci4448.MediaManagementSystem.ui.design.TextComponentFactory;
import com.csci4448.MediaManagementSystem.ui.design.UIFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminEditPanel extends MainContentPanel {

    // TODO: Move these to their own place
    private static final Color TEXT_COLOR = Color.DARK_GRAY;
    private static final Color BORDER_COLOR = Color.DARK_GRAY;
    private static final Color ACTION_BUTTON_DEFAULT_COLOR = Color.GRAY;
    private static final Color ACTION_BUTTON_ENTERED_COLOR = Color.LIGHT_GRAY;

    private BorderedButton saveButton;
    private BorderedButton cancelButton;
    private BorderedButton newButton;

    private TextPane titleLabel;
    private TextPane descriptionLabel;
    private TextPane typeLabel;
    private TextPane genreLabel;

    private EnterTextField titleText;
    private EnterTextField descriptionText;
    private TextButton movieChoice, bookChoice, musicChoice, tvChoice, audioChoice;
    private EnterTextField genreText;

    private MediaInfo savedMediaInfo = null;

    public AdminEditPanel(MainController controller, MediaInfo info) {
        super(controller);

        JLayeredPane content = getContent();
        savedMediaInfo = info;

        saveButton = new BorderedButton(this, "Save Changes", UIFont.FONT_20B.getFont(),
                TEXT_COLOR, TEXT_COLOR, TEXT_COLOR,
                ACTION_BUTTON_DEFAULT_COLOR, ACTION_BUTTON_ENTERED_COLOR, ACTION_BUTTON_DEFAULT_COLOR,
                BORDER_COLOR, BORDER_COLOR, BORDER_COLOR);
        saveButton.setSize(170, 30);
        saveButton.setLocation(573, 10);
        content.add(saveButton);

        cancelButton = new BorderedButton(this, "Cancel Changes", UIFont.FONT_20B.getFont(),
                TEXT_COLOR, TEXT_COLOR, TEXT_COLOR,
                ACTION_BUTTON_DEFAULT_COLOR, ACTION_BUTTON_ENTERED_COLOR, ACTION_BUTTON_DEFAULT_COLOR,
                BORDER_COLOR, BORDER_COLOR, BORDER_COLOR);
        cancelButton.setSize(170, 30);
        cancelButton.setLocation(382, 10);
        content.add(cancelButton);

        newButton = new BorderedButton(this, "New Media", UIFont.FONT_20B.getFont(),
                TEXT_COLOR, TEXT_COLOR, TEXT_COLOR,
                ACTION_BUTTON_DEFAULT_COLOR, ACTION_BUTTON_ENTERED_COLOR, ACTION_BUTTON_DEFAULT_COLOR,
                BORDER_COLOR, BORDER_COLOR, BORDER_COLOR);
        newButton.setSize(170, 30);
        newButton.setLocation(193, 10);
        content.add(newButton);

        titleLabel = TextComponentFactory.textPane("Title:", Style.ADMIN_LABEL);
        titleLabel.setSize(titleLabel.getPreferredSize());
        titleLabel.setLocation(15, 80);
        content.add(titleLabel);

        descriptionLabel = TextComponentFactory.textPane("Description:", Style.ADMIN_LABEL);
        descriptionLabel.setSize(descriptionLabel.getPreferredSize());
        descriptionLabel.setLocation(15, 120);
        content.add(descriptionLabel);

        typeLabel = TextComponentFactory.textPane("Type:", Style.ADMIN_LABEL);
        typeLabel.setSize(typeLabel.getPreferredSize());
        typeLabel.setLocation(15, 220);
        content.add(typeLabel);

        genreLabel = TextComponentFactory.textPane("Genre:", Style.ADMIN_LABEL);
        genreLabel.setSize(genreLabel.getPreferredSize());
        genreLabel.setLocation(15, 260);
        content.add(genreLabel);

        titleText = TextComponentFactory.enterText(this, "", Style.ADMIN_TEXT);
        titleText.setSize(725, 30);
        titleText.setLocation(175, 80);
        content.add(titleText);

        descriptionText = TextComponentFactory.enterText(this, "", Style.ADMIN_TEXT);
        descriptionText.setSize(725, 90);
        descriptionText.setLocation(175, 120);
        content.add(descriptionText);

        movieChoice = TextComponentFactory.smallButton(this, "Movie", Style.ADMIN_BUTTON);
        bookChoice = TextComponentFactory.smallButton(this, "Book", Style.ADMIN_BUTTON);
        musicChoice = TextComponentFactory.smallButton(this, "Music", Style.ADMIN_BUTTON);
        tvChoice = TextComponentFactory.smallButton(this, "TV Show", Style.ADMIN_BUTTON);
        audioChoice = TextComponentFactory.smallButton(this, "Audio Book", Style.ADMIN_BUTTON);
        movieChoice.setSize(movieChoice.getPreferredSize());
        movieChoice.setLocation(175, 220);
        bookChoice.setSize(bookChoice.getPreferredSize());
        bookChoice.setLocation(250, 220);
        musicChoice.setSize(musicChoice.getPreferredSize());
        musicChoice.setLocation(325, 220);
        tvChoice.setSize(tvChoice.getPreferredSize());
        tvChoice.setLocation(400, 220);
        audioChoice.setSize(audioChoice.getPreferredSize());
        audioChoice.setLocation(505, 220);
        content.add(movieChoice);
        content.add(bookChoice);
        content.add(musicChoice);
        content.add(tvChoice);
        content.add(audioChoice);
        movieChoice.setIsSelected(true);

        genreText = TextComponentFactory.enterText(this, "", Style.ADMIN_TEXT);
        genreText.setSize(225, 30);
        genreText.setLocation(175, 260);
        content.add(genreText);

        content.setSize(935, 520);
        updateContentSize();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();
    }

    private void setFields(String title, String desc, String type, String genre) {
        titleText.setText(title);
        descriptionText.setText(desc);
        setSelectedMediaType(type);
        genreText.setText(genre);
    }

    public void setSelectedMediaType(String type) {
        if (!Media.isValidType(type)) {
            // TODO: Report the error to the user
            System.out.println("The type '" + type + "' is not a valid media type.");
            return;
        }

        movieChoice.setIsSelected("Movie".equals(type));
        bookChoice.setIsSelected("Book".equals(type));
        musicChoice.setIsSelected("Music".equals(type));
        tvChoice.setIsSelected("TV Show".equals(type));
        audioChoice.setIsSelected("Audio Book".equals(type));
    }

    public String getSelectedMediaType() {
        if (movieChoice.getIsSelected()) {
            return "Movie";
        } else if (bookChoice.getIsSelected()) {
            return "Book";
        } else if (musicChoice.getIsSelected()) {
            return "Music";
        } else if (tvChoice.getIsSelected()) {
            return "TV Show";
        } else if (audioChoice.getIsSelected()) {
            return "Audio Book";
        } else {
            // TODO: Report this very unlikely error
            System.out.println("There was no valid selection for media type.");
            return "";
        }
    }

    public boolean getIsEditingExistingMedia() {
        return savedMediaInfo != null;
    }
}
