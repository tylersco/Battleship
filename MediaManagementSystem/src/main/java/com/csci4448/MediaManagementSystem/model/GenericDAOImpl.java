package com.csci4448.MediaManagementSystem.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public ID create(T newRecord) {
        /*
        Add a record of type T to T's entity class

        Returns: null if unsuccessful, the ID of the created record if successful
         */

        Transaction transaction = null;
        ID id = null;

        try {
            // Begin a transaction
            transaction = getCurrentSession().beginTransaction();
            // Save the new record
            id = (ID) getCurrentSession().save(newRecord);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
        }

        return id;

    }

    public boolean update(T record) {
        /*
        Update a record of type T in T's entity class

        Returns: false if unsuccessful, true if successful
         */

        Transaction transaction = null;

        try {
            // Begin a transaction
            transaction = getCurrentSession().beginTransaction();
            // Update the record
            getCurrentSession().update(record);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
            return false;
        }

        return true;

    }

    public boolean delete(T record) {
        /*
        Delete a record of type T in T's entity class

        Returns: false if unsuccessful, true if successful
         */

        Transaction transaction = null;

        try {
            // Begin a transaction
            transaction = getCurrentSession().beginTransaction();
            // Delete the record
            getCurrentSession().delete(record);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
            return false;
        }

        return true;
    }

    public T retrieve(ID id) {
        /*
        Retrieve a record of type T from T's entity class

        Returns: null if unsuccessful, the record of type T if successful
         */

        Transaction transaction = null;
        T record;

        try {
            // Begin a transaction
            transaction = getCurrentSession().beginTransaction();
            // Get the record based on the ID
            record = (T) getCurrentSession().get(daoType, id);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
            return null;
        }

        return record;

    }

    public List<T> getAll() {
        /*
        Retrieve all records of type T from T's entity class

        Returns: null if unsuccessful, a list of the records of type T if successful
         */

        Transaction transaction = null;
        List<T> records = null;

        try {
            // Begin a transaction
            transaction = getCurrentSession().beginTransaction();
            // Get the list of all records
            Query query = getCurrentSession().createQuery("from " + daoType.getName());
            records = query.list();
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();
            return null;
        }

        return records;

    }

}
