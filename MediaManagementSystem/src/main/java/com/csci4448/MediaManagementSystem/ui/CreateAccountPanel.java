package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountPanel extends JPanel implements ActionListener{

    private MainController controller;

    private EnterTextField firstName;
    private EnterTextField lastName;
    private EnterTextField username;
    private EnterTextField email;
    private EnterTextField password1;
    private EnterTextField password2;

    private TextButton submit;
    private TextButton cancel;

    private Font defaultFont = new Font("Helvetice Neue", Font.PLAIN, 14);
    private Color darkColor = new Color(75, 75, 75);
    private Color lightColor = new Color(75, 75, 75, 140);

    public CreateAccountPanel(MainController controller) {
        this.controller = controller;
        setLayout(null);
        setSize(350, 500);
        setPreferredSize(new Dimension(350, 500));
        setBackground(new Color(237, 237, 237));

        firstName = new EnterTextField(this, "First", defaultFont, lightColor, darkColor);
        firstName.setSize(145, 30);
        firstName.setLocation(25, 35);
        add(firstName);

        lastName = new EnterTextField(this, "Last", defaultFont, lightColor, darkColor);
        lastName.setSize(145, 30);
        lastName.setLocation(180, 35);
        add(lastName);

        username = new EnterTextField(this, "username", defaultFont, lightColor, darkColor);
        username.setSize(215, 30);
        username.setLocation(25, 95);
        add(username);

        email = new EnterTextField(this, "email", defaultFont, lightColor, darkColor);
        email.setSize(215, 30);
        email.setLocation(25, 155);
        add(email);

        password1 = new EnterTextField(this, "password", defaultFont, lightColor, darkColor);
        password1.setSize(215, 30);
        password1.setLocation(25, 215);
        add(password1);

        password2 = new EnterTextField(this, "confirm password", defaultFont, lightColor, darkColor);
        password2.setSize(215, 30);
        password2.setLocation(25, 275);
        add(password2);

        submit = new TextButton(this, "Create Account", new Font("Helvetice Neue", Font.PLAIN, 16), lightColor, darkColor);
        Dimension size = submit.getPreferredSize();
        submit.setSize(size);
        submit.setLocation((350-(int)size.getWidth())/2, 355);
        add(submit);

        cancel = new TextButton(this, "cancel", new Font("Helvetice Neue", Font.PLAIN, 11), lightColor, new Color(249, 72, 67));
        size = cancel.getPreferredSize();
        cancel.setSize(size);
        cancel.setLocation((350-(int)size.getWidth())/2, 395);
        add(cancel);

    }

    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();
        if (component.equals(firstName) | component.equals(lastName) | component.equals(username) | component.equals(email) | component.equals(password1) | component.equals(password2) | component.equals(submit)) {
            checkUserInput();
        } else if(component.equals(cancel)) {
            controller.createAccountCancelRequest();
        }
    }

    private void checkUserInput() {

    }

}
