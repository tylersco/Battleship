package com.csci4448.MediaManagementSystem.model.media;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SystemInventory {

    private static SystemInventory systemInventory = null;

    private HashMap<String, ArrayList<String>> typeGenreMap;

    private SystemInventory() {}

    public static synchronized SystemInventory getSystemInventory() {
        if (systemInventory == null)
            systemInventory = new SystemInventory();
        return systemInventory;
    }

    public List<Media> getAllMedia(){
        MediaDAOImpl mediaDAO = new MediaDAOImpl();
        List<Media> mediaRecords = mediaDAO.getAll();

        if(mediaRecords == null){
            return Collections.emptyList();
        }

        return mediaRecords;
    }

    public Media searchInventory(String searchText) {

        // ToDo: Implement this using new model structure
        return null;

    }

    public void addGenre(String genre){
        // ToDo: Implement this
    }

    public void addType(String type, String genre){
        // ToDo: Implement this
    }

    public boolean isValidType(String type) {
        // ToDo: Implement this
        return true;
    }

    public boolean isValidGenre(String type, String genre)   {
        // ToDo: Implement this
        return true;
    }

}
