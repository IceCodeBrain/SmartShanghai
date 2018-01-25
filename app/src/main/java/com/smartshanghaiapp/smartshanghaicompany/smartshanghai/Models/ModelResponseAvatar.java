package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 11/04/2016.
 */
public class ModelResponseAvatar {

    private boolean isSuccesful;
    private String data;

    public ModelResponseAvatar(boolean isSuccesful, String data) {
        this.isSuccesful = isSuccesful;
        this.data = data;
    }

    public ModelResponseAvatar() {
    }

    public boolean isSuccesful() {
        return isSuccesful;
    }

    public void setIsSuccesful(boolean isSuccesful) {
        this.isSuccesful = isSuccesful;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
