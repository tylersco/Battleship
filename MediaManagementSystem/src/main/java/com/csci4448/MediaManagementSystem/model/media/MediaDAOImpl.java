package com.csci4448.MediaManagementSystem.model.media;

import com.csci4448.MediaManagementSystem.model.GenericDAOImpl;
import com.csci4448.MediaManagementSystem.model.user.User;
import com.csci4448.MediaManagementSystem.model.user.UserDAO;
import com.csci4448.MediaManagementSystem.model.user.UserDAOImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.util.List;

public class MediaDAOImpl
        extends GenericDAOImpl<Media, Integer>
        implements MediaDAO {

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

        /* Valid types:
            Movie: Drama, Crime, Action, Thriller, Comedy
            Book: Biography, Business, Fiction
         */
        if (type == null || type.equals("") || !SystemInventory.getSystemInventory().isValidType(type))
            return mediaID;

        if (genre == null || genre.equals("") || !SystemInventory.getSystemInventory().isValidGenre(type, genre))
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

        if (type == null || type.equals("") || !SystemInventory.getSystemInventory().isValidType(type))
            return -1;

        if (genre == null || genre.equals("") || !SystemInventory.getSystemInventory().isValidGenre(type, genre))
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
        User adminUser = userDAO.getUser(username);

        if (adminUser == null || !adminUser.getIsAdmin())
            return -2;

        Media media = retrieve(mediaID);
        List<User> allUsers = userDAO.getAll();

        // ToDo: Fix the database integrity problem here
        for (User user: allUsers)
            user.removePersonalMedia(media);

        if (media == null)
            return -1;

        if (delete(media))
            return 0;
        return -1;

    }

    @SuppressWarnings("unchecked")
    public List<Media> searchMedia(String searchText) {

        // Open a DB session
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;

        List<Media> mediaList = null;

        try {
            // Begin a transaction
            transaction = session.beginTransaction();

            mediaList = session.createQuery("from Media where title like :title").setParameter("title", "%" + searchText + "%").list();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        return mediaList;
    }

    public int rentMedia(String username, int mediaID) {
        /*
        User can rent a specific media record.

        Returns 0 if successful,
        -1 if user doesn't have enough money,
        -2 if inventory count is 0,
        -3 if system error
         */

        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUser(username);
        Media media = getMedia(mediaID);

        // System error
        if (user == null || media == null)
            return -4;

        if (!media.getIsRentable())
            return -3;

        // ToDo: Possibly implement waitlist, if we get to it
        if (media.getInventoryCount() <= 0)
            return -2;

        // User does not have enough money
        if (user.getAccountBalance() < media.getPrice())
            return -1;

        media.setInventoryCount(media.getInventoryCount() - 1);
        user.setAccountBalance(user.getAccountBalance() - media.getPrice());
        user.addPersonalMedia(media);
        media.addUserOwner(user);

        update(media);
        userDAO.update(user);

        return 0;

    }

    public int buyMedia(String username, int mediaID){
        /*
        User can buy a specific media record.

        Returns 0 if successful,
        -1 if user doesn't have enough money,
        -2 if inventory count is 0,
        -3 if system error
         */

        UserDAO user = new UserDAOImpl();
        Media media = getMedia(mediaID);
        User userAccount = user.getUser(username);

        // return -3 for system error
        if(userAccount == null || media == null){
            return -3;
        }

        // return -1 if user doesn't have enough money
        if(userAccount.getAccountBalance() < media.getPrice()){
            return -1;
        }

        // return -2 if inventory doesn't have enough media objects
        if(media.getInventoryCount() < 1){
            return -2;
        }

        // subtract the price of media from user's account
        userAccount.setAccountBalance(userAccount.getAccountBalance() - media.getPrice());

        // add the media to the user's account
        userAccount.addPersonalMedia(media);

        // subtract 1 media item from system inventory
        media.setInventoryCount(media.getInventoryCount()-1);

        // add user to the list of current users of media
        media.addUserOwner(userAccount);


        update(media);
        user.update(userAccount);


        return 0;
    }

    public int sellMedia(String username, int mediaID) {
        /*
        User can sell back a bought media record.

        Returns 0 if successful,
        -1 if user hasn't purchased the media,
        -2 if the media is not buyable,
        -3 if system error
         */

        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUser(username);
        Media media = getMedia(mediaID);

        if (user == null || media == null)
            return -3;

        if (media.getIsRentable())
            return -2;

        boolean owned = false;

        for (Media ownedMedia : user.getPersonalInventory()) {
            if (ownedMedia.getMediaID() == media.getMediaID()) {
                owned = true;
                break;
            }
        }

        if (!owned)
            return -1;

        for (Media ownedMedia : user.getPersonalInventory()) {
            if (ownedMedia.getMediaID() == media.getMediaID()) {
                user.removePersonalMedia(ownedMedia);
                break;
            }
        }

        for (User currentUser : media.getCurrentUsers()) {
            if (currentUser.getUsername().equals(user.getUsername())) {
                media.removeCurrentUser(currentUser);
                break;
            }
        }

        media.setInventoryCount(media.getInventoryCount() + 1);
        user.setAccountBalance(user.getAccountBalance() + media.getSellPrice());

        update(media);
        userDAO.update(user);

        return 0;
    }

    public int returnMedia(String username, int mediaID) {
        /*
        User can return a rented media record.

        Returns 0 if successful,
        -1 if the user hasn't rented the media,
        -2 if the media is not rentable,
        -3 if system error
         */

        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.getUser(username);
        Media media = getMedia(mediaID);

        if (user == null || media == null)
            return -3;

        if (!media.getIsRentable())
            return -2;

        boolean owned = false;

        for (Media ownedMedia : user.getPersonalInventory()) {
            if (ownedMedia.getMediaID() == media.getMediaID()) {
                owned = true;
                break;
            }
        }

        if (!owned)
            return -1;

        for (Media ownedMedia : user.getPersonalInventory()) {
            if (ownedMedia.getMediaID() == media.getMediaID()) {
                user.removePersonalMedia(ownedMedia);
                break;
            }
        }

        for (User currentUser : media.getCurrentUsers()) {
            if (currentUser.getUsername().equals(user.getUsername())) {
                media.removeCurrentUser(currentUser);
                break;
            }
        }

        media.setInventoryCount(media.getInventoryCount() + 1);

        update(media);
        userDAO.update(user);

        return 0;
    }

    // ToDo: Possibly implement waitlist functionality if we have time

}

