package com.vandenrobotics.functionfirst.model;

/**
 * Created by Programming701-A on 2/10/2015.
 */
public class MatchData {

    public InitData mInitData;
    public AutoData mAutoData;
    public TeleData mTeleData;
    public PostData mPostData;

    public MatchData(){
        mInitData = new InitData();
        mAutoData = new AutoData();
        mTeleData = new TeleData();
        mPostData = new PostData();
    }

    public MatchData(String string){
        try{
            String[] dataString = string.split("\\$");
            mInitData = new InitData(dataString[0]);
            mAutoData = new AutoData(dataString[1]);
            mTeleData = new TeleData(dataString[2]);
            mPostData = new PostData(dataString[3]);
        } catch (Exception e){
            e.printStackTrace();
            mInitData = new InitData();
            mAutoData = new AutoData();
            mTeleData = new TeleData();
            mPostData = new PostData();
        }
    }

    public MatchData(MatchData matchData){
        mInitData = matchData.mInitData;
        mAutoData = matchData.mAutoData;
        mTeleData = matchData.mTeleData;
        mPostData = matchData.mPostData;
    }

    @Override
    public String toString(){
        return mInitData.toString()+"$"+mAutoData.toString()+"$"
                +mTeleData.toString()+"$"+mPostData.toString();
    }
}
