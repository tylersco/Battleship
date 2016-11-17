package com.csci4448.MediaManagementSystem.model;

import java.util.List;

public interface GenericDAO <T, ID> {

    ID create(T newRecord);
    T retrieve(ID id);
    boolean update(T record);
    boolean delete(T record);
    List<T> getAll();

}
