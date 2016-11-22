package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.ui.components.TextButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonFactory {

    //ToDo: move fonts and colors to their own class
    //ToDo: these fonts/colors are repetitive for now becasue each different button type will have its own font and colors
    private static Font menuMainFont = new Font("Helvetice Neue", Font.PLAIN, 25);
    private static Color menuMainDefaultColor = new Color(75, 75, 75, 180);
    private static Color menuMainEnteredColor = new Color(75, 75, 75);
    private static Color menuMainSelectedColor = new Color(75, 75, 75);

    private static Font menuSubFont = new Font("Helvetice Neue", Font.PLAIN, 12);
    private static Color menuSubDefaultColor = new Color(75, 75, 75, 180);
    private static Color menuSubEnteredColor = new Color(75, 75, 75);
    private static Color menuSubSelectedColor = new Color(75, 75, 75);

    public ButtonFactory() {

    }

    public static TextButton menuMain(ActionListener container, String text) {
        TextButton button = new TextButton(container, text, menuMainFont, menuMainDefaultColor, menuMainEnteredColor, menuMainSelectedColor);
        setSizeWrapWidth(button, 55);
        return button;
    }

    public static TextButton menuSub(ActionListener container, String text) {
        TextButton button = new TextButton(container, text, menuSubFont, menuSubDefaultColor, menuSubEnteredColor, menuSubSelectedColor);
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
        component.setSize((int)size.getWidth() + widthBuffer, (int)size.getHeight() + heightBuffer);
    }

    //ToDo: potentially change menuMain to be a regular wrap instead of needing this
    private static void setSizeWrapWidth(JComponent component , int height) {
        Dimension size;
        size = component.getPreferredSize();
        component.setSize((int)size.getWidth(), height);
    }
}
