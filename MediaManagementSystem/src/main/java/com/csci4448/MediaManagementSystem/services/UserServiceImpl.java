package com.csci4448.MediaManagementSystem.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.csci4448.MediaManagementSystem.model.User;

public class UserServiceImpl implements UserService {

    private SessionFactory sessionFactory;

    public UserServiceImpl() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public int addUser(String username, String password, String email, String firstName, String lastName, Boolean isAdmin) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        int userID = -1;

        try {
            transaction = session.beginTransaction();
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);

            if (firstName != null)
                user.setFirstName(firstName);

            if (lastName != null)
                user.setLastName(lastName);

            user.setIsAdmin(isAdmin);

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

    }

    public void listUsers() {

    }

}
