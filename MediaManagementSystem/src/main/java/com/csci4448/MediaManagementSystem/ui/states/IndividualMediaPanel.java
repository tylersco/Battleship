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

    private TextButton buyButton;
    private TextButton rentButton;

    public IndividualMediaPanel(MainController controller) {
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

        content.setSize(935, 550);
        updateContentSize();
    }

    public void populateMedia(MediaInfo info) {
        titleText.setText(info.getTitle());
        descriptionText.setText(info.getDescription());
        image.setImagePath(info.getImage());
        image.loadMediaImage(325, 456);
    }

    public void populateReviews(ArrayList<ReviewPanel> rs) {

    }
}
