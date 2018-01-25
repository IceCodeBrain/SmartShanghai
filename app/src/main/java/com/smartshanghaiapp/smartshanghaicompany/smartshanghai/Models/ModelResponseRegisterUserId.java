package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

import java.util.ArrayList;

/**
 * Created by Pithou on 11/04/2016.
 */
public class ModelResponseRegisterUserId {

    private int userId;

    public ModelResponseRegisterUserId(int userId) {
        this.userId = userId;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
