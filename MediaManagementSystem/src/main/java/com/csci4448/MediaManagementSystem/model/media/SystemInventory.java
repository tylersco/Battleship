package com.csci4448.MediaManagementSystem.model.media;

import java.util.*;

public class SystemInventory {

    private static SystemInventory systemInventory = null;

    private Map<String, ArrayList<String>> typeGenreMap;

    private SystemInventory() {

        // This is hard-coded for now. Not the best practice.
        typeGenreMap = new HashMap<String, ArrayList<String>>();

        ArrayList<String> bookGenres = new ArrayList<String>();
        bookGenres.add("Biography");
        bookGenres.add("Business");
        bookGenres.add("Fiction");

        ArrayList<String> movieGenres = new ArrayList<String>();
        movieGenres.add("Drama");
        movieGenres.add("Crime");
        movieGenres.add("Action");
        movieGenres.add("Thriller");
        movieGenres.add("Comedy");

        typeGenreMap.put("Movie", movieGenres);
        typeGenreMap.put("Book", bookGenres);

    }

    public static synchronized SystemInventory getSystemInventory() {
        if (systemInventory == null)
            systemInventory = new SystemInventory();
        return systemInventory;
    }

    public List<Media> getAllMedia() {
        MediaDAOImpl mediaDAO = new MediaDAOImpl();
        List<Media> mediaRecords = mediaDAO.getAll();

        if(mediaRecords == null){
            return Collections.emptyList();
        }

        return mediaRecords;
    }

    public List<Media> searchInventory(String searchText) {

        MediaDAO mediaDAO = new MediaDAOImpl();
        List<Media> mediaList = mediaDAO.searchMedia(searchText);
        if (mediaList == null)
            return Collections.emptyList();
        return mediaList;

    }

    public int rentMedia(String username, int mediaID) {
        /*
        User can rent a specific media record.

        Returns 0 if successful,
        -1 if user doesn't have enough money,
        -2 if inventory count is 0,
        -3 if media is not rentable,
        -4 if system error
         */

        MediaDAO mediaDAO = new MediaDAOImpl();
        return mediaDAO.rentMedia(username, mediaID);

    }

    public int buyMedia(String username, int mediaID){
        /*
        User can purchase media

         -1 if user doesn't have enough money
         -2 if there is no media in inventory
         -3 if there is a system error
         0 if success
          */

        int returnVal;
        MediaDAO mediaDAO = new MediaDAOImpl();

        returnVal = mediaDAO.buyMedia(username, mediaID);

        return returnVal;

    }

    public int sellMedia(String username, int mediaID) {
        /*
        User can sell back a bought media record.

        Returns 0 if successful,
        -1 if user hasn't purchased the media,
        -2 if the media is not buyable,
        -3 if system error
         */

        MediaDAO mediaDAO = new MediaDAOImpl();
        return mediaDAO.sellMedia(username, mediaID);
    }

    public int returnMedia(String username, int mediaID) {
        /*
        User can return a rented media record.

        Returns 0 if successful,
        -1 if the user hasn't rented the media,
        -2 if the media is not rentable,
        -3 if system error
         */

        MediaDAO mediaDAO = new MediaDAOImpl();
        return mediaDAO.returnMedia(username, mediaID);
    }

    public void addType(String type) {
        if (type != null && !type.equals("") && !typeGenreMap.containsKey(type))
            typeGenreMap.put(type.substring(0, 1).toUpperCase() + type.substring(1), new ArrayList<String>());
    }

    public void addGenre(String type, String genre) {
        if (type != null && genre != null && !genre.equals("") && typeGenreMap.containsKey(type)) {
            ArrayList<String> values = typeGenreMap.get(type);
            if (values != null && !values.contains(genre)) {
                values.add(genre.substring(0, 1).toUpperCase() + genre.substring(1));
                typeGenreMap.put(type, values);
            }
        }
    }

    public boolean isValidType(String type) {
        return typeGenreMap.containsKey(type);
    }

    public boolean isValidGenre(String type, String genre) {
        ArrayList<String> values = typeGenreMap.get(type);
        return values != null && values.contains(genre);
    }

}
