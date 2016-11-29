package com.csci4448.MediaManagementSystem.ui.design;

import java.awt.*;

public enum Style {

    MENU_MAIN (UIFont.FONT_25, UIColor.DEFAULT_MEDIUM, UIColor.DEFAULT_DARK, UIColor.BLUE_MENU),
    MENU_SUB (UIFont.FONT_12, UIColor.DEFAULT_MEDIUM, UIColor.DEFAULT_DARK),
    MENU_SEARCH (UIFont.FONT_16, UIColor.DEFAULT_MEDIUM, UIColor.DEFAULT_DARK),

    LOGIN_BASIC (UIFont.FONT_14, UIColor.DEFAULT_LIGHT, UIColor.DEFAULT_DARK),
    LOGIN_CREATE (UIFont.FONT_10, UIColor.BLUE_DARK, UIColor.BLUE_LIGHT),

    CREATE_BASIC (UIFont.FONT_16, UIColor.DEFAULT_LIGHT, UIColor.DEFAULT_DARK),
    CREATE_CANCEL (UIFont.FONT_11, UIColor.DEFAULT_LIGHT, UIColor.RED_CANCEL),

    REVIEW_HEADER (UIFont.FONT_20, UIColor.DEFAULT_DARK),
    REVIEW_BODY (UIFont.FONT_15, UIColor.DEFAULT_MEDIUM),
    REVIEW_SUB (UIFont.FONT_18, UIColor.DEFAULT_LIGHT, UIColor.DEFAULT_DARK),
    REVIEW_CANCEL (UIFont.FONT_18, UIColor.DEFAULT_LIGHT, UIColor.RED_CANCEL),
    STAR_RATING (UIFont.FONT_15, UIColor.GRAY_STAR, UIColor.GOLD_STAR, UIColor.GOLD_STAR),

    REVIEW_USER (UIFont.FONT_15, UIColor.DEFAULT_MEDIUM, UIColor.DEFAULT_DARK),
    REVIEW_STAR1 (UIFont.FONT_12, UIColor.GRAY_STAR),
    REVIEW_STAR2 (UIFont.FONT_12, UIColor.GOLD_STAR),

    CONFIRM_MESSAGE (UIFont.FONT_20, UIColor.DEFAULT_DARK),
    CONFIRM_OK (UIFont.FONT_18, UIColor.DEFAULT_MEDIUM, UIColor.BLUE_CONFIRM),
    CONFIRM_CANCEL (UIFont.FONT_18, UIColor.DEFAULT_MEDIUM, UIColor.RED_CANCEL),

    ERROR_MESSAGE (UIFont.FONT_18, UIColor.RED_ERROR),
    ERROR_CLOSE (UIFont.FONT_16, UIColor.DEFAULT_MEDIUM, UIColor.DEFAULT_DARK),

    INDMEDIA_TITLE (UIFont.FONT_35, UIColor.DEFAULT_DARK),
    INDMEDIA_DESCRIPTION (UIFont.FONT_18I, UIColor.DEFAULT_DARK),
    INDMEDIA_LARGELABEL (UIFont.FONT_22I, UIColor.DEFAULT_DARK),
    INDMEDIA_SMALLLABEL (UIFont.FONT_18I, UIColor.DEFAULT_DARK),
    INDMEDIA_REVIEWSLABEL (UIFont.FONT_25, UIColor.DEFAULT_DARK),

    ADMIN_LABEL (UIFont.FONT_22B, UIColor.DEFAULT_DARK),
    ADMIN_TEXT (UIFont.FONT_20, UIColor.DEFAULT_DARK),
    ADMIN_BUTTON (UIFont.FONT_20, UIColor.DEFAULT_LIGHT, UIColor.DEFAULT_DARK, UIColor.DEFAULT_DARK),

    ;
    private final UIFont defaultFont;
    private final UIColor primaryColor;
    private final UIColor secondaryColor;
    private final UIColor highlightColor;

    Style(UIFont defaultFont, UIColor primaryColor) {
        this.defaultFont = defaultFont;
        this.primaryColor = primaryColor;
        this.secondaryColor = primaryColor;
        this.highlightColor = primaryColor;
    }

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
