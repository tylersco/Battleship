package com.csci4448.MediaManagementSystem.ui.components;

import com.csci4448.MediaManagementSystem.ui.design.Style;
import com.csci4448.MediaManagementSystem.ui.design.TextComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReviewPanel extends JPanel implements ActionListener {

    private TextPane reviewText;
    private TextButton userText;
    private ArrayList<TextPane> stars = new ArrayList<TextPane>();

    private int leftMargin = 5;
    private int rightMargin = 5;
    private int topMargin = 5;
    private int bottomMargin = 5;

    public ReviewPanel(String user, String review, int rating) {
        setLayout(null);
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(228, 228, 228)));

        userText = TextComponentFactory.smallButton(this, user, Style.REVIEW_USER);
        add(userText);

        reviewText = TextComponentFactory.textPane(review, Style.REVIEW_BODY);
        reviewText.setLineWrap(true);
        reviewText.setSize(350, 250);
        reviewText.setLocation(400, 85);
        add(reviewText);

        for (int i = 0; i < rating; i++) {
            TextPane star = TextComponentFactory.textPane("★", Style.REVIEW_STAR2);
            add(star);
            stars.add(star);
        }
        for (int i = 5; i > rating; i--) {
            TextPane star = TextComponentFactory.textPane("★", Style.REVIEW_STAR1);
            add(star);
            stars.add(star);
        }

    }

    public void setSize(int width, int height) {
        super.setSize(width, height);

        Dimension size = userText.getPreferredSize();
        int userTextWidth = (int)size.getWidth();
        int userTextHeight = (int)size.getHeight();
        userText.setSize(size);
        userText.setLocation(leftMargin, topMargin);

        for (int i = 0; i < stars.size(); i++) {
            TextPane star = stars.get(i);
            star.setLocation(leftMargin + userTextWidth + 10 + (int)star.getWidth() * i, topMargin);
        }

        reviewText.setSize(width - leftMargin - rightMargin, height - userTextHeight - topMargin - bottomMargin);
        reviewText.setLocation(leftMargin, topMargin + userTextHeight);

    }

    public void actionPerformed(ActionEvent event) {

    }
}
