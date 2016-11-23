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

    private Font defaultFont = new Font("Helvetice Neue", Font.PLAIN, 14);
    private Color darkColor = new Color(75, 75, 75);
    private Color lightColor = new Color(75, 75, 75, 140);

    public MenuPanel(MainController controller) {
        this.controller = controller;
        setSize(950, 55);
        setLayout(new GridBagLayout());
        initializeMenuComponents();
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
    }

    private void initializeMenuComponents() {
        storeButton = TextComponentFactory.menuMainButton(this, "Store", Style.MENU_MAIN);
        GridBagConstraints storeConst = new GridBagConstraints(0, 0, 1, 2, .25, .5, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        add(storeButton, storeConst);

        libraryButton = TextComponentFactory.menuMainButton(this, "Library", Style.MENU_MAIN);
        GridBagConstraints libConst = new GridBagConstraints(1, 0, 1, 2, 1, .5, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,25,0,0), 0, 0);
        add(libraryButton, libConst);

        accountButton = TextComponentFactory.smallButton(this, "Account", Style.MENU_SUB);
        GridBagConstraints accConst = new GridBagConstraints(3, 1, 1, 1, .25, .5, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0,0,0,20), 0, 0);
        add(accountButton, accConst);

        fundsButton = TextComponentFactory.smallButton(this, "$0.00", Style.MENU_SUB);
        GridBagConstraints fundConst = new GridBagConstraints(4, 1, 1, 1, .25, .5, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        add(fundsButton, fundConst);

        searchBar = new SearchBar(this);
        GridBagConstraints searchConst = new GridBagConstraints(3, 0, 2, 1, .5, .5, GridBagConstraints.PAGE_END, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        add(searchBar, searchConst);
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
