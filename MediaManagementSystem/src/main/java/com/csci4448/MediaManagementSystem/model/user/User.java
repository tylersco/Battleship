package com.csci4448.MediaManagementSystem.model.user;

import com.csci4448.MediaManagementSystem.model.media.Media;
import com.csci4448.MediaManagementSystem.model.review.Review;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User implements UserInterface {

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
    private boolean isAdmin;

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
        isAdmin = false;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    void setUsername(String _username) {
        username = _username;
    }

    void setPassword(String _password) {
        password = _password;
    }

    public String getEmail() {
        return email;
    }

    void setEmail(String _email) {
        email = _email;
    }

    public String getFirstName() {
        return firstName;
    }

    void setFirstName(String _firstName) {
        firstName = _firstName;
    }

    public String getLastName() {
        return lastName;
    }

    void setLastName(String _lastName) {
        lastName = _lastName;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    void setIsAdmin(boolean _isAdmin) {
        isAdmin = _isAdmin;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    void setAccountBalance(int _accountBalance) {
        accountBalance = _accountBalance;
    }

    Set<Review> getReviews() {
        return reviews;
    }

    Set<Media> getPersonalInventory() {
        return personalInventory;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + "\nLast Name: " + lastName + "\n Username: " + username + "\nEmail: " + email + "\n";
    }

}
