package com.csci4448.MediaManagementSystem.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MediaListing extends JPanel implements ActionListener {

    private int leftMargin = 0;
    private int rightMargin = 0;
    private int topMargin = 0;
    private int bottomMargin = 0;

    private double imageWidthRatio = 1;
    private double imageHeightRatio = 1;

    private MediaImage image;
    private TextButton actionButton;
    private TextButton title;

    public MediaListing() {
        setLayout(null);
        setBackground(new Color(250, 250, 250));
    }

    public void setActionButton(TextButton actionButton) {
        if (this.actionButton != null) {
            remove(this.actionButton);
        }
        this.actionButton = actionButton;
        add(actionButton);
        actionButton.setLocation(leftMargin, topMargin);
    }

    public void setTitle(TextButton title) {
        if (this.title != null) {
            remove(this.title);
        }
        this.title = title;
        add(title);
        title.setLocation(0, 0);
    }

    public void setImage(MediaImage image) {
        if (this.image != null) {
            remove(this.image);
        }
        this.image = image;
        add(image);
        image.setLocation(leftMargin, topMargin);
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
        if (image != null) {
            image.loadMediaImage((int)((width - leftMargin - rightMargin) * imageWidthRatio), (int)((height - leftMargin - rightMargin) * imageHeightRatio));
        }
    }

    public void actionPerformed(ActionEvent event) {

    }
}
