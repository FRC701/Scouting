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


public class avgWeight extends AppCompatActivity {

    private Text startLevel1_tv;
    private Text startLevel2_tv;
    private Text noShow_tv;
    private Text redCard_tv;
    private Text yellowCard_tv;
    private Text foul_tv;
    private Text techFoul_tv;
    private Text disabled_tv;
    private Text preloadCargo_tv;
    private Text preloadHatch_tv;
    private Button rocketTopC_tv;
    private Button rocketTopH_tv;
    private Button rocketMiddleC_tv;
    private Button rocketMiddleH_tv;
    private Button rocketBottomC_tv;
    private Button rocketBottomH_tv;
    private Button cargoShipC_tv;
    private Button cargoShipH_tv;
    private Text crossHubLine_tv;
    private Text endLevel1_tv;
    private Text endLevel2_tv;
    private Text endLevel3_tv;
    private Text endNone_tv;
    private Text defense_tv;


    private int matchNum;
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
                    int a = noShow/matches*100 ;
                    int b = redCard / matches*100;
                    int c = yellowCard / matches*100;
                    int d = disabled / matches*100;
                    int e = startLevel1 / matches*100;
                    int f = startLevel2 / matches*100;
                    int g = preloadCargo / matches*100;
                    int h = preloadHatch / matches*100;
                    int j = crossHubLine / matches*100;
                    int k = endLevel1 / matches*100;
                    int l = endLevel2 / matches*100;
                    int m = endLevel3 / matches*100;
                    int n = endNone / matches*100;
                    int o = defense / matches*100;
                    int p = foul / matches;
                    int q = techFoul / matches;
                    int r = rocketTopC / matches;
                    int s = rocketTopH / matches;
                    int t = rocketMiddleC / matches;
                    int u = rocketMiddleH / matches;
                    int w = rocketBottomC / matches;
                    int x = rocketBottomH / matches;
                    int y = cargoShipC / matches;
                    int z = cargoShipH / matches;

                    //noShow_tv.setText("");
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
