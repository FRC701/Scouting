package com.vandenrobotics.functionfirst.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.activities.MatchActivity;
import com.vandenrobotics.functionfirst.dialogs.NoShowDialogFragment;
import com.vandenrobotics.functionfirst.model.InitData;
import com.vandenrobotics.functionfirst.tools.ImageTools;

/**
 * Created by Programming701-A on 12/11/2014.
 */
public class InitFragment extends Fragment {

    private MatchActivity mActivity;

    private CheckBox noShow;
    private ImageView robotPic;

    public NoShowDialogFragment noShowDF;

    private boolean viewsAssigned = false;

    private InitData mInitData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_init, container, false);
        mActivity = (MatchActivity) getActivity();

        Bundle args = getArguments();
        mInitData = args.getParcelable("initData");

        if(viewsAssigned) loadData(mInitData);

        noShowDF = new NoShowDialogFragment();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        assignViews(view);
        loadData(mInitData);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser){
        super.setUserVisibleHint(isVisibleToUser);
        if(!viewsAssigned);
        else if(isVisibleToUser){
            assignViews(getView());
            loadData(mInitData);
        }
        else if(!isVisibleToUser){
            mInitData = saveData();
        }

    }

    @Override
    public void onPause(){
        super.onPause();
        mInitData = saveData();
        viewsAssigned=false;
    }

    @Override
    public void onResume(){
        super.onResume();
        loadData(mInitData);
        assignViews(getView());
    }

    private void loadData(final InitData initData){
        // take the initData and assign it to each view
        noShow.setChecked(initData.noShow);
    }

    private InitData saveData(){
        InitData initData = new InitData();

        initData.noShow = noShow.isChecked();

        return initData;
    }

    private void assignViews(View view){
        try{
            noShow = (CheckBox) view.findViewById(R.id.cb_noShow);
            robotPic = (ImageView) view.findViewById(R.id.img_teamPic);
            ImageTools.placeImage(getActivity(), mActivity.mTeamNumber, robotPic);

            viewsAssigned = true;
        } catch (Exception e){
            e.printStackTrace();
            viewsAssigned = false;
        }
    }

    public void command_noShow(View view) {
        if (noShow.isChecked()) {
            noShowDF.show(getFragmentManager(), "NoShowDialogFragment");
        }
    }

    public void setNoShow(boolean b){
        noShow.setChecked(b);
    }
}
