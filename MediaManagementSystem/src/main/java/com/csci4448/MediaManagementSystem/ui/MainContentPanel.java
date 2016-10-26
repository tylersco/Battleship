package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.components.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public abstract class MainContentPanel extends JLayeredPane implements DisplayState {

    private JScrollPane scrollView;
    private JPanel scrollLayout;
    private MenuPanel menuPanel;
    private JPanel content;

    public MainContentPanel(MainController controller) {
        setLayout(null);
        addComponentListener(new ComponentAdapter() {
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
        add(menuPanel, new Integer(2));

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
        add(scrollView, new Integer(1));

    }

    public void setContent(JPanel content) {
        this.content = content;
        int height = content.getHeight();
        scrollLayout.setPreferredSize(new Dimension(935,height));
        scrollLayout.add(content);
    }

    public void onActivate(MainController controller, Display display) {
        display.setSize(950, 650);
        display.setMinimumSize(new Dimension(950, 425));
        display.setResizable(true);
        display.setLocationRelativeTo(null);

        display.add(this);
        display.setVisible(true);
    }

    public void update(Update update) {

    }

    public void onDeactivate(MainController controller, Display display) {
        display.remove(this);
    }



}
