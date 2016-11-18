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
    private boolean isAdmin;

    @Column(nullable = false)
    private int accountBalance;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Review> reviews = new HashSet<Review>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_MEDIA",
                joinColumns = @JoinColumn(name = "userID"),
                inverseJoinColumns = @JoinColumn(name = "mediaID"))
    private Set<Media> personalInventory = new HashSet<Media>();

    public User() {
        firstName = null;
        lastName = null;
        isAdmin = false;
        accountBalance = 0;
    }

    public int getUserID() {
        return userID;
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
        accountBalance = _accountBalance;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public Set<Media> getPersonalInventory() {
        return personalInventory;
    }

    public void addPersonalMedia(Media media){
        personalInventory.add(media);
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + "\nLast Name: " + lastName + "\nUsername: " + username + "\nEmail: " + email + "\n";
    }

}
