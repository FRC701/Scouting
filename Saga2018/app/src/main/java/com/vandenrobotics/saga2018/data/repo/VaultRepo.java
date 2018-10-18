package com.vandenrobotics.saga2018.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vandenrobotics.saga2018.data.DatabaseManager;
import com.vandenrobotics.saga2018.data.model.Competitions;
import com.vandenrobotics.saga2018.data.model.Vault;

/**
 * Created by Programming701-A on 2/10/2018.
 */

public class VaultRepo {

    private final String TAG = VaultRepo.class.getSimpleName();

    public static String createTable(){
        return "CREATE TABLE " + Vault.TABLE + "("
                + Vault.KEY_CompId + " TEXT not null , "
                + Vault.KEY_MatchNum + " INTEGER not null , "
                + Vault.KEY_Alliance + " TEXT not null , "
                + Vault.KEY_ForceTime + " TEXT , "
                + Vault.KEY_ForceCubes + " INTEGER , "
                + Vault.KEY_LevTime + " TEXT , "
                + Vault.KEY_LevCubes + " INTEGER , "
                + Vault.KEY_BoostTime + " TEXT , "
                + Vault.KEY_BoostCubes + " INTEGER , "
                + Vault.KEY_ForceCubesAtTime + " INTEGER , "
                + Vault.KEY_LevCubesAtTime + " INTEGER , "
                + Vault.KEY_BoostCubesAtTime + " INTEGER , "
                + "PRIMARY KEY ( '" + Vault.KEY_CompId
                + "' , '" + Vault.KEY_MatchNum + "' , "
                + "'" + Vault.KEY_Alliance + "' ),"
                + " FOREIGN KEY ( " + Vault.KEY_CompId + " ) REFERENCES " + Competitions.TABLE
                + " ( " + Competitions.KEY_CompId + " ))";
    }

    public int insert(Vault vault){
        int vaultId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Vault.KEY_CompId, vault.getCompId());
        values.put(Vault.KEY_MatchNum, vault.getMatchNum());
        values.put(Vault.KEY_Alliance, vault.getAlliance());
        values.put(Vault.KEY_ForceTime, vault.getForceTime());
        values.put(Vault.KEY_ForceCubes, vault.getForceCubes());
        values.put(Vault.KEY_LevTime, vault.getLevitateTime());
        values.put(Vault.KEY_LevCubes, vault.getLevitateCubes());
        values.put(Vault.KEY_BoostTime, vault.getBoostTime());
        values.put(Vault.KEY_BoostCubes, vault.getBoostCubes());
        values.put(Vault.KEY_ForceCubesAtTime, vault.getForceCubesAtTime());
        values.put(Vault.KEY_LevCubesAtTime, vault.getLevitateCubesAtTime());
        values.put(Vault.KEY_BoostCubesAtTime, vault.getBoostCubesAtTime());

        vaultId = (int)db.insertWithOnConflict(Vault.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        DatabaseManager.getInstance().closeDatabase();
        return vaultId;
    }

    public void update(Vault vault){
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(Vault.KEY_ForceTime, vault.getForceTime());
        values.put(Vault.KEY_ForceCubes, vault.getForceCubes());
        values.put(Vault.KEY_LevTime, vault.getLevitateTime());
        values.put(Vault.KEY_LevCubes, vault.getLevitateCubes());
        values.put(Vault.KEY_BoostTime, vault.getBoostTime());
        values.put(Vault.KEY_BoostCubes, vault.getBoostCubes());
        values.put(Vault.KEY_ForceCubesAtTime, vault.getForceCubesAtTime());
        values.put(Vault.KEY_LevCubesAtTime, vault.getLevitateCubesAtTime());
        values.put(Vault.KEY_BoostCubesAtTime, vault.getBoostCubesAtTime());

        db.update(Vault.TABLE, values, Vault.KEY_CompId + " = '" + vault.getCompId()
                + "' AND " + Vault.KEY_MatchNum + " = " + vault.getMatchNum()
                + " AND " + Vault.KEY_Alliance + " = '" + vault.getAlliance() + "'",null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public void deleteForComp(String event) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(Vault.TABLE, Vault.KEY_CompId + " =  '" + event + "'",null);
        DatabaseManager.getInstance().closeDatabase();
    }

    public Vault getVaultData(String event, int match, String alliance){
        Vault vault = new Vault();


        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT Vault." + Vault.KEY_ForceTime
                + " , Vault." + Vault.KEY_ForceTime
                + " , Vault." + Vault.KEY_ForceCubes
                + " , Vault." + Vault.KEY_LevTime
                + " , Vault." + Vault.KEY_LevCubes
                + " , Vault." + Vault.KEY_BoostTime
                + " , Vault." + Vault.KEY_BoostCubes
                + " , Vault." + Vault.KEY_ForceCubesAtTime
                + " , Vault." + Vault.KEY_LevCubesAtTime
                + " , Vault." + Vault.KEY_BoostCubesAtTime
                + " FROM " + Vault.TABLE
                + " WHERE Vault." + Vault.KEY_CompId + " = '" + event + "'"
                + " AND Vault." + Vault.KEY_MatchNum + " = " + match
                + " AND Vault." + Vault.KEY_Alliance + " = '" + alliance + "'";

        Log.d(TAG, selectQuery);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            vault.setForceTime(cursor.getString(cursor.getColumnIndex(Vault.KEY_ForceTime)));
            vault.setForceCubes(cursor.getInt(cursor.getColumnIndex(Vault.KEY_ForceCubes)));
            vault.setLevitateTime(cursor.getString(cursor.getColumnIndex(Vault.KEY_LevTime)));
            vault.setLevitateCubes(cursor.getInt(cursor.getColumnIndex(Vault.KEY_LevCubes)));
            vault.setBoostTime(cursor.getString(cursor.getColumnIndex(Vault.KEY_BoostTime)));
            vault.setBoostCubes(cursor.getInt(cursor.getColumnIndex(Vault.KEY_BoostCubes)));
            vault.setForceCubesAtTime(cursor.getInt(cursor.getColumnIndex(Vault.KEY_ForceCubesAtTime)));
            vault.setLevitateCubesAtTime(cursor.getInt(cursor.getColumnIndex(Vault.KEY_LevCubesAtTime)));
            vault.setBoostCubesAtTime(cursor.getInt(cursor.getColumnIndex(Vault.KEY_BoostCubesAtTime)));
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return vault;
    }

}
