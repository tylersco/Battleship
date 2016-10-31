package com.csci4448.MediaManagementSystem.model.user;

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

    protected int getId() {
        return id;
    }

    protected String getUsername() {
        return username;
    }

    protected void setUsername(String _username) {
        username = _username;
    }

    protected void setPassword(String _password) {
        password = _password;
    }

    protected String getEmail() {
        return email;
    }

    protected void setEmail(String _email) {
        email = _email;
    }

    protected String getFirstName() {
        return firstName;
    }

    protected void setFirstName(String _firstName) {
        firstName = _firstName;
    }

    protected String getLastName() {
        return lastName;
    }

    protected void setLastName(String _lastName) {
        lastName = _lastName;
    }

    protected boolean getIsAdmin() {
        return isAdmin;
    }

    protected void setIsAdmin(boolean _isAdmin) {
        isAdmin = _isAdmin;
    }

    protected int getAccountBalance() {
        return accountBalance;
    }

    protected void setAccountBalance(int _accountBalance) {
        if (_accountBalance >= 0)
            accountBalance = _accountBalance;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + "\nLast Name: " + lastName + "\n Username: " + username + "\nEmail: " + email + "\n";
    }

}
