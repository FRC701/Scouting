package com.vandenrobotics.stats.data.model;

public class MaxMin {

    public static final String TABLE = "MinMaxWeights";

    public static final String KEY_TeamNum = "TeamNum";
    public static final String INDEX = "team";
    public static final String KEY_MinNoShow = "MinNoShow";
    public static final String KEY_MinStartLevel1 = "MinStartLevel1";
    public static final String KEY_MinStartLevel2 = "MinStartLevel2";
    public static final String KEY_MinPreloadCargo = "MinPreloadCargo";
    public static final String KEY_MinPreloadHatch = "MinPreloadHatch";
    public static final String KEY_MinRocketTopC = "MinRocketTopCargo";
    public static final String KEY_MinRocketTopH = "MinRocketTopHatch";
    public static final String KEY_MinRocketMiddleC = "MinRocketMiddleCargo";
    public static final String KEY_MinRocketMiddleH = "MinRocketMiddleHatch";
    public static final String KEY_MinRocketBottomC = "MinRocketBottomCargo";
    public static final String KEY_MinRocketBottomH = "MinRocketBottomHatch";
    public static final String KEY_MinCargoShipC = "MinCargoShipCargo";
    public static final String KEY_MinCargoShipH = "MinCargoShipHatch";
    public static final String KEY_MinCrossHubLine= "MinCrossHubLine";
    public static final String KEY_MinEndLevel1 = "MinEndLevel1";
    public static final String KEY_MinEndLevel2 = "MinEndLevel2";
    public static final String KEY_MinEndLevel3 = "MinEndLevel3";
    public static final String KEY_MinEndNone = "MinEndNone";
    public static final String KEY_MinRobotDisabled = "MinRobotDisabled";
    public static final String KEY_MinRedCard = "MinRedCard";
    public static final String KEY_MinYellowCard = "MinYellowCard";
    public static final String KEY_MinFouls = "MinFouls";
    public static final String KEY_MinTechFouls = "MinTechFouls";
    public static final String KEY_MinDefense = "MinDefense";


    public static final String KEY_MaxNoShow = "MaxNoShow";
    public static final String KEY_MaxStartLevel1 = "MaxStartLevel1";
    public static final String KEY_MaxStartLevel2 = "MaxStartLevel2";
    public static final String KEY_MaxPreloadCargo = "MaxPreloadCargo";
    public static final String KEY_MaxPreloadHatch = "MaxPreloadHatch";
    public static final String KEY_MaxRocketTopC = "MaxRocketTopCargo";
    public static final String KEY_MaxRocketTopH = "MaxRocketTopHatch";
    public static final String KEY_MaxRocketMiddleC = "MaxRocketMiddleCargo";
    public static final String KEY_MaxRocketMiddleH = "MaxRocketMiddleHatch";
    public static final String KEY_MaxRocketBottomC = "MaxRocketBottomCargo";
    public static final String KEY_MaxRocketBottomH = "MaxRocketBottomHatch";
    public static final String KEY_MaxCargoShipC = "MaxCargoShipCargo";
    public static final String KEY_MaxCargoShipH = "MaxCargoShipHatch";
    public static final String KEY_MaxCrossHubLine= "MaxCrossHubLine";
    public static final String KEY_MaxEndLevel1 = "MaxEndLevel1";
    public static final String KEY_MaxEndLevel2 = "MaxEndLevel2";
    public static final String KEY_MaxEndLevel3 = "MaxEndLevel3";
    public static final String KEY_MaxEndNone = "MaxEndNone";
    public static final String KEY_MaxRobotDisabled = "MaxRobotDisabled";
    public static final String KEY_MaxRedCard = "MaxRedCard";
    public static final String KEY_MaxYellowCard = "MaxYellowCard";
    public static final String KEY_MaxFouls = "MaxFouls";
    public static final String KEY_MaxTechFouls = "MaxTechFouls";
    public static final String KEY_MaxDefense = "MaxDefense";


    private String teamNum;
    private int minNoShow;
    private int minHadAuto;
    private int minRedCard;
    private int minYellowCard;
    private int minFoul;
    private int minTechFoul;
    private int minDisabled;
    private int minStartLevel1;
    private int minStartLevel2;
    private int minPreloadCargo;
    private int minPreloadHatch;
    private int minRocketTopC;
    private int minRocketTopH;
    private int minRocketMiddleC;
    private int minRocketMiddleH;
    private int minRocketBottomC;
    private int minRocketBottomH;
    private int minCargoShipC;
    private int minCargoShipH;
    private int minCrossHubLine;
    private int minEndLevel1;
    private int minEndLevel2;
    private int minEndLevel3;
    private int minEndNone;
    private int minDefense;


    private int maxNoShow;
    private int maxHadAuto;
    private int maxRedCard;
    private int maxYellowCard;
    private int maxFoul;
    private int maxTechFoul;
    private int maxDisabled;
    private int maxStartLevel1;
    private int maxStartLevel2;
    private int maxPreloadCargo;
    private int maxPreloadHatch;
    private int maxRocketTopC;
    private int maxRocketTopH;
    private int maxRocketMiddleC;
    private int maxRocketMiddleH;
    private int maxRocketBottomC;
    private int maxRocketBottomH;
    private int maxCargoShipC;
    private int maxCargoShipH;
    private int maxCrossHubLine;
    private int maxEndLevel1;
    private int maxEndLevel2;
    private int maxEndLevel3;
    private int maxEndNone;
    private int maxDefense;

    public MaxMin(){
        teamNum = "";
        minNoShow = 0;
        minHadAuto = 0;
        minRedCard = 0;
        minYellowCard = 0;
        minFoul = 0;
        minTechFoul = 0;
        minDisabled = 0;
        minStartLevel1 = 0;
        minStartLevel2 = 0;
        minPreloadCargo = 0;
        minPreloadHatch = 0;
        minRocketTopC = 0;
        minRocketTopH = 0;
        minRocketMiddleC = 0;
        minRocketMiddleH = 0;
        minRocketBottomC = 0;
        minRocketBottomH = 0;
        minCargoShipC = 0;
        minCargoShipH = 0;
        minCrossHubLine = 0;
        minEndLevel1 = 0;
        minEndLevel2 = 0;
        minEndLevel3 = 0;
        minEndNone = 0;
        minDefense = 0;

        maxNoShow = 0;
        maxHadAuto = 0;
        maxRedCard = 0;
        maxYellowCard = 0;
        maxFoul = 0;
        maxTechFoul = 0;
        maxDisabled = 0;
        maxStartLevel1 = 0;
        maxStartLevel2 = 0;
        maxPreloadCargo = 0;
        maxPreloadHatch = 0;
        maxRocketTopC = 0;
        maxRocketTopH = 0;
        maxRocketMiddleC = 0;
        maxRocketMiddleH = 0;
        maxRocketBottomC = 0;
        maxRocketBottomH = 0;
        maxCargoShipC = 0;
        maxCargoShipH = 0;
        maxCrossHubLine = 0;
        maxEndLevel1 = 0;
        maxEndLevel2 = 0;
        maxEndLevel3 = 0;
        maxEndNone = 0;
        maxDefense = 0;
    }

    public String getTeamNum(){
        return teamNum;
    }
    public void setKEY_TeamNum(String i){ teamNum= i;
    }

    public int getMinNoShow(){
        return minNoShow;
    }
    public void setMinNoShow(int i){
        minNoShow = i;
    }

    public int getMinHadAuto(){
        return minHadAuto;
    }
    public void setMinHadAuto(int i){
        minHadAuto = i;
    }

    public int getMinRedCard(){
        return minRedCard;
    }
    public void setMinRedCard(int i){
        minRedCard = i;
    }

    public int getMinYellowCard(){
        return minYellowCard;
    }
    public void setMinYellowCard(int i){
        minYellowCard = i;
    }

    public int getMinDisabled(){
        return minDisabled;
    }
    public void setMinDisabled(int i){
        minDisabled = i;
    }

    public int getMinFoul(){
        return minFoul;
    }
    public void setMinFoul(int i){
        minFoul = i;
    }

    public int getMinTechFoul(){
        return minTechFoul;
    }
    public void setMinTechFoul(int i){
        minTechFoul = i;
    }

    public int getMinStartLevel1() {
        return minStartLevel1;
    }
    public void setMinStartLevel1(int b){minStartLevel1 = b;}

    public int getMinStartLevel2() {
        return minStartLevel2;
    }
    public void setMinStartLevel2(int b){minStartLevel2 = b;}

    public int getMinPreloadCargo() {
        return minPreloadCargo;
    }
    public void setMinPreloadCargo(int b){minPreloadCargo = b;}

    public int getMinPreloadHatch() {
        return minPreloadHatch;
    }
    public void setMinPreloadHatch(int b){minPreloadHatch = b;}

    public int getMinRocketTopC() {
        return minRocketTopC;
    }
    public void setMinRocketTopC(int b){minRocketTopC= b;}

    public int getMinRocketTopH() {
        return minRocketTopH;
    }
    public void setMinRocketTopH(int b){minRocketTopH= b;}

    public int getMinRocketMiddleC() {
        return minRocketMiddleC;
    }
    public void setMinRocketMiddleC(int b){minRocketMiddleC= b;}

    public int getMinRocketMiddleH() {
        return minRocketMiddleH;
    }
    public void setMinRocketMiddleH(int b){minRocketMiddleH= b;}

    public int getMinRocketMBottomC() {
        return minRocketBottomC;
    }
    public void setMinRocketBottomC(int b){minRocketBottomC= b;}

    public int getMinRocketMBottomH() {
        return minRocketBottomH;
    }
    public void setMinRocketBottomH(int b){minRocketBottomH= b;}

    public int getMinCargoShipC() {
        return minCargoShipC;
    }
    public void setMinCargoShipC(int b){minCargoShipC= b;}

    public int getMinCargoShipH() {
        return minCargoShipH;
    }
    public void setMinCargoShipH(int b){minCargoShipH= b;}

    public int getMinCrossHubLine() {
        return minCrossHubLine;
    }
    public void setMinCrossHubLine(int b){minCrossHubLine= b;}

    public int getMinEndLevel1() {
        return minEndLevel1;
    }
    public void setMinEndLevel1(int b){minEndLevel1 = b;}

    public int getMinEndLevel2() {
        return minEndLevel2;
    }
    public void setMinEndLevel2(int b){minEndLevel2 = b;}

    public int getMinEndLevel3() {
        return minEndLevel3;
    }
    public void setMinEndLevel3(int b){minEndLevel3 = b;}

    public int getMinEndNone() {
        return minEndNone;
    }
    public void setMinEndNone(int b){minEndNone = b;}

    public int getMinDefense() {
        return minDefense;
    }
    public void setMinDefense(int b){minDefense = b;}



    public int getMaxNoShow(){
        return maxNoShow;
    }
    public void setMaxNoShow(int i){
        maxNoShow = i;
    }

    public int getMaxHadAuto(){
        return maxHadAuto;
    }
    public void setMaxHadAuto(int i){
        maxHadAuto = i;
    }

    public int getMaxRedCard(){
        return maxRedCard;
    }
    public void setMaxRedCard(int i){
        maxRedCard = i;
    }

    public int getMaxYellowCard(){
        return maxYellowCard;
    }
    public void setMaxYellowCard(int i){
        maxYellowCard = i;
    }

    public int getMaxDisabled(){
        return maxDisabled;
    }
    public void setMaxDisabled(int i){
        maxDisabled = i;
    }

    public int getMaxFoul(){
        return maxFoul;
    }
    public void setMaxFoul(int i){
        maxFoul = i;
    }

    public int getMaxTechFoul(){
        return maxTechFoul;
    }
    public void setMaxTechFoul(int i){
        maxTechFoul = i;
    }

    public int getMaxStartLevel1() {
        return maxStartLevel1;
    }
    public void setMaxStartLevel1(int b){maxStartLevel1 = b;}

    public int getMaxStartLevel2() {
        return maxStartLevel2;
    }
    public void setMaxStartLevel2(int b){maxStartLevel2 = b;}

    public int getMaxPreloadCargo() {
        return maxPreloadCargo;
    }
    public void setMaxPreloadCargo(int b){maxPreloadCargo = b;}

    public int getMaxPreloadHatch() {
        return maxPreloadHatch;
    }
    public void setMaxPreloadHatch(int b){maxPreloadHatch = b;}

    public int getMaxRocketTopC() {
        return maxRocketTopC;
    }
    public void setMaxRocketTopC(int b){maxRocketTopC= b;}

    public int getMaxRocketTopH() {
        return maxRocketTopH;
    }
    public void setMaxRocketTopH(int b){maxRocketTopH= b;}

    public int getMaxRocketMiddleC() {
        return maxRocketMiddleC;
    }
    public void setMaxRocketMiddleC(int b){maxRocketMiddleC= b;}

    public int getMaxRocketMiddleH() {
        return maxRocketMiddleH;
    }
    public void setMaxRocketMiddleH(int b){maxRocketMiddleH= b;}

    public int getMaxRocketBottomC() {
        return maxRocketBottomC;
    }
    public void setMaxRocketBottomC(int b){maxRocketBottomC= b;}

    public int getMaxRocketBottomH() {
        return maxRocketBottomH;
    }
    public void setMaxRocketBottomH(int b){maxRocketBottomH= b;}

    public int getMaxCargoShipC() {
        return maxCargoShipC;
    }
    public void setMaxCargoShipC(int b){maxCargoShipC= b;}

    public int getMaxCargoShipH() {
        return maxCargoShipH;
    }
    public void setMaxCargoShipH(int b){maxCargoShipH= b;}

    public int getMaxCrossHubLine() {
        return maxCrossHubLine;
    }
    public void setMaxCrossHubLine(int b){maxCrossHubLine= b;}

    public int getMaxEndLevel1() {
        return maxEndLevel1;
    }
    public void setMaxEndLevel1(int b){maxEndLevel1 = b;}

    public int getMaxEndLevel2() {
        return maxEndLevel2;
    }
    public void setMaxEndLevel2(int b){maxEndLevel2 = b;}

    public int getMaxEndLevel3() {
        return maxEndLevel3;
    }
    public void setMaxEndLevel3(int b){maxEndLevel3 = b;}

    public int getMaxEndNone() {
        return maxEndNone;
    }
    public void setMaxEndNone(int b){maxEndNone = b;}

    public int getMaxDefense() {
        return maxDefense;
    }
    public void setMaxDefense(int b){maxDefense = b;}

}
