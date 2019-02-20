package com.vandenrobotics.stats.data.model;

public class AvgWeights {

    public static final String TABLE = "AvgWeights";

    public static final String INDEX = "comp_match_team";

    public static final String KEY_TeamNum = "TeamNum";
    public static final String KEY_AvgOff = "OffWS";
    public static final String KEY_AvgDef = "DefWS";
    public static final String KEY_AvgTotal = "TotalWS";
    public static final String KEY_AvgNeg = "NegWS";

    private int teamNum;
    private double avgOffWS;
    private double avgDefWS;
    private double avgTotalWS;
    private double avgNegWS;

    public AvgWeights() {
        teamNum = 0;
        avgOffWS = 0;
        avgDefWS = 0;
        avgTotalWS = 0;
        avgNegWS = 0;
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


}
