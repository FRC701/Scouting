package com.vandenrobotics.stats.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.vandenrobotics.stats.R;
import com.vandenrobotics.stats.data.model.Stats;
import com.vandenrobotics.stats.data.repo.StatsRepo;
import com.vandenrobotics.stats.data.repo.TeamInfoRepo;
import com.vandenrobotics.stats.data.repo.TeamsRepo;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;


public class MinMax extends AppCompatActivity{

    private TextView minstartLevel1_tv;
    private TextView minstartLevel2_tv;
    private TextView minnoShow_tv;
    private TextView minredCard_tv;
    private TextView minyellowCard_tv;
    private TextView minfoul_tv;
    private TextView mintechFoul_tv;
    private TextView mindisabled_tv;
    private TextView minpreloadCargo_tv;
    private TextView minpreloadHatch_tv;
    private TextView mincrossHubLine_tv;
    private TextView minendLevel1_tv;
    private TextView minendLevel2_tv;
    private TextView minendLevel3_tv;
    private TextView minendNone_tv;
    private TextView mindefense_tv;
    private Button minrocketTopC_btn;
    private Button minrocketTopH_btn;
    private Button minrocketMiddleC_btn;
    private Button minrocketMiddleH_btn;
    private Button minrocketBottomC_btn;
    private Button minrocketBottomH_btn;
    private Button mincargoShipC_btn;
    private Button mincargoShipH_btn;


    private TextView maxstartLevel1_tv;
    private TextView maxstartLevel2_tv;
    private TextView maxnoShow_tv;
    private TextView maxredCard_tv;
    private TextView maxyellowCard_tv;
    private TextView maxfoul_tv;
    private TextView maxtechFoul_tv;
    private TextView maxdisabled_tv;
    private TextView maxpreloadCargo_tv;
    private TextView maxpreloadHatch_tv;
    private TextView maxcrossHubLine_tv;
    private TextView maxendLevel1_tv;
    private TextView maxendLevel2_tv;
    private TextView maxendLevel3_tv;
    private TextView maxendNone_tv;
    private TextView maxdefense_tv;
    private Button maxrocketTopC_btn;
    private Button maxrocketTopH_btn;
    private Button maxrocketMiddleC_btn;
    private Button maxrocketMiddleH_btn;
    private Button maxrocketBottomC_btn;
    private Button maxrocketBottomH_btn;
    private Button maxcargoShipC_btn;
    private Button maxcargoShipH_btn;


    private int minnoShow = -1;
    private int minredCard;
    private int minyellowCard;
    private int minfoul;
    private int mintechFoul;
    private int mindisabled;
    private int minstartLevel1;
    private int minstartLevel2;
    private int minpreloadCargo;
    private int minpreloadHatch;
    private int minrocketTopC;
    private int minrocketTopH;
    private int minrocketMiddleC;
    private int minrocketMiddleH;
    private int minrocketBottomC;
    private int minrocketBottomH;
    private int mincargoShipC;
    private int mincargoShipH;
    private int mincrossHubLine;
    private int minendLevel1;
    private int minendLevel2;
    private int minendLevel3;
    private int minendNone;
    private int mindefense;

    private int maxnoShow= -1;
    private int maxredCard;
    private int maxyellowCard;
    private int maxfoul;
    private int maxtechFoul;
    private int maxdisabled;
    private int maxstartLevel1;
    private int maxstartLevel2;
    private int maxpreloadCargo;
    private int maxpreloadHatch;
    private int maxrocketTopC;
    private int maxrocketTopH;
    private int maxrocketMiddleC;
    private int maxrocketMiddleH;
    private int maxrocketBottomC;
    private int maxrocketBottomH;
    private int maxcargoShipC;
    private int maxcargoShipH;
    private int maxcrossHubLine;
    private int maxendLevel1;
    private int maxendLevel2;
    private int maxendLevel3;
    private int maxendNone;
    private int maxdefense;


    private StatsRepo statsRepo;

    private ArrayList<Integer> team_numbers;
    private TeamsRepo teamsRepo;
    private TeamInfoRepo teamInfoRepo;
    private Spinner spinnerTeams;
    private ArrayAdapter<Integer> teamAdapter;
    private int mTeamNumber;
    private int matches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avg_weight);

        TeamsRepo teamsRepo = new TeamsRepo();
        team_numbers = teamsRepo.getTeams();
        Collections.sort(team_numbers);
        spinnerTeams = (Spinner) findViewById(R.id.spinner3);
        teamAdapter = new ArrayAdapter<>(this, R.layout.spinner_base, team_numbers);
        teamAdapter.setDropDownViewResource(R.layout.spinner_dropdown);
        spinnerTeams.setSelection(teamAdapter.getPosition(mTeamNumber));
        spinnerTeams.setAdapter(teamAdapter);
        teamInfoRepo = new TeamInfoRepo();

        minstartLevel1_tv = (TextView) findViewById(R.id.startLevel1Min);
        minstartLevel2_tv = (TextView) findViewById(R.id.startLevel2Min);
        minnoShow_tv = (TextView) findViewById(R.id.noShowMin);
        minredCard_tv = (TextView) findViewById(R.id.redCard_tvMin);
        minyellowCard_tv = (TextView) findViewById(R.id.yellowCard_tvMin);
        minfoul_tv = (TextView) findViewById(R.id.foul_tvMin);
        mintechFoul_tv = (TextView) findViewById(R.id.techFouls_tvMin);
        mindisabled_tv = (TextView) findViewById(R.id.disabled_tvMin);
        minpreloadCargo_tv = (TextView) findViewById(R.id.preloadCargoMin);
        minpreloadHatch_tv = (TextView) findViewById(R.id.preloadHatchMin);
        mincrossHubLine_tv = (TextView) findViewById(R.id.crossHubLine_tv);
        minendLevel1_tv = (TextView) findViewById(R.id.endLevel1_tvMin);
        minendLevel2_tv = (TextView) findViewById(R.id.endLevel2_tvMin);
        minendLevel3_tv = (TextView) findViewById(R.id.endLevel3_tvMin);
        minendNone_tv = (TextView) findViewById(R.id.endNone_tvMin);
        mindefense_tv = (TextView) findViewById(R.id.defense_tvMin);
        minrocketTopC_btn = (Button) findViewById(R.id.rocketTopC_btnMin);
        minrocketTopH_btn = (Button) findViewById(R.id.rocketTopH_btnMin);
        minrocketMiddleC_btn = (Button) findViewById(R.id.rocketMiddleC_btnMin);
        minrocketMiddleH_btn = (Button) findViewById(R.id.rocketMiddleH_btnMin);
        minrocketBottomC_btn = (Button) findViewById(R.id.rocketBottomC_btnMin);
        minrocketBottomH_btn = (Button) findViewById(R.id.rocketBottomH_btnMin);
        mincargoShipC_btn = (Button) findViewById(R.id.cargoShipC_btnMin);
        mincargoShipH_btn = (Button) findViewById(R.id.cargoShipH_btnMin);

        maxstartLevel1_tv = (TextView) findViewById(R.id.startLevel1Max);
        maxstartLevel2_tv = (TextView) findViewById(R.id.startLevel2Max);
        maxnoShow_tv = (TextView) findViewById(R.id.noShowMax);
        maxredCard_tv = (TextView) findViewById(R.id.redCard_tvMax);
        maxyellowCard_tv = (TextView) findViewById(R.id.yellowCard_tvMax);
        maxfoul_tv = (TextView) findViewById(R.id.foul_tvMax);
        maxtechFoul_tv = (TextView) findViewById(R.id.techFouls_tvMax);
        maxdisabled_tv = (TextView) findViewById(R.id.disabled_tvMax);
        maxpreloadCargo_tv = (TextView) findViewById(R.id.preloadCargoMax);
        maxpreloadHatch_tv = (TextView) findViewById(R.id.preloadHatchMax);
        maxcrossHubLine_tv = (TextView) findViewById(R.id.crossHubLine_tvMax);
        maxendLevel1_tv = (TextView) findViewById(R.id.endLevel1_tvMax);
        maxendLevel2_tv = (TextView) findViewById(R.id.endLevel2_tvMax);
        maxendLevel3_tv = (TextView) findViewById(R.id.endLevel3_tvMax);
        maxendNone_tv = (TextView) findViewById(R.id.endNone_tvMax);
        maxdefense_tv = (TextView) findViewById(R.id.defense_tvMax);
        maxrocketTopC_btn = (Button) findViewById(R.id.rocketTopC_btnMax);
        maxrocketTopH_btn = (Button) findViewById(R.id.rocketTopH_btnMax);
        maxrocketMiddleC_btn = (Button) findViewById(R.id.rocketMiddleC_btnMax);
        maxrocketMiddleH_btn = (Button) findViewById(R.id.rocketMiddleH_btnMax);
        maxrocketBottomC_btn = (Button) findViewById(R.id.rocketBottomC_btnMax);
        maxrocketBottomH_btn = (Button) findViewById(R.id.rocketBottomH_btnMax);
        maxcargoShipC_btn = (Button) findViewById(R.id.cargoShipC_btnMax);
        maxcargoShipH_btn = (Button) findViewById(R.id.cargoShipH_btnMax);

        spinnerTeams.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapter, View v, int position, long arg3){
                mTeamNumber = Integer.parseInt(spinnerTeams.getItemAtPosition(position).toString());
                ArrayList<Integer> teamsHaveData = statsRepo.getTeams();
                matches= 0;
                for(int i : teamsHaveData){
                    if(i == mTeamNumber){
                        Stats stats = statsRepo.getStats(mTeamNumber);
                        loadData(stats);
                        matches++;
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapter){

            }
        });
    }

    private void loadData(Stats stats) {
        if(minnoShow == -1){
            init(stats);
        }
    }
    private void init(Stats stats){
        minstartLevel1 = stats.getStartLevel1();
        minredCard = stats.getRedCard();
        minyellowCard = stats.getYellowCard();
        minfoul = stats.getFoul();
        mintechFoul = stats.getTechFoul();
        mindisabled = stats.getDisabled();
        minstartLevel2 = stats.getStartLevel2();
        minpreloadCargo = stats.getPreloadCargo();
        minpreloadHatch = stats.getPreloadHatch();
        minrocketTopC = stats.getRocketTopC();
        minrocketTopH = stats.getRocketTopH();
        minrocketMiddleC = stats.getRocketMiddleC();
        
    }
}
