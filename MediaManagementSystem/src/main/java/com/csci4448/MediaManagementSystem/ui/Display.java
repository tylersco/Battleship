package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.*;
import com.csci4448.MediaManagementSystem.model.*;
import com.csci4448.MediaManagementSystem.state.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.*;

public class Display extends JFrame {

    private MainController controller;

    private JLayeredPane mainLayout;
    private JScrollPane scrollView;
    private JPanel scrollLayout;
    private MenuPanel menuPanel;

    private DisplayState activeState;
    private boolean isMainLayoutValid;

    public Display(MainController controller) {
        super("Media");
        this.controller = controller;
        this.activeState = null;
        this.isMainLayoutValid = false;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setState(DisplayState state) {
        if (activeState != null)
            activeState.onDeactivate(controller, this);

        activeState = state;
        if (activeState != null)
            activeState.onActivate(controller, this);
    }

    // Adds a Component to the scrollLayout, assuming that it is currently the main layout
    // If it is not currently in the main layout, it will add the component, but will not show the change.
    public void addMainWindowContent(Component component) {
        scrollLayout.add(component);
    }

    // Removes a Component from the scrollLayout, assuming that it is currently the main layout
    // If it is not currently in the main layout, it will remove the component, but will not show the change.
    public void removeMainWindowContent(Component component) {
        scrollLayout.remove(component);
    }

    // Called by the states when they change the overall layout of the window from what is considered the "main layout".
    // This relies on the states to call this when needed, which opens it up to bugs, but should work for now.
    public void invalidateMainLayout() {
        if (isMainLayoutValid)
            remove(mainLayout);

        isMainLayoutValid = false;
    }

    public void ensureMainLayout() {
        if (isMainLayoutValid)
            return;

        setSize(950, 650);
        setMinimumSize(new Dimension(950, 425));
        setResizable(true);
        setLocationRelativeTo(null);

        mainLayout = new JLayeredPane();
        mainLayout.setLayout(null);
        mainLayout.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension newDim = e.getComponent().getBounds().getSize();
                int width = (int) newDim.getWidth();
                int height = (int) newDim.getHeight();

                scrollView.setSize(width, height-55);
                menuPanel.setSize(width, 55);
                menuPanel.resizeMenu(width, 55);
            }
        });

        //ToDo: apply user info to menu components
        menuPanel = new MenuPanel(controller);
        menuPanel.setLocation(0, 0);
        mainLayout.add(menuPanel, new Integer(2));

        scrollLayout = new JPanel();
        scrollLayout.setLayout(null);
        scrollLayout.setBackground(new Color(237, 237, 237));

        System.out.println(Integer.toString((int)scrollLayout.getPreferredSize().getHeight()));
        scrollView = new JScrollPane(scrollLayout);
        scrollView.setLocation(0, 55);
        scrollView.setSize(950, 700);
        scrollView.setBorder(BorderFactory.createEmptyBorder());
        scrollView.getVerticalScrollBar().setUnitIncrement(10);
        scrollView.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
        //scrollView.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        mainLayout.add(scrollView, new Integer(1));

        add(mainLayout);
        setVisible(true);
        isMainLayoutValid = true;
    }

    //public void displayStore(/*ArrayList<Media>*/) {
    //    GridMediaPanel g = new GridMediaPanel(controller, 215, 250, 15, 35);
    //    scrollLayout.add(g);
    //    g.setLocation(15, 10);
    //    for(int i = 0; i < 100; i++) {
    //        g.addMediaListing(new MediaListing());
    //    }
    //    scrollLayout.setPreferredSize(new Dimension(935, g.getHeight()));
    //    //scrollLayout.invalidate();
    //    //scrollView.repaint();
    //
    //}

    //public void displayLibrary(/*ArrayList<Media>*/) {
    //
    //}
}
