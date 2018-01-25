package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 11/04/2016.
 */
public class ModelResponsePostReview {

    private boolean isSuccesful;
    private String message;

    public ModelResponsePostReview(boolean isSuccesful, String message) {
        this.isSuccesful = isSuccesful;
        this.message = message;
    }

    public ModelResponsePostReview() {
    }

    public boolean isSuccesful() {
        return isSuccesful;
    }

    public void setIsSuccesful(boolean isSuccesful) {
        this.isSuccesful = isSuccesful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
