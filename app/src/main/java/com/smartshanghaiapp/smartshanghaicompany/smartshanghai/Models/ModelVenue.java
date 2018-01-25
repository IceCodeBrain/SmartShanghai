package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pithou on 13/04/2016.
 */
public class ModelVenue implements Comparable, Parcelable {

    private int id;
    private String name;
    private String address_en;
    private String address_cn;
    private String phone;
    private String hours;
    private Double map_x;
    private Double map_y;
    private int metro_station_id;
    private String distance;
    private String description;
    private String card;
    private String website;
    private int price;
    private String thumb;
    private int reviews_count;
    private float reviews_average;
    private int metro_distance_time;
    private int closed;
    private String chope_widget_url;

    public ModelVenue(int id, String name, String address_en, Double map_x, Double map_y) {
        this.id = id;
        this.name = name;
        this.address_en = address_en;
        this.map_x = map_x;
        this.map_y = map_y;
    }

    public ModelVenue(int id, String name, String address_en, Double map_x, Double map_y, int reviews_count, float reviews_average, int closed) {
        this.id = id;
        this.name = name;
        this.address_en = address_en;
        this.map_x = map_x;
        this.map_y = map_y;
        this.reviews_count = reviews_count;
        this.reviews_average = reviews_average;
        this.closed = closed;

    }

    protected ModelVenue(Parcel in) {
        id = in.readInt();
        name = in.readString();
        address_en = in.readString();
        address_cn = in.readString();
        phone = in.readString();
        hours = in.readString();
        metro_station_id = in.readInt();
        distance = in.readString();
        description = in.readString();
        card = in.readString();
        website = in.readString();
        price = in.readInt();
        thumb = in.readString();
        reviews_count = in.readInt();
        reviews_average = in.readFloat();
        metro_distance_time = in.readInt();
        closed = in.readInt();
    }

    public static final Creator<ModelVenue> CREATOR = new Creator<ModelVenue>() {
        @Override
        public ModelVenue createFromParcel(Parcel in) {
            return new ModelVenue(in);
        }

        @Override
        public ModelVenue[] newArray(int size) {
            return new ModelVenue[size];
        }
    };

    public int getClosed() {
        return closed;
    }

    public void setClosed(int closed) {
        this.closed = closed;
    }

    public ModelVenue(int id, String name, String address_en, String address_cn, String phone, String hours, Double map_x, Double map_y, int metro_station_id, String distance, String description, String card, String website, int price, String thumb, int reviews_count, float reviews_average, int metro_distance_time, int closed, String chope_widget_url) {
        this.id = id;
        this.name = name;
        this.address_en = address_en;
        this.address_cn = address_cn;
        this.phone = phone;
        this.hours = hours;
        this.map_x = map_x;
        this.map_y = map_y;
        this.metro_station_id = metro_station_id;
        this.distance = distance;
        this.description = description;
        this.card = card;
        this.website = website;
        this.price = price;
        this.thumb = thumb;
        this.reviews_count = reviews_count;
        this.reviews_average = reviews_average;
        this.metro_distance_time = metro_distance_time;
        this.closed = closed;
        this.chope_widget_url = chope_widget_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMetro_distance_time() {
        return metro_distance_time;
    }

    public void setMetro_distance_time(int metro_distance_time) {
        this.metro_distance_time = metro_distance_time;
    }

    public String getChope_widget_url() {
        return chope_widget_url;
    }

    public void setChope_widget_url(String chope_widget_url) {
        this.chope_widget_url = chope_widget_url;
    }

    public ModelVenue(int id, String name, String address_en, Double map_x, Double map_y, String distance, String description, int price, String thumb) {
        this.id = id;
        this.name = name;
        this.address_en = address_en;
        this.map_x = map_x;
        this.map_y = map_y;
        this.distance = distance;
        this.description = description;
        this.price = price;
        this.thumb = thumb;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public float getReviews_average() {
        return reviews_average;
    }

    public void setReviews_average(float reviews_average) {
        this.reviews_average = reviews_average;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress_cn() {
        return address_cn;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public void setAddress_cn(String address_cn) {
        this.address_cn = address_cn;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public int getMetro_station_id() {
        return metro_station_id;
    }

    public void setMetro_station_id(int metro_station_id) {
        this.metro_station_id = metro_station_id;
    }

    public ModelVenue() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Double getMap_x() {
        return map_x;
    }

    public void setMap_x(Double map_x) {
        this.map_x = map_x;
    }

    public Double getMap_y() {
        return map_y;
    }

    public void setMap_y(Double map_y) {
        this.map_y = map_y;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(Object another) {
        ModelVenue f = (ModelVenue) another;
        if (f.distance.equals("no data")) {
            return -1;
        } else if (distance.equals("no data")) {
            return 1;
        } else if (new Double(distance) <= new Double(f.distance)) {
            return -1;
        } else if (new Double(distance) > new Double(f.distance)) {
            return 1;
        }

        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(address_en);
        dest.writeString(address_cn);
        dest.writeString(phone);
        dest.writeString(hours);
        dest.writeInt(metro_station_id);
        dest.writeString(distance);
        dest.writeString(description);
        dest.writeString(card);
        dest.writeString(website);
        dest.writeInt(price);
        dest.writeString(thumb);
        dest.writeInt(reviews_count);
        dest.writeFloat(reviews_average);
        dest.writeInt(metro_distance_time);
        dest.writeInt(closed);
    }
}
