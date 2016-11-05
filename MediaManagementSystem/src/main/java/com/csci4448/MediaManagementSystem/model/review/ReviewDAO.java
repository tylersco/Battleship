package com.csci4448.MediaManagementSystem.model.review;

import com.csci4448.MediaManagementSystem.model.user.User;
import com.csci4448.MediaManagementSystem.model.user.UserDAO;
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
        sessionFactory = new Configuration().configure().buildSessionFactory();

    }

    // Todo: add param for foreign key, attaching to media object
    public int addReview(String textReview, int rating, String username){

        User user = null;

        Session session = sessionFactory.openSession();
        Transaction transaction = null;



        try {

            transaction = session.beginTransaction();

            try {
                user = (User) session.createQuery("from User where username = :username").setParameter("username", username).uniqueResult();
            }
            catch (HibernateException ex){
                return -1;
            }



            Review review = new Review();

            if (rating >= 1 && rating <= 5) {
                review.setRatingValue(rating);
            }

            if(textReview != null && !textReview.equals("")) {
                review.setReviewText(textReview);
            }

            review.setUser(user);


            transaction.commit();


        }

        catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        finally {
            session.close();
            return 0;
        }

    }


}


