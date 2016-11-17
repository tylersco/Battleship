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

    public Media searchInventory(String searchText){
        for(Media media : mediaList){
            if (media.getTitle().equals(searchText)){
                return media;
            }
        }
        return null;
    }

    public void addMedia(Media media){
        mediaList.add(media);
    }

    public void deleteMedia(Media media){
        for(Media m: mediaList){
            if(m.getTitle().equals(media.getTitle())){
                mediaList.remove(media);
            }
        }
    }

    public void addGenre(String genre){

    }

    public void addType(String type, String genre){

    }

}
