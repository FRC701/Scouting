package com.vandenrobotics.stats.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vandenrobotics.stats.app.App;
import com.vandenrobotics.stats.data.model.Competitions;
import com.vandenrobotics.stats.data.model.MatchInfo;
import com.vandenrobotics.stats.data.model.Matches;
import com.vandenrobotics.stats.data.model.PitData;
import com.vandenrobotics.stats.data.model.Stats;
import com.vandenrobotics.stats.data.model.Teams;
import com.vandenrobotics.stats.data.repo.CompetitionsRepo;
import com.vandenrobotics.stats.data.repo.MatchInfoRepo;
import com.vandenrobotics.stats.data.repo.MatchesRepo;
import com.vandenrobotics.stats.data.repo.PitDataRepo;
import com.vandenrobotics.stats.data.repo.StatsRepo;
import com.vandenrobotics.stats.data.repo.TeamsRepo;

/**
 * Created by Programming701-A on 12/15/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    //update when making any changes to tables or indexes
    private static final int DATABASE_VERSION = 49;
    private static final String DATABASE_NAME = "stats.db";
    private static final String TAG = DataBaseHelper.class.getSimpleName();

    public DataBaseHelper(){
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    //creates all tables using the string from each of the repo
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TeamsRepo.createTable());
        db.execSQL(PitDataRepo.createTable());
        db.execSQL(CompetitionsRepo.createTable());
        db.execSQL(MatchesRepo.createTable());
        db.execSQL(MatchInfoRepo.createTable());
        db.execSQL(MatchInfoRepo.createIndex());
        db.execSQL(StatsRepo.createTable());
        db.execSQL(StatsRepo.createIndex());

}
    @Override
    //drops all tables if there is any change to the database version
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        db.execSQL("Drop Table if Exists "+ Competitions.TABLE);
        db.execSQL("Drop Table if Exists "+ Matches.TABLE);
        db.execSQL("Drop Table if Exists "+ MatchInfo.TABLE);
        db.execSQL("Drop Table if Exists "+ PitData.TABLE);
        db.execSQL("Drop Table if Exists "+ Stats.TABLE);
        db.execSQL("Drop Table if Exists "+ Teams.TABLE);
        onCreate(db);
    }
}
