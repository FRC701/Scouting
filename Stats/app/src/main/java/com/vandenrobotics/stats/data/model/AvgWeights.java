package com.vandenrobotics.stats.data.model;

public class AvgWeights {

    public static final String TABLE = "AvgWeights";

    public static final String INDEX = "comp_match_team";

    public static final String KEY_TeamNum = "TeamNum";
    public static final String KEY_AvgOff = "OffWS";
    public static final String KEY_AvgDef = "DefWS";
    public static final String KEY_AvgTotal = "TotalWS";
    public static final String KEY_AvgNeg = "NegWS";
    public static final String KEY_PNoShow = "PercentNoShow";
    public static final String KEY_PStartLevel1 = "PercentStartLevel1";
    public static final String KEY_PStartLevel2 = "PercentStartLevel2";
    public static final String KEY_PPreloadCargo = "PercentPreloadC";
    public static final String KEY_PPreloadHatch = "PercentPreloadH";
    public static final String KEY_AvgRocketTC = "AvgRocketTopC";
    public static final String KEY_AvgRocketTH = "AvgRocketTopH";
    public static final String KEY_AvgRocketMC = "AvgRocketMiddleC";
    public static final String KEY_AvgRocketMH = "AvgRocketMiddleH";
    public static final String KEY_AvgRocketBC = "AvgRocketBottomC";
    public static final String KEY_AvgRocketBH = "AvgRocketBottomH";
    public static final String KEY_AvgCargoShipC = "AvgCargoShipC";
    public static final String KEY_AvgCargoShipH = "AvgCargoShipH";
    public static final String KEY_PCrossHubline = "PercentCrossHubLine";
    public static final String KEY_PEndLevel1 = "PercentEndLevel1";
    public static final String KEY_PEndLevel2 = "PercentEndLevel2";
    public static final String KEY_PEndLevel3 = "PercentEndLevel3";
    public static final String KEY_PEndNone = "PercentEndNone";
    public static final String KEY_PRobotDisabled = "PercentRobotDisabled";
    public static final String KEY_PRedCard = "PercentRedCard";
    public static final String KEY_PYellowCard = "PercentYellowCard";
    public static final String KEY_AvgFouls = "AvgFouls";
    public static final String KEY_AvgTechFouls = "AvgTechFouls";
    public static final String KEY_PDefense = "PercentDefense";

    private int teamNum;
    private double avgOffWS;
    private double avgDefWS;
    private double avgTotalWS;
    private double avgNegWS;
    private double percentNoShow;
    private double percentStartLevel1;
    private double percentStartLevel2;
    private double percentPreLoadC;
    private double percentPreLoadH;
    private double avgRocketTopC;
    private double avgRocketTopH;
    private double avgRocketMiddleC;
    private double avgRocketMiddleH;
    private double avgRocketBottomC;
    private double avgRocketBottomH;
    private double avgCargoShipC;
    private double avgCargoShipH;
    private double percentCrossHubLine;
    private double percentEndLevel1;
    private double percentEndLevel2;
    private double percentEndLevel3;
    private double percentEndNone;
    private double percentDisabled;
    private double percentRedCard;
    private double percentYellowCard;
    private double avgFouls;
    private double avgTechFouls;
    private double percentDefense;

    public AvgWeights(){
        teamNum = 0;
        avgOffWS = 0;
        avgDefWS = 0;
        avgTotalWS = 0;
        avgNegWS = 0;
        percentNoShow = 0;
        percentStartLevel1 = 0;
        percentStartLevel2 = 0;
        percentPreLoadC = 0;
        percentPreLoadH = 0;
        avgRocketTopC = 0;
        avgRocketTopH = 0;
        avgRocketMiddleC = 0;
        avgRocketMiddleH = 0;
        avgRocketBottomC = 0;
        avgRocketBottomH = 0;
        avgCargoShipC = 0;
        avgCargoShipH = 0;
        percentCrossHubLine = 0;
        percentEndLevel1 = 0;
        percentEndLevel2 = 0;
        percentEndLevel3 = 0;
        percentEndNone = 0;
        percentDisabled = 0;
        percentRedCard = 0;
        percentYellowCard = 0;
        avgFouls = 0;
        avgTechFouls = 0;
        percentDefense = 0;
    }

    public int getTeamNum() {
        return teamNum;
    }
    public void setTeamNum(int b){teamNum = b;}

    public double getAvgOffWS() {
        return avgOffWS;
    }
    public void setAvgOffWS(double b){avgOffWS = b;}

    public double getAvgDefWS() {
        return avgDefWS;
    }
    public void setAvgDefWS(double b){avgDefWS = b;}

    public double getAvgTotalWS() {
        return avgTotalWS;
    }
    public void setAvgTotalWS(double b){avgTotalWS = b;}

    public double getAvgNegWS() {
        return avgNegWS;
    }
    public void setAvgNegWS(double b){avgNegWS = b;}

    public double getPercentNoShow() {
        return percentNoShow;
    }
    public void setNoShow(double b){percentNoShow = b;}

    public double getPercentStartLevel1() {
        return percentStartLevel1;
    }
    public void setPercentStartLevel1(double b){percentStartLevel1 = b;}

    public double getPercentStartLevel2() {
        return percentStartLevel2;
    }
    public void setPercentStartLevel2(double b){percentStartLevel2 = b;}

    public double getPercentPreLoadC() {
        return percentPreLoadC;
    }
    public void setPercentPreLoadC(double b){percentPreLoadC = b;}

    public double getPercentPreLoadH() {
        return percentPreLoadH;
    }
    public void setPercentPreLoadH(double b){percentPreLoadH = b;}

    public double getAvgRocketTopC() {
        return avgRocketTopC;
    }
    public void setAvgRocketTopC(double b){avgRocketTopC = b;}

    public double getAvgRocketTopH() {
        return avgRocketTopH;
    }
    public void setAvgRocketTopH(double b){avgRocketTopH = b;}

    public double getAvgRocketMiddleC() {
        return avgRocketMiddleC;
    }
    public void setAvgRocketMiddleC(double b){avgRocketMiddleC = b;}

    public double getAvgRocketMiddleH() {
        return avgRocketMiddleH;
    }
    public void setAvgRocketMiddleH(double b){avgRocketMiddleH = b;}

    public double getAvgRocketBottomC() {
        return avgRocketBottomC;
    }
    public void setAvgRocketBottomC(double b){avgRocketBottomC = b;}

    public double getAvgRocketBottomH() {
        return avgRocketBottomH;
    }
    public void setAvgRocketBottomH(double b){avgRocketBottomH = b;}

    public double getAvgCargoShipC() {
        return avgCargoShipC;
    }
    public void setAvgCargoShipC(double b){avgCargoShipC = b;}

    public double getAvgCargoShipH() {
        return avgCargoShipH;
    }
    public void setAvgCargoShipH(double b){avgCargoShipH = b;}

    public double getPercentCrossHubLine() {
        return percentCrossHubLine;
    }
    public void setPercentCrossHubLine(double b){percentCrossHubLine = b;}

    public double getPercentEndLevel1() {
        return percentEndLevel1;
    }
    public void setPercentEndLevel1(double b){percentEndLevel1 = b;}

    public double getPercentEndLevel2() {
        return percentEndLevel2;
    }
    public void setPercentEndLevel2(double b){percentEndLevel2 = b;}

    public double getPercentEndLevel3() {
        return percentEndLevel3;
    }
    public void setPercentEndLevel3(double b){percentEndLevel3 = b;}

    public double getPercentEndNone() {
        return percentEndNone;
    }
    public void setPercentEndNone(double b){percentEndNone = b;}

    public double getPercentRedCard() {
        return percentRedCard;
    }
    public void setPercentRedCard(double b){percentRedCard = b;}

    public double getPercentYellowCard() {
        return percentYellowCard;
    }
    public void setPercentYellowCard(double b){percentYellowCard = b;}

    public double getAvgFouls() {
        return avgFouls;
    }
    public void setAvgFouls(double b){avgFouls = b;}

    public double getAvgTechFouls() {
        return avgTechFouls;
    }
    public void setAvgTechFouls(double b){avgTechFouls = b;}

    public double getPercentDefense() {
        return percentDefense;
    }
    public void setPercentDefense(double b){percentDefense = b;}

    public double getPercentDisabled() {
        return percentDisabled;
    }
    public void setPercentDisabled(double b){percentDisabled = b;}




}
