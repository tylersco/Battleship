package com.csci4448.MediaManagementSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private Boolean isAdmin;

    public User() {
        username = "";
        password = "";
        email = "";
        firstName = "";
        lastName = "";
        isAdmin = true; // TODO: For now, all users are admins until we get the database up, for testing
    }

    public int getId() {
        return id;
    }

    public void setID(int _id) {
        id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String _username) {
        username = _username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String _password) {
        password = _password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String _email) {
        email = _email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String _firstName) {
        firstName = _firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String _lastName) {
        lastName = _lastName;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean _isAdmin) {
        isAdmin = _isAdmin;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + "\nLast Name: " + lastName + "\n Username: " + username + "\nEmail: " + email + "\n";
    }

}
