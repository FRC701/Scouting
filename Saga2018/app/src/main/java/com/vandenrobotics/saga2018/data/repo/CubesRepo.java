package com.vandenrobotics.saga2018.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vandenrobotics.saga2018.data.DatabaseManager;
import com.vandenrobotics.saga2018.data.model.Competitions;
import com.vandenrobotics.saga2018.data.model.Cubes;
import com.vandenrobotics.saga2018.data.model.Teams;

/**
 * Created by Programming701-A on 2/10/2018.
 */

public class CubesRepo {

    private final String TAG = CubesRepo.class.getSimpleName();

    public static String createTable(){
        return "CREATE TABLE " + Cubes.TABLE + "("
                + Cubes.KEY_PRIMARY + " INTEGER not null PRIMARY KEY AUTOINCREMENT , "
                + Cubes.KEY_CompId + " TEXT not null , "
                + Cubes.KEY_MatchNumber + " INTEGER not null , "
                + Cubes.KEY_TeamNumber + " INTEGER not null , "
                + Cubes.KEY_Balance + " TEXT , "
                + Cubes.KEY_Time + " TEXT , "
                + Cubes.KEY_Phase + " TEXT not null , "
                + " FOREIGN KEY ( " + Cubes.KEY_CompId + " ) REFERENCES " + Competitions.TABLE
                + " ( " + Competitions.KEY_CompId + " ), "
                //makes sure TeamNum column exists in the Team Table
                + " FOREIGN KEY ( " + Cubes.KEY_TeamNumber + " ) REFERENCES " + Teams.TABLE
                + " ( " + Teams.KEY_TeamNum + " ))";
    }

    public int insert(Cubes cubes){
        int cubeId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Cubes.KEY_CompId, cubes.getCompId());
        values.put(Cubes.KEY_MatchNumber, cubes.getMatchNum());
        values.put(Cubes.KEY_TeamNumber, cubes.getTeamNum());
        values.put(Cubes.KEY_Balance, cubes.getBalance());
        values.put(Cubes.KEY_Time, cubes.getTime());
        values.put(Cubes.KEY_Phase, cubes.getPhase());

        cubeId = (int) db.insertWithOnConflict(Cubes.TABLE, null , values, SQLiteDatabase.CONFLICT_IGNORE);
        DatabaseManager.getInstance().closeDatabase();
        return cubeId;
    }

    public void update(Cubes cubes){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Cubes.KEY_Balance, cubes.getBalance());
        values.put(Cubes.KEY_Time, cubes.getTime());
        values.put(Cubes.KEY_Phase, cubes.getPhase());

        db.updateWithOnConflict(Cubes.TABLE, values, Cubes.KEY_CompId + " =  '" + cubes.getCompId() + "' AND "
                + Cubes.KEY_MatchNumber + " = " + cubes.getMatchNum(), null, SQLiteDatabase.CONFLICT_IGNORE);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void deleteForComp(String event){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Cubes.TABLE, Cubes.KEY_CompId + " =  \"" + event + "\"", null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void deleteForCompAndMatch(String event, int match, String phase){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Cubes.TABLE, Cubes.KEY_CompId + " =  \"" + event + "\" AND "
                + Cubes.KEY_MatchNumber + " = " + match + " AND "
                + Cubes.KEY_Phase + " = \"" + phase + "\"", null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public int checkForSameMatch(String event, int match, String phase){
        int sameMatchCubes = 0;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = " SELECT Cubes." + Cubes.KEY_CompId
                + ", Cubes." + Cubes.KEY_MatchNumber
                + " FROM " + Cubes.TABLE
                + " WHERE Cubes." + Cubes.KEY_CompId + " = \"" + event + "\""
                + " AND Cubes." + Cubes.KEY_MatchNumber + " = " + match
                + " AND Cubes." + Cubes.KEY_Phase + " = \"" + phase + "\"";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                sameMatchCubes++;
            }while(cursor.moveToNext());
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        Log.d(TAG, "The number of ownerships with the same match is: " + sameMatchCubes);
        return sameMatchCubes;
    }
}






















