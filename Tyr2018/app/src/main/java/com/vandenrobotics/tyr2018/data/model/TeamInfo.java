package com.vandenrobotics.tyr2018.data.model;

/**
 * Created by Sarah Bergendahl on 2/24/2018.
 */

public class TeamInfo {

    public static final String TABLE = "TeamInfo";

    public static final String INDEX = "comp_match_team";

    public static final String KEY_TeamNum = "TeamNum";
    public static final String KEY_NumMatch = "NumMatch";
    public static final String KEY_PNoShow = "PNoShow";
    public static final String KEY_AvgOff = "OffWS";
    public static final String KEY_AvgDef = "DefWS";
    public static final String KEY_AvgTotal = "TotalWS";
    public static final String KEY_AvgNeg = "NegWS";
    public static final String KEY_AvgAuto = "AutoWS";
    public static final String KEY_PHadAuto = "PHadAuto";
    public static final String KEY_PCrossAutoLine = "PCrossAutoLine";
    public static final String KEY_PAutoPickedUpCube = "PAutoPickedUpCube";
    public static final String KEY_AvgAutoCubesInSw1 = "AvgAutoCubesInSw1";
    public static final String KEY_AvgAutoCubesInSw2 = "AvgAutoCubesInSw2";
    public static final String KEY_AvgAutoCubesInScl = "AvgAutoCubesInScl";
    public static final String KEY_AvgAutoCubesInEx = "AvgAutoCubesInEx";
    public static final String KEY_AvgAutoOwnGainSw1 = "AvgAutoOwnGainSw1";
    public static final String KEY_AvgAutoOwnedSw1 = "AvgAutoOwnedSw1";
    public static final String KEY_AvgAutoOwnGainSw2 = "AvgAutoOwnGainSw2";
    public static final String KEY_AvgAutoOwnedSw2 = "AvgAutoOwnedSw2";
    public static final String KEY_AvgAutoOwnGainScl = "AvgAutoOwnGainScl";
    public static final String KEY_AvgAutoOwnedScl = "AvgAutoOwnedScl";
    public static final String KEY_AvgAutoNumOwnChangesSw1 = "AvgAutoNumOwnChangesSw1";
    public static final String KEY_AvgAutoNumOwnChangesSw2 = "AvgAutoNumOwnChangesSw2";
    public static final String KEY_AvgAutoNumOwnChangesScl = "AvgAutoNumOwnChangesScl";
    public static final String KEY_AvgTele = "TeleWS";
    public static final String KEY_PTelePickedUpCube = "AvgTelePickedUpCube";
    public static final String KEY_AvgTeleCubesInSw1 = "AvgTeleCubesInSw1";
    public static final String KEY_AvgTeleCubesInSw2 = "AvgTeleCubesInSw2";
    public static final String KEY_AvgTeleCubesInScl = "AvgTeleCubesInScl";
    public static final String KEY_AvgTeleCubesInEx = "AvgTeleCubesInEx";
    public static final String KEY_AvgTeleOwnGainSw1 = "AvgTeleOwnGainSw1";
    public static final String KEY_AvgTeleOwnedSw1 = "AvgTeleOwnedSw1";
    public static final String KEY_AvgTeleOwnGainSw2 = "AvgTeleOwnGainSw2";
    public static final String KEY_AvgTeleOwnedSw2 = "AvgTeleOwnedSw2";
    public static final String KEY_AvgTeleOwnGainScl = "AvgTeleOwnGainScl";
    public static final String KEY_AvgTeleOwnedScl = "AvgTeleOwnedScl";
    public static final String KEY_AvgTeleNumOwnChangesSw1 = "AvgTeleNumOwnChangesSw1";
    public static final String KEY_AvgTeleNumOwnChangesSw2 = "AvgTeleNumOwnChangesSw2";
    public static final String KEY_AvgTeleNumOwnChangesScl = "AvgTeleNumOwnChangesScl";
    public static final String KEY_PClimb = "PClimb";
    public static final String KEY_PClimbAssist = "PClimbAssist";
    public static final String KEY_PParking = "PParking";
    public static final String KEY_PHumanPlayer = "PHumanPlayer";
    public static final String KEY_PTechFoul = "PTechFoul";
    public static final String KEY_PFoul = "PFoul";
    public static final String KEY_PYellowCard = "PYellowCard";
    public static final String KEY_PRedCard = "PRedCard";
    public static final String KEY_PDisabled = "PDisabled";
    public static final String KEY_AvgNumCubesInVault = "AvgNumCubesInVault";
    public static final String KEY_AvgActiveFceTime = "AvgActiveFceTime";
    public static final String KEY_AvgActiveLevTime = "AvgActiveLevTime";
    public static final String KEY_AvgActiveBstTime = "AvgActiveBstTime";
    public static final String KEY_AvgCubesAtActiveFce = "AvgCubesAtActiveFce";
    public static final String KEY_AvgCubesAtActiveLev = "AvgCubesAtActiveLev";
    public static final String KEY_AvgCubesAtActiveBst = "AvgCubesAtActiveBst";
    public static final String KEY_MaxAutoNumOwnChangesSw1 = "MaxAutoNumOwnChangesSw1";
    public static final String KEY_MinAutoNumOwnChangesSw1 = "MinAutoNumOwnChangesSw1";
    public static final String KEY_MaxAutoNumOwnChangesSw2 = "MaxAutoNumOwnChangesSw2";
    public static final String KEY_MinAutoNumOwnChangesSw2 = "MinAutoNumOwnChangesSw2";
    public static final String KEY_MaxAutoNumOwnChangesScl = "MaxAutoNumOwnChangesScl";
    public static final String KEY_MinAutoNumOwnChangesScl = "MinAutoNumOwnChangesScl";
    public static final String KEY_MaxAutoCubesInSw1 = "MaxAutoCubesInSw1";
    public static final String KEY_MinAutoCubesInSw1 = "MinAutoCubesInSw1";
    public static final String KEY_MaxAutoCubesInSw2 = "MaxAutoCubesInSw2";
    public static final String KEY_MinAutoCubesInSw2 = "MinAutoCubesInSw2";
    public static final String KEY_MaxAutoCubesInScl = "MaxAutoCubesInScl";
    public static final String KEY_MinAutoCubesInScl = "MinAutoCubesInScl";
    public static final String KEY_MaxAutoCubesInEx = "MaxAutoCubesInEx";
    public static final String KEY_MinAutoCubesInEx = "MinAutoCubesInEx";
    public static final String KEY_MaxAutoOwnGainSw1 = "MaxAutoOwnGainSw1";
    public static final String KEY_MinAutoOwnGainSw1 = "MinAutoOwnGainSw1";
    public static final String KEY_MaxAutoOwnedSw1 = "MaxAutoOwnedSw1";
    public static final String KEY_MinAutoOwnedSw1 = "MinAutoOwnedSw1";
    public static final String KEY_MaxAutoOwnGainSw2 = "MaxAutoOwnGainSw2";
    public static final String KEY_MinAutoOwnGainSw2 = "MinAutoOwnGainSw2";
    public static final String KEY_MaxAutoOwnedSw2 = "MaxAutoOwnedSw2";
    public static final String KEY_MinAutoOwnedSw2 = "MinAutoOwnedSw2";
    public static final String KEY_MaxAutoOwnGainScl = "MaxAutoOwnGainScl";
    public static final String KEY_MinAutoOwnGainScl = "MinAutoOwnGainScl";
    public static final String KEY_MaxAutoOwnedScl = "MaxAutoOwnedScl";
    public static final String KEY_MinAutoOwnedScl = "MinAutoOwnedScl";
    public static final String KEY_MaxTeleNumOwnChangesSw1 = "MaxTeleNumOwnChangesSw1";
    public static final String KEY_MinTeleNumOwnChangesSw1 = "MinTeleNumOwnChangesSw1";
    public static final String KEY_MaxTeleNumOwnChangesSw2 = "MaxTeleNumOwnChangesSw2";
    public static final String KEY_MinTeleNumOwnChangesSw2 = "MinTeleNumOwnChangesSw2";
    public static final String KEY_MaxTeleNumOwnChangesScl = "MaxTeleNumOwnChangesScl";
    public static final String KEY_MinTeleNumOwnChangesScl = "MinTeleNumOwnChangesScl";
    public static final String KEY_MaxTeleCubesInSw1 = "MaxTeleCubesInSw1";
    public static final String KEY_MinTeleCubesInSw1 = "MinTeleCubesInSw1";
    public static final String KEY_MaxTeleCubesInSw2 = "MaxTeleCubesInSw2";
    public static final String KEY_MinTeleCubesInSw2 = "MinTeleCubesInSw2";
    public static final String KEY_MaxTeleCubesInScl = "MaxTeleCubesInScl";
    public static final String KEY_MinTeleCubesInScl = "MinTeleCubesInScl";
    public static final String KEY_MaxTeleCubesInEx = "MaxTeleCubesInEx";
    public static final String KEY_MinTeleCubesInEx = "MinTeleCubesInEx";
    public static final String KEY_MaxTeleOwnGainSw1 = "MaxTeleOwnGainSw1";
    public static final String KEY_MinTeleOwnGainSw1 = "MinTeleOwnGainSw1";
    public static final String KEY_MaxTeleOwnedSw1 = "MaxTeleOwnedSw1";
    public static final String KEY_MinTeleOwnedSw1 = "MinTeleOwnedSw1";
    public static final String KEY_MaxTeleOwnGainSw2 = "MaxTeleOwnGainSw2";
    public static final String KEY_MinTeleOwnGainSw2 = "MinTeleOwnGainSw2";
    public static final String KEY_MaxTeleOwnedSw2 = "MaxTeleOwnedSw2";
    public static final String KEY_MinTeleOwnedSw2 = "MinTeleOwnedSw2";
    public static final String KEY_MaxTeleOwnGainScl = "MaxTeleOwnGainScl";
    public static final String KEY_MinTeleOwnGainScl = "MinTeleOwnGainScl";
    public static final String KEY_MaxTeleOwnedScl = "MaxTeleOwnedScl";
    public static final String KEY_MinTeleOwnedScl = "MinTeleOwnedScl";
    public static final String KEY_MaxNumCubesInVault = "MaxNumCubesInVault";
    public static final String KEY_MinNumCubesInVault = "MinNumCubesInVault";
    public static final String KEY_MaxActiveFceTime = "MaxActiveFceTime";
    public static final String KEY_MinActiveFceTime = "MinActiveFceTime";
    public static final String KEY_MaxActiveLevTime = "MaxActiveLevTime";
    public static final String KEY_MinActiveLevTime = "MinActiveLevTime";
    public static final String KEY_MaxActiveBstTime = "MaxActiveBstTime";
    public static final String KEY_MinActiveBstTime = "MinActiveBstTime";
    public static final String KEY_MaxCubesAtActiveFce = "MaxCubesAtActiveFce";
    public static final String KEY_MinCubesAtActiveFce = "MinCubesAtActiveFce";
    public static final String KEY_MaxCubesAtActiveLev = "MaxCubesAtActiveLev";
    public static final String KEY_MinCubesAtActiveLev = "MinCubesAtActiveLev";
    public static final String KEY_MaxCubesAtActiveBst = "MaxCubesAtActiveBst";
    public static final String KEY_MinCubesAtActiveBst = "MinCubesAtActiveBst";
    public static final String KEY_HadFoul = "PHadFoul";


    private int TeamNum;
    private double numMatch;
    private double pNoShow;
    private double pHadAuto;
    private double pCrossAutoLine;
    private double pAutoPickedUpCube;
    private double avgAutoCubesInSw1;
    private double avgAutoCubesInSw2;
    private double avgAutoCubesInScl;
    private double avgAutoCubesInEx;
    private double avgAutoOwnGainSw1;
    private double avgAutoOwnedSw1;
    private double avgAutoOwnGainSw2;
    private double avgAutoOwnedSw2;
    private double avgAutoOwnGainScl;
    private double avgAutoOwnedScl;
    private double avgAutoNumOwnChangesSw1;
    private double avgAutoNumOwnChangesSw2;
    private double avgAutoNumOwnChangesScl;
    private double pTelePickedUpCube;
    private double avgTeleCubesInSw1;
    private double avgTeleCubesInSw2;
    private double avgTeleCubesInScl;
    private double avgTeleCubesInEx;
    private double avgTeleOwnGainSw1;
    private double avgTeleOwnedSw1;
    private double avgTeleOwnGainSw2;
    private double avgTeleOwnedSw2;
    private double avgTeleOwnGainScl;
    private double avgTeleOwnedScl;
    private double avgTeleNumOwnChangesSw1;
    private double avgTeleNumOwnChangesSw2;
    private double avgTeleNumOwnChangesScl;
    private double pClimb;
    private double pClimbAssist;
    private double pParking;
    private double pHumanPlayer;
    private double pTechFoul;
    private double pFoul;
    private double pYellowCard;
    private double pRedCard;
    private double pDisabled;
    private double avgNumCubesInVault;
    private double avgActiveFceTime;
    private double avgActiveLevTime;
    private double avgActiveBstTime;
    private double avgCubesAtActiveFce;
    private double avgCubesAtActiveLev;
    private double avgCubesAtActiveBst;
    private double teleWS;
    private double negWS;
    private double autoWS;
    private double defensiveWS;
    private double offensiveWS;
    private double totalWS;
    private int maxAutoNumOwnChangesSw1;
    private int minAutoNumOwnChangesSw1;
    private int maxAutoNumOwnChangesSw2;
    private int minAutoNumOwnChangesSw2;
    private int maxAutoNumOwnChangesScl;
    private int minAutoNumOwnChangesScl;
    private int maxAutoCubesInSw1;
    private int minAutoCubesInSw1;
    private int maxAutoCubesInSw2;
    private int minAutoCubesInSw2;
    private int maxAutoCubesInScl;
    private int minAutoCubesInScl;
    private int maxAutoCubesInEx;
    private int minAutoCubesInEx;
    private int maxAutoOwnGainSw1;
    private int minAutoOwnGainSw1;
    private int maxAutoOwnedSw1;
    private int minAutoOwnedSw1;
    private int maxAutoOwnGainSw2;
    private int minAutoOwnGainSw2;
    private int maxAutoOwnedSw2;
    private int minAutoOwnedSw2;
    private int maxAutoOwnGainScl;
    private int minAutoOwnGainScl;
    private int maxAutoOwnedScl;
    private int minAutoOwnedScl;
    private int maxTeleNumOwnChangesSw1;
    private int minTeleNumOwnChangesSw1;
    private int maxTeleNumOwnChangesSw2;
    private int minTeleNumOwnChangesSw2;
    private int maxTeleNumOwnChangesScl;
    private int minTeleNumOwnChangesScl;
    private int maxTeleCubesInSw1;
    private int minTeleCubesInSw1;
    private int maxTeleCubesInSw2;
    private int minTeleCubesInSw2;
    private int maxTeleCubesInScl;
    private int minTeleCubesInScl;
    private int maxTeleCubesInEx;
    private int minTeleCubesInEx;
    private int maxTeleOwnGainSw1;
    private int minTeleOwnGainSw1;
    private int maxTeleOwnedSw1;
    private int minTeleOwnedSw1;
    private int maxTeleOwnGainSw2;
    private int minTeleOwnGainSw2;
    private int maxTeleOwnedSw2;
    private int minTeleOwnedSw2;
    private int maxTeleOwnGainScl;
    private int minTeleOwnGainScl;
    private int maxTeleOwnedScl;
    private int minTeleOwnedScl;
    private int maxNumCubesInVault;
    private int minNumCubesInVault;
    private int maxActiveFceTime;
    private int minActiveFceTime;
    private int maxActiveLevTime;
    private int minActiveLevTime;
    private int maxActiveBstTime;
    private int minActiveBstTime;
    private int maxCubesAtActiveFce;
    private int minCubesAtActiveFce;
    private int maxCubesAtActiveLev;
    private int minCubesAtActiveLev;
    private int maxCubesAtActiveBst;
    private int minCubesAtActiveBst;
    private double hadFoul;


    public TeamInfo(){
        TeamNum = 0;
        numMatch = 0;
        pNoShow = 0.0;
        pHadAuto = 0;
        pCrossAutoLine = 0;
        pAutoPickedUpCube = 0;
        avgAutoCubesInSw1 = 0;
        avgAutoCubesInSw2 = 0;
        avgAutoCubesInScl = 0;
        avgAutoCubesInEx = 0;
        avgAutoOwnGainSw1 = 0;
        avgAutoOwnedSw1 = 0;
        avgAutoOwnGainSw2 = 0;
        avgAutoOwnedSw2 = 0;
        avgAutoOwnGainScl = 0;
        avgAutoOwnedScl = 0;
        avgAutoNumOwnChangesSw1 = 0;
        avgAutoNumOwnChangesSw2 = 0;
        avgAutoNumOwnChangesScl = 0;
        pTelePickedUpCube = 0;
        avgTeleCubesInSw1 = 0;
        avgTeleCubesInSw2 = 0;
        avgTeleCubesInScl = 0;
        avgTeleCubesInEx = 0;
        avgTeleOwnGainSw1 = 0;
        avgTeleOwnedSw1 = 0;
        avgTeleOwnGainSw2 = 0;
        avgTeleOwnedSw2 = 0;
        avgTeleOwnGainScl = 0;
        avgTeleOwnedScl = 0;
        avgTeleNumOwnChangesSw1 = 0;
        avgTeleNumOwnChangesSw2 = 0;
        avgTeleNumOwnChangesScl = 0;
        pClimb = 0;
        pClimbAssist = 0;
        pParking = 0;
        pHumanPlayer = 0;
        pTechFoul = 0;
        pFoul = 0;
        pYellowCard = 0;
        pRedCard = 0;
        pDisabled = 0;
        avgNumCubesInVault = 0;
        avgActiveFceTime = 0;
        avgActiveLevTime = 0;
        avgActiveBstTime = 0;
        avgCubesAtActiveFce = 0;
        avgCubesAtActiveLev = 0;
        avgCubesAtActiveBst = 0;
        teleWS = 0;
        negWS = 0;
        autoWS = 0;
        defensiveWS = 0;
        offensiveWS = 0;
        totalWS = 0;
        minAutoNumOwnChangesSw1 = 0;
        maxAutoNumOwnChangesSw2 = 0;
        minAutoNumOwnChangesSw2 = 0;
        maxAutoNumOwnChangesScl = 0;
        minAutoNumOwnChangesScl = 0;
        maxAutoCubesInSw1 =  0;
        minAutoCubesInSw1 = 0;
        maxAutoCubesInSw2 = 0;
        minAutoCubesInSw2 = 0;
        maxAutoCubesInScl = 0;
        minAutoCubesInScl = 0;
        maxAutoCubesInEx = 0;
        minAutoCubesInEx = 0;
        maxAutoOwnGainSw1 = 0;
        minAutoOwnGainSw1 = 0;
        maxAutoOwnedSw1 = 0;
        minAutoOwnedSw1 = 0;
        maxAutoOwnGainSw2 = 0;
        minAutoOwnGainSw2 = 0;
        maxAutoOwnedSw2 = 0;
        minAutoOwnedSw2 = 0;
        maxAutoOwnGainScl = 0;
        minAutoOwnGainScl = 0;
        maxAutoOwnedScl = 0;
        minAutoOwnedScl = 0;
        maxTeleNumOwnChangesSw1 = 0;
        minTeleNumOwnChangesSw1 = 0;
        maxTeleNumOwnChangesSw2 = 0;
        minTeleNumOwnChangesSw2 = 0;
        maxTeleNumOwnChangesScl = 0;
        minTeleNumOwnChangesScl = 0;
        maxTeleCubesInSw1 = 0;
        minTeleCubesInSw1 = 0;
        maxTeleCubesInSw2 = 0;
        minTeleCubesInSw2 = 0;
        maxTeleCubesInScl = 0;
        minTeleCubesInScl = 0;
        maxTeleCubesInEx = 0;
        minTeleCubesInEx = 0;
        maxTeleOwnGainSw1 = 0;
        minTeleOwnGainSw1 = 0;
        maxTeleOwnedSw1 = 0;
        minTeleOwnedSw1 = 0;
        maxTeleOwnGainSw2 = 0;
        minTeleOwnGainSw2 = 0;
        maxTeleOwnedSw2 = 0;
        maxTeleOwnedSw2 = 0;
        maxTeleOwnGainScl = 0;
        minTeleOwnGainScl = 0;
        maxTeleOwnedScl = 0;
        minTeleOwnedScl = 0;
        maxNumCubesInVault = 0;
        minNumCubesInVault = 0;
        maxActiveFceTime = 0;
        minActiveFceTime = 0;
        maxActiveLevTime = 0;
        minActiveLevTime = 0;
        maxActiveBstTime = 0;
        minActiveBstTime = 0;
        maxCubesAtActiveFce = 0;
        minCubesAtActiveFce = 0;
        maxCubesAtActiveLev = 0;
        minCubesAtActiveLev = 0;
        maxCubesAtActiveBst = 0;
        minCubesAtActiveBst = 0;

        hadFoul = 0;
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

    public double getHadFoul(){return hadFoul;}
    public void setHadFoul(double i){ hadFoul = i;}

    public double getDefensiveWS(){return defensiveWS;}
    public void setDefensiveWS(double i){ defensiveWS = i;}

    public double getNegWS(){return negWS;}
    public void setNegWS(double i){ negWS = i;}

    public double getAutoWS(){return autoWS;}
    public void setAutoWS(double i){ autoWS = i;}

    public int getTeamNum(){
        return TeamNum;
    }
    public void setTeamNum(int i){
        TeamNum = i;
    }

    public double getPHadAuto(){
        return pHadAuto;
    }
    public void setPHadAuto(double i){pHadAuto= i;}

    public double getPCrossAutoLine(){
        return pCrossAutoLine;
    }
    public void setPCrossAutoLine(double i){
        pCrossAutoLine = i;
    }

    public double getPAutoPickedUpCube(){
        return pAutoPickedUpCube;
    }
    public void setPAutoPickedUpCube(double i){pAutoPickedUpCube= i;}

    public double getAvgAutoCubesInSw1(){
        return avgAutoCubesInSw1;
    }
    public void setAvgAutoCubesInSw1(double i){avgAutoCubesInSw1= i;}

    public double getAvgAutoCubesInSw2(){
        return avgAutoCubesInSw2;
    }
    public void setAvgAutoCubesInSw2(double i){avgAutoCubesInSw2= i;}

    public double getAvgAutoCubesInScl(){
        return avgAutoCubesInScl;
    }
    public void setAvgAutoCubesInScl(double i){avgAutoCubesInScl= i;}

    public double getAvgAutoCubesInEx(){
        return avgAutoCubesInEx;
    }
    public void setAvgAutoCubesInEx(double i){avgAutoCubesInEx= i;}

    public double getAvgAutoOwnGainSw1(){
        return avgAutoOwnGainSw1;
    }
    public void setAvgAutoOwnGainSw1(double i){avgAutoOwnGainSw1= i;}

    public double getAvgAutoOwnedSw1(){
        return avgAutoOwnedSw1;
    }
    public void setAvgAutoOwnedSw1(double i){avgAutoOwnedSw1= i;}

    public double getAvgAutoOwnGainSw2(){
        return avgAutoOwnGainSw2;
    }
    public void setAvgAutoOwnGainSw2(double i){avgAutoOwnGainSw2= i;}

    public double getAvgAutoOwnedSw2(){
        return avgAutoOwnedSw2;
    }
    public void setAvgAutoOwnedSw2(double i){avgAutoOwnedSw2= i;}

    public double getAvgAutoOwnGainScl(){
        return avgAutoOwnGainScl;
    }
    public void setAvgAutoOwnGainScl(double i){avgAutoOwnGainScl= i;}

    public double getAvgAutoOwnedScl(){
        return avgAutoOwnedScl;
    }
    public void setAvgAutoOwnedScl(double i){avgAutoOwnedScl= i;}

    public double getAvgAutoNumOwnChangesSw1(){
        return avgAutoNumOwnChangesSw1;
    }
    public void setAvgAutoNumOwnChangesSw1(double i){avgAutoNumOwnChangesSw1= i;}

    public double getAvgAutoNumOwnChangesSw2(){
        return avgAutoNumOwnChangesSw2;
    }
    public void setAvgAutoNumOwnChangesSw2(double i){avgAutoNumOwnChangesSw2= i;}

    public double getAvgAutoNumOwnChangesScl(){
        return avgAutoNumOwnChangesScl;
    }
    public void setAvgAutoNumOwnChangesScl(double i){avgAutoNumOwnChangesScl= i;}

    public double getTeleWS(){return teleWS;}
    public void setTeleWS(double i){ teleWS = i;}

    public double getPTelePickedUpCube(){
        return pTelePickedUpCube;
    }
    public void setPTelePickedUpCube(double i){pTelePickedUpCube= i;}

    public double getAvgTeleCubesInSw1(){
        return avgTeleCubesInSw1;
    }
    public void setAvgTeleCubesInSw1(double i){avgTeleCubesInSw1= i;}

    public double getAvgTeleCubesInSw2(){
        return avgTeleCubesInSw2;
    }
    public void setAvgTeleCubesInSw2(double i){avgTeleCubesInSw2= i;}

    public double getAvgTeleCubesInScl(){
        return avgTeleCubesInScl;
    }
    public void setAvgTeleCubesInScl(double i){avgTeleCubesInScl= i;}

    public double getAvgTeleCubesInEx(){
        return avgTeleCubesInEx;
    }
    public void setAvgTeleCubesInEx(double i){avgTeleCubesInEx= i;}

    public double getAvgTeleOwnGainSw1(){
        return avgTeleOwnGainSw1;
    }
    public void setAvgTeleOwnGainSw1(double i){avgTeleOwnGainSw1= i;}

    public double getAvgTeleOwnedSw1(){
        return avgTeleOwnedSw1;
    }
    public void setAvgTeleOwnedSw1(double i){avgTeleOwnedSw1= i;}

    public double getAvgTeleOwnGainSw2(){
        return avgTeleOwnGainSw2;
    }
    public void setAvgTeleOwnGainSw2(double i){avgTeleOwnGainSw2= i;}

    public double getAvgTeleOwnedSw2(){
        return avgTeleOwnedSw2;
    }
    public void setAvgTeleOwnedSw2(double i){avgTeleOwnedSw2= i;}

    public double getAvgTeleOwnGainScl(){
        return avgTeleOwnGainScl;
    }
    public void setAvgTeleOwnGainScl(double i){avgTeleOwnGainScl= i;}

    public double getAvgTeleOwnedScl(){
        return avgTeleOwnedScl;
    }
    public void setAvgTeleOwnedScl(double i){avgTeleOwnedScl= i;}

    public double getAvgTeleNumOwnChangesSw1(){
        return avgTeleNumOwnChangesSw1;
    }
    public void setAvgTeleNumOwnChangesSw1(double i){avgTeleNumOwnChangesSw1= i;}

    public double getAvgTeleNumOwnChangesSw2(){
        return avgTeleNumOwnChangesSw2;
    }
    public void setAvgTeleNumOwnChangesSw2(double i){avgTeleNumOwnChangesSw2= i;}

    public double getAvgTeleNumOwnChangesScl(){
        return avgTeleNumOwnChangesScl;
    }
    public void setAvgTeleNumOwnChangesScl(double i){avgTeleNumOwnChangesScl= i;}

    public double getPClimb(){
        return pClimb;
    }
    public void setPClimb(double i){pClimb= i;}

    public double getPClimbAssist(){
        return pClimbAssist;
    }
    public void setPClimbAssist(double i){pClimbAssist= i;}

    public double getPParking(){
        return pParking;
    }
    public void setPParking(double i){pParking= i;}

    public double getPHumanPlayer(){
        return pHumanPlayer;
    }
    public void setPHumanPlayer(double i){pHumanPlayer= i;}

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

    public double getAvgNumCubesInVault(){
        return avgNumCubesInVault;
    }
    public void setAvgNumCubesInVault(double i){avgNumCubesInVault= i;}

    public double getAvgActiveFceTime(){
        return avgActiveFceTime;
    }
    public void setAvgActiveFceTime(double i){avgActiveFceTime= i;}

    public double getAvgActiveLevTime(){
        return avgActiveLevTime;
    }
    public void setAvgActiveLevTime(double i){avgActiveLevTime= i;}

    public double getAvgActiveBstTime(){
        return avgActiveBstTime;
    }
    public void setAvgActiveBstTime(double i){avgActiveBstTime= i;}

    public double getAvgCubesAtActiveFce(){
        return avgCubesAtActiveFce;
    }
    public void setAvgCubesAtActiveFce(double i){avgCubesAtActiveFce= i;}

    public double getAvgCubesAtActiveLev(){
        return avgCubesAtActiveLev;
    }
    public void setAvgCubesAtActiveLev(double i){avgCubesAtActiveLev= i;}

    public double getAvgCubesAtActiveBst(){
        return avgCubesAtActiveBst;
    }
    public void setAvgCubesAtActiveBst(double i){avgCubesAtActiveBst= i;}

    public int getMaxAutoNumOwnChangesSw1(){return maxAutoNumOwnChangesSw1;}
    public void setMaxAutoNumOwnChangesSw1(int i){ maxAutoNumOwnChangesSw1 = i;}

    public int getMinAutoNumOwnChangesSw1(){return minAutoNumOwnChangesSw1;}
    public void setMinAutoNumOwnChangesSw1(int i){ minAutoNumOwnChangesSw1 = i;}

    public int getMaxAutoNumOwnChangesSw2(){return maxAutoNumOwnChangesSw2;}
    public void setMaxAutoNumOwnChangesSw2(int i){ maxAutoNumOwnChangesSw2 = i;}

    public int getMinAutoNumOwnChangesSw2(){return minAutoNumOwnChangesSw2;}
    public void setMinAutoNumOwnChangesSw2(int i){ minAutoNumOwnChangesSw2 = i;}

    public int getMaxAutoNumOwnChangesScl(){return maxAutoNumOwnChangesScl;}
    public void setMaxAutoNumOwnChangesScl(int i){ maxAutoNumOwnChangesScl = i;}

    public int getMinAutoNumOwnChangesScl(){return minAutoNumOwnChangesScl;}
    public void setMinAutoNumOwnChangesScl(int i){ minAutoNumOwnChangesScl = i;}

    public int getMaxAutoCubesInSw1(){return maxAutoCubesInSw1;}
    public void setMaxAutoCubesInSw1(int i){ maxAutoCubesInSw1 = i;}

    public int getMinAutoCubesInSw1(){return minAutoCubesInSw1;}
    public void setMinAutoCubesInSw1(int i){ minAutoCubesInSw1 = i;}

    public int getMaxAutoCubesInSw2(){return maxAutoCubesInSw2;}
    public void setMaxAutoCubesInSw2(int i){ maxAutoCubesInSw2 = i;}

    public int getMinAutoCubesInSw2(){return minAutoCubesInSw2;}
    public void setMinAutoCubesInSw2(int i){ minAutoCubesInSw2 = i;}

    public int getMaxAutoCubesInScl(){return maxAutoCubesInScl;}
    public void setMaxAutoCubesInScl(int i){ maxAutoCubesInScl = i;}

    public int getMinAutoCubesInScl(){return minAutoCubesInScl;}
    public void setMinAutoCubesInScl(int i){ minAutoCubesInScl = i;}

    public int getMaxAutoCubesInEx(){return maxAutoCubesInEx;}
    public void setMaxAutoCubesInEx(int i){ maxAutoCubesInEx = i;}

    public int getMinAutoCubesInEx(){return minAutoCubesInEx;}
    public void setMinAutoCubesInEx(int i){ minAutoCubesInEx = i;}

    public int getMaxAutoOwnGainSw1(){return maxAutoOwnGainSw1;}
    public void setMaxAutoOwnGainSw1(int i){ maxAutoOwnGainSw1 = i;}

    public int getMinAutoOwnGainSw1(){return minAutoOwnGainSw1;}
    public void setMinAutoOwnGainSw1(int i){ minAutoOwnGainSw1 = i;}

    public int getMaxAutoOwnedSw1(){return maxAutoOwnedSw1;}
    public void setMaxAutoOwnedSw1(int i){ maxAutoOwnedSw1 = i;}

    public int getMinAutoOwnedSw1(){return minAutoOwnedSw1;}
    public void setMinAutoOwnedSw1(int i){ minAutoOwnedSw1 = i;}

    public int getMaxAutoOwnGainSw2(){return maxAutoOwnGainSw2;}
    public void setMaxAutoOwnGainSw2(int i){ maxAutoOwnGainSw2 = i;}

    public int getMinAutoOwnGainSw2(){return minAutoOwnGainSw2;}
    public void setMinAutoOwnGainSw2(int i){ minAutoOwnGainSw2 = i;}

    public int getMaxAutoOwnedSw2(){return maxAutoOwnedSw2;}
    public void setMaxAutoOwnedSw2(int i){ maxAutoOwnedSw2 = i;}

    public int getMinAutoOwnedSw2(){return minAutoOwnedSw2;}
    public void setMinAutoOwnedSw2(int i){ minAutoOwnedSw2 = i;}

    public int getMaxAutoOwnGainScl(){return maxAutoOwnGainScl;}
    public void setMaxAutoOwnGainScl(int i){ maxAutoOwnGainScl = i;}

    public int getMinAutoOwnGainScl(){return minAutoOwnGainScl;}
    public void setMinAutoOwnGainScl(int i){ minAutoOwnGainScl = i;}

    public int getMaxAutoOwnedScl(){return maxAutoOwnedScl;}
    public void setMaxAutoOwnedScl(int i){ maxAutoOwnedScl = i;}

    public int getMinAutoOwnedScl(){return minAutoOwnedScl;}
    public void setMinAutoOwnedScl(int i){ minAutoOwnedScl = i;}

    public int getMaxTeleNumOwnChangesSw1(){return maxTeleNumOwnChangesSw1;}
    public void setMaxTeleNumOwnChangesSw1(int i){ maxTeleNumOwnChangesSw1 = i;}

    public int getMinTeleNumOwnChangesSw1(){return minTeleNumOwnChangesSw1;}
    public void setMinTeleNumOwnChangesSw1(int i){ minTeleNumOwnChangesSw1 = i;}

    public int getMaxTeleNumOwnChangesSw2(){return maxTeleNumOwnChangesSw2;}
    public void setMaxTeleNumOwnChangesSw2(int i){ maxTeleNumOwnChangesSw2 = i;}

    public int getMinTeleNumOwnChangesSw2(){return minTeleNumOwnChangesSw2;}
    public void setMinTeleNumOwnChangesSw2(int i){ minTeleNumOwnChangesSw2 = i;}

    public int getMaxTeleNumOwnChangesScl(){return maxTeleNumOwnChangesScl;}
    public void setMaxTeleNumOwnChangesScl(int i){ maxTeleNumOwnChangesScl = i;}

    public int getMinTeleNumOwnChangesScl(){return minTeleNumOwnChangesScl;}
    public void setMinTeleNumOwnChangesScl(int i){ minTeleNumOwnChangesScl = i;}

    public int getMaxTeleCubesInSw1(){return maxTeleCubesInSw1;}
    public void setMaxTeleCubesInSw1(int i){ maxTeleCubesInSw1 = i;}

    public int getMinTeleCubesInSw1(){return minTeleCubesInSw1;}
    public void setMinTeleCubesInSw1(int i){ minTeleCubesInSw1 = i;}

    public int getMaxTeleCubesInSw2(){return maxTeleCubesInSw2;}
    public void setMaxTeleCubesInSw2(int i){ maxTeleCubesInSw2 = i;}

    public int getMinTeleCubesInSw2(){return minTeleCubesInSw2;}
    public void setMinTeleCubesInSw2(int i){ minTeleCubesInSw2 = i;}

    public int getMaxTeleCubesInScl(){return maxTeleCubesInScl;}
    public void setMaxTeleCubesInScl(int i){ maxTeleCubesInScl = i;}

    public int getMinTeleCubesInScl(){return minTeleCubesInScl;}
    public void setMinTeleCubesInScl(int i){ minTeleCubesInScl = i;}

    public int getMaxTeleCubesInEx(){return maxTeleCubesInEx;}
    public void setMaxTeleCubesInEx(int i){ maxTeleCubesInEx = i;}

    public int getMinTeleCubesInEx(){return minTeleCubesInEx;}
    public void setMinTeleCubesInEx(int i){ minTeleCubesInEx = i;}

    public int getMaxTeleOwnGainSw1(){return maxTeleOwnGainSw1;}
    public void setMaxTeleOwnGainSw1(int i){ maxTeleOwnGainSw1 = i;}

    public int getMinTeleOwnGainSw1(){return minTeleOwnGainSw1;}
    public void setMinTeleOwnGainSw1(int i){ minTeleOwnGainSw1 = i;}

    public int getMaxTeleOwnedSw1(){return maxTeleOwnedSw1;}
    public void setMaxTeleOwnedSw1(int i){ maxTeleOwnedSw1 = i;}

    public int getMinTeleOwnedSw1(){return minTeleOwnedSw1;}
    public void setMinTeleOwnedSw1(int i){ minTeleOwnedSw1 = i;}

    public int getMaxTeleOwnGainSw2(){return maxTeleOwnGainSw2;}
    public void setMaxTeleOwnGainSw2(int i){ maxTeleOwnGainSw2 = i;}

    public int getMinTeleOwnGainSw2(){return minTeleOwnGainSw2;}
    public void setMinTeleOwnGainSw2(int i){ minTeleOwnGainSw2 = i;}

    public int getMaxTeleOwnedSw2(){return maxTeleOwnedSw2;}
    public void setMaxTeleOwnedSw2(int i){ maxTeleOwnedSw2 = i;}

    public int getMinTeleOwnedSw2(){return minTeleOwnedSw2;}
    public void setMinTeleOwnedSw2(int i){ minTeleOwnedSw2 = i;}

    public int getMaxTeleOwnGainScl(){return maxTeleOwnGainScl;}
    public void setMaxTeleOwnGainScl(int i){ maxTeleOwnGainScl = i;}

    public int getMinTeleOwnGainScl(){return minTeleOwnGainScl;}
    public void setMinTeleOwnGainScl(int i){ minTeleOwnGainScl = i;}

    public int getMaxTeleOwnedScl(){return maxTeleOwnedScl;}
    public void setMaxTeleOwnedScl(int i){ maxTeleOwnedScl = i;}

    public int getMinTeleOwnedScl(){return minTeleOwnedScl;}
    public void setMinTeleOwnedScl(int i){ minTeleOwnedScl = i;}

    public int getMaxNumCubesInVault(){return maxNumCubesInVault;}
    public void setMaxNumCubesInVault(int i){ maxNumCubesInVault = i;}

    public int getMinNumCubesInVault(){return minNumCubesInVault;}
    public void setMinNumCubesInVault(int i){ minNumCubesInVault = i;}

    public int getMaxActiveFceTime(){return maxActiveFceTime;}
    public void setMaxActiveFceTime(int i){ maxActiveFceTime = i;}

    public int getMinActiveFceTime(){return minActiveFceTime;}
    public void setMinActiveFceTime(int i){ minActiveFceTime = i;}

    public int getMaxActiveLevTime(){return maxActiveLevTime;}
    public void setMaxActiveLevTime(int i){ maxActiveLevTime = i;}

    public int getMinActiveLevTime(){return minActiveLevTime;}
    public void setMinActiveLevTime(int i){ minActiveLevTime = i;}

    public int getMaxActiveBstTime(){return maxActiveBstTime;}
    public void setMaxActiveBstTime(int i){ maxActiveBstTime = i;}

    public int getMinActiveBstTime(){return minActiveBstTime;}
    public void setMinActiveBstTime(int i){ minActiveBstTime = i;}

    public int getMaxCubesAtActiveFce(){return maxCubesAtActiveFce;}
    public void setMaxCubesAtActiveFce(int i){ maxCubesAtActiveFce = i;}

    public int getMinCubesAtActiveFce(){return minCubesAtActiveFce;}
    public void setMinCubesAtActiveFce(int i){ minCubesAtActiveFce = i;}

    public int getMaxCubesAtActiveLev(){return maxCubesAtActiveLev;}
    public void setMaxCubesAtActiveLev(int i){ maxCubesAtActiveLev = i;}

    public int getMinCubesAtActiveLev(){return minCubesAtActiveLev;}
    public void setMinCubesAtActiveLev(int i){ minCubesAtActiveLev = i;}

    public int getMaxCubesAtActiveBst(){return maxCubesAtActiveBst;}
    public void setMaxCubesAtActiveBst(int i){ maxCubesAtActiveBst = i;}

    public int getMinCubesAtActiveBst(){return minCubesAtActiveBst;}
    public void setMinCubesAtActiveBst(int i){ minCubesAtActiveBst = i;}
}
