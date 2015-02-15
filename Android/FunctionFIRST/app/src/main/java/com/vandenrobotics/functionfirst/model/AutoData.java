package com.vandenrobotics.functionfirst.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AutoData implements Parcelable {

    public boolean hadAuto;

    public AutoData(){
        hadAuto = false;
    }

    public AutoData(String string){
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

            hadAuto = (data[0]==1);

        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            hadAuto = false;
        } catch (Exception e){
            e.printStackTrace();
            hadAuto = false;
        }
    }

    public AutoData(AutoData autoData){
        hadAuto = autoData.hadAuto;
    }

    @Override
    public String toString(){
        int tempAuto = hadAuto? 1 : 0;

        return tempAuto+"";
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
