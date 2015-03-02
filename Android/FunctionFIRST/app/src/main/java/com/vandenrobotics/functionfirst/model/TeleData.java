package com.vandenrobotics.functionfirst.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Programming701-A on 1/31/2015.
 */
public class TeleData implements Parcelable {

    public ArrayList<Stack> stacks;         // all the stacks for the match
    public ArrayList<StepStack> stepStacks; // all of the stacks made on the step for the match (up to 3 that this robot can contribute to)

    public int totesFromChute;
    public int litterFromChute;
    public int totesFromLandfill;
    public int totesFromStep;
    public int litterToLandfill;
    public int containersUpright;
    public int totesUpright;

    public TeleData(){
        stacks = new ArrayList<>();
        stepStacks = new ArrayList<>();
        totesFromChute = 0;
        litterFromChute = 0;
        totesFromLandfill = 0;
        totesFromStep = 0;
        litterToLandfill = 0;
        containersUpright = 0;
        totesUpright = 0;
    }

    public TeleData(String string){
        this();
    }

    public TeleData(TeleData teleData) {
        this();

        stacks = teleData.stacks;
        stepStacks = teleData.stepStacks;
        totesFromChute = teleData.totesFromChute;
        litterFromChute = teleData.totesFromLandfill;
        totesFromLandfill = teleData.totesFromLandfill;
        totesFromStep = teleData.totesFromStep;
        litterToLandfill = teleData.litterToLandfill;
        containersUpright = teleData.containersUpright;
        totesUpright = teleData.totesUpright;
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
