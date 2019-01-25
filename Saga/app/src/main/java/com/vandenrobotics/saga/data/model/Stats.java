
package com.vandenrobotics.saga.data.model;

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
    public static final String KEY_StartLevel1 = "StartLevel1";
    public static final String KEY_StartLevel2 = "StartLevel2";
    public static final String KEY_PreloadCargo = "PreloadCargo";
    public static final String KEY_PreloadHatch = "PreloadHatch";
    public static final String KEY_SsComments = "SandstormComments";
    public static final String KEY_RocketTopC = "RocketTopCargo";
    public static final String KEY_RocketTopH = "RocketTopHatch";
    public static final String KEY_RocketMiddleC = "RocketMiddleCargo";
    public static final String KEY_RocketMiddleH = "RocketMiddleHatch";
    public static final String KEY_RocketBottomC = "RocketBottomCargo";
    public static final String KEY_RocketBottomH = "RocketBottomHatch";
    public static final String KEY_CargoShipC = "CargoShipCargo";
    public static final String KEY_CargoShipH = "CargoShipHatch";
    public static final String KEY_CrossHubLine= "CrossHubLine";
    public static final String KEY_EndLevel1 = "EndLevel1";
    public static final String KEY_EndLevel2 = "EndLevel2";
    public static final String KEY_EndLevel3 = "EndLevel3";
    public static final String KEY_EndNone = "EndNone";
    public static final String KEY_RobotDisabled = "RobotDisabled";
    public static final String KEY_RedCard = "RedCard";
    public static final String KEY_YellowCard = "YellowCard";
    public static final String KEY_Fouls = "Fouls";
    public static final String KEY_TechFouls = "TechFouls";
    public static final String KEY_Disqualified = "Disqualified";

    private String compId;
    private int matchNum;
    private int teamNum;
    private int matchPos;
    private int noShow;
    private int hadAuto;
    private int redCard;
    private int yellowCard;
    private int foul;
    private int techFoul;
    private int disabled;
    private int startLevel1;
    private int startLevel2;
    private int preloadCargo;
    private int preloadHatch;
    private String sandstormComments;
    private int rocketTopC;
    private int rocketTopH;
    private int rocketMiddleC;
    private int rocketMiddleH;
    private int rocketBottomC;
    private int rocketBottomH;
    private int cargoShipC;
    private int cargoShipH;
    private int crossHubLine;
    private int endLevel1;
    private int endLevel2;
    private int endLevel3;
    private int endNone;
    private int disqualified;


    public Stats(){
        compId = "None";
        matchNum = 0;
        teamNum = 0;
        matchPos = 0;
        noShow = 0;
        hadAuto = 0;
        redCard = 0;
        yellowCard = 0;
        foul = 0;
        techFoul = 0;
        disabled = 0;
        startLevel1 = 0;
        startLevel2 = 0;
        preloadCargo = 0;
        preloadHatch = 0;
        sandstormComments = "";
        rocketTopC = 0;
        rocketTopH = 0;
        rocketMiddleC = 0;
        rocketMiddleH = 0;
        rocketBottomC = 0;
        rocketBottomH = 0;
        cargoShipC = 0;
        cargoShipH = 0;
        crossHubLine = 0;
        endLevel1 = 0;
        endLevel2 = 0;
        endLevel3 = 0;
        endNone = 0;
        disqualified = 0;
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

    public int getStartLevel1() {
        return startLevel1;
    }
    public void setStartLevel1(int b){startLevel1 = b;}

    public int getStartLevel2() {
        return startLevel2;
    }
    public void setStartLevel2(int b){startLevel2 = b;}

    public int getPreloadCargo() {
        return preloadCargo;
    }
    public void setPreloadCargo(int b){preloadCargo = b;}

    public int getPreloadHatch() {
        return preloadHatch;
    }
    public void setPreloadHatch(int b){preloadHatch = b;}

    public String getSsComments() {
        return sandstormComments;
    }
    public void setSscomments(String b){sandstormComments = b;}

    public int getRocketTopC() {
        return rocketTopC;
    }
    public void setRocketTopC(int b){rocketTopC= b;}

    public int getRocketTopH() {
        return rocketTopH;
    }
    public void setRocketTopH(int b){rocketTopH= b;}

    public int getRocketMiddleC() {
        return rocketMiddleC;
    }
    public void setRocketMiddleC(int b){rocketMiddleC= b;}

    public int getRocketMiddleH() {
        return rocketMiddleH;
    }
    public void setRocketMiddleH(int b){rocketMiddleH= b;}

    public int getRocketMBottomC() {
        return rocketBottomC;
    }
    public void setRocketBottomC(int b){rocketBottomC= b;}

    public int getRocketMBottomH() {
        return rocketBottomH;
    }
    public void setRocketBottomH(int b){rocketBottomH= b;}

    public int getCargoShipC() {
        return cargoShipC;
    }
    public void setCargoShipC(int b){cargoShipC= b;}

    public int getCargoShipH() {
        return cargoShipH;
    }
    public void setCargoShipH(int b){cargoShipH= b;}

    public int getCrossHubLine() {
        return crossHubLine;
    }
    public void setCrossHubLine(int b){crossHubLine= b;}

    public int getEndLevel1() {
        return endLevel1;
    }
    public void setEndLevel1(int b){endLevel1 = b;}

    public int getEndLevel2() {
        return endLevel2;
    }
    public void setEndLevel2(int b){endLevel2 = b;}

    public int getEndLevel3() {
        return endLevel3;
    }
    public void setEndLevel3(int b){endLevel3 = b;}

    public int getEndNone() {
        return endNone;
    }
    public void setEndNone(int b){endNone = b;}

    public int getDisqualified(){
        return disqualified;
    }
    public void setDisqualified(int b){disqualified = b;}

}

