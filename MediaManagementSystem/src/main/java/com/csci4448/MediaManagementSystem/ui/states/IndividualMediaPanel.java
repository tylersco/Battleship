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
    private static final MediaInfo DEFAULT_MEDIA_INFO = MediaInfo.createDefault();

    // TODO: Move this to its own location
    private static final Color TEXT_COLOR = Color.DARK_GRAY;
    private static final Color BORDER_COLOR = Color.DARK_GRAY;
    private static final Color ACTION_BUTTON_DEFAULT_COLOR = Color.GRAY;
    private static final Color ACTION_BUTTON_ENTERED_COLOR = Color.LIGHT_GRAY;
    private static final Color EDIT_BUTTON_DEFAULT_COLOR = Color.ORANGE;
    private static final Color EDIT_BUTTON_ENTERED_COLOR = Color.YELLOW;

    private MediaImage image;
    private TextPane titleText;
    private TextPane descriptionText;
    private TextPane typeText;
    private TextPane genreLabel;
    private TextPane genreText;
    private TextPane reviewsLabel;

    private BorderedButton buyButton;
    private BorderedButton rentButton;
    private BorderedButton editButton;

    private MediaInfo savedMediaInfo = null;

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

        rentButton = new BorderedButton(this, "Rent", UIFont.FONT_20B.getFont(),
                TEXT_COLOR, TEXT_COLOR, TEXT_COLOR,
                ACTION_BUTTON_DEFAULT_COLOR, ACTION_BUTTON_ENTERED_COLOR, ACTION_BUTTON_DEFAULT_COLOR,
                BORDER_COLOR, BORDER_COLOR, BORDER_COLOR);
        rentButton.setSize(75, 25);
        rentButton.setLocation(15, 480);
        content.add(rentButton);

        buyButton = new BorderedButton(this, "Buy", UIFont.FONT_20B.getFont(),
                TEXT_COLOR, TEXT_COLOR, TEXT_COLOR,
                ACTION_BUTTON_DEFAULT_COLOR, ACTION_BUTTON_ENTERED_COLOR, ACTION_BUTTON_DEFAULT_COLOR,
                BORDER_COLOR, BORDER_COLOR, BORDER_COLOR);
        buyButton.setSize(75, 25);
        buyButton.setLocation(105, 480);
        content.add(buyButton);

        editButton = new BorderedButton(this, "Edit", UIFont.FONT_20B.getFont(),
                TEXT_COLOR, TEXT_COLOR, TEXT_COLOR,
                EDIT_BUTTON_DEFAULT_COLOR, EDIT_BUTTON_ENTERED_COLOR, EDIT_BUTTON_DEFAULT_COLOR,
                BORDER_COLOR, BORDER_COLOR, BORDER_COLOR);
        editButton.setSize(75, 25);
        editButton.setLocation(195, 480);
        content.add(editButton);
        if (!getController().isAdmin())
            editButton.setVisible(false);

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
            getController().adminRequest(null);
        }
    }
}
