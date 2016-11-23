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

    public LoginPanel(MainController controller) {
        this.controller = controller;
        setLayout(new GridBagLayout());
        setSize(350, 300);
        setBackground(new Color(237, 237, 237));

        username = TextComponentFactory.enterText(this, "Username", Style.LOGIN_BASIC, 200, 30);
        GridBagConstraints userConst = new GridBagConstraints(0, 0, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,0,20,0), 0, 0);
        add(username, userConst);

        password = TextComponentFactory.enterTextHidden(this, "Password", Style.LOGIN_BASIC, 200, 30);
        GridBagConstraints passConst = new GridBagConstraints(0, 1, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,0,30,0), 0, 0);
        add(password, passConst);

        submit = TextComponentFactory.smallButton(this, "Submit", Style.LOGIN_BASIC);
        GridBagConstraints submConst = new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,10,0), 0, 0);
        add(submit, submConst);

        createAccount = TextComponentFactory.smallButton(this, "Create Account",  Style.LOGIN_CREATE);
        GridBagConstraints creaConst = new GridBagConstraints(2, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,10,0), 0, 0);
        add(createAccount, creaConst);

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
