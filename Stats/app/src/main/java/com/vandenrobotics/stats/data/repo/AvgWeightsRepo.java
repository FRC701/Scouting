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
            String selectQuery = " SELECT AvgWeights." + AvgWeights.KEY_AvgOff
                    + ", AvgWeights." + AvgWeights.KEY_AvgDef
                    + ", AvgWeights." + AvgWeights.KEY_AvgTotal
                    + ", AvgWeights." + AvgWeights.KEY_AvgNeg
                    + " FROM " + AvgWeights.TABLE
                    + " WHERE AvgWeights." + AvgWeights.KEY_TeamNum + " = \"" + team + "\"";

            Log.d(TAG, selectQuery);
            //uses the selection query to get rows from the database one at a time
            Cursor cursor = db.rawQuery(selectQuery, null);
            //gets the first row that matches the specifications from the selection query
            if (cursor.moveToFirst()) {
                avgWeights.setAvgOffWS(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgOff)));
                avgWeights.setAvgDefWS(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgDef)));
                avgWeights.setAvgTotalWS(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgTotal)));
                avgWeights.setAvgNegWS(cursor.getDouble(cursor.getColumnIndex(AvgWeights.KEY_AvgNeg)));

            }


            return avgWeights;
        }    }