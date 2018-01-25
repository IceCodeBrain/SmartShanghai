package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 22/04/2016.
 */
public class ModelBuyAndSell {

    private int id;
    private String title;
    private String price;
    private String description;
    private String datePosted;
    private String thumb;

    public ModelBuyAndSell(int id, String title, String price, String description, String datePosted, String thumb) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
        this.datePosted = datePosted;
        this.thumb = thumb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
