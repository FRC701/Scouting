package com.vandenrobotics.tyr2018.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vandenrobotics.tyr2018.data.DatabaseManager;
import com.vandenrobotics.tyr2018.data.model.PitData;
import com.vandenrobotics.tyr2018.data.model.Teams;

import java.util.ArrayList;

import static com.loopj.android.http.AsyncHttpClient.LOG_TAG;
import static com.loopj.android.http.AsyncHttpClient.log;

/**
 * Created by Programming701-A on 1/17/2018.
 */

public class PitDataRepo {
    private PitData pitData;

    public PitDataRepo(){
        pitData = new PitData();
    }

    public static String createTable() {
        return "CREATE TABLE " + PitData.TABLE + "("
                + PitData.KEY_TeamNumber + " INTEGER, "
                + PitData.KEY_AutoBaseline + " INTEGER, "
                + PitData.KEY_AutoCubeInSwitch + " INTEGER, "
                + PitData.KEY_AutoCubeInScale + " INTEGER, "
                + PitData.KEY_AutoCubeInExchange + " INTEGER, "
                + PitData.KEY_AutoOther + " INTEGER, "
                + PitData.KEY_CycleGround + " INTEGER, "
                + PitData.KEY_CyclePortal + " INTEGER, "
                + PitData.KEY_CycleSwitches + " INTEGER, "
                + PitData.KEY_Climb + " TEXT, "
                + PitData.KEY_GetSwitch + " INTEGER, "
                + PitData.KEY_GetScale + " INTEGER, "
                + PitData.KEY_FloorPickUp + " INTEGER, "
                + PitData.KEY_IntakeAndMech + " TEXT, "
                + PitData.KEY_DriveTrain + " TEXT, "
                + PitData.KEY_Lang + " TEXT, "
                + PitData.KEY_Comments + " TEXT, "
                + PitData.KEY_Speed + " TEXT, "
                //makes sure TeamNumber column exists in the Team Table
                + " FOREIGN KEY ( " + PitData.KEY_TeamNumber + " ) REFERENCES " + Teams.TABLE
                + " ( " + Teams.KEY_TeamNumber + " ))";
    }

    public ArrayList<Integer> getTeams(){
        ArrayList<Integer> teams = new ArrayList<>();
        teams.add(0);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = " SELECT PitDataAll." + PitData.KEY_TeamNumber
                + " FROM " +PitData.TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                teams.add(cursor.getInt(cursor.getColumnIndex(PitData.KEY_TeamNumber)));
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return teams;
    }
    public int insert (PitData pitData){
        int pitDataId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(PitData.KEY_TeamNumber, pitData.getTeamNum());
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
    public PitData getTeamData(int TeamNumber){
        PitData pitData = new PitData();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = " SELECT PitDataAll." + PitData.KEY_TeamNumber
                + ", PitDataAll." + PitData.KEY_AutoBaseline
                + ", PitDataAll." + PitData.KEY_AutoCubeInSwitch
                + ", PitDataAll." + PitData.KEY_AutoCubeInScale
                + ", PitDataAll." + PitData.KEY_AutoCubeInExchange
                + ", PitDataAll." + PitData.KEY_AutoOther
                + ", PitDataAll." + PitData.KEY_CycleGround
                + ", PitDataAll." + PitData.KEY_CyclePortal
                + ", PitDataAll." + PitData.KEY_CycleSwitches
                + ", PitDataAll." + PitData.KEY_Climb
                + ", PitDataAll." + PitData.KEY_GetScale
                + ", PitDataAll." + PitData.KEY_GetSwitch
                + ", PitDataAll." + PitData.KEY_FloorPickUp
                + ", PitDataAll." + PitData.KEY_IntakeAndMech
                + ", PitDataAll." + PitData.KEY_DriveTrain
                + ", PitDataAll." + PitData.KEY_Lang
                + ", PitDataAll." + PitData.KEY_Comments
                + ", PitDataAll." + PitData.KEY_Speed
                + " FROM " + PitData.TABLE
                + " WHERE PitDataAll." + PitData.KEY_TeamNumber + " = " + TeamNumber;
        Cursor cursor = db.rawQuery(selectQuery, null);
        log.d(LOG_TAG, " " + TeamNumber);

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
            pitData.setCommetns(cursor.getString(cursor.getColumnIndex(PitData.KEY_Comments)));
            pitData.setSpeed(cursor.getString(cursor.getColumnIndex(PitData.KEY_Speed)));
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return pitData;
    }

}
