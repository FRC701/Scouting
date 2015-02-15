package com.vandenrobotics.functionfirst.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AutoData implements Parcelable {

    public boolean hadAuto;

    public int totesToAuto;
    public int containersToAuto;
    public int containersKnockedOver;
    public int containersFromStep;
    public int totesFromLandfill;
    public int totesStacked;

    public boolean[] autoStack;

    public boolean endInAuto;
    public boolean hadOther;


    public AutoData(){
        hadAuto = false;
        totesToAuto = 0;
        containersToAuto = 0;
        containersKnockedOver = 0;
        containersFromStep = 0;
        totesFromLandfill = 0;
        totesStacked = 0;

        autoStack = new boolean[3];
        for(int i =0; i < autoStack.length; i++)
            autoStack[i] = false;

        endInAuto = false;
        hadOther = false;
    }

    public AutoData(String string){
        this();
        try{
            String[] dataString = string.split(",");

            int[] data = new int[dataString.length];

            try{
                for(int i = 0; i < data.length; i++)
                    data[i] = Integer.parseInt(dataString[i]);
            } catch (NumberFormatException e){
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }

            int index = 0;

            hadAuto = (data[index]==1);
            index += 1;
            totesToAuto = data[index];
            index += 1;
            containersToAuto = data[index];
            index += 1;
            containersKnockedOver = data[index];
            index += 1;
            containersFromStep = data[index];
            index += 1;
            totesFromLandfill = data[index];
            index += 1;
            totesStacked = data[index];
            index += 1;

            for(int i = 0; i < autoStack.length; i++) {
                autoStack[i] = (data[index] == 1);
                index += 1;
            }

            endInAuto = (data[index]==1);
            index += 1;
            hadOther = (data[index]==1);

        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public AutoData(AutoData autoData){
        hadAuto = autoData.hadAuto;
    }

    @Override
    public String toString(){
        int tempAuto = hadAuto? 1 : 0;
        int tempStackBase = autoStack[0]? 1 : 0;
        int tempStackMid = autoStack[1]? 1 : 0;
        int tempStackTop = autoStack[2]? 1 : 0;
        int tempEndAuto = endInAuto? 1 : 0;
        int tempOther = hadOther? 1 : 0;

        return tempAuto+","+totesToAuto+","+containersToAuto+","+
                containersKnockedOver+","+containersFromStep+","+
                totesFromLandfill+","+totesStacked+","+
                tempStackBase+","+tempStackMid+","+tempStackTop+","+
                tempEndAuto+","+tempOther;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel arg0, int arg1) {
        // TODO Auto-generated method stub

    }
}
