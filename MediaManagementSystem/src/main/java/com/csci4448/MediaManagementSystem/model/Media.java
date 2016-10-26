package com.csci4448.MediaManagementSystem.model;

import java.util.ArrayList;

public abstract class Media {

    private String title;
    private String description;
    private String type;
    private String image;
    private ArrayList<String> reviews; // TODO: Use the Review class, like the diagram wants us to
    private String genre;

    public Media() {

    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public String getImage() { return image; }
    public ArrayList<String> getReviews() { return reviews; }
    public String getGenre() { return genre; }
}
