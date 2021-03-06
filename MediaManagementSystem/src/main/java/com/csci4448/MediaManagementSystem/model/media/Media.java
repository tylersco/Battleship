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
    private boolean isRentable;

    @OneToMany(mappedBy = "media", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DETACH}, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<Review>();

    @ManyToMany(mappedBy = "personalInventory", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
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

    public void setTitle(String _title) {
        title = _title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String _description) {
        description = _description;
    }

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String _image) {
        image = _image;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String _genre) {
        genre = _genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int _price) {
        price = _price;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int _sellPrice) {
        sellPrice = _sellPrice;
    }

    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int _inventoryCount) {
        inventoryCount = _inventoryCount;
    }

    public boolean getIsRentable() {
        return isRentable;
    }

    public void addUserOwner(User user){
        currentUsers.add(user);
    }

    public void setIsRentable(boolean _isRentable) {
        isRentable = _isRentable;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public Set<User> getCurrentUsers() {
        return currentUsers;
    }

    public void addCurrentUser(User user){
        currentUsers.add(user);
    }

    public void removeCurrentUser(User user) {
        currentUsers.remove(user);
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nType: " + type + "\nGenre: " + genre + "\n";
    }

}

