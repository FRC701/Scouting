package com.vandenrobotics.saga2018.tabs;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.vandenrobotics.saga2018.R;
import com.vandenrobotics.saga2018.activities.MatchActivity;
import com.vandenrobotics.saga2018.data.model.Cubes;
import com.vandenrobotics.saga2018.data.model.Stats;
import com.vandenrobotics.saga2018.data.repo.CubesRepo;
import com.vandenrobotics.saga2018.data.repo.StatsRepo;
import com.vandenrobotics.saga2018.views.NumberPicker;

import java.util.ArrayList;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class TeleFragment extends Fragment {

    private MatchActivity mActivity;
    private boolean viewAssigned = false;

    private ConstraintLayout teleField;

    private CheckBox teleFragCb_removeCube;
    private CheckBox teleFragCb_pickUpCube;
    private CheckBox teleFragCb_disabled;
    private CheckBox teleFragCb_redCard;
    private CheckBox teleFragCb_yellowCard;
    private CheckBox teleFragCb_humanPlayer;

    private ToggleButton teleFragTb_enableCubePlacement;

    private final String mPhase = "Tele";

    private CheckBox teleFragRb_climb;
    private CheckBox teleFragRb_climbAssist;
    private CheckBox teleFragRb_parking;

    private NumberPicker teleFragNp_foulNum;
    private NumberPicker teleFragNp_techFoulNum;

    private TextView teleFragTv_switch1Counter;
    private TextView teleFragTv_scaleCounter;
    private TextView teleFragTv_switch2Counter;
    private TextView teleFragTv_exchangeCounter;

    private Button teleFragBtn_startTimer;
    private Button teleFragBtn_switch1Cube;
    private Button teleFragBtn_switch2Cube;
    private Button teleFragBtn_scaleCube;
    private Button teleFragBtn_exchangeCube;


    private int tele_switch1Cubes;
    private int tele_switch2Cubes;
    private int tele_scaleCubes;
    private int tele_exchangeCubes;

    private OnCubeCountClickListener onCubeCountClickListener;

    private String mEvent;
    private int mMatchNum;
    private int mTeamNum;
    private int mMatchPos;
    private String mAlliance;

    private final String mSwitch1 = "Sw1";
    private final String mScale = "Scl";
    private final String mSwitch2 = "Sw2";
    private final String mExchange = "Ex";

    private StatsRepo statsRepo;
    private CubesRepo cubesRepo;
    private ArrayList<Cubes> cubesList;

    private boolean enableCubePlacement = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_tele, container, false);
        mActivity = (MatchActivity)getActivity();
        mEvent = mActivity.mEvent;
        mMatchNum = mActivity.mMatchNumber;
        mTeamNum = mActivity.mTeamNumber;
        mMatchPos =mActivity.mDeviceNumber;
        mAlliance = mActivity.mAllianceColor;

        statsRepo = new StatsRepo();
        cubesRepo = new CubesRepo();

        cubesList = new ArrayList<>();

        if(!viewAssigned) assignViews(rootView);
        if(viewAssigned)loadData();

        return rootView;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        assignViews(view);
        if (viewAssigned)loadData();
    }

    @Override
    public void onPause(){
        //gets data from teleTab when clicked out/ paused
        super.onPause();
        Stats stats = saveData();
        statsRepo.setTeleStats(stats);
        checkCubes();
    }
    public Stats saveData(){
        Stats stats = new Stats();
        stats.setCompId(mEvent);
        stats.setMatchNum(mMatchNum);
        stats.setTeamNum(mTeamNum);
        int disabled = (teleFragCb_disabled.isChecked() ? 1 : 0);
        stats.setDisabled(disabled);
        int redCard = (teleFragCb_redCard.isChecked() ? 1 : 0);
        stats.setRedCard(redCard);
        int yellowCard = (teleFragCb_yellowCard.isChecked() ? 1 : 0);
        stats.setYellowCard(yellowCard);
        int pC = (teleFragCb_pickUpCube.isChecked() ? 1 : 0);
        stats.setPickedUpCube(pC);
        int climb = (teleFragRb_climb.isChecked() ? 1 : 0);
        stats.setTeleClimb(climb);
        int climbAssist = (teleFragRb_climbAssist.isChecked() ? 1 : 0);
        stats.setTeleClimbAssist(climbAssist);
        int parking = (teleFragRb_parking.isChecked() ? 1 : 0);
        stats.setParking(parking);
        int humanPlayer = (teleFragCb_humanPlayer.isChecked() ? 1 : 0);
        stats.setHumanPlayer(humanPlayer);
        int foul = (teleFragNp_foulNum.getValue());
        stats.setFoul(foul);
        int techFoul = (teleFragNp_techFoulNum.getValue());
        stats.setTechFoul(techFoul);
        stats.setTeleCubesInSw1(tele_switch1Cubes);
        stats.setTeleCubesInSc1(tele_scaleCubes);
        stats.setTeleCubesInSw2(tele_switch2Cubes);
        stats.setTeleCubesInEx(tele_exchangeCubes);
        Log.d("TeleFrag saveData", "team id " + stats.getTeamNum());
        return stats;
    }
    @Override
    public void onResume() {
        //when resumed, loads data onto the tab
        super.onResume();
        assignViews(getView());
        if (viewAssigned) loadData();
    }
    private void loadData() {
        Stats stats = statsRepo.getTeleStats(mEvent, mMatchNum, mMatchPos);
        teleFragCb_pickUpCube.setChecked(stats.getPickedUpCube() == 1);
        teleFragCb_disabled.setChecked(stats.getDisabled() == 1);
        teleFragCb_redCard.setChecked(stats.getRedCard() == 1);
        teleFragCb_yellowCard.setChecked(stats.getYellowCard() == 1);
        teleFragCb_humanPlayer.setChecked(stats.getHumanPlayer() == 1);
        teleFragRb_climb.setChecked(stats.getTeleClimb() == 1);
        teleFragRb_climbAssist.setChecked(stats.getTeleClimbAssist() == 1);
        teleFragRb_parking.setChecked(stats.getParking() == 1);
        tele_switch1Cubes = stats.getTeleCubesInSw1();
        tele_scaleCubes = stats.getTeleCubesInSc1();
        tele_switch2Cubes = stats.getTeleCubesInSw2();
        tele_exchangeCubes = stats.getTeleCubesInEx();

        if (cubesRepo.checkForSameMatch(mEvent, mMatchNum, mPhase) == 0){
            enableCubePlacement = false;

        }else {
            enableCubePlacement = true;
            teleFragBtn_switch1Cube.setEnabled(false);
            teleFragBtn_scaleCube.setEnabled(false);
            teleFragBtn_switch2Cube.setEnabled(false);
            teleFragBtn_exchangeCube.setEnabled(false);
        }
        teleFragTb_enableCubePlacement.setChecked(enableCubePlacement);

        teleFragTv_switch1Counter.setText(tele_switch1Cubes + "");
        teleFragTv_scaleCounter.setText(tele_scaleCubes + "");
        teleFragTv_switch2Counter.setText(tele_switch2Cubes + "");
        teleFragTv_exchangeCounter.setText(tele_exchangeCubes + "");

        teleFragNp_techFoulNum.setValue(stats.getTechFoul());
        teleFragNp_techFoulNum.setValue(stats.getFoul());
    }
    public void assignViews(View view){
        try {
            teleField = (ConstraintLayout) view.findViewById(R.id.field_view2);
            if (mAlliance.equals("RED")){
                teleField.setBackground(getResources().getDrawable(R.drawable.field2018red));
            }else{
                teleField.setBackground(getResources().getDrawable(R.drawable.field2018blue));
            }
            teleFragCb_removeCube = (CheckBox) view.findViewById(R.id.teleCb_removeCube);
            teleFragCb_pickUpCube = (CheckBox) view.findViewById(R.id.teleCb_pickUpCube);
            teleFragCb_disabled = (CheckBox) view.findViewById(R.id.teleCb_disabled);
            teleFragCb_redCard = (CheckBox) view.findViewById(R.id.teleCb_redCard);
            teleFragCb_yellowCard = (CheckBox) view.findViewById(R.id.teleCb_yellowCard);
            teleFragCb_humanPlayer = (CheckBox) view.findViewById(R.id.teleCb_humanPlayer);
            teleFragRb_climb = (CheckBox) view.findViewById(R.id.teleRb_climb);
            teleFragRb_climbAssist = (CheckBox) view.findViewById(R.id.teleRb_climbAssist);
            teleFragRb_parking = (CheckBox) view.findViewById(R.id.teleRb_parking);
            teleFragNp_foulNum = (NumberPicker) view.findViewById(R.id.teleNp_foulNum);
            teleFragNp_techFoulNum = (NumberPicker) view.findViewById(R.id.teleNp_techFoulNum);
            teleFragTv_switch1Counter = (TextView) view.findViewById(R.id.teleTv_switch1Counter);
            teleFragTv_scaleCounter = (TextView) view.findViewById(R.id.teleTv_scaleCounter);
            teleFragTv_switch2Counter = (TextView) view.findViewById(R.id.teleTv_switch2Counter);
            teleFragTv_exchangeCounter = (TextView) view.findViewById(R.id.teleTv_exchangeCounter);
            teleFragBtn_startTimer = (Button) view.findViewById(R.id.teleBtn_start);
            teleFragBtn_switch1Cube = (Button) view.findViewById(R.id.teleBtn_switch1Cube);
            teleFragBtn_switch2Cube = (Button) view.findViewById(R.id.teleBtn_switch2Cube);
            teleFragBtn_scaleCube = (Button) view.findViewById(R.id.teleBtn_scaleCube);
            teleFragBtn_exchangeCube = (Button) view.findViewById(R.id.teleBtn_exchangeCube);

            teleFragTb_enableCubePlacement = (ToggleButton) view.findViewById(R.id.teleTb_enableCubes);

            teleFragBtn_exchangeCube.getBackground().setAlpha(0);
            teleFragBtn_switch1Cube.getBackground().setAlpha(0);
            teleFragBtn_scaleCube.getBackground().setAlpha(0);
            teleFragBtn_switch2Cube.getBackground().setAlpha(0);

            onCubeCountClickListener = new TeleFragment.OnCubeCountClickListener();

            teleFragBtn_startTimer.setOnClickListener(new TeleFragment.OnControlTimer());
            teleFragBtn_switch1Cube.setOnClickListener(onCubeCountClickListener);
            teleFragBtn_switch2Cube.setOnClickListener(onCubeCountClickListener);
            teleFragBtn_scaleCube.setOnClickListener(onCubeCountClickListener);
            teleFragBtn_exchangeCube.setOnClickListener(onCubeCountClickListener);

            tele_switch1Cubes = 0;
            tele_switch2Cubes = 0;
            tele_scaleCubes = 0;
            tele_exchangeCubes = 0;

            teleFragTb_enableCubePlacement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (enableCubePlacement){
                        tele_switch1Cubes = 0;
                        tele_scaleCubes = 0;
                        tele_switch2Cubes = 0;
                        tele_exchangeCubes = 0;
                        String cubeNum =tele_switch1Cubes+"";
                        teleFragTv_switch1Counter.setText(cubeNum);
                        String cubeNum2 =tele_switch2Cubes+"";
                        teleFragTv_switch2Counter.setText(cubeNum2);
                        String cubeNum3 =tele_scaleCubes+"";
                        teleFragTv_scaleCounter.setText(cubeNum3);
                        String cubeNum4 =tele_exchangeCubes+"";
                        teleFragTv_exchangeCounter.setText(cubeNum4);
                        teleFragBtn_switch1Cube.setEnabled(true);
                        teleFragBtn_scaleCube.setEnabled(true);
                        teleFragBtn_switch2Cube.setEnabled(true);
                        teleFragBtn_exchangeCube.setEnabled(true);
                        enableCubePlacement = false;
                    }else{
                        teleFragBtn_switch1Cube.setEnabled(false);
                        teleFragBtn_scaleCube.setEnabled(false);
                        teleFragBtn_switch2Cube.setEnabled(false);
                        teleFragBtn_exchangeCube.setEnabled(false);
                        enableCubePlacement = true;
                    }
                }
            });

            viewAssigned = true;
        }catch(Exception e){
            e.printStackTrace();
            viewAssigned = false;
        }

    }
    public void checkCubes(){
        if(!cubesList.isEmpty()){
            if (cubesRepo.checkForSameMatch(mEvent, mMatchNum, mPhase) == 0){
                for (Cubes cubes : cubesList){
                    Log.d(TAG, cubes.getTime().trim().toLowerCase());
                    if(!cubes.getTime().trim().toLowerCase().equals("start match")){
                        cubesRepo.insert(cubes);
                        Log.d(TAG, cubes.getTime().trim().toLowerCase());
                    }
                }
            }else{
                cubesRepo.deleteForCompAndMatch(mEvent, mMatchNum, mPhase);
                for (Cubes c : cubesList) {
                    cubesRepo.insert(c);
                }
            }
        }
    }

    public class OnCubeCountClickListener implements Button.OnClickListener{
        @Override
        public void onClick(View view) {
            Cubes cubes = new Cubes();
            cubes.setCompId(mEvent);
            cubes.setMatchNum(mMatchNum);
            cubes.setTeamNum(mTeamNum);
            cubes.setPhase(mPhase);

            switch(view.getId()){
                case R.id.teleBtn_switch1Cube:
                    cubes.setBalance(mSwitch1);
                    if(teleFragCb_removeCube.isChecked() && tele_switch1Cubes > 0){
                        cubesList.remove(cubesList.size()-1);
                        tele_switch1Cubes --;
                    }else {
                        if (getTime().trim().toLowerCase().equals("start match")) {
                            Toast.makeText(getContext(), "Start the Match", Toast.LENGTH_SHORT).show();
                        }else if(getTime().trim().equals("0:00.000")) {
                            Toast.makeText(getContext(), "Tele has Ended", Toast.LENGTH_SHORT).show();
                        }else{
                            cubes.setTime(getTime());
                            cubesList.add(cubes);
                            tele_switch1Cubes++;
                        }
                    }
                    String cubeNum = tele_switch1Cubes+"";
                    teleFragTv_switch1Counter.setText(cubeNum);
                    break;
                case R.id.teleBtn_switch2Cube:
                    cubes.setBalance(mSwitch2);
                    if(teleFragCb_removeCube.isChecked() && tele_switch2Cubes > 0){
                        cubesList.remove(cubesList.size()-1);
                        tele_switch2Cubes --;
                    }else{
                        if (getTime().trim().toLowerCase().equals("start match")) {
                            Toast.makeText(getContext(), "Start the Match", Toast.LENGTH_SHORT).show();
                        }else if(getTime().trim().equals("0:00.000")) {
                            Toast.makeText(getContext(), "Tele has Ended", Toast.LENGTH_SHORT).show();
                        }else{
                            cubes.setTime(getTime());
                            cubesList.add(cubes);
                            tele_switch2Cubes++;
                        }
                    }
                    String cubeNum2 = tele_switch2Cubes+"";
                    teleFragTv_switch2Counter.setText(cubeNum2);
                    break;
                case R.id.teleBtn_scaleCube:
                    cubes.setBalance(mScale);
                    if(teleFragCb_removeCube.isChecked() && tele_scaleCubes > 0){
                        cubesList.remove(cubesList.size()-1);
                        tele_scaleCubes --;
                    }else{
                        if (getTime().trim().toLowerCase().equals("start match")) {
                            Toast.makeText(getContext(), "Start the Match", Toast.LENGTH_SHORT).show();
                        }else if(getTime().trim().equals("0:00.000")) {
                            Toast.makeText(getContext(), "Tele has Ended", Toast.LENGTH_SHORT).show();
                        }else{
                            cubes.setTime(getTime());
                            cubesList.add(cubes);
                            tele_scaleCubes++;
                        }
                    }
                    String cubeNum3 = tele_scaleCubes+"";
                    teleFragTv_scaleCounter.setText(cubeNum3);
                    break;
                case R.id.teleBtn_exchangeCube:
                    cubes.setBalance(mExchange);
                    if(teleFragCb_removeCube.isChecked() && tele_exchangeCubes > 0){
                        cubesList.remove(cubesList.size()-1);
                        tele_exchangeCubes --;
                    }else {
                        if (getTime().trim().toLowerCase().equals("start match")) {
                            Toast.makeText(getContext(), "Start the Match", Toast.LENGTH_SHORT).show();
                        }else if(getTime().trim().equals("0:00.000")) {
                            Toast.makeText(getContext(), "Tele has Ended", Toast.LENGTH_SHORT).show();
                        }else{
                            cubes.setTime(getTime());
                            cubesList.add(cubes);
                            tele_exchangeCubes++;
                        }
                    }
                    String cubeNum4 = tele_exchangeCubes+"";
                    teleFragTv_exchangeCounter.setText(cubeNum4);
                    break;
            }
        }

        public String getTime(){
            String time = teleFragBtn_startTimer.getText().toString();
            Toast.makeText(getContext(), time, Toast.LENGTH_SHORT).show();
            return time;
        }
    }
    public class OnControlTimer implements Button.OnClickListener{
        boolean hasStarted = false;

        CountDownTimer countDownTimer = new CountDownTimer(135000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                long mili = millisUntilFinished % 1000;
                long seconds = (millisUntilFinished / 1000) % 60;
                long minuets = (millisUntilFinished / 1000) / 60;
                teleFragBtn_startTimer.setText(String.format(Locale.US,"%d:%d.%d", minuets, seconds, mili));
            }

            @Override
            public void onFinish() {
            }
        };

        @Override
        public void onClick(View view) {
            if(!hasStarted){
                hasStarted = true;
                countDownTimer.start();
            }else{
                hasStarted = false;
                countDownTimer.cancel();
                teleFragBtn_startTimer.setText(R.string.start_match);
            }
        }
    }
}
