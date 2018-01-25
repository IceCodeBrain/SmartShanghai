package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

import java.util.ArrayList;

/**
 * Created by Pithou on 11/04/2016.
 */
public class ModelResponseRegister {

    private boolean isSuccesful;
    private String message;
    private ModelResponseRegisterUserId data;

    public ModelResponseRegister() {
    }

    public ModelResponseRegister(boolean isSuccesful, String message, ModelResponseRegisterUserId data) {
        this.isSuccesful = isSuccesful;
        this.message = message;
        this.data = data;
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

    public ModelResponseRegisterUserId getData() {
        return data;
    }

    public void setData(ModelResponseRegisterUserId data) {
        this.data = data;
    }
}
