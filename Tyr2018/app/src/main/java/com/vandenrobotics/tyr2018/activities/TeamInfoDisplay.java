package com.vandenrobotics.tyr2018.activities;

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

public class TeamInfoDisplay extends AppCompatActivity {

    private TextView numMatch;
    private TextView pNoShow;
    private TextView pHadAuto;
    private TextView pCrossAutoLine;
    private TextView pAutoPickedUpCube;
    private TextView avgAutoCubesInSw1;
    private TextView avgAutoCubesInSw2;
    private TextView avgAutoCubesInScl;
    private TextView avgAutoCubesInEx;
    private TextView avgAutoOwnGainSw1;
    private TextView avgAutoOwnedSw1;
    private TextView avgAutoOwnGainSw2;
    private TextView avgAutoOwnedSw2;
    private TextView avgAutoOwnGainScl;
    private TextView avgAutoOwnedScl;
    private TextView avgAutoNumOwnChangesSw1;
    private TextView avgAutoNumOwnChangesSw2;
    private TextView avgAutoNumOwnChangesScl;
    private TextView pTelePickedUpCube;
    private TextView avgTeleCubesInSw1;
    private TextView avgTeleCubesInSw2;
    private TextView avgTeleCubesInScl;
    private TextView avgTeleCubesInEx;
    private TextView avgTeleOwnGainSw1;
    private TextView avgTeleOwnedSw1;
    private TextView avgTeleOwnGainSw2;
    private TextView avgTeleOwnedSw2;
    private TextView avgTeleOwnGainScl;
    private TextView avgTeleOwnedScl;
    private TextView avgTeleNumOwnChangesSw1;
    private TextView avgTeleNumOwnChangesSw2;
    private TextView avgTeleNumOwnChangesScl;
    private TextView pClimb;
    private TextView pClimbAssist;
    private TextView pParking;
    private TextView pHumanPlayer;
    private TextView pTechFoul;
    private TextView pFoul;
    private TextView pYellowCard;
    private TextView pRedCard;
    private TextView pDisabled;
    private TextView avgNumCubesInVault;
    private TextView avgActiveFceTime;
    private TextView avgActiveLevTime;
    private TextView avgActiveBstTime;
    private ImageView fceCube1;
    private ImageView levCube1;
    private ImageView bstCube1;
    private ImageView fceCube2;
    private ImageView levCube2;
    private ImageView bstCube2;
    private ImageView fceCube3;
    private ImageView levCube3;
    private ImageView bstCube3;
    private TextView negWs;
    private TextView totalWs;
    private TextView offensiveWs;
    private TextView defensiveWs;
    private TextView autoWs;
    private TextView teleWs;
    private TextView hadFoul;


    private ArrayList<Integer> team_numbers;
    private TeamsRepo teamsRepo;
    private TeamInfoRepo teamInfoRepo;
    private Spinner spinnerTeams;
    private ArrayAdapter<Integer> teamAdapter;
    private int mTeamNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info2);


        TeamsRepo teamsRepo = new TeamsRepo();
        team_numbers = teamsRepo.getTeams();
        Collections.sort(team_numbers);
        spinnerTeams = (Spinner) findViewById(R.id.spinner);
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
                        TeamInfo teamInfo = teamInfoRepo.getTeamInfo(mTeamNumber);
                        loadData(teamInfo);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter){

            }
        });

        numMatch = (TextView) findViewById(R.id.teamInfo_numMatchAns);
        pNoShow = (TextView) findViewById(R.id.teamInfo_pNoShowAns);
        offensiveWs = (TextView) findViewById(R.id.teamInfo_AvgOffAns);
        defensiveWs = (TextView) findViewById(R.id.teamInfo_AvgDefAns);
        totalWs = (TextView) findViewById(R.id.teamInfo_AvgTotalAns);
        negWs = (TextView) findViewById(R.id.teamInfo_AvgNegAns);
        autoWs = (TextView) findViewById(R.id.teamInfo_autoAns);
        pHadAuto = (TextView) findViewById(R.id.teamInfo_pHadAutoAns);
        pCrossAutoLine = (TextView) findViewById(R.id.teamInfo_pCrossAutoLineAns);
        pAutoPickedUpCube = (TextView) findViewById(R.id.teamInfo_pAutoPickedUpCubeAns);
        avgAutoCubesInSw1 = (TextView) findViewById(R.id.teamInfo_avgAutoCubesInSw1Ans);
        avgAutoCubesInSw2 = (TextView) findViewById(R.id.teamInfo_avgAutoCubesInSw2Ans);
        avgAutoCubesInScl = (TextView) findViewById(R.id.teamInfo_avgAutoCubesInSclAns);
        avgAutoCubesInEx = (TextView) findViewById(R.id.teamInfo_avgAutoCubesInExAns);
        avgAutoOwnGainSw1 = (TextView) findViewById(R.id.teamInfo_avgAutoOwnGainSw1Ans);
        avgAutoOwnedSw1 = (TextView) findViewById(R.id.teamInfo_avgAutoOwnedSw1Ans);
        avgAutoOwnGainSw2 = (TextView) findViewById(R.id.teamInfo_avgAutoOwnGainSw2Ans);
        avgAutoOwnedSw2 = (TextView) findViewById(R.id.teamInfo_avgAutoOwnedSw2Ans);
        avgAutoOwnGainScl = (TextView) findViewById(R.id.teamInfo_avgAutoOwnGainSclAns);
        avgAutoOwnedScl = (TextView) findViewById(R.id.teamInfo_avgAutoOwnedSclAns);
        avgAutoNumOwnChangesSw1 = (TextView) findViewById(R.id.teamInfo_avgAutoNumOwnChangeSw1Ans);
        avgAutoNumOwnChangesSw2 = (TextView) findViewById(R.id.teamInfo_avgAutoNumOwnChangeSw2Ans);
        avgAutoNumOwnChangesScl = (TextView) findViewById(R.id.teamInfo_avgAutoNumOwnChangeSclAns);
        teleWs = (TextView) findViewById(R.id.teamInfo_teleAns);
        pTelePickedUpCube = (TextView) findViewById(R.id.teamInfo_pTelePickedUpCubeAns);
        avgTeleCubesInSw1 = (TextView) findViewById(R.id.teamInfo_avgTeleCubesInSw1Ans);
        avgTeleCubesInSw2 = (TextView) findViewById(R.id.teamInfo_avgTeleCubesInSw2Ans);
        avgTeleCubesInScl = (TextView) findViewById(R.id.teamInfo_avgTeleCubesInSclAns);
        avgTeleCubesInEx = (TextView) findViewById(R.id.teamInfo_avgTeleCubesInExAns);
        avgTeleOwnGainSw1 = (TextView) findViewById(R.id.teamInfo_avgTeleOwnGainSw1Ans);
        avgTeleOwnedSw1 = (TextView) findViewById(R.id.teamInfo_avgTeleOwnedSw1Ans);
        avgTeleOwnGainSw2 = (TextView) findViewById(R.id.teamInfo_avgTeleOwnGainSw2Ans);
        avgTeleOwnedSw2 = (TextView) findViewById(R.id.teamInfo_avgTeleOwnedSw2Ans);
        avgTeleOwnGainScl = (TextView) findViewById(R.id.teamInfo_avgTeleOwnGainSclAns);
        avgTeleOwnedScl = (TextView) findViewById(R.id.teamInfo_avgTeleOwnedSclAns);
        avgTeleNumOwnChangesSw1 = (TextView) findViewById(R.id.teamInfo_avgTeleNumOwnChangeSw1Ans);
        avgTeleNumOwnChangesSw2 = (TextView) findViewById(R.id.teamInfo_avgTeleNumOwnChangeSw2Ans);
        avgTeleNumOwnChangesScl = (TextView) findViewById(R.id.teamInfo_avgTeleNumOwnChangeSclAns);
        pClimb = (TextView) findViewById(R.id.teamInfo_pClimbAns);
        pClimbAssist = (TextView) findViewById(R.id.teamInfo_pClimbAssistAns);
        pParking = (TextView) findViewById(R.id.teamInfo_pParkingAns);
        pHumanPlayer = (TextView) findViewById(R.id.teamInfo_pHumanPlayerAns);
        pTechFoul = (TextView) findViewById(R.id.teamInfo_pTechFoulAns);
        pFoul = (TextView) findViewById(R.id.teamInfo_pFoulAns);
        pYellowCard = (TextView) findViewById(R.id.teamInfo_pYellowCardAns);
        pRedCard = (TextView) findViewById(R.id.teamInfo_pRedCardAns);
        pDisabled = (TextView) findViewById(R.id.teamInfo_pDisabledAns);
        avgNumCubesInVault = (TextView) findViewById(R.id.teamInfo_avgNumCubesInVaultAns);
        avgActiveFceTime = (TextView) findViewById(R.id.teamInfo_avgActiveFceTimeAns);
        avgActiveLevTime = (TextView) findViewById(R.id.teamInfo_avgActiveLevTimeAns);
        avgActiveBstTime = (TextView) findViewById(R.id.teamInfo_avgActiveBstTimeAns);
        fceCube1 = (ImageView) findViewById(R.id.fceCube1);
        levCube1 = (ImageView) findViewById(R.id.levCube1);
        bstCube1 = (ImageView) findViewById(R.id.bstCube1);
        fceCube2 = (ImageView) findViewById(R.id.fceCube2);
        levCube2 = (ImageView) findViewById(R.id.levCube2);
        bstCube2 = (ImageView) findViewById(R.id.bstCube2);
        fceCube3 = (ImageView) findViewById(R.id.fceCube3);
        levCube3 = (ImageView) findViewById(R.id.levCube3);
        bstCube3 = (ImageView) findViewById(R.id.bstCube3);
        hadFoul = (TextView) findViewById(R.id.teamInfo_hadFoulAns);
        fceCube1.setImageResource(R.drawable.greybox);
        fceCube2.setImageResource(R.drawable.greybox);
        fceCube3.setImageResource(R.drawable.greybox);
        levCube1.setImageResource(R.drawable.greybox);
        levCube2.setImageResource(R.drawable.greybox);
        levCube3.setImageResource(R.drawable.greybox);
        bstCube1.setImageResource(R.drawable.greybox);
        bstCube2.setImageResource(R.drawable.greybox);
        bstCube3.setImageResource(R.drawable.greybox);

    }
    private void loadData(TeamInfo teamInfo) {

        double nm = teamInfo.getNumMatch();
        numMatch.setText(nm + "");
        double noShow = teamInfo.getPNoShow();
        pNoShow.setText(noShow + "%");
        double tWS = teamInfo.getTotalWS();
        totalWs.setText(tWS + "");
        double nWS = teamInfo.getNegWS();
        negWs.setText(nWS + "");
        double oWS = teamInfo.getOffensiveWS();
        offensiveWs.setText(oWS + "");
        double dWS = teamInfo.getDefensiveWS();
        defensiveWs.setText(dWS + "");
        double aWS = teamInfo.getAutoWS();
        autoWs.setText(aWS + "");
        double hadAuto = teamInfo.getPHadAuto();
        pHadAuto.setText(hadAuto + "%");
        double crossAL = teamInfo.getPCrossAutoLine();
        pCrossAutoLine.setText(crossAL + "%");
        double autoPickUpCube = teamInfo.getPAutoPickedUpCube();
        pAutoPickedUpCube.setText(autoPickUpCube + "%");
        double autoCubesInSw1 = teamInfo.getAvgAutoCubesInSw1();
        avgAutoCubesInSw1.setText(autoCubesInSw1 + "");
        double autoCubesInSw2 = teamInfo.getAvgAutoCubesInSw2();
        avgAutoCubesInSw2.setText(autoCubesInSw2 + "");
        double autoCubesInScl = teamInfo.getAvgAutoCubesInScl();
        avgAutoCubesInScl.setText(autoCubesInScl + "");
        double autoCubesInEx = teamInfo.getAvgAutoCubesInEx();
        avgAutoCubesInEx.setText(autoCubesInEx + "");
        double autoOwnGainSw1 = teamInfo.getAvgAutoOwnGainSw1();
        avgAutoOwnGainSw1.setText(autoOwnGainSw1 + "");
        double autoOwnedSw1 = teamInfo.getAvgAutoOwnedSw1();
        avgAutoOwnedSw1.setText(autoOwnedSw1 + "");
        double autoOwnGainSw2 = teamInfo.getAvgAutoOwnGainSw2();
        avgAutoOwnGainSw2.setText(autoOwnGainSw2 + "");
        double autoOwnedSw2 = teamInfo.getAvgAutoOwnedSw2();
        avgAutoOwnedSw2.setText(autoOwnedSw2 + "");
        double autoOwnGainScl = teamInfo.getAvgAutoOwnGainScl();
        avgAutoOwnGainScl.setText(autoOwnGainScl + "");
        double autoOwnedScl = teamInfo.getAvgAutoOwnedScl();
        avgAutoOwnedScl.setText(autoOwnedScl + "");
        double autoNumOwnChangesSw1 = teamInfo.getAvgAutoNumOwnChangesSw1();
        avgAutoNumOwnChangesSw1.setText(autoNumOwnChangesSw1 + "");
        double autoNumOwnChangesSw2 = teamInfo.getAvgAutoNumOwnChangesSw2();
        avgAutoNumOwnChangesSw2.setText(autoNumOwnChangesSw2 + "");
        double autoNumOwnChangesScl = teamInfo.getAvgAutoNumOwnChangesScl();
        avgAutoNumOwnChangesScl.setText(autoNumOwnChangesScl + "");
        double telWS = teamInfo.getTeleWS();
        teleWs.setText(telWS + "");
        double telePickUpCube = teamInfo.getPTelePickedUpCube();
        pTelePickedUpCube.setText(telePickUpCube + "%");
        double teleCubesInSw1 = teamInfo.getAvgTeleCubesInSw1();
        avgTeleCubesInSw1.setText(teleCubesInSw1 + "");
        double teleCubesInSw2 = teamInfo.getAvgTeleCubesInSw2();
        avgTeleCubesInSw2.setText(teleCubesInSw2 + "");
        double teleCubesInScl = teamInfo.getAvgTeleCubesInScl();
        avgTeleCubesInScl.setText(teleCubesInScl + "");
        double teleCubesInEx = teamInfo.getAvgTeleCubesInEx();
        avgTeleCubesInEx.setText(teleCubesInEx + "");
        double teleOwnGainSw1 = teamInfo.getAvgTeleOwnGainSw1();
        avgTeleOwnGainSw1.setText(teleOwnGainSw1 + "");
        double teleOwnedSw1 = teamInfo.getAvgTeleOwnedSw1();
        avgTeleOwnedSw1.setText(teleOwnedSw1 + "");
        double teleOwnGainSw2 = teamInfo.getAvgTeleOwnGainSw2();
        avgTeleOwnGainSw2.setText(teleOwnGainSw2 + "");
        double teleOwnedSw2 = teamInfo.getAvgTeleOwnedSw2();
        avgTeleOwnedSw2.setText(teleOwnedSw2 + "");
        double teleOwnGainScl = teamInfo.getAvgTeleOwnGainScl();
        avgTeleOwnGainScl.setText(teleOwnGainScl + "");
        double teleOwnedScl = teamInfo.getAvgTeleOwnedScl();
        avgTeleOwnedScl.setText(teleOwnedScl + "");
        double teleNumOwnChangesSw1 = teamInfo.getAvgTeleNumOwnChangesSw1();
        avgTeleNumOwnChangesSw1.setText(teleNumOwnChangesSw1 + "");
        double teleNumOwnChangesSw2 = teamInfo.getAvgTeleNumOwnChangesSw2();
        avgTeleNumOwnChangesSw2.setText(teleNumOwnChangesSw2 + "");
        double teleNumOwnChangesScl = teamInfo.getAvgTeleNumOwnChangesScl();
        avgTeleNumOwnChangesScl.setText(teleNumOwnChangesScl + "");
        double climb = teamInfo.getPClimb();
        pClimb.setText(climb + "%");
        double climbAssist = teamInfo.getPClimbAssist();
        pClimbAssist.setText(climbAssist + "%");
        double parking = teamInfo.getPParking();
        pParking.setText(parking + "%");
        double humanPlayer = teamInfo.getPHumanPlayer();
        pHumanPlayer.setText(humanPlayer + "%");
        double techFoul = teamInfo.getPTechFoul();
        pTechFoul.setText(techFoul + "%");
        double foul = teamInfo.getPFoul();
        pFoul.setText(foul + "%");
        double yellowCard = teamInfo.getPYellowCard();
        pYellowCard.setText(yellowCard + "%");
        double redCard = teamInfo.getPRedCard();
        pRedCard.setText(redCard + "%");
        double disabled = teamInfo.getPDisabled();
        pDisabled.setText(disabled + "%");
        double numCubesInVault= teamInfo.getAvgNumCubesInVault();
        avgNumCubesInVault.setText(numCubesInVault + "");
        double activeFceTime = teamInfo.getAvgActiveFceTime();
        avgActiveFceTime.setText(activeFceTime + "");
        double activeLevTime = teamInfo.getAvgActiveLevTime();
        avgActiveLevTime.setText(activeLevTime + "");
        double activeBstTime = teamInfo.getAvgActiveBstTime();
        avgActiveBstTime.setText(activeBstTime + "");
        double cubesAtActiveFce = teamInfo.getAvgCubesAtActiveFce();
        if(cubesAtActiveFce >= 0 && cubesAtActiveFce <=3){
            if(cubesAtActiveFce >=.5 && cubesAtActiveFce <= 1.4){
                fceCube1.setImageResource(R.drawable.yellowbox);
            }
            if(cubesAtActiveFce >= 1.5 && cubesAtActiveFce >= 2.4){
                fceCube1.setImageResource(R.drawable.yellowbox);
                fceCube2.setImageResource(R.drawable.yellowbox);
            }
            if(cubesAtActiveFce >= 2.5&& cubesAtActiveFce >= 3){
                fceCube1.setImageResource(R.drawable.yellowbox);
                fceCube2.setImageResource(R.drawable.yellowbox);
                fceCube3.setImageResource(R.drawable.yellowbox);
            }
        }
        double cubesAtActiveLev = teamInfo.getAvgCubesAtActiveLev();
        if(cubesAtActiveLev >= 0 && cubesAtActiveLev <=3){
            if(cubesAtActiveLev >=.5 && cubesAtActiveLev <= 1.4){
                levCube1.setImageResource(R.drawable.yellowbox);
            }
            if(cubesAtActiveLev >= 1.5 && cubesAtActiveLev >= 2.4){
                levCube1.setImageResource(R.drawable.yellowbox);
                levCube2.setImageResource(R.drawable.yellowbox);
            }
            if(cubesAtActiveLev >= 2.5&& cubesAtActiveLev >= 3){
                levCube1.setImageResource(R.drawable.yellowbox);
                levCube2.setImageResource(R.drawable.yellowbox);
                levCube3.setImageResource(R.drawable.yellowbox);
            }
        }
        double cubesAtActiveBst = teamInfo.getAvgCubesAtActiveBst();
        if(cubesAtActiveBst >= 0 && cubesAtActiveBst <=3){
            if(cubesAtActiveBst >=.5 && cubesAtActiveBst <= 1.4){
                bstCube1.setImageResource(R.drawable.yellowbox);
            }
            if(cubesAtActiveBst >= 1.5 && cubesAtActiveBst >= 2.4){
                bstCube1.setImageResource(R.drawable.yellowbox);
                bstCube2.setImageResource(R.drawable.yellowbox);
            }
            if(cubesAtActiveBst >= 2.5&& cubesAtActiveBst >= 3){
                bstCube1.setImageResource(R.drawable.yellowbox);
                bstCube2.setImageResource(R.drawable.yellowbox);
                bstCube3.setImageResource(R.drawable.yellowbox);
            }
        }
        double hf = teamInfo.getHadFoul();
        hadFoul.setText(hf + "");

}
}
