package com.pss.booking.model;

/**
 * Created by mdev3 on 10/19/16.
 */
public class CategoryModel {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private String name ="";
    private int image;
}
