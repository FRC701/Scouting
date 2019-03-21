package com.vandenrobotics.saga.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vandenrobotics.saga.data.DatabaseManager;
import com.vandenrobotics.saga.data.model.PitData;

import java.util.ArrayList;

/**
 * Created by Programming701-A on 1/17/2018.
 */

public class PitDataRepo {

    public static String createTable() {
        return "CREATE TABLE " + PitData.TABLE + " ( "
                + PitData.KEY_TeamNum + " INTEGER PRIMARY KEY , "
                + PitData.KEY_Auto + " INTEGER , "
                + PitData.KEY_DriveBlindly + " INTEGER , "
                + PitData.KEY_Vision + " INTEGER , "
                + PitData.KEY_Camera + " INTEGER , "
                + PitData.KEY_Other + " INTEGER , "
                + PitData.KEY_StartLevel1 + " INTEGER , "
                + PitData.KEY_StartLevel2 + " INTEGER , "
                + PitData.KEY_RobotCargo + " INTEGER , "
                + PitData.KEY_RobotHatch + " INTEGER , "
                + PitData.KEY_CargoShipCargo + " INTEGER , "
                + PitData.KEY_CargoShipHatch + " INTEGER , "
                + PitData.KEY_HatchInCargoShip + " INTEGER , "
                + PitData.KEY_CargoInCargoShip + " INTEGER , "
                + PitData.KEY_HatchInRocket + " INTEGER , "
                + PitData.KEY_CargoInRocket + " INTEGER , "
                + PitData.KEY_IntakeHatch + " INTEGER , "
                + PitData.KEY_IntakeCargo + " INTEGER , "
                + PitData.KEY_ReachFirstPlatform + " INTEGER , "
                + PitData.KEY_ReachSecondPlatform+ " INTEGER , "
                + PitData.KEY_ReachThirdPlatform + " INTEGER , "
                + PitData.KEY_ScoreBottom + " INTEGER , "
                + PitData.KEY_ScoreMiddle + " INTEGER , "
                + PitData.KEY_ScoreTop + " INTEGER , "
                + PitData.KEY_IntakeAndMech + " TEXT , "
                + PitData.KEY_DriveTrain + " TEXT , "
                + PitData.KEY_Lang + " TEXT , "
                + PitData.KEY_Comments + " TEXT , "
                + PitData.KEY_Speed + " TEXT ) "
                + PitData.KEY_CoDriverExperience + " TEXT ) "
                + PitData.KEY_DriverExperience + " TEXT ) "
                + PitData.KEY_Climb + " TEXT ) ";
    }

    public int insert (PitData pitData){
        int pitDataId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(PitData.KEY_TeamNum, pitData.getTeamNum());
        values.put(PitData.KEY_IntakeAndMech, pitData.getIntakeAndMech());
        values.put(PitData.KEY_DriveTrain, pitData.getDriveTrain());
        values.put(PitData.KEY_Lang, pitData.getLang());
        values.put(PitData.KEY_Comments, pitData.getComments());
        values.put(PitData.KEY_Speed, pitData.getSpeed());
        values.put(PitData.KEY_DriverExperience, pitData.getDriverExperience());
        values.put(PitData.KEY_CoDriverExperience, pitData.getCoDriverExperience());
        values.put(PitData.KEY_Climb, pitData.getClimb());
        values.put(PitData.KEY_DriveBlindly, pitData.getDriveBlindly());
        values.put(PitData.KEY_Auto, pitData.getAuto());
        values.put(PitData.KEY_Vision, pitData.getVision());
        values.put(PitData.KEY_Camera, pitData.getCamera());
        values.put(PitData.KEY_Other, pitData.getOther());
        values.put(PitData.KEY_StartLevel1, pitData.getStartLevel1());
        values.put(PitData.KEY_StartLevel2, pitData.getStartLevel2());
        values.put(PitData.KEY_RobotCargo, pitData.getRobotCargo());
        values.put(PitData.KEY_RobotHatch, pitData.getRobotHatch());
        values.put(PitData.KEY_CargoShipCargo, pitData.getCargoShipCargo());
        values.put(PitData.KEY_CargoShipHatch, pitData.getCargoShipHatch());
        values.put(PitData.KEY_HatchInCargoShip, pitData.getHatchInCargoShip());
        values.put(PitData.KEY_CargoInCargoShip, pitData.getCargoInCargoShip());
        values.put(PitData.KEY_HatchInRocket, pitData.getHatchInRocket());
        values.put(PitData.KEY_CargoInRocket, pitData.getCargoInRocket());
        values.put(PitData.KEY_IntakeHatch, pitData.getIntakeHatch());
        values.put(PitData.KEY_IntakeCargo, pitData.getIntakeCargo());
        values.put(PitData.KEY_ReachFirstPlatform, pitData.getReachFirstPlatform());
        values.put(PitData.KEY_ReachSecondPlatform, pitData.getReachSecondPlatform());
        values.put(PitData.KEY_ReachThirdPlatform, pitData.getReachThirdPlatform());
        values.put(PitData.KEY_ScoreBottom, pitData.getScoreBottom());
        values.put(PitData.KEY_ScoreMiddle, pitData.getScoreMiddle());
        values.put(PitData.KEY_ScoreTop, pitData.getScoreTop());
        values.put(PitData.KEY_CoDriverExperience, pitData.getCoDriverExperience());
        values.put(PitData.KEY_DriverExperience, pitData.getDriverExperience());
        values.put(PitData.KEY_Climb, pitData.getClimb());

        pitDataId = (int)db.insertWithOnConflict(PitData.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        DatabaseManager.getInstance().closeDatabase();
        return pitDataId;
    }
    public int update (PitData pitData){
        int pitDataId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(PitData.KEY_IntakeAndMech, pitData.getIntakeAndMech());
        values.put(PitData.KEY_DriveTrain, pitData.getDriveTrain());
        values.put(PitData.KEY_Lang, pitData.getLang());
        values.put(PitData.KEY_Comments, pitData.getComments());
        values.put(PitData.KEY_Speed, pitData.getSpeed());
        values.put(PitData.KEY_DriverExperience, pitData.getDriverExperience());
        values.put(PitData.KEY_CoDriverExperience, pitData.getCoDriverExperience());
        values.put(PitData.KEY_Climb, pitData.getClimb());
        values.put(PitData.KEY_DriveBlindly, pitData.getDriveBlindly());
        values.put(PitData.KEY_Auto, pitData.getAuto());
        values.put(PitData.KEY_Vision, pitData.getVision());
        values.put(PitData.KEY_Camera, pitData.getCamera());
        values.put(PitData.KEY_Other, pitData.getOther());
        values.put(PitData.KEY_StartLevel1, pitData.getStartLevel1());
        values.put(PitData.KEY_StartLevel2, pitData.getStartLevel2());
        values.put(PitData.KEY_RobotCargo, pitData.getRobotCargo());
        values.put(PitData.KEY_RobotHatch, pitData.getRobotHatch());
        values.put(PitData.KEY_CargoShipCargo, pitData.getCargoShipCargo());
        values.put(PitData.KEY_CargoShipHatch, pitData.getCargoShipHatch());
        values.put(PitData.KEY_HatchInCargoShip, pitData.getHatchInCargoShip());
        values.put(PitData.KEY_CargoInCargoShip, pitData.getCargoInCargoShip());
        values.put(PitData.KEY_HatchInRocket, pitData.getHatchInRocket());
        values.put(PitData.KEY_CargoInRocket, pitData.getCargoInRocket());
        values.put(PitData.KEY_IntakeHatch, pitData.getIntakeHatch());
        values.put(PitData.KEY_IntakeCargo, pitData.getIntakeCargo());
        values.put(PitData.KEY_ReachFirstPlatform, pitData.getReachFirstPlatform());
        values.put(PitData.KEY_ReachSecondPlatform, pitData.getReachSecondPlatform());
        values.put(PitData.KEY_ReachThirdPlatform, pitData.getReachThirdPlatform());
        values.put(PitData.KEY_ScoreBottom, pitData.getScoreBottom());
        values.put(PitData.KEY_ScoreMiddle, pitData.getScoreMiddle());
        values.put(PitData.KEY_ScoreTop, pitData.getScoreTop());
        values.put(PitData.KEY_CoDriverExperience, pitData.getCoDriverExperience());
        values.put(PitData.KEY_DriverExperience, pitData.getDriverExperience());
        values.put(PitData.KEY_Climb, pitData.getClimb());

        pitDataId = (int)db.update(PitData.TABLE, values, PitData.KEY_TeamNum + " = " + pitData.getTeamNum(),null);
        DatabaseManager.getInstance().closeDatabase();
        return pitDataId;
    }
    public ArrayList<Integer> getTeams(){
        ArrayList<Integer> teams = new ArrayList<>();
        teams.add(0);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = " SELECT PitData." + PitData.KEY_TeamNum
                + " FROM " +PitData.TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                teams.add(cursor.getInt(cursor.getColumnIndex(PitData.KEY_TeamNum)));
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return teams;
    }
    public PitData getTeamData(int teamNum){
        PitData pitData = new PitData(teamNum);

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = " SELECT PitData." +PitData.KEY_TeamNum
                + ", PitData." + PitData.KEY_Auto
                + ", PitData." + PitData.KEY_IntakeAndMech
                + ", PitData." + PitData.KEY_DriveTrain
                + ", PitData." + PitData.KEY_Lang
                + ", PitData." + PitData.KEY_Comments
                + ", PitData." + PitData.KEY_Speed
                + ", PitData." + PitData.KEY_DriverExperience
                + ", PitData." + PitData.KEY_CoDriverExperience
                + ", PitData." + PitData.KEY_Climb
                + ", PitData." + PitData.KEY_DriveBlindly
                + ", PitData." + PitData.KEY_Auto
                + ", PitData." + PitData.KEY_Vision
                + ", PitData." + PitData.KEY_Camera
                + ", PitData." + PitData.KEY_Other
                + ", PitData." + PitData.KEY_StartLevel1
                + ", PitData." + PitData.KEY_StartLevel2
                + ", PitData." + PitData.KEY_RobotCargo
                + ", PitData." + PitData.KEY_RobotHatch
                + ", PitData." + PitData.KEY_CargoShipCargo
                + ", PitData." + PitData.KEY_CargoShipHatch
                + ", PitData." + PitData.KEY_HatchInCargoShip
                + ", PitData." + PitData.KEY_CargoInCargoShip
                + ", PitData." + PitData.KEY_HatchInRocket
                + ", PitData." + PitData.KEY_CargoInRocket
                + ", PitData." + PitData.KEY_IntakeHatch
                + ", PitData." + PitData.KEY_IntakeCargo
                + ", PitData." + PitData.KEY_ReachFirstPlatform
                + ", PitData." + PitData.KEY_ReachSecondPlatform
                + ", PitData." + PitData.KEY_ReachThirdPlatform
                + ", PitData." + PitData.KEY_ScoreBottom
                + ", PitData." + PitData.KEY_ScoreMiddle
                + ", PitData." + PitData.KEY_ScoreTop
                + ", PitData." + PitData.KEY_CoDriverExperience
                + ", PitData." + PitData.KEY_DriverExperience
                + ", PitData." + PitData.KEY_Climb
                + " FROM " + PitData.TABLE
                + " WHERE PitData." + PitData.KEY_TeamNum + " = " + teamNum;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            pitData.setAuto(cursor.getInt(cursor.getColumnIndex(PitData.KEY_Auto)));
            pitData.setIntakeAndMech(cursor.getString(cursor.getColumnIndex(PitData.KEY_IntakeAndMech)));
            pitData.setDriveTrain(cursor.getString(cursor.getColumnIndex(PitData.KEY_DriveTrain)));
            pitData.setLang(cursor.getString(cursor.getColumnIndex(PitData.KEY_Lang)));
            pitData.setComments(cursor.getString(cursor.getColumnIndex(PitData.KEY_Comments)));
            pitData.setSpeed(cursor.getString(cursor.getColumnIndex(PitData.KEY_Speed)));
            pitData.setSpeed(cursor.getString(cursor.getColumnIndex(PitData.KEY_DriverExperience)));
            pitData.setSpeed(cursor.getString(cursor.getColumnIndex(PitData.KEY_CoDriverExperience)));
            pitData.setSpeed(cursor.getString(cursor.getColumnIndex(PitData.KEY_Climb)));
            pitData.setDriveBlindly(cursor.getInt(cursor.getColumnIndex(PitData.KEY_DriveBlindly)));
            pitData.setVision(cursor.getInt(cursor.getColumnIndex(PitData.KEY_Vision)));
            pitData.setCamera(cursor.getInt(cursor.getColumnIndex(PitData.KEY_Camera)));
            pitData.setOther(cursor.getInt(cursor.getColumnIndex(PitData.KEY_Other)));
            pitData.setStartLevel1(cursor.getInt(cursor.getColumnIndex(PitData.KEY_StartLevel1)));
            pitData.setStartLevel2(cursor.getInt(cursor.getColumnIndex(PitData.KEY_StartLevel2)));
            pitData.setRobotCargo(cursor.getInt(cursor.getColumnIndex(PitData.KEY_RobotCargo)));
            pitData.setRobotHatch(cursor.getInt(cursor.getColumnIndex(PitData.KEY_RobotHatch)));
            pitData.setCargoShipCargo(cursor.getInt(cursor.getColumnIndex(PitData.KEY_CargoShipCargo)));
            pitData.setCargoShipHatch(cursor.getInt(cursor.getColumnIndex(PitData.KEY_CargoShipHatch)));
            pitData.setHatchInCargoShip(cursor.getInt(cursor.getColumnIndex(PitData.KEY_HatchInCargoShip)));
            pitData.setCargoInCargoShip(cursor.getInt(cursor.getColumnIndex(PitData.KEY_CargoInCargoShip)));
            pitData.setHatchInRocket(cursor.getInt(cursor.getColumnIndex(PitData.KEY_HatchInRocket)));
            pitData.setCargoInRocket(cursor.getInt(cursor.getColumnIndex(PitData.KEY_CargoInRocket)));
            pitData.setIntakeHatch(cursor.getInt(cursor.getColumnIndex(PitData.KEY_IntakeHatch)));
            pitData.setIntakeCargo(cursor.getInt(cursor.getColumnIndex(PitData.KEY_IntakeCargo)));
            pitData.setReachFirstPlatform(cursor.getInt(cursor.getColumnIndex(PitData.KEY_ReachFirstPlatform)));
            pitData.setReachSecondPlatform(cursor.getInt(cursor.getColumnIndex(PitData.KEY_ReachSecondPlatform)));
            pitData.setReachThirdPlatform(cursor.getInt(cursor.getColumnIndex(PitData.KEY_ReachThirdPlatform)));
            pitData.setScoreBottom(cursor.getInt(cursor.getColumnIndex(PitData.KEY_ScoreBottom)));
            pitData.setScoreMiddle(cursor.getInt(cursor.getColumnIndex(PitData.KEY_ScoreMiddle)));
            pitData.setScoreTop(cursor.getInt(cursor.getColumnIndex(PitData.KEY_ScoreTop)));
            pitData.setCoDriverExperience(cursor.getString(cursor.getColumnIndex(PitData.KEY_CoDriverExperience)));
            pitData.setDriverExperience(cursor.getString(cursor.getColumnIndex(PitData.KEY_DriverExperience)));
            pitData.setClimb(cursor.getString(cursor.getColumnIndex(PitData.KEY_Climb)));
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return pitData;
    }
}
