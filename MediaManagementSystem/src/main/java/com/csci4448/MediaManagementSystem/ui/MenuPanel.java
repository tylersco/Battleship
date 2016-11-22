package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.components.SearchBar;
import com.csci4448.MediaManagementSystem.ui.components.TextButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    private MainController controller;

    private TextButton storeButton;
    private TextButton libraryButton;
    private SearchBar searchBar;
    private TextButton fundsButton;
    private TextButton accountButton;

    private Font mainButtonFont = new Font("Helvetice Neue", Font.PLAIN, 25);
    private Font subButtonFont = new Font("Helvetice Neue", Font.PLAIN, 12);
    private Color defaultColor = new Color(75, 75, 75, 180);
    private Color enteredColor = new Color(75, 75, 75);
    private Color selectedColor = new Color(75, 75, 75);

    public MenuPanel(MainController controller) {
        this.controller = controller;
        setLayout(null);
        initializeMenuComponents();
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
    }

    private void initializeMenuComponents() {
        storeButton = TextComponentFactory.menuMainButton(this, "Store", Style.MENU_MAIN);
        storeButton.setLocation(80, 0);
        add(storeButton);

        libraryButton = TextComponentFactory.menuMainButton(this, "Library", Style.MENU_MAIN);
        libraryButton.setLocation(180, 0);
        add(libraryButton);

        accountButton = TextComponentFactory.smallButton(this, "Account", Style.MENU_SUB);
        accountButton.setLocation(775, 5);
        add(accountButton);

        fundsButton = TextComponentFactory.smallButton(this, "$0.00", Style.MENU_SUB);
        fundsButton.setLocation(840, 5);
        add(fundsButton);

        searchBar = new SearchBar(this);
        searchBar.setSize(searchBar.getPreferredSize());
        searchBar.setLocation(775, 26);
        add(searchBar);
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
        //storeButton.setLocation();
        //libraryButton.setLocation();
        searchBar.setLocation(width-185, 10);
        //ToDo: shift funds to end of searchBar
        fundsButton.setLocation(width-110, 35);
        accountButton.setLocation(width-175, 35);
    }

    public TextButton getStoreButton() {
        return storeButton;
    }

    public TextButton getLibraryButton() {
        return libraryButton;
    }

    public void removeSearchBar() {
        remove(searchBar);
        //ToDo: account/funds buttons locations
    }

    public void addSearchBar() {
        add(searchBar);
        //ToDo: account/funds buttons locations
    }

    public void setFunds(int amount) {
        fundsButton.setText(Integer.toString(amount));
        //ToDo: resize/location of fundsButton
    }

    //ToDo: needs to be reworked for new state format
    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();
        if (component.equals(storeButton)) {
            controller.storeRequest();
        } else if (component.equals(libraryButton)) {
            controller.libraryRequest();
        } else if (component.equals(fundsButton)) {
            controller.addFundsRequest();
        } else if (component.equals(accountButton)) {
            //controller.accountAccessRequest();
        } else if (component.equals(searchBar)) {
            //System.out.println(searchBar.getSearchText());
        }
    }
}
