package com.csci4448.MediaManagementSystem.model.review;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class ReviewDAO {

    private SessionFactory sessionFactory;

    public ReviewDAO(){
        sessionFactory = new Configuration().configure().buildSessionFactory()

    }

    // Todo: add param for foreign key, attaching to media object
    public void addReview(String textReview, int rating, String username){

        Session session = sessionFactory.openSession();
        Transaction transaction = null;


        try {

            transaction = session.beginTransaction();

            Review review = new Review();

            if (rating >= 1 && rating <= 5) {
                review.setRatingValue(rating);
            }

            if(textReview != null && !textReview.equals("")) {
                review.setReviewText(textReview);
            }


            transaction.commit();


        }

        catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        finally {
            session.close()
        }

    }


    }

}
