package com.csci4448.MediaManagementSystem.ui.components;

import com.csci4448.MediaManagementSystem.ui.design.Style;
import com.csci4448.MediaManagementSystem.ui.design.TextComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchBar extends JPanel implements ActionListener {

    private ActionListener container;

    private EnterTextField input;
    private TextButton searchSymbol;

    public SearchBar(ActionListener container) {
        this.container = container;

        setPreferredSize(new Dimension(140, 20));
        setLayout(null);

        input = new EnterTextField(this, "search", new Font("Helvetice Neue", Font.PLAIN, 12), new Color(100, 100, 100, 180), new Color(100, 100, 100), false);
        input.setSize(115, 20);
        input.setLocation(0, 0);
        input.setBackground(null);
        add(input);

        searchSymbol = TextComponentFactory.smallButton(this, "⌕", Style.MENU_SEARCH);
        searchSymbol.setLocation(120, -2);
        add(searchSymbol);
    }

    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();
        String searchText = input.getText().trim();
        if (searchText.length() < 0) {
            input.errorText("search");
        } else {
            container.actionPerformed(new ActionEvent(SearchBar.this, 1, searchText));
        }

    }

}
