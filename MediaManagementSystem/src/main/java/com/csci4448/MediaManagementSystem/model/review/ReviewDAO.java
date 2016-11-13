package com.csci4448.MediaManagementSystem.model.review;

import com.csci4448.MediaManagementSystem.model.user.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReviewDAO {

    private SessionFactory sessionFactory;

    public ReviewDAO() {

        sessionFactory = new Configuration().configure().buildSessionFactory();

    }

    // ToDo: Add connection to the specific media that is being reviewed
    public int addReview(String textReview, int rating, String username) {
        /*
        Add review for a given media, as a specific user.

        Returns: -1 if unsuccessful and the ID of the new review if successful
         */

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        User user = null;
        int reviewID = -1;

        try {

            transaction = session.beginTransaction();

            user = (User) session.createQuery("from User where username = :username").setParameter("username", username).uniqueResult();

            if (user == null)
                return reviewID;

            Review review = new Review();

            if (rating < 1 || rating > 5)
                return reviewID;

            review.setRatingValue(rating);

            if (textReview == null || textReview.equals(""))
                return reviewID;

            review.setReviewText(textReview);
            review.setUser(user);

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

}


