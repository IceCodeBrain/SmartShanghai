package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 22/04/2016.
 */
public class ModelBuyAndSellCategory {

    private int id;
    private String tag_name;
    private int tag_id;

    public ModelBuyAndSellCategory(int id, String tag_name, int tag_id) {
        this.id = id;
        this.tag_name = tag_name;
        this.tag_id = tag_id;
    }

    public ModelBuyAndSellCategory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }
}
