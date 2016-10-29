package com.csci4448.MediaManagementSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "REVIEW")
public class Review {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private int ratingValue;

    @Column(nullable = false)
    private String reviewText;

    @OneToOne
    @JoinColumn(name = "userID")
    private User user;

    //Todo: Implement personalInventory hash table. Not sure the best way to do this

    public Review() {
    }

    public int getID() {
        return id;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int _ratingValue) {
        ratingValue = _ratingValue;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String _reviewText) {
        reviewText = _reviewText;
    }

    public User getUser() {
        return user;
    }

}
