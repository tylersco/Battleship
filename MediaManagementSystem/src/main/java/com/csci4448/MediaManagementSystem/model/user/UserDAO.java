package com.csci4448.MediaManagementSystem.model.user;

import com.csci4448.MediaManagementSystem.model.media.Media;
import com.csci4448.MediaManagementSystem.model.media.MediaDAO;
import com.csci4448.MediaManagementSystem.model.review.Review;
import com.csci4448.MediaManagementSystem.model.review.ReviewDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Set;

public class UserDAO implements UserInterface {

    private SessionFactory sessionFactory;
    private User activeUser;

    public UserDAO() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        activeUser = null;
    }

    public int getUserID() {
        return activeUser.getUserID();
    }

    public String getUsername() {
        return activeUser.getUsername();
    }

    public String getEmail() { return activeUser.getEmail(); }

    public String getFirstName() {
        return activeUser.getFirstName();
    }

    public String getLastName() {
        return activeUser.getLastName();
    }

    public boolean getIsAdmin() {
        return activeUser.getIsAdmin();
    }

    public int getAccountBalance() {
        return activeUser.getAccountBalance();
    }

    public boolean increaseAccountBalance(int _amount) {
        /*
        Increase the active user's account balance by amount.

        Returns false if unsuccessful, true if successful
         */

        if (_amount <= 0)
            return false;

        activeUser.setAccountBalance(activeUser.getAccountBalance() + _amount);
        return true;
    }

    public boolean decreaseAccountBalance(int _amount) {
        /*
        Decrease the active user's account balance by amount.

        Returns false if unsuccessful, true if successful
         */

        if (_amount <= 0)
            return false;

        int currentBalance = activeUser.getAccountBalance();
        if (currentBalance - _amount < 0)
            return false;

        activeUser.setAccountBalance(currentBalance - _amount);
        return true;
    }

    public Set<Review> getReviews() {
        return activeUser.getReviews();
    }

    public Set<Media> getPersonalInventory() {
        return activeUser.getPersonalInventory();
    }

    public int addUser(String username, String password, String email, String firstName, String lastName) {
        /*
        Add a user to the User table.

        Returns: -1 if unsuccessful, the ID of the created user if successful
         */

        // Open a DB session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        int userID = -1;

        try {
            // Begin a transaction
            transaction = session.beginTransaction();

            // Create new user and all
            User user = new User();

            if (username == null || username.equals(""))
                return userID;

            if (password == null || password.equals(""))
                return userID;

            if (email == null || email.equals(""))
                return userID;

            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);

            if (firstName != null && !firstName.equals(""))
                user.setFirstName(firstName);

            if (lastName != null && !lastName.equals(""))
                user.setLastName(lastName);

            // Save user and commit transaction
            userID = (Integer) session.save(user);
            transaction.commit();
            activeUser = user;
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        return userID;

    }

    private User getUser(String username, String password) {
        /*
        Query the User table for a specific user.

        Returns: null if unsuccessful, the specifc User object if successful
         */

        // Open a DB session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        User user = null;

        try {
            // Begin a transaction
            transaction = session.beginTransaction();

            user = (User) session.createQuery("from User where username = :username and password = :password").setParameter("username", username).setParameter("password", password).uniqueResult();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        return user;

    }

    public boolean userExists(String username, String password) {

        User user = getUser(username, password);
        return user != null;

    }

    public boolean activeUserSet() {
        return activeUser != null;
    }

    public void setActiveUser(String username, String password) {

        activeUser = getUser(username, password);

    }

    public boolean isAdmin() {
        return (activeUser != null && activeUser.getIsAdmin());
    }

}
