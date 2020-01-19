package com.vandenrobotics.saga2020.data.model;

/**
 * Created by Programming701-A on 1/17/2018.
 */

public class PitData {

    //hey if i did this wrong i'm sorry

    public static final String TABLE = "PitData";
    public static final String KEY_TeamNum = "TeamNumber";
    // start here
    public static final String KEY_IntakeAndMech = "TypeOfIntakeAndMech";
    public static final String KEY_DriveTrain = "TypeOfDriveTrain";
    public static final String KEY_Speed = "AverageSpeed";
    public static final String KEY_Lang = "ProgrammingLanguage";
    public static final String KEY_Comments = "Comments";
    public static final String KEY_DriverExperience = "DriverExperience";
    public static final String KEY_CoDriverExperience = "CoDriverExperience";
    private int teamNum;


    //start here
    private String intakeAndMech;
    private String driveTrain;
    private String speed;
    private String lang;
    private String comments;
    private String driverExperience;
    private String coDriverExperience;

    public PitData(int team){
        teamNum = team;
        intakeAndMech = "";
        driveTrain = "";
        lang = "";
        comments = "";
        driverExperience = "";
        coDriverExperience = "";
    }


    public int getTeamNum(){
        return teamNum;
    }
    public void setTeamNum(int s){
        teamNum = s;
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
    public void setComments(String s){ comments = s; }

    public String getDriverExperience(){
        return driverExperience;
    }
    public void setDriverExperience(String s){ driverExperience = s; }

    public String getCoDriverExperience(){
        return coDriverExperience;
    }
    public void setCoDriverExperience(String s){ coDriverExperience = s; }

}

