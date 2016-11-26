package com.csci4448.MediaManagementSystem.ui.states;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.model.media.MediaInfo;
import com.csci4448.MediaManagementSystem.ui.components.*;
import com.csci4448.MediaManagementSystem.ui.design.Style;
import com.csci4448.MediaManagementSystem.ui.design.TextComponentFactory;
import com.csci4448.MediaManagementSystem.ui.design.UIFont;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class IndividualMediaPanel extends MainContentPanel {

    private static final String DEFAULT_IMAGE_PATH = "src/main/resources/test.png";

    // TODO: Move this to its own location
    private static final Color TEXT_DEFAULT = new Color(237, 237, 237);
    private static final Color BORDER_HIGHLIGHT = new Color(243, 243, 243);
    private static final Color ACTION_DEFAULT = new Color(51, 155, 241);
    private static final Color ACTION_ENTERED = new Color(74, 184, 241);
    private static final Color EDIT_DEFAULT = new Color(241, 79, 39);
    private static final Color EDIT_ENTERED = new Color(241, 117, 88);
    private static final Color ADDREVIEW_DEFAULT = new Color(92, 135, 63);
    private static final Color ADDREVIEW_ENTERED = new Color(122, 155, 102);

    private MediaImage image;
    private TextPane titleText;
    private TextPane descriptionText;
    private TextPane typeText;
    private TextPane genreLabel;
    private TextPane genreText;
    private TextPane reviewsLabel;

    private BorderedButton actionButton;
    private BorderedButton editButton;
    private BorderedButton addReviewButton;

    private MediaInfo savedMediaInfo = null;

    public IndividualMediaPanel(MainController controller) {
        super(controller);

        JLayeredPane content = getContent();

        titleText = TextComponentFactory.textPane("<TITLE>", Style.INDMEDIA_TITLE);
        titleText.setSize(500, (int)titleText.getPreferredSize().getHeight());
        titleText.setLocation(375, 25);
        content.add(titleText);

        image = new MediaImage(DEFAULT_IMAGE_PATH);
        image.loadMediaImage(325, 456);
        image.setLocation(15, 15);
        content.add(image);

        typeText = TextComponentFactory.textPane("<TYPE>", Style.INDMEDIA_LARGELABEL);
        typeText.setSize(500, (int)typeText.getPreferredSize().getHeight());
        typeText.setLocation(380, 65);
        content.add(typeText);

        genreLabel = TextComponentFactory.textPane("Genre:", Style.INDMEDIA_SMALLLABEL);
        genreLabel.setSize(genreLabel.getPreferredSize());
        genreLabel.setLocation(380, 92);
        content.add(genreLabel);

        genreText = TextComponentFactory.textPane("<GENRE>", Style.INDMEDIA_SMALLLABEL);
        genreText.setSize(500, (int)genreText.getPreferredSize().getHeight());
        genreText.setLocation((int)genreLabel.getPreferredSize().getWidth() + 390, 92);
        content.add(genreText);

        descriptionText = TextComponentFactory.textPane("<DESCRIPTION>", Style.INDMEDIA_DESCRIPTION);
        descriptionText.setLineWrap(true);
        descriptionText.setSize(350, 250);
        descriptionText.setLocation(400, 130);
        content.add(descriptionText);

        actionButton = new BorderedButton(this, "<ACTION>", UIFont.FONT_20B.getFont(),
                TEXT_DEFAULT, TEXT_DEFAULT, TEXT_DEFAULT,
                ACTION_DEFAULT, ACTION_ENTERED, ACTION_DEFAULT,
                ACTION_DEFAULT, BORDER_HIGHLIGHT, ACTION_DEFAULT);
        actionButton.setSize((int)actionButton.getPreferredSize().getWidth() + 25, 25);
        actionButton.setLocation(20, 480);
        content.add(actionButton);

        editButton = new BorderedButton(this, "Edit", UIFont.FONT_20B.getFont(),
                TEXT_DEFAULT, TEXT_DEFAULT, TEXT_DEFAULT,
                EDIT_DEFAULT, EDIT_ENTERED, EDIT_DEFAULT,
                EDIT_DEFAULT, BORDER_HIGHLIGHT, EDIT_DEFAULT);
        editButton.setSize(75, 25);
        editButton.setLocation(195, 480);
        content.add(editButton);
        if (!getController().isAdmin())
            editButton.setVisible(false);

        addReviewButton = new BorderedButton(this, "Add Review +", UIFont.FONT_16.getFont(),
                TEXT_DEFAULT, TEXT_DEFAULT, TEXT_DEFAULT,
                ADDREVIEW_DEFAULT, ADDREVIEW_ENTERED, ADDREVIEW_DEFAULT,
                ADDREVIEW_DEFAULT, BORDER_HIGHLIGHT, ADDREVIEW_DEFAULT);
        addReviewButton.setSize((int)addReviewButton.getPreferredSize().getWidth() + 25, 20);
        addReviewButton.setLocation(700, 550);
        content.add(addReviewButton);

        reviewsLabel = TextComponentFactory.textPane("User Reviews", Style.INDMEDIA_REVIEWSLABEL);
        reviewsLabel.setSize(reviewsLabel.getPreferredSize());
        reviewsLabel.setLocation(15, 535);
        content.add(reviewsLabel);

        content.setSize(935, 550);
        updateContentSize();
    }

    public void populateMedia(MediaInfo info) {
        savedMediaInfo = info;
        titleText.setText(info.getTitle());
        descriptionText.setText(info.getDescription());
        image.setImagePath(info.getImage());
        image.loadMediaImage(325, 456);
        typeText.setText(info.getType());
        genreText.setText(info.getGenre());

        actionButton.setText(info.getIsRentable() ? "Rent" : "Buy");
    }

    public void populateReviews(ArrayList<ReviewPanel> rs) {
        JLayeredPane content = getContent();
        for (int i = 0; i < rs.size(); ++i) {
            ReviewPanel panel = rs.get(i);
            panel.setSize(855, 65);
            panel.setLocation(35, 580 + (i * 70));
            content.add(panel);
        }
        content.setSize(935, rs.size() * 70 + 595);
        updateContentSize();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();

        if (component.equals(editButton)) {
            getController().adminRequest(savedMediaInfo);
        } else if (component.equals(actionButton)) {
            getController().individualMediaActionRequest(savedMediaInfo.getMediaID());
        } else if (component.equals(addReviewButton)) {
            getController().reviewMediaRequest(savedMediaInfo.getMediaID());
        }
    }
}
