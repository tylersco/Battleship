package com.csci4448.MediaManagementSystem.model.review;

import com.csci4448.MediaManagementSystem.model.media.Media;
import com.csci4448.MediaManagementSystem.model.media.MediaDAO;
import com.csci4448.MediaManagementSystem.model.user.User;
import com.csci4448.MediaManagementSystem.model.user.UserDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReviewDAO implements ReviewInterface {

    private SessionFactory sessionFactory;
    private Review activeReview;

    public ReviewDAO() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        activeReview = null;
    }

    public int getReviewID() {
        return activeReview.getReviewID();
    }

    public int getRatingValue() {
        return activeReview.getRatingValue();
    }

    public String getReviewText() {
        return activeReview.getReviewText();
    }

    public UserDAO getUser() {
        User user = activeReview.getUser();
        UserDAO userDAO = new UserDAO();
        userDAO.setActiveUser(user.getUserID());
        return userDAO;
    }

    public MediaDAO getMedia() {
        Media media = activeReview.getMedia();
        MediaDAO mediaDAO = new MediaDAO();
        mediaDAO.setActiveMedia(media.getMediaID());
        return mediaDAO;
    }

    public int addReview(String textReview, int rating, int userID, int mediaID) {
        /*
        Add review for a given media, as a specific user.

        Returns: -1 if unsuccessful and the ID of the new review if successful
         */

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        User user = null;
        Media media = null;
        int reviewID = -1;

        try {

            transaction = session.beginTransaction();

            user = (User) session.createQuery("from User where userID = :userID").setParameter("userID", userID).uniqueResult();

            if (user == null)
                return reviewID;

            media = (Media) session.createQuery("from Media where mediaID = :mediaID").setParameter("mediaID", mediaID).uniqueResult();

            if (media == null)
                return reviewID;

            Review review = new Review();

            if (rating < 1 || rating > 5)
                return reviewID;

            review.setRatingValue(rating);

            if (textReview == null || textReview.equals(""))
                return reviewID;

            review.setReviewText(textReview);
            review.setUser(user);
            review.setMedia(media);

            // Save review and commit transaction
            reviewID = (Integer) session.save(user);
            transaction.commit();

        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
        }
        finally {
            session.close();
        }

        return reviewID;

    }

    private Review getReview(int reviewID) {
        /*
        Query the User table for a specific review.

        Returns: null if unsuccessful, the specifc Review object if successful
         */

        // Open a DB session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        Review review = null;

        try {
            // Begin a transaction
            transaction = session.beginTransaction();

            review = (Review) session.createQuery("from Review where reviewID = :reviewID").setParameter("reviewID", reviewID).uniqueResult();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        return review;

    }

    public boolean activeReviewSet() {
        return activeReview != null;
    }

    public void setActiveReview(int reviewID) {

        activeReview = getReview(reviewID);

    }

}


