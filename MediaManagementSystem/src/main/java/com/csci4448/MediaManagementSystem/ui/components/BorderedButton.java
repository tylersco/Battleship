package com.csci4448.MediaManagementSystem.ui.components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BorderedButton extends JTextField {

    private ActionListener container;

    private boolean isSelected = false;

    private Font defaultFont;
    private Color defaultColor;
    private Color enteredColor;
    private Color selectedColor;
    private Color backDefaultColor;
    private Color backEnteredColor;
    private Color backSelectedColor;
    private Color borderDefaultColor;
    private Color borderEnteredColor;
    private Color borderSelectedColor;

    public BorderedButton(ActionListener container, String text, Font font,
                          Color defaultColor, Color enteredColor, Color selectedColor,
                          Color backDefaultColor, Color backEnteredColor, Color backSelectedColor,
                          Color borderDefaultColor, Color borderEnteredColor, Color borderSelectedColor) {
        super(text);
        this.container = container;
        this.defaultFont = font;
        this.defaultColor = defaultColor;
        this.enteredColor = enteredColor;
        this.selectedColor = selectedColor;
        this.backDefaultColor = backDefaultColor;
        this.backEnteredColor = backEnteredColor;
        this.backSelectedColor = backSelectedColor;
        this.borderDefaultColor = borderDefaultColor;
        this.borderEnteredColor = borderEnteredColor;
        this.borderSelectedColor = borderSelectedColor;

        setFont(defaultFont);
        setForeground(defaultColor);
        setEditable(false);
        setHighlighter(null);
        setOpaque(true);
        setBackground(backDefaultColor);
        // TODO: Make a default border that looks good
        setBorder(BorderFactory.createMatteBorder(2, 5, 2, 2, borderDefaultColor));
        setHorizontalAlignment(JTextField.CENTER);
        addMouseListener();
    }

    public BorderedButton(ActionListener container, String text, Font font, Color defaultColor, Color enteredColor,
                          Color backDefaultColor, Color borderDefaultColor) {
        this(container, text, font, defaultColor, enteredColor, defaultColor, backDefaultColor, backDefaultColor,
                backDefaultColor, borderDefaultColor, borderDefaultColor, borderDefaultColor);
    }

    public void setIsSelected(boolean selected) {
        if (!isSelected && selected) {
            setForeground(selectedColor);
            setBackground(backSelectedColor);
            setBorder(BorderFactory.createMatteBorder(2, 5, 2, 2, borderSelectedColor));
        } else if (!selected && isSelected) {
            setForeground(defaultColor);
            setBackground(backDefaultColor);
            setBorder(BorderFactory.createMatteBorder(2, 5, 2, 2, borderDefaultColor));
        }
        this.isSelected = selected;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void addMouseListener() {
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isSelected) {
                    setForeground(enteredColor);
                    setBackground(backEnteredColor);
                    setBorder(BorderFactory.createMatteBorder(2, 5, 2, 2, borderEnteredColor));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (!isSelected) {
                    setForeground(defaultColor);
                    setBackground(backDefaultColor);
                    setBorder(BorderFactory.createMatteBorder(2, 5, 2, 2, borderDefaultColor));
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                container.actionPerformed(new ActionEvent(BorderedButton.this, 1, "MousePressed"));
            }
        });
    }
}
