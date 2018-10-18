package com.vandenrobotics.saga2018.data.model;

/**
 * Created by Programming701-A on 1/19/2018.
 */

public class MatchInfo {

    public static final String TABLE = "MatchInfo";

    public static final String INDEX = "comp_match_device";

    public static final String KEY_CompId = "CompID";
    public static final String KEY_ScoutType = "Type";
    public static final String KEY_CurrentMatch = "CurrentMatch";
    public static final String KEY_DeviceNum = "DeviceNum";

    private String compId;
    private String scoutType;
    private int currentMatch;
    private int deviceNum;

    public MatchInfo(){
        compId = "None";
        scoutType = "None";
        currentMatch = 0;
        deviceNum = 0;
    }

    public String getCompId(){
        return compId;
    }

    public void setCompId(String s){
        compId = s;
    }

    public String getScoutType(){
        return scoutType;
    }

    public void setScoutType(String s){
        scoutType = s;
    }

    public int getCurrentMatch(){
        return currentMatch;
    }

    public void setCurrentMatch(int i){
        currentMatch = i;
    }

    public void incCurrentMatch(){
        currentMatch++;
    }

    public int getDeviceNum(){
        return deviceNum;
    }

    public  void setDeviceNum(int i){
        deviceNum = i;
    }
}
