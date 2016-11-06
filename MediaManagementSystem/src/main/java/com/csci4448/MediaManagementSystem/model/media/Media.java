package com.csci4448.MediaManagementSystem.model.media;

import com.csci4448.MediaManagementSystem.model.review.Review;
import com.csci4448.MediaManagementSystem.model.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MEDIA")
public class Media {

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
    private Boolean isRentable;

    @OneToMany(mappedBy = "media")
    private Set<Review> reviews = new HashSet<Review>();

    @ManyToMany(mappedBy = "personalInventory")
    private Set<User> currentUsers = new HashSet<User>();

    public Media() {
        description = null;
        image = null;
        sellPrice = -1;
    }

    protected int getMediaID() {
        return mediaID;
    }

    public String getTitle() {
        return title;
    }

    protected void setTitle(String _title) {
        title = _title;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String _description) {
        description = _description;
    }

    public String getType() {
        return type;
    }

    protected void setType(String _type) {
        type = _type;
    }

    protected String getImage() {
        return image;
    }

    protected void setImage(String _image) {
        image = _image;
    }

    public String getGenre() {
        return genre;
    }

    protected void setGenre(String _genre) {
        genre = _genre;
    }

    protected int getPrice() {
        return price;
    }

    protected void setPrice(int _price) {
        price = _price;
    }

    protected int getSellPrice() {
        return sellPrice;
    }

    protected void setSellPrice(int _sellPrice) {
        sellPrice = _sellPrice;
    }

    protected int getInventoryCount() {
        return inventoryCount;
    }

    protected void setInventoryCount(int _inventoryCount) {
        inventoryCount = _inventoryCount;
    }

    protected Boolean getIsRentable() {
        return isRentable;
    }

    protected void setIsRentable(Boolean _isRentable) {
        isRentable = _isRentable;
    }

    protected Set<Review> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nType: " + type + "\n Genre: " + genre + "\n";
    }

}
