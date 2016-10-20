package com.csci4448.MediaManagementSystem.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SearchBar extends JPanel {

    private ActionListener container;

    private JTextField input;
    private JPanel searchSymbol;

    public SearchBar(ActionListener container) {
        this.container = container;

        setPreferredSize(new Dimension(145, 20));
        setLayout(null);

        input = new JTextField("search");
        input.setFont(new Font("Helvetice Neue", Font.PLAIN, 12));
        input.setSize(115, 20);
        input.setLocation(3, 0);
        input.setForeground(new Color(100, 100, 100));
        input.setBackground(null);
        input.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        addListeners();
        add(input);

        //ToDo: add searchSymbol at the end of the SearchBar

    }

    public void addListeners() {
        input.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                container.buttonClicked(SearchBar.this);
            }
        });
        input.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //ToDo: check boolean value that toggles to false after text has been typed
                if (input.getText().equals("search")) {
                    input.setText("");
                }
            }
        });
    }

    public String getSearchText() {
        return input.getText();
    }



}
