package com.vandenrobotics.functionfirst.model;

import android.graphics.PointF;

/**
 * Created by Programming701-A on 2/21/2015.
 */
public class StepStack {

    public PointF mPoint;
    public int[] mTotes;        // takes values 0 for no tote, 1 for tote stacked width-wise, 2 for tote stacked length-wise
                                // takes values 3 for upside down width-wise, 4 for upside-down length-wise
                                // this allows us to track: number of totes contributed to stack, level of top tote, orientation
    public boolean mKnocked;    // takes values 0 for no, 1 for this robot knocked over this stack

    public StepStack(){
        mPoint = new PointF();
        mTotes = new int[6];
        mKnocked = false;
    }

    public StepStack(StepStack stepstack){
        this();

        mPoint = stepstack.mPoint;
        mTotes = stepstack.mTotes;
        mKnocked = stepstack.mKnocked;
    }

    @Override
    public String toString(){
        return mPoint.x+","+mPoint.y+","+
               mTotes[0]+","+mTotes[1]+","+
               mTotes[2]+","+mTotes[3]+","+
               mTotes[4]+","+mTotes[5]+","+
               mKnocked;
    }

    public int getHeight(){
        int i;
        for(i = mTotes.length-1; i >= 0; i--){
            if(mTotes[i]!=0) break;
        }
        return (i>=0)? i : 0;
    }
}
