package com.smartshanghaiapp.smartshanghaicompany.smartshanghai.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Pithou on 05/04/2016.
 */
public class ModelListOfCollections implements Parcelable {

    public boolean isSuccesful;
    public ArrayList<ModelCollection> data;

    public ModelListOfCollections() {
    }

    public ModelListOfCollections(ArrayList<ModelCollection> data) {
        this.data = data;
    }

    protected ModelListOfCollections(Parcel in) {
        isSuccesful = in.readByte() != 0;
        data = in.createTypedArrayList(ModelCollection.CREATOR);
    }

    public static final Creator<ModelListOfCollections> CREATOR = new Creator<ModelListOfCollections>() {
        @Override
        public ModelListOfCollections createFromParcel(Parcel in) {
            return new ModelListOfCollections(in);
        }

        @Override
        public ModelListOfCollections[] newArray(int size) {
            return new ModelListOfCollections[size];
        }
    };

    public ArrayList<ModelCollection> getData() {
        return data;
    }

    public void setData(ArrayList<ModelCollection> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (isSuccesful ? 1 : 0));
        dest.writeTypedList(data);
    }
}
