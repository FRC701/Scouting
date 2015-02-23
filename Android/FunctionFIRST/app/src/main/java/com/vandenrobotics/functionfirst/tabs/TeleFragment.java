package com.vandenrobotics.functionfirst.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.vandenrobotics.functionfirst.views.NumberPicker;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.activities.MatchActivity;
import com.vandenrobotics.functionfirst.model.TeleData;

/**
 * Created by Programming701-A on 12/11/2014.
 */
public class TeleFragment extends Fragment {

    private MatchActivity mActivity;
    private boolean viewsAssigned = false;

    private TeleData mTeleData;

    private ImageView fieldDiagram;
    private NumberPicker totesFromChute;
    private NumberPicker litterFromChute;
    private NumberPicker totesFromLandfill;
    private NumberPicker totesFromStep;
    private NumberPicker litterToLandfill;
    private NumberPicker containersUpright;
    private NumberPicker totesUpright;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_tele, container, false);
        mActivity = (MatchActivity) getActivity();

        mTeleData = mActivity.mMatchData.mTeleData;

        if(viewsAssigned) loadData(mTeleData);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        assignViews(view);
        loadData(mTeleData);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        if(!viewsAssigned);
        else if(isVisibleToUser){
            assignViews(getView());
            loadData(mTeleData);
        }
        else if(!isVisibleToUser){
            mTeleData = new TeleData(saveData());
        }

    }

    @Override
    public void onPause(){
        super.onPause();
        mTeleData = new TeleData(saveData());
        viewsAssigned=false;
    }

    @Override
    public void onResume(){
        super.onResume();
        assignViews(getView());
        loadData(mTeleData);
    }

    public void loadData(final TeleData teleData){
        // take the teleData and assign it to each view
        fieldDiagram.setImageDrawable((mActivity.mDeviceNumber>0 && mActivity.mDeviceNumber<4)?
                getResources().getDrawable(R.drawable.field_diagram_red) : getResources().getDrawable(R.drawable.field_diagram_blue));
        totesFromChute.setValue(teleData.totesFromChute);
        litterFromChute.setValue(teleData.litterFromChute);
        totesFromLandfill.setValue(teleData.totesFromLandfill);
        totesFromStep.setValue(teleData.totesFromStep);
        litterToLandfill.setValue(teleData.litterToLandfill);
        containersUpright.setValue(teleData.containersUpright);
        totesUpright.setValue(teleData.totesUpright);
    }

    public TeleData saveData(){
        TeleData teleData = new TeleData();

        teleData.totesFromChute = totesFromChute.getValue();
        teleData.litterFromChute = litterFromChute.getValue();
        teleData.totesFromLandfill = totesFromLandfill.getValue();
        teleData.totesFromStep = totesFromStep.getValue();
        teleData.litterToLandfill = litterToLandfill.getValue();
        teleData.containersUpright = containersUpright.getValue();
        teleData.totesUpright = totesUpright.getValue();

        return teleData;
    }

    private void assignViews(View view){
        try{
            // assign all the custom view info to their respective views in the xml
            fieldDiagram = (ImageView)view.findViewById(R.id.fieldDiagram);
            totesFromChute = (NumberPicker)view.findViewById(R.id.pickerTotesFromChute);
            litterFromChute = (NumberPicker)view.findViewById(R.id.pickerLitterFromChute);
            totesFromLandfill = (NumberPicker)view.findViewById(R.id.pickerTotesFromLandfillTele);
            totesFromStep = (NumberPicker)view.findViewById(R.id.pickerTotesFromStepTele);
            litterToLandfill = (NumberPicker)view.findViewById(R.id.pickerLitterToLandfill);
            containersUpright = (NumberPicker)view.findViewById(R.id.pickerContainersUpright);
            totesUpright = (NumberPicker)view.findViewById(R.id.pickerTotesUpright);

            totesFromChute.setMinValue(0);
            totesFromChute.setMaxValue(30);
            litterFromChute.setMinValue(0);
            litterFromChute.setMaxValue(10);
            totesFromLandfill.setMinValue(0);
            totesFromLandfill.setMaxValue(28);
            totesFromStep.setMinValue(0);
            totesFromStep.setMaxValue(12);
            litterToLandfill.setMinValue(0);
            litterToLandfill.setMaxValue(20);
            containersUpright.setMinValue(0);
            containersUpright.setMaxValue(999);
            totesUpright.setMinValue(0);
            totesUpright.setMaxValue(999);


            viewsAssigned = true;
        } catch (Exception e){
            e.printStackTrace();
            viewsAssigned = false;
        }
    }
}
