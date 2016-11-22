package com.csci4448.MediaManagementSystem.ui.components;

import com.csci4448.MediaManagementSystem.ui.IndividualMediaPanel;
import com.csci4448.MediaManagementSystem.ui.MainContentPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConfirmationWindow extends JPanel implements ActionListener {

    MainContentPanel parent;

    private boolean isConfirmed = false;

    private TextArea confirmationText;
    private TextButton confirm;
    private TextButton cancel;

    private Color defaultColor = new Color(75, 75, 75, 255);
    private Color enteredColor = new Color(75, 75, 75);

    public ConfirmationWindow(IndividualMediaPanel parent, String confirmation) {
        this.parent = parent;

        setLayout(null);
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(228, 228, 228)));

        //ToDo: fix formatting, size, and colors

        confirmationText = new TextArea(confirmation, new Font("Helvetice Neue", Font.PLAIN, 17), defaultColor);
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

    public void setSize(int width, int height) {
        super.setSize(width, height);
    }

    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();
        if (component.equals(cancel)) {
            isConfirmed = false;
            parent.actionPerformed(new ActionEvent(ConfirmationWindow.this, 1, "Not Confirmed"));
            //ToDo: clean up
            parent.getContent().remove(this);
            parent.getContent().validate();
            parent.getContent().repaint();
        } else if (component.equals(confirm)) {
            isConfirmed = true;
            parent.actionPerformed(new ActionEvent(ConfirmationWindow.this, 1, "Confirmed"));
            parent.getContent().remove(this);
            parent.getContent().validate();
            parent.getContent().repaint();
        }
    }

    public boolean getIsConfirmed() {
        return isConfirmed;
    }
}
