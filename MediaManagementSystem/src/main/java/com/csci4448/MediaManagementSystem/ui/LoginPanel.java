package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {

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

        submit = new TextButton(this, "Submit", defaultFont, lightColor, darkColor);
        Dimension size = submit.getPreferredSize();
        submit.setSize(size.width+1, size.height);
        submit.setLocation((350-(int)size.getWidth())/2, 185);
        add(submit);

        createAccount = new TextButton(this, "Create Account", new Font("Helvetice Neue", Font.PLAIN, 10), new Color(55, 137, 199), new Color(70, 177, 249));
        size = createAccount.getPreferredSize();
        createAccount.setSize(size.width+1, size.height);
        createAccount.setLocation((350-(int)size.getWidth())/2, 215);
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
        String usernameText = username.getText();
        String passwordText = password.getText();

        if (usernameText.length() <= 0) {
            //ToDo: inform needed username
            validInput = false;
        }
        if (passwordText.length() <= 0) {
            //ToDo: inform needed password
            validInput = false;
        }
        if (validInput) {
            controller.loginSubmitRequest(usernameText, passwordText);
        }
    }
}
