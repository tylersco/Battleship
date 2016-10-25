package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;

import javax.swing.*;
import java.awt.*;

public class GridMediaPanel extends JPanel {

    private MainController controller;

    private int columns = 4;
    private int cellWidth;
    private int cellHeight;
    private int marginWidth;
    private int marginHeight;
    private int count = 0;

    public GridMediaPanel(MainController controller, int cellWidth, int cellHeight, int maginWidth, int marginHeight) {
        this.controller = controller;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.marginWidth = maginWidth;
        this.marginHeight = marginHeight;
        setLayout(null);
        setSize(935, 0);
    }

    public int addMediaListing(MediaListing media) {
        int col = count % columns;
        int row = count / columns;
        media.setSize(cellWidth, cellHeight);
        media.setLocation((col * (cellWidth + marginWidth)), (row * (cellHeight + marginHeight)));
        add(media);
        count++;
        if (col == 0) {
            //System.out.println(Integer.toString(getHeight()));
            setSize(935, getHeight() + cellHeight + marginHeight);
        }
        return 0;
    }


}
