package com.vandenrobotics.saga2018.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vandenrobotics.saga2018.app.App;
import com.vandenrobotics.saga2018.data.model.Competitions;
import com.vandenrobotics.saga2018.data.model.Cubes;
import com.vandenrobotics.saga2018.data.model.MatchInfo;
import com.vandenrobotics.saga2018.data.model.Matches;
import com.vandenrobotics.saga2018.data.model.Ownership;
import com.vandenrobotics.saga2018.data.model.PitData;
import com.vandenrobotics.saga2018.data.model.Stats;
import com.vandenrobotics.saga2018.data.model.Teams;
import com.vandenrobotics.saga2018.data.model.Vault;
import com.vandenrobotics.saga2018.data.repo.CompetitionsRepo;
import com.vandenrobotics.saga2018.data.repo.CubesRepo;
import com.vandenrobotics.saga2018.data.repo.MatchInfoRepo;
import com.vandenrobotics.saga2018.data.repo.MatchesRepo;
import com.vandenrobotics.saga2018.data.repo.OwnershipRepo;
import com.vandenrobotics.saga2018.data.repo.PitDataRepo;
import com.vandenrobotics.saga2018.data.repo.StatsRepo;
import com.vandenrobotics.saga2018.data.repo.TeamsRepo;
import com.vandenrobotics.saga2018.data.repo.VaultRepo;

/**
 * Created by Programming701-A on 12/15/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    //update when making any changes to tables or indexes
    private static final int DATABASE_VERSION = 42;
    private static final String DATABASE_NAME = "ScoutingData.db";
    private static final String TAG = DataBaseHelper.class.getSimpleName();

    public DataBaseHelper(){
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    //creates all tables using the string from each of the repo
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CompetitionsRepo.createTable());
        db.execSQL(TeamsRepo.createTable());
        db.execSQL(MatchesRepo.createTable());
        db.execSQL(MatchesRepo.createIndex());
        db.execSQL(StatsRepo.createTable());
        db.execSQL(StatsRepo.createIndex());
        db.execSQL(MatchInfoRepo.createTable());
        db.execSQL(MatchInfoRepo.createIndex());
        db.execSQL(OwnershipRepo.createTable());
        db.execSQL(PitDataRepo.createTable());
        db.execSQL(CubesRepo.createTable());
        db.execSQL(VaultRepo.createTable());
    }

    @Override
    //drops all tables if there is any change to the database version
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d -> %d)", oldVersion, newVersion));

        db.execSQL("Drop Table if Exists "+ Competitions.TABLE);
        db.execSQL("Drop Table if Exists "+ Matches.TABLE);
        db.execSQL("Drop Table if Exists "+ Teams.TABLE);
        db.execSQL("Drop Table if Exists "+ Stats.TABLE);
        db.execSQL("Drop Table if Exists "+ MatchInfo.TABLE);
        db.execSQL("Drop Table if Exists "+ Ownership.TABLE);
        db.execSQL("Drop Table if Exists "+ PitData.TABLE );
        db.execSQL("Drop Table if Exists "+ Cubes.TABLE );
        db.execSQL("Drop Table if Exists "+ Vault.TABLE );
        onCreate(db);
    }


}
