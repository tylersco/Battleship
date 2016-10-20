package com.csci4448.MediaManagementSystem.ui;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel implements ActionListener{

    private Display display;

    private TextButton storeButton;
    private TextButton libraryButton;
    private SearchBar searchBar;
    private TextButton fundsButton;
    private TextButton accountButton;

    private Font mainButtonFont = new Font("Helvetice Neue", Font.PLAIN, 25);
    private Font subButtonFont = new Font("Helvetice Neue", Font.PLAIN, 12);
    private Color defaultColor = new Color(75, 75, 75, 180);
    private Color enteredColor = new Color(75, 75, 75);
    private Color selectedColor = new Color(121, 179, 188);

    public MenuPanel(Display display) {
        this.display = display;
        setLayout(null);
        addButtons();
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
    }

    public void resizeMenu(int width, int height) {
        //storeButton.setLocation();
        //libraryButton.setLocation();
        searchBar.setLocation(width-185, 10);
        fundsButton.setLocation(width-110, 35);
        accountButton.setLocation(width-175, 35);
    }

    public void removeSearchBar() {
        remove(searchBar);
    }

    public void addSearchBar() {
        add(searchBar);
    }

    private void addButtons() {
        Dimension size;
        storeButton = new TextButton(this, "Store", mainButtonFont, defaultColor, enteredColor, selectedColor);
        size = storeButton.getPreferredSize();
        storeButton.setSize(size);
        storeButton.setLocation(80, (55-(int)size.getHeight())/2);
        add(storeButton);

        libraryButton = new TextButton(this, "Library", mainButtonFont, defaultColor, enteredColor, selectedColor);
        size = libraryButton.getPreferredSize();
        libraryButton.setSize(size);
        libraryButton.setLocation(180, (55-(int)size.getHeight())/2);
        add(libraryButton);

        accountButton = new TextButton(this, "Account", subButtonFont, defaultColor, enteredColor, selectedColor);
        size = accountButton.getPreferredSize();
        accountButton.setSize(size);
        accountButton.setLocation(775, 5);
        add(accountButton);

        fundsButton = new TextButton(this, "$0.00", subButtonFont, defaultColor, enteredColor, selectedColor);
        size = fundsButton.getPreferredSize();
        fundsButton.setSize(size);
        fundsButton.setLocation(840, 5);
        add(fundsButton);

        searchBar = new SearchBar(this);
        size = searchBar.getPreferredSize();
        searchBar.setSize(size);
        searchBar.setLocation(775, 26);
        add(searchBar);
    }

    public boolean buttonClicked(JComponent component) {
        if (component.equals(storeButton)) {
            storeButton.setIsSelected(true);
            libraryButton.setIsSelected(false);
            //display
        } else if (component.equals(libraryButton)) {
            storeButton.setIsSelected(false);
            libraryButton.setIsSelected(true);
            //display
        } else if (component.equals(fundsButton)) {
            //display
        } else if (component.equals(accountButton)) {

        } else if (component.equals(searchBar)) {
            System.out.println(searchBar.getSearchText());
        }
        return true;
    }
}
