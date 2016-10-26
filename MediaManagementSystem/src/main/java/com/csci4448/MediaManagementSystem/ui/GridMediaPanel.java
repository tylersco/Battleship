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

    public GridMediaPanel(MainController controller, int cellWidth, int cellHeight, int marginWidth, int marginHeight) {
        super(controller);
        this.controller = controller;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.marginWidth = marginWidth;
        this.marginHeight = marginHeight;
        view = getContent();
    }

    public void addMediaListing(MediaListing media) {
        int col = count % columns;
        int row = count / columns;
        media.setSize(cellWidth, cellHeight);
        media.setLocation((col * (cellWidth + marginWidth)), (row * (cellHeight + marginHeight)));
        view.add(media);
        count++;
        if (col == 0) {
            view.setSize(935, view.getHeight() + cellHeight + marginHeight);
        }
        updateContentSize();

    }




}
