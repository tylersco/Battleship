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

public class CreateAccountPanel extends JLayeredPane implements ActionListener, DisplayState {

    private MainController controller;

    private JPanel content;
    private ErrorWindow errorWindow;

    private EnterTextField firstName;
    private EnterTextField lastName;
    private EnterTextField username;
    private EnterTextField email;
    private EnterTextField password1;
    private EnterTextField password2;

    private TextButton submit;
    private TextButton cancel;

    public CreateAccountPanel(MainController controller) {
        this.controller = controller;
        content = new JPanel();
        content.setLayout(new GridBagLayout());
        content.setSize(350, 500);
        content.setPreferredSize(new Dimension(350, 500));
        content.setBackground(new Color(237, 237, 237));

        firstName = TextComponentFactory.enterText(this, "First", Style.LOGIN_BASIC, 145, 30);
        GridBagConstraints firsConst = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,30,10), 0, 0);
        content.add(firstName, firsConst);

        lastName = TextComponentFactory.enterText(this, "Last", Style.LOGIN_BASIC, 145, 30);
        GridBagConstraints lastConst = new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,30,0), 0, 0);
        content.add(lastName, lastConst);

        username = TextComponentFactory.enterText(this, "username", Style.LOGIN_BASIC, 215, 30);
        GridBagConstraints userConst = new GridBagConstraints(0, 1, 2, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,30,0), 0, 0);
        content.add(username, userConst);

        email = TextComponentFactory.enterText(this, "email", Style.LOGIN_BASIC, 215, 30);
        GridBagConstraints emaiConst = new GridBagConstraints(0, 2, 2, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,30,0), 0, 0);
        content.add(email, emaiConst);

        password1 = TextComponentFactory.enterTextHidden(this, "password", Style.LOGIN_BASIC, 215, 30);
        GridBagConstraints pas1Const = new GridBagConstraints(0, 3, 2, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,30,0), 0, 0);
        content.add(password1, pas1Const);

        password2 = TextComponentFactory.enterTextHidden(this, "confirm password", Style.LOGIN_BASIC, 215, 30);
        GridBagConstraints pas2Const = new GridBagConstraints(0, 4, 2, 1, 0, 0, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,50,0), 0, 0);
        content.add(password2, pas2Const);

        submit = TextComponentFactory.smallButton(this, "Create Account", Style.CREATE_BASIC);
        GridBagConstraints submConst = new GridBagConstraints(0, 5, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,10,0), 0, 0);
        content.add(submit, submConst);

        cancel = TextComponentFactory.smallButton(this, "cancel", Style.CREATE_CANCEL);
        GridBagConstraints cancConst = new GridBagConstraints(0, 6, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0,0,10,0), 0, 0);
        content.add(cancel, cancConst);

        add(content, new Integer(1));

    }

    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();
        if (component.equals(firstName) | component.equals(lastName) | component.equals(username) | component.equals(email) | component.equals(password1) | component.equals(password2) | component.equals(submit)) {
            checkUserInput();
        } else if(component.equals(cancel)) {
            controller.loginRequest();
        }
    }

    private void checkUserInput() {
        boolean validInput = true;
        String usernameText = username.getText().trim();
        String password1Text = password1.getText().trim();
        String password2Text = password2.getText().trim();
        String firstNameText = firstName.getText().trim();
        String lastNameText = lastName.getText().trim();
        String emailText = email.getText().trim();

        if (usernameText.length() <= 0) {
            //ToDo: inform needed username
            validInput = false;
        }
        else if (password1Text.length() <= 0) {
            //ToDo: inform needed password
            validInput = false;
        }
        else if (password2Text.length() <= 0) {
            //ToDo: inform needed password
            validInput = false;
        }
        else if (emailText.length() <= 0) {
            //ToDo: inform needed email
            validInput = false;
        }
        else if (!password1Text.equals(password2Text)) {
            //ToDo: inform that passwords do not match
            validInput = false;
        }

        if (validInput) {
            controller.createAccountSubmitRequest(firstNameText, lastNameText, usernameText, emailText, password1Text);
        }
    }

    public void onActivate(MainController controller, Display display) {
        display.setSize(350, 500);
        display.setResizable(false);
        display.setLocationRelativeTo(null);

        //display.add(this);

        //display.setVisible(true);
    }

    public JComponent getStateView() {
        return this;
    }

    public void setErrorWindow(ErrorWindow errorWindow) {
        if (this.errorWindow != null) {
            remove(this.errorWindow);
            this.errorWindow = null;
            validate();
            repaint();
        }
        if (errorWindow != null) {
            this.errorWindow = errorWindow;
            Dimension windowSize = errorWindow.getPreferredSize();
            errorWindow.setSize(windowSize);
            errorWindow.setLocation((getWidth() - (int)windowSize.getWidth())/2, 150);
            add(errorWindow, new Integer(2));
        }
    }

    public void onDeactivate(MainController controller, Display display) {
    }

}
