package com.vandenrobotics.saga2018.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vandenrobotics.saga2018.data.DatabaseManager;
import com.vandenrobotics.saga2018.data.model.Competitions;
import com.vandenrobotics.saga2018.data.model.Ownership;

import java.util.ArrayList;

/**
 * Created by Programming701-A on 1/20/2018.
 */

public class OwnershipRepo {

    private final String TAG = OwnershipRepo.class.getSimpleName();

    //Holds String to execute SQLite where the Ownership Table is created and specified how it is made
    public static String createTable(){
        return "CREATE TABLE " + Ownership.TABLE + "("
                //makes a Primary Key that auto increments to make a variable # of times ownership is changed
                + Ownership.KEY_PRIMARY + " INTEGER not null PRIMARY KEY AUTOINCREMENT , "
                + Ownership.KEY_CompId + " TEXT not null , "
                + Ownership.KEY_MatchNumber + " INTEGER not null , "
                + Ownership.KEY_Balance + " TEXT , "
                + Ownership.KEY_Owner + " TEXT , "
                + Ownership.KEY_Time + " TEXT , "
                //makes sure CompId column exists in the Competitions Table
                + " FOREIGN KEY ( " + Ownership.KEY_CompId + " ) REFERENCES " + Competitions.TABLE
                + " ( " + Competitions.KEY_CompId + " )) ";
    }

    //inserts a new ownership row into the database
    public int insert(Ownership ownership){
        int ownId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Ownership.KEY_CompId, ownership.getCompId());
        values.put(Ownership.KEY_MatchNumber, ownership.getMatchNum());
        values.put(Ownership.KEY_Balance, ownership.getBalance());
        values.put(Ownership.KEY_Owner, ownership.getOwner());
        values.put(Ownership.KEY_Time, ownership.getTime());

        ownId = (int) db.insertWithOnConflict(Ownership.TABLE, null , values, SQLiteDatabase.CONFLICT_IGNORE);
        DatabaseManager.getInstance().closeDatabase();
        return ownId;
    }

    public int update(Ownership ownership){
        int ownId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Ownership.KEY_Balance, ownership.getBalance());
        values.put(Ownership.KEY_Owner, ownership.getOwner());
        values.put(Ownership.KEY_Time, ownership.getTime());

        ownId = db.updateWithOnConflict(Ownership.TABLE, values, Ownership.KEY_CompId + " =  \"" + ownership.getCompId() + "\" AND "
                + Ownership.KEY_MatchNumber + " = " + ownership.getMatchNum(), null, SQLiteDatabase.CONFLICT_IGNORE);
        DatabaseManager.getInstance().closeDatabase();
        return ownId;
    }

    public void deleteForComp(String event){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Ownership.TABLE, Ownership.KEY_CompId + " =  \"" + event + "\"", null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void deleteForCompAndMatch(String event, int match){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Ownership.TABLE, Ownership.KEY_CompId + " =  \"" + event + "\" AND "
                + Ownership.KEY_MatchNumber + " = " + match, null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public int checkForSameMatch(String event, int match){
        int sameMatchOwnerships = 0;

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = " SELECT Ownership." + Ownership.KEY_CompId
                + ", Ownership." + Ownership.KEY_MatchNumber
                + " FROM " + Ownership.TABLE
                + " WHERE Ownership." + Ownership.KEY_CompId + " = \"" + event + "\""
                + " AND Ownership." + Ownership.KEY_MatchNumber + " = " + match;

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                sameMatchOwnerships++;
            }while(cursor.moveToNext());
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        Log.d(TAG, "The number of ownerships with the same match is: " + sameMatchOwnerships);
        return sameMatchOwnerships;
    }

    public String getLastSwitch1Owner(String event, int match){
        final String switch1 = "Sw1";
        String owner = "None";

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = " SELECT Ownership." + Ownership.KEY_Time
                + ", Ownership." + Ownership.KEY_Owner
                + " FROM " + Ownership.TABLE
                + " WHERE Ownership." + Ownership.KEY_CompId + " = \"" + event + "\""
                + " AND  Ownership." + Ownership.KEY_MatchNumber + " = " + match
                + " AND Ownership." + Ownership.KEY_Balance + " = \"" + switch1 + "\"";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<Ownership> switchOwners = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                Ownership ownership = new Ownership();
                ownership.setTime(cursor.getString(cursor.getColumnIndex(Ownership.KEY_Time)));
                ownership.setOwner(cursor.getString(cursor.getColumnIndex(Ownership.KEY_Owner)));

                switchOwners.add(ownership);
            }while(cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        ArrayList<Double> times = new ArrayList<>();
        for (Ownership ownership : switchOwners) {
            times.add(timeStringToInt(ownership.getTime()));
        }
        double smallestTime = 135;
        for (int i = 0; i < times.size(); i++){
            if (smallestTime > times.get(i)){
                smallestTime = times.get(i);
            }
        }

        int largestTimeIndex  = times.indexOf(smallestTime);

        try {
            return switchOwners.get(largestTimeIndex).getOwner();
        }catch (ArrayIndexOutOfBoundsException e){
            return owner;
        }
    }

    public String getLastScaleOwner(String event, int match){
        final String scale = "Scl";
        String owner = "None";

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = " SELECT Ownership." + Ownership.KEY_Time
                + ", Ownership." + Ownership.KEY_Owner
                + " FROM " + Ownership.TABLE
                + " WHERE Ownership." + Ownership.KEY_CompId + " = \"" + event + "\""
                + " AND  Ownership." + Ownership.KEY_MatchNumber + " = " + match
                + " AND Ownership." + Ownership.KEY_Balance + " = \"" + scale + "\"";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<Ownership> switchOwners = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                Ownership ownership = new Ownership();
                ownership.setTime(cursor.getString(cursor.getColumnIndex(Ownership.KEY_Time)));
                ownership.setOwner(cursor.getString(cursor.getColumnIndex(Ownership.KEY_Owner)));

                switchOwners.add(ownership);
            }while(cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        ArrayList<Double> times = new ArrayList<>();
        for (Ownership ownership : switchOwners) {
            times.add(timeStringToInt(ownership.getTime()));
        }
        double smallestTime = 135;
        for (int i = 0; i < times.size(); i++){
            if (smallestTime > times.get(i)){
                smallestTime = times.get(i);
            }
        }
        int largestTimeIndex  = times.indexOf(smallestTime);

        try {
            return switchOwners.get(largestTimeIndex).getOwner();

        }catch (ArrayIndexOutOfBoundsException e){
            return owner;
        }
    }

    public String getLastSwitch2Owner(String event, int match){
        final String switch2 = "Sw2";
        String owner = "None";

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = " SELECT Ownership." + Ownership.KEY_Time
                + ", Ownership." + Ownership.KEY_Owner
                + " FROM " + Ownership.TABLE
                + " WHERE Ownership." + Ownership.KEY_CompId + " = \"" + event + "\""
                + " AND Ownership." + Ownership.KEY_MatchNumber + " = " + match
                + " AND Ownership." + Ownership.KEY_Balance + " = \"" + switch2 + "\"";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);

        ArrayList<Ownership> switchOwners = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                Ownership ownership = new Ownership();
                ownership.setTime(cursor.getString(cursor.getColumnIndex(Ownership.KEY_Time)));
                ownership.setOwner(cursor.getString(cursor.getColumnIndex(Ownership.KEY_Owner)));

                switchOwners.add(ownership);
            }while(cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        ArrayList<Double> times = new ArrayList<>();
        for (Ownership ownership : switchOwners) {
            times.add(timeStringToInt(ownership.getTime()));
        }
        double smallestTime = 135;
        for (int i = 0; i < times.size(); i++){
            if (smallestTime > times.get(i)){
                smallestTime = times.get(i);
            }
        }
        int largestTimeIndex  = times.indexOf(smallestTime);

        try {
            return switchOwners.get(largestTimeIndex).getOwner();
        }catch (ArrayIndexOutOfBoundsException e){
            return owner;
        }
    }

    private double timeStringToInt(String time) {
        double min = 0;
        double sec;

        String[] minAndSec;

        if (!time.equals("0")){

            minAndSec = time.split(":");


            min = Double.parseDouble(minAndSec[0]);
            sec = Double.parseDouble(minAndSec[1]);

        }else{
            sec = Integer.parseInt(time);
        }

        sec += min*60;

        return sec;
    }

}
