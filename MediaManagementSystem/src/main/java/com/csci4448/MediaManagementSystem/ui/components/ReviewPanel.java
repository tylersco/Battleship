package com.csci4448.MediaManagementSystem.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReviewPanel extends JPanel implements ActionListener {

    private TextArea reviewText;
    private TextButton userText;
    private TextArea ratingText;

    private int leftMargin = 5;
    private int rightMargin = 5;
    private int topMargin = 5;
    private int bottomMargin = 5;

    private Color defaultColor = new Color(75, 75, 75, 180);
    private Color enteredColor = new Color(75, 75, 75);

    public ReviewPanel(String user, String review, int rating) {
        setLayout(null);
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(228, 228, 228)));

        userText = new TextButton(this, user, new Font("Helvetice Neue", Font.PLAIN, 15), defaultColor, enteredColor);
        add(userText);

        reviewText = new TextArea(review, new Font("Helvetice Neue", Font.PLAIN, 15), new Color(75, 75, 75, 200));
        reviewText.setLineWrap(true);
        reviewText.setSize(350, 250);
        reviewText.setLocation(400, 85);
        add(reviewText);

        //ToDo: display the number of starts not a number
        ratingText = new TextArea(Integer.toString(rating), new Font("Helvetice Neue", Font.PLAIN, 13), new Color(75, 75, 75, 200));
        ratingText.setLineWrap(true);
        ratingText.setSize(350, 250);
        ratingText.setLocation(400, 85);
        add(ratingText);

    }

    public void setSize(int width, int height) {
        super.setSize(width, height);

        Dimension size = userText.getPreferredSize();
        int userTextWidth = (int)size.getWidth();
        int userTextHeight = (int)size.getHeight();
        userText.setSize(size);
        userText.setLocation(leftMargin, topMargin);

        ratingText.setSize(userTextHeight, 20);
        ratingText.setLocation(leftMargin + userTextWidth + 10, topMargin);

        reviewText.setSize(width - leftMargin - rightMargin, height - userTextHeight - topMargin - bottomMargin);
        reviewText.setLocation(leftMargin, topMargin + userTextHeight);

    }

    public void actionPerformed(ActionEvent event) {

    }
}
