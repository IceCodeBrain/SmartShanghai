package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Pithou on 22/04/2016.
 */
public class ModelPictureOfGallery implements Parcelable {

    private int pixid;
    private int smartchinaid;
    private int galid;
    private String uptime;

    public ModelPictureOfGallery() {
    }

    public ModelPictureOfGallery(int pixid, int smartchinaid, int galid, String uptime) {
        this.pixid = pixid;
        this.smartchinaid = smartchinaid;
        this.galid = galid;
        this.uptime = uptime;
    }

    protected ModelPictureOfGallery(Parcel in) {
        pixid = in.readInt();
        smartchinaid = in.readInt();
        galid = in.readInt();
        uptime = in.readString();
    }

    public static final Creator<ModelPictureOfGallery> CREATOR = new Creator<ModelPictureOfGallery>() {
        @Override
        public ModelPictureOfGallery createFromParcel(Parcel in) {
            return new ModelPictureOfGallery(in);
        }

        @Override
        public ModelPictureOfGallery[] newArray(int size) {
            return new ModelPictureOfGallery[size];
        }
    };

    public int getPixid() {
        return pixid;
    }

    public void setPixid(int pixid) {
        this.pixid = pixid;
    }

    public int getSmartchinaid() {
        return smartchinaid;
    }

    public void setSmartchinaid(int smartchinaid) {
        this.smartchinaid = smartchinaid;
    }

    public int getGalid() {
        return galid;
    }

    public void setGalid(int galid) {
        this.galid = galid;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pixid);
        dest.writeInt(smartchinaid);
        dest.writeInt(galid);
        dest.writeString(uptime);
    }
}
