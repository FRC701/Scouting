package com.vandenrobotics.stats.data;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.sql.Connection;

public class DataBaseMerger {
    private SQLiteDatabase mDatabase;
    public static void mergeDatabases(String path1, String path2){
        Connection c = openDatabaseConnection(path1);

    }
}
