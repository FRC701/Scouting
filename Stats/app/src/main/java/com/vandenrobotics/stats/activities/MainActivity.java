package com.vandenrobotics.stats.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vandenrobotics.stats.R;
import com.vandenrobotics.stats.data.DatabaseManager;
import com.vandenrobotics.stats.data.model.Matches;
import com.vandenrobotics.stats.data.model.PitData;
import com.vandenrobotics.stats.data.model.Stats;
import com.vandenrobotics.stats.data.repo.MatchesRepo;

import java.io.File;
import java.io.PipedReader;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db1;     // Data in table called 'table1'
    private SQLiteDatabase db2;     // NOTE: data is in table named 'tabel1'. This is misspelled!!!
                                    // shouldn't matter since when doing our db they will be uniform

    private final File DOWNLOADS_FILE = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

    private MatchesRepo matchrepo = new MatchesRepo();

    // View elements
    private Button pitData;
    private Button avgWeight;
    private Button minMax;
    private Button matchPrediction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);

        mergeDatabases();

        pitData = (Button) findViewById(R.id.pitData);
        avgWeight = (Button) findViewById(R.id.avgWeights);
        minMax = (Button) findViewById(R.id.minMax);
        matchPrediction = (Button) findViewById(R.id.matchPrediction);
    }

    public void mergeDatabases() {
        SQLiteDatabase mainDB = DatabaseManager.getInstance().openDatabase();

        //Attach all the databases
        attachDB("TabletData1.db", "db1");
        attachDB("TabletData2.db", "db2");
        attachDB("TabletData3.db", "db3");
        attachDB("TabletData4.db", "db4");
        attachDB("TabletData5.db", "db5");
        attachDB("TabletData6.db", "db6");

        // Merge database
        mainDB.execSQL(genAddMatchesDataScript("db1"));
        mainDB.execSQL(genAddStatsDataScript("db1"));
        mainDB.execSQL(genAddPitDataScript("db1"));

        mainDB.execSQL(genAddMatchesDataScript("db2"));
        mainDB.execSQL(genAddStatsDataScript("db2"));
        mainDB.execSQL(genAddPitDataScript("db2"));

        mainDB.execSQL(genAddMatchesDataScript("db3"));
        mainDB.execSQL(genAddStatsDataScript("db3"));
        mainDB.execSQL(genAddPitDataScript("db3"));

        mainDB.execSQL(genAddMatchesDataScript("db3"));
        mainDB.execSQL(genAddStatsDataScript("db3"));
        mainDB.execSQL(genAddPitDataScript("db3"));

        mainDB.execSQL(genAddMatchesDataScript("db4"));
        mainDB.execSQL(genAddStatsDataScript("db4"));
        mainDB.execSQL(genAddPitDataScript("db4"));

        mainDB.execSQL(genAddMatchesDataScript("db5"));
        mainDB.execSQL(genAddStatsDataScript("db5"));
        mainDB.execSQL(genAddPitDataScript("db5"));

        mainDB.execSQL(genAddMatchesDataScript("db6"));
        mainDB.execSQL(genAddStatsDataScript("db6"));
        mainDB.execSQL(genAddPitDataScript("db6"));

        // Detach all databse
        detachDB("db1");
        detachDB("db2");
        detachDB("db3");
        detachDB("db4");
        detachDB("db5");
        detachDB("db6");
    }

    public void pit(View view){
        Intent intent = new Intent(this, PitScout.class );
        startActivity(intent);
    }
    public void avg(View view){
        Intent intent = new Intent(this, avgWeight.class );
        startActivity(intent);
    }
    public void min (View view){
        Intent intent = new Intent(this, MinMax.class );
        startActivity(intent);
    }
    public void matchpred(View view){
        Intent intent = new Intent(this, MatchPrediction.class );
        startActivity(intent);
    }

    public String genAddPitDataScript(String attachedDBName){
        return  "" +
                "INSERT INTO " + PitData.TABLE + " " +
                "SELECT * " +
                "FROM "+attachedDBName+".PitData S " +
                "WHERE NOT EXISTS ("+
                "SELECT T."+PitData.KEY_TeamNum + " " +
                "FROM "+ PitData.TABLE+" T " +
                "WHERE T."+PitData.KEY_TeamNum+" = S."+PitData.KEY_TeamNum +
                ")";
    }

    public String genAddStatsDataScript(String attachedDBName) {
        return  "" +
                "INSERT INTO " + Stats.TABLE + " " +
                "SELECT * " +
                "FROM "+attachedDBName+"." + Stats.TABLE +" S " +
                "WHERE NOT EXISTS ("+
                "SELECT T."+Stats.KEY_TeamNum+", T."+Stats.KEY_MatchNum + " " +
                "FROM "+Stats.TABLE+" T " +
                "WHERE T."+Stats.KEY_TeamNum+" = S."+Stats.KEY_TeamNum +" AND T."+Stats.KEY_MatchNum+" = S." + Stats.KEY_MatchNum+
                ")";
    }

    public String genAddMatchesDataScript(String attachedDBName) {
        return  "" +
                "INSERT INTO " + Matches.TABLE + " " +
                "SELECT * " +
                "FROM "+attachedDBName+"." + Matches.TABLE +" S " +
                "WHERE NOT EXISTS ("+
                "SELECT T."+Matches.KEY_CompId+", T."+Matches.KEY_MatchNumber +", T."+Matches.KEY_TeamNumber + " " +
                "FROM "+Matches.TABLE+" T " +
                "WHERE T."+Matches.KEY_CompId+" = S."+Matches.KEY_CompId+" AND T." + Matches.KEY_MatchNumber+" = S."+Matches.KEY_MatchNumber+" AND T." +
                Matches.KEY_TeamNumber+" = S."+Matches.KEY_TeamNumber+
                ")";
    }

    public void attachDB(String fileName, String name) {
        SQLiteDatabase mainDB = DatabaseManager.getInstance().openDatabase();
        String attachDB = "ATTACH DATABASE '"+DOWNLOADS_FILE.getAbsolutePath()+"/"+fileName+"' as "+name;

        mainDB.execSQL(attachDB);
    }

    public void detachDB(String name) {
        SQLiteDatabase mainDB = DatabaseManager.getInstance().openDatabase();
        String detachDB = "DETACH "+name;

        mainDB.execSQL(detachDB);
    }

}
