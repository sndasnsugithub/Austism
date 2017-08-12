package com.mlab.mechanixlab.austimapp;

import android.graphics.Bitmap;

public class ImageItem {
    private Bitmap image;
    private String title;
    private String soundPath;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSoundPath() {
        return soundPath;
    }

    public void setSoundPath(String soundPath) {
        this.soundPath = soundPath;
    }
}
