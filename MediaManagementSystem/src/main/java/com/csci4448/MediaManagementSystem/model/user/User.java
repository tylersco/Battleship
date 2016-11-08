package com.csci4448.MediaManagementSystem.model.user;

import com.csci4448.MediaManagementSystem.model.media.Media;
import com.csci4448.MediaManagementSystem.model.review.Review;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

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

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews = new HashSet<Review>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "USER_MEDIA",
                joinColumns = @JoinColumn(name = "userID"),
                inverseJoinColumns = @JoinColumn(name = "mediaID"))
    private Set<Media> personalInventory = new HashSet<Media>();

    public User() {
        firstName = null;
        lastName = null;
    }

    protected int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    protected void setUsername(String _username) {
        username = _username;
    }

    protected void setPassword(String _password) {
        password = _password;
    }

    public String getEmail() {
        return email;
    }

    protected void setEmail(String _email) {
        email = _email;
    }

    public String getFirstName() {
        return firstName;
    }

    protected void setFirstName(String _firstName) {
        firstName = _firstName;
    }

    public String getLastName() {
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
        accountBalance = _accountBalance;
    }

    protected Set<Review> getReviews() {
        return reviews;
    }

    protected Set<Media> getPersonalInventory() {
        return personalInventory;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + "\nLast Name: " + lastName + "\n Username: " + username + "\nEmail: " + email + "\n";
    }

}
