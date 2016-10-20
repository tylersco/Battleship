package com.csci4448.MediaManagementSystem.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBar extends JPanel {

    private ActionListener container;

    private EnterTextField input;
    private JPanel searchSymbol;

    public SearchBar(ActionListener container) {
        this.container = container;

        setPreferredSize(new Dimension(145, 20));
        setLayout(null);

        input = new EnterTextField(container, "search", new Font("Helvetice Neue", Font.PLAIN, 12), new Color(100, 100, 100, 180), new Color(100, 100, 100));
        input.setSize(115, 20);
        input.setBackground(null);

        //addListeners();
        add(input);

        //ToDo: add searchSymbol at the end of the SearchBar

    }

}
