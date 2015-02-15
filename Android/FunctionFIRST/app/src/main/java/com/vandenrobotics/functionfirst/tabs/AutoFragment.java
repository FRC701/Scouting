package com.vandenrobotics.functionfirst.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.activities.MatchActivity;
import com.vandenrobotics.functionfirst.model.AutoData;

/**
 * Created by Programming701-A on 12/11/2014.
 */
public class AutoFragment extends Fragment {

    private MatchActivity mActivity;
    private boolean viewsAssigned = false;

    private AutoData mAutoData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_auto, container, false);
        mActivity = (MatchActivity) getActivity();

        Bundle args = getArguments();
        mAutoData = args.getParcelable("autoData");

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
            mAutoData = saveData();
        }

    }

    @Override
    public void onPause(){
        super.onPause();
        mAutoData = saveData();
        viewsAssigned=false;
    }

    @Override
    public void onResume(){
        super.onResume();
        loadData(mAutoData);
        assignViews(getView());
    }

    private void loadData(final AutoData autoData){
        // take the autoData and assign it to each view
    }

    private AutoData saveData(){
        AutoData autoData = new AutoData();

        // save the data from each view into the data formats

        return autoData;
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
