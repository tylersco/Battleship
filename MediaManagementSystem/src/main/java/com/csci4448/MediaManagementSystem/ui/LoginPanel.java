package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.components.EnterTextField;
import com.csci4448.MediaManagementSystem.ui.components.TextButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener, DisplayState{

    private MainController controller;

    private EnterTextField username;
    private EnterTextField password;
    private TextButton submit;
    private TextButton createAccount;

    private Font defaultFont = new Font("Helvetice Neue", Font.PLAIN, 14);
    private Color darkColor = new Color(75, 75, 75);
    private Color lightColor = new Color(75, 75, 75, 140);

    public LoginPanel(MainController controller) {
        this.controller = controller;
        setLayout(null);
        setSize(350, 300);
        setPreferredSize(new Dimension(350, 300));
        setBackground(new Color(237, 237, 237));

        username = new EnterTextField(this, "Username", defaultFont, lightColor, darkColor, false);
        username.setSize(200, 30);
        username.setLocation(75, 75);
        add(username);

        password = new EnterTextField(this, "Password", defaultFont, lightColor, darkColor, true);
        password.setSize(200, 30);
        password.setLocation(75, 125);
        add(password);

        submit = TextComponentFactory.smallButton(this, "Submit", Style.LOGIN_BASIC);
        submit.setLocation((350-submit.getWidth())/2, 185);
        add(submit);

        createAccount = TextComponentFactory.smallButton(this, "Create Account",  Style.LOGIN_CREATE);
        createAccount.setLocation((350-createAccount.getWidth())/2, 215);
        add(createAccount);

    }

    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();
        if (component.equals(username) | component.equals(password) | component.equals(submit)) {
            checkUserInput();
        } else if (component.equals(createAccount)) {
            controller.createAccountRequest();
        }

    }

    private void checkUserInput() {
        boolean validInput = true;
        String usernameText = username.getText().trim();
        String passwordText = password.getText().trim();

        if (usernameText.length() <= 0) {
            //ToDo: inform needed username
            validInput = false;
        }
        else if (passwordText.length() <= 0) {
            //ToDo: inform needed password
            validInput = false;
        }

        if (validInput) {
            controller.loginSubmitRequest(usernameText, passwordText);
        }
    }

    public void onActivate(MainController controller, Display display) {
        display.setSize(350, 300);
        display.setResizable(false);
        display.setLocationRelativeTo(null);

        display.add(this);

        display.setVisible(true);
    }

    public void onDeactivate(MainController controller, Display display) {
        display.remove(this);
    }

}
