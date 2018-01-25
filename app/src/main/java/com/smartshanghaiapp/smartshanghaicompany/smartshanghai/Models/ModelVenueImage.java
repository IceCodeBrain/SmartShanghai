package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pithou on 22/04/2016.
 */
public class ModelVenueImage  {

    private int id;
    private int venue_id;
    private String large_picture;
    private String thumb170x170;

    public ModelVenueImage() {
    }

    public ModelVenueImage(int id, int venue_id, String large_picture, String thumb170x170) {
        this.id = id;
        this.venue_id = venue_id;
        this.large_picture = large_picture;
        this.thumb170x170 = thumb170x170;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(int venue_id) {
        this.venue_id = venue_id;
    }

    public String getLarge_picture() {
        return large_picture;
    }

    public void setLarge_picture(String large_picture) {
        this.large_picture = large_picture;
    }

    public String getThumb170x170() {
        return thumb170x170;
    }

    public void setThumb170x170(String thumb170x170) {
        this.thumb170x170 = thumb170x170;
    }
}
