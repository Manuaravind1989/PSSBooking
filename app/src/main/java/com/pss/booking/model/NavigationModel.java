package com.pss.booking.model;

/**
 * Created by mdev3 on 10/24/16.
 */
public class NavigationModel {

    private int image;

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    private boolean isActive =false;
}
