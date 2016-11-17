package com.csci4448.MediaManagementSystem.model.media;

import java.util.ArrayList;
import java.util.HashMap;

public class SystemInventory {

    private static SystemInventory systemInventory = null;

    private ArrayList<Media> mediaList;

    private HashMap<String, ArrayList<String>> typeGenreMap;

    private SystemInventory() {}

    public static synchronized SystemInventory getSystemInventory() {
        if (systemInventory == null)
            systemInventory = new SystemInventory();
        return systemInventory;
    }

    public ArrayList<Media> searchInventory(String searchText){

    }

    public void addMedia(Media media){

    }

    public void deleteMedia(Media media){

    }

    public void addGenre(String genre){

    }

    public void addType(String type, String genre){

    }

}
