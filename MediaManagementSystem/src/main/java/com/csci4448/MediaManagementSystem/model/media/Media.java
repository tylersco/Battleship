package com.csci4448.MediaManagementSystem.model.media;

import javax.persistence.*;

@Entity
@Table(name = "MEDIA")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    private int inventoryCount;

    //Todo: Use foreign keys to attach review for the given media

    //Todo: Use foreign keys to attach a waitlist of users for the media

    protected int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Title: " + title + "\nType: " + type + "\n Genre: " + genre + "\n";
    }

}

