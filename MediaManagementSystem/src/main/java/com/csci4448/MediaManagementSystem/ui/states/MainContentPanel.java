package com.csci4448.MediaManagementSystem.ui.states;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.components.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public abstract class MainContentPanel extends JLayeredPane implements DisplayState, ActionListener {

    private MainController controller;

    private JScrollPane scrollView;
    private JPanel scrollLayout;
    private MenuPanel menuPanel;
    private JLayeredPane content;
    private JComponent popUpWindow = null;

    public MainContentPanel(MainController controller) {
        this.controller = controller;
        setLayout(null);
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension newDim = e.getComponent().getBounds().getSize();
                int width = (int) newDim.getWidth();
                int height = (int) newDim.getHeight();

                scrollView.setSize(width, height-55);
                scrollLayout.setPreferredSize(new Dimension(width - 15, (int)scrollLayout.getPreferredSize().getHeight()));
                if (content != null) {
                    content.setLocation(((width - 15) - content.getWidth())/2 + 15, 10);
                }
                menuPanel.setSize(width, 55);
            }
        });

        //ToDo: apply user info to menu components
        menuPanel = new MenuPanel(controller);
        menuPanel.setLocation(0, 0);
        add(menuPanel, new Integer(2));

        scrollLayout = new JPanel();
        scrollLayout.setLayout(null);
        scrollLayout.setBackground(new Color(237, 237, 237));

        scrollView = new JScrollPane(scrollLayout);
        scrollView.setLocation(0, 55);
        scrollView.setSize(950, 700);
        scrollView.setBorder(BorderFactory.createEmptyBorder());
        scrollView.getVerticalScrollBar().setUnitIncrement(10);
        scrollView.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
        //scrollView.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        add(scrollView, new Integer(1));

        content = new JLayeredPane();
        content.setLayout(null);
        content.setSize(935, 0);
        content.setLocation(15, 10);
        content.setBackground(null);
        scrollLayout.add(content);
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public JLayeredPane getContent() {
        return content;
    }

    public void updateContentSize() {
        int height = content.getHeight();
        scrollLayout.setPreferredSize(new Dimension(935,height));
    }

    public void setPopUpWindow(JComponent popUpWindow) {
        if (this.popUpWindow == null) {
            this.popUpWindow = popUpWindow;
            Dimension windowSize = popUpWindow.getPreferredSize();
            popUpWindow.setSize(windowSize);
            popUpWindow.setLocation((getWidth() - (int)windowSize.getWidth())/2, 150);
            add(this.popUpWindow, new Integer(3));
        }
    }

    public void removePopUpWindow() {
        if (popUpWindow != null) {
            remove(popUpWindow);
            popUpWindow = null;
            validate();
            repaint();
        }
    }

    public void onActivate(MainController controller, Display display) {
        display.setSize(950, 650);
        display.setMinimumSize(new Dimension(950, 425));
        display.setResizable(true);
        display.setLocationRelativeTo(null);

        display.add(this);
        display.setVisible(true);
    }

    public void onDeactivate(MainController controller, Display display) {
        display.remove(this);
    }


    protected MainController getController() { return controller; }

    public void actionPerformed(ActionEvent event) {

    }
}
