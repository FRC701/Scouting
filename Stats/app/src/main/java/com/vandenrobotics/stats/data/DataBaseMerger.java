package com.vandenrobotics.stats.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseMerger {

    // https://stackoverflow.com/questions/4498664/android-multiple-databases-open


    private SQLiteDatabase mDatabase;
    static String tag = "Merge";
    public static void merge() {

        Log.d("Test", "Log 1");
        Connection c, c2 = null;
        Statement stmt, stmt2 = null;
        int toMerge = 1;
        Log.d("Test", "Log 2");
        while(toMerge<=6) {
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection(Environment.getExternalStorageDirectory() + "/data/Stats/stats.db");
                Log.d("Test", "Log 3");
                Log.d(tag,"Opened stats.db database successfully");
                c.setAutoCommit(false);
                stmt = c.createStatement();

                String asql = "ATTACH DATABASE '" +  "/data/Stats/TabletData" + toMerge + ".db" + "' AS tablet";
                Log.d(tag,"attaching child db");
                ResultSet attachrs = stmt.executeQuery(asql);
                Log.d(tag, " attach "+attachrs.toString());
                ResultSet rs = stmt.executeQuery("SELECT * FROM tablet.Competitions;");
                String sql;
                while (rs.next()) {

                    try {
                        Log.d(tag,rs.getRow()+"");
                        sql = "INSERT INTO Competitions";
                        stmt.executeUpdate(sql);
                    }
                    catch (Exception e){
                        Log.d(tag,e.getMessage());
                    }
                }
                rs.close();

                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {
                Log.d(tag,e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            Log.d(tag,"Operation done successfully");
            toMerge++;
        }
    }
}
