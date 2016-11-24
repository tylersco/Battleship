package com.csci4448.MediaManagementSystem.ui.design;

import com.csci4448.MediaManagementSystem.ui.components.EnterTextField;
import com.csci4448.MediaManagementSystem.ui.components.TextButton;
import com.csci4448.MediaManagementSystem.ui.components.TextPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TextComponentFactory {

    public TextComponentFactory() {

    }

    public static TextButton largeButton(ActionListener container, String text, Style style) {
        TextButton button = new TextButton(container, text, style.getDefaultFont(), style.getPrimaryColor(), style.getSecondaryColor(), style.getHighlightColor());
        setSizeWrap(button);
        return button;
    }

    public static TextButton smallButton(ActionListener container, String text, Style style) {
        TextButton button = new TextButton(container, text, style.getDefaultFont(), style.getPrimaryColor(), style.getSecondaryColor(), style.getHighlightColor());
        setSizeWrap(button, 1, 0);
        return button;
    }

    public static EnterTextField enterText(ActionListener container, String text, Style style) {
        EnterTextField field = new EnterTextField(container, text, style.getDefaultFont(), style.getPrimaryColor(), style.getSecondaryColor(), false);
        setSizeWrap(field);
        return field;
    }

    public static EnterTextField enterTextHidden(ActionListener container, String text, Style style) {
        EnterTextField field = new EnterTextField(container, text, style.getDefaultFont(), style.getPrimaryColor(), style.getSecondaryColor(), true);
        setSizeWrap(field);
        return field;
    }

    public static EnterTextField enterText(ActionListener container, String text, Style style, int width, int height) {
        EnterTextField field = new EnterTextField(container, text, style.getDefaultFont(), style.getPrimaryColor(), style.getSecondaryColor(), false);
        field.setSize(width, height);
        field.setPreferredSize(new Dimension(width, height));
        return field;
    }

    public static EnterTextField enterTextHidden(ActionListener container, String text, Style style, int width, int height) {
        EnterTextField field = new EnterTextField(container, text, style.getDefaultFont(), style.getPrimaryColor(), style.getSecondaryColor(), true);
        field.setSize(width, height);
        field.setPreferredSize(new Dimension(width, height));
        return field;
    }

    public static TextPane textPane(String text, Style style) {
        TextPane pane = new TextPane(text, style.getDefaultFont(), style.getPrimaryColor());
        setSizeWrap(pane);
        return pane;
    }

    public static TextPane textPaneEdit(String text, Style style, int width, int height) {
        TextPane pane = new TextPane(text, style.getDefaultFont(), style.getPrimaryColor());
        pane.setLineWrap(true);
        pane.setEditable(true);
        pane.setSize(width, height);
        pane.setPreferredSize(new Dimension(width, height));
        return pane;
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
