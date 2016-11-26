package com.csci4448.MediaManagementSystem.ui.states;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.components.ErrorWindow;
import com.csci4448.MediaManagementSystem.ui.design.Style;
import com.csci4448.MediaManagementSystem.ui.design.TextComponentFactory;
import com.csci4448.MediaManagementSystem.ui.components.EnterTextField;
import com.csci4448.MediaManagementSystem.ui.components.TextButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JLayeredPane implements ActionListener, DisplayState {

    private MainController controller;

    private JPanel content;
    private JComponent popUpWindow;

    private EnterTextField username;
    private EnterTextField password;
    private TextButton submit;
    private TextButton createAccount;

    public LoginPanel(MainController controller) {
        this.controller = controller;
        content = new JPanel();
        content.setLayout(new GridBagLayout());
        content.setSize(350, 300);
        content.setBackground(new Color(237, 237, 237));

        username = TextComponentFactory.enterText(this, "Username", Style.LOGIN_BASIC, 200, 30);
        GridBagConstraints userConst = new GridBagConstraints(0, 0, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,0,20,0), 0, 0);
        content.add(username, userConst);

        password = TextComponentFactory.enterTextHidden(this, "Password", Style.LOGIN_BASIC, 200, 30);
        GridBagConstraints passConst = new GridBagConstraints(0, 1, 3, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0,0,30,0), 0, 0);
        content.add(password, passConst);

        submit = TextComponentFactory.smallButton(this, "Submit", Style.LOGIN_BASIC);
        GridBagConstraints submConst = new GridBagConstraints(2, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,10,0), 0, 0);
        content.add(submit, submConst);

        createAccount = TextComponentFactory.smallButton(this, "Create Account",  Style.LOGIN_CREATE);
        GridBagConstraints creaConst = new GridBagConstraints(2, 3, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,10,0), 0, 0);
        content.add(createAccount, creaConst);

        add(content, new Integer(1));

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
            username.errorText("Username");
            validInput = false;
        }
        if (passwordText.length() <= 0) {
            password.errorText("Password");
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
    }

    public JComponent getStateView() {
        return this;
    }

    public void setPopUpWindow(JComponent popUpWindow) {
        if (this.popUpWindow != null) {
            remove(this.popUpWindow);
            this.popUpWindow = null;
            validate();
            repaint();
        }
        if (popUpWindow != null) {
            this.popUpWindow = popUpWindow;
            Dimension windowSize = this.popUpWindow.getPreferredSize();
            this.popUpWindow.setSize(windowSize);
            this.popUpWindow.setLocation((getWidth() - (int)windowSize.getWidth())/2, 70);
            add(this.popUpWindow, new Integer(3));
        }
    }

    public void onDeactivate(MainController controller, Display display) {
    }

}
