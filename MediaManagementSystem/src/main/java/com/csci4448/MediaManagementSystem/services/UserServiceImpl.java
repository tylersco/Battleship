package com.csci4448.MediaManagementSystem.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Iterator;
import java.util.List;

import com.csci4448.MediaManagementSystem.model.User;

public class UserServiceImpl implements UserService {

    private SessionFactory sessionFactory;

    public UserServiceImpl() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public int addUser(String username, String password, String email, String firstName, String lastName, Boolean isAdmin) {

        // Open a DB session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        int userID = -1;

        try {
            // Begin a DB transaction (SQL operation essentially)
            transaction = session.beginTransaction();

            // Create new user and all
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);

            if (firstName != null)
                user.setFirstName(firstName);

            if (lastName != null)
                user.setLastName(lastName);

            user.setIsAdmin(isAdmin);

            // Save user and commit transaction
            userID = (Integer) session.save(user);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        return userID;

    }

    public void deleteUser(int userID) {

        // Open a DB session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            // Begin a transaction (SQL operation essentially)
            transaction = session.beginTransaction();

            // Get user object, delete it, commit transaction
            User user = (User) session.get(User.class, userID);
            session.delete(user);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

    }

    public Iterator listUsers() {

        // Open a DB session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        Iterator userIterator = null;

        try {
            // Begin a transaction (SQL operation essentially)
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

}
