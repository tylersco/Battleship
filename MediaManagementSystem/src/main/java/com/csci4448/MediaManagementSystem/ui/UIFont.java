package com.csci4448.MediaManagementSystem.ui;

import java.awt.*;

public enum UIFont {

    MENU_LARGE ("Helvetice Neue", Font.PLAIN, 25),
    MENU_SMALL ("Helvetice Neue", Font.PLAIN, 12),
    LOGIN_LARGE ("Helvetice Neue", Font.PLAIN, 14),
    LOGIN_SMALL ("Helvetice Neue", Font.PLAIN, 10);

    private final Font font;

    UIFont(String name, int type, int size) {
        font = new Font(name, type, size);
    }

    public Font getFont() {
        return font;
    }
}
