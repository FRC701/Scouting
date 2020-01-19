package com.vandenrobotics.saga2020.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.vandenrobotics.saga2020.R;
import com.vandenrobotics.saga2020.data.model.PitData;
import com.vandenrobotics.saga2020.data.repo.PitDataRepo;
import com.vandenrobotics.saga2020.data.repo.TeamsRepo;
import com.vandenrobotics.saga2020.tools.ExternalStorageTools;

import java.util.ArrayList;
import java.util.Collections;

public class PitScoutingActivity extends AppCompatActivity {

    private EditText intakeAndMech;
    private EditText driveTrain;
    private EditText speed;
    private EditText lang;
    private EditText comments;
    private EditText driverExperience;
    private EditText coDriverExperience;
    private ArrayList<Integer> team_numbers;
    private TeamsRepo teamsRepo;
    private PitDataRepo pitDataRepo;
    private Spinner spinnerTeams;
    private ArrayAdapter<Integer> teamAdapter;
    private int mTeamNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting);

        pitDataRepo = new PitDataRepo();

        teamsRepo = new TeamsRepo();
        team_numbers = teamsRepo.getAllTeamNums();
        Collections.sort(team_numbers);
        spinnerTeams = (Spinner) findViewById(R.id.pit_spinnerTeamNum);
        teamAdapter = new ArrayAdapter<>(this, R.layout.spinner_base, team_numbers);
        teamAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinnerTeams.setSelection(teamAdapter.getPosition(mTeamNumber));
        spinnerTeams.setAdapter(teamAdapter);

        spinnerTeams.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v, int position, long arg3){
                mTeamNumber = Integer.parseInt(spinnerTeams.getItemAtPosition(position).toString());
                ArrayList<Integer> teamsHaveData = pitDataRepo.getTeams();
                if (!teamsHaveData.isEmpty()){
                    boolean teamLoaded = false;
                    for(int i : teamsHaveData){
                        if(i == mTeamNumber) {
                            PitData pitData = pitDataRepo.getTeamData(mTeamNumber);
                            loadData(pitData);
                            teamLoaded = true;
                        }
                    }
                    if (!teamLoaded){
                        PitData pitData = new PitData(mTeamNumber);
                        loadData(pitData);
                    }
                }else{
                    for (int team : teamsRepo.getAllTeamNums()){
                        PitData pitData = new PitData(team);
                        pitDataRepo.insert(pitData);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter){

            }
        });

        intakeAndMech = (EditText)findViewById(R.id.mechanismAnswer);
        driveTrain = (EditText)findViewById(R.id.driveTrainAnswer);
        speed = (EditText)findViewById(R.id.speedAnswer);
        lang = (EditText)findViewById(R.id.programmingLanguageAnswer);
        comments = (EditText)findViewById(R.id.commentsAnswer);
        driverExperience = (EditText)findViewById(R.id.driverExperience);
        coDriverExperience = (EditText)findViewById(R.id.coDriverExperience);


    }

   public void save(View view){
       PitData pitdata = new PitData(mTeamNumber);
       pitdata.setIntakeAndMech(intakeAndMech.getText().toString());
       pitdata.setDriveTrain(driveTrain.getText().toString());
       pitdata.setSpeed(speed.getText().toString());
       pitdata.setLang(lang.getText().toString());
       pitdata.setComments(comments.getText().toString());
       pitdata.setDriverExperience(driverExperience.getText().toString());
       pitdata.setCoDriverExperience(coDriverExperience.getText().toString());




        pitDataRepo.insert(pitdata);




       Toast.makeText(this, "Saved Data", Toast.LENGTH_LONG).show();
       ExternalStorageTools.writeDatabaseToES();
   }

   public void loadData(PitData pitData){
        intakeAndMech.setText(pitData.getIntakeAndMech());
        driveTrain.setText(pitData.getDriveTrain());
        speed.setText(pitData.getSpeed());
        lang.setText(pitData.getLang());
        comments.setText(pitData.getComments());
        driverExperience.setText(pitData.getDriverExperience());
        coDriverExperience.setText(pitData.getCoDriverExperience());



   }


}
