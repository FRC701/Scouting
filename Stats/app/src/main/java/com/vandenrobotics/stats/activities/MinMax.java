package com.vandenrobotics.stats.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.vandenrobotics.stats.R;
import com.vandenrobotics.stats.data.model.Stats;
import com.vandenrobotics.stats.data.repo.StatsRepo;
import com.vandenrobotics.stats.data.repo.TeamInfoRepo;
import com.vandenrobotics.stats.data.repo.TeamsRepo;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;


public class MinMax extends AppCompatActivity{

    private Text minstartLevel1_tv;
    private Text minstartLevel2_tv;
    private Text minnoShow_tv;
    private Text minredCard_tv;
    private Text minyellowCard_tv;
    private Text minfoul_tv;
    private Text mintechFoul_tv;
    private Text mindisabled_tv;
    private Text minpreloadCargo_tv;
    private Text minpreloadHatch_tv;
    private Text mincrossHubLine_tv;
    private Text minendLevel1_tv;
    private Text minendLevel2_tv;
    private Text minendLevel3_tv;
    private Text minendNone_tv;
    private Text mindefense_tv;
    private Button minrocketTopC_tv;
    private Button minrocketTopH_tv;
    private Button minrocketMiddleC_tv;
    private Button minrocketMiddleH_tv;
    private Button minrocketBottomC_tv;
    private Button minrocketBottomH_tv;
    private Button mincargoShipC_tv;
    private Button mincargoShipH_tv;


    private Text maxstartLevel1_tv;
    private Text maxstartLevel2_tv;
    private Text maxnoShow_tv;
    private Text maxredCard_tv;
    private Text maxyellowCard_tv;
    private Text maxfoul_tv;
    private Text maxtechFoul_tv;
    private Text maxdisabled_tv;
    private Text maxpreloadCargo_tv;
    private Text maxpreloadHatch_tv;
    private Text maxcrossHubLine_tv;
    private Text maxendLevel1_tv;
    private Text maxendLevel2_tv;
    private Text maxendLevel3_tv;
    private Text maxendNone_tv;
    private Text maxdefense_tv;
    private Button maxrocketTopC_tv;
    private Button maxrocketTopH_tv;
    private Button maxrocketMiddleC_tv;
    private Button maxrocketMiddleH_tv;
    private Button maxrocketBottomC_tv;
    private Button maxrocketBottomH_tv;
    private Button maxcargoShipC_tv;
    private Button maxcargoShipH_tv;


    private int noShow;
    private int redCard;
    private int yellowCard;
    private int foul;
    private int techFoul;
    private int disabled;
    private int startLevel1;
    private int startLevel2;
    private int preloadCargo;
    private int preloadHatch;
    private int rocketTopC;
    private int rocketTopH;
    private int rocketMiddleC;
    private int rocketMiddleH;
    private int rocketBottomC;
    private int rocketBottomH;
    private int cargoShipC;
    private int cargoShipH;
    private int crossHubLine;
    private int endLevel1;
    private int endLevel2;
    private int endLevel3;
    private int endNone;
    private int defense;


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
        noShow += stats.getNoShow();
        redCard += stats.getRedCard();
        yellowCard += stats.getYellowCard();
        foul += stats.getFoul();
        techFoul += stats.getTechFoul();
        disabled += stats.getDisabled();
        startLevel1 += stats.getStartLevel1();
        startLevel2 += stats.getStartLevel2();
        preloadCargo += stats.getPreloadCargo();
        preloadHatch += stats.getPreloadHatch();
        rocketTopC += stats.getRocketTopC();
        rocketTopH += stats.getRocketTopH();
        rocketMiddleC += stats.getRocketMiddleC();
        rocketMiddleH += stats.getRocketMiddleH();
        rocketBottomC += stats.getRocketMBottomC();
        rocketBottomH += stats.getRocketMBottomH();
        cargoShipC += stats.getCargoShipC();
        cargoShipH += stats.getCargoShipH();
        crossHubLine += stats.getCrossHubLine();
        endLevel1 += stats.getEndLevel1();
        endLevel2 += stats.getEndLevel2();
        endLevel3 += stats.getEndLevel3();
        endNone += stats.getEndNone();
        defense += stats.getDefense();
    }
}
