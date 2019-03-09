package com.vandenrobotics.stats.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vandenrobotics.stats.data.DatabaseManager;
import com.vandenrobotics.stats.data.model.Competitions;
import com.vandenrobotics.stats.data.model.MaxMin;
import com.vandenrobotics.stats.data.model.Teams;

public class MaxMinRepo {
    private MaxMin maxMin;

    private final String TAG = MaxMinRepo.class.getSimpleName();
    public MaxMinRepo(){
        maxMin = new MaxMin();
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
                + MaxMin.TABLE
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
        values.put(MaxMin.KEY_MaxRocketBottomC , maxMin.getMaxRocketBottomC());
        values.put(MaxMin.KEY_MaxRocketBottomH , maxMin.getMaxRocketBottomH());
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


    //deletes all rows in the maxMin table
    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(MaxMin.TABLE, null,null);
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
        values.put(MaxMin.KEY_MaxNoShow, maxMin.getMaxNoShow());
        values.put(MaxMin.KEY_MaxStartLevel1 , maxMin.getMaxStartLevel1());
        values.put(MaxMin.KEY_MaxStartLevel2, maxMin.getMaxStartLevel2());
        values.put(MaxMin.KEY_MaxPreloadCargo , maxMin.getMaxPreloadCargo());
        values.put(MaxMin.KEY_MaxPreloadHatch , maxMin.getMaxPreloadHatch());
        values.put(MaxMin.KEY_MaxRocketTopC , maxMin.getMaxRocketTopC());
        values.put(MaxMin.KEY_MaxRocketTopH , maxMin.getMaxRocketTopH());
        values.put(MaxMin.KEY_MaxRocketMiddleC , maxMin.getMaxRocketMiddleC());
        values.put(MaxMin.KEY_MaxRocketMiddleH , maxMin.getMaxRocketMiddleH());
        values.put(MaxMin.KEY_MaxRocketBottomC , maxMin.getMaxRocketBottomC());
        values.put(MaxMin.KEY_MaxRocketBottomH , maxMin.getMaxRocketBottomH());
        values.put(MaxMin.KEY_MaxCargoShipC , maxMin.getMaxCargoShipC());
        values.put(MaxMin.KEY_MaxCargoShipH , maxMin.getMaxCargoShipH());
        values.put(MaxMin.KEY_MaxCrossHubLine , maxMin.getMaxCrossHubLine());
        values.put(MaxMin.KEY_MaxDefense , maxMin.getMaxDefense());
        values.put(MaxMin.KEY_MaxEndLevel1, maxMin.getMaxEndLevel1());
        values.put(MaxMin.KEY_MaxEndLevel2 , maxMin.getMaxEndLevel2());
        values.put(MaxMin.KEY_MaxEndLevel3 , maxMin.getMaxEndLevel3());
        values.put(MaxMin.KEY_MaxEndNone , maxMin.getMaxEndNone());
        values.put(MaxMin.KEY_MaxRobotDisabled, maxMin.getMaxDisabled());
        values.put(MaxMin.KEY_MaxRedCard, maxMin.getMaxRedCard());
        values.put(MaxMin.KEY_MaxYellowCard, maxMin.getMaxYellowCard());
        values.put(MaxMin.KEY_MaxFouls, maxMin.getMaxFoul());
        values.put(MaxMin.KEY_MaxTechFouls, maxMin.getMaxTechFoul());

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
        values.put(MaxMin.KEY_MinDefense , maxMin.getMinDefense());
        values.put(MaxMin.KEY_MinEndLevel1, maxMin.getMinEndLevel1());
        values.put(MaxMin.KEY_MinEndLevel2 , maxMin.getMinEndLevel2());
        values.put(MaxMin.KEY_MinEndLevel3 , maxMin.getMinEndLevel3());
        values.put(MaxMin.KEY_MinEndNone , maxMin.getMinEndNone());
        values.put(MaxMin.KEY_MinRobotDisabled, maxMin.getMinDisabled());
        values.put(MaxMin.KEY_MinRedCard, maxMin.getMinRedCard());
        values.put(MaxMin.KEY_MinYellowCard, maxMin.getMinYellowCard());
        values.put(MaxMin.KEY_MinFouls, maxMin.getMinFoul());
        values.put(MaxMin.KEY_MinTechFouls, maxMin.getMinTechFoul());

        db.update(MaxMin.TABLE, values,  " AND "
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
            maxMin.setMaxNoShow(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxNoShow)));
            maxMin.setMaxStartLevel1(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxStartLevel1)));
            maxMin.setMaxStartLevel2(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxStartLevel2)));
            maxMin.setMaxPreloadCargo(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxPreloadCargo)));
            maxMin.setMaxPreloadHatch(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxPreloadHatch)));
            maxMin.setMaxRocketTopC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxRocketTopC)));
            maxMin.setMaxRocketTopH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxRocketTopH)));
            maxMin.setMaxRocketMiddleC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxRocketMiddleC)));
            maxMin.setMaxRocketMiddleH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxRocketMiddleH)));
            maxMin.setMaxRocketBottomC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxRocketBottomC)));
            maxMin.setMaxRocketBottomH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxRocketBottomH)));
            maxMin.setMaxCargoShipC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxCargoShipC)));
            maxMin.setMaxCargoShipH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxCargoShipH)));
            maxMin.setMaxCrossHubLine(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxCrossHubLine)));
            maxMin.setMaxDisabled(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxRobotDisabled)));
            maxMin.setMaxRedCard(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxRedCard)));
            maxMin.setMaxYellowCard(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxYellowCard)));
            maxMin.setMaxFoul(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxFouls)));
            maxMin.setMaxTechFoul(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxTechFouls)));
            maxMin.setMaxEndLevel1(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxEndLevel1)));
            maxMin.setMaxEndLevel2(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxEndLevel2)));
            maxMin.setMaxEndLevel3(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxEndLevel3)));
            maxMin.setMaxEndNone(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MaxEndNone)));

            maxMin.setMinNoShow(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinNoShow)));
            maxMin.setMinStartLevel1(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinStartLevel1)));
            maxMin.setMinStartLevel2(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinStartLevel2)));
            maxMin.setMinPreloadCargo(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinPreloadCargo)));
            maxMin.setMinPreloadHatch(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinPreloadHatch)));
            maxMin.setMinRocketTopC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinRocketTopC)));
            maxMin.setMinRocketTopH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinRocketTopH)));
            maxMin.setMinRocketMiddleC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinRocketMiddleC)));
            maxMin.setMinRocketMiddleH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinRocketMiddleH)));
            maxMin.setMinRocketBottomC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinRocketBottomC)));
            maxMin.setMinRocketBottomH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinRocketBottomH)));
            maxMin.setMinCargoShipC(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinCargoShipC)));
            maxMin.setMinCargoShipH(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinCargoShipH)));
            maxMin.setMinCrossHubLine(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinCrossHubLine)));
            maxMin.setMinDisabled(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinRobotDisabled)));
            maxMin.setMinRedCard(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinRedCard)));
            maxMin.setMinYellowCard(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinYellowCard)));
            maxMin.setMinFoul(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinFouls)));
            maxMin.setMinTechFoul(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinTechFouls)));
            maxMin.setMinEndLevel1(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinEndLevel1)));
            maxMin.setMinEndLevel2(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinEndLevel2)));
            maxMin.setMinEndLevel3(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinEndLevel3)));
            maxMin.setMinEndNone(cursor.getInt(cursor.getColumnIndex(MaxMin.KEY_MinEndNone)));
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        //returns a maxMin row object with the values from the database
        return maxMin;
    }
}
