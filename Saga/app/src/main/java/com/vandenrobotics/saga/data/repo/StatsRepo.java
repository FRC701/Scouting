package com.vandenrobotics.saga.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vandenrobotics.saga.data.DatabaseManager;
import com.vandenrobotics.saga.data.model.Competitions;
import com.vandenrobotics.saga.data.model.Stats;
import com.vandenrobotics.saga.data.model.Teams;

/**
 * Created by Programming701-A on 12/18/2017.
 */

public class StatsRepo {

    private Stats stats;

    private final String TAG = StatsRepo.class.getSimpleName();
    public StatsRepo(){
        stats = new Stats();
    }

    //Holds String to execute SQLite where the Stats Table is created and specified how it is made
    public static String createTable(){
        return "CREATE TABLE " + Stats.TABLE + "("
                + Stats.KEY_CompId + " TEXT not null , "
                + Stats.KEY_MatchNum + " INTEGER not null , "
                + Stats.KEY_TeamNum + " INTEGER not null , "
                //Makes sure the match position is between 1 and 6 for each tablet
                + Stats.KEY_MatchPosition + " INTEGER not null CHECK ( " + Stats.KEY_MatchPosition + " BETWEEN 1 AND 6 ), "
                + Stats.KEY_NoShow + " INTEGER , "
                + Stats.KEY_StartLevel1 + " BOOL , "
                + Stats.KEY_StartLevel2 + " BOOL , "
                + Stats.KEY_PreloadCargo + " BOOL , "
                + Stats.KEY_PreloadHatch + " BOOL , "
                + Stats.KEY_SsComments + " TEXT , "
                + Stats.KEY_RocketTopC + " INTEGER , "
                + Stats.KEY_RocketTopH + " INTEGER , "
                + Stats.KEY_RocketMiddleC + " INTEGER , "
                + Stats.KEY_RocketMiddleH + " INTEGER , "
                + Stats.KEY_RocketBottomC + " INTEGER , "
                + Stats.KEY_RocketBottomH + " INTEGER , "
                + Stats.KEY_CargoShipC + " INTEGER , "
                + Stats.KEY_CargoShipH + " INTEGER , "
                + Stats.KEY_CrossHubLine + " BOOL , "
                + Stats.KEY_EndLevel1 + " BOOL , "
                + Stats.KEY_EndLevel2 + " BOOL , "
                + Stats.KEY_EndLevel3 + " BOOL , "
                + Stats.KEY_EndNone + " BOOL , "
                + Stats.KEY_RobotDisabled + " INTEGER , "
                + Stats.KEY_RedCard + " INTEGER , "
                + Stats.KEY_YellowCard + " INTEGER , "
                + Stats.KEY_Fouls + " INTEGER , "
                + Stats.KEY_TechFouls + " INTEGER , "
                + Stats.KEY_Disqualified + " INTEGER , " +
                //makes the CompId, MatchNum and MatchPos Primary Key so there needs
                //to be a unique combination of these attributes in each row in the Stats table
                "PRIMARY KEY ( '" + Stats.KEY_CompId
                + "' , '" + Stats.KEY_MatchNum
                + "' , '" + Stats.KEY_MatchPosition + "' ), "
                //makes sure CompId column exists in the Competitions Table
                + " FOREIGN KEY ( " + Stats.KEY_CompId + " ) REFERENCES " + Competitions.TABLE
                + " ( " + Competitions.KEY_CompId + " ), "
                //makes sure TeamNum column exists in the Team Table
                + " FOREIGN KEY ( " + Stats.KEY_TeamNum + " ) REFERENCES " + Teams.TABLE
                + " ( " + Teams.KEY_TeamNum + " ))";
    }

    //Holds String to execute SQLite where the Stats Index is created to specify that there
    //Is a unique combination of CompId, Match# and Team# in every row in the stats table
    public static String createIndex(){
        return "CREATE UNIQUE INDEX '" + Stats.INDEX + "' ON "
                + Stats.TABLE
                + " ( " + Stats.KEY_CompId
                + " , " + Stats.KEY_MatchNum
                + " , " + Stats.KEY_TeamNum + " )";
    }

    //inserts all values of a stats row object into the sql database
    public int insertAll(Stats stats){
        int statsId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Stats.KEY_CompId, stats.getCompId());
        values.put(Stats.KEY_MatchNum, stats.getMatchNum());
        values.put(Stats.KEY_TeamNum, stats.getTeamNum());
        values.put(Stats.KEY_MatchPosition, stats.getMatchPos());
        values.put(Stats.KEY_NoShow, stats.getNoShow());
        values.put(Stats.KEY_StartLevel1 , stats.getStartLevel1());
        values.put(Stats.KEY_StartLevel2, stats.getStartLevel2());
        values.put(Stats.KEY_PreloadCargo , stats.getPreloadCargo());
        values.put(Stats.KEY_PreloadHatch , stats.getPreloadHatch());
        values.put(Stats.KEY_SsComments, stats.getSsComments());
        values.put(Stats.KEY_RocketTopC , stats.getRocketTopC());
        values.put(Stats.KEY_RocketTopH , stats.getRocketTopH());
        values.put(Stats.KEY_RocketMiddleC , stats.getRocketMiddleC());
        values.put(Stats.KEY_RocketMiddleH , stats.getRocketMiddleH());
        values.put(Stats.KEY_RocketBottomC , stats.getRocketMBottomC());
        values.put(Stats.KEY_RocketBottomH , stats.getRocketMBottomH());
        values.put(Stats.KEY_CargoShipC , stats.getCargoShipC());
        values.put(Stats.KEY_CargoShipH , stats.getCargoShipH());
        values.put(Stats.KEY_CrossHubLine , stats.getCrossHubLine());
        values.put(Stats.KEY_EndLevel1, stats.getEndLevel1());
        values.put(Stats.KEY_EndLevel2 , stats.getEndLevel2());
        values.put(Stats.KEY_EndLevel3 , stats.getEndLevel3());
        values.put(Stats.KEY_EndNone , stats.getEndNone());
        values.put(Stats.KEY_RobotDisabled, stats.getDisabled());
        values.put(Stats.KEY_RedCard, stats.getRedCard());
        values.put(Stats.KEY_YellowCard, stats.getYellowCard());
        values.put(Stats.KEY_Fouls, stats.getFoul());
        values.put(Stats.KEY_TechFouls, stats.getTechFoul());
        values.put(Stats.KEY_Disqualified, stats.getDisqualified());

        //check if there is a conflict. It should return -1 if there is a copy of the exact combination of the Primary Keys
        statsId=(int)db.insertWithOnConflict(Stats.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);

        DatabaseManager.getInstance().closeDatabase();
        //return to check conflict
        return statsId;
    }

    //updates the first part of the row with a new team
    public int updatePart(Stats stats){
        int statsId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Stats.KEY_TeamNum, stats.getTeamNum());

        //updates row with the same CompId, Match# and Match Position
        statsId = db.update(Stats.TABLE, values,Stats.KEY_CompId + " =  '" + stats.getCompId() + "' AND "
                + Stats.KEY_MatchNum + " = " + stats.getMatchNum() + " AND "
                + Stats.KEY_MatchPosition + " = " + stats.getMatchPos(), null);
        DatabaseManager.getInstance().closeDatabase();
        return statsId;
    }

    //deletes all rows in the stats table
    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Stats.TABLE, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    //deletes all rows in the stats table for the specified comp
    public void deleteForComp(String event) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Stats.TABLE, Stats.KEY_CompId + " =  '" + event + "'",null);
        DatabaseManager.getInstance().closeDatabase();
    }

    /*
    * All set functions save each part of a single row for each phase of the match
    * using the initial insert that adds the comp, match, team and match pos
    * used for saving data
    * */

    public void setPreStats(Stats stats){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        Log.d("StatsRepo auto", "team id " + stats.getTeamNum());
        values.put(Stats.KEY_NoShow, stats.getNoShow());
        values.put(Stats.KEY_StartLevel1 , stats.getStartLevel1());
        values.put(Stats.KEY_StartLevel2, stats.getStartLevel2());
        values.put(Stats.KEY_PreloadCargo , stats.getPreloadCargo());
        values.put(Stats.KEY_PreloadHatch , stats.getPreloadHatch());
        values.put(Stats.KEY_SsComments, stats.getSsComments());

        db.update(Stats.TABLE, values, Stats.KEY_CompId + " =  '" + stats.getCompId() + "' AND "
                + Stats.KEY_MatchNum + " = " + stats.getMatchNum() + " AND "
                + Stats.KEY_TeamNum + " = " + stats.getTeamNum(), null);
        DatabaseManager.getInstance().closeDatabase();
    }
    public void setMidStats(Stats stats){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        Log.d("StatsRepo auto", "team id " + stats.getTeamNum());
        values.put(Stats.KEY_RocketTopC , stats.getRocketTopC());
        values.put(Stats.KEY_RocketTopH , stats.getRocketTopH());
        values.put(Stats.KEY_RocketMiddleC , stats.getRocketMiddleC());
        values.put(Stats.KEY_RocketMiddleH , stats.getRocketMiddleH());
        values.put(Stats.KEY_RocketBottomC , stats.getRocketMBottomC());
        values.put(Stats.KEY_RocketBottomH , stats.getRocketMBottomH());
        values.put(Stats.KEY_CargoShipC , stats.getCargoShipC());
        values.put(Stats.KEY_CargoShipH , stats.getCargoShipH());
        values.put(Stats.KEY_CrossHubLine , stats.getCrossHubLine());

        db.update(Stats.TABLE, values, Stats.KEY_CompId + " =  '" + stats.getCompId() + "' AND "
                + Stats.KEY_MatchNum + " = " + stats.getMatchNum() + " AND "
                + Stats.KEY_TeamNum + " = " + stats.getTeamNum(), null);
        DatabaseManager.getInstance().closeDatabase();
    }

    //save the Tele Stats to the database for the current comp, match and team
    public void setPostStats(Stats stats) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        Log.d("StatsRepo", "Team id " + stats.getTeamNum());
        values.put(Stats.KEY_EndLevel1, stats.getEndLevel1());
        values.put(Stats.KEY_EndLevel2 , stats.getEndLevel2());
        values.put(Stats.KEY_EndLevel3 , stats.getEndLevel3());
        values.put(Stats.KEY_EndNone , stats.getEndNone());
        values.put(Stats.KEY_RobotDisabled, stats.getDisabled());
        values.put(Stats.KEY_RedCard, stats.getRedCard());
        values.put(Stats.KEY_YellowCard, stats.getYellowCard());
        values.put(Stats.KEY_Fouls, stats.getFoul());
        values.put(Stats.KEY_TechFouls, stats.getTechFoul());
        values.put(Stats.KEY_Disqualified, stats.getDisqualified());
        Log.d("StatsRepo", "updating table"+values.toString());
        try {
            db.update(Stats.TABLE, values, Stats.KEY_CompId + " =  \"" + stats.getCompId() + "\" AND "
                    + Stats.KEY_MatchNum + " = " + stats.getMatchNum() + " AND "
                    + Stats.KEY_TeamNum + " = " + stats.getTeamNum(), null);
        }catch(Exception e){
            Log.e("StatsRepo", "Exception updating database" + e.getMessage());

        }
        DatabaseManager.getInstance().closeDatabase();
    }

    /*
    * get functions gets their phase from part of the row with the current comp, match, and position
    * used for loading data
    * */

    //get the Auto stats from the row with the current comp, match and match position

    public Stats getPreStats(String event, int match, int matchPos){
        Stats stats = new Stats();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        //makes the selection query for the stats table to get the auto stats
        String selectQuery = " SELECT Stats." + Stats.KEY_NoShow
                + ", Stats." + Stats.KEY_StartLevel1
                + ", Stats." + Stats.KEY_StartLevel2
                + ", Stats." + Stats.KEY_PreloadCargo
                + ", Stats." + Stats.KEY_PreloadHatch
                + ", Stats." + Stats.KEY_SsComments
                + " FROM " + Stats.TABLE
                + " WHERE Stats." + Stats.KEY_CompId + " = \"" + event + "\""
                + " AND Stats." + Stats.KEY_MatchNum + " = " + match
                + " AND Stats." + Stats.KEY_MatchPosition + " = " + matchPos;

        Log.d(TAG, selectQuery);
        //uses the selection query to get rows from the database one at a time
        Cursor cursor = db.rawQuery(selectQuery, null);
        //gets the first row that matches the specifications from the selection query
        if (cursor.moveToFirst()){
            stats.setNoShow(cursor.getInt(cursor.getColumnIndex(Stats.KEY_NoShow)));
            stats.setStartLevel1(cursor.getInt(cursor.getColumnIndex(Stats.KEY_StartLevel1)));
            stats.setStartLevel2(cursor.getInt(cursor.getColumnIndex(Stats.KEY_StartLevel2)));
            stats.setPreloadCargo(cursor.getInt(cursor.getColumnIndex(Stats.KEY_PreloadCargo)));
            stats.setPreloadHatch(cursor.getInt(cursor.getColumnIndex(Stats.KEY_PreloadHatch)));
            stats.setSscomments(cursor.getString(cursor.getColumnIndex(Stats.KEY_SsComments)));
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        //returns a stats row object with the values from the database
        return stats;
    }
    public Stats getMidStats(String event, int match, int matchPos){
        Stats stats = new Stats();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        //makes the selection query for the stats table to get the auto stats
        String selectQuery = " SELECT Stats." + Stats.KEY_RocketTopC
                + ", Stats." + Stats.KEY_RocketTopH
                + ", Stats." + Stats.KEY_RocketMiddleC
                + ", Stats." + Stats.KEY_RocketMiddleH
                + ", Stats." + Stats.KEY_RocketBottomC
                + ", Stats." + Stats.KEY_RocketBottomH
                + ", Stats." + Stats.KEY_CargoShipC
                + ", Stats." + Stats.KEY_CargoShipH
                + ", Stats." + Stats.KEY_CrossHubLine
                + " FROM " + Stats.TABLE
                + " WHERE Stats." + Stats.KEY_CompId + " = \"" + event + "\""
                + " AND Stats." + Stats.KEY_MatchNum + " = " + match
                + " AND Stats." + Stats.KEY_MatchPosition + " = " + matchPos;

        Log.d(TAG, selectQuery);
        //uses the selection query to get rows from the database one at a time
        Cursor cursor = db.rawQuery(selectQuery, null);
        //gets the first row that matches the specifications from the selection query
        if (cursor.moveToFirst()){
            stats.setRocketTopC(cursor.getInt(cursor.getColumnIndex(Stats.KEY_RocketTopC)));
            stats.setRocketTopH(cursor.getInt(cursor.getColumnIndex(Stats.KEY_RocketTopH)));
            stats.setRocketMiddleC(cursor.getInt(cursor.getColumnIndex(Stats.KEY_RocketMiddleC)));
            stats.setRocketMiddleH(cursor.getInt(cursor.getColumnIndex(Stats.KEY_RocketMiddleH)));
            stats.setRocketBottomC(cursor.getInt(cursor.getColumnIndex(Stats.KEY_RocketBottomC)));
            stats.setRocketBottomH(cursor.getInt(cursor.getColumnIndex(Stats.KEY_RocketBottomH)));
            stats.setCargoShipC(cursor.getInt(cursor.getColumnIndex(Stats.KEY_CargoShipC)));
            stats.setCargoShipH(cursor.getInt(cursor.getColumnIndex(Stats.KEY_CargoShipH)));
            stats.setCrossHubLine(cursor.getInt(cursor.getColumnIndex(Stats.KEY_CrossHubLine)));
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        //returns a stats row object with the values from the database
        return stats;
    }
    //get the Tele stats from the row with the current comp, match and match position
    public Stats getTeleStats (String event, int match, int matchPos) {
        Stats stats = new Stats();

        //makes the selection query for the stats table to get the tele stats
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = " SELECT Stats." + Stats.KEY_EndLevel1
                + ", Stats." + Stats.KEY_EndLevel2
                + ", Stats." + Stats.KEY_EndLevel3
                + ", Stats." + Stats.KEY_EndNone
                + ", Stats." + Stats.KEY_RobotDisabled
                + ", Stats." + Stats.KEY_RedCard
                + ", Stats." + Stats.KEY_YellowCard
                + ", Stats." + Stats.KEY_Fouls
                + ", Stats." + Stats.KEY_TechFouls
                + ", Stats." + Stats.KEY_Disqualified
                + " FROM " + Stats.TABLE
                + " WHERE Stats." + Stats.KEY_CompId + " = \"" + event + "\""
                + " AND Stats." + Stats.KEY_MatchNum + " = " + match
                + " AND Stats." + Stats.KEY_MatchPosition + " = " + matchPos;

        Log.d(TAG, selectQuery);
        //uses the selection query to get rows from the database one at a time
        Cursor cursor = db.rawQuery(selectQuery, null);
        //gets the first row that matches the specifications from the selection query
        if (cursor.moveToFirst()){
            stats.setDisabled(cursor.getInt(cursor.getColumnIndex(Stats.KEY_RobotDisabled)));
            stats.setRedCard(cursor.getInt(cursor.getColumnIndex(Stats.KEY_RedCard)));
            stats.setYellowCard(cursor.getInt(cursor.getColumnIndex(Stats.KEY_YellowCard)));
            stats.setFoul(cursor.getInt(cursor.getColumnIndex(Stats.KEY_Fouls)));
            stats.setTechFoul(cursor.getInt(cursor.getColumnIndex(Stats.KEY_TechFouls)));
            stats.setEndLevel1(cursor.getInt(cursor.getColumnIndex(Stats.KEY_EndLevel1)));
            stats.setEndLevel2(cursor.getInt(cursor.getColumnIndex(Stats.KEY_EndLevel2)));
            stats.setEndLevel3(cursor.getInt(cursor.getColumnIndex(Stats.KEY_EndLevel3)));
            stats.setEndNone(cursor.getInt(cursor.getColumnIndex(Stats.KEY_EndNone)));
            stats.setDisqualified(cursor.getInt(cursor.getColumnIndex(Stats.KEY_Disqualified)));
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        //returns a stats row object with the values from the database
        return stats;
    }
}
