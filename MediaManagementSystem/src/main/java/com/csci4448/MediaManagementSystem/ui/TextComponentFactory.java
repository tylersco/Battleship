package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.ui.components.EnterTextField;
import com.csci4448.MediaManagementSystem.ui.components.TextButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TextComponentFactory {

    public TextComponentFactory() {

    }

    public static TextButton menuMainButton(ActionListener container, String text, Style style) {
        TextButton button = new TextButton(container, text, style.getDefaultFont(), style.getDefaultColor(), style.getEnteredColor(), style.getSelectedColor());
        setSizeWrap(button);
        return button;
    }

    public static TextButton smallButton(ActionListener container, String text, Style style) {
        TextButton button = new TextButton(container, text, style.getDefaultFont(), style.getDefaultColor(), style.getEnteredColor(), style.getSelectedColor());
        setSizeWrap(button, 1, 0);
        return button;
    }

    private static void setSizeWrap(JComponent component) {
        Dimension size;
        size = component.getPreferredSize();
        component.setSize(size);
    }

    private static void setSizeWrap(JComponent component, int widthBuffer, int heightBuffer) {
        Dimension size;
        size = component.getPreferredSize();
        size.width = (int)size.getWidth() + widthBuffer;
        size.height = (int)size.getHeight() + heightBuffer;
        component.setSize(size);
        component.setPreferredSize(size);
    }
}
