package com.csci4448.MediaManagementSystem.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class GenericDAOImpl<T, ID extends Serializable>
        implements GenericDAO<T, ID> {

    protected SessionFactory sessionFactory;

    private Class<? extends T> daoType;

    public GenericDAOImpl() {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        daoType = (Class) parameterizedType.getActualTypeArguments()[0];
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public ID create(T newRecord) {
        /*
        Add a record of type T to T's entity class

        Returns: null if unsuccessful, the ID of the created record if successful
         */

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        ID id = null;

        try {
            // Begin a transaction
            transaction = session.beginTransaction();
            // Save the new record
            id = (ID) session.save(newRecord);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
        } finally {
            session.close();
        }

        return id;

    }

    public boolean update(T record) {
        /*
        Update a record of type T in T's entity class

        Returns: false if unsuccessful, true if successful
         */

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            // Begin a transaction
            transaction = session.beginTransaction();
            // Update the record
            session.update(record);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
            return false;
        } finally {
            session.close();
        }

        return true;

    }

    public boolean delete(T record) {
        /*
        Delete a record of type T in T's entity class

        Returns: false if unsuccessful, true if successful
         */

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            // Begin a transaction
            transaction = session.beginTransaction();
            // Delete the record
            session.delete(record);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
            return false;
        } finally {
            session.close();
        }

        return true;
    }

    public T retrieve(ID id) {
        /*
        Retrieve a record of type T from T's entity class

        Returns: null if unsuccessful, the record of type T if successful
         */

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        T record;

        try {
            // Begin a transaction
            transaction = session.beginTransaction();
            // Get the record based on the ID
            record = (T) session.get(daoType, id);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
            return null;
        } finally {
            session.close();
        }

        return record;

    }

    public List<T> getAll() {
        /*
        Retrieve all records of type T from T's entity class

        Returns: null if unsuccessful, a list of the records of type T if successful
         */

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<T> records = null;

        try {
            // Begin a transaction
            transaction = session.beginTransaction();
            // Get the list of all records
            Query query = session.createQuery("from " + daoType.getName());
            records = query.list();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
            return null;
        } finally {
            session.close();
        }

        return records;

    }

}
