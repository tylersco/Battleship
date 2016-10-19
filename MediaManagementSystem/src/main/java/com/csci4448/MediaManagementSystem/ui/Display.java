package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class Display extends JFrame {

    private MainController controller;

    private JLayeredPane mainLayout;
    private JScrollPane scrollView;
    private JPanel scrollLayout;
    private MenuPanel menuPanel;
    private boolean adminEditMode;

    public Display() {
        super("Media");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 650);
        setMinimumSize(new Dimension(950, 425));

        initializeMainLayout();
        add(mainLayout);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeMainLayout() {
        mainLayout = new JLayeredPane();
        mainLayout.setLayout(null);
        mainLayout.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                Dimension newDim = e.getComponent().getBounds().getSize();
                int width = (int) newDim.getWidth();
                int height = (int) newDim.getHeight();
                System.out.println("No");

                scrollView.setSize(width, height-55);
                menuPanel.setSize(width, 55);
                scrollLayout.setPreferredSize(new Dimension(width-15, 2000));
            }
        });

        menuPanel = new MenuPanel();
        menuPanel.setLocation(0, 0);
        mainLayout.add(menuPanel, new Integer(2));

        scrollLayout = new JPanel();
        scrollLayout.setLayout(null);
        scrollLayout.setPreferredSize(new Dimension(950-15, 2000));
        scrollLayout.setBackground(new Color(237, 237, 237));

        scrollView = new JScrollPane(scrollLayout);
        scrollView.setLocation(0, 55);
        scrollView.setSize(950, 700);
        scrollView.setBorder(BorderFactory.createEmptyBorder());
        scrollView.getVerticalScrollBar().setUnitIncrement(10);
        scrollView.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
        //scrollView.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));
        mainLayout.add(scrollView, new Integer(1));

    }

    public static void main(String[] args) {
        Display d = new Display();

    }
}
