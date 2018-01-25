package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 22/04/2016.
 */
public class ModelCollectionResult {

    private int id;
    private String venueName;
    private String addressEnglish;
    private String addressChinese;
    private double map_x;
    private double map_y;
    private String venueTagDescription;
    private String venueTagThumb;
    private String disatance;
    private int type; // type is used to know if it is the bot top of


    public ModelCollectionResult() {
    }

    public ModelCollectionResult(int id, String venueName, String addressEnglish, String addressChinese, double map_x, double map_y, String venueTagDescription, String venueTagThumb, String disatance, int type) {
        this.id = id;
        this.venueName = venueName;
        this.addressEnglish = addressEnglish;
        this.addressChinese = addressChinese;
        this.map_x = map_x;
        this.map_y = map_y;
        this.venueTagDescription = venueTagDescription;
        this.venueTagThumb = venueTagThumb;
        this.disatance = disatance;
        this.type = type;
    }

    public ModelCollectionResult(int id, String venueName, String addressEnglish, String addressChinese, String venueTagDescription, String venueTagThumb, String disatance, int type) {
        this.id = id;
        this.venueName = venueName;
        this.addressEnglish = addressEnglish;
        this.addressChinese = addressChinese;
        this.venueTagDescription = venueTagDescription;
        this.venueTagThumb = venueTagThumb;
        this.disatance = disatance;
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getAddressEnglish() {
        return addressEnglish;
    }

    public void setAddressEnglish(String addressEnglish) {
        this.addressEnglish = addressEnglish;
    }

    public String getAddressChinese() {
        return addressChinese;
    }

    public void setAddressChinese(String addressChinese) {
        this.addressChinese = addressChinese;
    }

    public double getMap_x() {
        return map_x;
    }

    public void setMap_x(double map_x) {
        this.map_x = map_x;
    }

    public double getMap_y() {
        return map_y;
    }

    public void setMap_y(double map_y) {
        this.map_y = map_y;
    }

    public String getVenueTagDescription() {
        return venueTagDescription;
    }

    public void setVenueTagDescription(String venueTagDescription) {
        this.venueTagDescription = venueTagDescription;
    }

    public String getVenueTagThumb() {
        return venueTagThumb;
    }

    public void setVenueTagThumb(String venueTagThumb) {
        this.venueTagThumb = venueTagThumb;
    }

    public String getDisatance() {
        return disatance;
    }

    public void setDisatance(String disatance) {
        this.disatance = disatance;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}