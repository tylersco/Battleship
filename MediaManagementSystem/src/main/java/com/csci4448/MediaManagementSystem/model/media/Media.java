package com.csci4448.MediaManagementSystem.model.media;

import com.csci4448.MediaManagementSystem.model.review.Review;
import com.csci4448.MediaManagementSystem.model.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MEDIA")
public class Media implements MediaInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mediaID;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String type;

    private String image;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private int price;

    private int sellPrice;

    @Column(nullable = false)
    private int inventoryCount;

    @Column(nullable = false)
    private boolean isRentable;

    @OneToMany(mappedBy = "media")
    private Set<Review> reviews = new HashSet<Review>();

    @ManyToMany(mappedBy = "personalInventory")
    private Set<User> currentUsers = new HashSet<User>();

    public Media() {
        description = null;
        image = null;
        sellPrice = -1;
    }

    public int getMediaID() {
        return mediaID;
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String _title) {
        title = _title;
    }

    public String getDescription() {
        return description;
    }

    void setDescription(String _description) {
        description = _description;
    }

    public String getType() {
        return type;
    }

    void setType(String _type) {
        type = _type;
    }

    public String getImage() {
        return image;
    }

    void setImage(String _image) {
        image = _image;
    }

    public String getGenre() {
        return genre;
    }

    void setGenre(String _genre) {
        genre = _genre;
    }

    public int getPrice() {
        return price;
    }

    void setPrice(int _price) {
        price = _price;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    void setSellPrice(int _sellPrice) {
        sellPrice = _sellPrice;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    void setInventoryCount(int _inventoryCount) {
        inventoryCount = _inventoryCount;
    }

    public boolean getIsRentable() {
        return isRentable;
    }

    void setIsRentable(boolean _isRentable) {
        isRentable = _isRentable;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nType: " + type + "\n Genre: " + genre + "\n";
    }

}

