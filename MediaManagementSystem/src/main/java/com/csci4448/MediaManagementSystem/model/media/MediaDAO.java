package com.csci4448.MediaManagementSystem.model.media;

import com.csci4448.MediaManagementSystem.model.GenericDAO;

import java.util.List;

public interface MediaDAO extends GenericDAO<Media, Integer> {

    Media getMedia(int mediaID);
    int addMedia(String username, String title, String description, String type, String genre, int price, int sellPrice, int inventoryCount, boolean isRentable);
    int editMedia(String username, int mediaID, String title, String description, String type, String genre, int price, int sellPrice, int inventoryCount, boolean isRentable);
    int deleteMedia(int mediaID, String username);
    List<Media> searchMedia(String searchText);
    int rentMedia(String username, int mediaID);
    int buyMedia(String username, int mediaID);
    int sellMedia(String username, int mediaID);
    int returnMedia(String username, int mediaID);

}
