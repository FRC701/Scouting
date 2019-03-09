package com.vandenrobotics.tyr2018.data.repo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.vandenrobotics.tyr2018.data.DatabaseManager;
import com.vandenrobotics.tyr2018.data.model.Teams;
import com.vandenrobotics.tyr2018.data.model.TeamInfo;

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
                + TeamInfo.KEY_AvgOff + " REAL , "
                + TeamInfo.KEY_AvgTotal + " REAL , "
                + TeamInfo.KEY_AvgNeg + " REAL , "
                + TeamInfo.KEY_PNoShow + " REAL  , "
                + TeamInfo.KEY_PStartLevel + " REAL , "
                + TeamInfo.KEY_PStartLevel2 + " REAL , "
                + TeamInfo.KEY_PPreloadCargo + " REAL , "
                + TeamInfo.KEY_PPreloadHatch + " REAL , "
                + TeamInfo.KEY_PCrossHubLine + " REAL , "
                + TeamInfo.KEY_AvgRobotTopC + " REAL , "
                + TeamInfo.KEY_AvgRobotTopH + " REAL , "
                + TeamInfo.KEY_AvgRobotMiddleC + " REAL , "
                + TeamInfo.KEY_AvgRobotMiddleH + " REAL , "
                + TeamInfo.KEY_AvgRobotBottomC + " REAL , "
                + TeamInfo.KEY_AvgRobotBottomH + " REAL , "
                + TeamInfo.KEY_AvgCargoShipC + " REAL , "
                + TeamInfo.KEY_AvgCargoShipH + " REAL , "
                + TeamInfo.KEY_PEndLevel1 + " REAL , "
                + TeamInfo.KEY_PEndLevel2 + " REAL , "
                + TeamInfo.KEY_PEndLevel3 + " REAL , "
                + TeamInfo.KEY_PEndNone + " REAL , "
                + TeamInfo.KEY_PFoul + " REAL , "
                + TeamInfo.KEY_PTechFoul + " REAL , "
                + TeamInfo.KEY_PYellowCard + " REAL , "
                + TeamInfo.KEY_PRedCard + " REAL , "
                + TeamInfo.KEY_PDisabled + " REAL , "
                + TeamInfo.KEY_MaxRocketTopC + " REAL , "
                + TeamInfo.KEY_MinRocketTopC+ " REAL , "
                + TeamInfo.KEY_MaxRocketTopH + " REAL , "
                + TeamInfo.KEY_MinRocketTopH + " REAL , "
                + TeamInfo.KEY_MaxRocketMiddleC + " REAL , "
                + TeamInfo.KEY_MinRocketMiddleC + " REAL , "
                + TeamInfo.KEY_MaxRocketMiddleH + " REAL , "
                + TeamInfo.KEY_MinRocketMiddleH + " REAL , "
                + TeamInfo.KEY_MaxRocketBottomC + " REAL , "
                + TeamInfo.KEY_MinRocketBottomC + " REAL , "
                + TeamInfo.KEY_MaxRocketBottomH + " REAL , "
                + TeamInfo.KEY_MinRocketBottomH + " REAL , "
                + TeamInfo.KEY_MaxCargoShipC + " REAL , "
                + TeamInfo.KEY_MinCargoShipC + " REAL , "
                + TeamInfo.KEY_MaxCargoShipH + " REAL , "
                + TeamInfo.KEY_MinCargoShipH + " REAL , " +
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
        values.put(TeamInfo.KEY_AvgTotal, teamInfo.getTotalWS());
        values.put(TeamInfo.KEY_AvgNeg, teamInfo.getNegWS());
        values.put(TeamInfo.KEY_PNoShow, teamInfo.getPNoShow());
        values.put(TeamInfo.KEY_PStartLevel, teamInfo.getpStartLevel1());
        values.put(TeamInfo.KEY_PStartLevel2, teamInfo.getpStartLevel2());
        values.put(TeamInfo.KEY_PPreloadCargo, teamInfo.getpPreloadCargo());
        values.put(TeamInfo.KEY_PPreloadHatch, teamInfo.getpPreloadHatch());
        values.put(TeamInfo.KEY_PCrossHubLine, teamInfo.getpCrossHubLine());
        values.put(TeamInfo.KEY_AvgRobotTopC, teamInfo.getAvgRocketTopC());
        values.put(TeamInfo.KEY_AvgRobotTopH, teamInfo.getAvgRocketTopH());
        values.put(TeamInfo.KEY_AvgRobotMiddleC, teamInfo.getAvgRocketMiddleC());
        values.put(TeamInfo.KEY_AvgRobotMiddleH, teamInfo.getAvgRocketMiddleH());
        values.put(TeamInfo.KEY_AvgRobotBottomC, teamInfo.getAvgRocketBottomC());
        values.put(TeamInfo.KEY_AvgRobotBottomH, teamInfo.getAvgRocketBottomH());
        values.put(TeamInfo.KEY_AvgCargoShipC, teamInfo.getAvgCargoShipC());
        values.put(TeamInfo.KEY_AvgCargoShipH, teamInfo.getAvgCargoShipH());
        values.put(TeamInfo.KEY_PEndLevel1, teamInfo.getpEndLevel1());
        values.put(TeamInfo.KEY_PEndLevel2, teamInfo.getpEndLevel2());
        values.put(TeamInfo.KEY_PEndLevel3, teamInfo.getpEndLevel3());
        values.put(TeamInfo.KEY_PEndNone, teamInfo.getpEndNone());
        values.put(TeamInfo.KEY_PFoul, teamInfo.getPFoul());
        values.put(TeamInfo.KEY_PTechFoul, teamInfo.getPTechFoul());
        values.put(TeamInfo.KEY_PYellowCard, teamInfo.getPYellowCard());
        values.put(TeamInfo.KEY_PRedCard, teamInfo.getPRedCard());
        values.put(TeamInfo.KEY_PDisabled, teamInfo.getPDisabled());
        values.put(TeamInfo.KEY_MaxRocketTopC, teamInfo.getMaxRocketTopC());
        values.put(TeamInfo.KEY_MinRocketTopC, teamInfo.getMinRocketTopC());
        values.put(TeamInfo.KEY_MaxRocketTopH, teamInfo.getMaxRocketTopH());
        values.put(TeamInfo.KEY_MinRocketTopH, teamInfo.getMinRocketTopH());
        values.put(TeamInfo.KEY_MaxRocketMiddleC, teamInfo.getMaxRocketMiddleC());
        values.put(TeamInfo.KEY_MinRocketMiddleC, teamInfo.getMinRocketMiddleC());
        values.put(TeamInfo.KEY_MaxRocketMiddleH, teamInfo.getMaxRocketMiddleH());
        values.put(TeamInfo.KEY_MinRocketMiddleH, teamInfo.getMinRocketMiddleH());
        values.put(TeamInfo.KEY_MaxRocketBottomC, teamInfo.getMaxRocketBottomC());
        values.put(TeamInfo.KEY_MinRocketBottomC, teamInfo.getMinRocketBottomC());
        values.put(TeamInfo.KEY_MaxRocketBottomH, teamInfo.getMaxRocketBottomH());
        values.put(TeamInfo.KEY_MinRocketBottomH, teamInfo.getMinRocketBottomH());
        values.put(TeamInfo.KEY_MaxCargoShipC, teamInfo.getMaxCargoShipC());
        values.put(TeamInfo.KEY_MinCargoShipC, teamInfo.getMinCargoShipC());
        values.put(TeamInfo.KEY_MaxCargoShipH, teamInfo.getMaxCargoShipH());
        values.put(TeamInfo.KEY_MinCargoShipH, teamInfo.getMinCargoShipH());
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

