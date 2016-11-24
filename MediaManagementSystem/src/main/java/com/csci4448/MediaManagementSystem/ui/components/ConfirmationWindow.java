package com.csci4448.MediaManagementSystem.ui.components;

import com.csci4448.MediaManagementSystem.controller.MainController;

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

    private Color defaultColor = new Color(75, 75, 75, 255);
    private Color enteredColor = new Color(75, 75, 75);

    public ConfirmationWindow(MainController controller, String confirmation) {
        this.controller = controller;

        setLayout(null);
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(228, 228, 228)));

        //ToDo: fix formatting, size, and colors

        confirmationText = new TextPane(confirmation, new Font("Helvetice Neue", Font.PLAIN, 17), defaultColor);
        confirmationText.setSize(confirmationText.getPreferredSize());
        confirmationText.setLocation(5, 5);
        add(confirmationText);

        confirm = new TextButton(this, "Confirm", new Font("Helvetice Neue", Font.PLAIN, 15), defaultColor, enteredColor);
        confirm.setSize(confirm.getPreferredSize());
        confirm.setLocation(630, 275);
        add(confirm);

        cancel = new TextButton(this, "Cancel", new Font("Helvetice Neue", Font.PLAIN, 15), defaultColor, enteredColor);
        cancel.setSize(cancel.getPreferredSize());
        cancel.setLocation(550, 275);
        add(cancel);

        setSize(680, 300);
        setLocation(135, 100);

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
