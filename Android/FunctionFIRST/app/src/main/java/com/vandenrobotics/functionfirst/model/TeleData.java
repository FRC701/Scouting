package com.vandenrobotics.functionfirst.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Programming701-A on 1/31/2015.
 */
public class TeleData implements Parcelable {

    public TeleData(){

    }

    public TeleData(String string){

    }

    public TeleData(TeleData teleData) {
        this();
    }

    @Override
    public String toString(){
        return null;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel arg0, int arg1) {
        // TODO Auto-generated method stub
        arg0.writeString(this.toString());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public TeleData createFromParcel(Parcel in){
            return new TeleData(in.readString());
        }

        public TeleData[] newArray(int size){
            return new TeleData[size];
        }
    };
}
