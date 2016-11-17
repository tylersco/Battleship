package com.csci4448.MediaManagementSystem.model.media;

import com.csci4448.MediaManagementSystem.model.GenericDAOImpl;
import com.csci4448.MediaManagementSystem.model.user.User;
import com.csci4448.MediaManagementSystem.model.user.UserDAO;
import com.csci4448.MediaManagementSystem.model.user.UserDAOImpl;

import java.io.File;

public class MediaDAOImpl
        extends GenericDAOImpl<Media, Integer>
        implements MediaDAO {

    public boolean incrementInventoryCount(int mediaID) {
        /*
        Increment the inventory count of a media.

        Returns false if unsuccessful, true if successful
         */

        Media media = getMedia(mediaID);
        media.setInventoryCount(media.getInventoryCount() + 1);
        return update(media);

    }

    public int decrementInventoryCount(int mediaID) {
        /*
        Decrement the inventory count of a media.

        Returns -2 if inventory count is 0, -1 if unsuccessful, 0 if successful
         */

        Media media = getMedia(mediaID);

        int currentInventoryCount = media.getInventoryCount();

        if (currentInventoryCount == 0)
            return -2;

        media.setInventoryCount(media.getInventoryCount() + 1);
        if (update(media))
            return 0;
        return -1;
    }

    public Media getMedia(int mediaID) {
        /*
        Query the Media table for a specific media.

        Returns: null if unsuccessful, the specifc Media object if successful
         */

        return retrieve(mediaID);

    }

    public int addMedia(String username, String title, String description, String type, String genre, int price, int sellPrice, int inventoryCount, boolean isRentable) {
        /*
        Add a media record to the Media table.

        Returns: -2 if user is not an admin, -1 if unsuccessful, the ID of the created media if successful
         */

        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUser(username);

        if (user == null || !user.getIsAdmin())
            return -2;

        Integer mediaID = -1;

        // Create new media
        Media media = new Media();

        if (title == null || title.equals(""))
            return mediaID;

        // ToDo: Ensure that type and genre are valid

        if (type == null || type.equals(""))
            return mediaID;

        if (genre == null || genre.equals(""))
            return mediaID;

        if (price < 0)
            return mediaID;

        if (inventoryCount < 0)
            return mediaID;

        String imgPath = "../../../../../resources/" + type.toLowerCase() + "/" + title.toLowerCase().replaceAll("\\s","") + ".jpg";

        File img = new File(imgPath);
        if (!img.exists()) {
            imgPath = "../../../../../resources/test.png";
        }

        media.setTitle(title);

        if (description != null && !description.equals(""))
            media.setDescription(description);

        media.setType(type);
        media.setImage(imgPath);
        media.setGenre(genre);
        media.setPrice(price);

        if (!isRentable)
            media.setSellPrice(sellPrice);
        else
            media.setSellPrice(-1);

        media.setInventoryCount(inventoryCount);
        media.setIsRentable(isRentable);

        mediaID = create(media);

        if (mediaID == null)
            return -1;
        return mediaID;

    }

    public int editMedia(String username, int mediaID, String title, String description, String type, String genre, int price, int sellPrice, int inventoryCount, boolean isRentable) {
        /*
        Edit a media record in the Media table.

        Returns -2 if user is not an admin, -1 if unsuccessful, 0 if successful
         */

        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUser(username);

        if (user == null || !user.getIsAdmin())
            return -2;

        Media media = retrieve(mediaID);

        if (media == null)
            return -1;

        if (title == null || title.equals(""))
            return -1;

        // ToDo: Ensure that type and genre are valid

        if (type == null || type.equals(""))
            return -1;

        if (genre == null || genre.equals(""))
            return -1;

        if (price < 0)
            return -1;

        if (inventoryCount < 0)
            return -1;

        String imgPath = "../../../../../resources/" + type.toLowerCase() + "/" + title.toLowerCase().replaceAll("\\s","") + ".jpg";

        File img = new File(imgPath);
        if (!img.exists()) {
            imgPath = "../../../../../resources/test.png";
        }

        media.setTitle(title);
        media.setDescription(description);
        media.setType(type);
        media.setImage(imgPath);
        media.setGenre(genre);
        media.setPrice(price);
        media.setIsRentable(isRentable);

        if (!isRentable)
            media.setSellPrice(sellPrice);
        else
            media.setSellPrice(-1);

        media.setInventoryCount(inventoryCount);

        if (update(media))
            return 0;
        return -1;

    }

    public int deleteMedia(int mediaID, String username) {
        /*
        Delete a media record from the Media table.

        Returns -2 if user is not an admin, -1 if unsuccessful, 0 if successful
        */

        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUser(username);

        if (user == null || !user.getIsAdmin())
            return -2;

        Media media = retrieve(mediaID);

        if (media == null)
            return -1;

        if (delete(media))
            return 0;
        return -1;

    }

    // ToDo: Possibly implement waitlist functionality if we have time

}

