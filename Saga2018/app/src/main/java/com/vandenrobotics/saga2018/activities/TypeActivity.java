package com.vandenrobotics.saga2018.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.vandenrobotics.saga2018.R;

public class TypeActivity extends AppCompatActivity {

    private String mEvent;
    private final String mTeamScout = "Team";
    private final String mOwnerScout = "Owner";
    private final String mVaultScout = "Vault";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        mEvent = getIntent().getStringExtra("event");
    }

    public void team_scout(View view){
        Intent intent = new Intent(this, ScoutActivity.class);
        intent.putExtra("event", mEvent);
        intent.putExtra("type", mTeamScout);
        startActivity(intent);
    }

    public void own_scout(View view){
        Intent intent = new Intent(this, OwnershipActivity.class);
        intent.putExtra("event", mEvent);
        intent.putExtra("type", mOwnerScout);
        startActivity(intent);
    }

    public void vault_scout(View view){
        Intent intent = new Intent(this, VaultActivity.class);
        intent.putExtra("event", mEvent);
        intent.putExtra("type", mVaultScout);
        startActivity(intent);
    }

}
