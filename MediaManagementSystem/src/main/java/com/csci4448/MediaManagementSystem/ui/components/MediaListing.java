package com.csci4448.MediaManagementSystem.ui.components;

import com.csci4448.MediaManagementSystem.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MediaListing extends JPanel implements ActionListener {

    private MainController controller;

    private int leftMargin = 6;
    private int rightMargin = 6;
    private int topMargin = 6;
    private int bottomMargin = 2;

    private double imageWidthRatio = 1;
    private double imageHeightRatio = .8;

    private int mediaId;
    private MediaImage image;
    private TextButton priceButton;
    private TextButton titleButton;

    private Color defaultColor = new Color(75, 75, 75, 180);
    private Color enteredColor = new Color(75, 75, 75);

    public MediaListing(MainController controller, int mediaId, String imagePath, String title, int price) {
        this.controller = controller;
        this.mediaId = mediaId;

        setLayout(null);
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(228, 228, 228)));

        image = new MediaImage(imagePath);
        add(image);
        image.setLocation(leftMargin, topMargin);

        titleButton = new TextButton(this, title, new Font("Helvetice Neue", Font.PLAIN, 20), defaultColor, enteredColor);
        add(titleButton);
        priceButton = new TextButton(this, "$" + price, new Font("Helvetice Neue", Font.PLAIN, 14), defaultColor, enteredColor);
        add(priceButton);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionPerformed(new ActionEvent(MediaListing.this, 1, "MediaListingClicked"));
            }
        });
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
        int contentsWidth = width - leftMargin - rightMargin;
        int contentsHeight = height - topMargin - bottomMargin;
        if (image != null) {
            image.loadMediaImage((int)(contentsWidth * imageWidthRatio), (int)(contentsHeight * imageHeightRatio));
        }

        Dimension size;
        size = titleButton.getPreferredSize();
        titleButton.setSize(size);
        titleButton.setLocation(leftMargin + 5, (int)(contentsHeight * imageHeightRatio) + 8);

        size = priceButton.getPreferredSize();
        priceButton.setSize(size);
        priceButton.setLocation(contentsWidth - (int)size.getWidth(), contentsHeight - (int)size.getHeight());
    }

    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();
        if (component.equals(priceButton) | component.equals(titleButton) | component.equals(this)) {
            controller.individualMediaRequest(mediaId);
        }
    }
}
