package com.vandenrobotics.stats.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.loopj.android.http.AsyncHttpClient.log;

public class DataBaseMerger {
    private SQLiteDatabase mDatabase;
    static String tag = "Merge";
    public static void merge() {
        Connection c, c2 = null;
        Statement stmt, stmt2 = null;
        int toMerge = 1;

        while(toMerge<=6) {
            try {
//                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection(Environment.getExternalStorageDirectory() + "/data/Stats/stats.db");
                log.d(tag,"Opened stats.db database successfully");
                c.setAutoCommit(false);
                stmt = c.createStatement();

                String asql = "ATTACH DATABASE '" +  "/data/Stats/TabletData" + toMerge + ".db" + "' AS tablet";
                log.d(tag,"attaching child db");
                ResultSet attachrs = stmt.executeQuery(asql);
                log.d(tag, " attach "+attachrs.toString());
                ResultSet rs = stmt.executeQuery("SELECT * FROM tablet.Competitions;");
                String sql;
                while (rs.next()) {

                    try {
                        log.d(tag,rs.getRow()+"");
                        sql = "INSERT INTO Competitions";
                        stmt.executeUpdate(sql);
                    }
                    catch (Exception e){
                        log.d(tag,e.getMessage());
                    }
                }
                rs.close();

                stmt.close();
                c.commit();
                c.close();
            } catch (Exception e) {
                log.d(tag,e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            log.d(tag,"Operation done successfully");
            toMerge++;
        }
    }
}
