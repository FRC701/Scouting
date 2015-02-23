package com.vandenrobotics.functionfirst.model;

/**
 * Created by Programming701-A on 2/21/2015.
 */
public class StepStack {

    public int mIndex;
    public int[] mTotes;        // takes values 0 for no tote, 1 for tote stacked width-wise, 2 for tote stacked length-wise
                                // takes values 3 for upside down width-wise, 4 for upside-down length-wise
                                // this allows us to track: number of totes contributed to stack, level of top tote, orientation
    public boolean mKnocked;    // takes values 0 for no, 1 for this robot knocked over this stack

    public StepStack(){
        mIndex = 0;
        mTotes = new int[6];
        mKnocked = false;
    }

    public StepStack(StepStack stepstack){
        this();

        mIndex = stepstack.mIndex;
        mTotes = stepstack.mTotes;
        mKnocked = stepstack.mKnocked;
    }

    @Override
    public String toString(){
        return mIndex+","+
               mTotes[0]+","+mTotes[1]+","+
               mTotes[2]+","+mTotes[3]+","+
               mTotes[4]+","+mTotes[5]+","+
               mKnocked;
    }
}
