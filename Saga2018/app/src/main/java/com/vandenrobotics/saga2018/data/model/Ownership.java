package com.vandenrobotics.saga2018.data.model;

/**
 * Created by Programming701-A on 1/20/2018.
 */

public class Ownership {

    public static final String TABLE = "Ownership";
    public static final String KEY_PRIMARY = "ID";
    public static final String KEY_CompId = "CompID";
    public static final String KEY_MatchNumber = "MatchNumber";
    public static final String KEY_Balance = "Balance";
    public static final String KEY_Owner = "Owner";
    public static final String KEY_Time = "Time";

    private int Id;
    private String compId;
    private int matchNum;
    private String balance;
    private String owner;
    private String time;

    public int getId(){
        return Id;
    }

    public void setId(int i){
        Id = i;
    }

    public String getCompId(){
        return compId;
    }

    public void setCompId(String s){
        compId = s;
    }

    public int getMatchNum(){
        return matchNum;
    }

    public void setMatchNum(int i){
        matchNum = i;
    }

    public String getBalance(){
        return balance;
    }

    public void setBalance(String s){
        balance = s;
    }

    public String getOwner(){
        return owner;
    }

    public void setOwner(String s){
        owner = s;
    }

    public String getTime(){
        return  time;
    }

    public void setTime(String s){
        time = s;
    }

}
