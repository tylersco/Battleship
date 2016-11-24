package com.csci4448.MediaManagementSystem.ui;

import java.awt.*;

public enum UIFont {

    FONT_25 (25),
    FONT_20 (20),
    FONT_18 (18),
    FONT_16 (16),
    FONT_15 (15),
    FONT_14 (14),
    FONT_12 (12),
    FONT_11 (11),
    FONT_10 (10),;


    private String defaultFontName = "Helvetice Neue";
    private int defaultFontType = Font.PLAIN;

    private final Font font;

    UIFont(int size) {
        font = new Font(defaultFontName, defaultFontType, size);
    }

    UIFont(String name, int type, int size) {
        font = new Font(name, type, size);
    }

    public Font getFont() {
        return font;
    }
}
