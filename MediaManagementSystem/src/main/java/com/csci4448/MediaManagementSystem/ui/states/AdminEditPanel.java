package com.csci4448.MediaManagementSystem.ui.states;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.model.media.MediaInfo;
import com.csci4448.MediaManagementSystem.ui.components.EnterTextField;
import com.csci4448.MediaManagementSystem.ui.components.TextPane;
import com.csci4448.MediaManagementSystem.ui.design.Style;
import com.csci4448.MediaManagementSystem.ui.design.TextComponentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AdminEditPanel extends MainContentPanel {

    private TextPane titleLabel;
    private TextPane descriptionLabel;

    private EnterTextField titleText;
    private EnterTextField descriptionText;


    public AdminEditPanel(MainController controller, MediaInfo info) {
        super(controller);

        JLayeredPane content = getContent();

        content.setSize(935, 550);
        updateContentSize();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

    }
}
