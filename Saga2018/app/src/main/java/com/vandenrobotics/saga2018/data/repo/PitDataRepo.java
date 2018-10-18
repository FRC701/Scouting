package com.vandenrobotics.saga2018.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vandenrobotics.saga2018.data.DatabaseManager;
import com.vandenrobotics.saga2018.data.model.PitData;

import java.util.ArrayList;

/**
 * Created by Programming701-A on 1/17/2018.
 */

public class PitDataRepo {

    public static String createTable() {
        return "CREATE TABLE " + PitData.TABLE + " ( "
                + PitData.KEY_TeamNum + " INTEGER PRIMARY KEY , "
                + PitData.KEY_AutoBaseline + " INTEGER , "
                + PitData.KEY_AutoCubeInSwitch + " INTEGER , "
                + PitData.KEY_AutoCubeInScale + " INTEGER , "
                + PitData.KEY_AutoCubeInExchange + " INTEGER , "
                + PitData.KEY_AutoOther + " INTEGER , "
                + PitData.KEY_CycleSwitches + " INTEGER , "
                + PitData.KEY_CycleGround + " INTEGER , "
                + PitData.KEY_CyclePortal + " INTEGER , "
                + PitData.KEY_Climb + " TEXT , "
                + PitData.KEY_GetSwitch + " INTEGER , "
                + PitData.KEY_GetScale + " INTEGER , "
                + PitData.KEY_FloorPickUp + " INTEGER , "
                + PitData.KEY_IntakeAndMech + " TEXT , "
                + PitData.KEY_DriveTrain + " TEXT , "
                + PitData.KEY_Lang + " TEXT , "
                + PitData.KEY_Comments + " TEXT , "
                + PitData.KEY_Speed + " TEXT ) ";
    }

    public int insert (PitData pitData){
        int pitDataId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(PitData.KEY_TeamNum, pitData.getTeamNum());
        values.put(PitData.KEY_AutoBaseline, pitData.getAutoBaseline());
        values.put(PitData.KEY_AutoCubeInSwitch, pitData.getAutoCubeInSwitch());
        values.put(PitData.KEY_AutoCubeInScale, pitData.getAutoCubeInScale());
        values.put(PitData.KEY_AutoCubeInExchange, pitData.getAutoCubeInExchange());
        values.put(PitData.KEY_AutoOther, pitData.getAutoOther());
        values.put(PitData.KEY_CycleSwitches, pitData.getCycleSwitches());
        values.put(PitData.KEY_CycleGround, pitData.getCycleGround());
        values.put(PitData.KEY_CyclePortal, pitData.getCyclePortal());
        values.put(PitData.KEY_Climb, pitData.getClimb());
        values.put(PitData.KEY_GetSwitch, pitData.getGetSwitch());
        values.put(PitData.KEY_GetScale, pitData.getGetScale());
        values.put(PitData.KEY_FloorPickUp, pitData.getFloorPickUp());
        values.put(PitData.KEY_IntakeAndMech, pitData.getIntakeAndMech());
        values.put(PitData.KEY_DriveTrain, pitData.getDriveTrain());
        values.put(PitData.KEY_Lang, pitData.getLang());
        values.put(PitData.KEY_Comments, pitData.getComments());
        values.put(PitData.KEY_Speed, pitData.getSpeed());

        pitDataId = (int)db.insertWithOnConflict(PitData.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        DatabaseManager.getInstance().closeDatabase();
        return pitDataId;
    }
    public int update (PitData pitData){
        int pitDataId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(PitData.KEY_AutoBaseline, pitData.getAutoBaseline());
        values.put(PitData.KEY_AutoCubeInSwitch, pitData.getAutoCubeInSwitch());
        values.put(PitData.KEY_AutoCubeInScale, pitData.getAutoCubeInScale());
        values.put(PitData.KEY_AutoCubeInExchange, pitData.getAutoCubeInExchange());
        values.put(PitData.KEY_AutoOther, pitData.getAutoOther());
        values.put(PitData.KEY_CycleGround, pitData.getCycleGround());
        values.put(PitData.KEY_CyclePortal, pitData.getCyclePortal());
        values.put(PitData.KEY_CycleSwitches, pitData.getCycleSwitches());
        values.put(PitData.KEY_Climb, pitData.getClimb());
        values.put(PitData.KEY_GetSwitch, pitData.getGetSwitch());
        values.put(PitData.KEY_GetScale, pitData.getGetScale());
        values.put(PitData.KEY_FloorPickUp, pitData.getFloorPickUp());
        values.put(PitData.KEY_IntakeAndMech, pitData.getIntakeAndMech());
        values.put(PitData.KEY_DriveTrain, pitData.getDriveTrain());
        values.put(PitData.KEY_Lang, pitData.getLang());
        values.put(PitData.KEY_Comments, pitData.getComments());
        values.put(PitData.KEY_Speed, pitData.getSpeed());

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
        String selectQuery = " SELECT PitData." + PitData.KEY_AutoBaseline
                + ", PitData." + PitData.KEY_AutoCubeInSwitch
                + ", PitData." + PitData.KEY_AutoCubeInScale
                + ", PitData." + PitData.KEY_AutoCubeInExchange
                + ", PitData." + PitData.KEY_AutoOther
                + ", PitData." + PitData.KEY_CycleGround
                + ", PitData." + PitData.KEY_CyclePortal
                + ", PitData." + PitData.KEY_CycleSwitches
                + ", PitData." + PitData.KEY_Climb
                + ", PitData." + PitData.KEY_GetScale
                + ", PitData." + PitData.KEY_GetSwitch
                + ", PitData." + PitData.KEY_FloorPickUp
                + ", PitData." + PitData.KEY_IntakeAndMech
                + ", PitData." + PitData.KEY_DriveTrain
                + ", PitData." + PitData.KEY_Lang
                + ", PitData." + PitData.KEY_Comments
                + ", PitData." + PitData.KEY_Speed
                + " FROM " + PitData.TABLE
                + " WHERE PitData." + PitData.KEY_TeamNum + " = " + teamNum;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            pitData.setAutoBaseline(cursor.getInt(cursor.getColumnIndex(PitData.KEY_AutoBaseline)));
            pitData.setAutoCubeInSwitch(cursor.getInt(cursor.getColumnIndex(PitData.KEY_AutoCubeInSwitch)));
            pitData.setAutoCubeInScale(cursor.getInt(cursor.getColumnIndex(PitData.KEY_AutoCubeInScale)));
            pitData.setAutoCubeInExchange(cursor.getInt(cursor.getColumnIndex(PitData.KEY_AutoCubeInExchange)));
            pitData.setAutoOther(cursor.getInt(cursor.getColumnIndex(PitData.KEY_AutoOther)));
            pitData.setCycleGround(cursor.getInt(cursor.getColumnIndex(PitData.KEY_CycleGround)));
            pitData.setCyclePortal(cursor.getInt(cursor.getColumnIndex(PitData.KEY_CyclePortal)));
            pitData.setCycleSwitches(cursor.getInt(cursor.getColumnIndex(PitData.KEY_CycleSwitches)));
            pitData.setClimb(cursor.getString(cursor.getColumnIndex(PitData.KEY_Climb)));
            pitData.setGetSwitch(cursor.getInt(cursor.getColumnIndex(PitData.KEY_GetSwitch)));
            pitData.setGetScale(cursor.getInt(cursor.getColumnIndex(PitData.KEY_GetScale)));
            pitData.setFloorPickUp(cursor.getInt(cursor.getColumnIndex(PitData.KEY_FloorPickUp)));
            pitData.setIntakeAndMech(cursor.getString(cursor.getColumnIndex(PitData.KEY_IntakeAndMech)));
            pitData.setDriveTrain(cursor.getString(cursor.getColumnIndex(PitData.KEY_DriveTrain)));
            pitData.setLang(cursor.getString(cursor.getColumnIndex(PitData.KEY_Lang)));
            pitData.setComments(cursor.getString(cursor.getColumnIndex(PitData.KEY_Comments)));
            pitData.setSpeed(cursor.getString(cursor.getColumnIndex(PitData.KEY_Speed)));
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return pitData;
    }
}
