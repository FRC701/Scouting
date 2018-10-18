package com.vandenrobotics.tyr2018.activities;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.vandenrobotics.tyr2018.R;
import com.vandenrobotics.tyr2018.data.model.TeamInfo;
import com.vandenrobotics.tyr2018.data.repo.TeamInfoRepo;
import com.vandenrobotics.tyr2018.data.repo.TeamsRepo;

import java.util.ArrayList;
import java.util.Collections;

public class MinMax extends AppCompatActivity {

    private TextView maxAutoNumOwnChangesSw1;
    private TextView minAutoNumOwnChangesSw1;
    private TextView maxAutoNumOwnChangesSw2;
    private TextView minAutoNumOwnChangesSw2;
    private TextView maxAutoNumOwnChangesScl;
    private TextView minAutoNumOwnChangesScl;
    private TextView maxAutoCubesInSw1;
    private TextView minAutoCubesInSw1;
    private TextView maxAutoCubesInSw2;
    private TextView minAutoCubesInSw2;
    private TextView maxAutoCubesInScl;
    private TextView minAutoCubesInScl;
    private TextView maxAutoCubesInEx;
    private TextView minAutoCubesInEx;
    private TextView maxAutoOwnGainSw1;
    private TextView minAutoOwnGainSw1;
    private TextView maxAutoOwnedSw1;
    private TextView minAutoOwnedSw1;
    private TextView maxAutoOwnGainSw2;
    private TextView minAutoOwnGainSw2;
    private TextView maxAutoOwnedSw2;
    private TextView minAutoOwnedSw2;
    private TextView maxAutoOwnGainScl;
    private TextView minAutoOwnGainScl;
    private TextView maxAutoOwnedScl;
    private TextView minAutoOwnedScl;
    private TextView maxTeleNumOwnChangesSw1;
    private TextView minTeleNumOwnChangesSw1;
    private TextView maxTeleNumOwnChangesSw2;
    private TextView minTeleNumOwnChangesSw2;
    private TextView maxTeleNumOwnChangesScl;
    private TextView minTeleNumOwnChangesScl;
    private TextView maxTeleCubesInSw1;
    private TextView minTeleCubesInSw1;
    private TextView maxTeleCubesInSw2;
    private TextView minTeleCubesInSw2;
    private TextView maxTeleCubesInScl;
    private TextView minTeleCubesInScl;
    private TextView maxTeleCubesInEx;
    private TextView minTeleCubesInEx;
    private TextView maxTeleOwnGainSw1;
    private TextView minTeleOwnGainSw1;
    private TextView maxTeleOwnedSw1;
    private TextView minTeleOwnedSw1;
    private TextView maxTeleOwnGainSw2;
    private TextView minTeleOwnGainSw2;
    private TextView maxTeleOwnedSw2;
    private TextView minTeleOwnedSw2;
    private TextView maxTeleOwnGainScl;
    private TextView minTeleOwnGainScl;
    private TextView maxTeleOwnedScl;
    private TextView minTeleOwnedScl;
    private TextView maxNumCubesInVault;
    private TextView minNumCubesInVault;
    private TextView maxActiveFceTime;
    private TextView minActiveFceTime;
    private TextView maxActiveLevTime;
    private TextView minActiveLevTime;
    private TextView maxActiveBstTime;
    private TextView minActiveBstTime;
    private TextView maxCubesAtActiveFce;
    private TextView minCubesAtActiveFce;
    private TextView maxCubesAtActiveLev;
    private TextView minCubesAtActiveLev;
    private TextView maxCubesAtActiveBst;
    private TextView minCubesAtActiveBst;
    private ImageView minFceCube1;
    private ImageView minLevCube1;
    private ImageView minBstCube1;
    private ImageView minFceCube2;
    private ImageView minLevCube2;
    private ImageView minBstCube2;
    private ImageView minFceCube3;
    private ImageView minLevCube3;
    private ImageView minBstCube3;
    private ImageView maxFceCube1;
    private ImageView maxLevCube1;
    private ImageView maxBstCube1;
    private ImageView maxFceCube2;
    private ImageView maxLevCube2;
    private ImageView maxBstCube2;
    private ImageView maxFceCube3;
    private ImageView maxLevCube3;
    private ImageView maxBstCube3;


//TODO add had auto

    private ArrayList<Integer> team_numbers;
    private TeamsRepo teamsRepo;
    private TeamInfoRepo teamInfoRepo;
    private Spinner spinnerTeams;
    private ArrayAdapter<Integer> teamAdapter;
    private int mTeamNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.min_max_2);


        TeamsRepo teamsRepo = new TeamsRepo();
        team_numbers = teamsRepo.getTeams();
        Collections.sort(team_numbers);
        spinnerTeams = (Spinner) findViewById(R.id.spinner3);
        teamAdapter = new ArrayAdapter<>(this, R.layout.spinner_base, team_numbers);
        teamAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinnerTeams.setSelection(teamAdapter.getPosition(mTeamNumber));
        spinnerTeams.setAdapter(teamAdapter);
        teamInfoRepo = new TeamInfoRepo();

        spinnerTeams.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v, int position, long arg3){
                mTeamNumber = Integer.parseInt(spinnerTeams.getItemAtPosition(position).toString());
                ArrayList<Integer> teamsHaveData = teamInfoRepo.getTeams();
                for(int i : teamsHaveData){
                    if(i == mTeamNumber){
                        TeamInfo teamInfo = teamInfoRepo.getMaxMin(mTeamNumber);
                        loadData(teamInfo);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter){

            }
        });

        maxAutoNumOwnChangesSw1 = (TextView) findViewById(R.id.maxAutoNumOwnChangesSw1Ans);
        minAutoNumOwnChangesSw1 = (TextView) findViewById(R.id.minAutoNumOwnChangesSw1Ans);
        maxAutoNumOwnChangesSw2 = (TextView) findViewById(R.id.maxAutoNumOwnChangesSw2Ans);
        minAutoNumOwnChangesSw2 = (TextView) findViewById(R.id.minAutoNumOwnChangesSw2Ans);
        maxAutoNumOwnChangesScl = (TextView) findViewById(R.id.maxAutoNumOwnChangesSclAns);
        minAutoNumOwnChangesScl = (TextView) findViewById(R.id.minAutoNumOwnChangesSclAns);
        maxAutoCubesInSw1 = (TextView) findViewById(R.id.maxAutoCubesInSw1Ans);
        minAutoCubesInSw1 = (TextView) findViewById(R.id.minAutoCubesInSw1Ans);
        maxAutoCubesInSw2 = (TextView) findViewById(R.id.maxAutoCubesInSw2Ans);
        minAutoCubesInSw2 = (TextView) findViewById(R.id.minAutoCubesInSw2Ans);
        maxAutoCubesInScl = (TextView) findViewById(R.id.maxAutoCubesInSclAns);
        minAutoCubesInScl = (TextView) findViewById(R.id.minAutoCubesInSclAns);
        maxAutoCubesInEx = (TextView) findViewById(R.id.maxAutoCubesInExAns);
        minAutoCubesInEx = (TextView) findViewById(R.id.minAutoCubesInExAns);
        maxAutoOwnGainSw1 = (TextView) findViewById(R.id.maxAutoOwnGainSw1Ans);
        minAutoOwnGainSw1 = (TextView) findViewById(R.id.minAutoOwnGainSw1Ans);
        maxAutoOwnedSw1 = (TextView) findViewById(R.id.maxAutoOwnedSw1Ans);
        minAutoOwnedSw1 = (TextView) findViewById(R.id.minAutoOwnedSw1Ans);
        maxAutoOwnGainSw2 = (TextView) findViewById(R.id.maxAutoOwnGainSw2Ans);
        minAutoOwnGainSw2 = (TextView) findViewById(R.id.minAutoOwnGainSw2Ans);
        maxAutoOwnedSw2 = (TextView) findViewById(R.id.maxAutoOwnedSw2Ans);
        minAutoOwnedSw2 = (TextView) findViewById(R.id.minAutoOwnedSw2Ans);
        maxAutoOwnGainScl = (TextView) findViewById(R.id.maxAutoOwnGainSclAns);
        minAutoOwnGainScl = (TextView) findViewById(R.id.minAutoOwnGainSclAns);
        maxAutoOwnedScl = (TextView) findViewById(R.id.maxAutoOwnedSclAns);
        minAutoOwnedScl = (TextView) findViewById(R.id.minAutoOwnedSclAns);

        maxTeleNumOwnChangesSw1 = (TextView) findViewById(R.id.maxTeleNumOwnChangesSw1Ans);
        minTeleNumOwnChangesSw1 = (TextView) findViewById(R.id.minTeleNumOwnChangesSw1Ans);
        maxTeleNumOwnChangesSw2 = (TextView) findViewById(R.id.maxTeleNumOwnChangesSw2Ans);
        minTeleNumOwnChangesSw2 = (TextView) findViewById(R.id.minTeleNumOwnChangesSw2Ans);
        maxTeleNumOwnChangesScl = (TextView) findViewById(R.id.maxTeleNumOwnChangesSclAns);
        minTeleNumOwnChangesScl = (TextView) findViewById(R.id.minTeleNumOwnChangesSclAns);
        maxTeleCubesInSw1 = (TextView) findViewById(R.id.maxTeleCubesInSw1Ans);
        minTeleCubesInSw1 = (TextView) findViewById(R.id.minTeleCubesInSw1Ans);
        maxTeleCubesInSw2 = (TextView) findViewById(R.id.maxTeleCubesInSw2Ans);
        minTeleCubesInSw2 = (TextView) findViewById(R.id.minTeleCubesInSw2Ans);
        maxTeleCubesInScl = (TextView) findViewById(R.id.maxTeleCubesInSclAns);
        minTeleCubesInScl = (TextView) findViewById(R.id.minTeleCubesInSclAns);
        maxTeleCubesInEx = (TextView) findViewById(R.id.maxTeleCubesInExAns);
        minTeleCubesInEx = (TextView) findViewById(R.id.minTeleCubesInExAns);
        maxTeleOwnGainSw1 = (TextView) findViewById(R.id.maxTeleOwnGainSw1Ans);
        minTeleOwnGainSw1 = (TextView) findViewById(R.id.minTeleOwnGainSw1Ans);
        maxTeleOwnedSw1 = (TextView) findViewById(R.id.maxTeleOwnedSw1Ans);
        minTeleOwnedSw1 = (TextView) findViewById(R.id.minTeleOwnedSw1Ans);
        maxTeleOwnGainSw2 = (TextView) findViewById(R.id.maxTeleOwnGainSw2Ans);
        minTeleOwnGainSw2 = (TextView) findViewById(R.id.minTeleOwnGainSw2Ans);
        maxTeleOwnedSw2 = (TextView) findViewById(R.id.maxTeleOwnedSw2Ans);
        minTeleOwnedSw2 = (TextView) findViewById(R.id.minTeleOwnedSw2Ans);
        maxTeleOwnGainScl = (TextView) findViewById(R.id.maxTeleOwnGainSclAns);
        minTeleOwnGainScl = (TextView) findViewById(R.id.minTeleOwnGainSclAns);
        maxTeleOwnedScl = (TextView) findViewById(R.id.maxTeleOwnedSclAns);
        minTeleOwnedScl = (TextView) findViewById(R.id.minTeleOwnedSclAns);

        maxNumCubesInVault = (TextView) findViewById(R.id.maxNumCubesInVaultAns);
        minNumCubesInVault = (TextView) findViewById(R.id.minNumCubesInVaultAns);
        maxActiveFceTime = (TextView) findViewById(R.id.maxActiveFceTimeAns);
        minActiveFceTime = (TextView) findViewById(R.id.minActiveFceTimeAns);
        maxActiveLevTime = (TextView) findViewById(R.id.maxActiveLevTimeAns);
        minActiveLevTime = (TextView) findViewById(R.id.minActiveLevTimeAns);
        maxActiveBstTime = (TextView) findViewById(R.id.maxActiveBstTimeAns);
        minActiveBstTime = (TextView) findViewById(R.id.minActiveBstTimeAns);
        minFceCube1 = (ImageView) findViewById(R.id.minFceCube1);
        minLevCube1 = (ImageView) findViewById(R.id.minLevCube1);
        minBstCube1 = (ImageView) findViewById(R.id.minBstCube1);
        minFceCube2 = (ImageView) findViewById(R.id.minFceCube2);
        minLevCube2 = (ImageView) findViewById(R.id.minLevCube2);
        minBstCube2 = (ImageView) findViewById(R.id.minBstCube2);
        minFceCube3 = (ImageView) findViewById(R.id.minFceCube3);
        minLevCube3 = (ImageView) findViewById(R.id.minLevCube3);
        minBstCube3 = (ImageView) findViewById(R.id.minBstCube3);

        maxFceCube1 = (ImageView) findViewById(R.id.maxFceCube1);
        maxLevCube1 = (ImageView) findViewById(R.id.maxLevCube1);
        maxBstCube1 = (ImageView) findViewById(R.id.maxBstCube1);
        maxFceCube2 = (ImageView) findViewById(R.id.maxFceCube2);
        maxLevCube2 = (ImageView) findViewById(R.id.maxLevCube2);
        maxBstCube2 = (ImageView) findViewById(R.id.maxBstCube2);
        maxFceCube3 = (ImageView) findViewById(R.id.maxFceCube3);
        maxLevCube3 = (ImageView) findViewById(R.id.maxLevCube3);
        maxBstCube3 = (ImageView) findViewById(R.id.maxBstCube3);


    }
    private void loadData(TeamInfo teamInfo) {
        int maxanocSw1 = teamInfo.getMaxAutoNumOwnChangesSw1();
        maxAutoNumOwnChangesSw1.setText(maxanocSw1 + "");
        int minanocSw1 = teamInfo.getMinAutoNumOwnChangesSw1();
        minAutoNumOwnChangesSw1.setText(minanocSw1 + "");
        int maxanocSw2 = teamInfo.getMaxAutoNumOwnChangesSw2();
        maxAutoNumOwnChangesSw2.setText(maxanocSw2 + "");
        int minanocSw2 = teamInfo.getMinAutoNumOwnChangesSw2();
        minAutoNumOwnChangesSw2.setText(minanocSw2 + "");
        int maxanocScl = teamInfo.getMaxAutoNumOwnChangesScl();
        maxAutoNumOwnChangesScl.setText(maxanocScl + "");
        int minanocScl = teamInfo.getMinAutoNumOwnChangesScl();
        minAutoNumOwnChangesScl.setText(minanocScl + "");
        int maxaciSw1 = teamInfo.getMaxAutoCubesInSw1();
        maxAutoCubesInSw1.setText(maxaciSw1 + "");
        int minaciSw1 = teamInfo.getMinAutoCubesInSw1();
        minAutoCubesInSw1.setText(minaciSw1 + "");
        int maxaciSw2 = teamInfo.getMaxAutoCubesInSw2();
        maxAutoCubesInSw2.setText(maxaciSw2 + "");
        int minaciSw2 = teamInfo.getMinAutoCubesInSw2();
        minAutoCubesInSw2.setText(minaciSw2 + "");
        int maxaciScl = teamInfo.getMaxAutoCubesInScl();
        maxAutoCubesInScl.setText(maxaciScl + "");
        int minaciScl = teamInfo.getMinAutoCubesInScl();
        minAutoCubesInScl.setText(minaciScl + "");
        int maxaciEx = teamInfo.getMaxAutoCubesInEx();
        maxAutoCubesInEx.setText(maxaciEx + "");
        int minaciEx = teamInfo.getMinAutoCubesInEx();
        minAutoCubesInEx.setText(minaciEx + "");
        int maxaogSw1 = teamInfo.getMaxAutoOwnGainSw1();
        maxAutoOwnGainSw1.setText(maxaogSw1 + "");
        int minaogSw1 = teamInfo.getMinAutoOwnGainSw1();
        minAutoOwnGainSw1.setText(minaogSw1 + "");
        int maxaoSw1 = teamInfo.getMaxAutoOwnedSw1();
        maxAutoOwnedSw1.setText(maxaoSw1 + "");
        int minaoSw1 = teamInfo.getMinAutoOwnedSw1();
        minAutoOwnedSw1.setText(minaoSw1 + "");
        int maxaogSw2 = teamInfo.getMaxAutoOwnGainSw2();
        maxAutoOwnGainSw2.setText(maxaogSw2 + "");
        int minaogSw2 = teamInfo.getMinAutoOwnGainSw2();
        minAutoOwnGainSw2.setText(minaogSw2 + "");
        int maxaoSw2 = teamInfo.getMaxAutoOwnedSw2();
        maxAutoOwnedSw2.setText(maxaoSw2 + "");
        int minaoSw2 = teamInfo.getMinAutoOwnedSw2();
        minAutoOwnedSw2.setText(minaoSw2 + "");
        int maxaogScl = teamInfo.getMaxAutoOwnGainScl();
        maxAutoOwnGainScl.setText(maxaogScl + "");
        int minaogScl = teamInfo.getMinAutoOwnGainScl();
        minAutoOwnGainScl.setText(minaogScl + "");
        int maxaoScl = teamInfo.getMaxAutoOwnedScl();
        maxAutoOwnedScl.setText(maxaoScl + "");
        int minaoScl = teamInfo.getMinAutoOwnedScl();
        minAutoOwnedScl.setText(minaoScl + "");
        int maxtnocSw1 = teamInfo.getMaxTeleNumOwnChangesSw1();
        maxTeleNumOwnChangesSw1.setText(maxtnocSw1 + "");
        int mintnocSw1 = teamInfo.getMinTeleNumOwnChangesSw1();
        minTeleNumOwnChangesSw1.setText(mintnocSw1 + "");
        int maxtnocSw2 = teamInfo.getMaxTeleNumOwnChangesSw2();
        maxTeleNumOwnChangesSw2.setText(maxtnocSw2 + "");
        int mintnocSw2 = teamInfo.getMinTeleNumOwnChangesSw2();
        minTeleNumOwnChangesSw2.setText(mintnocSw2 + "");
        int maxtnocScl = teamInfo.getMaxTeleNumOwnChangesScl();
        maxTeleNumOwnChangesScl.setText(maxtnocScl + "");
        int mintnocScl = teamInfo.getMinTeleNumOwnChangesScl();
        minTeleNumOwnChangesScl.setText(mintnocScl + "");
        int maxtciSw1 = teamInfo.getMaxTeleCubesInSw1();
        maxTeleCubesInSw1.setText(maxtciSw1 + "");
        int mintciSw1 = teamInfo.getMinTeleCubesInSw1();
        minTeleCubesInSw1.setText(mintciSw1 + "");
        int maxtciSw2 = teamInfo.getMaxTeleCubesInSw2();
        maxTeleCubesInSw2.setText(maxtciSw2 + "");
        int mintciSw2 = teamInfo.getMinTeleCubesInSw2();
        minTeleCubesInSw2.setText(mintciSw2 + "");
        int maxtciScl = teamInfo.getMaxTeleCubesInScl();
        maxTeleCubesInScl.setText(maxtciScl + "");
        int mintciScl = teamInfo.getMinTeleCubesInScl();
        minTeleCubesInScl.setText(mintciScl + "");
        int maxtciEx = teamInfo.getMaxTeleCubesInEx();
        maxTeleCubesInEx.setText(maxtciEx + "");
        int mintciEx = teamInfo.getMinTeleCubesInEx();
        minTeleCubesInEx.setText(mintciEx + "");
        int maxtogSw1 = teamInfo.getMaxTeleOwnGainSw1();
        maxTeleOwnGainSw1.setText(maxtogSw1 + "");
        int mintogSw1 = teamInfo.getMinTeleOwnGainSw1();
        minTeleOwnGainSw1.setText(mintogSw1 + "");
        int maxtoSw1 = teamInfo.getMaxTeleOwnedSw1();
        maxTeleOwnedSw1.setText(maxtoSw1 + "");
        int mintoSw1 = teamInfo.getMinTeleOwnedSw1();
        minTeleOwnedSw1.setText(mintoSw1 + "");
        int maxtogSw2 = teamInfo.getMaxTeleOwnGainSw2();
        maxTeleOwnGainSw2.setText(maxtogSw2 + "");
        int mintogSw2 = teamInfo.getMinTeleOwnGainSw2();
        minTeleOwnGainSw2.setText(mintogSw2 + "");
        int maxtoSw2 = teamInfo.getMaxTeleOwnedSw2();
        maxTeleOwnedSw2.setText(maxtoSw2 + "");
        int mintoSw2 = teamInfo.getMinTeleOwnedSw2();
        minTeleOwnedSw2.setText(mintoSw2 + "");
        int maxtogScl = teamInfo.getMaxTeleOwnGainScl();
        maxTeleOwnGainScl.setText(maxtogScl + "");
        int mintogScl = teamInfo.getMinTeleOwnGainScl();
        minTeleOwnGainScl.setText(mintogScl + "");
        int maxtoScl = teamInfo.getMaxTeleOwnedScl();
        maxTeleOwnedScl.setText(maxtoScl + "");
        int mintoScl = teamInfo.getMinTeleOwnedScl();
        minTeleOwnedScl.setText(mintoScl + "");
        int maxnciv = teamInfo.getMaxNumCubesInVault();
        maxNumCubesInVault.setText(maxnciv + "");
        int minnciv = teamInfo.getMinNumCubesInVault();
        minNumCubesInVault.setText(minnciv + "");
        int maxaFcet = teamInfo.getMaxActiveFceTime();
        maxActiveFceTime.setText(maxaFcet + "");
        int minaFcet = teamInfo.getMinActiveFceTime();
        minActiveFceTime.setText(minaFcet + "");
        int maxaLevt = teamInfo.getMaxActiveLevTime();
        maxActiveLevTime.setText(maxaLevt + "");
        int minaLevt = teamInfo.getMinActiveLevTime();
        minActiveLevTime.setText(minaLevt + "");
        int maxaBstt = teamInfo.getMaxActiveBstTime();
        maxActiveBstTime.setText(maxaBstt + "");
        int minaBstt = teamInfo.getMinActiveBstTime();
        minActiveBstTime.setText(minaBstt + "");
        int maxcaaFce = teamInfo.getMaxCubesAtActiveFce();
        maxCubesAtActiveFce.setText(maxcaaFce + "");
        int mincaaFce = teamInfo.getMinCubesAtActiveFce();
        minCubesAtActiveFce.setText(mincaaFce + "");
        int maxcaaLev = teamInfo.getMaxCubesAtActiveLev();
        maxCubesAtActiveLev.setText(maxcaaLev + "");
        int mincaaLev = teamInfo.getMinCubesAtActiveLev();
        minCubesAtActiveLev.setText(mincaaLev + "");
        int maxcaaBst = teamInfo.getMaxCubesAtActiveBst();
        maxCubesAtActiveBst.setText(maxcaaBst + "");
        int mincaaBst = teamInfo.getMinCubesAtActiveBst();
        minCubesAtActiveBst.setText(mincaaBst + "");

        double mincubesAtActiveFce = teamInfo.getMinCubesAtActiveFce();
        if(mincubesAtActiveFce >= 0 && mincubesAtActiveFce <=3){
            if(mincubesAtActiveFce >=.5 && mincubesAtActiveFce <= 1.4){
                minFceCube1.setImageResource(R.drawable.yellowbox);
            }
            if(mincubesAtActiveFce >= 1.5 && mincubesAtActiveFce >= 2.4){
                minFceCube1.setImageResource(R.drawable.yellowbox);
                minFceCube2.setImageResource(R.drawable.yellowbox);
            }
            if(mincubesAtActiveFce >= 2.5&& mincubesAtActiveFce >= 3){
                minFceCube1.setImageResource(R.drawable.yellowbox);
                minFceCube2.setImageResource(R.drawable.yellowbox);
                minFceCube3.setImageResource(R.drawable.yellowbox);
            }
        }
        double mincubesAtActiveLev = teamInfo.getMinCubesAtActiveLev();
        if(mincubesAtActiveLev >= 0 && mincubesAtActiveLev <=3){
            if(mincubesAtActiveLev >=.5 && mincubesAtActiveLev <= 1.4){
                minLevCube1.setImageResource(R.drawable.yellowbox);
            }
            if(mincubesAtActiveLev >= 1.5 && mincubesAtActiveLev >= 2.4){
                minLevCube1.setImageResource(R.drawable.yellowbox);
                minLevCube2.setImageResource(R.drawable.yellowbox);
            }
            if(mincubesAtActiveLev >= 2.5&& mincubesAtActiveLev >= 3){
                minLevCube1.setImageResource(R.drawable.yellowbox);
                minLevCube2.setImageResource(R.drawable.yellowbox);
                minLevCube3.setImageResource(R.drawable.yellowbox);
            }
        }
        double mincubesAtActiveBst = teamInfo.getMinCubesAtActiveBst();
        if(mincubesAtActiveBst >= 0 && mincubesAtActiveBst <=3){
            if(mincubesAtActiveBst >=.5 && mincubesAtActiveBst <= 1.4){
                minBstCube1.setImageResource(R.drawable.yellowbox);
            }
            if(mincubesAtActiveBst >= 1.5 && mincubesAtActiveBst >= 2.4){
                minBstCube1.setImageResource(R.drawable.yellowbox);
                minBstCube2.setImageResource(R.drawable.yellowbox);
            }
            if(mincubesAtActiveBst >= 2.5&& mincubesAtActiveBst >= 3){
                minBstCube1.setImageResource(R.drawable.yellowbox);
                minBstCube2.setImageResource(R.drawable.yellowbox);
                minBstCube3.setImageResource(R.drawable.yellowbox);
            }
        }
        double maxcubesAtActiveFce = teamInfo.getMaxCubesAtActiveFce();
        if(maxcubesAtActiveFce >= 0 && maxcubesAtActiveFce <=3){
            if(maxcubesAtActiveFce >=.5 && maxcubesAtActiveFce <= 1.4){
                maxFceCube1.setImageResource(R.drawable.yellowbox);
            }
            if(maxcubesAtActiveFce >= 1.5 && maxcubesAtActiveFce >= 2.4){
                maxFceCube1.setImageResource(R.drawable.yellowbox);
                maxFceCube2.setImageResource(R.drawable.yellowbox);
            }
            if(maxcubesAtActiveFce >= 2.5&& maxcubesAtActiveFce >= 3){
                maxFceCube1.setImageResource(R.drawable.yellowbox);
                maxFceCube2.setImageResource(R.drawable.yellowbox);
                maxFceCube3.setImageResource(R.drawable.yellowbox);
            }
        }
        double maxcubesAtActiveLev = teamInfo.getMaxCubesAtActiveLev();
        if(maxcubesAtActiveLev >= 0 && maxcubesAtActiveLev <=3){
            if(maxcubesAtActiveLev >=.5 && maxcubesAtActiveLev <= 1.4){
                maxLevCube1.setImageResource(R.drawable.yellowbox);
            }
            if(maxcubesAtActiveLev >= 1.5 && maxcubesAtActiveLev >= 2.4){
                maxLevCube1.setImageResource(R.drawable.yellowbox);
                maxLevCube2.setImageResource(R.drawable.yellowbox);
            }
            if(maxcubesAtActiveLev >= 2.5&& maxcubesAtActiveLev >= 3){
                maxLevCube1.setImageResource(R.drawable.yellowbox);
                maxLevCube2.setImageResource(R.drawable.yellowbox);
                maxLevCube3.setImageResource(R.drawable.yellowbox);
            }
        }
        double maxcubesAtActiveBst = teamInfo.getMaxCubesAtActiveBst();
        if(maxcubesAtActiveBst >= 0 && maxcubesAtActiveBst <=3){
            if(maxcubesAtActiveBst >=.5 && maxcubesAtActiveBst <= 1.4){
                maxBstCube1.setImageResource(R.drawable.yellowbox);
            }
            if(maxcubesAtActiveBst >= 1.5 && maxcubesAtActiveBst >= 2.4){
                maxBstCube1.setImageResource(R.drawable.yellowbox);
                maxBstCube2.setImageResource(R.drawable.yellowbox);
            }
            if(maxcubesAtActiveBst >= 2.5&& maxcubesAtActiveBst >= 3){
                maxBstCube1.setImageResource(R.drawable.yellowbox);
                maxBstCube2.setImageResource(R.drawable.yellowbox);
                maxBstCube3.setImageResource(R.drawable.yellowbox);
            }
        }
    }

}
