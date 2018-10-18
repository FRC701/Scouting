package com.vandenrobotics.saga2018.data.model;

/**
 * Created by Programming701-A on 2/10/2018.
 */

public class Vault {
    public static final String TABLE = "Vault";

    public static final String KEY_CompId = "CompID";
    public static final String KEY_MatchNum = "MatchNumber";
    public static final String KEY_Alliance = "Alliance";

    public static final String KEY_ForceTime = "ForceTime";
    public static final String KEY_ForceCubes = "ForceCubes";
    public static final String KEY_LevTime = "LevitateTime";
    public static final String KEY_LevCubes = "LevitateCubes";
    public static final String KEY_BoostTime = "BoostTime";
    public static final String KEY_BoostCubes = "BoostCubes";
    public static final String KEY_ForceCubesAtTime = "ForceCubesAtTime";
    public static final String KEY_LevCubesAtTime = "LevitateCubesAtTime";
    public static final String KEY_BoostCubesAtTime = "BoostCubesAtTime";

    private String compId;
    private int matchNum;
    private String alliance;
    private String forceTime;
    private String boostTime;
    private String levitateTime;
    private int forceCubes;
    private int boostCubes;
    private int levitateCubes;
    private int forceCubesAtTime;
    private int levCubesAtTime;
    private int boostCubesAtTime;

    public Vault(){
        compId = "None";
        matchNum = 0;
        alliance = "None";
        forceTime = "";
        forceCubes = 0;
        levitateTime = "";
        levitateCubes = 0;
        boostTime = "";
        boostCubes = 0;
        forceCubesAtTime = 0;
        levCubesAtTime = 0;
        boostCubesAtTime = 0;
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

    public String getAlliance(){
        return alliance;
    }
    public void setAlliance(String s){
        alliance = s;
    }

    public String getForceTime(){
        return forceTime;
    }
    public void setForceTime(String s){
        forceTime = s;
    }

    public int getForceCubes(){
        return forceCubes;
    }
    public void setForceCubes(int i){
        forceCubes = i;
    }

    public String getLevitateTime(){
        return levitateTime;
    }
    public void setLevitateTime(String s){
        levitateTime = s;
    }

    public int getLevitateCubes(){
        return levitateCubes;
    }
    public void setLevitateCubes(int i){
        levitateCubes = i;
    }

    public String getBoostTime(){
        return boostTime;
    }
    public void setBoostTime(String s){
        boostTime = s;
    }

    public int getBoostCubes(){
        return boostCubes;
    }
    public void setBoostCubes(int i){
        boostCubes = i;
    }

    public int getLevitateCubesAtTime(){
        return levCubesAtTime;
    }
    public void setLevitateCubesAtTime(int i){
        levCubesAtTime = i;
    }

    public int getForceCubesAtTime(){
        return forceCubesAtTime;
    }
    public void setForceCubesAtTime(int i){
        forceCubesAtTime = i;
    }

    public int getBoostCubesAtTime(){
        return boostCubesAtTime;
    }
    public void setBoostCubesAtTime(int i){
        boostCubesAtTime = i;
    }
}
