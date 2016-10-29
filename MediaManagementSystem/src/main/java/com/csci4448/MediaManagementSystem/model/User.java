package com.csci4448.MediaManagementSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private Boolean isAdmin;

    @Column(nullable = false)
    private int accountBalance = 0;

    //Todo: Implement personalInventory hash table. Not sure the best way to do this

    public User() {
        firstName = null;
        lastName = null;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String _username) {
        username = _username;
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

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int _accountBalance) {
        if (_accountBalance >= 0)
            accountBalance = _accountBalance;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + "\nLast Name: " + lastName + "\n Username: " + username + "\nEmail: " + email + "\n";
    }

}
