package com.vandenrobotics.saga2018.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.vandenrobotics.saga2018.R;
import com.vandenrobotics.saga2018.data.model.MatchInfo;
import com.vandenrobotics.saga2018.data.model.Vault;
import com.vandenrobotics.saga2018.data.repo.MatchInfoRepo;
import com.vandenrobotics.saga2018.data.repo.VaultRepo;
import com.vandenrobotics.saga2018.tools.ExternalStorageTools;

import java.util.Locale;

public class VaultScoutingActivity extends AppCompatActivity {

    private String mEvent;
    private int mMatchNumber;
    private String mType;

    private MatchInfoRepo matchInfoRepo;
    private VaultRepo vaultRepo;

    private Button vaultBtn_startTimer;

    private Button redBtn_forceMain;
    private Button redBtn_forceTop;
    private Button redBtn_forceMid;
    private Button redBtn_forceBottom;
    private Button redBtn_levitateMain;
    private Button redBtn_levitateTop;
    private Button redBtn_levitateMid;
    private Button redBtn_levitateBottom;
    private Button redBtn_boostMain;
    private Button redBtn_boostTop;
    private Button redBtn_boostMid;
    private Button redBtn_boostBottom;

    private Button blueBtn_forceMain;
    private Button blueBtn_forceTop;
    private Button blueBtn_forceMid;
    private Button blueBtn_forceBottom;
    private Button blueBtn_levitateMain;
    private Button blueBtn_levitateTop;
    private Button blueBtn_levitateMid;
    private Button blueBtn_levitateBottom;
    private Button blueBtn_boostMain;
    private Button blueBtn_boostTop;
    private Button blueBtn_boostMid;
    private Button blueBtn_boostBottom;

    private String red_forceActTime;
    private String red_levitateActTime;
    private String red_boostActTime;

    private int red_forceCubes;
    private int red_levitateCubes;
    private int red_boostCubes;

    private int red_fceCubesAtTime;
    private int red_levCubesAtTime;
    private int red_bstCubesAtTime;

    private int blue_fceCubesAtTime;
    private int blue_levCubesAtTime;
    private int blue_bstCubesAtTime;

    private String blue_forceActTime;
    private String blue_levitateActTime;
    private String blue_boostActTime;

    private int blue_forceCubes;
    private int blue_levitateCubes;
    private int blue_boostCubes;

    private final String red = "Red";
    private final String blue = "Blue";

    private OnPowerUpClickListener onPowerUpClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault_scouting);
        mEvent = getIntent().getStringExtra("event");
        mType = getIntent().getStringExtra("type");
        mMatchNumber = getIntent().getIntExtra("matchNumber", 1);

        vaultRepo = new VaultRepo();
        matchInfoRepo = new MatchInfoRepo();

        vaultBtn_startTimer = (Button) findViewById(R.id.vaultBtn_startMatch);
        redBtn_forceMain = (Button) findViewById(R.id.redBtn_forceMain);
        redBtn_forceTop = (Button) findViewById(R.id.redBtn_forceTop);
        redBtn_forceMid = (Button) findViewById(R.id.redBtn_forceMid);
        redBtn_forceBottom = (Button) findViewById(R.id.redBtn_forceBottom);
        redBtn_levitateMain = (Button) findViewById(R.id.redBtn_levitateMain);
        redBtn_levitateTop = (Button) findViewById(R.id.redBtn_levitateTop);
        redBtn_levitateMid = (Button) findViewById(R.id.redBtn_levitateMid);
        redBtn_levitateBottom = (Button) findViewById(R.id.redBtn_levitateBottom);
        redBtn_boostMain = (Button) findViewById(R.id.redBtn_boostMain);
        redBtn_boostTop = (Button) findViewById(R.id.redBtn_boostTop);
        redBtn_boostMid = (Button) findViewById(R.id.redBtn_boostMid);
        redBtn_boostBottom = (Button) findViewById(R.id.redBtn_boostBottom);

        blueBtn_forceMain = (Button) findViewById(R.id.blueBtn_forceMain);
        blueBtn_forceTop = (Button) findViewById(R.id.blueBtn_forceTop);
        blueBtn_forceMid = (Button) findViewById(R.id.blueBtn_forceMid);
        blueBtn_levitateMain = (Button) findViewById(R.id.blueBtn_levitateMain);
        blueBtn_levitateTop = (Button) findViewById(R.id.blueBtn_levitateTop);
        blueBtn_levitateMid = (Button) findViewById(R.id.blueBtn_levitateMid);
        blueBtn_levitateBottom = (Button) findViewById(R.id.blueBtn_levitateBottom);
        blueBtn_boostMain = (Button) findViewById(R.id.blueBtn_boostMain);
        blueBtn_boostTop = (Button) findViewById(R.id.blueBtn_boostTop);
        blueBtn_boostMid = (Button) findViewById(R.id.blueBtn_boostMid);
        blueBtn_boostBottom = (Button) findViewById(R.id.blueBtn_boostBottom);
        blueBtn_forceBottom = (Button) findViewById(R.id.blueBtn_forceBottom);

        onPowerUpClickListener = new VaultScoutingActivity.OnPowerUpClickListener();

        vaultBtn_startTimer.setOnClickListener(new VaultScoutingActivity.OnControlTimer());

        redBtn_forceMain.setOnClickListener(onPowerUpClickListener);
        redBtn_forceTop.setOnClickListener(onPowerUpClickListener);
        redBtn_forceMid.setOnClickListener(onPowerUpClickListener);
        redBtn_forceBottom.setOnClickListener(onPowerUpClickListener);
        redBtn_levitateMain.setOnClickListener(onPowerUpClickListener);
        redBtn_levitateTop.setOnClickListener(onPowerUpClickListener);
        redBtn_levitateMid.setOnClickListener(onPowerUpClickListener);
        redBtn_levitateBottom.setOnClickListener(onPowerUpClickListener);
        redBtn_boostMain.setOnClickListener(onPowerUpClickListener);
        redBtn_boostTop.setOnClickListener(onPowerUpClickListener);
        redBtn_boostMid.setOnClickListener(onPowerUpClickListener);
        redBtn_boostBottom.setOnClickListener(onPowerUpClickListener);

        red_forceCubes = 0;
        red_forceActTime = "";
        red_levitateCubes = 0;
        red_levitateActTime = "";
        red_boostCubes = 0;
        red_boostActTime = "";
        red_fceCubesAtTime = 0;
        red_levCubesAtTime = 0;
        red_bstCubesAtTime = 0;

        blueBtn_forceMain.setOnClickListener(onPowerUpClickListener);
        blueBtn_forceTop.setOnClickListener(onPowerUpClickListener);
        blueBtn_forceMid.setOnClickListener(onPowerUpClickListener);
        blueBtn_forceBottom.setOnClickListener(onPowerUpClickListener);
        blueBtn_levitateMain.setOnClickListener(onPowerUpClickListener);
        blueBtn_levitateTop.setOnClickListener(onPowerUpClickListener);
        blueBtn_levitateMid.setOnClickListener(onPowerUpClickListener);
        blueBtn_levitateBottom.setOnClickListener(onPowerUpClickListener);
        blueBtn_boostMain.setOnClickListener(onPowerUpClickListener);
        blueBtn_boostTop.setOnClickListener(onPowerUpClickListener);
        blueBtn_boostMid.setOnClickListener(onPowerUpClickListener);
        blueBtn_boostBottom.setOnClickListener(onPowerUpClickListener);

        blue_forceCubes = 0;
        blue_forceActTime = "";
        blue_levitateCubes = 0;
        blue_levitateActTime = "";
        blue_boostCubes = 0;
        blue_boostActTime = "";
        blue_fceCubesAtTime = 0;
        blue_levCubesAtTime = 0;
        blue_bstCubesAtTime = 0;

        redBtn_forceMain.setEnabled(false);
        redBtn_levitateMain.setEnabled(false);
        redBtn_boostMain.setEnabled(false);
        blueBtn_forceMain.setEnabled(false);
        blueBtn_levitateMain.setEnabled(false);
        blueBtn_boostMain.setEnabled(false);
    }

    @Override
    public void onResume(){
        super.onResume();
        loadData();
    }

    public void loadData(){
        Vault vaultRed = vaultRepo.getVaultData(mEvent,mMatchNumber,red);
        if(vaultRed.getForceTime().equals("0") || vaultRed.getForceTime().equals("")){
            redBtn_forceMain.setText(R.string.force);
        }
        else{
            redBtn_forceMain.setText(vaultRed.getForceTime());
        }
        if(vaultRed.getLevitateTime().equals("0") || vaultRed.getLevitateTime().equals("")){
            redBtn_levitateMain.setText(R.string.levitate);
        }
        else{
            redBtn_levitateMain.setText(vaultRed.getLevitateTime());
        }
        if(vaultRed.getBoostTime().equals("0") || vaultRed.getBoostTime().equals("")){
            redBtn_boostMain.setText(R.string.boost);
        }
        else{
            redBtn_boostMain.setText(vaultRed.getBoostTime());
        }

        red_forceCubes = vaultRed.getForceCubes();
        red_forceActTime = vaultRed.getForceTime();
        red_levitateCubes = vaultRed.getLevitateCubes();
        red_levitateActTime = vaultRed.getLevitateTime();
        red_boostCubes = vaultRed.getBoostCubes();
        red_boostActTime = vaultRed.getBoostTime();
        red_fceCubesAtTime = vaultRed.getForceCubesAtTime();
        red_levCubesAtTime = vaultRed.getLevitateCubesAtTime();
        red_bstCubesAtTime = vaultRed.getBoostCubesAtTime();

        //checks how many cubes are in each power up of the vault and adds the numbers accordingly
        if (vaultRed.getForceCubes() == 1) {
            redBtn_forceBottom.setText(R.string.one);
            redBtn_forceBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_forceMain.setEnabled(true);
        } else if (vaultRed.getForceCubes() == 2) {
            redBtn_forceBottom.setText(R.string.one);
            redBtn_forceMid.setText(R.string.two);
            redBtn_forceBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_forceMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_forceMain.setEnabled(true);
        } else if (vaultRed.getForceCubes() == 3) {
            redBtn_forceBottom.setText(R.string.one);
            redBtn_forceMid.setText(R.string.two);
            redBtn_forceTop.setText(R.string.three);
            redBtn_forceBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_forceMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_forceTop.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_forceMain.setEnabled(true);
        }
        if (vaultRed.getLevitateCubes() == 1) {
            redBtn_levitateBottom.setText(R.string.one);
            redBtn_levitateBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
        } else if (vaultRed.getLevitateCubes() == 2) {
            redBtn_levitateBottom.setText(R.string.one);
            redBtn_levitateMid.setText(R.string.two);
            redBtn_levitateBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_levitateMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
        } else if (vaultRed.getLevitateCubes() == 3) {
            redBtn_levitateBottom.setText(R.string.one);
            redBtn_levitateMid.setText(R.string.two);
            redBtn_levitateTop.setText(R.string.three);
            redBtn_levitateBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_levitateMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_levitateTop.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_levitateMain.setEnabled(true);
        }
        if (vaultRed.getBoostCubes() == 1) {
            redBtn_boostBottom.setText(R.string.one);
            redBtn_boostBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_boostMain.setEnabled(true);
        } else if (vaultRed.getBoostCubes() == 2) {
            redBtn_boostBottom.setText(R.string.one);
            redBtn_boostMid.setText(R.string.two);
            redBtn_boostBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_boostMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_boostMain.setEnabled(true);
        } else if (vaultRed.getBoostCubes() == 3) {
            redBtn_boostBottom.setText(R.string.one);
            redBtn_boostMid.setText(R.string.two);
            redBtn_boostTop.setText(R.string.three);
            redBtn_boostBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_boostMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_boostTop.setBackgroundColor(getResources().getColor(R.color.Goldish));
            redBtn_boostMain.setEnabled(true);
        }

        Vault vaultBlue = vaultRepo.getVaultData(mEvent,mMatchNumber,blue);
        if(vaultBlue.getForceTime().equals("0") || vaultBlue.getForceTime().equals("")){
            blueBtn_forceMain.setText(R.string.force);
        }
        else{
            blueBtn_forceMain.setText(vaultBlue.getForceTime());
        }
        if(vaultBlue.getLevitateTime().equals("0") || vaultBlue.getLevitateTime().equals("")){
            blueBtn_levitateMain.setText(R.string.levitate);
        }
        else{
            blueBtn_levitateMain.setText(vaultBlue.getLevitateTime());
        }
        if(vaultBlue.getBoostTime().equals("0") || vaultBlue.getBoostTime().equals("")){
            blueBtn_boostMain.setText(R.string.boost);
        }
        else{
            blueBtn_boostMain.setText(vaultBlue.getBoostTime());
        }

        blue_forceCubes = vaultBlue.getForceCubes();
        blue_forceActTime = vaultBlue.getForceTime();
        blue_levitateCubes = vaultBlue.getLevitateCubes();
        blue_levitateActTime = vaultBlue.getLevitateTime();
        blue_boostCubes = vaultBlue.getBoostCubes();
        blue_boostActTime = vaultBlue.getBoostTime();
        blue_fceCubesAtTime = vaultBlue.getForceCubesAtTime();
        blue_levCubesAtTime = vaultBlue.getLevitateCubesAtTime();
        blue_bstCubesAtTime = vaultBlue.getBoostCubesAtTime();

        //checks how many cubes are in each power up of the vault and adds the numbers accordingly
        if (vaultBlue.getForceCubes() == 1) {
            blueBtn_forceBottom.setText(R.string.one);
            blueBtn_forceBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_forceMain.setEnabled(true);
        } else if (vaultBlue.getForceCubes() == 2) {
            blueBtn_forceBottom.setText(R.string.one);
            blueBtn_forceMid.setText(R.string.two);
            blueBtn_forceBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_forceMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_forceMain.setEnabled(true);
        } else if (vaultBlue.getForceCubes() == 3) {
            blueBtn_forceBottom.setText(R.string.one);
            blueBtn_forceMid.setText(R.string.two);
            blueBtn_forceTop.setText(R.string.three);
            blueBtn_forceBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_forceMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_forceTop.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_forceMain.setEnabled(true);
        }
        if (vaultBlue.getLevitateCubes() == 1) {
            blueBtn_levitateBottom.setText(R.string.one);
            blueBtn_levitateBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
        } else if (vaultBlue.getLevitateCubes() == 2) {
            blueBtn_levitateBottom.setText(R.string.one);
            blueBtn_levitateMid.setText(R.string.two);
            blueBtn_levitateBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_levitateMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
        } else if (vaultBlue.getLevitateCubes() == 3) {
            blueBtn_levitateBottom.setText(R.string.one);
            blueBtn_levitateMid.setText(R.string.two);
            blueBtn_levitateTop.setText(R.string.three);
            blueBtn_levitateBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_levitateMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_levitateTop.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_levitateMain.setEnabled(true);
        }
        if (vaultBlue.getBoostCubes() == 1) {
            blueBtn_boostBottom.setText(R.string.one);
            blueBtn_boostBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_boostMain.setEnabled(true);
        } else if (vaultBlue.getBoostCubes() == 2) {
            blueBtn_boostBottom.setText(R.string.one);
            blueBtn_boostMid.setText(R.string.two);
            blueBtn_boostBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_boostMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_boostMain.setEnabled(true);
        } else if (vaultBlue.getBoostCubes() == 3) {
            blueBtn_boostBottom.setText(R.string.one);
            blueBtn_boostMid.setText(R.string.two);
            blueBtn_boostTop.setText(R.string.three);
            blueBtn_boostBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_boostMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_boostTop.setBackgroundColor(getResources().getColor(R.color.Goldish));
            blueBtn_boostMain.setEnabled(true);
        }
    }

    public void finishVault(View view){
        MatchInfo matchInfo =  new MatchInfo();
        matchInfo.setCompId(mEvent);
        matchInfo.setScoutType(mType);
        matchInfo.setCurrentMatch(mMatchNumber+1);
        matchInfo.setDeviceNum(0);
        matchInfoRepo.update(matchInfo);

        saveData();

        ExternalStorageTools.writeDatabaseToES();
        Intent intent = new Intent(this, VaultActivity.class);
        intent.putExtra("event", mEvent);
        intent.putExtra("type", mType);
        startActivity(intent);
        this.finish();
    }

    public void saveData(){
        Vault vault = new Vault();
        vault.setCompId(mEvent);
        vault.setMatchNum(mMatchNumber);
        vault.setAlliance(red);
        vault.setForceTime(red_forceActTime);
        vault.setForceCubes(red_forceCubes);
        vault.setLevitateTime(red_levitateActTime);
        vault.setLevitateCubes(red_levitateCubes);
        vault.setBoostTime(red_boostActTime);
        vault.setBoostCubes(red_boostCubes);
        vault.setForceCubesAtTime(red_fceCubesAtTime);
        vault.setLevitateCubesAtTime(red_levCubesAtTime);
        vault.setBoostCubesAtTime(red_bstCubesAtTime);

        if (vaultRepo.insert(vault) == -1){
            vaultRepo.update(vault);
        }

        Vault vault2 = new Vault();
        vault2.setCompId(mEvent);
        vault2.setMatchNum(mMatchNumber);
        vault2.setAlliance(blue);
        vault2.setForceTime(blue_forceActTime);
        vault2.setForceCubes(blue_forceCubes);
        vault2.setLevitateTime(blue_levitateActTime);
        vault2.setLevitateCubes(blue_levitateCubes);
        vault2.setBoostTime(blue_boostActTime);
        vault2.setBoostCubes(blue_boostCubes);
        vault2.setForceCubesAtTime(blue_fceCubesAtTime);
        vault2.setLevitateCubesAtTime(blue_levCubesAtTime);
        vault2.setBoostCubesAtTime(blue_bstCubesAtTime);

        if (vaultRepo.insert(vault2) == -1){
            vaultRepo.update(vault2);
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
                vaultBtn_startTimer.setText(String.format(Locale.US,"%d:%d.%d", minuets, seconds, mili));
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
                vaultBtn_startTimer.setText(R.string.start_match);
            }
        }
    }

    public class OnPowerUpClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.redBtn_forceBottom:
                    if (red_forceCubes == 0) {
                        red_forceCubes = 1;
                        redBtn_forceBottom.setText(R.string.one);
                        redBtn_forceBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
                        redBtn_forceMain.setEnabled(true);
                    } else if (red_forceCubes == 1) {
                        red_forceCubes = 0;
                        redBtn_forceBottom.setText("");
                        redBtn_forceBottom.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                        redBtn_forceMain.setEnabled(false);
                    }
                    break;
                case R.id.redBtn_forceMid:
                    if (red_forceCubes == 1) {
                        red_forceCubes = 2;
                        redBtn_forceMid.setText(R.string.two);
                        redBtn_forceMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
                    } else if (red_forceCubes == 2) {
                        red_forceCubes = 1;
                        redBtn_forceMid.setText("");
                        redBtn_forceMid.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                    }
                    break;
                case R.id.redBtn_forceTop:
                    if (red_forceCubes == 2) {
                        red_forceCubes = 3;
                        redBtn_forceTop.setText(R.string.three);
                        redBtn_forceTop.setBackgroundColor(getResources().getColor(R.color.Goldish));
                    } else if (red_forceCubes == 3) {
                        red_forceCubes = 2;
                        redBtn_forceTop.setText("");
                        redBtn_forceTop.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                    }
                    break;
                case R.id.redBtn_forceMain:
                    if (red_forceActTime.equals("")) {
                        red_forceActTime = vaultBtn_startTimer.getText().toString();
                        redBtn_forceMain.setText(red_forceActTime);
                        red_fceCubesAtTime = red_forceCubes;
                    } else {
                        red_forceActTime = "";
                        redBtn_forceMain.setText(R.string.force);
                    }
                    break;
                case R.id.redBtn_levitateBottom:
                    if (red_levitateCubes == 0) {
                        red_levitateCubes = 1;
                        redBtn_levitateBottom.setText(R.string.one);
                        redBtn_levitateBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
                    } else if (red_levitateCubes == 1) {
                        red_levitateCubes = 0;
                        redBtn_levitateBottom.setText("");
                        redBtn_levitateBottom.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                    }
                    break;
                case R.id.redBtn_levitateMid:
                    if (red_levitateCubes == 1) {
                        red_levitateCubes = 2;
                        redBtn_levitateMid.setText(R.string.two);
                        redBtn_levitateMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
                    } else if (red_levitateCubes == 2) {
                        red_levitateCubes = 1;
                        redBtn_levitateMid.setText("");
                        redBtn_levitateMid.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                    }
                    break;
                case R.id.redBtn_levitateTop:
                    if (red_levitateCubes == 2) {
                        red_levitateCubes = 3;
                        redBtn_levitateTop.setText(R.string.three);
                        redBtn_levitateTop.setBackgroundColor(getResources().getColor(R.color.Goldish));
                        redBtn_levitateMain.setEnabled(true);
                    } else if (red_levitateCubes == 3) {
                        red_levitateCubes = 2;
                        redBtn_levitateTop.setText("");
                        redBtn_levitateTop.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                        redBtn_levitateMain.setEnabled(false);
                    }
                    break;
                case R.id.redBtn_levitateMain:
                    if (red_levitateActTime.equals("")) {
                        red_levitateActTime = vaultBtn_startTimer.getText().toString();
                        redBtn_levitateMain.setText(red_levitateActTime);
                        red_levCubesAtTime = red_levitateCubes;
                    } else {
                        red_levitateActTime = "";
                        redBtn_levitateMain.setText(R.string.levitate);
                    }
                    break;
                case R.id.redBtn_boostBottom:
                    if (red_boostCubes == 0) {
                        red_boostCubes = 1;
                        redBtn_boostBottom.setText(R.string.one);
                        redBtn_boostBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
                        redBtn_boostMain.setEnabled(true);
                    } else if (red_boostCubes == 1) {
                        red_boostCubes = 0;
                        redBtn_boostBottom.setText("");
                        redBtn_boostBottom.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                        redBtn_boostMain.setEnabled(false);
                    }
                    break;
                case R.id.redBtn_boostMid:
                    if (red_boostCubes == 1) {
                        red_boostCubes = 2;
                        redBtn_boostMid.setText(R.string.two);
                        redBtn_boostMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
                    } else if (red_boostCubes == 2) {
                        red_boostCubes = 1;
                        redBtn_boostMid.setText("");
                        redBtn_boostMid.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                    }
                    break;
                case R.id.redBtn_boostTop:
                    if (red_boostCubes == 2) {
                        red_boostCubes = 3;
                        redBtn_boostTop.setText(R.string.three);
                        redBtn_boostTop.setBackgroundColor(getResources().getColor(R.color.Goldish));
                    } else if (red_boostCubes == 3) {
                        red_boostCubes = 2;
                        redBtn_boostTop.setText("");
                        redBtn_boostTop.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                    }
                    break;
                case R.id.redBtn_boostMain:
                    if (red_boostActTime.equals("")) {
                        red_boostActTime = vaultBtn_startTimer.getText().toString();
                        redBtn_boostMain.setText(red_boostActTime);
                        red_bstCubesAtTime = red_boostCubes;
                    } else {
                        red_boostActTime = "";
                        redBtn_boostMain.setText(R.string.boost);
                    }
                    break;

                case R.id.blueBtn_forceBottom:
                    if (blue_forceCubes == 0) {
                        blue_forceCubes = 1;
                        blueBtn_forceBottom.setText(R.string.one);
                        blueBtn_forceBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
                        blueBtn_forceMain.setEnabled(true);
                    } else if (blue_forceCubes == 1) {
                        blue_forceCubes = 0;
                        blueBtn_forceBottom.setText("");
                        blueBtn_forceBottom.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                        blueBtn_forceMain.setEnabled(false);
                    }
                    break;
                case R.id.blueBtn_forceMid:
                    if (blue_forceCubes == 1) {
                        blue_forceCubes = 2;
                        blueBtn_forceMid.setText(R.string.two);
                        blueBtn_forceMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
                    } else if (blue_forceCubes == 2) {
                        blue_forceCubes = 1;
                        blueBtn_forceMid.setText("");
                        blueBtn_forceMid.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                    }
                    break;
                case R.id.blueBtn_forceTop:
                    if (blue_forceCubes == 2) {
                        blue_forceCubes = 3;
                        blueBtn_forceTop.setText(R.string.three);
                        blueBtn_forceTop.setBackgroundColor(getResources().getColor(R.color.Goldish));
                    } else if (blue_forceCubes == 3) {
                        blue_forceCubes = 2;
                        blueBtn_forceTop.setText("");
                        blueBtn_forceTop.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                    }
                    break;
                case R.id.blueBtn_forceMain:
                    if (blue_forceActTime.equals("")) {
                        blue_forceActTime = vaultBtn_startTimer.getText().toString();
                        blueBtn_forceMain.setText(blue_forceActTime);
                        blue_fceCubesAtTime = blue_forceCubes;
                    } else {
                        blue_forceActTime = "";
                        blueBtn_forceMain.setText(R.string.force);
                    }
                    break;
                case R.id.blueBtn_levitateBottom:
                    if (blue_levitateCubes == 0) {
                        blue_levitateCubes = 1;
                        blueBtn_levitateBottom.setText(R.string.one);
                        blueBtn_levitateBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
                    } else if (blue_levitateCubes == 1) {
                        blue_levitateCubes = 0;
                        blueBtn_levitateBottom.setText("");
                        blueBtn_levitateBottom.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                    }
                    break;
                case R.id.blueBtn_levitateMid:
                    if (blue_levitateCubes == 1) {
                        blue_levitateCubes = 2;
                        blueBtn_levitateMid.setText(R.string.two);
                        blueBtn_levitateMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
                    } else if (blue_levitateCubes == 2) {
                        blue_levitateCubes = 1;
                        blueBtn_levitateMid.setText("");
                        blueBtn_levitateMid.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                    }
                    break;
                case R.id.blueBtn_levitateTop:
                    if (blue_levitateCubes == 2) {
                        blue_levitateCubes = 3;
                        blueBtn_levitateTop.setText(R.string.three);
                        blueBtn_levitateTop.setBackgroundColor(getResources().getColor(R.color.Goldish));
                        blueBtn_levitateMain.setEnabled(true);
                    } else if (blue_levitateCubes == 3) {
                        blue_levitateCubes = 2;
                        blueBtn_levitateTop.setText("");
                        blueBtn_levitateTop.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                        blueBtn_levitateMain.setEnabled(false);
                    }
                    break;
                case R.id.blueBtn_levitateMain:
                    if (blue_levitateActTime.equals("")) {
                        blue_levitateActTime = vaultBtn_startTimer.getText().toString();
                        blueBtn_levitateMain.setText(blue_levitateActTime);
                        blue_levCubesAtTime = blue_levitateCubes;
                    } else {
                        blue_levitateActTime = "";
                        blueBtn_levitateMain.setText(R.string.levitate);
                    }
                    break;
                case R.id.blueBtn_boostBottom:
                    if (blue_boostCubes == 0) {
                        blue_boostCubes = 1;
                        blueBtn_boostBottom.setText(R.string.one);
                        blueBtn_boostBottom.setBackgroundColor(getResources().getColor(R.color.Goldish));
                        blueBtn_boostMain.setEnabled(true);
                    } else if (blue_boostCubes == 1) {
                        blue_boostCubes = 0;
                        blueBtn_boostBottom.setText("");
                        blueBtn_boostBottom.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                        blueBtn_boostMain.setEnabled(false);
                    }
                    break;
                case R.id.blueBtn_boostMid:
                    if (blue_boostCubes == 1) {
                        blue_boostCubes = 2;
                        blueBtn_boostMid.setText(R.string.two);
                        blueBtn_boostMid.setBackgroundColor(getResources().getColor(R.color.Goldish));
                    } else if (blue_boostCubes == 2) {
                        blue_boostCubes = 1;
                        blueBtn_boostMid.setText("");
                        blueBtn_boostMid.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                    }
                    break;
                case R.id.blueBtn_boostTop:
                    if (blue_boostCubes == 2) {
                        blue_boostCubes = 3;
                        blueBtn_boostTop.setText(R.string.three);
                        blueBtn_boostTop.setBackgroundColor(getResources().getColor(R.color.Goldish));
                    } else if (blue_boostCubes == 3) {
                        blue_boostCubes = 2;
                        blueBtn_boostTop.setText("");
                        blueBtn_boostTop.setBackgroundColor(getResources().getColor(R.color.DarkGrey));
                    }
                    break;
                case R.id.blueBtn_boostMain:
                    if (blue_boostActTime.equals("")) {
                        blue_boostActTime = vaultBtn_startTimer.getText().toString();
                        blueBtn_boostMain.setText(blue_boostActTime);
                        blue_bstCubesAtTime = blue_boostCubes;
                    } else {
                        blue_boostActTime = "";
                        blueBtn_boostMain.setText(R.string.boost);
                    }
                    break;
            }

        }
    }
}
