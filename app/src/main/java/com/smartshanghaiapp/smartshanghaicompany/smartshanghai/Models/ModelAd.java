package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 12/05/2016.
 */
public class ModelAd {

  int bannerId;

    public ModelAd(int bannerId) {
        this.bannerId = bannerId;
    }

    public ModelAd() {
    }

    public int getBannerId() {
        return bannerId;
    }

    public void setBannerId(int bannerId) {
        this.bannerId = bannerId;
    }
}
