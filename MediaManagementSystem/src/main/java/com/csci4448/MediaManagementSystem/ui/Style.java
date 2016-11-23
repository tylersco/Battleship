package com.csci4448.MediaManagementSystem.ui;

import java.awt.*;

public enum Style {

    MENU_MAIN (UIFont.MENU_LARGE, UIColor.DEFAULT_MEDIUM, UIColor.DEFAULT_DARK, UIColor.DEFAULT_DARK),
    MENU_SUB (UIFont.MENU_SMALL, UIColor.DEFAULT_MEDIUM, UIColor.DEFAULT_DARK),

    LOGIN_BASIC (UIFont.LOGIN_LARGE, UIColor.DEFAULT_LIGHT, UIColor.DEFAULT_DARK),
    LOGIN_CREATE (UIFont.LOGIN_SMALL, UIColor.BLUE_DARK, UIColor.BLUE_LIGHT),

    CREATE_BASIC (UIFont.CREATE_LARGE, UIColor.DEFAULT_LIGHT, UIColor.DEFAULT_DARK),
    CREATE_CANCEL (UIFont.CREATE_SMALL, UIColor.DEFAULT_LIGHT, UIColor.RED_CANCEL);


    private final UIFont defaultFont;
    private final UIColor primaryColor;
    private final UIColor secondaryColor;
    private final UIColor highlightColor;

    Style(UIFont defaultFont, UIColor primaryColor, UIColor secondaryColor) {
        this.defaultFont = defaultFont;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.highlightColor = primaryColor;
    }

    Style(UIFont defaultFont, UIColor primaryColor, UIColor secondaryColor, UIColor highlightColor) {
        this.defaultFont = defaultFont;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.highlightColor = highlightColor;
    }


    public Font getDefaultFont() {
        return defaultFont.getFont();
    }

    public Color getPrimaryColor() {
        return primaryColor.getColor();
    }

    public Color getSecondaryColor() {
        return secondaryColor.getColor();
    }

    public Color getHighlightColor() {
        return highlightColor.getColor();
    }
}
