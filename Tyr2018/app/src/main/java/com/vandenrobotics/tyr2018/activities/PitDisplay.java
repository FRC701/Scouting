package com.vandenrobotics.tyr2018.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.vandenrobotics.tyr2018.R;
import com.vandenrobotics.tyr2018.data.model.PitData;
import com.vandenrobotics.tyr2018.data.repo.PitDataRepo;
import com.vandenrobotics.tyr2018.data.repo.TeamsRepo;

import java.util.ArrayList;
import java.util.Collections;


public class PitDisplay extends AppCompatActivity {

    private TextView auto;
    private TextView swSc;
    private TextView avgSpeed;
    private TextView cycle;
    private TextView climb;
    private TextView floor;
    private TextView driveTrain;
    private TextView intake;
    private TextView lang;
    private TextView comments;

    private ArrayList<Integer> team_numbers;
    private PitDataRepo pitDataRepo;
    private Spinner spinnerTeams;
    private ArrayAdapter<Integer> teamAdapter;
    private int mTeamNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_display);



        TeamsRepo teamsRepo = new TeamsRepo();
        team_numbers = teamsRepo.getTeams();
        Collections.sort(team_numbers);
        spinnerTeams = (Spinner) findViewById(R.id.pit_spinnerTeamNum);
        teamAdapter = new ArrayAdapter<>(this, R.layout.spinner_base, team_numbers);
        teamAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinnerTeams.setSelection(teamAdapter.getPosition(mTeamNumber));
        spinnerTeams.setAdapter(teamAdapter);
        pitDataRepo = new PitDataRepo();

        spinnerTeams.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v, int position, long arg3){
                mTeamNumber = Integer.parseInt(spinnerTeams.getItemAtPosition(position).toString());
                ArrayList<Integer> teamsHaveData = pitDataRepo.getTeams();
                for(int i : teamsHaveData){
                    if(i == mTeamNumber){
                        PitData pitData = pitDataRepo.getTeamData(mTeamNumber);
                        loadData(pitData);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter){

            }
        });


        auto = (TextView) findViewById(R.id.pit_autoAns);
        swSc = (TextView) findViewById(R.id.pit_SwScAns);
        avgSpeed = (TextView) findViewById(R.id.pit_averageSpeedAns);
        cycle = (TextView) findViewById(R.id.pit_cycleAns);
        climb = (TextView) findViewById(R.id.pit_climbAns);
        floor = (TextView) findViewById(R.id.pit_FloorAns);
        driveTrain = (TextView) findViewById(R.id.pit_driveTrainAns);
        intake = (TextView) findViewById(R.id.pit_intakeAns);
        comments = (TextView) findViewById(R.id.pit_commentsAns);
        lang = (TextView) findViewById(R.id.pit_langAns);
    }
    private void loadData (PitData pitData){
        String a = "";
        if(pitData.getAutoBaseline() == 1){
            a += "Crosses AutoLine, ";
        }
        else if(pitData.getAutoCubeInSwitch() == 1){
            a += "Cube in Switch, ";
        }
        else if(pitData.getAutoCubeInScale() == 1){
            a += "Cube in Scale, ";
        }
        else if(pitData.getAutoCubeInExchange() == 1){
            a += "Cube in Exchange, ";
        }
        else if(pitData.getAutoOther() == 1){
            a += "Other, ";
        }
        else{
            a += "None";
        }
        auto.setText(a);
        String c = "";
        if(pitData.getCycleGround() == 1){
            c += "Ground, ";
        }
        else if(pitData.getCyclePortal() == 1){
            c += "Portal, ";
        }
        else if(pitData.getCycleSwitches() == 1){
            c += "Switch Area, ";
        }
        cycle.setText(c);

        String s = "";
        if(pitData.getGetSwitch() == 1){
            s += "Switch, ";
        }
        else if(pitData.getGetScale() == 1){
            s += "Scale, ";
        }
        swSc.setText(s);
        String p = "";
        if(pitData.getFloorPickUp() == 1){
            p += "Yes";
        }
        else{
            p += "No";
        }
        floor.setText(p);
        String clim = pitData.getClimb();
        climb.setText(clim);
        String i = pitData.getIntakeAndMech();
        intake.setText(i);
        String d = pitData.getDriveTrain();
        driveTrain.setText(d);
        String sp = pitData.getSpeed();
        avgSpeed.setText(sp);
        String l = pitData.getLang();
        lang.setText(l);
        String co = pitData.getComments();
        comments.setText(co);
    }

}