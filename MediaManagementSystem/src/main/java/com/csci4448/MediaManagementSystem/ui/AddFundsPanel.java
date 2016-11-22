package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.components.EnterTextField;
import com.csci4448.MediaManagementSystem.ui.components.TextButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFundsPanel extends MainContentPanel implements ActionListener {

    private Font buttonFont = new Font("Helvetice Neue", Font.PLAIN, 15);
    private Color defaultColor = new Color(75, 75, 75, 180);
    private Color defaultColor2 = new Color(75, 75, 75);

    private EnterTextField fundsEnterText;
    private TextButton submit;
    private TextButton cancel;

    public AddFundsPanel(MainController controller) {
        super(controller);

        JLayeredPane content = getContent();

        //ToDo: add title, "Add Funds", and add dollar sign
        //ToDo: fix size and format of components
        fundsEnterText = new EnterTextField(this, "Enter Amount", buttonFont, defaultColor, defaultColor2, false);
        fundsEnterText.setSize(200, 30);
        fundsEnterText.setLocation(375, 50);
        content.add(fundsEnterText);

        submit = new TextButton(this, "Submit", new Font("Helvetice Neue", Font.PLAIN, 15), defaultColor, defaultColor2);
        submit.setSize(submit.getPreferredSize());
        submit.setLocation(525, 100);
        content.add(submit);

        cancel = new TextButton(this, "Cancel", new Font("Helvetice Neue", Font.PLAIN, 15), defaultColor, defaultColor2);
        cancel.setSize(cancel.getPreferredSize());
        cancel.setLocation(350, 100);
        content.add(cancel);

        content.setSize(950, 550);
        //ToDo: make updateContentSize apart of content.add method
        updateContentSize();


    }

    public void actionPerformed(ActionEvent event) {
        //ToDo: implement actions
    }
}
