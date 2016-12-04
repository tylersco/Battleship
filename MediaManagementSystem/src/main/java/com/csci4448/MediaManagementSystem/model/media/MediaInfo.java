package com.csci4448.MediaManagementSystem.model.media;

import java.lang.reflect.Field;
import java.util.HashMap;

// Very lightweight class used to save the info of a media. This class is designed to be a "readonly" version of the
// Media class, without the reviews.
public class MediaInfo {

    private int mediaID;
    private String title;
    private String description;
    private String type;
    private String image;
    private String genre;
    private int price;
    private int sellPrice;
    private int inventoryCount;
    private boolean isRentable;

    private MediaInfo(int _id, String _title, String _desc, String _image, String _type, String _genre, int _price,
                  int _sellPrice, int _invCount, boolean _isRentable) {
        this.mediaID = _id;
        this.title = _title;
        this.description = _desc;
        this.image = _image;
        this.type = _type;
        this.genre = _genre;
        this.price = _price;
        this.sellPrice = _sellPrice;
        this.inventoryCount = _invCount;
        this.isRentable = _isRentable;

    }

    @Override
    public Object clone() {
        return new MediaInfo(mediaID, title, description, image, type, genre, price, sellPrice, inventoryCount, isRentable);
    }

    public int getMediaID() { return mediaID; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getType() { return type; }
    public String getImage() { return image; }
    public String getGenre() { return genre; }
    public int getPrice() { return price; }
    public int getSellPrice() { return sellPrice; }
    public int getInventoryCount() { return inventoryCount; }
    public boolean getIsRentable() { return isRentable; }

    public static MediaInfo createFromMedia(Media media) {
        return new MediaInfo(media.getMediaID(), media.getTitle(), media.getDescription(), media.getImage(),
                media.getType(), media.getGenre(), media.getPrice(), media.getSellPrice(), media.getInventoryCount(),
                media.getIsRentable());
    }

    public static MediaInfo createFromInfo(int _id, String _title, String _desc, String _image, String _type,
                                           String _genre, int _price, int _sellPrice, int _invCount,
                                           boolean _isRentable) {
        return new MediaInfo(_id, _title, _desc, _image, _type, _genre, _price, _sellPrice, _invCount, _isRentable);
    }

    public static MediaInfo createFromModified(MediaInfo info, HashMap<String, Object> map) {
        MediaInfo ret = (MediaInfo)info.clone();

        // I know Java reflection is *very* slow, but we arent exactly writing a performant application anyway
        // Also, yes, I know, this is a dirty nasty hack, and I will make atonement for this later
        for (String key : map.keySet()) {
            Object value = map.get(key);
            try {
                Field field = MediaInfo.class.getDeclaredField(key);
                field.setAccessible(true);
                field.set(ret, value);
                field.setAccessible(false);
            } catch (NoSuchFieldException e) {
                System.err.println("The MediaInfo class does not have the field '" + key + "'.");
            } catch (IllegalAccessException e) {
                System.err.println("The MediaInfo field '" + key + "' could not be accessed.");
            }
        }

        return ret;
    }
}
