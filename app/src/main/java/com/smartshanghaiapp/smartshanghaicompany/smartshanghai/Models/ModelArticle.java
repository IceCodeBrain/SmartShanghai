package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 22/04/2016.
 */
public class ModelArticle {

    private int id;
    private String title;
    private String url;
    private String teaser;
    private String datePublished;
    private String coverImage;

    public ModelArticle(int id, String title, String url, String teaser, String datePublished, String coverImage) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.teaser = teaser;
        this.datePublished = datePublished;
        this.coverImage = coverImage;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
