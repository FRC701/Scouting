package com.vandenrobotics.maxMin.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vandenrobotics.stats.data.DatabaseManager;
import com.vandenrobotics.stats.data.model.Competitions;
import com.vandenrobotics.stats.data.model.MaxMin;
import com.vandenrobotics.stats.data.model.Teams;

public class MaxMinRepo {
    private MaxMinRepo maxMin;

    private final String TAG = MaxMinRepo.class.getSimpleName();
    public MaxMinRepo(){
        maxMin = new MaxMinRepo();
    }

    //Holds String to execute SQLite where the MaxMin Table is created and specified how it is made
    public static String createTable(){
        return "CREATE TABLE " + MaxMin.TABLE + "("
                + MaxMin.KEY_MaxNoShow + " INTEGER , "
                + MaxMin.KEY_MaxStartLevel1 + " INTEGER , "
                + MaxMin.KEY_MaxStartLevel2 + " INTEGER , "
                + MaxMin.KEY_MaxPreloadCargo + " INTEGER , "
                + MaxMin.KEY_MaxPreloadHatch + " INTEGER , "
                + MaxMin.KEY_MaxRocketTopC + " INTEGER , "
                + MaxMin.KEY_MaxRocketTopH + " INTEGER , "
                + MaxMin.KEY_MaxRocketMiddleC + " INTEGER , "
                + MaxMin.KEY_MaxRocketMiddleH + " INTEGER , "
                + MaxMin.KEY_MaxRocketBottomC + " INTEGER , "
                + MaxMin.KEY_MaxRocketBottomH + " INTEGER , "
                + MaxMin.KEY_MaxCargoShipC + " INTEGER , "
                + MaxMin.KEY_MaxCargoShipH + " INTEGER , "
                + MaxMin.KEY_MaxCrossHubLine + " INTEGER , "
                + MaxMin.KEY_MaxEndLevel1 + " INTEGER , "
                + MaxMin.KEY_MaxEndLevel2 + " INTEGER , "
                + MaxMin.KEY_MaxEndLevel3 + " INTEGER , "
                + MaxMin.KEY_MaxEndNone + " INTEGER , "
                + MaxMin.KEY_MaxRobotDisabled + " INTEGER , "
                + MaxMin.KEY_MaxRedCard + " INTEGER , "
                + MaxMin.KEY_MaxYellowCard + " INTEGER , "
                + MaxMin.KEY_MaxFouls + " INTEGER , "
                + MaxMin.KEY_MaxTechFouls + " INTEGER , "
                + MaxMin.KEY_MaxDefense + " INTEGER , "

                + MaxMin.KEY_MinNoShow + " INTEGER , "
                + MaxMin.KEY_MinStartLevel1 + " INTEGER , "
                + MaxMin.KEY_MinStartLevel2 + " INTEGER , "
                + MaxMin.KEY_MinPreloadCargo + " INTEGER , "
                + MaxMin.KEY_MinPreloadHatch + " INTEGER , "
                + MaxMin.KEY_MinRocketTopC + " INTEGER , "
                + MaxMin.KEY_MinRocketTopH + " INTEGER , "
                + MaxMin.KEY_MinRocketMiddleC + " INTEGER , "
                + MaxMin.KEY_MinRocketMiddleH + " INTEGER , "
                + MaxMin.KEY_MinRocketBottomC + " INTEGER , "
                + MaxMin.KEY_MinRocketBottomH + " INTEGER , "
                + MaxMin.KEY_MinCargoShipC + " INTEGER , "
                + MaxMin.KEY_MinCargoShipH + " INTEGER , "
                + MaxMin.KEY_MinCrossHubLine + " INTEGER , "
                + MaxMin.KEY_MinEndLevel1 + " INTEGER , "
                + MaxMin.KEY_MinEndLevel3 + " INTEGER , "
                + MaxMin.KEY_MinEndNone + " INTEGER , "
                + MaxMin.KEY_MinRobotDisabled + " INTEGER , "
                + MaxMin.KEY_MinRedCard + " INTEGER , "
                + MaxMin.KEY_MinYellowCard + " INTEGER , "
                + MaxMin.KEY_MinFouls + " INTEGER , "
                + MaxMin.KEY_MinTechFouls + " INTEGER , "
                + MaxMin.KEY_MinDefense + " INTEGER , "
                //makes the CompId, MatchNum and MatchPos Primary Key so there needs
                //to be a unique combination of these attributes in each row in the MaxMin table
                //makes sure TeamNum column exists in the Team Table
                + " FOREIGN KEY ( " + MaxMin.KEY_TeamNum + " ) REFERENCES " + Teams.TABLE
                + " ( " + Teams.KEY_TeamNumber + " ))";
    }

    //Holds String to execute SQLite where the MaxMin Index is created to specify that there
    //Is a unique combination of CompId, Match# and Team# in every row in the maxMin table
    public static String createIndex(){
        return "CREATE UNIQUE INDEX '" + MaxMin.INDEX + "' ON "
                + Stats.TABLE
                + " ( " + MaxMin.KEY_TeamNum + " )";
    }

    //inserts all values of a maxMin row object into the sql database
    public int insertAll(MaxMin maxMin){
        int maxMinId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(MaxMin.KEY_MaxNoShow, maxMin.getMaxNoShow());
        values.put(MaxMin.KEY_MaxStartLevel1 , maxMin.getMaxStartLevel1());
        values.put(MaxMin.KEY_MaxStartLevel2, maxMin.getMaxStartLevel2());
        values.put(MaxMin.KEY_MaxPreloadCargo , maxMin.getMaxPreloadCargo());
        values.put(MaxMin.KEY_MaxPreloadHatch , maxMin.getMaxPreloadHatch());
        values.put(MaxMin.KEY_MaxRocketTopC , maxMin.getMaxRocketTopC());
        values.put(MaxMin.KEY_MaxRocketTopH , maxMin.getMaxRocketTopH());
        values.put(MaxMin.KEY_MaxRocketMiddleC , maxMin.getMaxRocketMiddleC());
        values.put(MaxMin.KEY_MaxRocketMiddleH , maxMin.getMaxRocketMiddleH());
        values.put(MaxMin.KEY_MaxRocketBottomC , maxMin.getMaxRocketMBottomC());
        values.put(MaxMin.KEY_MaxRocketBottomH , maxMin.getMaxRocketMBottomH());
        values.put(MaxMin.KEY_MaxCargoShipC , maxMin.getMaxCargoShipC());
        values.put(MaxMin.KEY_MaxCargoShipH , maxMin.getMaxCargoShipH());
        values.put(MaxMin.KEY_MaxCrossHubLine , maxMin.getMaxCrossHubLine());
        values.put(MaxMin.KEY_MaxEndLevel1, maxMin.getMaxEndLevel1());
        values.put(MaxMin.KEY_MaxEndLevel2 , maxMin.getMaxEndLevel2());
        values.put(MaxMin.KEY_MaxEndLevel3 , maxMin.getMaxEndLevel3());
        values.put(MaxMin.KEY_MaxEndNone , maxMin.getMaxEndNone());
        values.put(MaxMin.KEY_MaxRobotDisabled, maxMin.getMaxDisabled());
        values.put(MaxMin.KEY_MaxRedCard, maxMin.getMaxRedCard());
        values.put(MaxMin.KEY_MaxYellowCard, maxMin.getMaxYellowCard());
        values.put(MaxMin.KEY_MaxFouls, maxMin.getMaxFoul());
        values.put(MaxMin.KEY_MaxTechFouls, maxMin.getMaxTechFoul());
        values.put(MaxMin.KEY_MaxDefense, maxMin.getMaxDefense());

        values.put(MaxMin.KEY_MinNoShow, maxMin.getMinNoShow());
        values.put(MaxMin.KEY_MinStartLevel1 , maxMin.getMinStartLevel1());
        values.put(MaxMin.KEY_MinStartLevel2, maxMin.getMinStartLevel2());
        values.put(MaxMin.KEY_MinPreloadCargo , maxMin.getMinPreloadCargo());
        values.put(MaxMin.KEY_MinPreloadHatch , maxMin.getMinPreloadHatch());
        values.put(MaxMin.KEY_MinRocketTopC , maxMin.getMinRocketTopC());
        values.put(MaxMin.KEY_MinRocketTopH , maxMin.getMinRocketTopH());
        values.put(MaxMin.KEY_MinRocketMiddleC , maxMin.getMinRocketMiddleC());
        values.put(MaxMin.KEY_MinRocketMiddleH , maxMin.getMinRocketMiddleH());
        values.put(MaxMin.KEY_MinRocketBottomC , maxMin.getMinRocketMBottomC());
        values.put(MaxMin.KEY_MinRocketBottomH , maxMin.getMinRocketMBottomH());
        values.put(MaxMin.KEY_MinCargoShipC , maxMin.getMinCargoShipC());
        values.put(MaxMin.KEY_MinCargoShipH , maxMin.getMinCargoShipH());
        values.put(MaxMin.KEY_MinCrossHubLine , maxMin.getMinCrossHubLine());
        values.put(MaxMin.KEY_MinEndLevel1, maxMin.getMinEndLevel1());
        values.put(MaxMin.KEY_MinEndLevel2 , maxMin.getMinEndLevel2());
        values.put(MaxMin.KEY_MinEndLevel3 , maxMin.getMinEndLevel3());
        values.put(MaxMin.KEY_MinEndNone , maxMin.getMinEndNone());
        values.put(MaxMin.KEY_MinRobotDisabled, maxMin.getMinDisabled());
        values.put(MaxMin.KEY_MinRedCard, maxMin.getMinRedCard());
        values.put(MaxMin.KEY_MinYellowCard, maxMin.getMinYellowCard());
        values.put(MaxMin.KEY_MinFouls, maxMin.getMinFoul());
        values.put(MaxMin.KEY_MinTechFouls, maxMin.getMinTechFoul());
        values.put(MaxMin.KEY_MinDefense, maxMin.getMinDefense());

        //check if there is a conflict. It should return -1 if there is a copy of the exact combination of the Primary Keys
        maxMinId=(int)db.insertWithOnConflict(MaxMin.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        DatabaseManager.getInstance().closeDatabase();
        //return to check conflict
        return maxMinId;
    }

    //updates the first part of the row with a new team
    public int updatePart(MaxMin maxMin){
        int maxMinId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(MaxMin.KEY_TeamNum, maxMin.getTeamNum());

        //updates row with the same CompId, Match# and Match Position
        maxMinId = db.update(MaxMin.TABLE, values,MaxMin.KEY_CompId + " =  '" + maxMin.getCompId() + "' AND "
                + MaxMin.KEY_MatchNum + " = " + maxMin.getMatchNum() + " AND "
                + MaxMin.KEY_MatchPosition + " = " + maxMin.getMatchPos(), null);
        DatabaseManager.getInstance().closeDatabase();
        return maxMinId;
    }

    //deletes all rows in the maxMin table
    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(MaxMin.TABLE, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }

    //deletes all rows in the maxMin table for the specified comp
    public void deleteForComp(String event) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(MaxMin.TABLE, MaxMin.KEY_CompId + " =  '" + event + "'",null);
        DatabaseManager.getInstance().closeDatabase();
    }

    /*
     * All set functions save each part of a single row for each phase of the match
     * using the initial insert that adds the comp, match, team and match pos
     * used for saving data
     * */

    public void setMaxMin(MaxMin maxMin){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        Log.d("MaxMinRepo auto", "team id " + maxMin.getTeamNum());
        values.put(MaxMin.KEY_MaxNoShow, maxMin.getNoShow());
        values.put(MaxMin.KEY_MaxStartLevel1 , maxMin.getStartLevel1());
        values.put(MaxMin.KEY_MaxStartLevel2, maxMin.getStartLevel2());
        values.put(MaxMin.KEY_MaxPreloadCargo , maxMin.getPreloadCargo());
        values.put(MaxMin.KEY_MaxPreloadHatch , maxMin.getPreloadHatch());
        values.put(MaxMin.KEY_MaxRocketTopC , maxMin.getRocketTopC());
        values.put(MaxMin.KEY_MaxRocketTopH , maxMin.getRocketTopH());
        values.put(MaxMin.KEY_MaxRocketMiddleC , maxMin.getRocketMiddleC());
        values.put(MaxMin.KEY_MaxRocketMiddleH , maxMin.getRocketMiddleH());
        values.put(MaxMin.KEY_MaxRocketBottomC , maxMin.getRocketMBottomC());
        values.put(MaxMin.KEY_MaxRocketBottomH , maxMin.getRocketMBottomH());
        values.put(MaxMin.KEY_MaxCargoShipC , maxMin.getCargoShipC());
        values.put(MaxMin.KEY_MaxCargoShipH , maxMin.getCargoShipH());
        values.put(MaxMin.KEY_MaxCrossHubLine , maxMin.getCrossHubLine());
        values.put(MaxMin.KEY_MaxDefense , maxMin.getDefense());
        values.put(MaxMin.KEY_MaxEndLevel1, maxMin.getEndLevel1());
        values.put(MaxMin.KEY_MaxEndLevel2 , maxMin.getEndLevel2());
        values.put(MaxMin.KEY_MaxEndLevel3 , maxMin.getEndLevel3());
        values.put(MaxMin.KEY_MaxEndNone , maxMin.getEndNone());
        values.put(MaxMin.KEY_MaxRobotDisabled, maxMin.getDisabled());
        values.put(MaxMin.KEY_MaxRedCard, maxMin.getRedCard());
        values.put(MaxMin.KEY_MaxYellowCard, maxMin.getYellowCard());
        values.put(MaxMin.KEY_MaxFouls, maxMin.getFoul());
        values.put(MaxMin.KEY_MaxTechFouls, maxMin.getTechFoul());

        values.put(MaxMin.KEY_MinNoShow, maxMin.getNoShow());
        values.put(MaxMin.KEY_MinStartLevel1 , maxMin.getStartLevel1());
        values.put(MaxMin.KEY_MinStartLevel2, maxMin.getStartLevel2());
        values.put(MaxMin.KEY_MinPreloadCargo , maxMin.getPreloadCargo());
        values.put(MaxMin.KEY_MinPreloadHatch , maxMin.getPreloadHatch());
        values.put(MaxMin.KEY_MinRocketTopC , maxMin.getRocketTopC());
        values.put(MaxMin.KEY_MinRocketTopH , maxMin.getRocketTopH());
        values.put(MaxMin.KEY_MinRocketMiddleC , maxMin.getRocketMiddleC());
        values.put(MaxMin.KEY_MinRocketMiddleH , maxMin.getRocketMiddleH());
        values.put(MaxMin.KEY_MinRocketBottomC , maxMin.getRocketMBottomC());
        values.put(MaxMin.KEY_MinRocketBottomH , maxMin.getRocketMBottomH());
        values.put(MaxMin.KEY_MinCargoShipC , maxMin.getCargoShipC());
        values.put(MaxMin.KEY_MinCargoShipH , maxMin.getCargoShipH());
        values.put(MaxMin.KEY_MinCrossHubLine , maxMin.getCrossHubLine());
        values.put(MaxMin.KEY_MinDefense , maxMin.getDefense());
        values.put(MaxMin.KEY_MinEndLevel1, maxMin.getEndLevel1());
        values.put(MaxMin.KEY_MinEndLevel2 , maxMin.getEndLevel2());
        values.put(MaxMin.KEY_MinEndLevel3 , maxMin.getEndLevel3());
        values.put(MaxMin.KEY_MinEndNone , maxMin.getEndNone());
        values.put(MaxMin.KEY_MinRobotDisabled, maxMin.getDisabled());
        values.put(MaxMin.KEY_MinRedCard, maxMin.getRedCard());
        values.put(MaxMin.KEY_MinYellowCard, maxMin.getYellowCard());
        values.put(MaxMin.KEY_MinFouls, maxMin.getFoul());
        values.put(MaxMin.KEY_MinTechFouls, maxMin.getTechFoul());

        db.update(MaxMin.TABLE, values, MaxMin.KEY_CompId + " =  '" + maxMin.getCompId() + "' AND "
                + MaxMin.KEY_MatchNum + " = " + maxMin.getMatchNum() + " AND "
                + MaxMin.KEY_TeamNum + " = " + maxMin.getTeamNum(), null);
        DatabaseManager.getInstance().closeDatabase();
    }
    /*
     * get functions gets their phase from part of the row with the current comp, match, and position
     * used for loading data
     * */

    //get the Auto maxMin from the row with the current comp, match and match position

    public MaxMin getMaxMin(int team){
        MaxMin maxMin = new MaxMin();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        //makes the selection query for the maxMin table to get the auto maxMin
        String selectQuery = " SELECT MaxMin." + MaxMin.KEY_MaxNoShow
                + ", MaxMin." + MaxMin.KEY_MaxStartLevel1
                + ", MaxMin." + MaxMin.KEY_MaxStartLevel2
                + ", MaxMin." + MaxMin.KEY_MaxPreloadCargo
                + ", MaxMin." + MaxMin.KEY_MaxPreloadHatch
                + ", MaxMin." + MaxMin.KEY_MaxRocketTopC
                + ", MaxMin." + MaxMin.KEY_MaxRocketTopH
                + ", MaxMin." + MaxMin.KEY_MaxRocketMiddleC
                + ", MaxMin." + MaxMin.KEY_MaxRocketMiddleH
                + ", MaxMin." + MaxMin.KEY_MaxRocketBottomC
                + ", MaxMin." + MaxMin.KEY_MaxRocketBottomH
                + ", MaxMin." + MaxMin.KEY_MaxCargoShipC
                + ", MaxMin." + MaxMin.KEY_MaxCargoShipH
                + ", MaxMin." + MaxMin.KEY_MaxCrossHubLine
                + ", MaxMin." + MaxMin.KEY_MaxEndLevel1
                + ", MaxMin." + MaxMin.KEY_MaxEndLevel2
                + ", MaxMin." + MaxMin.KEY_MaxEndLevel3
                + ", MaxMin." + MaxMin.KEY_MaxEndNone
                + ", MaxMin." + MaxMin.KEY_MaxRobotDisabled
                + ", MaxMin." + MaxMin.KEY_MaxRedCard
                + ", MaxMin." + MaxMin.KEY_MaxYellowCard
                + ", MaxMin." + MaxMin.KEY_MaxFouls
                + ", MaxMin." + MaxMin.KEY_MaxTechFouls

                + ", MaxMin." + MaxMin.KEY_MinNoShow
                + ", MaxMin." + MaxMin.KEY_MinStartLevel1
                + ", MaxMin." + MaxMin.KEY_MinStartLevel2
                + ", MaxMin." + MaxMin.KEY_MinPreloadCargo
                + ", MaxMin." + MaxMin.KEY_MinPreloadHatch
                + ", MaxMin." + MaxMin.KEY_MinRocketTopC
                + ", MaxMin." + MaxMin.KEY_MinRocketTopH
                + ", MaxMin." + MaxMin.KEY_MinRocketMiddleC
                + ", MaxMin." + MaxMin.KEY_MinRocketMiddleH
                + ", MaxMin." + MaxMin.KEY_MinRocketBottomC
                + ", MaxMin." + MaxMin.KEY_MinRocketBottomH
                + ", MaxMin." + MaxMin.KEY_MinCargoShipC
                + ", MaxMin." + MaxMin.KEY_MinCargoShipH
                + ", MaxMin." + MaxMin.KEY_MinCrossHubLine
                + ", MaxMin." + MaxMin.KEY_MinEndLevel1
                + ", MaxMin." + MaxMin.KEY_MinEndLevel2
                + ", MaxMin." + MaxMin.KEY_MinEndLevel3
                + ", MaxMin." + MaxMin.KEY_MinEndNone
                + ", MaxMin." + MaxMin.KEY_MinRobotDisabled 
                + ", MaxMin." + MaxMin.KEY_MinRedCard
                + ", MaxMin." + MaxMin.KEY_MinYellowCard
                + ", MaxMin." + MaxMin.KEY_MinFouls
                + ", MaxMin." + MaxMin.KEY_MinTechFouls
                + " FROM " + MaxMin.TABLE
                + " WHERE MaxMin." + MaxMin.KEY_TeamNum + " = " + team;

        Log.d(TAG, selectQuery);
        //uses the selection query to get rows from the database one at a time
        Cursor cursor = db.rawQuery(selectQuery, null);
        //gets the first row that matches the specifications from the selection query
        if (cursor.moveToFirst()){
            maxMin.setNoShow(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_NoShow)));
            maxMin.setStartLevel1(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_StartLevel1)));
            maxMin.setStartLevel2(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_StartLevel2)));
            maxMin.setPreloadCargo(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_PreloadCargo)));
            maxMin.setPreloadHatch(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_PreloadHatch)));
            maxMin.setRocketTopC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RocketTopC)));
            maxMin.setRocketTopH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RocketTopH)));
            maxMin.setRocketMiddleC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RocketMiddleC)));
            maxMin.setRocketMiddleH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RocketMiddleH)));
            maxMin.setRocketBottomC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RocketBottomC)));
            maxMin.setRocketBottomH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RocketBottomH)));
            maxMin.setCargoShipC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_CargoShipC)));
            maxMin.setCargoShipH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_CargoShipH)));
            maxMin.setCrossHubLine(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_CrossHubLine)));
            maxMin.setDisabled(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RobotDisabled)));
            maxMin.setRedCard(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RedCard)));
            maxMin.setYellowCard(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_YellowCard)));
            maxMin.setFoul(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_Fouls)));
            maxMin.setTechFoul(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_TechFouls)));
            maxMin.setEndLevel1(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_EndLevel1)));
            maxMin.setEndLevel2(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_EndLevel2)));
            maxMin.setEndLevel3(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_EndLevel3)));
            maxMin.setEndNone(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_EndNone)));

            maxMin.setNoShow(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_NoShow)));
            maxMin.setStartLevel1(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_StartLevel1)));
            maxMin.setStartLevel2(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_StartLevel2)));
            maxMin.setPreloadCargo(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_PreloadCargo)));
            maxMin.setPreloadHatch(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_PreloadHatch)));
            maxMin.setRocketTopC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RocketTopC)));
            maxMin.setRocketTopH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RocketTopH)));
            maxMin.setRocketMiddleC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RocketMiddleC)));
            maxMin.setRocketMiddleH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RocketMiddleH)));
            maxMin.setRocketBottomC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RocketBottomC)));
            maxMin.setRocketBottomH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RocketBottomH)));
            maxMin.setCargoShipC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_CargoShipC)));
            maxMin.setCargoShipH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_CargoShipH)));
            maxMin.setCrossHubLine(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_CrossHubLine)));
            maxMin.setDisabled(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RobotDisabled)));
            maxMin.setRedCard(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_RedCard)));
            maxMin.setYellowCard(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_YellowCard)));
            maxMin.setFoul(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_Fouls)));
            maxMin.setTechFoul(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_TechFouls)));
            maxMin.setEndLevel1(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_EndLevel1)));
            maxMin.setEndLevel2(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_EndLevel2)));
            maxMin.setEndLevel3(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_EndLevel3)));
            maxMin.setEndNone(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_EndNone)));
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        //returns a maxMin row object with the values from the database
        return maxMin;
    }
}
