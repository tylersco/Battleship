package com.csci4448.MediaManagementSystem.ui.components;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.design.MediaError;
import com.csci4448.MediaManagementSystem.ui.design.Style;
import com.csci4448.MediaManagementSystem.ui.design.TextComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditReviewPanel extends JPanel implements ActionListener {

    private MainController controller;
    private int mediaId;
    private int selectedStar = -1;

    private TextPane reviewTextField;
    private TextPane reviewTitle;
    private ArrayList<TextButton> stars = new ArrayList<TextButton>();
    private TextButton submit;
    private TextButton cancel;

    public EditReviewPanel(MainController controller, int mediaId) {
        this.controller = controller;
        this.mediaId = mediaId;

        setLayout(null);
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(228, 228, 228)));

        reviewTitle = TextComponentFactory.textPane("Add Review", Style.REVIEW_HEADER);
        reviewTitle.setLocation(5, 5);
        add(reviewTitle);

        reviewTextField = TextComponentFactory.textPaneEdit(this, "Enter your review here", Style.REVIEW_BODY, 650, 150);
        reviewTextField.setLocation(15, 60);
        add(reviewTextField);

        submit = TextComponentFactory.smallButton(this, "Submit", Style.REVIEW_SUB);
        submit.setLocation(600, 255);
        add(submit);

        cancel = TextComponentFactory.smallButton(this, "Cancel", Style.REVIEW_CANCEL);
        cancel.setLocation(520, 255);
        add(cancel);

        setPreferredSize(new Dimension(680, 300));


        for (int i = 0; i < 5; i++) {
            TextButton star = TextComponentFactory.smallButton(this, "★", Style.STAR_RATING);
            star.setLocation((int)star.getWidth() * i + 15, 35);
            add(star);
            stars.add(star);
        }

    }

    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();
        if (component.equals(cancel)) {
            controller.reviewMediaCancelRequest();
        } else if (component.equals(submit)) {
            boolean validInput = true;

            String review = reviewTextField.getText().trim();
            if (review.equals("") || review.equals("Enter your review here")) {
                validInput = false;
                reviewTextField.setText("Enter your review here");
                reviewTextField.setForeground(new Color(249, 72, 67, 180));
            }

            if (selectedStar < 0 || selectedStar > 4) {
                validInput = false;
                for (int i = 0; i < stars.size(); i++) {
                    stars.get(i).setForeground(new Color(249, 72, 67, 126));
                }
            }

            if (validInput) controller.reviewMediaSubmitRequest(mediaId, review, selectedStar + 1);

        } else if (component.equals(reviewTextField)) {
            if (reviewTextField.getText().trim().equals("Enter your review here")) {
                reviewTextField.setForeground(new Color(75, 75, 75, 180));
                reviewTextField.setText("");
            }
        } else {
            for (int i = 0; i < 5; i++) {
                if (component.equals(stars.get(i))) {
                    selectedStar = i;
                    break;
                }
            }
            for (int i = 0; i <= selectedStar; i++) {
                stars.get(i).setIsSelected(true);
            }
            for (int i = 4; i > selectedStar; i--) {
                stars.get(i).setIsSelected(false);
            }
        }
    }
}
