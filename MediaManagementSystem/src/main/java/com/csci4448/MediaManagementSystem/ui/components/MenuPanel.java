package com.csci4448.MediaManagementSystem.ui.components;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.design.TextComponentFactory;
import com.csci4448.MediaManagementSystem.ui.design.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuPanel extends JPanel implements ActionListener {

    private MainController controller;

    private JPanel headContainer;
    private TextButton storeButton;
    private TextButton libraryButton;
    private TextButton adminButton;
    private SearchBar searchBar;
    private TextButton fundsButton;
    private TextButton accountButton;

    private JPanel dropDownContainer;
    private ArrayList<BorderedButton> types = new ArrayList<BorderedButton>();


    public MenuPanel(MainController controller) {
        this.controller = controller;
        setLayout(null);

        headContainer = new JPanel();
        headContainer.setSize(950, 55);
        headContainer.setLocation(0, 0);
        headContainer.setLayout(new GridBagLayout());
        initializeMenuComponents();
        headContainer.setBackground(new Color(250, 250, 250));
        headContainer.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(200, 200, 200)));
        add(headContainer);

    }

    private void initializeMenuComponents() {
        storeButton = TextComponentFactory.largeButton(this, "Store", Style.MENU_MAIN);
        GridBagConstraints storeConst = new GridBagConstraints(0, 0, 1, 2, .25, .5, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        headContainer.add(storeButton, storeConst);

        libraryButton = TextComponentFactory.largeButton(this, "Library", Style.MENU_MAIN);
        GridBagConstraints libConst = new GridBagConstraints(1, 0, 1, 2, 1, .5, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,25,0,0), 0, 0);
        headContainer.add(libraryButton, libConst);

        adminButton = TextComponentFactory.largeButton(this, "Admin", Style.MENU_MAIN);
        GridBagConstraints adminConst = new GridBagConstraints(2, 0, 1, 2, 2, .5, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        headContainer.add(adminButton, adminConst);
        if (!controller.isAdmin())
            adminButton.setVisible(false);

        accountButton = TextComponentFactory.smallButton(this, controller.getUser().getUsername(), Style.MENU_SUB);
        GridBagConstraints accConst = new GridBagConstraints(3, 1, 1, 1, .25, .5, GridBagConstraints.LINE_END, GridBagConstraints.NONE, new Insets(0,0,0,20), 0, 0);
        headContainer.add(accountButton, accConst);

        fundsButton = TextComponentFactory.smallButton(this, "$" + controller.getUser().getAccountBalance() + ".00", Style.MENU_SUB);
        GridBagConstraints fundConst = new GridBagConstraints(4, 1, 1, 1, .25, .5, GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        headContainer.add(fundsButton, fundConst);

        searchBar = new SearchBar(this);
        GridBagConstraints searchConst = new GridBagConstraints(3, 0, 2, 1, .5, .5, GridBagConstraints.PAGE_END, GridBagConstraints.NONE, new Insets(0,0,0,0), 0, 0);
        headContainer.add(searchBar, searchConst);
    }

    public void setSize(int width, int height) {
        super.setSize(width, height);
        headContainer.setSize(width, height);
    }

    public TextButton getStoreButton() {
        return storeButton;
    }

    public TextButton getLibraryButton() {
        return libraryButton;
    }

    public TextButton getAdminButton() {
        return adminButton;
    }

    public void removeSearchBar() {
        remove(searchBar);
        //ToDo: account/funds buttons locations
    }

    public void addSearchBar() {
        add(searchBar);
        //ToDo: account/funds buttons locations
    }

    //ToDo: needs to be reworked for new state format
    public void actionPerformed(ActionEvent event) {
        Object component = event.getSource();
        if (component.equals(storeButton)) {
            controller.storeRequest();
        } else if (component.equals(libraryButton)) {
            controller.libraryRequest();
        } else if (component.equals(adminButton)) {
            controller.adminRequest(null);
        } else if (component.equals(fundsButton)) {
            controller.addFundsRequest();
        } else if (component.equals(accountButton)) {
            //controller.accountAccessRequest();
        } else if (component.equals(searchBar)) {
            controller.searchRequest(event.getActionCommand());
        }
    }
}
