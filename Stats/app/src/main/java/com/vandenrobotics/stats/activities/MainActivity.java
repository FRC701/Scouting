package com.vandenrobotics.stats.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.vandenrobotics.stats.R;
import com.vandenrobotics.stats.data.DataBaseMerger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Merge(View view){
        DataBaseMerger.merge();
    }
}
