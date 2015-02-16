package com.vandenrobotics.functionfirst.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Programming701-A on 1/31/2015.
 */
public class PostData implements Parcelable {

    public PostData(){

    }

    public PostData(String string){

    }

    public PostData(PostData postData) {
        this();
    }

    @Override
    public String toString(){
        return null;
    }

    @Override
    public int describeContents() {
        // TODO auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel arg0, int arg1) {
        // TODO auto-generated method stub
        arg0.writeString(this.toString());
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public PostData createFromParcel(Parcel in){
            return new PostData(in.readString());
        }

        public PostData[] newArray(int size){
            return new PostData[size];
        }
    };
}
