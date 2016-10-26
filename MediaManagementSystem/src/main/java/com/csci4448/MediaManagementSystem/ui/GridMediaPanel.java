package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.components.MediaListing;

import javax.swing.*;
import java.awt.*;

public class GridMediaPanel extends MainContentPanel {

    private MainController controller;

    private JPanel view;

    private int columns = 4;
    private int cellWidth;
    private int cellHeight;
    private int marginWidth;
    private int marginHeight;
    private int count = 0;

    public GridMediaPanel(MainController controller, int cellWidth, int cellHeight, int maginWidth, int marginHeight) {
        super(controller);
        this.controller = controller;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.marginWidth = maginWidth;
        this.marginHeight = marginHeight;
        view = new JPanel();
        view.setLayout(null);
        view.setSize(935, 0);
        view.setLocation(10, 10);

        setContent(view);
    }

    public int addMediaListing(MediaListing media) {
        int col = count % columns;
        int row = count / columns;
        media.setSize(cellWidth, cellHeight);
        media.setLocation((col * (cellWidth + marginWidth)), (row * (cellHeight + marginHeight)));
        view.add(media);
        count++;
        if (col == 0) {
            //System.out.println(Integer.toString(getHeight()));
            setSize(935, getHeight() + cellHeight + marginHeight);
        }
        return 0;
    }




}
