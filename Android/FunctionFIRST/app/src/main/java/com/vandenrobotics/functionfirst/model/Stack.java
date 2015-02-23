package com.vandenrobotics.functionfirst.model;

/**
 * Created by Programming701-A on 2/21/2015.
 */
public class Stack {

    public int mIndex;
    public int[] mTotes;        // takes values 0 for no tote, 1 for tote stacked width-wise, 2 for tote stacked length-wise
                                // takes values 3 for tote upside down width-wise, 4 for tote upside down length-wise
                                // this allows us to track: number of totes contributed to stack, level of top tote, orientation
    public int mContainer;      // takes values 0 for no container, 1 for container stacked upright, 2 for container stacked sideways, 3 container upside down
    public int mContainerHeight;// takes the value of the number of totes under the container when stacked (if this robot stacked the container), otherwise -1
    public int mLitter;         // takes values 0 for not litter, 1 for litter loaded normal, 2 for litter loaded
    public boolean mKnocked;    // takes values 0 for no, 1 for this robot knocked over this stack

    public Stack(){
        mIndex = 0;
        mTotes = new int[6];
        mContainer = 0;
        mContainerHeight = -1;
        mLitter = 0;
        mKnocked = false;
    }

    public Stack(Stack stack){
        this();

        mIndex = stack.mIndex;
        mTotes = stack.mTotes;
        mContainer = stack.mContainer;
        mContainerHeight = stack.mContainerHeight;
        mLitter = stack.mLitter;
        mKnocked = stack.mKnocked;
    }

    @Override
    public String toString(){
        return mIndex+","+
               mTotes[0]+","+mTotes[1]+","+
               mTotes[2]+","+mTotes[3]+","+
               mTotes[4]+","+mTotes[5]+","+
               mContainer+","+mContainerHeight+","+
               mLitter+","+mKnocked;
    }
}
