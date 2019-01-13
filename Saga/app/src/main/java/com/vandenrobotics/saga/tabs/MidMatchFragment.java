package com.vandenrobotics.saga.tabs;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.vandenrobotics.saga.R;
import com.vandenrobotics.saga.activities.MatchActivity;
import com.vandenrobotics.saga.data.model.Stats;
import com.vandenrobotics.saga.data.repo.StatsRepo;


/**
 * Created by Programming701-A on 1/14/2017.
 */

public class MidMatchFragment extends Fragment {

    private MatchActivity mActivity;
    private boolean viewAssigned = false;

    private ConstraintLayout autoField;

    private Button rocketTopC_Bt;
    private Button rocketTopH_Bt;
    private Button rocketMiddleC_Bt;
    private Button rocketMiddleH_Bt;
    private Button rocketBottomC_Bt;
    private Button rocketBottomH_Bt;
    private Button cargoShipC_Bt;
    private Button cargoShipH_Bt;
    private CheckBox removeGP_Cb;
    private CheckBox crossHubLine_Cb;


    private int rocketTopC = 0;
    private int rocketTopH = 0;
    private int rocketMiddleC = 0;
    private int rocketMiddleH = 0;
    private int rocketBottomC = 0;
    private int rocketBottomH = 0;
    private int cargoC = 0;
    private int cargoH = 0;

    private String mEvent;
    private int mMatchNum;
    private int mTeamNum;
    private int mMatchPos;
    private String mAlliance;


    private StatsRepo statsRepo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_mid, container, false);
        mActivity = (MatchActivity)getActivity();
        mEvent = mActivity.mEvent;
        mMatchNum = mActivity.mMatchNumber;
        mTeamNum = mActivity.mTeamNumber;
        mMatchPos = mActivity.mDeviceNumber;
        mAlliance = mActivity.mAllianceColor;

        statsRepo = new StatsRepo();

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
        statsRepo.setMidStats(stats);
    }

    public Stats saveData(){
        Stats stat = new Stats();
        stat.setCompId(mEvent);
        stat.setMatchNum(mMatchNum);
        stat.setTeamNum(mTeamNum);
        stat.setRocketTopC(rocketTopC);
        stat.setRocketTopH(rocketTopH);
        stat.setRocketMiddleC(rocketMiddleC);
        stat.setRocketMiddleH(rocketMiddleH);
        stat.setRocketBottomC(rocketBottomC);
        stat.setRocketBottomH(rocketBottomH);
        stat.setCargoShipC(cargoC);
        stat.setCargoShipH(cargoH);
        int ch = (crossHubLine_Cb.isChecked() ? 0:1);
        stat.setCrossHubLine(ch);
        return stat;
    }

    @Override
    public void onResume(){
        super.onResume();
        assignViews(getView());
        if(viewAssigned) loadData();
    }

    private void loadData() {
        Stats stats = statsRepo.getMidStats(mEvent, mMatchNum, mMatchPos);
        rocketTopC = stats.getRocketTopC();
        rocketTopH = stats.getRocketTopH();
        rocketMiddleC = stats.getRocketMiddleC();
        rocketMiddleH = stats.getRocketMiddleH();
        rocketBottomC = stats.getRocketMBottomC();
        rocketBottomH = stats.getRocketMBottomH();
        crossHubLine_Cb.setChecked(stats.getCrossHubLine() == 1);
    }

    public void assignViews(View view){
        try {
            rocketTopC_Bt = (Button) view.findViewById(R.id.rocketTopC_Bt);
            rocketTopH_Bt = (Button) view.findViewById(R.id.rocketTopH_Bt);
            rocketMiddleC_Bt = (Button) view.findViewById(R.id.rocketMiddleC_Bt);
            rocketMiddleH_Bt = (Button) view.findViewById(R.id.rocketMiddleH_Bt);
            rocketBottomC_Bt = (Button) view.findViewById(R.id.rocketBottomC_Bt);
            rocketBottomH_Bt = (Button) view.findViewById(R.id.rocketBottomH_Bt);
            cargoShipC_Bt = (Button) view.findViewById(R.id.cargoShipC_Bt);
            cargoShipH_Bt = (Button) view.findViewById(R.id.cargoShipH_Bt);
            removeGP_Cb = (CheckBox) view.findViewById(R.id.removeGP_Cb);
            crossHubLine_Cb = (CheckBox) view.findViewById(R.id.crossHubLine_Cb);
            viewAssigned = true;
        }catch(Exception e){
            e.printStackTrace();
            viewAssigned = false;
        }

//        rocketTopC_Bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rocketTopC++;
//                rocketTopC_Bt.setText(rocketTopC);
//            }
//        });
    }
   public void OnClick(View view){
        switch (view.getId()){
            case R.id.rocketTopC_Bt:
                rocketTopC++;
                if(removeGP_Cb.isChecked()){
                    rocketTopC--;
                }
                rocketTopC_Bt.setText(rocketTopC);
                break;
            case R.id.rocketTopH_Bt:
                rocketTopH++;
                if(removeGP_Cb.isChecked()){
                    rocketTopH--;
                }
                rocketTopH_Bt.setText(rocketTopH);
                break;
            case R.id.rocketMiddleC_Bt:
                rocketMiddleC++;
                if(removeGP_Cb.isChecked()){
                    rocketMiddleC--;
                }
                rocketMiddleC_Bt.setText(rocketMiddleC);
                break;
            case R.id.rocketMiddleH_Bt:
                rocketMiddleH++;
                if(removeGP_Cb.isChecked()){
                    rocketMiddleH--;
                }
                rocketMiddleH_Bt.setText(rocketMiddleH);
                break;
            case R.id.rocketBottomC_Bt:
                rocketBottomC++;
                if(removeGP_Cb.isChecked()){
                    rocketBottomC--;
                }
                rocketBottomC_Bt.setText(rocketBottomC);
                break;
            case R.id.rocketBottomH_Bt:
                rocketBottomH++;
                if(removeGP_Cb.isChecked()){
                    rocketBottomH--;
                }
                rocketBottomH_Bt.setText(rocketBottomH);
                break;
            case R.id.cargoShipC_Bt:
                cargoC++;
                if(removeGP_Cb.isChecked()){
                    cargoC--;
                }
                cargoShipC_Bt.setText(cargoC);
                break;
            case R.id.cargoShipH_Bt:
                cargoH++;
                if(removeGP_Cb.isChecked()){
                    cargoH--;
                }
                cargoShipH_Bt.setText(cargoH);
                break;
        }
   }


}
