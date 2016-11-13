package com.csci4448.MediaManagementSystem.model.media;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class MediaDAO {

    private SessionFactory sessionFactory;

    public MediaDAO() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    //ToDo: Basic add/edit/delete of media, and waitlist of rentable media

}
