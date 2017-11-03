package com.pss.booking.model;

/**
 * Created by Ravi on 29/07/15.
 */
public class NavDrawerItem {
    private boolean showNotify = false;
    private String title;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private int image;

    public int getImageHighlight() {
        return ImageHighlight;
    }

    public void setImageHighlight(int imageHighlight) {
        ImageHighlight = imageHighlight;
    }

    private int ImageHighlight;


    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title, int image, int imageHighlight) {
        this.showNotify = showNotify;
        this.title = title;
        this.image = image;
        this.ImageHighlight = imageHighlight;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
