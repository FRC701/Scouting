package com.vandenrobotics.tyr2018.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vandenrobotics.tyr2018.R;
import com.vandenrobotics.tyr2018.data.model.TeamInfo;

public class OptionScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_screen);
    }
    public void pit_scout(View view){
        Intent intent = new Intent(this, PitDisplay.class );
        startActivity(intent);

    }
    public void min_max(View view){
        Intent intent = new Intent(this, MinMax.class );
        startActivity(intent);

    }
    public void team_info(View view){
        Intent intent = new Intent(this, TeamInfoDisplay.class );
        startActivity(intent);

    }
}
