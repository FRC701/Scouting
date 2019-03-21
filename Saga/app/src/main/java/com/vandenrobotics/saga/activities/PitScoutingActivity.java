package com.vandenrobotics.saga.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.vandenrobotics.saga.R;
import com.vandenrobotics.saga.data.model.PitData;
import com.vandenrobotics.saga.data.repo.PitDataRepo;
import com.vandenrobotics.saga.data.repo.TeamsRepo;
import com.vandenrobotics.saga.tools.ExternalStorageTools;

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
    private EditText climb;
    private CheckBox driveBlindly;
    private CheckBox auto;
    private CheckBox vision;
    private CheckBox camera;
    private CheckBox other;
    private RadioButton startLevel1;
    private RadioButton startLevel2;
    private RadioButton robotCargo;
    private RadioButton robotHatch;
    private RadioButton cargoShipCargo;
    private RadioButton cargoShipHatch;
    private CheckBox hatchInRocket;
    private CheckBox cargoInRocket;
    private CheckBox hatchInCargoShip;
    private CheckBox cargoInCargoShip;
    private CheckBox intakeHatch;
    private CheckBox intakeCargo;
    private CheckBox reachFirstPlatform;
    private CheckBox reachSecondPlatform;
    private CheckBox reachThirdPlatform;
    private CheckBox scoreBottom;
    private CheckBox scoreMiddle;
    private CheckBox scoreTop;
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
        climb = (EditText)findViewById(R.id.climbTime);
        driveBlindly = (CheckBox)findViewById(R.id.pitDriveBlindly_Cb);
        auto = (CheckBox)findViewById(R.id.pitAuto_Cb);
        vision = (CheckBox)findViewById(R.id.pitVision_Cb);
        camera = (CheckBox)findViewById(R.id.pitCamera_Cb);
        other = (CheckBox)findViewById(R.id.pitOther_Cb);
        startLevel1 = (RadioButton) findViewById(R.id.pitLevel1_Rb);
        startLevel2 = (RadioButton) findViewById(R.id.pitLevel2_Rb);
        robotCargo = (RadioButton) findViewById(R.id.pitRobotCargo_Rb);
        robotHatch = (RadioButton) findViewById(R.id.pitRobotHatch_Rb);
        cargoShipCargo = (RadioButton) findViewById(R.id.pitCargoShipCargo_Rb);
        cargoShipHatch = (RadioButton) findViewById(R.id.pitCargoShipHatch_Rb);
        hatchInRocket = (CheckBox) findViewById(R.id.pitHatchInRocketShip_Cb);
        cargoInRocket = (CheckBox) findViewById(R.id.pitCargoInRocketShip_Cb);
        hatchInCargoShip = (CheckBox) findViewById(R.id.pitHatchInCargoShip_Cb);
        cargoInCargoShip = (CheckBox) findViewById(R.id.pitCargoInCargoShip_Cb);
        intakeHatch = (CheckBox) findViewById(R.id.pitHatch_Cb);
        intakeCargo = (CheckBox) findViewById(R.id.cargo_Cb);
        reachFirstPlatform = (CheckBox) findViewById(R.id.reachFirstPlatform_Cb);
        reachSecondPlatform = (CheckBox) findViewById(R.id.reachSecondPlatform_Cb);
        reachThirdPlatform = (CheckBox) findViewById(R.id.reachThirdPlatform_Cb);
        scoreBottom = (CheckBox) findViewById(R.id.scoreBottom_Cb);
        scoreMiddle = (CheckBox) findViewById(R.id.scoreMiddle_Cb);
        scoreTop = (CheckBox) findViewById(R.id.scoreTop_Cb);

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
       pitdata.setClimb(climb.getText().toString());

       int db = (driveBlindly.isChecked() ? 1:0);
       pitdata.setDriveBlindly(db);
       int a = (auto.isChecked() ? 1:0);
       pitdata.setAuto(a);
       int v = (vision.isChecked() ? 1:0);
       pitdata.setVision(v);
       int cam = (camera.isChecked() ? 1:0);
       pitdata.setCamera(cam);
       int oth = (other.isChecked() ? 1:0);
       pitdata.setOther(oth);
       int sl1 = (startLevel1.isChecked() ? 1:0);
       pitdata.setStartLevel1(sl1);
       int sl2 = (startLevel2.isChecked() ? 1:0);
       pitdata.setStartLevel2(sl2);
       int rc = (robotCargo.isChecked() ? 1:0);
       pitdata.setRobotCargo(rc);
       int rh = (robotHatch.isChecked() ? 1:0);
       pitdata.setRobotHatch(rh);
       int csc = (cargoShipCargo.isChecked() ? 1:0);
       pitdata.setCargoShipCargo(csc);
       int csh = (cargoShipHatch.isChecked() ? 1:0);
       pitdata.setCargoShipHatch(csh);
       int hr = (hatchInRocket.isChecked() ? 1:0);
       pitdata.setHatchInRocket(hr);
       int cr = (cargoInRocket.isChecked() ? 1:0);
       pitdata.setCargoInRocket(cr);
       int hcs = (hatchInCargoShip.isChecked() ? 1:0);
       pitdata.setHatchInCargoShip(hcs);
       int ccs = (cargoInCargoShip.isChecked() ? 1:0);
       pitdata.setCargoInCargoShip(ccs);
       int ih = (intakeHatch.isChecked() ? 1:0);
       pitdata.setIntakeHatch(ih);
       int ic = (intakeCargo.isChecked() ? 1:0);
       pitdata.setIntakeCargo(ic);
       int rfp = (reachFirstPlatform.isChecked() ? 1:0);
       pitdata.setReachFirstPlatform(rfp);
       int rsp = (reachSecondPlatform.isChecked() ? 1:0);
       pitdata.setReachSecondPlatform(rsp);
       int rtp = (reachThirdPlatform.isChecked() ? 1:0);
       pitdata.setReachThirdPlatform(rtp);
       int sb = (scoreBottom.isChecked() ? 1:0);
       pitdata.setScoreBottom(sb);
       int sm = (scoreMiddle.isChecked() ? 1:0);
       pitdata.setScoreMiddle(sm);
       int st = (scoreTop.isChecked() ? 1:0);
       pitdata.setScoreTop(st);



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
        climb.setText(pitData.getClimb());
        driveBlindly.setChecked(pitData.getDriveBlindly() == 1);
        auto.setChecked(pitData.getAuto() == 1);
        vision.setChecked(pitData.getVision() == 1);
        camera.setChecked(pitData.getCamera() == 1);
        other.setChecked(pitData.getOther() == 1);
        startLevel1.setChecked(pitData.getStartLevel1() == 1);
        startLevel2.setChecked(pitData.getStartLevel2() == 1);
        robotCargo.setChecked(pitData.getRobotCargo() == 1);
        robotHatch.setChecked(pitData.getRobotHatch() == 1);
        cargoShipCargo.setChecked(pitData.getCargoShipCargo() == 1);
        cargoShipHatch.setChecked(pitData.getCargoShipCargo() == 1);
        hatchInRocket.setChecked(pitData.getHatchInRocket() == 1);
        cargoInRocket.setChecked(pitData.getCargoInRocket() == 1);
        hatchInCargoShip.setChecked(pitData.getHatchInCargoShip() == 1);
        cargoInCargoShip.setChecked(pitData.getCargoInCargoShip() == 1);
        intakeHatch.setChecked(pitData.getIntakeHatch() == 1);
        intakeCargo.setChecked(pitData.getIntakeCargo() == 1);
        reachFirstPlatform.setChecked(pitData.getReachFirstPlatform() == 1);
        reachSecondPlatform.setChecked(pitData.getReachSecondPlatform() == 1);
        reachThirdPlatform.setChecked(pitData.getReachThirdPlatform() == 1);
        scoreBottom.setChecked(pitData.getScoreBottom() == 1);
        scoreMiddle.setChecked(pitData.getScoreMiddle() == 1);
        scoreTop.setChecked(pitData.getScoreTop() == 1);





   }


}
