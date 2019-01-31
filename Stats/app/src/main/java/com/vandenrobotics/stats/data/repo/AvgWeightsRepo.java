package com.vandenrobotics.stats.data.repo;
    
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vandenrobotics.stats.data.DatabaseManager;
import com.vandenrobotics.stats.data.model.Competitions;
import com.vandenrobotics.stats.data.model.AvgWeights;
import com.vandenrobotics.stats.data.model.Teams;

    /**
     * Created by Programming701-A on 12/18/2017.
     */

    public class AvgWeightsRepo {

        private AvgWeightsRepo avgWeights;

        private final String TAG = com.vandenrobotics.stats.data.repo.AvgWeightsRepo.class.getSimpleName();
        public AvgWeightsRepo(){
            avgWeights = new AvgWeightsRepo();
        }

        //Holds String to execute SQLite where the AvgWeights Table is created and specified how it is made
        public static String createTable(){
            return "CREATE TABLE " + AvgWeights.TABLE + "("
                    + AvgWeights.KEY_TeamNum + " INTEGER not null , "
                    //Makes sure the match position is between 1 and 6 for each tablet
                    + AvgWeights.KEY_PNoShow + " REAL , "
                    + AvgWeights.KEY_PStartLevel1 + " REAL , "
                    + AvgWeights.KEY_PStartLevel2 + " REAL , "
                    + AvgWeights.KEY_PPreloadCargo + " REAL , "
                    + AvgWeights.KEY_PPreloadHatch + " REAL , "
                    + AvgWeights.KEY_AvgRocketTC + " REAL , "
                    + AvgWeights.KEY_AvgRocketTH + " REAL , "
                    + AvgWeights.KEY_AvgRocketMC + " REAL , "
                    + AvgWeights.KEY_AvgRocketMH + " REAL , "
                    + AvgWeights.KEY_AvgRocketBC + " REAL , "
                    + AvgWeights.KEY_AvgRocketBH + " REAL , "
                    + AvgWeights.KEY_AvgCargoShipC + " REAL , "
                    + AvgWeights.KEY_AvgCargoShipH + " REAL , "
                    + AvgWeights.KEY_PCrossHubline + " REAL , "
                    + AvgWeights.KEY_PEndLevel1 + " REAL , "
                    + AvgWeights.KEY_PEndLevel2 + " REAL , "
                    + AvgWeights.KEY_PEndLevel3 + " REAL , "
                    + AvgWeights.KEY_PEndNone + " REAL , "
                    + AvgWeights.KEY_PRobotDisabled + " REAL , "
                    + AvgWeights.KEY_PRedCard + " REAL , "
                    + AvgWeights.KEY_PYellowCard + " REAL , "
                    + AvgWeights.KEY_AvgFouls + " REAL , "
                    + AvgWeights.KEY_AvgTechFouls + " REAL , "
                    + AvgWeights.KEY_PDefense + " REAL , "
                    + AvgWeights.KEY_AvgOff + " REAL , "
                    + AvgWeights.KEY_AvgDef + " REAL , "
                    + AvgWeights.KEY_AvgTotal + " REAL , "
                    + AvgWeights.KEY_AvgNeg + " REAL , " +
                    //makes the CompId, MatchNum and MatchPos Primary Key so there needs
                    //to be a unique combination of these attributes in each row in the AvgWeights table
                    //makes sure CompId column exists in the Competitions Table
                    //makes sure TeamNum column exists in the Team Table
                     " FOREIGN KEY ( " + AvgWeights.KEY_TeamNum + " ) REFERENCES " + Teams.TABLE
                    + " ( " + Teams.KEY_TeamNumber + " ))";
        }

        //Holds String to execute SQLite where the AvgWeights Index is created to specify that there
        //Is a unique combination of CompId, Match# and Team# in every row in the avgWeights table
        public static String createIndex(){
            return "CREATE UNIQUE INDEX '" + AvgWeights.INDEX + "' ON "
                    + AvgWeights.TABLE
                    + " ( "+ AvgWeights.KEY_TeamNum + " )" ;
        }

        //inserts all values of a avgWeights row object into the sql database
        public int insertAll(AvgWeights avgWeights) {
            int avgWeightsId;
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
            ContentValues values = new ContentValues();
            values.put(AvgWeights.KEY_TeamNum, avgWeights.getTeamNum());
            values.put(AvgWeights.KEY_PNoShow, avgWeights.getPercentNoShow());
            values.put(AvgWeights.KEY_PStartLevel1, avgWeights.getPercentStartLevel1());
            values.put(AvgWeights.KEY_PStartLevel2, avgWeights.getPercentStartLevel2());
            values.put(AvgWeights.KEY_PPreloadCargo, avgWeights.getPercentPreLoadC());
            values.put(AvgWeights.KEY_PPreloadHatch, avgWeights.getPercentPreLoadH());
            values.put(AvgWeights.KEY_AvgRocketTC, avgWeights.getAvgRocketTopC());
            values.put(AvgWeights.KEY_AvgRocketTH, avgWeights.getAvgRocketTopH());
            values.put(AvgWeights.KEY_AvgRocketMC, avgWeights.getAvgRocketMiddleC());
            values.put(AvgWeights.KEY_AvgRocketMH, avgWeights.getAvgRocketMiddleH());
            values.put(AvgWeights.KEY_AvgRocketBC, avgWeights.getAvgRocketBottomC());
            values.put(AvgWeights.KEY_AvgRocketBH, avgWeights.getAvgRocketBottomH());
            values.put(AvgWeights.KEY_AvgCargoShipC, avgWeights.getAvgCargoShipC());
            values.put(AvgWeights.KEY_AvgCargoShipH, avgWeights.getAvgCargoShipH());
            values.put(AvgWeights.KEY_PCrossHubline, avgWeights.getPercentCrossHubLine());
            values.put(AvgWeights.KEY_PEndLevel1, avgWeights.getPercentEndLevel1());
            values.put(AvgWeights.KEY_PEndLevel2, avgWeights.getPercentEndLevel2());
            values.put(AvgWeights.KEY_PEndLevel3, avgWeights.getPercentEndLevel3());
            values.put(AvgWeights.KEY_PEndNone, avgWeights.getPercentEndNone());
            values.put(AvgWeights.KEY_PRobotDisabled, avgWeights.getPercentDisabled());
            values.put(AvgWeights.KEY_PRedCard, avgWeights.getPercentRedCard());
            values.put(AvgWeights.KEY_PYellowCard, avgWeights.getPercentYellowCard());
            values.put(AvgWeights.KEY_AvgFouls, avgWeights.getAvgFouls());
            values.put(AvgWeights.KEY_AvgTechFouls, avgWeights.getAvgTechFouls());
            values.put(AvgWeights.KEY_PDefense, avgWeights.getAvgDefWS());
            values.put(AvgWeights.KEY_AvgOff, avgWeights.getAvgOffWS());
            values.put(AvgWeights.KEY_AvgDef, avgWeights.getAvgDefWS());
            values.put(AvgWeights.KEY_AvgTotal, avgWeights.getAvgTotalWS());
            values.put(AvgWeights.KEY_AvgNeg, avgWeights.getAvgNegWS());


            //check if there is a conflict. It should return -1 if there is a copy of the exact combination of the Primary Keys
            avgWeightsId = (int) db.insertWithOnConflict(AvgWeights.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
            DatabaseManager.getInstance().closeDatabase();
            //return to check conflict
            return avgWeightsId;
        }
        
            

        //deletes all rows in the avgWeights table
//        public void delete( ) {
//            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
//            db.delete(AvgWeights.TABLE, null,null);
//            DatabaseManager.getInstance().closeDatabase();
//        }

        /*
         * All set functions save each part of a single row for each phase of the match
         * using the initial insert that adds the comp, match, team and match pos
         * used for saving data
         * */

        public void setAvgWeights(AvgWeights avgWeights){
            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
            ContentValues values = new ContentValues();
            Log.d("AvgWeightsRepo auto", "team id " + avgWeights.getTeamNum());
            values.put(AvgWeights.KEY_PNoShow, avgWeights.getPercentNoShow());
            values.put(AvgWeights.KEY_PStartLevel1 , avgWeights.getPercentStartLevel1());
            values.put(AvgWeights.KEY_PStartLevel2, avgWeights.getPercentStartLevel2());
            values.put(AvgWeights.KEY_PPreloadCargo , avgWeights.getPercentPreLoadC());
            values.put(AvgWeights.KEY_PPreloadHatch , avgWeights.getPercentPreLoadH());
            values.put(AvgWeights.KEY_AvgRocketTC , avgWeights.getAvgRocketTopC());
            values.put(AvgWeights.KEY_AvgRocketTH , avgWeights.getAvgRocketTopH());
            values.put(AvgWeights.KEY_AvgRocketMC , avgWeights.getAvgRocketMiddleC());
            values.put(AvgWeights.KEY_AvgRocketMH , avgWeights.getAvgRocketMiddleH());
            values.put(AvgWeights.KEY_AvgRocketBC , avgWeights.getAvgRocketBottomC());
            values.put(AvgWeights.KEY_AvgRocketBH , avgWeights.getAvgRocketBottomH());
            values.put(AvgWeights.KEY_AvgCargoShipC , avgWeights.getAvgCargoShipC());
            values.put(AvgWeights.KEY_AvgCargoShipH , avgWeights.getAvgCargoShipH());
            values.put(AvgWeights.KEY_PCrossHubline , avgWeights.getPercentCrossHubLine());
            values.put(AvgWeights.KEY_PDefense , avgWeights.getPercentDefense());
            values.put(AvgWeights.KEY_PEndLevel1, avgWeights.getPercentEndLevel1());
            values.put(AvgWeights.KEY_PEndLevel2 , avgWeights.getPercentEndLevel2());
            values.put(AvgWeights.KEY_PEndLevel3 , avgWeights.getPercentEndLevel3());
            values.put(AvgWeights.KEY_PEndNone , avgWeights.getPercentEndNone());
            values.put(AvgWeights.KEY_PRobotDisabled, avgWeights.getPercentDisabled());
            values.put(AvgWeights.KEY_PRedCard, avgWeights.getPercentRedCard());
            values.put(AvgWeights.KEY_PYellowCard, avgWeights.getPercentYellowCard());
            values.put(AvgWeights.KEY_AvgFouls, avgWeights.getAvgFouls());
            values.put(AvgWeights.KEY_AvgTechFouls, avgWeights.getAvgTechFouls());
            values.put(AvgWeights.KEY_AvgOff, avgWeights.getAvgOffWS());
            values.put(AvgWeights.KEY_AvgDef, avgWeights.getAvgDefWS());
            values.put(AvgWeights.KEY_AvgTotal, avgWeights.getAvgTotalWS());
            values.put(AvgWeights.KEY_AvgNeg, avgWeights.getAvgNegWS());
            
            db.update(AvgWeights.TABLE, values, " AND "
                    + AvgWeights.KEY_TeamNum + " = " + avgWeights.getTeamNum(), null);
            DatabaseManager.getInstance().closeDatabase();
        }

        public AvgWeights getAvgWeights(int team) {
            AvgWeights avgWeights = new AvgWeights();

            SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
            //makes the selection query for the avgWeights table to get the auto avgWeights
            String selectQuery = " SELECT AvgWeights." + AvgWeights.KEY_PNoShow
                    + ", AvgWeights." + AvgWeights.KEY_PStartLevel1
                    + ", AvgWeights." + AvgWeights.KEY_PStartLevel2
                    + ", AvgWeights." + AvgWeights.KEY_PPreloadCargo
                    + ", AvgWeights." + AvgWeights.KEY_PPreloadHatch
                    + ", AvgWeights." + AvgWeights.KEY_AvgRocketTC
                    + ", AvgWeights." + AvgWeights.KEY_AvgRocketTH
                    + ", AvgWeights." + AvgWeights.KEY_AvgRocketMC
                    + ", AvgWeights." + AvgWeights.KEY_AvgRocketMH
                    + ", AvgWeights." + AvgWeights.KEY_AvgRocketBC
                    + ", AvgWeights." + AvgWeights.KEY_AvgRocketBH
                    + ", AvgWeights." + AvgWeights.KEY_AvgCargoShipC
                    + ", AvgWeights." + AvgWeights.KEY_AvgCargoShipH
                    + ", AvgWeights." + AvgWeights.KEY_PCrossHubline
                    + ", AvgWeights." + AvgWeights.KEY_PEndLevel1
                    + ", AvgWeights." + AvgWeights.KEY_PEndLevel2
                    + ", AvgWeights." + AvgWeights.KEY_PEndLevel3
                    + ", AvgWeights." + AvgWeights.KEY_PEndNone
                    + ", AvgWeights." + AvgWeights.KEY_PRobotDisabled
                    + ", AvgWeights." + AvgWeights.KEY_PRedCard
                    + ", AvgWeights." + AvgWeights.KEY_PYellowCard
                    + ", AvgWeights." + AvgWeights.KEY_AvgFouls
                    + ", AvgWeights." + AvgWeights.KEY_AvgTechFouls
                    + ", AvgWeights." + AvgWeights.KEY_AvgOff
                    + ", AvgWeights." + AvgWeights.KEY_AvgDef
                    + ", AvgWeights." + AvgWeights.KEY_AvgTotal
                    + ", AvgWeights." + AvgWeights.KEY_AvgNeg
                    + ", AvgWeights." + AvgWeights.KEY_PDefense
                    + " FROM " + AvgWeights.TABLE
                    + " WHERE AvgWeights." + AvgWeights.KEY_TeamNum + " = \"" + team + "\"";

            Log.d(TAG, selectQuery);
            //uses the selection query to get rows from the database one at a time
            Cursor cursor = db.rawQuery(selectQuery, null);
            //gets the first row that matches the specifications from the selection query
            if (cursor.moveToFirst()) {
                avgWeights.setNoShow(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_PNoShow)));
                avgWeights.setPercentStartLevel1(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_PStartLevel1)));
                avgWeights.setPercentStartLevel2(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_PStartLevel2)));
                avgWeights.setPercentPreLoadC(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_PPreloadCargo)));
                avgWeights.setPercentPreLoadH(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_PPreloadHatch)));
                avgWeights.setAvgRocketTopC(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgRocketTC)));
                avgWeights.setAvgRocketTopH(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgRocketTH)));
                avgWeights.setAvgRocketMiddleC(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgRocketMC)));
                avgWeights.setAvgRocketMiddleH(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgRocketMH)));
                avgWeights.setAvgRocketBottomC(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgRocketBC)));
                avgWeights.setAvgRocketBottomH(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgRocketBH)));
                avgWeights.setAvgCargoShipC(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgCargoShipC)));
                avgWeights.setAvgCargoShipH(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgCargoShipH)));
                avgWeights.setPercentCrossHubLine(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_PCrossHubline)));
                avgWeights.setPercentDisabled(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_PRobotDisabled)));
                avgWeights.setPercentRedCard(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_PRedCard)));
                avgWeights.setPercentYellowCard(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_PYellowCard)));
                avgWeights.setAvgFouls(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgFouls)));
                avgWeights.setAvgTechFouls(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgTechFouls)));
                avgWeights.setPercentEndLevel1(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_PEndLevel1)));
                avgWeights.setPercentEndLevel2(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_PEndLevel2)));
                avgWeights.setPercentEndLevel3(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_PEndLevel3)));
                avgWeights.setPercentEndNone(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_PEndNone)));
                avgWeights.setAvgOffWS(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgOff)));
                avgWeights.setAvgDefWS(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgDef)));
                avgWeights.setAvgTotalWS(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgTotal)));
                avgWeights.setAvgNegWS(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgNeg)));
                avgWeights.setPercentDefense(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_PDefense)));

            }


            return avgWeights;
        }    }