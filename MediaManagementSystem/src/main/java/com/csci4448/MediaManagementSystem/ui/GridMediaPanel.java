package com.csci4448.MediaManagementSystem.ui;

import com.csci4448.MediaManagementSystem.controller.MainController;
import com.csci4448.MediaManagementSystem.ui.components.MediaListing;

import javax.swing.*;
import java.awt.*;

public class GridMediaPanel extends MainContentPanel {

    private int columns = 4;
    private int cellWidth;
    private int cellHeight;
    private int marginWidth;
    private int marginHeight;
    private int count = 0;

    public GridMediaPanel(MainController controller, int cellWidth, int cellHeight, int marginWidth, int marginHeight) {
        super(controller);
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        this.marginWidth = marginWidth;
        this.marginHeight = marginHeight;
    }

    //ToDo: take in a single arraylist of media items in the constructor instead of the controller calling this method
    public void add(MediaListing media) {
        int col = count % columns;
        int row = count / columns;
        media.setSize(cellWidth, cellHeight);
        media.setLocation((col * (cellWidth + marginWidth)), (row * (cellHeight + marginHeight)));
        JLayeredPane content = getContent();
        content.add(media);
        count++;
        if (col == 0) {
            content.setSize(935, content.getHeight() + cellHeight + marginHeight);
        }
        updateContentSize();

    }




}
