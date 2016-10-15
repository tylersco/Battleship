package com.csci4448.MediaManagementSystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

    private long id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean isAdmin;

    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

}
