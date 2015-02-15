package com.vandenrobotics.functionfirst.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_tele, container, false);
        mActivity = (MatchActivity) getActivity();

        Bundle args = getArguments();
        mTeleData = args.getParcelable("teleData");

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
            mTeleData = saveData();
        }

    }

    @Override
    public void onPause(){
        super.onPause();
        mTeleData = saveData();
        viewsAssigned=false;
    }

    @Override
    public void onResume(){
        super.onResume();
        loadData(mTeleData);
        assignViews(getView());
    }

    private void loadData(final TeleData teleData){
        // take the teleData and assign it to each view
    }

    private TeleData saveData(){
        TeleData teleData = new TeleData();

        // save the data from each view into the data formats

        return teleData;
    }

    private void assignViews(View view){
        try{
            // assign all the custom view info to their respective views in the xml

            viewsAssigned = true;
        } catch (Exception e){
            e.printStackTrace();
            viewsAssigned = false;
        }
    }
}
