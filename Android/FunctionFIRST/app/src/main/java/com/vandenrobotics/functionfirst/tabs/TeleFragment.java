package com.vandenrobotics.functionfirst.tabs;

import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.vandenrobotics.functionfirst.dialogs.DeleteStackDialogFragment;
import com.vandenrobotics.functionfirst.dialogs.DeleteStepStackDialogFragment;
import com.vandenrobotics.functionfirst.dialogs.EditStackDialogFragment;
import com.vandenrobotics.functionfirst.dialogs.EditStepStackDialogFragment;
import com.vandenrobotics.functionfirst.model.Stack;
import com.vandenrobotics.functionfirst.model.StepStack;
import com.vandenrobotics.functionfirst.views.FieldDiagram;
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

    private FieldDiagram fieldDiagram;
    private NumberPicker totesFromChute;
    private NumberPicker litterFromChute;
    private NumberPicker totesFromLandfill;
    private NumberPicker totesFromStep;
    private NumberPicker litterToLandfill;
    private NumberPicker containersUpright;
    private NumberPicker totesUpright;

    private long then;
    private final int longClickDuration = 1000;

    private Stack mStackToEdit;
    private StepStack mStepStackToEdit;

    private Stack mStackToDelete;
    private StepStack mStepStackToDelete;

    public DeleteStackDialogFragment deleteStackDF;
    public DeleteStepStackDialogFragment deleteStepStackDF;

    public EditStackDialogFragment editStackDF;
    public EditStepStackDialogFragment editStepStackDF;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_tele, container, false);
        mActivity = (MatchActivity) getActivity();

        mTeleData = mActivity.mMatchData.mTeleData;

        if(!viewsAssigned) assignViews(rootView);
        if(viewsAssigned){
            loadData(mTeleData);
            System.out.println("VIEWS ASSIGNED AND DATA LOADED");
        }

        deleteStackDF = new DeleteStackDialogFragment();
        deleteStepStackDF = new DeleteStepStackDialogFragment();

        editStackDF = new EditStackDialogFragment();
        editStepStackDF = new EditStepStackDialogFragment();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        assignViews(view);
        loadData(mTeleData);
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
        totesFromChute.setValue(teleData.totesFromChute);
        litterFromChute.setValue(teleData.litterFromChute);
        totesFromLandfill.setValue(teleData.totesFromLandfill);
        totesFromStep.setValue(teleData.totesFromStep);
        litterToLandfill.setValue(teleData.litterToLandfill);
        containersUpright.setValue(teleData.containersUpright);
        totesUpright.setValue(teleData.totesUpright);
        fieldDiagram.mStacks = teleData.stacks;
        fieldDiagram.mStepStacks = teleData.stepStacks;
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
        teleData.stacks = fieldDiagram.mStacks;
        teleData.stepStacks = fieldDiagram.mStepStacks;

        return teleData;
    }

    private void assignViews(View view){
        try{
            // assign all the custom view info to their respective views in the xml
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

            fieldDiagram = (FieldDiagram)view.findViewById(R.id.fieldDiagram);
            fieldDiagram.setupCanvas((mActivity.mDeviceNumber > 0 && mActivity.mDeviceNumber < 4) ? R.drawable.field_diagram_red : R.drawable.field_diagram_blue);

            fieldDiagram.setOnTouchListener(new View.OnTouchListener(){
                @Override
                public boolean onTouch(View v, MotionEvent event){
                    switch(event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            then = System.currentTimeMillis();
                            break;
                        case MotionEvent.ACTION_UP:
                            PointF point = new PointF(event.getX() / fieldDiagram.getWidth(), event.getY() / fieldDiagram.getHeight());
                            boolean step = (mActivity.mDeviceNumber > 0 && mActivity.mDeviceNumber < 4) ? point.x <= 0.1500 : point.x >= 0.8500;
                            if (!step) {
                                boolean stackExists = false;
                                for(Stack s : fieldDiagram.mStacks) {
                                    if (point.x >= s.mPoint.x - (30.0 / fieldDiagram.getWidth())  && point.x <= s.mPoint.x + (30.0 / fieldDiagram.getWidth()) &&
                                        point.y >= s.mPoint.y - (20.0 / fieldDiagram.getHeight()) && point.y <= s.mPoint.y + (20.0 / fieldDiagram.getHeight())) {
                                        // the click was on a stack that already existed
                                        if ((System.currentTimeMillis() - then) >= longClickDuration) {
                                            // delete the stack after displaying the dialog if the user held the touch down
                                            mStackToDelete = s;
                                            mActivity.dialog_deleteStack(v);
                                        } else {
                                            // edit the stack instead
                                            mStackToEdit = s;
                                            editStackDF.mStack = mStackToEdit;
                                            mActivity.dialog_editStack(v);
                                        }
                                        stackExists = true;
                                        break;
                                    }
                                }
                                // the click was not on a place where a stack did not already exist
                                if (!stackExists) {
                                    Stack stack = new Stack();
                                    // edit the new Stack
                                    stack.mPoint = point;
                                    mStackToEdit = stack;
                                    editStackDF.mStack = mStackToEdit;
                                    fieldDiagram.mStacks.add(stack);
                                    mActivity.dialog_editStack(v);

                                }
                            } else {
                                boolean stackExists = false;
                                for(StepStack s : fieldDiagram.mStepStacks) {
                                    if (point.x >= s.mPoint.x - (30.0 / fieldDiagram.getWidth())  && point.x <= s.mPoint.x + (30.0 / fieldDiagram.getWidth()) &&
                                        point.y >= s.mPoint.y - (20.0 / fieldDiagram.getHeight()) && point.y <= s.mPoint.y + (20.0 / fieldDiagram.getHeight())) {
                                        // the click was on a stack that already existed
                                        if ((System.currentTimeMillis() - then) >= longClickDuration) {
                                            // delete the stack after displaying the dialog if the user held the touch down
                                            mStepStackToDelete = s;
                                            mActivity.dialog_deleteStepStack(v);
                                        } else {
                                            // edit the stack instead
                                            mStepStackToEdit = s;
                                            editStepStackDF.mStepStack = mStepStackToEdit;
                                            mActivity.dialog_editStepStack(v);
                                        }
                                        stackExists = true;
                                        break;
                                    }
                                }
                                // the click was not on a place where a stack did not already exist
                                if (!stackExists){
                                    StepStack stepStack = new StepStack();
                                    // edit the new StepStack
                                    stepStack.mPoint = point;
                                    mStepStackToEdit = stepStack;
                                    editStepStackDF.mStepStack = mStepStackToEdit;
                                    fieldDiagram.mStepStacks.add(stepStack);
                                    mActivity.dialog_editStepStack(v);
                                }
                            }
                            break;
                    }
                    return true;
                }
            });

            viewsAssigned = true;
        } catch (Exception e){
            e.printStackTrace();
            viewsAssigned = false;
        }
    }

    public void command_deleteStack(View view){
        deleteStackDF.show(getFragmentManager(), "DeleteStackDialogFragment");
    }

    public void command_deleteStepStack(View view){
        deleteStepStackDF.show(getFragmentManager(), "DeleteStepStackDialogFragment");
    }

    public void command_editStack(View view){
        editStackDF.show(getFragmentManager(), "EditStackDialogFragment");
    }

    public void command_editStepStack(View view){
        editStepStackDF.show(getFragmentManager(), "EditStepStackDialogFragment");
    }

    public void deleteStack(){
        try{
            fieldDiagram.mStacks.remove(mStackToDelete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStepStack(){
        try{
            fieldDiagram.mStepStacks.remove(mStepStackToDelete);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editStack(){
        try{
            fieldDiagram.mStacks.set(fieldDiagram.mStacks.indexOf(mStackToEdit), editStackDF.mStack);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editStepStack(){
        try{
            fieldDiagram.mStepStacks.set(fieldDiagram.mStepStacks.indexOf(mStepStackToEdit), editStepStackDF.mStepStack);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
