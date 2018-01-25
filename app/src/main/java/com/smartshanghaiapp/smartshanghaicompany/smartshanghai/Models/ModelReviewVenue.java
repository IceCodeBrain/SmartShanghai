package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Pithou on 05/04/2016.
 */
public class ModelReviewVenue {

    private int rev_id;
    private int rev_user;
    private String rev_time;
    private int rev_venue;
    private String rev_quote;
    private String rev_txt;
    private float rev_totalrating;
    private int rev_food;
    private int rev_drinks;
    private int rev_value;
    private int rev_service;
    private int rev_decor;
    private int rev_goodfordate;
    private int rev_goodforbusiness;
    private int rev_goodforfriends;
    private int rev_goodforfamily;
    private int rev_useful;
    private int approved;
    private String thumb;
    private String name_en;
    private String username;
    private String avatar;

    public ModelReviewVenue() {
    }

    public ModelReviewVenue(int rev_id, int rev_user, String rev_time, int rev_venue, String rev_quote, String rev_txt, float rev_totalrating, int rev_food, int rev_drinks, int rev_value, int rev_service, int rev_decor, int rev_goodfordate, int rev_goodforbusiness, int rev_goodforfriends, int rev_goodforfamily, int rev_useful, int approved, String thumb, String name_en, String username, String avatar) {
        this.rev_id = rev_id;
        this.rev_user = rev_user;
        this.rev_time = rev_time;
        this.rev_venue = rev_venue;
        this.rev_quote = rev_quote;
        this.rev_txt = rev_txt;
        this.rev_totalrating = rev_totalrating;
        this.rev_food = rev_food;
        this.rev_drinks = rev_drinks;
        this.rev_value = rev_value;
        this.rev_service = rev_service;
        this.rev_decor = rev_decor;
        this.rev_goodfordate = rev_goodfordate;
        this.rev_goodforbusiness = rev_goodforbusiness;
        this.rev_goodforfriends = rev_goodforfriends;
        this.rev_goodforfamily = rev_goodforfamily;
        this.rev_useful = rev_useful;
        this.approved = approved;
        this.thumb = thumb;
        this.name_en = name_en;
        this.username = username;
        this.avatar = avatar;
    }

    public int getRev_id() {
        return rev_id;
    }

    public void setRev_id(int rev_id) {
        this.rev_id = rev_id;
    }

    public int getRev_user() {
        return rev_user;
    }

    public void setRev_user(int rev_user) {
        this.rev_user = rev_user;
    }

    public String getRev_time() {
        return rev_time;
    }

    public void setRev_time(String rev_time) {
        this.rev_time = rev_time;
    }

    public int getRev_venue() {
        return rev_venue;
    }

    public void setRev_venue(int rev_venue) {
        this.rev_venue = rev_venue;
    }

    public String getRev_quote() {
        return rev_quote;
    }

    public void setRev_quote(String rev_quote) {
        this.rev_quote = rev_quote;
    }

    public String getRev_txt() {
        return rev_txt;
    }

    public void setRev_txt(String rev_txt) {
        this.rev_txt = rev_txt;
    }

    public float getRev_totalrating() {
        return rev_totalrating;
    }

    public void setRev_totalrating(float rev_totalrating) {
        this.rev_totalrating = rev_totalrating;
    }

    public int getRev_food() {
        return rev_food;
    }

    public void setRev_food(int rev_food) {
        this.rev_food = rev_food;
    }

    public int getRev_drinks() {
        return rev_drinks;
    }

    public void setRev_drinks(int rev_drinks) {
        this.rev_drinks = rev_drinks;
    }

    public int getRev_value() {
        return rev_value;
    }

    public void setRev_value(int rev_value) {
        this.rev_value = rev_value;
    }

    public int getRev_service() {
        return rev_service;
    }

    public void setRev_service(int rev_service) {
        this.rev_service = rev_service;
    }

    public int getRev_decor() {
        return rev_decor;
    }

    public void setRev_decor(int rev_decor) {
        this.rev_decor = rev_decor;
    }

    public int getRev_goodfordate() {
        return rev_goodfordate;
    }

    public void setRev_goodfordate(int rev_goodfordate) {
        this.rev_goodfordate = rev_goodfordate;
    }

    public int getRev_goodforbusiness() {
        return rev_goodforbusiness;
    }

    public void setRev_goodforbusiness(int rev_goodforbusiness) {
        this.rev_goodforbusiness = rev_goodforbusiness;
    }

    public int getRev_goodforfriends() {
        return rev_goodforfriends;
    }

    public void setRev_goodforfriends(int rev_goodforfriends) {
        this.rev_goodforfriends = rev_goodforfriends;
    }

    public int getRev_goodforfamily() {
        return rev_goodforfamily;
    }

    public void setRev_goodforfamily(int rev_goodforfamily) {
        this.rev_goodforfamily = rev_goodforfamily;
    }

    public int getRev_useful() {
        return rev_useful;
    }

    public void setRev_useful(int rev_useful) {
        this.rev_useful = rev_useful;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}


