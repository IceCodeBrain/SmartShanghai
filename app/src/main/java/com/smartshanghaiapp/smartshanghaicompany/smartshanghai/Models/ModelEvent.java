package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pithou on 05/04/2016.
 */
public class ModelEvent implements Parcelable{

    public String name;
    public String date_start;
    public String date_end;
    public String description_short_nohtml;
    public String prevpix;
    public int venue_id;
    public String venue_name;
    public String smartticket_link;
    public String date_featured;
    public String date_human;
    public double map_x;
    public double map_y;
    public String flyer;
    public int id;

    public ModelEvent() {
    }

    public ModelEvent(String name, String date_start, String date_end, String description_short_nohtml, String prevpix, int venue_id, String venue_name, String smartticket_link, String date_featured, String date_human, double map_x, double map_y, String flyer, int id) {
        this.name = name;
        this.date_start = date_start;
        this.date_end = date_end;
        this.description_short_nohtml = description_short_nohtml;
        this.prevpix = prevpix;
        this.venue_id = venue_id;
        this.venue_name = venue_name;
        this.smartticket_link = smartticket_link;
        this.date_featured = date_featured;
        this.date_human = date_human;
        this.map_x = map_x;
        this.map_y = map_y;
        this.flyer = flyer;
        this.id = id;
    }

    protected ModelEvent(Parcel in) {
        name = in.readString();
        date_start = in.readString();
        date_end = in.readString();
        description_short_nohtml = in.readString();
        prevpix = in.readString();
        venue_id = in.readInt();
        venue_name = in.readString();
        smartticket_link = in.readString();
        date_featured = in.readString();
        date_human = in.readString();
        map_x = in.readDouble();
        map_y = in.readDouble();
        flyer = in.readString();
        id = in.readInt();
    }

    public static final Creator<ModelEvent> CREATOR = new Creator<ModelEvent>() {
        @Override
        public ModelEvent createFromParcel(Parcel in) {
            return new ModelEvent(in);
        }

        @Override
        public ModelEvent[] newArray(int size) {
            return new ModelEvent[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getDescription_short_nohtml() {
        return description_short_nohtml;
    }

    public void setDescription_short_nohtml(String description_short_nohtml) {
        this.description_short_nohtml = description_short_nohtml;
    }

    public String getPrevpix() {
        return prevpix;
    }

    public void setPrevpix(String prevpix) {
        this.prevpix = prevpix;
    }

    public int getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(int venue_id) {
        this.venue_id = venue_id;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getSmartticket_link() {
        return smartticket_link;
    }

    public void setSmartticket_link(String smartticket_link) {
        this.smartticket_link = smartticket_link;
    }

    public String getDate_featured() {
        return date_featured;
    }

    public void setDate_featured(String date_featured) {
        this.date_featured = date_featured;
    }

    public String getDate_human() {
        return date_human;
    }

    public void setDate_human(String date_human) {
        this.date_human = date_human;
    }

    public double getMap_x() {
        return map_x;
    }

    public void setMap_x(double map_x) {
        this.map_x = map_x;
    }

    public double getMap_y() {
        return map_y;
    }

    public void setMap_y(double map_y) {
        this.map_y = map_y;
    }

    public String getFlyer() {
        return flyer;
    }

    public void setFlyer(String flyer) {
        this.flyer = flyer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(date_start);
        dest.writeString(date_end);
        dest.writeString(description_short_nohtml);
        dest.writeString(prevpix);
        dest.writeInt(venue_id);
        dest.writeString(venue_name);
        dest.writeString(smartticket_link);
        dest.writeString(date_featured);
        dest.writeString(date_human);
        dest.writeDouble(map_x);
        dest.writeDouble(map_y);
        dest.writeString(flyer);
        dest.writeInt(id);
    }
}
