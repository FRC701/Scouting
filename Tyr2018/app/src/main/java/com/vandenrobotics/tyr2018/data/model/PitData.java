package com.vandenrobotics.tyr2018.data.model;

/**
 * Created by Programming701-A on 1/17/2018.
 */

public class PitData {

    public static final String TABLE = "PitDataAll";
    public static final String KEY_TeamNumber = "TeamNumber";
    public static final String KEY_AutoBaseline = "AutoBaseline";
    public static final String KEY_AutoCubeInSwitch = "AutoCubeInSwitch";
    public static final String KEY_AutoCubeInScale = "AutoCubeInScale";
    public static final String KEY_AutoCubeInExchange = "AutoCubeInExchange";
    public static final String KEY_AutoOther = "AutoOther";
    public static final String KEY_CycleGround = "CycleGround";
    public static final String KEY_CyclePortal = "CyclePortal";
    public static final String KEY_CycleSwitches = "CycleSwitches";
    public static final String KEY_GetSwitch = "GetSwitch";
    public static final String KEY_GetScale = "GetScale";
    public static final String KEY_FloorPickUp = "FloorPickUp";
    public static final String KEY_Climb = "Climb";
    public static final String KEY_IntakeAndMech = "IntakeAndMech";
    public static final String KEY_DriveTrain = "DriveTrain";
    public static final String KEY_Lang = "ProgLang";
    public static final String KEY_Comments = "Comments";
    public static final String KEY_Speed = "Speed";

    private int teamNum;
    private int autoBaseline;
    private int autoCubeInSwitch;
    private int autoCubeInScale;
    private int autoCubeInExchange;
    private int autoOther;
    private int cycleGround;
    private int cyclePortal;
    private int cycleSwitches;
    private int getSwitch;
    private int getScale;
    private int floorPickUp;
    private String climb;
    private String intakeAndMech;
    private String driveTrain;
    private String speed;
    private String lang;
    private String comments;

    public int getTeamNum(){
        return teamNum;
    }
    public void setTeamNum(int s){
        teamNum = s;
    }

    public int getAutoBaseline(){
        return autoBaseline;
    }
    public void setAutoBaseline(int s){
        autoBaseline = s;
    }

    public int getAutoCubeInSwitch(){
        return autoCubeInSwitch;
    }
    public void setAutoCubeInSwitch(int s){
        autoCubeInSwitch = s;
    }

    public int getAutoCubeInScale(){
        return autoCubeInScale;
    }
    public void setAutoCubeInScale(int s){
        autoCubeInScale = s;
    }

    public int getAutoCubeInExchange(){
        return autoCubeInExchange;
    }
    public void setAutoCubeInExchange(int s){
        autoCubeInExchange = s;
    }

    public int getAutoOther(){
        return autoOther;
    }
    public void setAutoOther(int s){
        autoOther = s;
    }

    public int getCycleGround(){
        return cycleGround;
    }
    public void setCycleGround(int s){
        cycleGround = s;
    }

    public int getCyclePortal(){
        return cyclePortal;
    }
    public void setCyclePortal(int s){
        cyclePortal = s;
    }

    public int getCycleSwitches(){
        return cycleSwitches;
    }
    public void setCycleSwitches(int s){
        cycleSwitches = s;
    }

    public int getGetSwitch(){
        return getSwitch;
    }
    public void setGetSwitch(int s){
        getSwitch = s;
    }

    public int getGetScale(){
        return getScale;
    }
    public void setGetScale(int s){
        getScale = s;
    }

    public int getFloorPickUp(){
        return floorPickUp;
    }
    public void setFloorPickUp(int s){
        floorPickUp = s;
    }

    public String getClimb(){
        return climb;
    }
    public void setClimb(String s){
         climb = s;
    }

    public String getIntakeAndMech(){
        return intakeAndMech;
    }
    public void setIntakeAndMech(String s){
        intakeAndMech = s;
    }

    public String getDriveTrain(){
        return driveTrain;
    }
    public void setDriveTrain(String s){
        driveTrain = s;
    }

    public String getSpeed(){
        return speed;
    }
    public void setSpeed(String s){
        speed = s;
    }

    public String getLang(){
        return lang;
    }
    public void setLang(String s){
        lang = s;
    }

    public String getComments(){
        return comments;
    }
    public void setCommetns(String s){
        comments = s;
    }
}

