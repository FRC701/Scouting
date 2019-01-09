package com.vandenrobotics.saga.tabs;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.vandenrobotics.saga.R;
import com.vandenrobotics.saga.activities.MatchActivity;
import com.vandenrobotics.saga.data.model.Stats;
import com.vandenrobotics.saga.data.repo.StatsRepo;
import com.vandenrobotics.saga.dialogs.NoShowDialogFragment;
import com.vandenrobotics.saga.tools.ImageTools;

//import com.vandenrobotics.saga2018.model.;

public class PreMatchFragment extends Fragment {

    private MatchActivity mActivity;

    private CheckBox noShow;
    private RadioButton level1_Rb;
    private RadioButton level2_Rb;
    private RadioButton preloadCargo_Rb;
    private RadioButton preloadHatch_Rb;
    private EditText ssComments_Et;

    private StatsRepo statsRepo;

    private String mEvent;
    private int mMatchNum;
    private int mTeamNum;

    private int noShowValue;


    public NoShowDialogFragment noShowDF;


    private boolean viewsAssigned = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_init, container, false);
        mActivity = (MatchActivity) getActivity();
        mEvent = mActivity.mEvent;
        mMatchNum = mActivity.mMatchNumber;
        mTeamNum = mActivity.mTeamNumber;
        statsRepo = new StatsRepo();

        noShowDF = new NoShowDialogFragment();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        assignViews(view);
        //if(viewsAssigned) loadData(mInitData);
    }

//    @Override
//    public void onPause(){
//        super.onPause();
//        noShowValue = (noShow.isChecked() == true ? 1 : 0);
//        statsRepo.setInitStats(mEvent, mMatchNum, mTeamNum, noShowValue);
//        viewsAssigned=false;
//    }
//
//    @Override
//    public void onResume(){
//        super.onResume();
//        assignViews(getView());
//        //if(viewsAssigned) loadData(mInitData);
//    }
//
//@Override
    public void onPause(){
        super.onPause();
        Stats stats = saveData();
        statsRepo.setAutoStats(stats);
    }

    public Stats saveData(){
        Stats stat = new Stats();
        stat.setCompId(mEvent);
        stat.setMatchNum(mMatchNum);
        stat.setTeamNum(mTeamNum);
        int hA = (autoFragCb_hadAuto.isChecked() ? 1 : 0);
        stat.setHadAuto(hA);
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
    }
    private void assignViews(View view){
        try{
            noShow = (CheckBox) view.findViewById(R.id.noShow_Cb);
            level1_Rb = (RadioButton) view.findViewById(R.id.level1_Rb);
            level2_Rb = (RadioButton) view.findViewById(R.id.level2_Rb);
            preloadCargo_Rb = (RadioButton) view.findViewById(R.id.preloadCargo_Rb);
            preloadHatch_Rb = (RadioButton) view.findViewById(R.id.preloadHatch_Rb);
            ssComments_Et = (EditText) view.findViewById(R.id.ssComments_Et);

            viewsAssigned = true;
        } catch (Exception e){
            e.printStackTrace();
            viewsAssigned = false;
        }
    }

    public void command_noShow(View view) {
        if (noShow.isChecked()) {
            noShowDF.show(getChildFragmentManager(), "NoShowDialogFragment");
        }
    }


    public void setNoShow(boolean b){
        noShow.setChecked(b);
    }
}
