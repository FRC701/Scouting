package com.vandenrobotics.functionfirst.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.model.Match;
import com.vandenrobotics.functionfirst.model.MatchData;
import com.vandenrobotics.functionfirst.tools.ExternalStorageTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ScoutActivity extends ActionBarActivity {

    private static String mEvent;
    private static int mDeviceNumber;
    private static int mCurMatch;
    private static ArrayList<Integer> team_numbers;

    private static ArrayList<Match> mMatchList;
    private static ArrayList<MatchData> mMatchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout);
        mEvent = getIntent().getStringExtra("event");
        mDeviceNumber = ExternalStorageTools.readDevice(mEvent);
        mCurMatch = ExternalStorageTools.readCurrentMatch(mEvent, mDeviceNumber);
        mMatchList = ExternalStorageTools.readMatches(mEvent);
        mMatchData = ExternalStorageTools.readData(mEvent, mDeviceNumber);

        ArrayList<JSONObject> teamInfo = ExternalStorageTools.readTeams(mEvent);
        team_numbers = new ArrayList<>(teamInfo.size());
        try {
            for (int i = 0; i < teamInfo.size(); i++) {
                team_numbers.add(i, teamInfo.get(i).getInt("team_number"));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        mCurMatch = ExternalStorageTools.readCurrentMatch(mEvent, mDeviceNumber);
        Intent intent = new Intent(this, MatchActivity.class);
        try {
            intent.putExtra("matchNumber", mCurMatch);
            intent.putExtra("team_numbers", team_numbers);
            intent.putExtra("teamNumber", mMatchList.get(mCurMatch - 1).teams[mDeviceNumber - 1]);
            intent.putExtra("deviceNumber", mDeviceNumber);
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        startActivity(intent);
    }

    public static Intent goToMatch(Context context, int curMatch, int newMatch, int dNum, MatchData matchData){
        // write old stuff to file
        mMatchData.add(mCurMatch-1, matchData);
        ExternalStorageTools.writeData(mMatchData, mEvent, mDeviceNumber);

        mCurMatch = (newMatch>0 && newMatch<=mMatchList.size()+1)? newMatch : curMatch;
        ExternalStorageTools.writeCurrentMatch(mCurMatch, mEvent, mDeviceNumber);

        mDeviceNumber = dNum;
        ExternalStorageTools.writeDevice(mDeviceNumber, mEvent);
        mMatchData = ExternalStorageTools.readData(mEvent, mDeviceNumber);

        Intent intent = new Intent(context, MatchActivity.class);
        try {
            intent.putExtra("matchNumber", mCurMatch);
            intent.putExtra("team_numbers", team_numbers);
            intent.putExtra("teamNumber", mMatchList.get(mCurMatch-1).teams[mDeviceNumber - 1]);
            intent.putExtra("deviceNumber", mDeviceNumber);
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return intent;
    }
}
