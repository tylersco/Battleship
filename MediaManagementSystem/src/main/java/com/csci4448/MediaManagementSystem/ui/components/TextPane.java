package com.csci4448.MediaManagementSystem.ui.components;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class TextPane extends JTextArea {


    public TextPane(String text, Font font, Color color) {
        super(text);
        setFont(font);
        setForeground(color);
        setEditable(false);
        setOpaque(false);
        setBackground(null);
        setWrapStyleWord(true);

    }



    /*
    public void setEditable(boolean setEditable) {
        super.setEditable(setEditable);
        //setHighlighter(setEditable);
        setLineWrap(setEditable);
        setWrapStyleWord(setEditable);
        DefaultCaret caret = (DefaultCaret)getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }
    */
}
