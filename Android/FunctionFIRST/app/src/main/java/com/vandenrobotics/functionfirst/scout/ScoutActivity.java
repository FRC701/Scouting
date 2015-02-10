package com.vandenrobotics.functionfirst.scout;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.tools.ImageTools;

public class ScoutActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scout);
        String event = getIntent().getStringExtra("event");

        ImageView teamPic = (ImageView) findViewById(R.id.teamPic);
        ImageTools.placeImage(this, 701, teamPic);

    }
}
