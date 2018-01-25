package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 11/04/2016.
 */
public class ModelResponseUpdate {

    private String message;
    private ModelResponseUpdateData data;

    public ModelResponseUpdate() {
    }


    public ModelResponseUpdate(String message, ModelResponseUpdateData data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ModelResponseUpdateData getData() {
        return data;
    }

    public void setData(ModelResponseUpdateData data) {
        this.data = data;
    }
}
