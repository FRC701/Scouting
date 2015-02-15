package com.vandenrobotics.functionfirst.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.activities.MatchActivity;
import com.vandenrobotics.functionfirst.model.PostData;

/**
 * Created by Programming701-A on 12/11/2014.
 */
public class PostFragment extends Fragment {

    private MatchActivity mActivity;
    private boolean viewsAssigned = false;

    private PostData mPostData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_post, container, false);
        mActivity = (MatchActivity) getActivity();

        Bundle args = getArguments();
        mPostData = args.getParcelable("postData");

        if(viewsAssigned) loadData(mPostData);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        assignViews(view);
        loadData(mPostData);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        if(!viewsAssigned);
        else if(isVisibleToUser){
            assignViews(getView());
            loadData(mPostData);
        }
        else if(!isVisibleToUser){
            mPostData = saveData();
        }

    }

    @Override
    public void onPause(){
        super.onPause();
        mPostData = saveData();
        viewsAssigned=false;
    }

    @Override
    public void onResume(){
        super.onResume();
        loadData(mPostData);
        assignViews(getView());
    }

    private void loadData(final PostData postData){
        // take the postData and assign it to each view
    }

    private PostData saveData(){
        PostData postData = new PostData();

        // save the data from each view into the data formats

        return postData;
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
