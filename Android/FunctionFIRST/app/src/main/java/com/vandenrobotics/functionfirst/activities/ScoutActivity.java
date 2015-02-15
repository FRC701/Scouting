package com.vandenrobotics.functionfirst.activities;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;

import android.text.TextWatcher;
import android.text.Editable;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.model.Match;
import com.vandenrobotics.functionfirst.model.MatchData;
import com.vandenrobotics.functionfirst.tools.ExternalStorageTools;
import com.vandenrobotics.functionfirst.tools.JSONTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class ScoutActivity extends Activity {

    private String mEvent;
    private int mDeviceNumber;
    private int mCurMatch;
    private int mTeamNumber;
    private ArrayList<Integer> team_numbers;

    private ArrayList<Match> mMatchList;
    private ArrayList<MatchData> mMatchData;

    private Spinner spinnerDevices;
    private ArrayAdapter<CharSequence> deviceAdapter;
    private EditText editMatches;
    private Spinner spinnerTeams;
    private ArrayAdapter<Integer> teamAdapter;

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
        teamInfo = JSONTools.sortJSONArray(teamInfo, "team_number");
        team_numbers = new ArrayList<>(teamInfo.size());
        try {
            for (int i = 0; i < teamInfo.size(); i++) {
                team_numbers.add(i, teamInfo.get(i).getInt("team_number"));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        Collections.sort(team_numbers);

        spinnerDevices = (Spinner)findViewById(R.id.spinnerDeviceNumber);
        deviceAdapter = ArrayAdapter.createFromResource(this, R.array.deviceOptions, R.layout.spinner_base);
        deviceAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinnerDevices.setAdapter(deviceAdapter);
        spinnerDevices.setSelection(mDeviceNumber-1);

        editMatches = (EditText)findViewById(R.id.editMatch);
        editMatches.setText(mCurMatch+"");

        spinnerTeams = (Spinner)findViewById(R.id.spinnerTeamNumber);
        teamAdapter = new ArrayAdapter<>(this, R.layout.spinner_base, team_numbers);
        teamAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinnerTeams.setAdapter(teamAdapter);
        spinnerTeams.setSelection(teamAdapter.getPosition(mTeamNumber));

        spinnerDevices.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v, int position, long arg3) {
                mDeviceNumber=spinnerDevices.getSelectedItemPosition()+1;
                mCurMatch = ExternalStorageTools.readCurrentMatch(mEvent, mDeviceNumber);
                mTeamNumber = (mMatchList.size()>0)? mMatchList.get(mCurMatch-1).teams[mDeviceNumber - 1] : 0;

                editMatches.setText(mCurMatch+"");
                spinnerTeams.setSelection(teamAdapter.getPosition(mTeamNumber));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter){

            }
        });

        editMatches.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s){
                mCurMatch = Integer.parseInt(s.toString());
                mTeamNumber = (mMatchList.size()>0)? mMatchList.get(mCurMatch-1).teams[mDeviceNumber - 1] : 0;

                spinnerTeams.setSelection(teamAdapter.getPosition(mTeamNumber));
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        spinnerTeams.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v, int position, long arg3) {
                mTeamNumber = Integer.parseInt(spinnerTeams.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter) {

            }
        });
    }

    public void activityMatch(View view){
        // load the new match, passing all the info to it
        mDeviceNumber = spinnerDevices.getSelectedItemPosition()+1;
        mCurMatch = Integer.parseInt(editMatches.getText().toString());
        mTeamNumber = (int)spinnerTeams.getSelectedItem();
        Intent intent = new Intent(this, MatchActivity.class);
        try {
            intent.putExtra("matchNumber", mCurMatch);
            intent.putExtra("team_numbers", team_numbers);
            intent.putExtra("teamNumber", mTeamNumber);
            intent.putExtra("deviceNumber", mDeviceNumber);
        } catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        startActivity(intent);
    }
}
