package com.csci4448.MediaManagementSystem.model.user;

import com.csci4448.MediaManagementSystem.model.GenericDAOImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAOImpl
        extends GenericDAOImpl<User, Integer>
        implements UserDAO {

    public boolean increaseAccountBalance(String username, int _amount) {
        /*
        Increase the user's account balance by amount.

        Returns false if unsuccessful, true if successful
         */

        User user = getUser(username);

        if (_amount <= 0)
            return false;

        user.setAccountBalance(user.getAccountBalance() + _amount);
        return true;
    }

    public boolean decreaseAccountBalance(String username, int _amount) {
        /*
        Decrease the user's account balance by amount.

        Returns false if unsuccessful, true if successful
         */

        User user = getUser(username);

        if (_amount <= 0)
            return false;

        int currentBalance = user.getAccountBalance();
        if (currentBalance - _amount < 0)
            return false;

        user.setAccountBalance(currentBalance - _amount);
        return true;
    }

    public int addUser(String username, String password, String email, String firstName, String lastName) {
        /*
        Add a user to the User table.

        Returns: -1 if unsuccessful, the ID of the created user if successful
         */

        Integer userID = -1;

        // Create new user
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

        userID = create(user);

        if (userID == null)
            return -1;
        else
            return userID;
    }


    public User getUser(String username, String password) {
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

    public User getUser(String username) {
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

            user = (User) session.createQuery("from User where username = :username").setParameter("username", username).uniqueResult();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        return user;

    }

    public User getUser(int userID) {
        /*
        Query the User table for a specific user.

        Returns: null if unsuccessful, the specifc User object if successful
         */

        return retrieve(userID);

    }

    public boolean userExists(String username, String password) {

        return getUser(username, password) != null;

    }

}
