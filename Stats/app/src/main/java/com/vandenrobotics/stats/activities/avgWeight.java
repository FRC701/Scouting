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


public class avgWeight extends AppCompatActivity {

    private TextView startLevel1_tv;
    private TextView startLevel2_tv;
    private TextView noShow_tv;
    private TextView offWS_tv;
    private TextView defWS_tv;
    private TextView totalWS_tv;
    private TextView negWS_tv;
    private TextView yellowCard_tv;
    private TextView foul_tv;
    private TextView techFoul_tv;
    private TextView disabled_tv;
    private TextView preloadCargo_tv;
    private TextView preloadHatch_tv;
    private Button rocketTopC_btn;
    private Button rocketTopH_btn;
    private Button rocketMiddleC_btn;
    private Button rocketMiddleH_btn;
    private Button rocketBottomC_btn;
    private Button rocketBottomH_btn;
    private Button cargoShipC_btn;
    private Button cargoShipH_btn;
    private TextView crossHubLine_tv;
    private TextView endLevel1_tv;
    private TextView endLevel2_tv;
    private TextView endLevel3_tv;
    private TextView endNone_tv;
    private TextView defense_tv;
    private TextView redCard_tv;


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
    private int offWS;
    private int defWS;
    private int totalWS;
    private int negWS;

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

        noShow_tv = (TextView) findViewById(R.id.percentNoShow);
        startLevel1_tv = (TextView) findViewById(R.id.percentStartLevel1);
        startLevel2_tv = (TextView) findViewById(R.id.percentStartLevel2);
        preloadCargo_tv = (TextView) findViewById(R.id.percentPreloadCargo);
        preloadHatch_tv = (TextView) findViewById(R.id.percentPreloadHatch);
        rocketTopC_btn = (Button) findViewById(R.id.rocketTopC_btn);
        rocketTopH_btn = (Button) findViewById(R.id.rocketTopH_btn);
        offWS_tv = (TextView) findViewById(R.id.offWS_tv);
        defWS_tv = (TextView) findViewById(R.id.defWS_tv);
        totalWS_tv = (TextView) findViewById(R.id.totalWS_tv);
        negWS_tv = (TextView) findViewById(R.id.negWS_tv);
        rocketMiddleC_btn = (Button) findViewById(R.id.rocketMiddleC_btn);
        rocketMiddleH_btn = (Button) findViewById(R.id.rocketMiddleH_btn);
        yellowCard_tv = (TextView) findViewById(R.id.yellowCard_tv);
        foul_tv = (TextView) findViewById(R.id.foul_tv);
        disabled_tv = (TextView) findViewById(R.id.disabled_tv);
        techFoul_tv = (TextView) findViewById(R.id.techFouls_tv);
        rocketBottomC_btn = (Button) findViewById(R.id.rocketBottomC_btn);
        rocketBottomH_btn = (Button) findViewById(R.id.rocketBottomH_btn);
        cargoShipC_btn = (Button) findViewById(R.id.cargoShipC_btn);
        cargoShipH_btn = (Button) findViewById(R.id.cargoShipH_btn);
        crossHubLine_tv = (TextView) findViewById(R.id.crossHubLine_tv);
        endLevel1_tv = (TextView) findViewById(R.id.endLevel1_tv);
        endLevel2_tv = (TextView) findViewById(R.id.endLevel2_tv);
        endLevel3_tv = (TextView) findViewById(R.id.endLevel3_tv);
        endNone_tv = (TextView) findViewById(R.id.endNone_tv);
        defense_tv = (TextView) findViewById(R.id.defense_tv);



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
                    noShow_tv.setText(a+"");
                    int b = redCard / matches*100;
                    redCard_tv.setText(b+"");
                    int c = yellowCard / matches*100;
                    yellowCard_tv.setText(c+"");
                    int d = disabled / matches*100;
                    disabled_tv.setText(d+"");
                    int e = startLevel1 / matches*100;
                    startLevel1_tv.setText(e+"");
                    int f = startLevel2 / matches*100;
                    startLevel2_tv.setText(f+"");
                    int g = preloadCargo / matches*100;
                    preloadCargo_tv.setText(g+"");
                    int h = preloadHatch / matches*100;
                    preloadHatch_tv.setText(h+"");
                    int j = crossHubLine / matches*100;
                    crossHubLine_tv.setText(j+"");
                    int k = endLevel1 / matches*100;
                    endLevel1_tv.setText(k+"");
                    int l = endLevel2 / matches*100;
                    endLevel2_tv.setText(l+"");
                    int m = endLevel3 / matches*100;
                    endLevel3_tv.setText(m+"");
                    int n = endNone / matches*100;
                    endNone_tv.setText(n+"");
                    int o = defense / matches*100;
                    defense_tv.setText(o+"");
                    int p = foul / matches;
                    foul_tv.setText(p+"");
                    int q = techFoul / matches;
                    techFoul_tv.setText(q+"");
                    int r = rocketTopC / matches;
                    rocketTopC_btn.setText(r+"");
                    int s = rocketTopH / matches;
                    rocketTopH_btn.setText(s+"");
                    int t = rocketMiddleC / matches;
                    rocketMiddleC_btn.setText(t+"");
                    int u = rocketMiddleH / matches;
                    rocketMiddleH_btn.setText(u+"");
                    int w = rocketBottomC / matches;
                    rocketBottomC_btn.setText(u+"");
                    int x = rocketBottomH / matches;
                    rocketBottomH_btn.setText(x+"");
                    int y = cargoShipC / matches;
                    cargoShipC_btn.setText(y+"");
                    int z = cargoShipH / matches;
                    cargoShipH_btn.setText(z+"");

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
