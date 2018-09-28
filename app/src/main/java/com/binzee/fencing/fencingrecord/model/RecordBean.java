package com.binzee.fencing.fencingrecord.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.LitePalSupport;

public class RecordBean extends LitePalSupport implements Parcelable{

    private int id;
    private String blue;
    private String red;
    private int scroBlue;
    private int scroRed;

    public RecordBean(){

    }

    protected RecordBean(Parcel in) {
        id = in.readInt();
        blue = in.readString();
        red = in.readString();
        scroBlue = in.readInt();
        scroRed = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(blue);
        dest.writeString(red);
        dest.writeInt(scroBlue);
        dest.writeInt(scroRed);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RecordBean> CREATOR = new Creator<RecordBean>() {
        @Override
        public RecordBean createFromParcel(Parcel in) {
            return new RecordBean(in);
        }

        @Override
        public RecordBean[] newArray(int size) {
            return new RecordBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlue() {
        return blue;
    }

    public void setBlue(String blue) {
        this.blue = blue;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }

    public int getScroBlue() {
        return scroBlue;
    }

    public void setScroBlue(int scroBlue) {
        this.scroBlue = scroBlue;
    }

    public int getScroRed() {
        return scroRed;
    }

    public void setScroRed(int scroRed) {
        this.scroRed = scroRed;
    }
}
