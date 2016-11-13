package com.csci4448.MediaManagementSystem.model.review;

import com.csci4448.MediaManagementSystem.model.user.User;
import com.csci4448.MediaManagementSystem.model.media.Media;

import javax.persistence.*;

@Entity
@Table(name = "REVIEW")
public class Review implements ReviewInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewID;

    @Column(nullable = false)
    private int ratingValue;

    @Column(nullable = false)
    private String reviewText;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "mediaID")
    private Media media;

    public int getReviewID() {
        return reviewID;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    protected void setRatingValue(int _ratingValue) {
            ratingValue = _ratingValue;
    }

    public String getReviewText() {
        return reviewText;
    }

    protected void setReviewText(String _reviewText) {
        reviewText = _reviewText;
    }

    public User getUser() {
        return user;
    }

    protected void setUser(User _user){
        user = _user;
    }

    public Media getMedia() {
        return media;
    }

    protected void setMedia(Media _media) {
        media = _media;
    }

    @Override
    public String toString() {
        return "User: " + user.getUsername() + "\nReview: " + reviewText + "\nRating: " + ratingValue + "\n";
    }

}
