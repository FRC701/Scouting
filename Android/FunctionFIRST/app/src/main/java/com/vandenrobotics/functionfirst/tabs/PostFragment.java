package com.vandenrobotics.functionfirst.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vandenrobotics.functionfirst.R;

/**
 * Created by Programming701-A on 12/11/2014.
 */
public class PostFragment extends Fragment {

    public static PostFragment newInstance(){
        PostFragment postFrag = new PostFragment();

        return postFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_post, container, false);

        return rootView;
    }
}
