package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 19/05/2016.
 */
public class ModelEventInfoFromDB {

    private String name;
    private String address_en;
    private String address_cn;
    private String phone;

    public ModelEventInfoFromDB() {
    }

    public ModelEventInfoFromDB(String name, String address_en, String address_cn, String phone) {
        this.name = name;
        this.address_en = address_en;
        this.address_cn = address_cn;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress_en() {
        return address_en;
    }

    public void setAddress_en(String address_en) {
        this.address_en = address_en;
    }

    public String getAddress_cn() {
        return address_cn;
    }

    public void setAddress_cn(String address_cn) {
        this.address_cn = address_cn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
