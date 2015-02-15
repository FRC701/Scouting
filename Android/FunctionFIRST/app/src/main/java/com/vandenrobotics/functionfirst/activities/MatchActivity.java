package com.vandenrobotics.functionfirst.activities;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.PagerTabStrip;
import android.view.View;
import android.widget.TextView;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.dialogs.DialogListener;
import com.vandenrobotics.functionfirst.tabs.*;

import java.util.ArrayList;

public class MatchActivity extends FragmentActivity implements DialogListener {

    private FragmentTabHost mTabHost;
    private InitFragment mInitFrag;
    private AutoFragment mAutoFrag;
    private TeleFragment mTeleFrag;
    private PostFragment mPostFrag;

    public int mMatchNumber;
    public ArrayList<Integer> mTeams;
    public int mTeamNumber;
    public int mDeviceNumber;

    private int allianceColor;
    private int textColor;

    private TextView initTeamNumber;
    private TextView initMatchNumber;
    private TextView initDeviceNumber;
    private PagerTabStrip allianceColorBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        mMatchNumber = getIntent().getIntExtra("matchNumber", 1);
        mTeams = getIntent().getIntegerArrayListExtra("team_numbers");
        mTeamNumber = getIntent().getIntExtra("teamNumber", 0);
        System.out.println("Team-Number: " + mTeamNumber);
        mDeviceNumber = getIntent().getIntExtra("deviceNumber", 1);

        allianceColor = (mDeviceNumber>0 && mDeviceNumber<4)? R.color.FIRST_RED : R.color.FIRST_BLUE;
        textColor = (allianceColor==R.color.FIRST_RED)? R.color.Black : R.color.White;

        setupInfoBar();

        mTabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("tab_init")
                .setIndicator(getResources().getString(R.string.title_initTab), null), InitFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab_auto")
                .setIndicator(getResources().getString(R.string.title_autoTab), null), AutoFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab_tele")
                .setIndicator(getResources().getString(R.string.title_teleTab), null), TeleFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tab_post")
                .setIndicator(getResources().getString(R.string.title_postTab), null), PostFragment.class, null);
    }

    private void setupInfoBar(){
        initTeamNumber = (TextView)findViewById(R.id.initTeamNumber);
        initTeamNumber.setText("Team: " + mTeamNumber);
        initTeamNumber.setTextColor(getResources().getColor(textColor));

        initMatchNumber = (TextView)findViewById(R.id.initMatchNumber);
        initMatchNumber.setText("Match: " + mMatchNumber);
        initMatchNumber.setTextColor(getResources().getColor(textColor));

        initDeviceNumber = (TextView)findViewById(R.id.initDeviceNumber);
        String deviceText = (mDeviceNumber>0 && mDeviceNumber<4)? "Red " + mDeviceNumber : "Blue " + (mDeviceNumber-3);
        initDeviceNumber.setText("Device: " + deviceText);
        initDeviceNumber.setTextColor(getResources().getColor(textColor));

        allianceColorBar = (PagerTabStrip)findViewById(R.id.pager_title_strip);
        allianceColorBar.setDrawFullUnderline(true);
        allianceColorBar.setTabIndicatorColor(getResources().getColor(allianceColor));
        allianceColorBar.setBackgroundColor(getResources().getColor(allianceColor));

    }

    private void set_fragments() {
        mInitFrag = (InitFragment) getSupportFragmentManager().findFragmentByTag("tab_init");
        mAutoFrag = (AutoFragment) getSupportFragmentManager().findFragmentByTag("tab_auto");
        mTeleFrag = (TeleFragment) getSupportFragmentManager().findFragmentByTag("tab_tele");
        mPostFrag = (PostFragment) getSupportFragmentManager().findFragmentByTag("tab_post");
    }

    public void dialog_noShow(View view) {
        if (mInitFrag == null)
            mInitFrag = (InitFragment) getSupportFragmentManager().findFragmentByTag("tab_init");
        mInitFrag.command_noShow(view);
    }

    public void dialog_autoAction(View view) {
        if (mAutoFrag == null)
            mAutoFrag = (AutoFragment) getSupportFragmentManager().findFragmentByTag("tab_auto");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if (!dialog.equals(null)) {
            if (dialog.equals(mInitFrag.noShowDF)) {
                mInitFrag.setNoShow(true);
                this.finish();
            }
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        if (!dialog.equals(null)) {
            if (dialog.equals(mInitFrag.noShowDF)) {
                mInitFrag.setNoShow(false);
            }
        }
    }
}