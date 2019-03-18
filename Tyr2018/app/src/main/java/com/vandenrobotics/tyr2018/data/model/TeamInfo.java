package com.vandenrobotics.tyr2018.data.model;

/**
 * Created by Sarah Bergendahl on 2/24/2018.
 */

public class TeamInfo {

    public static final String TABLE = "TeamInfo";

    public static final String INDEX = "comp_match_team";

    public static final String KEY_TeamNum = "TeamNum";
    public static final String KEY_NumMatch = "NumMatch";
    public static final String KEY_AvgOff = "OffWS";
    public static final String KEY_AvgTotal = "TotalWS";
    public static final String KEY_AvgNeg = "NegWS";
    public static final String KEY_PNoShow = "PNoShow";
    public static final String KEY_PStartLevel = "PercentageStartLevel";
    public static final String KEY_PStartLevel2 = "PercentageStartLevel";
    public static final String KEY_PPreloadCargo = "PercentagePreloadCargo";
    public static final String KEY_PPreloadHatch = "PercentagePreloadHatch";
    public static final String KEY_PCrossHubLine = "PCrossHubLine";
    public static final String KEY_AvgRobotTopC = "AvgRocketTopC";
    public static final String KEY_AvgRobotTopH = "AvgRocketTopH";
    public static final String KEY_AvgRobotMiddleC = "AvgRocketMiddleC";
    public static final String KEY_AvgRobotMiddleH = "AvgRocketMiddleH";
    public static final String KEY_AvgRobotBottomC = "AvgRocketBottomC";
    public static final String KEY_AvgRobotBottomH = "AvgRocketBottomH";
    public static final String KEY_AvgCargoShipC = "AvgCargoShipC";
    public static final String KEY_AvgCargoShipH = "AvgCargoShipH";
    public static final String KEY_PEndLevel1 = "PercentageEndLevel1";
    public static final String KEY_PEndLevel2 = "PercentageEndLevel2";
    public static final String KEY_PEndLevel3 = "PercentageEndLevel3";
    public static final String KEY_PEndNone = "PercentageEndNone";
    public static final String KEY_PFoul = "PercentageFoul";
    public static final String KEY_PTechFoul = "PercentageTechFoul";
    public static final String KEY_PYellowCard = "PercentageYellowCard";
    public static final String KEY_PRedCard = "PercentageRedCard";
    public static final String KEY_PDisabled = "PercentageDisabled";
    public static final String KEY_MaxRocketTopC = "MaxRocketTopC";
    public static final String KEY_MinRocketTopC = "MinRocketTopC";
    public static final String KEY_MaxRocketTopH = "MaxRocketTopH";
    public static final String KEY_MinRocketTopH = "MinRocketTopH";
    public static final String KEY_MaxRocketMiddleC = "MaxRocketMiddleC";
    public static final String KEY_MinRocketMiddleC = "MinRocketMiddleC";
    public static final String KEY_MaxRocketMiddleH = "MaxRocketMiddleH";
    public static final String KEY_MinRocketMiddleH = "MinRocketMiddleH";
    public static final String KEY_MaxRocketBottomC = "MaxRocketBottomC";
    public static final String KEY_MinRocketBottomC = "MinRocketBottomC";
    public static final String KEY_MaxRocketBottomH = "MaxRocketBottomH";
    public static final String KEY_MinRocketBottomH = "MinRocketBottomH";
    public static final String KEY_MaxCargoShipC = "MaxCargoShipC";
    public static final String KEY_MinCargoShipC = "MinCargoShipC";
    public static final String KEY_MaxCargoShipH = "MaxCargoShipH";
    public static final String KEY_MinCargoShipH = "MinCargoShipH";


    private int TeamNum;
    private double numMatch;
    private double pNoShow;
    private double pTechFoul;
    private double pFoul;
    private double pYellowCard;
    private double pRedCard;
    private double pDisabled;
    private double negWS;
    private double defensiveWS;
    private double offensiveWS;
    private double totalWS;
    private double pStartLevel1;
    private double pStartLevel2;
    private double pPreloadCargo;
    private double pPreloadHatch;
    private double pCrossHubLine;
    private double avgRocketTopC;
    private double avgRocketTopH;
    private double avgRocketMiddleC;
    private double avgRocketMiddleH;
    private double avgRocketBottomC;
    private double avgRocketBottomH;
    private double avgCargoShipC;
    private double avgCargoShipH;
    private double pEndLevel1;
    private double pEndLevel2;
    private double pEndLevel3;
    private double pEndNone;
    private double maxRocketTopC;
    private double minRocketTopC;
    private double maxRocketTopH;
    private double minRocketTopH;
    private double maxRocketMiddleC;
    private double minRocketMiddleC;
    private double maxRocketMiddleH;
    private double minRocketMiddleH;
    private double maxRocketBottomC;
    private double minRocketBottomC;
    private double maxRocketBottomH;
    private double minRocketBottomH;
    private double maxCargoShipC;
    private double minCargoShipC;
    private double maxCargoShipH;
    private double minCargoShipH;


    public TeamInfo(){
        TeamNum = 0;
        numMatch = 0;
        pNoShow = 0.0;
        pTechFoul = 0;
        pFoul = 0;
        pYellowCard = 0;
        pRedCard = 0;
        pDisabled = 0;
        negWS = 0;
        defensiveWS = 0;
        offensiveWS = 0;
        totalWS = 0;
        pStartLevel1 = 0;
        pStartLevel2 = 0;
        pPreloadCargo = 0;
        pPreloadHatch = 0;
        pCrossHubLine = 0;
        avgRocketTopC = 0;
        avgRocketTopH = 0;
        avgRocketMiddleC = 0;
        avgRocketMiddleH = 0;
        avgRocketBottomC = 0;
        avgRocketBottomH = 0;
        avgCargoShipC = 0;
        avgCargoShipH = 0;
        pEndLevel1 = 0;
        pEndLevel2 = 0;
        pEndLevel3 = 0;
        pEndNone = 0;
        maxRocketTopC = 0;
        minRocketTopC = 0;
        maxRocketTopH = 0;
        minRocketTopH = 0;
        maxRocketMiddleC = 0;
        minRocketMiddleC = 0;
        maxRocketMiddleH = 0;
        minRocketMiddleH = 0;
        maxRocketBottomC = 0;
        minRocketBottomC = 0;
        maxRocketBottomH = 0;
        minRocketBottomH = 0;
        maxCargoShipC = 0;
        minCargoShipC = 0;
        maxCargoShipH = 0;
        minCargoShipH = 0;

    }

    public double getNumMatch(){
        return numMatch;
    }
    public void setNumMatch(double i){
        numMatch = i;
    }

    public double getPNoShow(){
        return pNoShow;
    }
    public void setPNoShow(double i){
        pNoShow = i;
    }

    public double getTotalWS(){return totalWS;}
    public void setTotalWS(double i){ totalWS = i;}

    public double getOffensiveWS(){return offensiveWS;}
    public void setOffensiveWS(double i){ offensiveWS = i;}

    public double getDefensiveWS(){return defensiveWS;}
    public void setDefensiveWS(double i){ defensiveWS = i;}

    public double getNegWS(){return negWS;}
    public void setNegWS(double i){ negWS = i;}

    public int getTeamNum(){
        return TeamNum;
    }
    public void setTeamNum(int i){
        TeamNum = i;
    }

    public double getPTechFoul(){
        return pTechFoul;
    }
    public void setPTechFoul(double i){pTechFoul= i;}

    public double getPFoul(){
        return pFoul;
    }
    public void setPFoul(double i){pFoul= i;}

    public double getPYellowCard(){
        return pYellowCard;
    }
    public void setPYellowCard(double i){pYellowCard= i;}

    public double getPRedCard(){
        return pRedCard;
    }
    public void setPRedCard(double i){pRedCard= i;}

    public double getPDisabled(){
        return pDisabled;
    }
    public void setPDisabled(double i){pDisabled= i;}

    public double getpStartLevel1 (){ return pStartLevel1;}
    public void setpStartLevel1 (double i){ pStartLevel1 = i;}

    public double getpStartLevel2 (){ return pStartLevel2;}
    public void setpStartLevel2 (double i){ pStartLevel2= i;}

    public double getpPreloadCargo (){ return pPreloadCargo;}
    public void setpPreloadCargo (double i){pPreloadCargo = i;}

    public double getpPreloadHatch (){ return pPreloadHatch;}
    public void setpPreloadHatch (double i){pPreloadHatch = i;}

    public double getpCrossHubLine (){ return pCrossHubLine;}
    public void setpCrossHubLine (double i){pCrossHubLine = i;}

    public double getAvgRocketTopC (){ return avgRocketTopC;}
    public void setAvgRocketTopC (double i){avgRocketTopC = i;}

    public double getAvgRocketTopH (){ return avgRocketTopH;}
    public void setAvgRocketTopH (double i){avgRocketTopH = i;}

    public double getAvgRocketMiddleC (){ return avgRocketMiddleC;}
    public void setAvgRocketMiddleC (double i){avgRocketMiddleC = i;}

    public double getAvgRocketMiddleH (){ return avgRocketMiddleH;}
    public void setAvgRocketMiddleH (double i){avgRocketMiddleH = i;}

    public double getAvgRocketBottomC (){ return avgRocketBottomC;}
    public void setAvgRobotBottomC (double i){avgRocketBottomC = i;}

    public double getAvgRocketBottomH (){ return avgRocketBottomH;}
    public void setAvgRocketBottomH (double i){avgRocketBottomH = i;}

    public double getAvgCargoShipC (){ return avgCargoShipC;}
    public void setAvgCargoShipC (double i){avgCargoShipC = i;}

    public double getAvgCargoShipH (){ return avgCargoShipH;}
    public void setAvgCargoShipH (double i){avgCargoShipH = i;}

    public double getpEndLevel1 (){ return pEndLevel1;}
    public void setpEndLevel1 (double i){pEndLevel1 = i;}

    public double getpEndLevel2 (){ return pEndLevel2;}
    public void setpEndLevel2 (double i){pEndLevel2 = i;}

    public double getpEndLevel3 (){ return pEndLevel3;}
    public void setpEndLevel3 (double i){pEndLevel3 = i;}

    public double getpEndNone (){ return pEndNone;}
    public void setpEndNone (double i){pEndNone = i;}

    public double getMaxRocketTopC (){ return maxRocketTopC;}
    public void setMaxRocketTopC (double i){maxRocketTopC = i;}

    public double getMinRocketTopC (){ return minRocketTopC;}
    public void setMinRocketTopC (double i){minRocketTopC = i;}

    public double getMaxRocketTopH (){ return maxRocketTopH;}
    public void setMaxRocketTopH (double i){maxRocketTopH = i;}

    public double getMinRocketTopH (){ return minRocketTopH;}
    public void setMinRocketTopH (double i){minRocketTopH = i;}

    public double getMaxRocketMiddleC (){ return maxRocketMiddleC;}
    public void setMaxRocketMiddleC (double i){maxRocketMiddleC = i;}

    public double getMinRocketMiddleC (){ return minRocketMiddleC;}
    public void setMinRocketMiddleC (double i){minRocketMiddleC = i;}

    public double getMaxRocketMiddleH (){ return maxRocketMiddleH;}
    public void setMaxRocketMiddleH (double i){maxRocketMiddleH = i;}

    public double getMinRocketMiddleH (){ return minRocketMiddleH;}
    public void setMinRocketMiddleH (double i){minRocketMiddleH = i;}

    public double getMaxRocketBottomC (){ return maxRocketBottomC;}
    public void setMaxRocketBottomC (double i){maxRocketBottomC = i;}

    public double getMinRocketBottomC (){ return minRocketBottomC;}
    public void setMinRocketBottomC (double i){minRocketBottomC = i;}

    public double getMaxRocketBottomH (){ return maxRocketBottomH;}
    public void setMaxRocketBottomH (double i){maxRocketBottomH = i;}

    public double getMinRocketBottomH (){ return minRocketBottomH;}
    public void setMinRocketBottomH (double i){minRocketBottomH = i;}

    public double getMaxCargoShipC (){ return maxCargoShipC;}
    public void setMaxCargoShipC (double i){maxCargoShipC = i;}

    public double getMinCargoShipC (){ return minCargoShipC;}
    public void setMinCargoShipC (double i){minCargoShipC = i;}

    public double getMaxCargoShipH (){ return maxCargoShipH;}
    public void setMaxCargoShipH (double i){maxCargoShipH = i;}

    public double getMinCargoShipH (){ return minCargoShipH;}
    public void setMinCargoShipH (double i){minCargoShipH = i;}

}

