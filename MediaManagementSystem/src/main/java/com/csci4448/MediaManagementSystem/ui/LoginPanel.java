package com.csci4448.MediaManagementSystem.ui;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel implements ActionListener {

    private Display display;

    private EnterTextField username;
    private EnterTextField password;
    private TextButton submit;
    private TextButton createAccount;

    private Font defaultFont = new Font("Helvetice Neue", Font.PLAIN, 14);
    private Color darkColor = new Color(75, 75, 75);
    private Color lightColor = new Color(75, 75, 75, 140);

    public LoginPanel(Display display) {
        this.display = display;
        setLayout(null);
        setSize(350, 300);
        setPreferredSize(new Dimension(350, 300));
        setBackground(new Color(237, 237, 237));

        username = new EnterTextField(this, "Username", defaultFont, lightColor, darkColor);
        username.setSize(200, 30);
        username.setLocation(75, 75);
        add(username);

        password = new EnterTextField(this, "Password", defaultFont, lightColor, darkColor);
        password.setSize(200, 30);
        password.setLocation(75, 125);
        add(password);

        submit = new TextButton(this, "Submit", defaultFont, lightColor, darkColor);
        Dimension size = submit.getPreferredSize();
        submit.setSize(size);
        submit.setLocation((350-(int)size.getWidth())/2, 185);
        add(submit);

        createAccount = new TextButton(this, "Create Account", new Font("Helvetice Neue", Font.PLAIN, 10), new Color(55, 137, 199), new Color(70, 177, 249));
        size = createAccount.getPreferredSize();
        createAccount.setSize(size);
        createAccount.setLocation((350-(int)size.getWidth())/2, 215);
        add(createAccount);

    }

    public void buttonClicked(JComponent component) {
        if (component.equals(username) | component.equals(password) | component.equals(submit)) {
            checkUserInput();
        } else if (component.equals(createAccount)) {

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
            display.loginAttempt(usernameText, passwordText);
        }
    }
}
