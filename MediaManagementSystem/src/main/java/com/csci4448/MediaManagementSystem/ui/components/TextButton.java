package com.csci4448.MediaManagementSystem.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextButton extends JTextField {

    private ActionListener container;

    private boolean isSelected = false;

    private Font defaultFont;
    private Color defaultColor;
    private Color enteredColor;
    private Color selectedColor;

    public TextButton(ActionListener container, String text, Font defaultFont, Color defaultColor, Color enteredColor, Color selectedColor) {
        super(text);
        this.container = container;
        this.defaultFont = defaultFont;
        this.defaultColor = defaultColor;
        this.enteredColor = enteredColor;
        this.selectedColor = selectedColor;
        setFont(defaultFont);
        setForeground(defaultColor);
        setEditable(false);
        setHighlighter(null);
        setOpaque(false);
        setBackground(null);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        addMouseListener();

    }

    public TextButton(ActionListener container, String text, Font defaultFont, Color defaultColor, Color enteredColor) {
        this(container, text, defaultFont, defaultColor, enteredColor, defaultColor);
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
        if (isSelected) setForeground(selectedColor); else setForeground(defaultColor);
    }

    public void addMouseListener() {
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isSelected) setForeground(enteredColor);
            }
            @Override
            public void mouseExited(MouseEvent e) { if (!isSelected) setForeground(defaultColor); }
            @Override
            public void mousePressed(MouseEvent e) {
                container.actionPerformed(new ActionEvent(TextButton.this, 1, "MousePressed"));
            }
        });
    }


}
