package com.csci4448.MediaManagementSystem.model.media;

public interface MediaInterface {

    int getMediaID();
    String getTitle();
    String getDescription();
    String getType();
    String getImage();
    String getGenre();
    int getPrice();
    int getSellPrice();
    int getInventoryCount();
    boolean getIsRentable();

}
