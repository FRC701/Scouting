package com.vandenrobotics.saga2018.activities;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.vandenrobotics.saga2018.R;
import com.vandenrobotics.saga2018.data.model.MatchInfo;
import com.vandenrobotics.saga2018.data.repo.MatchInfoRepo;
import com.vandenrobotics.saga2018.data.repo.OwnershipRepo;
import com.vandenrobotics.saga2018.views.NumberPicker;

import java.io.File;
import java.util.List;

public class OwnershipActivity extends AppCompatActivity {

    private final String TAG = VaultActivity.class.getSimpleName();

    private String mEvent;
    private String mType;
    private int mCurMatch;

    private NumberPicker pickerMatches;

    private MatchInfo mMatchInfo;
    private MatchInfoRepo matchInfoRepo;
    private OwnershipRepo ownershipRepo;

    private CheckBox DataTransfer;
    private Button SendViaBluetooth;

    private final int MAX_MATCHES = 200; // a reasonable amount of matches to expect any event to have less than
    private static final int DISCOVER_DURATION = 300;
    private static final int REQUEST_BLU = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownership);

        mEvent = getIntent().getStringExtra("event");
        mType = getIntent().getStringExtra("type");

        matchInfoRepo = new MatchInfoRepo();
        ownershipRepo = new OwnershipRepo();

        if (matchInfoRepo.getMatchInfo(mEvent, mType).getScoutType() == "None") {
            MatchInfo matchInfo = new MatchInfo();
            matchInfo.setCompId(mEvent);
            matchInfo.setScoutType(mType);
            matchInfo.setCurrentMatch(1);
            matchInfo.setDeviceNum(0);
            matchInfoRepo.insert(matchInfo);
        }

        mMatchInfo = matchInfoRepo.getMatchInfoNext(mEvent, mType);
        mCurMatch = mMatchInfo.getCurrentMatch();

        pickerMatches = (NumberPicker) findViewById(R.id.pickerMatchOwn);
        pickerMatches.something(true);
        pickerMatches.setMinValue(1);
        pickerMatches.setMaxValue(MAX_MATCHES);
        pickerMatches.setValue(mCurMatch);

        SendViaBluetooth = (Button) findViewById(R.id.button_sendDataOwn);
        SendViaBluetooth.setEnabled(false);
        DataTransfer = (CheckBox) findViewById(R.id.checkBox_enableDataTransferOwn);
        DataTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataTransfer.isChecked())
                    enableDataTransfer();
                else
                    diableDataTransfer();
            }
        });
    }

    public void scoutOwn(View view){
        mCurMatch = pickerMatches.getValue();
        MatchInfo matchInfo = new MatchInfo();
        matchInfo.setCompId(mEvent);
        matchInfo.setScoutType(mType);
        matchInfo.setCurrentMatch(mCurMatch);
        matchInfoRepo.update(matchInfo);

        Intent intent = new Intent(this, OwnershipScoutingActivity.class);
        try {
            intent.putExtra("event", mEvent);
            intent.putExtra("matchNumber", mCurMatch);
            intent.putExtra("type", mType);

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        startActivity(intent);
        this.finish();

    }

    public void sendDataViaBluetooth3(View v) {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (btAdapter == null) {
            Toast.makeText(this, "Bluetooth is not supported on this device", Toast.LENGTH_LONG).show();
        } else {
            enableBluetooth();
        }
    }

    public void enableBluetooth(){
        Intent discoveryIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);

        discoveryIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, DISCOVER_DURATION);

        startActivityForResult(discoveryIntent, REQUEST_BLU);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode == DISCOVER_DURATION && requestCode == REQUEST_BLU){

            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_SEND);
            intent.setType("text/plain");
            File f = new File(Environment.getExternalStorageDirectory().toString(), "ScoutingData.db");
            Log.d(TAG, "Getting data from " + Environment.getExternalStorageDirectory().toString() + "/ScoutingData.db");
            intent.putExtra(android.content.Intent.EXTRA_STREAM, Uri.fromFile(f));

            PackageManager pm = getPackageManager();
            List<ResolveInfo> appList = pm.queryIntentActivities(intent, 0);

            if(appList.size() > 0){
                String packageName = null;
                String className = null;
                boolean found = false;
                for (ResolveInfo info: appList){
                    packageName = info.activityInfo.packageName;
                    if (packageName.equals("com.android.bluetooth")){
                        className = info.activityInfo.name;
                        found = true;
                        break;
                    }
                }
                if (!found){
                    Toast.makeText(this, "Bluetooth hasn't been found", Toast.LENGTH_LONG).show();
                }
                else {
                    intent.setClassName(packageName, className);
                    startActivity(intent);
                }
            }
            else{
                Toast.makeText(this, "Bluetooth is not cancelled", Toast.LENGTH_LONG).show();
            }
        }

    }
    private void enableDataTransfer(){
        SendViaBluetooth.setEnabled(true);
    }

    private void diableDataTransfer(){
        SendViaBluetooth.setEnabled(false);
    }
}
