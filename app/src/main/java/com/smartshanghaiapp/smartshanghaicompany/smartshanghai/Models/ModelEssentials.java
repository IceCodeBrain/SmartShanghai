package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 12/05/2016.
 */
public class ModelEssentials {

    private String title;
    private String description;
    private int smsh_venue_id;

    public ModelEssentials(String title, String description, int smsh_venue_id) {
        this.title = title;
        this.description = description;
        this.smsh_venue_id = smsh_venue_id;
    }

    public ModelEssentials() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSmsh_venue_id() {
        return smsh_venue_id;
    }

    public void setSmsh_venue_id(int smsh_venue_id) {
        this.smsh_venue_id = smsh_venue_id;
    }
}
