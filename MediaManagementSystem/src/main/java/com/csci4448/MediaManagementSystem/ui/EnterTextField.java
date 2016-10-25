package com.csci4448.MediaManagementSystem.ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterTextField extends JLayeredPane {

    private ActionListener container;

    private JPanel background;
    private JTextField underlaidText;
    private JTextField userInput;

    private boolean underlaidTextAdded = true;

    public EnterTextField(ActionListener container, String text, Font defaultFont, Color underlaidColor, Color inputColor, boolean hideInput) {
        this.container = container;

        setLayout(null);

        background = new JPanel();
        background.setLocation(0, 0);
        background.setBackground(new Color(223, 223, 223));
        background.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(204, 204, 204)));
        add(background, new Integer(1));

        underlaidText = new JTextField(text);
        underlaidText.setFont(defaultFont);
        underlaidText.setLocation(3, 0);
        underlaidText.setForeground(underlaidColor);
        underlaidText.setOpaque(false);
        underlaidText.setBackground(null);
        underlaidText.setEditable(false);
        underlaidText.setHighlighter(null);
        underlaidText.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        add(underlaidText, new Integer(2));

        if (hideInput) {
            userInput = new JPasswordField();
        } else {
            userInput = new JTextField("");
        }
        userInput.setFont(defaultFont);
        userInput.setLocation(3, 0);
        userInput.setForeground(inputColor);
        userInput.setOpaque(false);
        userInput.setBackground(null);
        userInput.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        addListeners();
        add(userInput, new Integer(3));
    }

    public String getText() {
        return userInput.getText();
    }

    public void setBackground(Color color) {
        if (color == null) {
            remove(background);
        } else {
            background.setBackground(color);
        }
    }

    private void addListeners() {
        userInput.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                container.actionPerformed(new ActionEvent(EnterTextField.this, 2, "ActionPerformed"));
            }
        });


        userInput.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                checkInputUpdate();
            }

            public void removeUpdate(DocumentEvent e) {
                checkInputUpdate();
            }

            public void changedUpdate(DocumentEvent e) {
                checkInputUpdate();
            }
        });
    }

    private void checkInputUpdate() {
        if (userInput.getText().length() > 0) {
            if (underlaidTextAdded) {
                remove(underlaidText);
                underlaidTextAdded = false;
            }

        } else {
            if (!underlaidTextAdded) {
                add(underlaidText);
                underlaidTextAdded = true;
            }
        }
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
        background.setSize(width, height);
        underlaidText.setSize(width-6, height);
        userInput.setSize(width-6, height);
    }
}
