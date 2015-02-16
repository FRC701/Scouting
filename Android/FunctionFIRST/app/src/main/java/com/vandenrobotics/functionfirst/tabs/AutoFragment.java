package com.vandenrobotics.functionfirst.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.ToggleButton;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.activities.MatchActivity;
import com.vandenrobotics.functionfirst.model.AutoData;

/**
 * Created by Programming701-A on 12/11/2014.
 */
public class AutoFragment extends Fragment {

    private MatchActivity mActivity;
    private boolean viewsAssigned = false;

    public AutoData mAutoData;

    private CheckBox hadAuto;
    private NumberPicker totesToAuto;
    private NumberPicker containersToAuto;
    private NumberPicker containersKnockedOver;
    private NumberPicker containersFromStep;
    private NumberPicker totesFromLandfill;
    private NumberPicker totesStacked;
    private ToggleButton[] autoStack;
    private CheckBox endInAuto;
    private CheckBox hadOther;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_auto, container, false);
        mActivity = (MatchActivity) getActivity();

        mAutoData = mActivity.mMatchData.mAutoData;

        if(viewsAssigned) loadData(mAutoData);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        assignViews(view);
        loadData(mAutoData);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        if(!viewsAssigned);
        else if(isVisibleToUser){
            assignViews(getView());
            loadData(mAutoData);
        }
        else if(!isVisibleToUser){
            mAutoData = new AutoData(saveData());
        }

    }

    @Override
    public void onPause(){
        super.onPause();
        mAutoData = new AutoData(saveData());
        mActivity.mMatchData.mAutoData = mAutoData;
        viewsAssigned=false;
    }

    @Override
    public void onResume(){
        super.onResume();
        assignViews(getView());
        loadData(mAutoData);
    }

    public void loadData(final AutoData autoData){
        // take the autoData and assign it to each view
        hadAuto.setChecked(autoData.hadAuto);
        totesToAuto.setValue(autoData.totesToAuto);
        containersToAuto.setValue(autoData.containersToAuto);
        containersKnockedOver.setValue(autoData.containersKnockedOver);
        containersFromStep.setValue(autoData.containersFromStep);
        totesFromLandfill.setValue(autoData.totesFromLandfill);
        totesStacked.setValue(autoData.totesStacked);
        for(int i = 0; i < autoStack.length; i++){
            autoStack[i].setChecked(autoData.autoStack[i]);
        }
        endInAuto.setChecked(autoData.endInAuto);
        hadOther.setChecked(autoData.hadOther);

        if(hadAuto.isChecked())
            enableAutoViews();
        else
            disableAutoViews();
    }

    public AutoData saveData(){
        AutoData autoData = new AutoData();
        if(viewsAssigned){
            autoData.hadAuto = hadAuto.isChecked();
            autoData.totesToAuto = totesToAuto.getValue();
            autoData.containersToAuto = containersToAuto.getValue();
            autoData.containersKnockedOver = containersKnockedOver.getValue();
            autoData.containersFromStep = containersFromStep.getValue();
            autoData.totesFromLandfill = totesFromLandfill.getValue();
            autoData.totesStacked = totesStacked.getValue();
            for(int i = 0; i < autoData.autoStack.length; i++){
                autoData.autoStack[i] = autoStack[i].isChecked();
            }
            autoData.endInAuto = endInAuto.isChecked();
            autoData.hadOther = hadOther.isChecked();
        }

        return autoData;
    }

    private void assignViews(View view){
        try{
            // assign all the custom view info to their respective views in the xml
            hadAuto = (CheckBox)view.findViewById(R.id.cb_hadAuto);
            totesToAuto = (NumberPicker)view.findViewById(R.id.pickerTotesToAuto);
            containersToAuto = (NumberPicker)view.findViewById(R.id.pickerContainersToAuto);
            containersKnockedOver = (NumberPicker)view.findViewById(R.id.pickerContainersKnockedOver);
            containersFromStep = (NumberPicker)view.findViewById(R.id.pickerContainersFromStep);
            totesFromLandfill = (NumberPicker)view.findViewById(R.id.pickerTotesFromLandfill);
            totesStacked = (NumberPicker)view.findViewById(R.id.pickerTotesStacked);
            autoStack = new ToggleButton[3];
            autoStack[0] = (ToggleButton)view.findViewById(R.id.toggleAutoStackBase);
            autoStack[1] = (ToggleButton)view.findViewById(R.id.toggleAutoStackMid);
            autoStack[2] = (ToggleButton)view.findViewById(R.id.toggleAutoStackTop);
            endInAuto = (CheckBox)view.findViewById(R.id.cb_endInAuto);
            hadOther = (CheckBox)view.findViewById(R.id.cb_hadOther);

            hadAuto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if(hadAuto.isChecked())
                        enableAutoViews();
                    else
                        disableAutoViews();
                }
            });

            totesToAuto.setMinValue(0);
            totesToAuto.setMaxValue(3);
            containersToAuto.setMinValue(0);
            containersToAuto.setMaxValue(5);
            containersKnockedOver.setMinValue(0);
            containersKnockedOver.setMaxValue(5);
            containersFromStep.setMinValue(0);
            containersFromStep.setMaxValue(2);
            totesFromLandfill.setMinValue(0);
            totesFromLandfill.setMaxValue(28);
            totesStacked.setMinValue(0);
            totesStacked.setMaxValue(28);

            viewsAssigned = true;
        } catch (Exception e){
            e.printStackTrace();
            viewsAssigned = false;
        }
    }

    private void enableAutoViews(){
        totesToAuto.setEnabled(true);
        containersToAuto.setEnabled(true);
        containersKnockedOver.setEnabled(true);
        containersFromStep.setEnabled(true);
        totesFromLandfill.setEnabled(true);
        totesStacked.setEnabled(true);
        for(ToggleButton tb : autoStack){
            tb.setEnabled(true);
        }
        endInAuto.setEnabled(true);
        hadOther.setEnabled(true);
    }

    private void disableAutoViews(){
        totesToAuto.setValue(0);
        totesToAuto.setEnabled(false);
        containersToAuto.setValue(0);
        containersToAuto.setEnabled(false);
        containersFromStep.setValue(0);
        containersFromStep.setEnabled(false);
        totesFromLandfill.setValue(0);
        totesFromLandfill.setEnabled(false);
        totesStacked.setValue(0);
        totesStacked.setEnabled(false);
        for(ToggleButton tb : autoStack){
            tb.setChecked(false);
            tb.setEnabled(false);
        }
        endInAuto.setChecked(false);
        endInAuto.setEnabled(false);
        hadOther.setChecked(false);
        hadOther.setEnabled(false);
    }
}
