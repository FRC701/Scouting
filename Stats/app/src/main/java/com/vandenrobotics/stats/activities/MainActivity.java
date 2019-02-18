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
import com.vandenrobotics.stats.app.App;
import com.vandenrobotics.stats.data.DataBaseHelper;
import com.vandenrobotics.stats.data.DataBaseMerger;
import com.vandenrobotics.stats.data.DatabaseManager;
import com.vandenrobotics.stats.data.model.Matches;
import com.vandenrobotics.stats.data.repo.MatchesRepo;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db1;
    private SQLiteDatabase db2;

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
            newDisplay += "Team: " + cursor.getString(1) + " Match: " + cursor.getString(2) + " Score: " + cursor.getString(3) + "\n";
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
            newDisplay += "Team: " + cursor.getString(1) + " Match: " + cursor.getString(2) + " Score: " + cursor.getString(3) + "\n";
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
}
