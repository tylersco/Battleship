package com.csci4448.MediaManagementSystem.ui.components;

import javax.swing.*;
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


}
