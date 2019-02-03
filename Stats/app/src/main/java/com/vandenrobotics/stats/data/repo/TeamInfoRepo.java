package com.vandenrobotics.stats.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vandenrobotics.stats.data.DatabaseManager;
import com.vandenrobotics.stats.data.model.Teams;

import java.util.ArrayList;

/**
 * Created by Sarah Bergendahl on 2/24/2018.
 */

public class TeamInfoRepo {
    private TeamInfo teamInfo;

    private final String TAG = TeamInfoRepo.class.getSimpleName();

    public TeamInfoRepo(){
        teamInfo = new TeamInfo();
    }

    //Holds String to execute SQLite where the TeamInfo Table is created and specified how it is made
    public static String createTable(){
        return "CREATE TABLE " + TeamInfo.TABLE + "("
                + TeamInfo.KEY_TeamNum + " INTEGER not null , "
                + TeamInfo.KEY_NumMatch + " REAL , "
                + TeamInfo.KEY_PNoShow + " REAL  , "
                + TeamInfo.KEY_AvgOff + " REAL , "
                + TeamInfo.KEY_AvgDef + " REAL , "
                + TeamInfo.KEY_AvgTotal + " REAL , "
                + TeamInfo.KEY_AvgNeg + " REAL , "
                + TeamInfo.KEY_AvgAuto + " REAL , "
                + TeamInfo.KEY_PHadAuto + " REAL , "
                + TeamInfo.KEY_PCrossAutoLine + " REAL , "
                + TeamInfo.KEY_PAutoPickedUpCube + " REAL , "
                + TeamInfo.KEY_AvgAutoCubesInSw1 + " REAL , "
                + TeamInfo.KEY_AvgAutoCubesInSw2 + " REAL , "
                + TeamInfo.KEY_AvgAutoCubesInScl + " REAL , "
                + TeamInfo.KEY_AvgAutoCubesInEx + " REAL , "
                + TeamInfo.KEY_AvgAutoOwnGainSw1 + " REAL , "
                + TeamInfo.KEY_AvgAutoOwnedSw1 + " REAL , "
                + TeamInfo.KEY_AvgAutoOwnGainSw2 + " REAL , "
                + TeamInfo.KEY_AvgAutoOwnedSw2 + " REAL , "
                + TeamInfo.KEY_AvgAutoOwnGainScl + " REAL , "
                + TeamInfo.KEY_AvgAutoOwnedScl + " REAL , "
                + TeamInfo.KEY_AvgAutoNumOwnChangesSw1 + " REAL , "
                + TeamInfo.KEY_AvgAutoNumOwnChangesSw2 + " REAL , "
                + TeamInfo.KEY_AvgAutoNumOwnChangesScl + " REAL , "
                + TeamInfo.KEY_AvgTele + " REAL , "
                + TeamInfo.KEY_PTelePickedUpCube + " REAL , "
                + TeamInfo.KEY_AvgTeleCubesInSw1 + " REAL , "
                + TeamInfo.KEY_AvgTeleCubesInSw2 + " REAL , "
                + TeamInfo.KEY_AvgTeleCubesInScl + " REAL , "
                + TeamInfo.KEY_AvgTeleCubesInEx + " REAL , "
                + TeamInfo.KEY_AvgTeleOwnGainSw1 + " REAL , "
                + TeamInfo.KEY_AvgTeleOwnedSw1 + " REAL , "
                + TeamInfo.KEY_AvgTeleOwnGainSw2 + " REAL , "
                + TeamInfo.KEY_AvgTeleOwnedSw2 + " REAL , "
                + TeamInfo.KEY_AvgTeleOwnGainScl + " REAL , "
                + TeamInfo.KEY_AvgTeleOwnedScl + " REAL , "
                + TeamInfo.KEY_AvgTeleNumOwnChangesSw1 + " REAL , "
                + TeamInfo.KEY_AvgTeleNumOwnChangesSw2 + " REAL , "
                + TeamInfo.KEY_AvgTeleNumOwnChangesScl + " REAL , "
                + TeamInfo.KEY_PClimb + " REAL , "
                + TeamInfo.KEY_PClimbAssist + " REAL , "
                + TeamInfo.KEY_PParking + " REAL , "
                + TeamInfo.KEY_PHumanPlayer + " REAL , "
                + TeamInfo.KEY_HadFoul + " REAL , "
                + TeamInfo.KEY_PTechFoul + " REAL , "
                + TeamInfo.KEY_PFoul + " REAL , "
                + TeamInfo.KEY_PYellowCard + " REAL , "
                + TeamInfo.KEY_PRedCard + " REAL , "
                + TeamInfo.KEY_PDisabled + " REAL , "
                + TeamInfo.KEY_AvgNumCubesInVault + " REAL , "
                + TeamInfo.KEY_AvgActiveFceTime + " REAL , "
                + TeamInfo.KEY_AvgActiveLevTime + " REAL , "
                + TeamInfo.KEY_AvgActiveBstTime + " REAL , "
                + TeamInfo.KEY_AvgCubesAtActiveFce + " REAL , "
                + TeamInfo.KEY_AvgCubesAtActiveLev + " REAL , "
                + TeamInfo.KEY_AvgCubesAtActiveBst + " REAL , "
                + TeamInfo.KEY_MaxAutoNumOwnChangesSw1 + " INTEGER , "
                + TeamInfo.KEY_MinAutoNumOwnChangesSw1 + " INTEGER , "
                + TeamInfo.KEY_MaxAutoNumOwnChangesSw2 + " INTEGER , "
                + TeamInfo.KEY_MinAutoNumOwnChangesSw2 + " INTEGER , "
                + TeamInfo.KEY_MaxAutoNumOwnChangesScl + " INTEGER , "
                + TeamInfo.KEY_MinAutoNumOwnChangesScl + " INTEGER , "
                + TeamInfo.KEY_MaxAutoCubesInSw1 + " INTEGER , "
                + TeamInfo.KEY_MinAutoCubesInSw1 + " INTEGER , "
                + TeamInfo.KEY_MaxAutoCubesInSw2 + " INTEGER , "
                + TeamInfo.KEY_MinAutoCubesInSw2 + " INTEGER , "
                + TeamInfo.KEY_MaxAutoCubesInScl + " INTEGER , "
                + TeamInfo.KEY_MinAutoCubesInScl + " INTEGER , "
                + TeamInfo.KEY_MaxAutoCubesInEx + " INTEGER , "
                + TeamInfo.KEY_MinAutoCubesInEx + " INTEGER , "
                + TeamInfo.KEY_MaxAutoOwnGainSw1 + " INTEGER , "
                + TeamInfo.KEY_MinAutoOwnGainSw1 + " INTEGER , "
                + TeamInfo.KEY_MaxAutoOwnedSw1 + " INTEGER , "
                + TeamInfo.KEY_MinAutoOwnedSw1 + " INTEGER , "
                + TeamInfo.KEY_MaxAutoOwnGainSw2 + " INTEGER , "
                + TeamInfo.KEY_MinAutoOwnGainSw2 + " INTEGER , "
                + TeamInfo.KEY_MaxAutoOwnedSw2 + " INTEGER , "
                + TeamInfo.KEY_MinAutoOwnedSw2 + " INTEGER , "
                + TeamInfo.KEY_MaxAutoOwnGainScl + " INTEGER , "
                + TeamInfo.KEY_MinAutoOwnGainScl + " INTEGER , "
                + TeamInfo.KEY_MaxAutoOwnedScl + " INTEGER , "
                + TeamInfo.KEY_MinAutoOwnedScl + " INTEGER , "
                + TeamInfo.KEY_MaxTeleNumOwnChangesSw1 + " INTEGER , "
                + TeamInfo.KEY_MinTeleNumOwnChangesSw1 + " INTEGER , "
                + TeamInfo.KEY_MaxTeleNumOwnChangesSw2 + " INTEGER , "
                + TeamInfo.KEY_MinTeleNumOwnChangesSw2 + " INTEGER , "
                + TeamInfo.KEY_MaxTeleNumOwnChangesScl + " INTEGER , "
                + TeamInfo.KEY_MinTeleNumOwnChangesScl + " INTEGER , "
                + TeamInfo.KEY_MaxTeleCubesInSw1 + " INTEGER , "
                + TeamInfo.KEY_MinTeleCubesInSw1 + " INTEGER , "
                + TeamInfo.KEY_MaxTeleCubesInSw2 + " INTEGER , "
                + TeamInfo.KEY_MinTeleCubesInSw2 + " INTEGER , "
                + TeamInfo.KEY_MaxTeleCubesInScl + " INTEGER , "
                + TeamInfo.KEY_MinTeleCubesInScl + " INTEGER , "
                + TeamInfo.KEY_MaxTeleCubesInEx + " INTEGER , "
                + TeamInfo.KEY_MinTeleCubesInEx + " INTEGER , "
                + TeamInfo.KEY_MaxTeleOwnGainSw1 + " INTEGER , "
                + TeamInfo.KEY_MinTeleOwnGainSw1 + " INTEGER , "
                + TeamInfo.KEY_MaxTeleOwnedSw1 + " INTEGER , "
                + TeamInfo.KEY_MinTeleOwnedSw1 + " INTEGER , "
                + TeamInfo.KEY_MaxTeleOwnGainSw2 + " INTEGER , "
                + TeamInfo.KEY_MinTeleOwnGainSw2 + " INTEGER , "
                + TeamInfo.KEY_MaxTeleOwnedSw2 + " INTEGER , "
                + TeamInfo.KEY_MinTeleOwnedSw2 + " INTEGER , "
                + TeamInfo.KEY_MaxTeleOwnGainScl + " INTEGER , "
                + TeamInfo.KEY_MinTeleOwnGainScl + " INTEGER , "
                + TeamInfo.KEY_MaxTeleOwnedScl + " INTEGER , "
                + TeamInfo.KEY_MinTeleOwnedScl + " INTEGER , "
                + TeamInfo.KEY_MaxNumCubesInVault + " INTEGER , "
                + TeamInfo.KEY_MinNumCubesInVault + " INTEGER , "
                + TeamInfo.KEY_MaxActiveFceTime + " INTEGER , "
                + TeamInfo.KEY_MinActiveFceTime + " INTEGER , "
                + TeamInfo.KEY_MaxActiveLevTime + " INTEGER , "
                + TeamInfo.KEY_MinActiveLevTime + " INTEGER , "
                + TeamInfo.KEY_MaxActiveBstTime + " INTEGER , "
                + TeamInfo.KEY_MinActiveBstTime + " INTEGER , "
                + TeamInfo.KEY_MaxCubesAtActiveFce + " INTEGER , "
                + TeamInfo.KEY_MinCubesAtActiveFce + " INTEGER , "
                + TeamInfo.KEY_MaxCubesAtActiveLev + " INTEGER , "
                + TeamInfo.KEY_MinCubesAtActiveLev + " INTEGER , "
                + TeamInfo.KEY_MaxCubesAtActiveBst + " INTEGER , "
                + TeamInfo.KEY_MinCubesAtActiveBst + " INTEGER , "+
                //makes the CompId, MatchNum and MatchPos Primary Key so there needs
                //to be a unique combination of these attributes in each row in the TeamInfo table
                "PRIMARY KEY ( '" + TeamInfo.KEY_TeamNum + "' ), "
                //makes sure TeamNum column exists in the Team Table
                + " FOREIGN KEY ( " + TeamInfo.KEY_TeamNum + " ) REFERENCES " + Teams.TABLE
                + " ( " + Teams.KEY_TeamNumber + " ))";
    }

    //Holds String to execute SQLite where the TeamInfo Index is created to specify that there
    //Is a unique combination of CompId, Match# and Team# in every row in the teamInfo table
    public static String createIndex(){
        return "CREATE UNIQUE INDEX '" + TeamInfo.INDEX + "' ON "
                + TeamInfo.TABLE + " , " + TeamInfo.KEY_TeamNum + " )";
    }

    //inserts all values of a teamInfo row object into the sql database
    public int insertAll(TeamInfo teamInfo){
        int teamInfoId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(TeamInfo.KEY_NumMatch, teamInfo.getNumMatch());
        values.put(TeamInfo.KEY_TeamNum, teamInfo.getTeamNum());
        values.put(TeamInfo.KEY_PNoShow, teamInfo.getPNoShow());
        values.put(TeamInfo.KEY_AvgOff, teamInfo.getOffensiveWS());
        values.put(TeamInfo.KEY_AvgDef, teamInfo.getDefensiveWS());
        values.put(TeamInfo.KEY_AvgTotal, teamInfo.getTotalWS());
        values.put(TeamInfo.KEY_AvgNeg, teamInfo.getNegWS());
        values.put(TeamInfo.KEY_AvgAuto, teamInfo.getAutoWS());
        values.put(TeamInfo.KEY_PHadAuto, teamInfo.getPHadAuto());
        values.put(TeamInfo.KEY_PCrossAutoLine, teamInfo.getPCrossAutoLine());
        values.put(TeamInfo.KEY_PAutoPickedUpCube, teamInfo.getPAutoPickedUpCube());
        values.put(TeamInfo.KEY_AvgAutoCubesInSw1, teamInfo.getAvgAutoCubesInSw1());
        values.put(TeamInfo.KEY_AvgAutoCubesInScl, teamInfo.getAvgAutoCubesInScl());
        values.put(TeamInfo.KEY_AvgAutoCubesInSw2, teamInfo.getAvgAutoCubesInSw2());
        values.put(TeamInfo.KEY_AvgAutoCubesInEx, teamInfo.getAvgAutoCubesInEx());
        values.put(TeamInfo.KEY_AvgAutoOwnGainSw1, teamInfo.getAvgAutoOwnGainSw1());
        values.put(TeamInfo.KEY_AvgAutoOwnedSw1, teamInfo.getAvgAutoOwnedSw1());
        values.put(TeamInfo.KEY_AvgAutoOwnGainSw2, teamInfo.getAvgAutoOwnGainSw2());
        values.put(TeamInfo.KEY_AvgAutoOwnedSw2, teamInfo.getAvgAutoOwnedSw2());
        values.put(TeamInfo.KEY_AvgAutoOwnGainScl, teamInfo.getAvgAutoOwnGainScl());
        values.put(TeamInfo.KEY_AvgAutoOwnedScl, teamInfo.getAvgAutoOwnedScl());
        values.put(TeamInfo.KEY_AvgAutoNumOwnChangesSw1, teamInfo.getAvgAutoNumOwnChangesSw1());
        values.put(TeamInfo.KEY_AvgAutoNumOwnChangesSw2, teamInfo.getAvgAutoNumOwnChangesSw2());
        values.put(TeamInfo.KEY_AvgAutoNumOwnChangesScl, teamInfo.getAvgAutoNumOwnChangesScl());
        values.put(TeamInfo.KEY_AvgTele, teamInfo.getTeleWS());
        values.put(TeamInfo.KEY_PTelePickedUpCube, teamInfo.getPTelePickedUpCube());
        values.put(TeamInfo.KEY_AvgTeleCubesInSw1, teamInfo.getAvgTeleCubesInSw1());
        values.put(TeamInfo.KEY_AvgTeleCubesInScl, teamInfo.getAvgTeleCubesInScl());
        values.put(TeamInfo.KEY_AvgTeleCubesInSw2, teamInfo.getAvgTeleCubesInSw2());
        values.put(TeamInfo.KEY_AvgTeleCubesInEx, teamInfo.getAvgTeleCubesInEx());
        values.put(TeamInfo.KEY_AvgTeleOwnGainSw1, teamInfo.getAvgTeleOwnGainSw1());
        values.put(TeamInfo.KEY_AvgTeleOwnedSw1, teamInfo.getAvgTeleOwnedSw1());
        values.put(TeamInfo.KEY_AvgTeleOwnGainSw2, teamInfo.getAvgTeleOwnGainSw2());
        values.put(TeamInfo.KEY_AvgTeleOwnedSw2, teamInfo.getAvgTeleOwnedSw2());
        values.put(TeamInfo.KEY_AvgTeleOwnGainScl, teamInfo.getAvgTeleOwnGainScl());
        values.put(TeamInfo.KEY_AvgTeleOwnedScl, teamInfo.getAvgTeleOwnedScl());
        values.put(TeamInfo.KEY_AvgTeleNumOwnChangesSw1, teamInfo.getAvgTeleNumOwnChangesSw1());
        values.put(TeamInfo.KEY_AvgTeleNumOwnChangesSw2, teamInfo.getAvgTeleNumOwnChangesSw2());
        values.put(TeamInfo.KEY_AvgTeleNumOwnChangesScl, teamInfo.getAvgTeleNumOwnChangesScl());
        values.put(TeamInfo.KEY_PClimb, teamInfo.getPClimb());
        values.put(TeamInfo.KEY_PClimbAssist, teamInfo.getPClimbAssist());
        values.put(TeamInfo.KEY_PParking, teamInfo.getPParking());
        values.put(TeamInfo.KEY_PHumanPlayer, teamInfo.getPHumanPlayer());
        values.put(TeamInfo.KEY_PDisabled, teamInfo.getPDisabled());
        values.put(TeamInfo.KEY_PRedCard, teamInfo.getPRedCard());
        values.put(TeamInfo.KEY_PYellowCard, teamInfo.getPYellowCard());
        values.put(TeamInfo.KEY_HadFoul, teamInfo.getHadFoul());
        values.put(TeamInfo.KEY_PFoul, teamInfo.getPFoul());
        values.put(TeamInfo.KEY_PTechFoul, teamInfo.getPTechFoul());
        values.put(TeamInfo.KEY_AvgNumCubesInVault, teamInfo.getAvgNumCubesInVault());
        values.put(TeamInfo.KEY_AvgActiveFceTime, teamInfo.getAvgActiveFceTime());
        values.put(TeamInfo.KEY_AvgActiveLevTime, teamInfo.getAvgActiveLevTime());
        values.put(TeamInfo.KEY_AvgActiveBstTime, teamInfo.getAvgActiveBstTime());
        values.put(TeamInfo.KEY_AvgCubesAtActiveFce, teamInfo.getAvgCubesAtActiveFce());
        values.put(TeamInfo.KEY_AvgCubesAtActiveLev, teamInfo.getAvgCubesAtActiveLev());
        values.put(TeamInfo.KEY_AvgCubesAtActiveBst, teamInfo.getAvgCubesAtActiveBst());
        values.put(TeamInfo.KEY_MaxAutoNumOwnChangesSw1, teamInfo.getMaxAutoNumOwnChangesSw1());
        values.put(TeamInfo.KEY_MinAutoNumOwnChangesSw1, teamInfo.getMinAutoNumOwnChangesSw1());
        values.put(TeamInfo.KEY_MaxAutoNumOwnChangesSw2, teamInfo.getMaxAutoNumOwnChangesSw2());
        values.put(TeamInfo.KEY_MinAutoNumOwnChangesSw2, teamInfo.getMinAutoNumOwnChangesSw2());
        values.put(TeamInfo.KEY_MaxAutoNumOwnChangesScl, teamInfo.getMaxAutoNumOwnChangesScl());
        values.put(TeamInfo.KEY_MinAutoNumOwnChangesScl, teamInfo.getMinAutoNumOwnChangesScl());
        values.put(TeamInfo.KEY_MaxAutoCubesInSw1, teamInfo.getMaxAutoCubesInSw1());
        values.put(TeamInfo.KEY_MinAutoCubesInSw1, teamInfo.getMinAutoCubesInSw1());
        values.put(TeamInfo.KEY_MaxAutoCubesInSw2, teamInfo.getMaxAutoCubesInSw2());
        values.put(TeamInfo.KEY_MinAutoCubesInSw2, teamInfo.getMinAutoCubesInSw2());
        values.put(TeamInfo.KEY_MaxAutoCubesInScl, teamInfo.getMaxAutoCubesInScl());
        values.put(TeamInfo.KEY_MinAutoCubesInScl, teamInfo.getMinAutoCubesInScl());
        values.put(TeamInfo.KEY_MaxAutoCubesInEx, teamInfo.getMaxAutoCubesInEx());
        values.put(TeamInfo.KEY_MinAutoCubesInEx, teamInfo.getMinAutoCubesInEx());
        values.put(TeamInfo.KEY_MaxAutoOwnGainSw1, teamInfo.getMaxAutoOwnGainSw1());
        values.put(TeamInfo.KEY_MinAutoOwnGainSw1, teamInfo.getMinAutoOwnGainSw1());
        values.put(TeamInfo.KEY_MaxAutoOwnedSw1, teamInfo.getMaxAutoOwnedSw1());
        values.put(TeamInfo.KEY_MinAutoOwnedSw1, teamInfo.getMinAutoOwnedSw1());
        values.put(TeamInfo.KEY_MaxAutoOwnGainSw2, teamInfo.getMaxAutoOwnGainSw2());
        values.put(TeamInfo.KEY_MinAutoOwnGainSw2, teamInfo.getMinAutoOwnGainSw2());
        values.put(TeamInfo.KEY_MaxAutoOwnedSw2, teamInfo.getMaxAutoOwnedSw2());
        values.put(TeamInfo.KEY_MinAutoOwnedSw2, teamInfo.getMinAutoOwnedSw2());
        values.put(TeamInfo.KEY_MaxAutoOwnGainScl, teamInfo.getMaxAutoOwnGainScl());
        values.put(TeamInfo.KEY_MinAutoOwnGainScl, teamInfo.getMinAutoOwnGainScl());
        values.put(TeamInfo.KEY_MaxAutoOwnedScl, teamInfo.getMaxAutoOwnedScl());
        values.put(TeamInfo.KEY_MinAutoOwnedScl, teamInfo.getMinAutoOwnedScl());
        values.put(TeamInfo.KEY_MaxTeleNumOwnChangesSw1, teamInfo.getMaxTeleNumOwnChangesSw1());
        values.put(TeamInfo.KEY_MinTeleNumOwnChangesSw1, teamInfo.getMinTeleNumOwnChangesSw1());
        values.put(TeamInfo.KEY_MaxTeleNumOwnChangesSw2, teamInfo.getMaxTeleNumOwnChangesSw2());
        values.put(TeamInfo.KEY_MinTeleNumOwnChangesSw2, teamInfo.getMinTeleNumOwnChangesSw2());
        values.put(TeamInfo.KEY_MaxTeleNumOwnChangesScl, teamInfo.getMaxTeleNumOwnChangesScl());
        values.put(TeamInfo.KEY_MinTeleNumOwnChangesScl, teamInfo.getMinTeleNumOwnChangesScl());
        values.put(TeamInfo.KEY_MaxTeleCubesInSw1, teamInfo.getMaxTeleCubesInSw1());
        values.put(TeamInfo.KEY_MinTeleCubesInSw1, teamInfo.getMinTeleCubesInSw1());
        values.put(TeamInfo.KEY_MaxTeleCubesInSw2, teamInfo.getMaxTeleCubesInSw2());
        values.put(TeamInfo.KEY_MinTeleCubesInSw2, teamInfo.getMinTeleCubesInSw2());
        values.put(TeamInfo.KEY_MaxTeleCubesInScl, teamInfo.getMaxTeleCubesInScl());
        values.put(TeamInfo.KEY_MinTeleCubesInScl, teamInfo.getMinTeleCubesInScl());
        values.put(TeamInfo.KEY_MaxTeleCubesInEx, teamInfo.getMaxTeleCubesInEx());
        values.put(TeamInfo.KEY_MinTeleCubesInEx, teamInfo.getMinTeleCubesInEx());
        values.put(TeamInfo.KEY_MaxTeleOwnGainSw1, teamInfo.getMaxTeleOwnGainSw1());
        values.put(TeamInfo.KEY_MinTeleOwnGainSw1, teamInfo.getMinTeleOwnGainSw1());
        values.put(TeamInfo.KEY_MaxTeleOwnedSw1, teamInfo.getMaxTeleOwnedSw1());
        values.put(TeamInfo.KEY_MinTeleOwnedSw1, teamInfo.getMinTeleOwnedSw1());
        values.put(TeamInfo.KEY_MaxTeleOwnGainSw2, teamInfo.getMaxTeleOwnGainSw2());
        values.put(TeamInfo.KEY_MinTeleOwnGainSw2, teamInfo.getMinTeleOwnGainSw2());
        values.put(TeamInfo.KEY_MaxTeleOwnedSw2, teamInfo.getMaxTeleOwnedSw2());
        values.put(TeamInfo.KEY_MinTeleOwnedSw2, teamInfo.getMinTeleOwnedSw2());
        values.put(TeamInfo.KEY_MaxTeleOwnGainScl, teamInfo.getMaxTeleOwnGainScl());
        values.put(TeamInfo.KEY_MinTeleOwnGainScl, teamInfo.getMinTeleOwnGainScl());
        values.put(TeamInfo.KEY_MaxTeleOwnedScl, teamInfo.getMaxTeleOwnedScl());
        values.put(TeamInfo.KEY_MinTeleOwnedScl, teamInfo.getMinTeleOwnedScl());
        values.put(TeamInfo.KEY_MaxNumCubesInVault, teamInfo.getMaxNumCubesInVault());
        values.put(TeamInfo.KEY_MinNumCubesInVault, teamInfo.getMinNumCubesInVault());
        values.put(TeamInfo.KEY_MaxActiveFceTime, teamInfo.getMaxActiveFceTime());
        values.put(TeamInfo.KEY_MinActiveFceTime, teamInfo.getMinActiveFceTime());
        values.put(TeamInfo.KEY_MaxActiveLevTime, teamInfo.getMaxActiveLevTime());
        values.put(TeamInfo.KEY_MinActiveLevTime, teamInfo.getMinActiveLevTime());
        values.put(TeamInfo.KEY_MaxActiveBstTime, teamInfo.getMaxActiveBstTime());
        values.put(TeamInfo.KEY_MinActiveBstTime, teamInfo.getMinActiveBstTime());
        values.put(TeamInfo.KEY_MaxCubesAtActiveFce, teamInfo.getMaxCubesAtActiveFce());
        values.put(TeamInfo.KEY_MinCubesAtActiveFce, teamInfo.getMinCubesAtActiveFce());
        values.put(TeamInfo.KEY_MaxCubesAtActiveLev, teamInfo.getMaxCubesAtActiveLev());
        values.put(TeamInfo.KEY_MinCubesAtActiveLev, teamInfo.getMinCubesAtActiveLev());
        values.put(TeamInfo.KEY_MaxCubesAtActiveBst, teamInfo.getMaxCubesAtActiveBst());
        values.put(TeamInfo.KEY_MinCubesAtActiveBst, teamInfo.getMinCubesAtActiveBst());

        //check if there is a conflict. It should return -1 if there is a copy of the exact combination of the Primary Keys
        teamInfoId=(int)db.insertWithOnConflict(TeamInfo.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
        DatabaseManager.getInstance().closeDatabase();
        //return to check conflict
        return teamInfoId;
    }

    public ArrayList<Integer> getTeams(){
        ArrayList<Integer> teams = new ArrayList<>();
        teams.add(0);
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        String selectQuery = " SELECT TeamInfo." + TeamInfo.KEY_TeamNum
                + " FROM " +TeamInfo.TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                teams.add(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_TeamNum)));
            }
            while(cursor.moveToNext());
        }
        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return teams;
    }
    //updates the first part of the row with a new team
    public int updatePart(TeamInfo teamInfo){
        int teamInfoId;
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(TeamInfo.KEY_TeamNum, teamInfo.getTeamNum());

        //updates row with the same CompId, Match# and Match Position
        teamInfoId = db.update(TeamInfo.TABLE, values,TeamInfo.KEY_TeamNum + " =  '" + teamInfo.getTeamNum(), null);
        DatabaseManager.getInstance().closeDatabase();
        return teamInfoId;
    }

    //deletes all rows in the teamInfo table
    public void delete( ) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        db.delete(TeamInfo.TABLE, null,null);
        DatabaseManager.getInstance().closeDatabase();
    }
    /*
    * All set functions save each part of a single row for each phase of the match
    * using the initial insert that adds the comp, match, team and match pos
    * used for saving data
    * */

    /*
    * get functions gets their phase from part of the row with the current comp, match, and position
    * used for loading data
    * */

    //get the Auto teamInfo from the row with the current comp, match and match position
    public TeamInfo getTeamInfo(int teamNum){
        TeamInfo teamInfo = new TeamInfo();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        //makes the selection query for the teamInfo table to get the auto teamInfo
        String selectQuery = " SELECT TeamInfo." + TeamInfo.KEY_NumMatch
                + ", TeamInfo." + TeamInfo.KEY_PNoShow
                + ", TeamInfo." + TeamInfo.KEY_AvgOff
                + ", TeamInfo." + TeamInfo.KEY_AvgDef
                + ", TeamInfo." + TeamInfo.KEY_AvgTotal
                + ", TeamInfo." + TeamInfo.KEY_AvgNeg
                + ", TeamInfo." + TeamInfo.KEY_AvgAuto
                + ", TeamInfo." + TeamInfo.KEY_PHadAuto
                + ", TeamInfo." + TeamInfo.KEY_PCrossAutoLine
                + ", TeamInfo." + TeamInfo.KEY_PAutoPickedUpCube
                + ", TeamInfo." + TeamInfo.KEY_AvgAutoCubesInSw1
                + ", TeamInfo." + TeamInfo.KEY_AvgAutoCubesInSw2
                + ", TeamInfo." + TeamInfo.KEY_AvgAutoCubesInScl
                + ", TeamInfo." + TeamInfo.KEY_AvgAutoCubesInEx
                + ", TeamInfo." + TeamInfo.KEY_AvgAutoOwnGainSw1
                + ", TeamInfo." + TeamInfo.KEY_AvgAutoOwnedSw1
                + ", TeamInfo." + TeamInfo.KEY_AvgAutoOwnGainSw2
                + ", TeamInfo." + TeamInfo.KEY_AvgAutoOwnedSw2
                + ", TeamInfo." + TeamInfo.KEY_AvgAutoOwnGainScl
                + ", TeamInfo." + TeamInfo.KEY_AvgAutoOwnedScl
                + ", TeamInfo." + TeamInfo.KEY_AvgAutoNumOwnChangesSw1
                + ", TeamInfo." + TeamInfo.KEY_AvgAutoNumOwnChangesSw2
                + ", TeamInfo." + TeamInfo.KEY_AvgAutoNumOwnChangesScl
                + ", TeamInfo." + TeamInfo.KEY_AvgTele
                + ", TeamInfo." + TeamInfo.KEY_PTelePickedUpCube
                + ", TeamInfo." + TeamInfo.KEY_AvgTeleCubesInSw1
                + ", TeamInfo." + TeamInfo.KEY_AvgTeleCubesInSw2
                + ", TeamInfo." + TeamInfo.KEY_AvgTeleCubesInScl
                + ", TeamInfo." + TeamInfo.KEY_AvgTeleCubesInEx
                + ", TeamInfo." + TeamInfo.KEY_AvgTeleOwnGainSw1
                + ", TeamInfo." + TeamInfo.KEY_AvgTeleOwnedSw1
                + ", TeamInfo." + TeamInfo.KEY_AvgTeleOwnGainSw2
                + ", TeamInfo." + TeamInfo.KEY_AvgTeleOwnedSw2
                + ", TeamInfo." + TeamInfo.KEY_AvgTeleOwnGainScl
                + ", TeamInfo." + TeamInfo.KEY_AvgTeleOwnedScl
                + ", TeamInfo." + TeamInfo.KEY_AvgTeleNumOwnChangesSw1
                + ", TeamInfo." + TeamInfo.KEY_AvgTeleNumOwnChangesSw2
                + ", TeamInfo." + TeamInfo.KEY_AvgTeleNumOwnChangesScl
                + ", TeamInfo." + TeamInfo.KEY_PClimb
                + ", TeamInfo." + TeamInfo.KEY_PClimbAssist
                + ", TeamInfo." + TeamInfo.KEY_PParking
                + ", TeamInfo." + TeamInfo.KEY_PHumanPlayer
                + ", TeamInfo." + TeamInfo.KEY_PTechFoul
                + ", TeamInfo." + TeamInfo.KEY_HadFoul
                + ", TeamInfo." + TeamInfo.KEY_PFoul
                + ", TeamInfo." + TeamInfo.KEY_PYellowCard
                + ", TeamInfo." + TeamInfo.KEY_PRedCard
                + ", TeamInfo." + TeamInfo.KEY_PDisabled
                + ", TeamInfo." + TeamInfo.KEY_AvgNumCubesInVault
                + ", TeamInfo." + TeamInfo.KEY_AvgActiveFceTime
                + ", TeamInfo." + TeamInfo.KEY_AvgActiveLevTime
                + ", TeamInfo." + TeamInfo.KEY_AvgActiveBstTime
                + ", TeamInfo." + TeamInfo.KEY_AvgCubesAtActiveFce
                + ", TeamInfo." + TeamInfo.KEY_AvgCubesAtActiveLev
                + ", TeamInfo." + TeamInfo.KEY_AvgCubesAtActiveBst
                + " FROM " + TeamInfo.TABLE
                + " WHERE TeamInfo." + TeamInfo.KEY_TeamNum + " = " + teamNum;

        Log.d(TAG, selectQuery);
        //uses the selection query to get rows from the database one at a time
        Cursor cursor = db.rawQuery(selectQuery, null);
        //gets the first row that matches the specifications from the selection query
        if (cursor.moveToFirst()){
            teamInfo.setNumMatch(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_NumMatch)));
            teamInfo.setPNoShow(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_PNoShow)));
            teamInfo.setOffensiveWS(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgOff)));
            teamInfo.setDefensiveWS(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgDef)));
            teamInfo.setTotalWS(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTotal)));
            teamInfo.setNegWS(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgNeg)));
            teamInfo.setAutoWS(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgAuto)));
            teamInfo.setPHadAuto(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_PHadAuto)));
            teamInfo.setPCrossAutoLine(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_PCrossAutoLine)));
            teamInfo.setPAutoPickedUpCube(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_PAutoPickedUpCube)));
            teamInfo.setAvgAutoCubesInSw1(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgAutoCubesInSw1)));
            teamInfo.setAvgAutoCubesInScl(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgAutoCubesInScl)));
            teamInfo.setAvgAutoCubesInSw2(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgAutoCubesInSw2)));
            teamInfo.setAvgAutoCubesInEx(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgAutoCubesInEx)));
            teamInfo.setAvgAutoOwnGainSw1(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgAutoOwnGainSw1)));
            teamInfo.setAvgAutoOwnedSw1(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgAutoOwnedSw1)));
            teamInfo.setAvgAutoOwnGainSw2(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgAutoOwnGainSw2)));
            teamInfo.setAvgAutoOwnedSw2(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgAutoOwnedSw2)));
            teamInfo.setAvgAutoOwnGainScl(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgAutoOwnGainScl)));
            teamInfo.setAvgAutoOwnedScl(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgAutoOwnedScl)));
            teamInfo.setAvgAutoNumOwnChangesSw1(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgAutoNumOwnChangesSw1)));
            teamInfo.setAvgAutoNumOwnChangesSw2(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgAutoNumOwnChangesSw2)));
            teamInfo.setAvgAutoNumOwnChangesScl(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgAutoNumOwnChangesScl)));
            teamInfo.setTeleWS(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTele)));
            teamInfo.setPTelePickedUpCube(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_PTelePickedUpCube)));
            teamInfo.setAvgTeleCubesInSw1(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTeleCubesInSw1)));
            teamInfo.setAvgTeleCubesInScl(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTeleCubesInScl)));
            teamInfo.setAvgTeleCubesInSw2(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTeleCubesInSw2)));
            teamInfo.setAvgTeleCubesInEx(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTeleCubesInEx)));
            teamInfo.setAvgTeleOwnGainSw1(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTeleOwnGainSw1)));
            teamInfo.setAvgTeleOwnedSw1(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTeleOwnedSw1)));
            teamInfo.setAvgTeleOwnGainSw2(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTeleOwnGainSw2)));
            teamInfo.setAvgTeleOwnedSw2(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTeleOwnedSw2)));
            teamInfo.setAvgTeleOwnGainScl(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTeleOwnGainScl)));
            teamInfo.setAvgTeleOwnedScl(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTeleOwnedScl)));
            teamInfo.setAvgTeleNumOwnChangesSw1(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTeleNumOwnChangesSw1)));
            teamInfo.setAvgTeleNumOwnChangesSw2(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTeleNumOwnChangesSw2)));
            teamInfo.setAvgTeleNumOwnChangesScl(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgTeleNumOwnChangesScl)));
            teamInfo.setPClimb(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_PClimb)));
            teamInfo.setPClimbAssist(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_PClimbAssist)));
            teamInfo.setPParking(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_PParking)));
            teamInfo.setPHumanPlayer(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_PHumanPlayer)));
            teamInfo.setHadFoul(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_HadFoul)));
            teamInfo.setPTechFoul(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_PTechFoul)));
            teamInfo.setPFoul(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_PFoul)));
            teamInfo.setPYellowCard(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_PYellowCard)));
            teamInfo.setPRedCard(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_PRedCard)));
            teamInfo.setPDisabled(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_PDisabled)));
            teamInfo.setAvgNumCubesInVault(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgNumCubesInVault)));
            teamInfo.setAvgActiveFceTime(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgActiveFceTime)));
            teamInfo.setAvgActiveLevTime(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgActiveLevTime)));
            teamInfo.setAvgActiveBstTime(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgActiveBstTime)));
            teamInfo.setAvgCubesAtActiveFce(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgCubesAtActiveFce)));
            teamInfo.setAvgCubesAtActiveLev(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgCubesAtActiveLev)));
            teamInfo.setAvgCubesAtActiveBst(cursor.getDouble(cursor.getColumnIndex(TeamInfo.KEY_AvgCubesAtActiveBst)));
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        //returns a teamInfo row object with the values from the database
        return teamInfo;
    }
    public TeamInfo getMaxMin(int teamNum){
        TeamInfo teamInfo = new TeamInfo();

        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        //makes the selection query for the teamInfo table to get the auto teamInfo
        String selectQuery = " SELECT TeamInfo." + TeamInfo.KEY_MaxAutoNumOwnChangesSw1
                + ", TeamInfo." + TeamInfo.KEY_MinAutoNumOwnChangesSw1
                + ", TeamInfo." + TeamInfo.KEY_MaxAutoNumOwnChangesSw2
                + ", TeamInfo." + TeamInfo.KEY_MinAutoNumOwnChangesSw2
                + ", TeamInfo." + TeamInfo.KEY_MaxAutoNumOwnChangesScl
                + ", TeamInfo." + TeamInfo.KEY_MinAutoNumOwnChangesScl
                + ", TeamInfo." + TeamInfo.KEY_MaxAutoCubesInSw1
                + ", TeamInfo." + TeamInfo.KEY_MinAutoCubesInSw1
                + ", TeamInfo." + TeamInfo.KEY_MaxAutoCubesInSw2
                + ", TeamInfo." + TeamInfo.KEY_MinAutoCubesInSw2
                + ", TeamInfo." + TeamInfo.KEY_MaxAutoCubesInScl
                + ", TeamInfo." + TeamInfo.KEY_MinAutoCubesInScl
                + ", TeamInfo." + TeamInfo.KEY_MaxAutoCubesInEx
                + ", TeamInfo." + TeamInfo.KEY_MinAutoCubesInEx
                + ", TeamInfo." + TeamInfo.KEY_MaxAutoOwnGainSw1
                + ", TeamInfo." + TeamInfo.KEY_MinAutoOwnGainSw1
                + ", TeamInfo." + TeamInfo.KEY_MaxAutoOwnedSw1
                + ", TeamInfo." + TeamInfo.KEY_MinAutoOwnedSw1
                + ", TeamInfo." + TeamInfo.KEY_MaxAutoOwnGainSw2
                + ", TeamInfo." + TeamInfo.KEY_MinAutoOwnGainSw2
                + ", TeamInfo." + TeamInfo.KEY_MaxAutoOwnedSw2
                + ", TeamInfo." + TeamInfo.KEY_MinAutoOwnedSw2
                + ", TeamInfo." + TeamInfo.KEY_MaxAutoOwnGainScl
                + ", TeamInfo." + TeamInfo.KEY_MinAutoOwnGainScl
                + ", TeamInfo." + TeamInfo.KEY_MaxAutoOwnedScl
                + ", TeamInfo." + TeamInfo.KEY_MinAutoOwnedScl
                + ", TeamInfo." + TeamInfo.KEY_MaxTeleNumOwnChangesSw1
                + ", TeamInfo." + TeamInfo.KEY_MinTeleNumOwnChangesSw1
                + ", TeamInfo." + TeamInfo.KEY_MaxTeleNumOwnChangesSw2
                + ", TeamInfo." + TeamInfo.KEY_MinTeleNumOwnChangesSw2
                + ", TeamInfo." + TeamInfo.KEY_MaxTeleNumOwnChangesScl
                + ", TeamInfo." + TeamInfo.KEY_MinTeleNumOwnChangesScl
                + ", TeamInfo." + TeamInfo.KEY_MaxTeleCubesInSw1
                + ", TeamInfo." + TeamInfo.KEY_MinTeleCubesInSw1
                + ", TeamInfo." + TeamInfo.KEY_MaxTeleCubesInSw2
                + ", TeamInfo." + TeamInfo.KEY_MinTeleCubesInSw2
                + ", TeamInfo." + TeamInfo.KEY_MaxTeleCubesInScl
                + ", TeamInfo." + TeamInfo.KEY_MinTeleCubesInScl
                + ", TeamInfo." + TeamInfo.KEY_MaxTeleCubesInEx
                + ", TeamInfo." + TeamInfo.KEY_MinTeleCubesInEx
                + ", TeamInfo." + TeamInfo.KEY_MaxTeleOwnGainSw1
                + ", TeamInfo." + TeamInfo.KEY_MinTeleOwnGainSw1
                + ", TeamInfo." + TeamInfo.KEY_MaxTeleOwnedSw1
                + ", TeamInfo." + TeamInfo.KEY_MinTeleOwnedSw1
                + ", TeamInfo." + TeamInfo.KEY_MaxTeleOwnGainSw2
                + ", TeamInfo." + TeamInfo.KEY_MinTeleOwnGainSw2
                + ", TeamInfo." + TeamInfo.KEY_MaxTeleOwnedSw2
                + ", TeamInfo." + TeamInfo.KEY_MinTeleOwnedSw2
                + ", TeamInfo." + TeamInfo.KEY_MaxTeleOwnGainScl
                + ", TeamInfo." + TeamInfo.KEY_MinTeleOwnGainScl
                + ", TeamInfo." + TeamInfo.KEY_MaxTeleOwnedScl
                + ", TeamInfo." + TeamInfo.KEY_MinTeleOwnedScl
                + ", TeamInfo." + TeamInfo.KEY_MaxNumCubesInVault
                + ", TeamInfo." + TeamInfo.KEY_MinNumCubesInVault
                + ", TeamInfo." + TeamInfo.KEY_MaxActiveFceTime
                + ", TeamInfo." + TeamInfo.KEY_MinActiveFceTime
                + ", TeamInfo." + TeamInfo.KEY_MaxActiveLevTime
                + ", TeamInfo." + TeamInfo.KEY_MinActiveLevTime
                + ", TeamInfo." + TeamInfo.KEY_MaxActiveBstTime
                + ", TeamInfo." + TeamInfo.KEY_MinActiveBstTime
                + ", TeamInfo." + TeamInfo.KEY_MaxCubesAtActiveFce
                + ", TeamInfo." + TeamInfo.KEY_MinCubesAtActiveFce
                + ", TeamInfo." + TeamInfo.KEY_MaxCubesAtActiveLev
                + ", TeamInfo." + TeamInfo.KEY_MinCubesAtActiveLev
                + ", TeamInfo." + TeamInfo.KEY_MaxCubesAtActiveBst
                + ", TeamInfo." + TeamInfo.KEY_MinCubesAtActiveBst
                + " FROM " + TeamInfo.TABLE
                + " WHERE TeamInfo." + TeamInfo.KEY_TeamNum + " = " + teamNum;

        Log.d(TAG, selectQuery);
        //uses the selection query to get rows from the database one at a time
        Cursor cursor = db.rawQuery(selectQuery, null);
        //gets the first row that matches the specifications from the selection query
        if (cursor.moveToFirst()){
            teamInfo.setMaxAutoNumOwnChangesSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxAutoNumOwnChangesSw1)));
            teamInfo.setMinAutoNumOwnChangesSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinAutoNumOwnChangesSw1)));
            teamInfo.setMaxAutoNumOwnChangesSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxAutoNumOwnChangesSw2)));
            teamInfo.setMinAutoNumOwnChangesSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinAutoNumOwnChangesSw2)));
            teamInfo.setMaxAutoNumOwnChangesScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxAutoNumOwnChangesScl)));
            teamInfo.setMinAutoNumOwnChangesScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinAutoNumOwnChangesScl)));
            teamInfo.setMaxAutoCubesInSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxAutoCubesInSw1)));
            teamInfo.setMinAutoCubesInSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinAutoCubesInSw1)));
            teamInfo.setMaxAutoCubesInSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxAutoCubesInSw2)));
            teamInfo.setMinAutoCubesInSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinAutoCubesInSw2)));
            teamInfo.setMaxAutoCubesInScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxAutoCubesInScl)));
            teamInfo.setMinAutoCubesInScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinAutoCubesInScl)));
            teamInfo.setMaxAutoCubesInEx(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxAutoCubesInEx)));
            teamInfo.setMinAutoCubesInEx(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinAutoCubesInEx)));
            teamInfo.setMaxAutoOwnGainSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxAutoOwnGainSw1)));
            teamInfo.setMinAutoOwnGainSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinAutoOwnGainSw1)));
            teamInfo.setMaxAutoOwnedSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxAutoOwnedSw1)));
            teamInfo.setMinAutoOwnedSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinAutoOwnedSw1)));
            teamInfo.setMaxAutoOwnGainSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxAutoOwnGainSw2)));
            teamInfo.setMinAutoOwnGainSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinAutoOwnGainSw2)));
            teamInfo.setMaxAutoOwnedSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxAutoOwnedSw2)));
            teamInfo.setMinAutoOwnedSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinAutoOwnedSw2)));
            teamInfo.setMaxAutoOwnGainScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxAutoOwnGainScl)));
            teamInfo.setMinAutoOwnGainScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinAutoOwnGainScl)));
            teamInfo.setMaxAutoOwnedScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxAutoOwnedScl)));
            teamInfo.setMinAutoOwnedScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinAutoOwnedScl)));
            teamInfo.setMaxTeleNumOwnChangesSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxTeleNumOwnChangesSw1)));
            teamInfo.setMinTeleNumOwnChangesSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinTeleNumOwnChangesSw1)));
            teamInfo.setMaxTeleNumOwnChangesSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxTeleNumOwnChangesSw2)));
            teamInfo.setMinTeleNumOwnChangesSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinTeleNumOwnChangesSw2)));
            teamInfo.setMaxTeleNumOwnChangesScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxTeleNumOwnChangesScl)));
            teamInfo.setMinTeleNumOwnChangesScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinTeleNumOwnChangesScl)));
            teamInfo.setMaxTeleCubesInSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxTeleCubesInSw1)));
            teamInfo.setMinTeleCubesInSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinTeleCubesInSw1)));
            teamInfo.setMaxTeleCubesInSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxTeleCubesInSw2)));
            teamInfo.setMinTeleCubesInSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinTeleCubesInSw2)));
            teamInfo.setMaxTeleCubesInScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxTeleCubesInScl)));
            teamInfo.setMinTeleCubesInScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinTeleCubesInScl)));
            teamInfo.setMaxTeleCubesInEx(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxTeleCubesInEx)));
            teamInfo.setMinTeleCubesInEx(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinTeleCubesInEx)));
            teamInfo.setMaxTeleOwnGainSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxTeleOwnGainSw1)));
            teamInfo.setMinTeleOwnGainSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinTeleOwnGainSw1)));
            teamInfo.setMaxTeleOwnedSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxTeleOwnedSw1)));
            teamInfo.setMinTeleOwnedSw1(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinTeleOwnedSw1)));
            teamInfo.setMaxTeleOwnGainSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxTeleOwnGainSw2)));
            teamInfo.setMinTeleOwnGainSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinTeleOwnGainSw2)));
            teamInfo.setMaxTeleOwnedSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxTeleOwnedSw2)));
            teamInfo.setMinTeleOwnedSw2(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinTeleOwnedSw2)));
            teamInfo.setMaxTeleOwnGainScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxTeleOwnGainScl)));
            teamInfo.setMinTeleOwnGainScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinTeleOwnGainScl)));
            teamInfo.setMaxTeleOwnedScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxTeleOwnedScl)));
            teamInfo.setMinTeleOwnedScl(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinTeleOwnedScl)));
            teamInfo.setMaxNumCubesInVault(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxNumCubesInVault)));
            teamInfo.setMinNumCubesInVault(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinNumCubesInVault)));
            teamInfo.setMaxActiveFceTime(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxActiveFceTime)));
            teamInfo.setMinActiveFceTime(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinActiveFceTime)));
            teamInfo.setMaxActiveLevTime(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxActiveLevTime)));
            teamInfo.setMinActiveLevTime(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinActiveLevTime)));
            teamInfo.setMaxActiveBstTime(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxActiveBstTime)));
            teamInfo.setMinActiveBstTime(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinActiveBstTime)));
            teamInfo.setMaxCubesAtActiveFce(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxCubesAtActiveFce)));
            teamInfo.setMinCubesAtActiveFce(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinCubesAtActiveFce)));
            teamInfo.setMaxCubesAtActiveLev(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxCubesAtActiveLev)));
            teamInfo.setMinCubesAtActiveLev(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinCubesAtActiveLev)));
            teamInfo.setMaxCubesAtActiveBst(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MaxCubesAtActiveBst)));
            teamInfo.setMinCubesAtActiveBst(cursor.getInt(cursor.getColumnIndex(TeamInfo.KEY_MinCubesAtActiveBst)));
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        //returns a teamInfo row object with the values from the database
        return teamInfo;
    }
}

