package com.csci4448.MediaManagementSystem.ui.components;

import com.csci4448.MediaManagementSystem.ui.IndividualMediaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditReviewPanel extends JPanel implements ActionListener {

    IndividualMediaPanel parent;

    private TextArea reviewTextField;
    private TextArea reviewTitle;
    private ArrayList<TextButton> stars = new ArrayList<TextButton>();
    private TextButton submit;
    private TextButton cancel;

    private Color defaultColor = new Color(75, 75, 75, 255);
    private Color enteredColor = new Color(75, 75, 75);

    private Font fontStar = new Font("Helvetice Neue", Font.PLAIN, 15);
    private Color defaultColorStar = new Color(149, 149, 149);
    private Color selectedColorStar = new Color(253, 216, 75);

    public EditReviewPanel(IndividualMediaPanel parent) {
        this.parent = parent;

        setLayout(null);
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(228, 228, 228)));

        reviewTitle = new TextArea("Add Review", new Font("Helvetice Neue", Font.PLAIN, 17), defaultColor);
        reviewTitle.setSize(reviewTitle.getPreferredSize());
        reviewTitle.setLocation(5, 5);
        add(reviewTitle);

        reviewTextField = new TextArea("Enter Review", new Font("Helvetice Neue", Font.PLAIN, 15), new Color(75, 75, 75, 200));
        reviewTextField.setLineWrap(true);
        reviewTextField.setEditable(true);
        reviewTextField.setSize(650, 200);
        reviewTextField.setLocation(15, 60);
        add(reviewTextField);

        submit = new TextButton(this, "Submit", new Font("Helvetice Neue", Font.PLAIN, 15), defaultColor, enteredColor);
        submit.setSize(submit.getPreferredSize());
        submit.setLocation(630, 275);
        add(submit);

        cancel = new TextButton(this, "Cancel", new Font("Helvetice Neue", Font.PLAIN, 15), defaultColor, enteredColor);
        cancel.setSize(cancel.getPreferredSize());
        cancel.setLocation(550, 275);
        add(cancel);

        setSize(680, 300);
        setLocation(135, 100);


        for (int i = 0; i < 5; i++) {
            TextButton star = new TextButton(this, "★", fontStar, defaultColorStar, selectedColorStar, selectedColorStar);
            Dimension d = star.getPreferredSize();
            star.setSize(d);
            star.setLocation((int)d.getWidth() * i + 15, 35);
            add(star);
            stars.add(star);
        }

    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
    }

    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();
        if (component.equals(cancel)) {
            parent.getContent().remove(this);
            parent.getContent().validate();
            parent.getContent().repaint();
        } else if (component.equals(submit)) {
            //ToDo: controller submit review request
        }
    }
}
