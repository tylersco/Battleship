package com.csci4448.MediaManagementSystem.ui.states;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.model.media.MediaInfo;
import com.csci4448.MediaManagementSystem.ui.components.MediaImage;
import com.csci4448.MediaManagementSystem.ui.components.ReviewPanel;
import com.csci4448.MediaManagementSystem.ui.components.TextButton;
import com.csci4448.MediaManagementSystem.ui.components.TextPane;
import com.csci4448.MediaManagementSystem.ui.design.Style;
import com.csci4448.MediaManagementSystem.ui.design.TextComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class IndividualMediaPanel extends MainContentPanel {

    private static final String DEFAULT_IMAGE_PATH = "src/main/resources/test.png";
    private static final MediaInfo DEFAULT_MEDIA_INFO = MediaInfo.createDefault();

    private MediaImage image;
    private TextPane titleText;
    private TextPane descriptionText;
    private TextPane typeText;
    private TextPane genreLabel;
    private TextPane genreText;
    private TextPane reviewsLabel;

    private TextButton actionButton;

    public IndividualMediaPanel(MainController controller, String mediaAction) {
        super(controller);

        JLayeredPane content = getContent();

        titleText = TextComponentFactory.textPane(DEFAULT_MEDIA_INFO.getTitle(), Style.INDMEDIA_TITLE);
        titleText.setSize(500, (int)titleText.getPreferredSize().getHeight());
        titleText.setLocation(375, 25);
        content.add(titleText);

        image = new MediaImage(DEFAULT_IMAGE_PATH);
        image.loadMediaImage(325, 456);
        image.setLocation(15, 15);
        content.add(image);

        typeText = TextComponentFactory.textPane(DEFAULT_MEDIA_INFO.getType(), Style.INDMEDIA_LARGELABEL);
        typeText.setSize(500, (int)typeText.getPreferredSize().getHeight());
        typeText.setLocation(380, 65);
        content.add(typeText);

        genreLabel = TextComponentFactory.textPane("Genre:", Style.INDMEDIA_SMALLLABEL);
        genreLabel.setSize(genreLabel.getPreferredSize());
        genreLabel.setLocation(380, 92);
        content.add(genreLabel);

        genreText = TextComponentFactory.textPane(DEFAULT_MEDIA_INFO.getGenre(), Style.INDMEDIA_SMALLLABEL);
        genreText.setSize(500, (int)genreText.getPreferredSize().getHeight());
        genreText.setLocation((int)genreLabel.getPreferredSize().getWidth() + 390, 92);
        content.add(genreText);

        descriptionText = TextComponentFactory.textPane(DEFAULT_MEDIA_INFO.getDescription(), Style.INDMEDIA_DESCRIPTION);
        descriptionText.setLineWrap(true);
        descriptionText.setSize(350, 250);
        descriptionText.setLocation(400, 130);
        content.add(descriptionText);

        actionButton = TextComponentFactory.smallButton(this, mediaAction, Style.INDMEDIA_BUTTON);
        actionButton.setSize(125, 25);
        actionButton.setLocation(15, 485);
        actionButton.setBackground(Color.GREEN);
        content.add(actionButton);

        reviewsLabel = TextComponentFactory.textPane("User Reviews", Style.INDMEDIA_REVIEWSLABEL);
        reviewsLabel.setSize(reviewsLabel.getPreferredSize());
        reviewsLabel.setLocation(15, 535);
        content.add(reviewsLabel);

        content.setSize(935, 550);
        updateContentSize();
    }

    public void populateMedia(MediaInfo info) {
        titleText.setText(info.getTitle());
        descriptionText.setText(info.getDescription());
        image.setImagePath(info.getImage());
        image.loadMediaImage(325, 456);
        typeText.setText(info.getType());
        genreText.setText(info.getGenre());
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

    }
}
