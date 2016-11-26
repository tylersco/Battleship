package com.csci4448.MediaManagementSystem.ui.components;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.design.Style;
import com.csci4448.MediaManagementSystem.ui.design.TextComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorWindow extends JPanel implements ActionListener {

    MainController controller;

    private TextPane errorText;
    private TextButton closeButton;

    public ErrorWindow(MainController controller, String errorMsg, String closeMsg) {
        this.controller = controller;

        setLayout(new GridBagLayout());
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(172, 71, 71)));

        errorText = TextComponentFactory.textPane(errorMsg, Style.ERROR_MESSAGE);
        GridBagConstraints textConst = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20,20,0,20), 0, 0);
        add(errorText, textConst);

        closeButton = TextComponentFactory.smallButton(this, closeMsg, Style.ERROR_CLOSE);
        GridBagConstraints confConst = new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(15,10,10,0), 0, 0);
        add(closeButton, confConst);

    }

    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();
        if (component.equals(closeButton)) {
            controller.errorHandledRequest();
        }
    }
}
