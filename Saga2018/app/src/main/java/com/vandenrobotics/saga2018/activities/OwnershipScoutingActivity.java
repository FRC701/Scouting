package com.vandenrobotics.saga2018.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.vandenrobotics.saga2018.R;
import com.vandenrobotics.saga2018.data.model.MatchInfo;
import com.vandenrobotics.saga2018.data.model.Ownership;
import com.vandenrobotics.saga2018.data.repo.MatchInfoRepo;
import com.vandenrobotics.saga2018.data.repo.OwnershipRepo;
import com.vandenrobotics.saga2018.tools.ExternalStorageTools;

import java.util.ArrayList;
import java.util.Locale;

import static com.vandenrobotics.saga2018.app.App.getContext;

public class OwnershipScoutingActivity extends AppCompatActivity {

    private static final String TAG = OwnershipScoutingActivity.class.getSimpleName();

    private String mEvent;
    private int mMatchNumber;
    private String mType;

    private MatchInfoRepo matchInfoRepo;

    private Button ownBtn_startTimer;

    private Button ownBtn_undo;

    private Button redBtn_switch1;
    private Button redBtn_switch2;
    private Button redBtn_scale;

    private Button blueBtn_switch1;
    private Button blueBtn_switch2;
    private Button blueBtn_scale;

    private ImageView ownImage_switch1;
    private ImageView ownImage_scale;
    private ImageView ownImage_switch2;

    private ToggleButton ownTb_enableOwnership;

    private int own_switch1State = 0;
    private int own_scaleState = 0;
    private int own_switch2State = 0;

    private boolean enableOwnershipChange = false;

    private OnPossessionClickListener onPossessionClickListener;

    private final int redSwitch = R.drawable.switchredtop;
    private final int redScale = R.drawable.scaleredtop;

    private final int blueSwitch = R.drawable.switchbluebottom;
    private final int blueScale = R.drawable.scalebluebottom;

    private final int blankSwitch = R.drawable.blankswitch;
    private final int blankScale = R.drawable.blankscale;

    private final String mSwitch1 = "Sw1";
    private final String mScale = "Scl";
    private final String mSwitch2 = "Sw2";

    private final String mNone = "None";

    private final String red = "Red";
    private final String blue = "Blue";

    private OwnershipRepo ownershipRepo;
    private Ownership lastOwnership;
    private ArrayList<Ownership> ownerships;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownership_scouting);
        mEvent = getIntent().getStringExtra("event");
        mType = getIntent().getStringExtra("type");
        mMatchNumber = getIntent().getIntExtra("matchNumber", 1);

        matchInfoRepo =  new MatchInfoRepo();

        ownershipRepo = new OwnershipRepo();
        lastOwnership = new Ownership();
        ownerships = new ArrayList<>();

        ownBtn_startTimer = (Button) findViewById(R.id.ownBtn_startMatch);

        ownBtn_startTimer.setOnClickListener(new OwnershipScoutingActivity.OnControlTimer());

        redBtn_scale = (Button) findViewById(R.id.redBtn_scale);
        redBtn_switch1 = (Button) findViewById(R.id.redBtn_switch1);
        redBtn_switch2 = (Button) findViewById(R.id.redBtn_switch2);
        blueBtn_scale = (Button) findViewById(R.id.blueBtn_scale);
        blueBtn_switch1 = (Button) findViewById(R.id.blueBtn_switch1);
        blueBtn_switch2 = (Button) findViewById(R.id.blueBtn_switch2);

        ownBtn_undo = (Button) findViewById(R.id.ownBtn_undo);

        ownImage_scale = (ImageView) findViewById(R.id.ownImage_scale);
        ownImage_switch1 = (ImageView) findViewById(R.id.ownImage_switch1);
        ownImage_switch2 = (ImageView) findViewById(R.id.ownImage_switch2);

        ownTb_enableOwnership = (ToggleButton) findViewById(R.id.ownTb_ownershipChange);

        ownImage_switch1.setImageResource(R.drawable.blankswitch);
        ownImage_switch2.setImageResource(R.drawable.blankswitch);
        ownImage_scale.setImageResource(R.drawable.blankswitch);

        ownTb_enableOwnership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enableOwnershipChange){
                    ownImage_switch1.setImageResource(blankSwitch);
                    ownImage_scale.setImageResource(blankScale);
                    ownImage_switch2.setImageResource(blankSwitch);
                    own_switch1State = 0;
                    own_scaleState = 0;
                    own_switch2State = 0;
                    enableOwnershipChange = false;
                    redBtn_switch1.setEnabled(true);
                    redBtn_scale.setEnabled(true);
                    redBtn_switch2.setEnabled(true);
                    ownBtn_undo.setEnabled(true);
                    blueBtn_switch1.setEnabled(true);
                    blueBtn_scale.setEnabled(true);
                    blueBtn_switch2.setEnabled(true);
                }else{
                    redBtn_switch1.setEnabled(false);
                    redBtn_scale.setEnabled(false);
                    redBtn_switch2.setEnabled(false);
                    ownBtn_undo.setEnabled(false);
                    blueBtn_switch1.setEnabled(false);
                    blueBtn_scale.setEnabled(false);
                    blueBtn_switch2.setEnabled(false);
                    enableOwnershipChange = true;
                }
            }
        });

        onPossessionClickListener = new OwnershipScoutingActivity.OnPossessionClickListener();

        redBtn_switch1.setOnClickListener(onPossessionClickListener);
        redBtn_scale.setOnClickListener(onPossessionClickListener);
        redBtn_switch2.setOnClickListener(onPossessionClickListener);
        blueBtn_switch1.setOnClickListener(onPossessionClickListener);
        blueBtn_scale.setOnClickListener(onPossessionClickListener);
        blueBtn_switch2.setOnClickListener(onPossessionClickListener);

        ownBtn_undo.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!ownerships.isEmpty()) {
                    switch (lastOwnership.getBalance()) {
                        case mSwitch1:
                            if (own_switch1State == 0){
                                if (lastOwnership.getOwner().equals(red)) {
                                    own_switch1State = 1;
                                    ownImage_switch1.setImageResource(redSwitch);
                                }else{
                                    own_switch1State = 2;
                                    ownImage_switch1.setImageResource(blueSwitch);
                                }
                            }else{
                                own_switch1State = 0;
                                ownImage_switch1.setImageResource(blankSwitch);
                            }
                            break;
                        case mScale:
                            if (own_scaleState == 0){
                                if (lastOwnership.getOwner().equals(red)) {
                                    own_scaleState = 1;
                                    ownImage_scale.setImageResource(redScale);
                                }else{
                                    own_scaleState = 2;
                                    ownImage_scale.setImageResource(blueScale);
                                }
                            }else{
                                own_switch1State = 0;
                                ownImage_switch1.setImageResource(blankScale);
                            }
                            break;
                        case mSwitch2:
                            if (own_switch2State == 0){
                                if (lastOwnership.getOwner().equals(red)) {
                                    own_switch2State = 1;
                                    ownImage_switch2.setImageResource(redSwitch);
                                }else{
                                    own_switch1State = 2;
                                    ownImage_switch2.setImageResource(blueSwitch);
                                }
                            }else{
                                own_switch1State = 0;
                                ownImage_switch2.setImageResource(blankSwitch);
                            }
                            break;
                    }
                    ownerships.remove(lastOwnership);
                }
            }
        });
    }

    public void checkOwnership(){
        if (!ownerships.isEmpty()) {
            if (ownershipRepo.checkForSameMatch(mEvent, mMatchNumber) == 0){
                for (Ownership ownership : ownerships) {
                    Log.d(TAG, ownership.getTime().trim().toLowerCase());
                    if(!ownership.getTime().trim().toLowerCase().equals("start match")){
                        ownershipRepo.insert(ownership);
                        Log.d(TAG, ownership.getTime().trim().toLowerCase());
                    }
                }
            }else{
                ownershipRepo.deleteForCompAndMatch(mEvent, mMatchNumber);
                for (Ownership ownership : ownerships) {
                    ownershipRepo.insert(ownership);
                }
            }
        }
    }

    @Override
    public void onResume() {
        //when resumed, loads data onto the tab
        super.onResume();
        loadData();

    }

    public void loadData(){
        if (ownershipRepo.checkForSameMatch(mEvent, mMatchNumber) == 0){
            enableOwnershipChange = false;
        }else {
            enableOwnershipChange = true;
            redBtn_switch1.setEnabled(false);
            redBtn_scale.setEnabled(false);
            redBtn_switch2.setEnabled(false);
            ownBtn_undo.setEnabled(false);
            blueBtn_switch1.setEnabled(false);
            blueBtn_scale.setEnabled(false);
            blueBtn_switch2.setEnabled(false);
            switch (ownershipRepo.getLastSwitch1Owner(mEvent, mMatchNumber)) {
                case mNone:
                    own_switch1State = 0;
                    ownImage_switch1.setImageResource(blankSwitch);
                    break;
                case red:
                    own_switch1State = 1;
                    ownImage_switch1.setImageResource(redSwitch);
                    break;
                case blue:
                    own_switch1State = 2;
                    ownImage_switch1.setImageResource(blueSwitch);
                    break;
            }
            switch (ownershipRepo.getLastScaleOwner(mEvent, mMatchNumber)) {
                case mNone:
                    own_scaleState = 0;
                    ownImage_scale.setImageResource(blankScale);
                    break;
                case red:
                    own_scaleState = 1;
                    ownImage_scale.setImageResource(redScale);
                    break;
                case blue:
                    own_scaleState = 2;
                    ownImage_scale.setImageResource(blueScale);
                    break;
            }
            switch (ownershipRepo.getLastSwitch2Owner(mEvent, mMatchNumber)) {
                case mNone:
                    own_switch2State = 0;
                    ownImage_switch2.setImageResource(blankSwitch);
                    break;
                case red:
                    own_switch1State = 1;
                    ownImage_switch2.setImageResource(redSwitch);
                    break;
                case blue:
                    own_switch1State = 2;
                    ownImage_switch2.setImageResource(blueSwitch);
                    break;
            }
        }
        ownTb_enableOwnership.setChecked(enableOwnershipChange);
    }

    public void finishOwn(View view){
        MatchInfo matchInfo =  new MatchInfo();
        matchInfo.setCompId(mEvent);
        matchInfo.setScoutType(mType);
        matchInfo.setCurrentMatch(mMatchNumber+1);
        matchInfo.setDeviceNum(0);
        matchInfoRepo.update(matchInfo);

        checkOwnership();

        ExternalStorageTools.writeDatabaseToES();
        Intent intent = new Intent(this, OwnershipActivity.class);
        intent.putExtra("event", mEvent);
        intent.putExtra("type", mType);
        startActivity(intent);
        this.finish();

    }

    public class OnPossessionClickListener implements Button.OnClickListener{
        @Override
        public void onClick(View view) {
            Ownership ownership = new Ownership();
            ownership.setCompId(mEvent);
            ownership.setMatchNum(mMatchNumber);
            switch(view.getId()){
                case R.id.redBtn_switch1://checks which button was pushed
                    ownership.setBalance(mSwitch1);
                    if (own_switch1State == 0 || own_switch1State == 2){
                        own_switch1State = 1;
                        ownImage_switch1.setImageResource(redSwitch);
                        ownership.setOwner(red);
                    }else{
                        own_switch1State = 0;
                        ownImage_switch1.setImageResource(blankSwitch);
                        ownership.setOwner(mNone);
                    }
                    break;
                case R.id.redBtn_scale:
                    ownership.setBalance(mScale);
                    if(own_scaleState == 0 || own_scaleState == 2){
                        own_scaleState = 1;
                        ownImage_scale.setImageResource(redScale);
                        ownership.setOwner(red);
                    }else{
                        own_scaleState = 0;
                        ownImage_scale.setImageResource(blankScale);
                        ownership.setOwner(mNone);
                    }
                    break;
                case R.id.redBtn_switch2:
                    ownership.setBalance(mSwitch2);
                    if(own_switch2State == 0 || own_switch2State == 2){
                        own_switch2State = 1;
                        ownImage_switch2.setImageResource(redSwitch);
                        ownership.setOwner(red);
                    }else{
                        own_switch2State = 0;
                        ownImage_switch2.setImageResource(blankSwitch);
                        ownership.setOwner(mNone);
                    }
                    break;
                case R.id.blueBtn_switch1://checks which button was pushed
                ownership.setBalance(mSwitch1);
                if (own_switch1State == 0 || own_switch1State == 1){
                    own_switch1State = 2;
                    ownImage_switch1.setImageResource(blueSwitch);
                    ownership.setOwner(blue);
                }else{
                    own_switch1State = 0;
                    ownImage_switch1.setImageResource(blankSwitch);
                    ownership.setOwner(mNone);
                }
                break;
                case R.id.blueBtn_scale:
                    ownership.setBalance(mScale);
                    if(own_scaleState == 0 || own_scaleState == 1){
                        own_scaleState = 2;
                        ownImage_scale.setImageResource(blueScale);
                        ownership.setOwner(blue);
                    }else{
                        own_scaleState = 0;
                        ownImage_scale.setImageResource(blankScale);
                        ownership.setOwner(mNone);
                    }
                    break;
                case R.id.blueBtn_switch2:
                    ownership.setBalance(mSwitch2);
                    if(own_switch2State == 0 || own_switch2State == 1){
                        own_switch2State = 2;
                        ownImage_switch2.setImageResource(blueSwitch);
                        ownership.setOwner(blue);
                    }else{
                        own_switch2State = 0;
                        ownImage_switch2.setImageResource(blankSwitch);
                        ownership.setOwner(mNone);
                    }
                    break;
            }

            String time = ownBtn_startTimer.getText().toString();
            Toast.makeText(getContext(), time, Toast.LENGTH_SHORT).show();
            ownership.setTime(time);
            lastOwnership = ownership;
            ownerships.add(ownership);
        }
    }

    public class OnControlTimer implements Button.OnClickListener{
        boolean hasStarted = false;

        CountDownTimer countDownTimer = new CountDownTimer(150000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                long mili = millisUntilFinished % 1000;
                long seconds = (millisUntilFinished / 1000) % 60;
                long minuets = (millisUntilFinished / 1000) / 60;
                ownBtn_startTimer.setText(String.format(Locale.US,"%d:%d.%d", minuets, seconds, mili));
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
                ownBtn_startTimer.setText(R.string.start_match);
            }
        }
    }
}
