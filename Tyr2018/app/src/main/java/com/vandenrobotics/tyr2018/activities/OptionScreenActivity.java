package com.vandenrobotics.tyr2018.activities;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vandenrobotics.tyr2018.R;
import com.vandenrobotics.tyr2018.data.model.TeamInfo;

public class OptionScreenActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);
    }
    public void team_info(View view){
        Intent intent = new Intent(this, TeamInfoDisplay.class );
        startActivity(intent);

    }
    public void match_pred(View view){
        Intent intent = new Intent(this, MatchPrediction.class );
        startActivity(intent);

    }
}
