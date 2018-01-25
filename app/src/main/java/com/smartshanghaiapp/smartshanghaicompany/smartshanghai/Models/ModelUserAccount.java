package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 11/04/2016.
 */
public class ModelUserAccount {

    private int id;
    private String username;
    private String emailAddress;
    private int emailIsVerified;
    private int nationalityTagId;
    private String avatar;

    public ModelUserAccount() {
    }

    public ModelUserAccount(int id, String username, String emailAddress, int emailIsVerified, int nationalityTagId, String avatar) {
        this.id = id;
        this.username = username;
        this.emailAddress = emailAddress;
        this.emailIsVerified = emailIsVerified;
        this.nationalityTagId = nationalityTagId;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getEmailIsVerified() {
        return emailIsVerified;
    }

    public void setEmailIsVerified(int emailIsVerified) {
        this.emailIsVerified = emailIsVerified;
    }

    public int getNationalityTagId() {
        return nationalityTagId;
    }

    public void setNationalityTagId(int nationalityTagId) {
        this.nationalityTagId = nationalityTagId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
