package com.csci4448.MediaManagementSystem.model.media;

import com.csci4448.MediaManagementSystem.model.review.Review;
import com.csci4448.MediaManagementSystem.model.review.ReviewDAO;
import com.csci4448.MediaManagementSystem.model.user.User;
import com.csci4448.MediaManagementSystem.model.user.UserDAO;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MediaDAO implements MediaInterface {

    private SessionFactory sessionFactory;
    private Media activeMedia;

    public MediaDAO() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        activeMedia = null;
    }

    public int getMediaID() {
        return activeMedia.getMediaID();
    }

    public String getTitle() {
        return activeMedia.getTitle();
    }

    public String getDescription() {
        return activeMedia.getDescription();
    }

    public String getType() {
        return activeMedia.getType();
    }

    public String getImage() {
        return activeMedia.getImage();
    }

    public String getGenre() {
        return activeMedia.getGenre();
    }

    public int getPrice() {
        return activeMedia.getPrice();
    }

    public int getSellPrice() {
        return activeMedia.getSellPrice();
    }

    public int getInventoryCount() {
        return activeMedia.getInventoryCount();
    }

    public boolean getIsRentable() {
        return activeMedia.getIsRentable();
    }

    public Set<ReviewDAO> getReviews() {

        Set<ReviewDAO> reviewDAOs = new HashSet<ReviewDAO>();

        Set<Review> reviews = activeMedia.getReviews();
        Iterator<Review> itr = reviews.iterator();

        while (itr.hasNext()) {
            ReviewDAO reviewDAO = new ReviewDAO();
            reviewDAO.setActiveReview(itr.next().getReviewID());
            reviewDAOs.add(reviewDAO);
        }

        return reviewDAOs;

    }

    public Set<UserDAO> getCurrentUsers() {

        Set<UserDAO> userDAOs = new HashSet<UserDAO>();

        Set<User> currentUsers = activeMedia.getCurrentUsers();
        Iterator<User> itr = currentUsers.iterator();

        while (itr.hasNext()) {
            UserDAO userDAO = new UserDAO();
            userDAO.setActiveUser(itr.next().getUserID());
            userDAOs.add(userDAO);
        }

        return userDAOs;

    }

    public boolean incrementInventoryCount() {
        activeMedia.setInventoryCount(activeMedia.getInventoryCount() + 1);
        return true;
    }

    public boolean decrementInventoryCount() {
        int currentInventoryCount = activeMedia.getInventoryCount();

        if (currentInventoryCount == 0)
            return false;

        activeMedia.setInventoryCount(activeMedia.getInventoryCount() - 1);
        return true;
    }

    private Media getMedia(int mediaID) {
        /*
        Query the Media table for a specific media recor.

        Returns: null if unsuccessful, the specifc Media object if successful
         */

        // Open a DB session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        Media media = null;

        try {
            // Begin a transaction
            transaction = session.beginTransaction();

            media = (Media) session.createQuery("from Media where mediaID = :mediaID").setParameter("mediaID", mediaID).uniqueResult();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        return media;

    }

    public int addMedia(UserDAO user, String title, String description, String type, String genre, int price, int sellPrice, int inventoryCount, boolean isRentable) {
        /*
        Add a media record to the Media table.

        Returns: -2 if user is not an admin, -1 if unsuccessful, the ID of the created media if successful
         */

        if (!user.isAdmin())
            return -2;

        // Open a DB session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        int mediaID = -1;

        try {
            // Begin a transaction
            transaction = session.beginTransaction();

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

            // Save media and commit transaction
            mediaID = (Integer) session.save(media);
            transaction.commit();
            activeMedia = media;
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        return mediaID;

    }

    public int deleteMedia(int mediaID, UserDAO user) {
        /*
        Delete a media record from the Media table.

        Returns -2 if user is not an admin, -1 if unsuccessful, 0 if successful
        */


        if (!user.isAdmin())
            return -2;

        // Open a DB session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            // Begin a transaction
            transaction = session.beginTransaction();

            // Get media object, delete it, commit transaction
            Media media = (Media) session.get(Media.class, mediaID);
            session.delete(media);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
            return -1;
        } finally {
            session.close();
        }

        return 0;

    }

    public int editMedia(UserDAO user, int mediaID, String title, String description, String type, String genre, int price, int sellPrice, int inventoryCount, boolean isRentable) {
        /*
        Edit a media record in the Media table.

        Returns -2 if user is not an admin, -1 if unsuccessful, 0 if successful
         */

        if (!user.isAdmin())
            return -2;

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Media media = (Media) session.get(Media.class, mediaID);

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

        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
            return -1;
        } finally {
            session.close();
        }

        return 0;

    }

    public boolean activeMediaSet() {
        return activeMedia != null;
    }

    public void setActiveMedia(int mediaID) {
        activeMedia = getMedia(mediaID);
    }

    //ToDo: Waitlist of rentable media

}
