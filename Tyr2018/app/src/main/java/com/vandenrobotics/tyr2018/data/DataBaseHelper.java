package com.vandenrobotics.tyr2018.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vandenrobotics.tyr2018.app.App;
import com.vandenrobotics.tyr2018.data.model.PitData;
import com.vandenrobotics.tyr2018.data.model.TeamInfo;
import com.vandenrobotics.tyr2018.data.model.Teams;
import com.vandenrobotics.tyr2018.data.repo.PitDataRepo;
import com.vandenrobotics.tyr2018.data.repo.TeamInfoRepo;
import com.vandenrobotics.tyr2018.data.repo.TeamsRepo;

/**
 * Created by Programming701-A on 12/15/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    //update when making any changes to tables or indexes
    private static final int DATABASE_VERSION = 45;
    private static final String DATABASE_NAME = "TyrData.db";
    private static final String TAG = DataBaseHelper.class.getSimpleName();

    public DataBaseHelper(){
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    //creates all tables using the string from each of the repo
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TeamsRepo.createTable());
        db.execSQL(PitDataRepo.createTable());
        db.execSQL(TeamInfoRepo.createTable());
        db.execSQL(TeamInfoRepo.createIndex());

}
    @Override
    //drops all tables if there is any change to the database version
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        db.execSQL("Drop Table if Exists "+ Teams.TABLE);
        db.execSQL("Drop Table if Exists "+ TeamInfo.TABLE);
        db.execSQL("Drop Table if Exists "+ PitData.TABLE);
        onCreate(db);
    }
}
