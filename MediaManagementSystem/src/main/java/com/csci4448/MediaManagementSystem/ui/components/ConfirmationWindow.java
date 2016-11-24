package com.csci4448.MediaManagementSystem.ui.components;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.design.Style;
import com.csci4448.MediaManagementSystem.ui.design.TextComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmationWindow extends JPanel implements ActionListener {

    MainController controller;

    private boolean isConfirmed = false;

    private TextPane confirmationText;
    private TextButton confirm;
    private TextButton cancel;

    public ConfirmationWindow(MainController controller, String confirmation, String confirmMsg, String cancelMsg) {
        this.controller = controller;

        setLayout(new GridBagLayout());
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(205, 205, 205)));

        confirmationText = TextComponentFactory.textPane(confirmation, Style.CONFIRM_MESSAGE);
        GridBagConstraints textConst = new GridBagConstraints(0, 0, 2, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20,20,0,20), 0, 0);
        add(confirmationText, textConst);

        confirm = TextComponentFactory.smallButton(this, confirmMsg, Style.CONFIRM_OK);
        GridBagConstraints confConst = new GridBagConstraints(1, 1, 1, 1, .5, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(10,10,20,0), 0, 0);
        add(confirm, confConst);

        cancel = TextComponentFactory.smallButton(this, cancelMsg, Style.CONFIRM_CANCEL);
        GridBagConstraints cancConst = new GridBagConstraints(0, 1, 1, 1, .5, 0, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(10,0,20,10), 0, 0);
        add(cancel, cancConst);

    }

    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();
        if (component.equals(cancel)) {
            isConfirmed = false;
            controller.confirmationRequest(isConfirmed);
        } else if (component.equals(confirm)) {
            isConfirmed = true;
            controller.confirmationRequest(isConfirmed);
        }
    }
}
