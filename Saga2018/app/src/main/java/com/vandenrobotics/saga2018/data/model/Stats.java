
package com.vandenrobotics.saga2018.data.model;

/**
 * Created by Programming701-A on 12/13/2017.
 */

public class Stats {

    public static final String TABLE = "Stats";

    public static final String INDEX = "comp_match_team";

    public static final String KEY_CompId = "CompID";
    public static final String KEY_MatchNum = "MatchNumber";
    public static final String KEY_TeamNum = "TeamNumber";
    public static final String KEY_MatchPosition = "MatchPosition";
                                                                                                                                                                                                                                                                       public static final String KEY_NoShow = "NoShow";
    public static final String KEY_HadAuto = "HadAuto";
    public static final String KEY_CrossedAutoLine = "CrossedAutoLine";
    public static final String KEY_AutoPickedUpCube = "AutoPickedUpCube";
    public static final String KEY_AutoCubesInSwitch1 = "Auto_CubesInSw1";
    public static final String KEY_AutoCubesInScale = "Auto_CubesInScl";
    public static final String KEY_AutoCubesInSwitch2 = "Auto_CubesInSw2";
    public static final String KEY_AutoCubesInExchange = "Auto_CubesInEx";
    public static final String KEY_TeleSwitch1Cubes = "TeleSwitch1Cubes";
    public static final String KEY_TeleSwitch2Cubes = "TeleSwitch2Cubes";
    public static final String KEY_TeleScaleCubes = "TeleScaleCubes";
    public static final String KEY_TeleExchangeCubes = "TeleExchangeCubes";
    public static final String KEY_PickedUpCube = "PickedUpCube";
    public static final String KEY_TeleHumanPlayer = "HumanPlayerControlledVault";
    public static final String KEY_RobotDisabled = "RobotDisabled";
    public static final String KEY_RedCard = "RedCard";
    public static final String KEY_YellowCard = "YellowCard";
    public static final String KEY_Fouls = "Fouls";
    public static final String KEY_TechFouls = "TechFouls";
    public static final String KEY_Climb = "Climb";
    public static final String KEY_ClimbAssist = "ClimbAssist";
    public static final String KEY_Parking = "Parking";

    private String compId;
    private int matchNum;
    private int teamNum;
    private int matchPos;
    private int noShow;
    private int hadAuto;
    private int crossedAutoLine;
    private int pickedUpCube;
    private int autoPickedUpCube;
    private int autoCubesInSw1;
    private int autoCubesInScl;
    private int autoCubesInSw2;
    private int autoCubesInEx;
    private int teleCubesInSw1;
    private int teleCubesInSw2;
    private int teleCubesInSc1;
    private int teleCubesInEx;
    private int humanPlayer;
    private int redCard;
    private int yellowCard;
    private int foul;
    private int techFoul;
    private int disabled;
    private int climb;
    private int climbAssist;
    private int parking;

    public Stats(){
        compId = "None";
        matchNum = 0;
        teamNum = 0;
        matchPos = 0;
        noShow = 0;
        hadAuto = 0;
        crossedAutoLine = 0;
        pickedUpCube = 0;
        autoPickedUpCube = 0;
        autoCubesInSw1 = 0;
        autoCubesInScl = 0;
        autoCubesInSw2 = 0;
        autoCubesInEx = 0;
        teleCubesInSw1 = 0;
        teleCubesInSc1 = 0;
        teleCubesInSw2 = 0;
        teleCubesInEx = 0;
        humanPlayer = 0;
        redCard = 0;
        yellowCard = 0;
        foul = 0;
        techFoul = 0;
        disabled = 0;
        climb = 0;
        climbAssist = 0;
        parking = 0;
    }

    public String getCompId(){
        return compId;
    }
    public void setCompId(String s){
        compId = s;
    }

    public int getMatchNum(){
        return matchNum;
    }
    public void setMatchNum(int i){
        matchNum = i;
    }

    public int getTeamNum(){
        return teamNum;
    }
    public void setTeamNum(int i){
        teamNum = i;
    }

    public int getMatchPos(){
        return matchPos;
    }
    public void setMatchPos(int i){
        matchPos = i;
    }

    public int getNoShow(){
        return noShow;
    }
    public void setNoShow(int i){
        noShow = i;
    }

    public int getHadAuto(){
        return hadAuto;
    }
    public void setHadAuto(int i){
        hadAuto = i;
    }

    public int getCrossedAutoLine(){
        return crossedAutoLine;
    }
    public void setCrossedAutoLine(int i){
        crossedAutoLine = i;
    }

    public int getPickedUpCube(){
        return pickedUpCube;
    }
    public void setPickedUpCube(int i){
        pickedUpCube = i;
    }

    public int getAutoPickedUpCube(){
        return autoPickedUpCube;
    }
    public void setAutoPickedUpCube(int i){
        autoPickedUpCube = i;
    }

    public int getAutoCubesInSw1(){
        return autoCubesInSw1;
    }
    public void setAutoCubesInSw1(int i){
        autoCubesInSw1 = i;
    }

    public int getAutoCubesInScl(){
        return autoCubesInScl;
    }
    public void setAutoCubesInScl(int i){
        autoCubesInScl = i;
    }

    public int getAutoCubesInSw2(){
        return autoCubesInSw2;
    }
    public void setAutoCubesInSw2(int i){
        autoCubesInSw2 = i;
    }

    public int getAutoCubesInEx(){
        return autoCubesInEx;
    }
    public void setAutoCubesInEx(int i){
        autoCubesInEx = i;
    }

    public int getTeleCubesInSw1(){
        return teleCubesInSw1;
    }
    public void setTeleCubesInSw1(int i){
        teleCubesInSw1 = i;
    }

    public int getTeleCubesInSw2(){
        return teleCubesInSw2;
    }
    public void setTeleCubesInSw2(int i){
        teleCubesInSw2 = i;
    }

    public int getTeleCubesInSc1(){
        return teleCubesInSc1;
    }
    public void setTeleCubesInSc1(int i){
        teleCubesInSc1 = i;
    }

    public int getRedCard(){
        return redCard;
    }
    public void setRedCard(int i){
        redCard = i;
    }

    public int getYellowCard(){
        return yellowCard;
    }
    public void setYellowCard(int i){
        yellowCard = i;
    }

    public int getHumanPlayer(){
        return humanPlayer;
    }
    public void setHumanPlayer(int i){
        humanPlayer = i;
    }

    public int getDisabled(){
        return disabled;
    }
    public void setDisabled(int i){
        disabled = i;
    }

    public int getFoul(){
        return foul;
    }
    public void setFoul(int i){
        foul = i;
    }

    public int getTechFoul(){
        return techFoul;
    }
    public void setTechFoul(int i){
        techFoul = i;
    }

    public int getTeleCubesInEx(){
        return teleCubesInEx;
    }
    public void setTeleCubesInEx(int i){
        teleCubesInEx = i;
    }

    public int getTeleClimb(){return climb;}
    public void setTeleClimb (int i){climb = i;}

    public int getTeleClimbAssist(){return climbAssist;}
    public void setTeleClimbAssist (int i){climbAssist = i;}

    public int getParking(){return parking;}
    public void setParking(int i){parking = i;}
}

