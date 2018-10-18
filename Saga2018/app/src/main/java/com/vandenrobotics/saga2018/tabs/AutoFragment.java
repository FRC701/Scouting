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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.vandenrobotics.saga2018.R;
import com.vandenrobotics.saga2018.activities.MatchActivity;
import com.vandenrobotics.saga2018.data.model.Cubes;
import com.vandenrobotics.saga2018.data.model.Stats;
import com.vandenrobotics.saga2018.data.repo.CubesRepo;
import com.vandenrobotics.saga2018.data.repo.StatsRepo;

import java.util.ArrayList;
import java.util.Locale;

import static android.content.ContentValues.TAG;


/**
 * Created by Programming701-A on 1/14/2017.
 */

public class AutoFragment extends Fragment {

    private MatchActivity mActivity;
    private boolean viewAssigned = false;

    private ConstraintLayout autoField;

    private CheckBox autoFragCb_hadAuto;
    private CheckBox autoFragCb_CrossedBl;
    private CheckBox autoFragCb_removeCube;
    private CheckBox autoFragCb_pickUpCube;

    private TextView autoFragTv_switch1Counter;
    private TextView autoFragTv_scaleCounter;
    private TextView autoFragTv_switch2Counter;
    private TextView autoFragTv_exchangeCounter;

    private ToggleButton autoFragTb_enableCubePlacement;

    private Button autoFragBtn_startTimer;
    private Button autoFragBtn_switch1Cube;
    private Button autoFragBtn_switch2Cube;
    private Button autoFragBtn_scaleCube;
    private Button autoFragBtn_exchangeCube;

    private int auto_switch1Cubes = 0;
    private int auto_switch2Cubes = 0;
    private int auto_scaleCubes = 0;
    private int auto_exchangeCubes = 0;

    private OnCubeCountClickListener onCubeCountClickListener;

    private String mEvent;
    private int mMatchNum;
    private int mTeamNum;
    private int mMatchPos;
    private String mAlliance;


    private final String mPhase = "Auto";
    private final String mSwitch1 = "Sw1";
    private final String mScale = "Scl";
    private final String mSwitch2 = "Sw2";
    private final String mExchange = "Ex";

    private StatsRepo statsRepo;
    private CubesRepo cubesRepo;
    private ArrayList<Cubes> cubesList;

    private  boolean enableCubePlacement = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_auto, container, false);
        mActivity = (MatchActivity)getActivity();
        mEvent = mActivity.mEvent;
        mMatchNum = mActivity.mMatchNumber;
        mTeamNum = mActivity.mTeamNumber;
        mMatchPos = mActivity.mDeviceNumber;
        mAlliance = mActivity.mAllianceColor;

        statsRepo = new StatsRepo();
        cubesRepo = new CubesRepo();

        cubesList = new ArrayList<>();

        if(!viewAssigned) assignViews(rootView);
        if (viewAssigned)loadData();

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
        super.onPause();
        Stats stats = saveData();
        statsRepo.setAutoStats(stats);
        checkCubes();
    }

    public Stats saveData(){
        Stats stat = new Stats();
        stat.setCompId(mEvent);
        stat.setMatchNum(mMatchNum);
        stat.setTeamNum(mTeamNum);
        int hA = (autoFragCb_hadAuto.isChecked() ? 1 : 0);
        stat.setHadAuto(hA);
        int cA = (autoFragCb_CrossedBl.isChecked() ? 1 : 0);
        stat.setCrossedAutoLine(cA);
        int pC = (autoFragCb_pickUpCube.isChecked() ? 1 : 0);
        stat.setAutoPickedUpCube(pC);
        stat.setAutoCubesInSw1(auto_switch1Cubes);
        stat.setAutoCubesInScl(auto_scaleCubes);
        stat.setAutoCubesInSw2(auto_switch2Cubes);
        stat.setAutoCubesInEx(auto_exchangeCubes);
        return stat;
    }

    @Override
    public void onResume(){
        super.onResume();
        assignViews(getView());
        if(viewAssigned) loadData();
    }

    private void loadData() {
        Stats stats = statsRepo.getAutoStats(mEvent, mMatchNum, mMatchPos);
        autoFragCb_hadAuto.setChecked(stats.getHadAuto() == 1);
        autoFragCb_CrossedBl.setChecked(stats.getCrossedAutoLine() == 1);
        autoFragCb_pickUpCube.setChecked(stats.getAutoPickedUpCube() == 1);
        auto_switch1Cubes = stats.getAutoCubesInSw1();
        auto_scaleCubes = stats.getAutoCubesInScl();
        auto_switch2Cubes = stats.getAutoCubesInSw2();
        auto_exchangeCubes = stats.getAutoCubesInEx();

        autoFragTv_switch1Counter.setText(auto_switch1Cubes+"");
        autoFragTv_scaleCounter.setText(auto_scaleCubes+"");
        autoFragTv_switch2Counter.setText(auto_switch2Cubes+"");
        autoFragTv_exchangeCounter.setText(auto_exchangeCubes+"");

        if (cubesRepo.checkForSameMatch(mEvent, mMatchNum, mPhase) == 0){
            enableCubePlacement = false;

        }else {
            enableCubePlacement = true;
            autoFragBtn_switch1Cube.setEnabled(false);
            autoFragBtn_scaleCube.setEnabled(false);
            autoFragBtn_switch2Cube.setEnabled(false);
            autoFragBtn_exchangeCube.setEnabled(false);
        }

        autoFragTb_enableCubePlacement.setChecked(enableCubePlacement);
    }

    public void assignViews(View view){
        try {
            autoField = (ConstraintLayout) view.findViewById(R.id.field_view);
            if (mAlliance.equals("RED")){
                autoField.setBackground(getResources().getDrawable(R.drawable.field2018red));
            }else{
                autoField.setBackground(getResources().getDrawable(R.drawable.field2018blue));
            }

            autoFragCb_hadAuto = (CheckBox) view.findViewById(R.id.autoCb_hadAuto);
            autoFragCb_CrossedBl = (CheckBox) view.findViewById(R.id.autoCb_crossedBl);
            autoFragCb_removeCube = (CheckBox) view.findViewById(R.id.autoCb_removeCube);
            autoFragCb_pickUpCube = (CheckBox) view.findViewById(R.id.autoCb_pickUpCube);
            autoFragTv_switch1Counter = (TextView) view.findViewById(R.id.autoTv_switch1Counter);
            autoFragTv_scaleCounter = (TextView) view.findViewById(R.id.autoTv_scaleCounter);
            autoFragTv_switch2Counter = (TextView) view.findViewById(R.id.autoTv_switch2Counter);
            autoFragTv_exchangeCounter = (TextView) view.findViewById(R.id.autoTv_exchangeCounter);
            autoFragTb_enableCubePlacement = (ToggleButton) view.findViewById(R.id.autoTb_enableCubes);
            autoFragBtn_startTimer = (Button) view.findViewById(R.id.autoBtn_start);
            autoFragBtn_switch1Cube = (Button) view.findViewById(R.id.autoBtn_switch1Cube);
            autoFragBtn_switch2Cube = (Button) view.findViewById(R.id.autoBtn_switch2Cube);
            autoFragBtn_scaleCube = (Button) view.findViewById(R.id.autoBtn_scaleCube);
            autoFragBtn_exchangeCube = (Button) view.findViewById(R.id.autoBtn_exchangeCube);

            autoFragCb_hadAuto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (autoFragCb_hadAuto.isChecked()){
                        enableViews();
                    }else
                        disableViews();
                }
            });

            autoFragBtn_exchangeCube.getBackground().setAlpha(0);
            autoFragBtn_switch1Cube.getBackground().setAlpha(0);
            autoFragBtn_scaleCube.getBackground().setAlpha(0);
            autoFragBtn_switch2Cube.getBackground().setAlpha(0);

            onCubeCountClickListener = new OnCubeCountClickListener();

            autoFragBtn_startTimer.setOnClickListener(new OnControlTimer());

            autoFragBtn_switch1Cube.setOnClickListener(onCubeCountClickListener);
            autoFragBtn_switch2Cube.setOnClickListener(onCubeCountClickListener);
            autoFragBtn_scaleCube.setOnClickListener(onCubeCountClickListener);
            autoFragBtn_exchangeCube.setOnClickListener(onCubeCountClickListener);

            autoFragTb_enableCubePlacement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (enableCubePlacement){
                        auto_switch1Cubes = 0;
                        auto_scaleCubes = 0;
                        auto_switch2Cubes = 0;
                        auto_exchangeCubes = 0;
                        String cubeNum =auto_switch1Cubes+"";
                        autoFragTv_switch1Counter.setText(cubeNum);
                        String cubeNum2 =auto_switch2Cubes+"";
                        autoFragTv_switch2Counter.setText(cubeNum2);
                        String cubeNum3 =auto_scaleCubes+"";
                        autoFragTv_scaleCounter.setText(cubeNum3);
                        String cubeNum4 =auto_exchangeCubes+"";
                        autoFragTv_exchangeCounter.setText(cubeNum4);
                        autoFragBtn_switch1Cube.setEnabled(true);
                        autoFragBtn_scaleCube.setEnabled(true);
                        autoFragBtn_switch2Cube.setEnabled(true);
                        autoFragBtn_exchangeCube.setEnabled(true);
                        enableCubePlacement = false;
                    }else{
                        autoFragBtn_switch1Cube.setEnabled(false);
                        autoFragBtn_scaleCube.setEnabled(false);
                        autoFragBtn_switch2Cube.setEnabled(false);
                        autoFragBtn_exchangeCube.setEnabled(false);
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

    public void enableViews(){
        autoFragCb_CrossedBl.setEnabled(true);
        autoFragCb_pickUpCube.setEnabled(true);
        autoFragCb_removeCube.setEnabled(true);
        autoFragBtn_startTimer.setEnabled(true);
    }

    public void disableViews(){
        autoFragCb_CrossedBl.setEnabled(false);
        autoFragCb_pickUpCube.setEnabled(false);
        autoFragCb_removeCube.setEnabled(false);
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
                case R.id.autoBtn_switch1Cube:
                    cubes.setBalance(mSwitch1);
                    if(autoFragCb_removeCube.isChecked() && auto_switch1Cubes > 0){
                        cubesList.remove(cubesList.size()-1);
                        auto_switch1Cubes --;
                    }else {
                        if (getTime().trim().toLowerCase().equals("start match")) {
                            Toast.makeText(getContext(), "Start the Match", Toast.LENGTH_SHORT).show();
                        }else if(getTime().trim().equals("0:00.000")) {
                            Toast.makeText(getContext(), "Auto has Ended", Toast.LENGTH_SHORT).show();
                        }else{
                            cubes.setTime(getTime());
                            cubesList.add(cubes);
                            auto_switch1Cubes++;
                        }
                    }
                    String cubeNum =auto_switch1Cubes+"";
                    autoFragTv_switch1Counter.setText(cubeNum);
                    break;
                case R.id.autoBtn_switch2Cube:
                    cubes.setBalance(mSwitch2);
                    if(autoFragCb_removeCube.isChecked() && auto_switch2Cubes > 0){
                        cubesList.remove(cubesList.size()-1);
                        auto_switch2Cubes --;
                    }else{
                        if (getTime().trim().toLowerCase().equals("start match")) {
                            Toast.makeText(getContext(), "Start the Match", Toast.LENGTH_SHORT).show();
                        }else if(getTime().trim().equals("0:00.000")) {
                            Toast.makeText(getContext(), "Auto has Ended", Toast.LENGTH_SHORT).show();
                        }else{
                            cubes.setTime(getTime());
                            cubesList.add(cubes);
                            auto_switch2Cubes++;
                        }
                    }
                    String cubeNum2 =auto_switch2Cubes+"";
                    autoFragTv_switch2Counter.setText(cubeNum2);
                    break;
                case R.id.autoBtn_scaleCube:
                    cubes.setBalance(mScale);
                    if(autoFragCb_removeCube.isChecked() && auto_scaleCubes > 0){
                        cubesList.remove(cubesList.size()-1);
                        auto_scaleCubes --;
                    }else{
                        if (getTime().trim().toLowerCase().equals("start match")) {
                            Toast.makeText(getContext(), "Start the Match", Toast.LENGTH_SHORT).show();
                        }else if(getTime().trim().equals("0:00.000")) {
                            Toast.makeText(getContext(), "Auto has Ended", Toast.LENGTH_SHORT).show();
                        }else{
                            cubes.setTime(getTime());
                            cubesList.add(cubes);
                            auto_scaleCubes++;
                        }
                    }
                    String cubeNum3 =auto_scaleCubes+"";
                    autoFragTv_scaleCounter.setText(cubeNum3);
                    break;
                case R.id.autoBtn_exchangeCube:
                    cubes.setBalance(mExchange);
                    if(autoFragCb_removeCube.isChecked() && auto_exchangeCubes > 0){
                        cubesList.remove(cubesList.size()-1);
                        auto_exchangeCubes --;
                    }else {
                        if (getTime().trim().toLowerCase().equals("start match")) {
                            Toast.makeText(getContext(), "Start the Match", Toast.LENGTH_SHORT).show();
                        }else if(getTime().trim().equals("0:00.000")) {
                            Toast.makeText(getContext(), "Auto has Ended", Toast.LENGTH_SHORT).show();
                        }else{
                            cubes.setTime(getTime());
                            cubesList.add(cubes);
                            auto_exchangeCubes++;
                        }
                    }
                    String cubeNum4 =auto_exchangeCubes+"";
                    autoFragTv_exchangeCounter.setText(cubeNum4);
                    break;
            }
        }

        public String getTime(){
            String time = autoFragBtn_startTimer.getText().toString();
            Toast.makeText(getContext(), time, Toast.LENGTH_SHORT).show();
            return time;
        }
    }
    public class OnControlTimer implements Button.OnClickListener{
        boolean hasStarted = false;

        CountDownTimer countDownTimer = new CountDownTimer(15000, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                long mili = millisUntilFinished % 1000;
                long seconds = (millisUntilFinished / 1000) % 60;
                long minutes = (millisUntilFinished / 1000) / 60;
                autoFragBtn_startTimer.setText(String.format(Locale.US,"%d:%d.%d", minutes, seconds, mili));
            }

            @Override
            public void onFinish() {
                autoFragBtn_startTimer.setText("0:00.000");
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
                autoFragBtn_startTimer.setText("Start Match");
            }
        }
    }

}
