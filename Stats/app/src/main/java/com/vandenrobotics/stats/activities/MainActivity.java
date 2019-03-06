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
        merge2();
        merge3();
        merge4();


        pitData = (Button) findViewById(R.id.pitData);
        avgWeight = (Button) findViewById(R.id.avgWeights);
        minMax = (Button) findViewById(R.id.minMax);
        matchPrediction = (Button) findViewById(R.id.matchPrediction);
    }

    public void merge2() {

        // Notes:
        //          In order for this method to work, matchNumber cannot be primary key since
        //          multiple matches will be going in that are the same number but different teams
        //
        //          These strings can be place inside of match repo or elsewhere, probably making a function
        //          to generate them. Doing both manually because of differences in names

        try {
            SQLiteDatabase stats = DatabaseManager.getInstance().openDatabase();

            String attachDB1 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData1.db" + "' as db1";
            String attachDB2 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData2.db" + "' as db2";
            String attachDB3 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData3.db" + "' as db3";
            String attachDB4 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData4.db" + "' as db4";
            String attachDB5 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData5.db" + "' as db5";
            String attachDB6 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData6.db" + "' as db6";

            String addDB1Data = "" +
                    "INSERT INTO " + Matches.TABLE + " " +
                    "SELECT " + "S.comp, S.matchNum, S.teamNumber, S.matchPos " +
                    "FROM db1." + Matches.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+Matches.KEY_CompId+", T."+Matches.KEY_MatchNumber +", T."+Matches.KEY_TeamNumber + " " +
                    "FROM "+Matches.TABLE+" T " +
                    "WHERE T."+Matches.KEY_CompId + " = S.comp AND T." + Matches.KEY_MatchNumber+" = S.matchNum AND T." +
                        Matches.KEY_TeamNumber + " = S.teamNumber" +
                    ")";

            String addDB2Data = "" +
                    "INSERT INTO " + Matches.TABLE + "( "+ Matches.KEY_COLUMNS+ ") " +
                    "SELECT " + "S.comp, S.team, S.matches, S.score " +
                    "FROM db2." + Matches.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+Matches.KEY_CompId+", T."+Matches.KEY_MatchNumber +", T."+Matches.KEY_TeamNumber + " " +
                    "FROM "+Matches.TABLE+" T " +
                    "WHERE T."+Matches.KEY_CompId + " = S.comp AND T." + Matches.KEY_MatchNumber+" = S.matchNum AND T." +
                    Matches.KEY_TeamNumber + " = S.teamNumber" +
                    ")";

            String addDB3Data = "" +
                    "INSERT INTO " + Matches.TABLE + "( "+ Matches.KEY_COLUMNS+ ") " +
                    "SELECT " + "S.comp, S.team, S.matches, S.score " +
                    "FROM db3." + Matches.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+Matches.KEY_CompId+", T."+Matches.KEY_MatchNumber +", T."+Matches.KEY_TeamNumber + " " +
                    "FROM "+Matches.TABLE+" T " +
                    "WHERE T."+Matches.KEY_CompId + " = S.comp AND T." + Matches.KEY_MatchNumber+" = S.matchNum AND T." +
                    Matches.KEY_TeamNumber + " = S.teamNumber" +
                    ")";

            String addDB4Data = "" +
                    "INSERT INTO " + Matches.TABLE + "( "+ Matches.KEY_COLUMNS+ ") " +
                    "SELECT " + "S.comp, S.team, S.matches, S.score " +
                    "FROM db4." + Matches.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+Matches.KEY_CompId+", T."+Matches.KEY_MatchNumber +", T."+Matches.KEY_TeamNumber + " " +
                    "FROM "+Matches.TABLE+" T " +
                    "WHERE T."+Matches.KEY_CompId + " = S.comp AND T." + Matches.KEY_MatchNumber+" = S.matchNum AND T." +
                    Matches.KEY_TeamNumber + " = S.teamNumber" +
                    ")";

            String addDB5Data = "" +
                    "INSERT INTO " + Matches.TABLE + "( "+ Matches.KEY_COLUMNS+ ") " +
                    "SELECT " + "S.comp, S.team, S.matches, S.score " +
                    "FROM db5." + Matches.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+Matches.KEY_CompId+", T."+Matches.KEY_MatchNumber +", T."+Matches.KEY_TeamNumber + " " +
                    "FROM "+Matches.TABLE+" T " +
                    "WHERE T."+Matches.KEY_CompId + " = S.comp AND T." + Matches.KEY_MatchNumber+" = S.matchNum AND T." +
                    Matches.KEY_TeamNumber + " = S.teamNumber" +
                    ")";

            String addDB6Data = "" +
                    "INSERT INTO " + Matches.TABLE + "( "+ Matches.KEY_COLUMNS+ ") " +
                    "SELECT " + "S.comp, S.team, S.matches, S.score " +
                    "FROM db6." + Matches.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+Matches.KEY_CompId+", T."+Matches.KEY_MatchNumber +", T."+Matches.KEY_TeamNumber + " " +
                    "FROM "+Matches.TABLE+" T " +
                    "WHERE T."+Matches.KEY_CompId + " = S.comp AND T." + Matches.KEY_MatchNumber+" = S.matchNum AND T." +
                    Matches.KEY_TeamNumber + " = S.teamNumber" +
                    ")";


            String detachDB1 = "DETACH db1";
            String detachDB2 = "DETACH db2";
            String detachDB3 = "DETACH db3";
            String detachDB4 = "DETACH db4";
            String detachDB5 = "DETACH db5";
            String detachDB6 = "DETACH db6";

            stats.execSQL(attachDB1);
            stats.execSQL(attachDB2);
            stats.execSQL(attachDB3);
            stats.execSQL(attachDB4);
            stats.execSQL(attachDB5);
            stats.execSQL(attachDB6);
            stats.execSQL(addDB1Data);
            stats.execSQL(addDB2Data);
            stats.execSQL(addDB3Data);
            stats.execSQL(addDB4Data);
            stats.execSQL(addDB5Data);
            stats.execSQL(addDB6Data);
            stats.execSQL(detachDB1);
            stats.execSQL(detachDB2);
            stats.execSQL(detachDB3);
            stats.execSQL(detachDB4);
            stats.execSQL(detachDB5);
            stats.execSQL(detachDB6);
            Toast.makeText(this, "merged stats", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("Test", "Merge: error " + e);
        }
    }
    public void merge3() {

        // Notes:
        //          In order for this method to work, matchNumber cannot be primary key since
        //          multiple matches will be going in that are the same number but different teams
        //
        //          These strings can be place inside of match repo or elsewhere, probably making a function
        //          to generate them. Doing both manually because of differences in names

        try {
            SQLiteDatabase stats = DatabaseManager.getInstance().openDatabase();

            String attachDB1 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData1.db" + "' as db1";
            String attachDB2 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData2.db" + "' as db2";
            String attachDB3 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData3.db" + "' as db3";
            String attachDB4 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData4.db" + "' as db4";
            String attachDB5 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData5.db" + "' as db5";
            String attachDB6 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData6.db" + "' as db6";

            String addDB1Data = "" +
                    "INSERT INTO " + Stats.TABLE + " " +
                    "SELECT " + Stats.KEY_Columns + " " +
                    "FROM db1." + Stats.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+Stats.KEY_TeamNum+", T."+Stats.KEY_MatchNum + " " +
                    "FROM "+Stats.TABLE+" T " +
                    "WHERE T."+Stats.KEY_TeamNum+" = S."+Stats.KEY_TeamNum +" AND T."+Stats.KEY_MatchNum+" = S." + Stats.KEY_MatchNum+
                    ")";

            String addDB2Data = "" +
                    "INSERT INTO " + Stats.TABLE + " " +
                    "SELECT " + Stats.KEY_Columns + " " +
                    "FROM db2." + Stats.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+Stats.KEY_TeamNum+", T."+Stats.KEY_MatchNum + " " +
                    "FROM "+Stats.TABLE+" T " +
                    "WHERE T."+Stats.KEY_TeamNum+" = S."+Stats.KEY_TeamNum +" AND T."+Stats.KEY_MatchNum+" = S." + Stats.KEY_MatchNum+
                    ")";

            String addDB3Data = "" +
                    "INSERT INTO " + Stats.TABLE + " " +
                    "SELECT " + Stats.KEY_Columns + " " +
                    "FROM db3." + Stats.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+Stats.KEY_TeamNum+", T."+Stats.KEY_MatchNum + " " +
                    "FROM "+Stats.TABLE+" T " +
                    "WHERE T."+Stats.KEY_TeamNum+" = S."+Stats.KEY_TeamNum +" AND T."+Stats.KEY_MatchNum+" = S." + Stats.KEY_MatchNum+
                    ")";

            String addDB4Data = "" +
                    "INSERT INTO " + Stats.TABLE + " " +
                    "SELECT " + Stats.KEY_Columns + " " +
                    "FROM db4." + Stats.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+Stats.KEY_TeamNum+", T."+Stats.KEY_MatchNum + " " +
                    "FROM "+Stats.TABLE+" T " +
                    "WHERE T."+Stats.KEY_TeamNum+" = S."+Stats.KEY_TeamNum +" AND T."+Stats.KEY_MatchNum+" = S." + Stats.KEY_MatchNum+
                    ")";

            String addDB5Data = "" +
                    "INSERT INTO " + Stats.TABLE + " " +
                    "SELECT " + Stats.KEY_Columns + " " +
                    "FROM db5." + Stats.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+Stats.KEY_TeamNum+", T."+Stats.KEY_MatchNum + " " +
                    "FROM "+Stats.TABLE+" T " +
                    "WHERE T."+Stats.KEY_TeamNum+" = S."+Stats.KEY_TeamNum +" AND T."+Stats.KEY_MatchNum+" = S." + Stats.KEY_MatchNum+
                    ")";

            String addDB6Data = "" +
                    "INSERT INTO " + Stats.TABLE + " " +
                    "SELECT " + Stats.KEY_Columns + " " +
                    "FROM db6." + Stats.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+Stats.KEY_TeamNum+", T."+Stats.KEY_MatchNum + " " +
                    "FROM "+Stats.TABLE+" T " +
                    "WHERE T."+Stats.KEY_TeamNum+" = S."+Stats.KEY_TeamNum +" AND T."+Stats.KEY_MatchNum+" = S." + Stats.KEY_MatchNum+
                    ")";

            String detachDB1 = "DETACH db1";
            String detachDB2 = "DETACH db2";
            String detachDB3 = "DETACH db3";
            String detachDB4 = "DETACH db4";
            String detachDB5 = "DETACH db5";
            String detachDB6 = "DETACH db6";

            stats.execSQL(attachDB1);
            stats.execSQL(attachDB2);
            stats.execSQL(attachDB3);
            stats.execSQL(attachDB4);
            stats.execSQL(attachDB5);
            stats.execSQL(attachDB6);
            stats.execSQL(addDB1Data);
            stats.execSQL(addDB2Data);
            stats.execSQL(addDB3Data);
            stats.execSQL(addDB4Data);
            stats.execSQL(addDB5Data);
            stats.execSQL(addDB6Data);
            stats.execSQL(detachDB1);
            stats.execSQL(detachDB2);
            stats.execSQL(detachDB3);
            stats.execSQL(detachDB4);
            stats.execSQL(detachDB5);
            stats.execSQL(detachDB6);
        } catch (Exception e) {
            Log.d("Test", "Merge: error " + e);
        }
    }
    public void merge4() {

        // Notes:
        //          In order for this method to work, matchNumber cannot be primary key since
        //          multiple matches will be going in that are the same number but different teams
        //
        //          These strings can be place inside of match repo or elsewhere, probably making a function
        //          to generate them. Doing both manually because of differences in names

        try {
            SQLiteDatabase stats = DatabaseManager.getInstance().openDatabase();

            String attachDB1 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData1.db" + "' as db1";
            String attachDB2 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData2.db" + "' as db2";
            String attachDB3 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData3.db" + "' as db3";
            String attachDB4 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData4.db" + "' as db4";
            String attachDB5 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData5.db" + "' as db5";
            String attachDB6 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/TabletData6.db" + "' as db6";


            String addDB1Data = "" +
                    "INSERT INTO " + PitData.TABLE + " " +
                    "SELECT " + PitData.KEY_Columns + " " +
                    "FROM db1." + PitData.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+PitData.KEY_TeamNum + " " +
                    "FROM "+ PitData.TABLE+" T " +
                    "WHERE T."+PitData.KEY_TeamNum+" = S."+PitData.KEY_TeamNum +
                    ")";

            String addDB2Data = "" +
                    "INSERT INTO " + PitData.TABLE + " " +
                    "SELECT " + PitData.KEY_Columns + " " +
                    "FROM db2." + PitData.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+PitData.KEY_TeamNum + " " +
                    "FROM "+PitData.TABLE+" T " +
                    "WHERE T."+PitData.KEY_TeamNum+" = S."+PitData.KEY_TeamNum +
                    ")";

            String addDB3Data = "" +
                    "INSERT INTO " + PitData.TABLE + " " +
                    "SELECT " + PitData.KEY_Columns + " " +
                    "FROM db3." + PitData.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+PitData.KEY_TeamNum + " " +
                    "FROM "+PitData.TABLE+" T " +
                    "WHERE T."+PitData.KEY_TeamNum+" = S."+PitData.KEY_TeamNum +
                    ")";

            String addDB4Data = "" +
                    "INSERT INTO " + PitData.TABLE + " " +
                    "SELECT " + PitData.KEY_Columns + " " +
                    "FROM db4." + PitData.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+PitData.KEY_TeamNum + " " +
                    "FROM "+PitData.TABLE+" T " +
                    "WHERE T."+PitData.KEY_TeamNum+" = S."+PitData.KEY_TeamNum +
                    ")";

            String addDB5Data = "" +
                    "INSERT INTO " + PitData.TABLE + " " +
                    "SELECT " + PitData.KEY_Columns + " " +
                    "FROM db5." + PitData.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+PitData.KEY_TeamNum + " " +
                    "FROM "+PitData.TABLE+" T " +
                    "WHERE T."+PitData.KEY_TeamNum+" = S."+PitData.KEY_TeamNum +
                    ")";

            String addDB6Data = "" +
                    "INSERT INTO " + PitData.TABLE + " " +
                    "SELECT " + PitData.KEY_Columns + " " +
                    "FROM db6." + PitData.TABLE +" S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+PitData.KEY_TeamNum + " " +
                    "FROM "+PitData.TABLE+" T " +
                    "WHERE T."+PitData.KEY_TeamNum+" = S."+PitData.KEY_TeamNum +
                    ")";

            String detachDB1 = "DETACH db1";
            String detachDB2 = "DETACH db2";
            String detachDB3 = "DETACH db3";
            String detachDB4 = "DETACH db4";
            String detachDB5 = "DETACH db5";
            String detachDB6 = "DETACH db6";

            stats.execSQL(attachDB1);
            stats.execSQL(attachDB2);
            stats.execSQL(attachDB3);
            stats.execSQL(attachDB4);
            stats.execSQL(attachDB5);
            stats.execSQL(attachDB6);
            stats.execSQL(addDB1Data);
            stats.execSQL(addDB2Data);
            stats.execSQL(addDB3Data);
            stats.execSQL(addDB4Data);
            stats.execSQL(addDB5Data);
            stats.execSQL(addDB6Data);
            stats.execSQL(detachDB1);
            stats.execSQL(detachDB2);
            stats.execSQL(detachDB3);
            stats.execSQL(detachDB4);
            stats.execSQL(detachDB5);
            stats.execSQL(detachDB6);
        } catch (Exception e) {
            Log.d("Test", "Merge: error " + e);
        }
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
}
