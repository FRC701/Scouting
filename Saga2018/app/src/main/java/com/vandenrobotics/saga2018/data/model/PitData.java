package com.vandenrobotics.saga2018.data.model;

/**
 * Created by Programming701-A on 1/17/2018.
 */

public class PitData {

    public static final String TABLE = "PitData";
    public static final String KEY_TeamNum = "TeamNumber";
    public static final String KEY_AutoBaseline = "AutoLineInAuto";
    public static final String KEY_AutoCubeInSwitch = "AutoCubeInSwitch";
    public static final String KEY_AutoCubeInScale = "AutoCubeInScale";
    public static final String KEY_AutoCubeInExchange = "AutoCubeInExchange";
    public static final String KEY_AutoOther = "AutoOther";
    public static final String KEY_CycleGround = "CycleGround";
    public static final String KEY_CyclePortal = "CyclePortal";
    public static final String KEY_CycleSwitches = "CycleSwitches";
    public static final String KEY_GetSwitch = "CanGetSwitch";
    public static final String KEY_GetScale = "CanGetScale";
    public static final String KEY_FloorPickUp = "CanPickUpCubesFromFloor";
    public static final String KEY_Climb = "CanClimb";
    public static final String KEY_IntakeAndMech = "TypeOfIntakeAndMech";
    public static final String KEY_DriveTrain = "TypeOfDriveTrain";
    public static final String KEY_Speed = "AverageSpeed";
    public static final String KEY_Lang = "ProgrammingLanguage";
    public static final String KEY_Comments = "Comments";

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

    public PitData(int team){
        teamNum = team;
        autoBaseline = 0;
        autoCubeInSwitch = 0;
        autoCubeInScale = 0;
        autoCubeInExchange = 0;
        autoOther = 0;
        cycleGround = 0;
        cyclePortal = 0;
        cycleSwitches = 0;
        getSwitch = 0;
        getScale = 0;
        floorPickUp = 0;
        climb = "";
        intakeAndMech = "";
        driveTrain = "";
        lang = "";
        comments = "";
    }

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
    public void setComments(String s){
        comments = s;
    }
}

