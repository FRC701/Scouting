package com.vandenrobotics.saga2018.data.model;

/**
 * Created by Programming701-A on 2/10/2018.
 */

public class Cubes {

    public static final String TABLE = "Cubes";
    public static final String KEY_PRIMARY = "ID";
    public static final String KEY_CompId = "CompID";
    public static final String KEY_MatchNumber = "MatchNumber";
    public static final String KEY_TeamNumber = "TeamNumber";
    public static final String KEY_Balance = "Balance";
    public static final String KEY_Time = "Time";
    public static final String KEY_Phase = "Phase";

    private int Id;
    private String compId;
    private int matchNum;
    private int teamNum;
    private String balance;
    private String time;
    private String phase;

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

    public int getTeamNum(){
        return teamNum;
    }

    public void setTeamNum(int i){
        teamNum = i;
    }

    public String getBalance(){
        return balance;
    }

    public void setBalance(String s){
        balance = s;
    }

    public String getTime(){
        return  time;
    }

    public void setTime(String s){
        time = s;
    }

    public String getPhase(){
        return phase;
    }

    public void setPhase(String s){
        phase = s;
    }

}
