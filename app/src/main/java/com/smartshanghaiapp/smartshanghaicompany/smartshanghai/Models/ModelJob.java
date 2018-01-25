package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 22/04/2016.
 */
public class ModelJob {

    private int id;
    private String title;
    private String company_name;
    private String company_logo;
    private String date_published;

    public ModelJob() {
    }

    public ModelJob(int id, String title, String company_name, String company_logo, String date_published) {
        this.id = id;
        this.title = title;
        this.company_name = company_name;
        this.company_logo = company_logo;
        this.date_published = date_published;
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

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public String getDate_published() {
        return date_published;
    }

    public void setDate_published(String date_published) {
        this.date_published = date_published;
    }
}

