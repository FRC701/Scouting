package com.vandenrobotics.tyr2018.activities;

import android.app.Activity;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
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

public class TeamInfoDisplay extends Activity {

    private TextView numMatch;
    private TextView offWS;
    private TextView totalWS;
    private TextView negWS;
    private TextView pNoShow;
    private TextView pStartLevel1;
    private TextView pStartLevel2;
    private TextView pPreloadC;
    private TextView pPreloadH;
    private TextView pCrossHubline;
    private TextView avgRocketTopC;
    private TextView avgRocketTopH;
    private TextView avgRocketMiddleC;
    private TextView avgRocketMiddleH;
    private TextView avgRocketBottomC;
    private TextView avgRocketBottomH;
    private TextView avgCargoC;
    private TextView avgCargoH;
    private TextView pEndLevel1;
    private TextView pEndLevel2;
    private TextView pEndLevel3;
    private TextView pEndNone;
    private TextView maxTopC;
    private TextView minTopC;
    private TextView maxTopH;
    private TextView minTopH;
    private TextView maxMidC;
    private TextView minMidC;
    private TextView maxMidH;
    private TextView minMidH;
    private TextView maxBotC;
    private TextView minBotC;
    private TextView maxBotH;
    private TextView minBotH;
    private TextView maxCargoC;
    private TextView minCargoC;
    private TextView maxCargoH;
    private TextView minCargoH;
    private TextView pTechFoul;
    private TextView pFoul;
    private TextView pYellowCard;
    private TextView pRedCard;
    private TextView pDisabled;


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

        numMatch = (TextView) findViewById(R.id.numMatch);
        pNoShow = (TextView) findViewById(R.id.pNoShow);
        offWS = (TextView) findViewById(R.id.offensiveWS);
        totalWS = (TextView) findViewById(R.id.totalWS);
        negWS = (TextView) findViewById(R.id.negativeWS);
        pStartLevel1 = (TextView) findViewById(R.id.pStartLevel1);
        pStartLevel2 = (TextView) findViewById(R.id.pStartLevel2);
        pPreloadC = (TextView) findViewById(R.id.pPreloadCargo);
        pPreloadH = (TextView) findViewById(R.id.pPreLoadHatch);
        pCrossHubline = (TextView) findViewById(R.id.pCrossHubline);
        avgRocketTopC = (TextView) findViewById(R.id.avgRocketTopC);
        avgRocketTopH = (TextView) findViewById(R.id.avgRocketTopH);
        avgRocketMiddleC = (TextView) findViewById(R.id.avgRocketMiddleC);
        avgRocketMiddleH = (TextView) findViewById(R.id.avgRocketMiddleH);
        avgRocketBottomC = (TextView) findViewById(R.id.avgRocketBottomC);
        avgRocketBottomH = (TextView) findViewById(R.id.avgRocketBottomH);
        avgCargoC = (TextView) findViewById(R.id.avgCargoShipC);
        avgCargoH = (TextView) findViewById(R.id.avgCargoShipH);
        pEndLevel1 = (TextView) findViewById(R.id.pEndLevel1);
        pEndLevel2 = (TextView) findViewById(R.id.pEndLevel2);
        pEndLevel3 = (TextView) findViewById(R.id.pEndlevel3);
        pEndNone = (TextView) findViewById(R.id.pEndNone);
        maxTopC = (TextView) findViewById(R.id.maxRocketTopC);
        minTopC = (TextView) findViewById(R.id.minRocketTopC);
        maxTopH = (TextView) findViewById(R.id.maxRocketTopH);
        minTopH = (TextView) findViewById(R.id.minRocketTopH);
        maxMidC = (TextView) findViewById(R.id.maxRocketMiddleC);
        minMidC = (TextView) findViewById(R.id.minRocketMiddleC);
        maxMidH = (TextView) findViewById(R.id.maxRocketMiddleH);
        minMidH = (TextView) findViewById(R.id.minRocketMiddleH);
        maxBotC = (TextView) findViewById(R.id.maxRocketBottomC);
        minBotC = (TextView) findViewById(R.id.minRocketBottomC);
        maxBotH = (TextView) findViewById(R.id.maxRocketBottomH);
        minBotH = (TextView) findViewById(R.id.minRocketBottomH);
        maxCargoC = (TextView) findViewById(R.id.maxCargoShipC);
        minCargoC = (TextView) findViewById(R.id.minCargoShipC);
        maxCargoH = (TextView) findViewById(R.id.maxCargoShipH);
        minCargoH = (TextView) findViewById(R.id.minCargoShipH);
        pTechFoul = (TextView) findViewById(R.id.pTechFoul);
        pFoul = (TextView) findViewById(R.id.pFoul);
        pYellowCard = (TextView) findViewById(R.id.pYellowCard);
        pRedCard = (TextView) findViewById(R.id.pRedCard);
        pDisabled = (TextView) findViewById(R.id.pDisabled);

    }
    private void loadData(TeamInfo teamInfo) {

        double nm = teamInfo.getNumMatch();
        numMatch.setText(nm + "");
        double noShow = teamInfo.getPNoShow();
        pNoShow.setText(noShow + "%");
        double tWS = teamInfo.getTotalWS();
        totalWS.setText(tWS + "");
        double nWS = teamInfo.getNegWS();
        negWS.setText(nWS + "");
        double oWS = teamInfo.getOffensiveWS();
        offWS.setText(oWS + "");
        double pls1 = teamInfo.getpStartLevel1();
        pStartLevel1.setText(pls1 + "");
        double pls2 = teamInfo.getpStartLevel2();
        pStartLevel2.setText(pls2 + "");
        double ppc = teamInfo.getpPreloadCargo();
        pPreloadC.setText(ppc + "%");
        double pph = teamInfo.getpPreloadHatch();
        pPreloadH.setText(pph + "%");
        double pchl = teamInfo.getpCrossHubLine();
        pCrossHubline.setText(pCrossHubline + "%");
        double artc = teamInfo.getAvgRocketTopC();
        avgRocketTopC.setText(artc + "");
        double arth = teamInfo.getAvgRocketTopH();
        avgRocketTopH.setText(arth + "");
        double armc = teamInfo.getAvgRocketMiddleC();
        avgRocketMiddleC.setText(armc + "");
        double armh = teamInfo.getAvgRocketMiddleH();
        avgRocketMiddleH.setText(armh + "");
        double arbc = teamInfo.getAvgRocketBottomC();
        avgRocketBottomC.setText(arbc + "");
        double arbh = teamInfo.getAvgRocketBottomH();
        avgRocketBottomH.setText(arbh + "");
        double acc = teamInfo.getAvgCargoShipC();
        avgCargoC.setText(acc + "");
        double ach = teamInfo.getAvgCargoShipH();
        avgCargoH.setText(ach + "");
        double pel1 = teamInfo.getpEndLevel1();
        pEndLevel1.setText(pel1 + "");
        double pel2 = teamInfo.getpEndLevel2();
        pEndLevel2.setText(pel2 + "");
        double pel3 = teamInfo.getpEndLevel3();
        pEndLevel3.setText(pel3 + "");
        double pen = teamInfo.getpEndNone();
        pEndNone.setText(pen + "");
        double matc = teamInfo.getMaxRocketTopC();
        maxTopC.setText(matc + "");
        double math = teamInfo.getMaxRocketTopH();
        maxTopH.setText(math + "");
        double mitc = teamInfo.getMinRocketTopC();
        minTopC.setText(mitc + "%");
        double mith = teamInfo.getMinRocketTopH();
        minTopH.setText(mith + "");
        double mamc = teamInfo.getMaxRocketMiddleC();
        maxMidC.setText(mamc + "");
        double mamh = teamInfo.getMaxRocketMiddleH();
        maxMidH.setText(mamh + "");
        double mimc = teamInfo.getMinRocketMiddleC();
        minMidC.setText(mimc + "");
        double mimh = teamInfo.getMinRocketMiddleH();
        minMidH.setText(mimh + "");
        double mabc = teamInfo.getMaxRocketBottomC();
        maxBotC.setText(mabc + "");
        double mibc = teamInfo.getMinRocketBottomC();
        minBotC.setText(mibc + "");
        double mabh = teamInfo.getMaxRocketBottomH();
        maxBotH.setText(mabh + "");
        double mibh = teamInfo.getMinRocketBottomH();
        minBotH.setText(mibh + "");
        double macc = teamInfo.getMaxCargoShipC();
        maxCargoC.setText(macc + "");
        double micc = teamInfo.getMinCargoShipC();
        minCargoC.setText(micc + "");
        double mach = teamInfo.getMaxCargoShipH();
        maxCargoH.setText(mach + "");
        double mich = teamInfo.getMinCargoShipH();
        minCargoH.setText(mich + "");
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
}
}
