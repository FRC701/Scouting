package com.vandenrobotics.saga.data.model;

/**
 * Created by Programming701-A on 1/17/2018.
 */

public class PitData {

    //hey if i did this wrong i'm sorry

    public static final String TABLE = "PitData";
    public static final String KEY_TeamNum = "TeamNumber";
    public static final String KEY_IntakeAndMech = "TypeOfIntakeAndMech";
    public static final String KEY_DriveTrain = "TypeOfDriveTrain";
    public static final String KEY_Speed = "AverageSpeed";
    public static final String KEY_Lang = "ProgrammingLanguage";
    public static final String KEY_Comments = "Comments";
    //i did everything after here
    public static final String KEY_DriveBlindly = "DriveBlindly";
    public static final String KEY_Auto = "Auto";
    public static final String KEY_Vision = "Vision";
    public static final String KEY_Camera = "Camera";
    public static final String KEY_Other = "Other";
    public static final String KEY_StartLevel1 = "StartLevel1";
    public static final String KEY_StartLevel2 = "StartLevel2";
    public static final String KEY_RobotCargo = "RobotCargo";
    public static final String KEY_RobotHatch = "RobotHatch";
    public static final String KEY_CargoShipCargo = "CargoShipCargo";
    public static final String KEY_CargoShipHatch = "CargoShipHatch";
    public static final String KEY_HatchInCargoShip = "HatchInCargoShip";
    public static final String KEY_CargoInCargoShip = "CargoInCargoShip";
    public static final String KEY_HatchInRocket = "HatchInRocket";
    public static final String KEY_CargoInRocket = "CargoInRocket";
    public static final String KEY_IntakeHatch = "IntakeHatch";
    public static final String KEY_IntakeCargo = "IntakeCargo";



    private int teamNum;
    private String intakeAndMech;
    private String driveTrain;
    private String speed;
    private String lang;
    private String comments;
    //and after here
    private int driveBlindly;
    private int auto;
    private int vision;
    private int camera;
    private int other;
    private int startLevel1;
    private int startLevel2;
    private int robotCargo;
    private int robotHatch;
    private int cargoShipCargo;
    private int cargoShipHatch;
    private int hatchInRocket;
    private int cargoInRocket;
    private int hatchInCargoShip;
    private int cargoInCargoShip;
    private int intakeHatch;
    private int intakeCargo;



    public PitData(int team){
        teamNum = team;
        intakeAndMech = "";
        driveTrain = "";
        lang = "";
        comments = "";
        driveBlindly = 0;
        auto = 0;
        vision = 0;
        camera = 0;
        other = 0;
        startLevel1 = 0;
        startLevel2 = 0;
        robotCargo = 0;
        robotHatch = 0;
        cargoShipCargo = 0;
        cargoShipHatch = 0;
        hatchInRocket = 0;
        cargoInRocket = 0;
        hatchInCargoShip = 0;
        cargoInCargoShip = 0;
        intakeHatch = 0;
        intakeCargo = 0;

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

//lol kaylani work after here

    public int getDriveBlindly(){ return driveBlindly;}
    public void setDriveBlindly(int b){ driveBlindly = b; }

    public int getAuto(){return auto; }
    public void setAuto(int b) { auto = b; }

    public int getVision(){return vision; }
    public void setVision(int b) { vision = b; }

    public int getCamera(){return camera; }
    public void setCamera(int b) { camera = b; }

    public int getOther(){return other; }
    public void setOther(int b) { other = b; }

    public int getStartLevel1 (){return startLevel1; }
    public void setStartLevel1(int b) { startLevel1 = b; }

    public int getStartLevel2(){return startLevel2; }
    public void setStartLevel2(int b) { startLevel2 = b; }

    public int getRobotCargo(){return robotCargo; }
    public void setRobotCargo(int b) { robotCargo = b; }

    public int getRobotHatch(){return robotHatch; }
    public void setRobotHatch(int b) { robotHatch = b; }

    public int getCargoShipCargo(){return cargoShipCargo; }
    public void setCargoShipCargo(int b) { cargoShipCargo = b; }

    public int getCargoShipHatch(){return cargoShipHatch; }
    public void setCargoShipHatch(int b) { cargoShipHatch = b; }

    public int getHatchInRocket(){return hatchInRocket; }
    public void setHatchInRocket(int b) { hatchInRocket = b; }

    public int getCargoInRocket(){return cargoInRocket; }
    public void setCargoInRocket(int b) { cargoInRocket = b; }

    public int getHatchInCargoShip(){return hatchInCargoShip; }
    public void setHatchInCargoShip(int b) { hatchInCargoShip = b; }

    public int getCargoInCargoShip(){return cargoInCargoShip; }
    public void setCargoInCargoShip(int b) { cargoInCargoShip = b; }

    public int getIntakeHatch(){return intakeHatch; }
    public void setIntakeHatch(int b) { intakeHatch = b; }

    public int getIntakeCargo(){return intakeCargo; }
    public void setIntakeCargo(int b) { intakeCargo = b; }



}

