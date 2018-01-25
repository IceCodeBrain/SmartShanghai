package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

/**
 * Created by Pithou on 05/04/2016.
 */
public class ModelTicket {

    public String show_name;
    public String show_prevpix;
    public String show_urlname;
    public String show_datetime;
    public String name_en;

    public ModelTicket() {
    }

    public ModelTicket(String show_name, String show_prevpix, String show_urlname, String show_datetime, String name_en) {
        this.show_name = show_name;
        this.show_prevpix = show_prevpix;
        this.show_urlname = show_urlname;
        this.show_datetime = show_datetime;
        this.name_en = name_en;
    }

    public String getShow_name() {
        return show_name;
    }

    public void setShow_name(String show_name) {
        this.show_name = show_name;
    }

    public String getShow_prevpix() {
        return show_prevpix;
    }

    public void setShow_prevpix(String show_prevpix) {
        this.show_prevpix = show_prevpix;
    }

    public String getShow_urlname() {
        return show_urlname;
    }

    public void setShow_urlname(String show_urlname) {
        this.show_urlname = show_urlname;
    }

    public String getShow_datetime() {
        return show_datetime;
    }

    public void setShow_datetime(String show_datetime) {
        this.show_datetime = show_datetime;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }
}
