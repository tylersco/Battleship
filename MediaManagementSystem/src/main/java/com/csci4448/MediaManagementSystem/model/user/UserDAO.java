package com.csci4448.MediaManagementSystem.model.user;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

public class UserDAO {

    private SessionFactory sessionFactory;
    private User activeUser;

    public UserDAO() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        activeUser = null;
    }

    public int addUser(String username, String password, String email, String firstName, String lastName, Boolean isAdmin) {

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

            if (isAdmin == null)
                return userID;

            user.setIsAdmin(isAdmin);

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

    public Iterator listUsers() {

        if (activeUser == null || !activeUser.getIsAdmin())
            return null;

        // Open a DB session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        Iterator userIterator = null;

        try {
            // Begin a transaction
            transaction = session.beginTransaction();

            // Get a list of all users as an iterator
            List users = session.createQuery("from User").list();
            userIterator = users.iterator();

            transaction.commit();

        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        return userIterator;

    }

    private User getUser(String username, String password) {

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

    public Boolean userExists(String username, String password) {

        User user = getUser(username, password);
        return user != null;

    }

    public Boolean activeUserSet() {
        return activeUser != null;
    }

    public void setActiveUser(String username, String password) {

        User user = getUser(username, password);
        activeUser = user;

    }

    public Boolean isAdmin() {
        return (activeUser != null && activeUser.getIsAdmin());
    }

}
