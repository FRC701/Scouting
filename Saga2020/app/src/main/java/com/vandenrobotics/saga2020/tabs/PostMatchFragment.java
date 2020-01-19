package com.vandenrobotics.saga2020.tabs;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.vandenrobotics.saga2020.R;
import com.vandenrobotics.saga2020.activities.MatchActivity;
import com.vandenrobotics.saga2020.data.model.Stats;
import com.vandenrobotics.saga2020.data.repo.StatsRepo;
import com.vandenrobotics.saga2020.views.NumberPicker;

public class PostMatchFragment extends Fragment {

    private MatchActivity mActivity;
    private boolean viewAssigned = false;

    private ConstraintLayout teleField;

    private RadioButton endLevel1_Rb;
    private RadioButton endLevel2_Rb;
    private RadioButton endLevel3_Rb;
    private RadioButton endNone_Rb;
    private CheckBox disabled_Cb;
    private NumberPicker foul_Np;
    private NumberPicker techFoul_Np;
    private CheckBox redCard_Cb;
    private CheckBox yellowCard_Cb;
    private CheckBox disqualified_Cb;
    private EditText climbStart;

    private String mEvent;
    private int mMatchNum;
    private int mTeamNum;
    private int mMatchPos;
    private String mAlliance;

    private StatsRepo statsRepo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_post, container, false);
        mActivity = (MatchActivity)getActivity();
        mEvent = mActivity.mEvent;
        mMatchNum = mActivity.mMatchNumber;
        mTeamNum = mActivity.mTeamNumber;
        mMatchPos =mActivity.mDeviceNumber;
        mAlliance = mActivity.mAllianceColor;

        statsRepo = new StatsRepo();

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
        statsRepo.setPostStats(stats);
    }
    public Stats saveData(){
        Stats stats = new Stats();
        stats.setCompId(mEvent);
        stats.setMatchNum(mMatchNum);
        stats.setTeamNum(mTeamNum);
        int eL1 = (endLevel1_Rb.isChecked() ? 1 : 0);
        stats.setEndLevel1(eL1);
        int eL2 = (endLevel2_Rb.isChecked() ? 1 : 0);
        stats.setEndLevel2(eL2);
        int eL3 = (endLevel3_Rb.isChecked() ? 1 : 0);
        stats.setEndLevel3(eL3);
        int eNone = (endNone_Rb.isChecked() ? 1 : 0);
        stats.setYellowCard(eNone);
        int disabled = (disabled_Cb.isChecked() ? 1 : 0);
        stats.setDisabled(disabled);
        int rC = (redCard_Cb.isChecked() ? 1 : 0);
        stats.setRedCard(rC);
        int yC = (yellowCard_Cb.isChecked() ? 1 : 0);
        stats.setYellowCard(yC);
        int foul = (foul_Np.getValue());
        stats.setFoul(foul);
        int techFoul = (techFoul_Np.getValue());
        stats.setTechFoul(techFoul);
        int disqualified = (disqualified_Cb.isChecked() ? 1 : 0);
        stats.setDisqualified(disqualified);
        String cS = (climbStart.getText().toString());
        stats.setClimbTime(cS);
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
        endLevel1_Rb.setChecked(stats.getEndLevel1() == 1);
        endLevel2_Rb.setChecked(stats.getEndLevel2() == 1);
        endLevel3_Rb.setChecked(stats.getEndLevel3() == 1);
        endNone_Rb.setChecked(stats.getEndNone() == 1);
        disabled_Cb.setChecked(stats.getDisabled() == 1);
        redCard_Cb.setChecked(stats.getRedCard() == 1);
        yellowCard_Cb.setChecked(stats.getYellowCard() == 1);
        foul_Np.setValue(stats.getFoul());
        techFoul_Np.setValue(stats.getTechFoul());
        disqualified_Cb.setChecked(stats.getDisqualified() == 1);
        climbStart.setText(stats.getClimbTime());

    }
    public void assignViews(View view){
        try {
            endLevel1_Rb = (RadioButton)view.findViewById(R.id.platform1st_Rb);
            endLevel2_Rb = (RadioButton)view.findViewById(R.id.platform2nd_Rb);
            endLevel3_Rb = (RadioButton)view.findViewById(R.id.platform3rd_Rb);
            endNone_Rb = (RadioButton)view.findViewById(R.id.platformNone_Rb);
            disabled_Cb = (CheckBox) view.findViewById(R.id.disabled_Cb);
            foul_Np = (NumberPicker) view.findViewById(R.id.foul_Np);
            techFoul_Np = (NumberPicker) view.findViewById(R.id.techFoul_Np);
            redCard_Cb = (CheckBox) view.findViewById(R.id.redCard_Cb);
            yellowCard_Cb = (CheckBox) view.findViewById(R.id.yellowCard_Cb);
            disqualified_Cb = (CheckBox) view.findViewById(R.id.disqualified_Cb);
            climbStart = (EditText) view.findViewById(R.id.climbStart);

            viewAssigned = true;
       }catch(Exception e){
           e.printStackTrace();
           viewAssigned = false;
       }

    }

}
