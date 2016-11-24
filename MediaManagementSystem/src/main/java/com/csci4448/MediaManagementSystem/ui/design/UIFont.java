package com.csci4448.MediaManagementSystem.ui.design;

import java.awt.*;

public enum UIFont {

    FONT_35  (35),
    FONT_25  (25),
    FONT_22  (22),
    FONT_22I (22, Font.ITALIC),
    FONT_20  (20),
    FONT_20B (20, Font.BOLD),
    FONT_18  (18),
    FONT_18I (18, Font.ITALIC),
    FONT_16  (16),
    FONT_15  (15),
    FONT_14  (14),
    FONT_12  (12),
    FONT_11  (11),
    FONT_10  (10),;


    private String defaultFontName = "Helvetice Neue";
    private int defaultFontType = Font.PLAIN;

    private final Font font;

    UIFont(int size) {
        font = new Font(defaultFontName, defaultFontType, size);
    }

    UIFont(int size, int type) {
        font = new Font(defaultFontName, type, size);
    }

    UIFont(String name, int size, int type) {
        font = new Font(name, type, size);
    }

    public Font getFont() {
        return font;
    }
}
