package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 26/04/2016.
 */
public class ModelReviewToPost {

    private String key;
    private String emailAddress;
    private String password;
    private int user_id;
    private int rev_city;
    private String rev_quote;
    private int rev_venue;
    private int rev_totalrating;
    private int source_id;

    private int rev_food;
    private int rev_service;
    private String rev_txt;
    private int rev_decor;
    private int rev_value;
    private int rev_drinks;
    private int rev_goodforfriends;
    private int rev_goodforbusiness;
    private int rev_goodforfamily;
    private int rev_goodfordate;

    public ModelReviewToPost() {
    }

    public ModelReviewToPost(String key, String emailAddress, String password, int user_id, int rev_city, String rev_quote, int rev_venue, int rev_totalrating, int source_id, int rev_food, int rev_service, String rev_txt, int rev_decor, int rev_value, int rev_drinks, int rev_goodforfriends, int rev_goodforbusiness, int rev_goodforfamily, int rev_goodfordate) {
        this.key = key;
        this.emailAddress = emailAddress;
        this.password = password;
        this.user_id = user_id;
        this.rev_city = rev_city;
        this.rev_quote = rev_quote;
        this.rev_venue = rev_venue;
        this.rev_totalrating = rev_totalrating;
        this.source_id = source_id;
        this.rev_food = rev_food;
        this.rev_service = rev_service;
        this.rev_txt = rev_txt;
        this.rev_decor = rev_decor;
        this.rev_value = rev_value;
        this.rev_drinks = rev_drinks;
        this.rev_goodforfriends = rev_goodforfriends;
        this.rev_goodforbusiness = rev_goodforbusiness;
        this.rev_goodforfamily = rev_goodforfamily;
        this.rev_goodfordate = rev_goodfordate;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRev_city() {
        return rev_city;
    }

    public void setRev_city(int rev_city) {
        this.rev_city = rev_city;
    }

    public String getRev_quote() {
        return rev_quote;
    }

    public void setRev_quote(String rev_quote) {
        this.rev_quote = rev_quote;
    }

    public int getRev_venue() {
        return rev_venue;
    }

    public void setRev_venue(int rev_venue) {
        this.rev_venue = rev_venue;
    }

    public int getRev_totalrating() {
        return rev_totalrating;
    }

    public void setRev_totalrating(int rev_totalrating) {
        this.rev_totalrating = rev_totalrating;
    }

    public int getSource_id() {
        return source_id;
    }

    public void setSource_id(int source_id) {
        this.source_id = source_id;
    }

    public int getRev_food() {
        return rev_food;
    }

    public void setRev_food(int rev_food) {
        this.rev_food = rev_food;
    }

    public int getRev_service() {
        return rev_service;
    }

    public void setRev_service(int rev_service) {
        this.rev_service = rev_service;
    }

    public String getRev_txt() {
        return rev_txt;
    }

    public void setRev_txt(String rev_txt) {
        this.rev_txt = rev_txt;
    }

    public int getRev_decor() {
        return rev_decor;
    }

    public void setRev_decor(int rev_decor) {
        this.rev_decor = rev_decor;
    }

    public int getRev_value() {
        return rev_value;
    }

    public void setRev_value(int rev_value) {
        this.rev_value = rev_value;
    }

    public int getRev_drinks() {
        return rev_drinks;
    }

    public void setRev_drinks(int rev_drinks) {
        this.rev_drinks = rev_drinks;
    }

    public int getRev_goodforfriends() {
        return rev_goodforfriends;
    }

    public void setRev_goodforfriends(int rev_goodforfriends) {
        this.rev_goodforfriends = rev_goodforfriends;
    }

    public int getRev_goodforbusiness() {
        return rev_goodforbusiness;
    }

    public void setRev_goodforbusiness(int rev_goodforbusiness) {
        this.rev_goodforbusiness = rev_goodforbusiness;
    }

    public int getRev_goodforfamily() {
        return rev_goodforfamily;
    }

    public void setRev_goodforfamily(int rev_goodforfamily) {
        this.rev_goodforfamily = rev_goodforfamily;
    }

    public int getRev_goodfordate() {
        return rev_goodfordate;
    }

    public void setRev_goodfordate(int rev_goodfordate) {
        this.rev_goodfordate = rev_goodfordate;
    }
}
