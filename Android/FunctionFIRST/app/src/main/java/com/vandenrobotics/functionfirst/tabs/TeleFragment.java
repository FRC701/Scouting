package com.vandenrobotics.functionfirst.tabs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.vandenrobotics.functionfirst.dialogs.DeleteStackDialogFragment;
import com.vandenrobotics.functionfirst.dialogs.DeleteStepStackDialogFragment;
import com.vandenrobotics.functionfirst.model.Stack;
import com.vandenrobotics.functionfirst.model.StepStack;
import com.vandenrobotics.functionfirst.views.NumberPicker;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.activities.MatchActivity;
import com.vandenrobotics.functionfirst.model.TeleData;

import java.util.ArrayList;

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

    private ArrayList<Stack> mStacks;
    private ArrayList<StepStack> mStepStacks;

    private long then;
    private final int longClickDuration = 1000;

    private Stack mStackToDelete;
    private StepStack mStepStackToDelete;

    public DeleteStackDialogFragment deleteStackDF;
    public DeleteStepStackDialogFragment deleteStepStackDF;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_tele, container, false);
        mActivity = (MatchActivity) getActivity();

        mTeleData = mActivity.mMatchData.mTeleData;

        if(viewsAssigned) loadData(mTeleData);

        deleteStackDF = new DeleteStackDialogFragment();
        deleteStepStackDF = new DeleteStepStackDialogFragment();

        mStacks = new ArrayList<>();
        mStepStacks = new ArrayList<>();

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        assignViews(view);
        if(viewsAssigned)loadData(mTeleData);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        if(!viewsAssigned);
        else if(isVisibleToUser){
            assignViews(getView());
            if(viewsAssigned)loadData(mTeleData);
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
        if(viewsAssigned) loadData(mTeleData);
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
        mStacks = teleData.stacks;
        mStepStacks = teleData.stepStacks;
        drawPoints();
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
        teleData.stacks = mStacks;
        teleData.stepStacks = mStepStacks;

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

            fieldDiagram = (ImageView)view.findViewById(R.id.fieldDiagram);
            fieldDiagram.setImageDrawable(getResources().getDrawable((mActivity.mDeviceNumber>0 && mActivity.mDeviceNumber<4)? R.drawable.field_diagram_red : R.drawable.field_diagram_blue));
            drawPoints();

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
                                for(Stack s : mStacks) {
                                    if (point.x >= s.mPoint.x - (30.0 / fieldDiagram.getWidth())  && point.x <= s.mPoint.x + (30.0 / fieldDiagram.getWidth()) &&
                                        point.y >= s.mPoint.y - (20.0 / fieldDiagram.getHeight()) && point.y <= s.mPoint.y + (20.0 / fieldDiagram.getHeight())) {
                                        // the click was on a stack that already existed
                                        if ((System.currentTimeMillis() - then) >= longClickDuration) {
                                            // delete the stack after displaying the dialog if the user held the touch down
                                            mStackToDelete = s;
                                            mActivity.dialog_deleteStack(v);
                                        } else {
                                            // edit the stack instead
                                        }
                                        stackExists = true;
                                        break;
                                    }
                                }
                                // the click was not on a place where a stack did not already exist
                                if (!stackExists) {
                                    Stack stack = new Stack();
                                    mStacks.add(stack);
                                    stack.mPoint = point;
                                }
                            } else {
                                boolean stackExists = false;
                                for(StepStack s : mStepStacks) {
                                    if (point.x >= s.mPoint.x - (30.0 / fieldDiagram.getWidth())  && point.x <= s.mPoint.x + (30.0 / fieldDiagram.getWidth()) &&
                                        point.y >= s.mPoint.y - (20.0 / fieldDiagram.getHeight()) && point.y <= s.mPoint.y + (20.0 / fieldDiagram.getHeight())) {
                                        // the click was on a stack that already existed
                                        if ((System.currentTimeMillis() - then) >= longClickDuration) {
                                            // delete the stack after displaying the dialog if the user held the touch down
                                            mStepStackToDelete = s;
                                            mActivity.dialog_deleteStepStack(v);
                                        } else {
                                            // edit the stack instead
                                        }
                                        stackExists = true;
                                        break;
                                    }
                                }
                                // the lick was not on a place where a stack did not already exist
                                if (!stackExists){
                                    StepStack stepStack = new StepStack();
                                    mStepStacks.add(stepStack);
                                    stepStack.mPoint = point;
                                }
                            }
                            drawPoints();
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

    public void deleteStack(){
        try{
            mStacks.remove(mStackToDelete);
            drawPoints();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStepStack(){
        try{
            mStepStacks.remove(mStepStackToDelete);
            drawPoints();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void drawPoints(){
        Bitmap background = BitmapFactory.decodeResource(getResources(), (mActivity.mDeviceNumber>0 && mActivity.mDeviceNumber<4)? R.drawable.field_diagram_red : R.drawable.field_diagram_blue);
        Bitmap bmp = Bitmap.createScaledBitmap(background, fieldDiagram.getWidth()!=0? fieldDiagram.getWidth() : 513, fieldDiagram.getHeight()!=0? fieldDiagram.getHeight() : 355, true);
        Canvas canvas = new Canvas(bmp);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        for (Stack s : mStacks) {
            String containerText = (s.mContainer != 0) ? "C" : "";
            String litterText = (s.mLitter != 0) ? "L" : "";
            String text = s.getHeight() + containerText + litterText;

            float x = (float) fieldDiagram.getWidth() * s.mPoint.x;
            float y = (float) fieldDiagram.getHeight() * s.mPoint.y;

            paint.setColor(getResources().getColor(R.color.Gray));
            canvas.drawRect(x - 25, (float) (y - 12.5), x + 25, (float) (y + 12.5), paint);
            paint.setColor(getResources().getColor(R.color.Gold));
            canvas.drawText(text, x, y, paint);
        }

        for (StepStack s : mStepStacks) {
            String text = s.getHeight() + "";

            float x = (float) fieldDiagram.getWidth() * s.mPoint.x;
            float y = (float) fieldDiagram.getHeight() * s.mPoint.y;

            paint.setColor(getResources().getColor(R.color.Gold));
            canvas.drawRect(x - 25, (float) (y - 12.5), x + 25, (float) (y + 12.5), paint);
            paint.setColor(getResources().getColor(R.color.Gray));
            canvas.drawText(text, x, y, paint);
        }

        fieldDiagram.setImageBitmap(bmp);
    }
}
