package com.csci4448.MediaManagementSystem.ui.states;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.model.media.MediaInfo;
import com.csci4448.MediaManagementSystem.ui.components.BorderedButton;
import com.csci4448.MediaManagementSystem.ui.components.EnterTextField;
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

    private EnterTextField titleText;
    private EnterTextField descriptionText;

    private boolean unsavedChanges = false;

    public AdminEditPanel(MainController controller, MediaInfo info) {
        super(controller);

        JLayeredPane content = getContent();

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

        content.setSize(935, 550);
        updateContentSize();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
