package com.csci4448.MediaManagementSystem.model.media;

import com.csci4448.MediaManagementSystem.model.review.Review;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Set;

public class MediaDAO implements MediaInterface {

    private SessionFactory sessionFactory;
    private Media activeMedia;

    public MediaDAO() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        activeMedia = null;
    }


    public Media getActiveMedia() { return activeMedia; }
    public void setActiveMedia(Media media) { activeMedia = media; }


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

    public Set<Review> getReviews() {
        return activeMedia.getReviews();
    }


    public void saveMediaChangesToDatabase() {
        // TODO: Literally the implementation of saving `activeMedia` to the database
        System.out.println("MEDIADAO: Saved the media '" + getTitle() + "' to the database.");
    }

    //ToDo: Basic add/edit/delete of media, and waitlist of rentable media

}
