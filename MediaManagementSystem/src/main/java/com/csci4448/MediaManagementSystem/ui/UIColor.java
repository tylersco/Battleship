package com.csci4448.MediaManagementSystem.ui;

import java.awt.*;

public enum UIColor {

    DEFAULT_LIGHT (75, 75, 75, 140),
    DEFAULT_MEDIUM (75, 75, 75, 180),
    DEFAULT_DARK (75, 75, 75),

    BLUE_LIGHT (70, 177, 249),
    BLUE_DARK (55, 137, 199),

    RED_CANCEL (249, 72, 67);

    private final Color color;

    UIColor(int r, int g, int b, int a) {
        color = new Color(r, g, b, a);
    }

    UIColor(int r, int g, int b) {
        color = new Color(r, g, b);
    }

    public Color getColor() {
        return color;
    }
}
