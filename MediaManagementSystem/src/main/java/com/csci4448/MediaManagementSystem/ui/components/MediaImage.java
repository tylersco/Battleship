package com.csci4448.MediaManagementSystem.ui.components;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MediaImage extends JLabel {

    private String imagePath;

    public MediaImage(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setImagePath(String newpath) {
        imagePath = newpath;
    }
    public String getImagePath() { return imagePath; }

    public void loadMediaImage(int width, int height) {
        

        setIcon(new ImageIcon(retrieveMediaImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
        setSize(width, height);
    }

    private BufferedImage retrieveMediaImage() {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(imagePath));
            return image;
        } catch (IOException e) {
            System.out.println("Error loading " + imagePath + " : " +  e.toString());
        }
        return null;
    }


}
