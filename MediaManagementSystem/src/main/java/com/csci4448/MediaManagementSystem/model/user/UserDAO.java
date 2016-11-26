package com.csci4448.MediaManagementSystem.model.user;

import com.csci4448.MediaManagementSystem.model.GenericDAO;
import com.csci4448.MediaManagementSystem.model.media.Media;

import java.util.List;

public interface UserDAO extends GenericDAO<User, Integer> {

    boolean increaseAccountBalance(String username, int _amount);
    boolean decreaseAccountBalance(String username, int _amount);
    int addUser(String username, String password, String email, String firstName, String lastName);
    User getUser(String username, String password);
    User getUser(String username);
    User getUser(int userID);
    boolean userExists(String username, String password);
    List<Media> getPersonalInventory(String username);

}
