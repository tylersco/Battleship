package com.csci4448.MediaManagementSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "REVIEW")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int ratingValue;

    @Column(nullable = false)
    private String reviewText;

    @OneToOne
    @JoinColumn(name = "userID")
    private User user;

    public int getID() {
        return id;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int _ratingValue) {
        if (_ratingValue >= 1 && _ratingValue <= 5)
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

    @Override
    public String toString() {
        return "User: " + user.getUsername() + "\nReview: " + reviewText + "\nRating: " + ratingValue + "\n";
    }

}
