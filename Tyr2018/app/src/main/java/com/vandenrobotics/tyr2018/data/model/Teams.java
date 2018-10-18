package com.vandenrobotics.tyr2018.data.model;

/**
 * Created by Programming701-A on 12/13/2017.
 */

public class Teams {

    public static final String TABLE = "TeamsAll";
    public static final String KEY_TeamNumber = "TeamNumber";
    public static final String KEY_TeamName = "TeamName";

    private int TeamNumber;
    private String teamName;

    public int getTeamNumber(){
        return TeamNumber;
    }

    public void setTeamNumber(int i){
        TeamNumber = i;
    }

    public String getTeamName(){
        return teamName;
    }

    public void setTeamName(String s){
        teamName = s;
    }
}
