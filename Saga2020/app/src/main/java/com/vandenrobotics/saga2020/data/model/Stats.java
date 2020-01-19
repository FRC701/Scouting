
package com.vandenrobotics.saga2020.data.model;

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
    private int disabled;
    private int redCard;
    private int yellowCard;
    private int foul;
    private int techFoul;
    private int disqualified;


    public Stats(){
        compId = "None";
        matchNum = 0;
        teamNum = 0;
        matchPos = 0;
        noShow = 0;
        disqualified = 0;
        disabled = 0;
        redCard = 0;
        yellowCard = 0;
        foul = 0;
        techFoul = 0;

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

    public int getDisqualified(){
        return disqualified;
    }
    public void setDisqualified(int b){disqualified = b;}

    public int getDisabled(){
        return disabled;
    }
    public void setDisabled(int b){disabled = b;}

    public int getRedCard(){
        return redCard;
    }
    public void setRedCard(int b){redCard = b;}

    public int getYellowCard(){
        return yellowCard;
    }
    public void setYellowCard(int b){yellowCard = b;}

    public int getFoul(){
        return foul;
    }
    public void setFoul(int b){foul = b;}

    public int getTechFoul(){
        return techFoul;
    }
    public void setTechFoul(int b){techFoul = b;}

}

