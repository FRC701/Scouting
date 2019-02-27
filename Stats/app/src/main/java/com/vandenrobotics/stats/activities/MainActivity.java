package com.vandenrobotics.stats.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.vandenrobotics.stats.R;
import com.vandenrobotics.stats.data.DatabaseManager;
import com.vandenrobotics.stats.data.model.Matches;
import com.vandenrobotics.stats.data.repo.MatchesRepo;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db1;     // Data in table called 'table1'
    private SQLiteDatabase db2;     // NOTE: data is in table named 'tabel1'. This is misspelled!!!
                                    // shouldn't matter since when doing our db they will be uniform

    private final File DOWNLOADS_FILE = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

    private MatchesRepo matchrepo = new MatchesRepo();

    // View elements
    TextView db1Display;
    TextView db2Display;
    TextView mergeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db1Display = findViewById(R.id.db1Display);
        db2Display = findViewById(R.id.db2Display);
        mergeDisplay = findViewById(R.id.mergeDisplay);
    }

    public void openDB1(View view) {
        try {
            db1 = SQLiteDatabase.openDatabase(DOWNLOADS_FILE.getAbsolutePath() + "/db1.db", null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            Toast.makeText(this, "Could not open db1: " + e, Toast.LENGTH_LONG).show();
        }
    }

    public void openDB2(View view) {
        try {
            db2 = SQLiteDatabase.openDatabase(DOWNLOADS_FILE.getAbsolutePath() + "/db2.db", null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {
            Toast.makeText(this, "Could not open db2: " + e, Toast.LENGTH_LONG).show();
        }
    }

    public void showDB1(View view) {
        String newDisplay = "";

        // Get data
        if (db1 == null) openDB1(null);

        Cursor cursor = db1.rawQuery("SELECT * FROM table1", null, null);
        while (cursor.moveToNext()) {
            newDisplay += "Team: "+cursor.getString(1)+" Match: "+cursor.getString(2)+" Score: "+cursor.getString(3)+" Comp: "+cursor.getString(4)+"\n";
        }
        cursor.close();

        // Display data
        if (newDisplay.length() == 0) {
            db1Display.setText("No db1 data to display");
        } else {
            db1Display.setText(newDisplay);
        }
    }

    public void showDB2(View veiw) {
        String newDisplay = "";

        // Get data
        if (db2 == null) openDB2(null);

        Cursor cursor = db2.rawQuery("SELECT * FROM tabel1", null, null);
        while (cursor.moveToNext()) {
            newDisplay += "Team: " + cursor.getString(1) + " Match: " + cursor.getString(2) + " Score: " + cursor.getString(3) + " Comp: "+ cursor.getString(4) + "\n";
        }
        cursor.close();

        // Display data
        if (newDisplay.length() == 0) {
            db2Display.setText("No db2 data to display");
        } else {
            db2Display.setText(newDisplay);
        }
    }

    public void Merge(View view) {
        if (db1 == null) openDB1(null);
        if (db2 == null) openDB2(null);

        merge2();
    }
    public void showMerge(View view) {
        String newDisplay = "";

        SQLiteDatabase statsDB = DatabaseManager.getInstance().openDatabase();
        try {
            Cursor cursor = statsDB.rawQuery("SELECT * FROM Matches", null, null);
            while (cursor.moveToNext()) {
                newDisplay += "Team: " + cursor.getString(2) + " Match: " + cursor.getString(1) + " Score: " + cursor.getString(3) + "\n";
            }
            cursor.close();
        } catch (Exception e) {
            Log.d("Test", "showMerge: error " + e);
        }

        // Display data
        if (newDisplay.length() == 0) {
            mergeDisplay.setText("No mergedDB data to display");
        } else {
            mergeDisplay.setText(newDisplay);
        }
    }

    public void merge1() {
        Cursor db1Cursor = db1.rawQuery("SELECT * FROM table1", null, null);
        while (db1Cursor.moveToNext()) {
            Matches match = new Matches();
            match.setCompId("test");
            match.setTeamNum(db1Cursor.getInt(1));
            match.setMatchNum(db1Cursor.getInt(2));
            match.setMatchPos(db1Cursor.getInt(3));

            matchrepo.insert(match);
            Log.d("Test", "Merge: adding match" + match);
        }
        db1Cursor.close();

        Cursor db2Cursor = db2.rawQuery("SELECT * FROM tabel1", null, null);
        while (db2Cursor.moveToNext()) {
            Matches match = new Matches();
            match.setCompId("test");
            match.setTeamNum(db2Cursor.getInt(1));
            match.setMatchNum(db2Cursor.getInt(2));
            match.setMatchPos(db2Cursor.getInt(3));

            matchrepo.insert(match);
            Log.d("Test", "Merge: adding match" + match);
        }
        db2Cursor.close();
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

            String attachDB1 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/db1.db" + "' as db1";
            String attachDB2 = "ATTACH DATABASE '" + DOWNLOADS_FILE.getAbsolutePath() + "/db2.db" + "' as db2";

            String addDB1Data = "" +
                    "INSERT INTO " + Matches.TABLE + "( "+ Matches.KEY_COLUMNS+ ") " +
                    "SELECT " + "S.comp, S.team, S.match, S.score " +
                    "FROM db1.table1 S " +
                    "WHERE NOT EXISTS ("+
                        "SELECT T."+Matches.KEY_TeamNumber+", T."+Matches.KEY_MatchNumber + " " +
                        "FROM "+Matches.TABLE+" T " +
                        "WHERE T."+Matches.KEY_TeamNumber+" = S.team AND T."+Matches.KEY_MatchNumber+" = S.match" +
                    ")";

            String addDB2Data = "" +
                    "INSERT INTO " + Matches.TABLE + "( "+ Matches.KEY_COLUMNS+ ") " +
                    "SELECT " + "S.comp, S.team, S.match, S.score " +
                    "FROM db2.tabel1 S " +
                    "WHERE NOT EXISTS ("+
                    "SELECT T."+Matches.KEY_TeamNumber+", T."+Matches.KEY_MatchNumber + " " +
                    "FROM "+Matches.TABLE+" T " +
                    "WHERE T."+Matches.KEY_TeamNumber+" = S.team AND T."+Matches.KEY_MatchNumber+" = S.match" +
                    ")";

            String detachDB1 = "DETACH db1";
            String detachDB2 = "DETACH db2";

            stats.execSQL(attachDB1);
            stats.execSQL(attachDB2);
            stats.execSQL(addDB1Data);
            stats.execSQL(addDB2Data);
            stats.execSQL(detachDB1);
            stats.execSQL(detachDB2);
        } catch (Exception e) {
            Log.d("Test", "Merge: error " + e);
        }
    }
}
