package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pithou on 22/04/2016.
 */
public class ModelCollection implements Parcelable {

    private int tag_id;
    private String title;
    private String thumb;
    private String teaser;
    private String description;
    private String last_edited;

    public ModelCollection() {
    }

    public ModelCollection(int tag_id, String title, String thumb, String teaser, String description, String last_edited) {
        this.tag_id = tag_id;
        this.title = title;
        this.thumb = thumb;
        this.teaser = teaser;
        this.description = description;
        this.last_edited = last_edited;
    }


    protected ModelCollection(Parcel in) {
        tag_id = in.readInt();
        title = in.readString();
        thumb = in.readString();
        teaser = in.readString();
        description = in.readString();
        last_edited = in.readString();
    }

    public static final Creator<ModelCollection> CREATOR = new Creator<ModelCollection>() {
        @Override
        public ModelCollection createFromParcel(Parcel in) {
            return new ModelCollection(in);
        }

        @Override
        public ModelCollection[] newArray(int size) {
            return new ModelCollection[size];
        }
    };

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLast_edited() {
        return last_edited;
    }

    public void setLast_edited(String last_edited) {
        this.last_edited = last_edited;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(tag_id);
        dest.writeString(title);
        dest.writeString(thumb);
        dest.writeString(teaser);
        dest.writeString(description);
        dest.writeString(last_edited);
    }
}

