package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 11/04/2016.
 */
public class ModelPlace {

    private String venueName;
    private String addressShort;
    private String distance;
    private String description;

    public ModelPlace(String venueName, String addressShort, String distance, String description) {
        this.venueName = venueName;
        this.addressShort = addressShort;
        this.distance = distance;
        this.description = description;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getAddressShort() {
        return addressShort;
    }

    public void setAddressShort(String addressShort) {
        this.addressShort = addressShort;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
