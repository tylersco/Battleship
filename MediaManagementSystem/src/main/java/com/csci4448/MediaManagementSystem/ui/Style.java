package com.csci4448.MediaManagementSystem.ui;

import java.awt.*;

public enum Style {

    MENU_MAIN (UIFont.MENU_LARGE, UIColor.DEFAULT_MEDIUM, UIColor.DEFAULT_DARK, UIColor.DEFAULT_DARK),
    MENU_SUB (UIFont.MENU_SMALL, UIColor.DEFAULT_MEDIUM, UIColor.DEFAULT_DARK),

    LOGIN_BASIC (UIFont.LOGIN_LARGE, UIColor.DEFAULT_LIGHT, UIColor.DEFAULT_DARK),
    LOGIN_CREATE (UIFont.LOGIN_SMALL, UIColor.BLUE_DARK, UIColor.BLUE_LIGHT);


    private final UIFont defaultFont;
    private final UIColor defaultColor;
    private final UIColor enteredColor;
    private final UIColor selectedColor;

    Style(UIFont defaultFont, UIColor defaultColor, UIColor enteredColor) {
        this.defaultFont = defaultFont;
        this.defaultColor = defaultColor;
        this.enteredColor = enteredColor;
        this.selectedColor = defaultColor;
    }

    Style(UIFont defaultFont, UIColor defaultColor, UIColor enteredColor, UIColor selectedColor) {
        this.defaultFont = defaultFont;
        this.defaultColor = defaultColor;
        this.enteredColor = enteredColor;
        this.selectedColor = selectedColor;
    }


    public Font getDefaultFont() {
        return defaultFont.getFont();
    }

    public Color getDefaultColor() {
        return defaultColor.getColor();
    }

    public Color getEnteredColor() {
        return enteredColor.getColor();
    }

    public Color getSelectedColor() {
        return selectedColor.getColor();
    }
}
