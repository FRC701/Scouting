package com.vandenrobotics.stats.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import org.apache.commons.math3.distribution.NormalDistribution;

import android.widget.TextView;

import com.vandenrobotics.stats.R;
import com.vandenrobotics.stats.data.model.AvgWeights;

public class MatchPrediction extends AppCompatActivity {
    private EditText red1_et;
    private EditText red2_et;
    private EditText red3_et;
    private EditText blue1_et;
    private EditText blue2_et;
    private EditText blue3_et;
    private TextView winner;

    int red1;
    int red2;
    int red3;
    int blue1;
    int blue2;
    int blue3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_pred);

        red1_et = (EditText)findViewById(R.id.red1_Et);
        red2_et = (EditText)findViewById(R.id.red2_Et);
        red3_et = (EditText)findViewById(R.id.red3_Et);
        blue1_et = (EditText)findViewById(R.id.blue1_Et);
        blue2_et = (EditText)findViewById(R.id.blue2_Et);
        blue3_et = (EditText)findViewById(R.id.blue3_Et);
        winner = (TextView) findViewById(R.id.winner);

        red1 = Integer.parseInt(red1_et.getText().toString());
        red2 = Integer.parseInt(red2_et.getText().toString());
        red3 = Integer.parseInt(red3_et.getText().toString());
        blue1 = Integer.parseInt(blue1_et.getText().toString());
        blue2 = Integer.parseInt(blue2_et.getText().toString());
        blue3 = Integer.parseInt(blue3_et.getText().toString());


    }
    private void predict(){
        double red1Score = 0;
        double red2Score = 0;
        double red3Score = 0;
        double blue1Score = 0;
        double blue2Score = 0;
        double blue3Score = 0;
        double [][] sigma = new double[2][3];

        AvgWeights avgWeights = new AvgWeights();
        if(avgWeights.getTeamNum() == red1){
            red1Score = avgWeights.getAvgTotalWS();
        }
        if(avgWeights.getTeamNum() == red2){
            red2Score = avgWeights.getAvgTotalWS();
        }
        if(avgWeights.getTeamNum() == red3){
            red3Score = avgWeights.getAvgTotalWS();
        }
        if(avgWeights.getTeamNum() == blue1){
            blue1Score = avgWeights.getAvgTotalWS();
        }
        if(avgWeights.getTeamNum() == blue2){
            blue2Score = avgWeights.getAvgTotalWS();
        }
        if(avgWeights.getTeamNum() == blue3){
            blue2Score = avgWeights.getAvgTotalWS();
        }

        double mur = 1/3*(red1Score+red2Score+red3Score);//red mean
        double mub = 1/3*(blue1Score+blue2Score+blue3Score);//blue mean

        sigma[0][0] = Math.pow(red1Score - mur, 2);
        sigma[0][1] = Math.pow(red2Score - mur, 2);
        sigma[0][2] = Math.pow(red3Score - mur, 2);
        sigma[1][0] = Math.pow(blue1Score - mub, 2);
        sigma[1][1] = Math.pow(blue2Score - mub, 2);
        sigma[1][2] = Math.pow(blue3Score - mub, 2);

        double rst = Math.sqrt((sigma[0][0]+sigma[0][1]+sigma[0][2])/3);//standard deviation
        double bst = Math.sqrt((sigma[1][0]+sigma[1][1]+sigma[1][2])/3);

        if(mur>mub){
            double zval = 0;
            if(Math.sqrt((rst*rst)+(bst*bst))>0){
                zval = (mur-mub)/Math.sqrt((rst*rst)+ (bst*bst));
            }
            NormalDistribution nd = new NormalDistribution();

            double perr = nd.cumulativeProbability(zval);

            winner.setText("Red Alliance: " + perr*100);
        }
        else{
            double zval = 0;
            if(Math.sqrt((rst*rst)+(bst*bst))>0){
                zval = (mub-mur)/Math.sqrt((rst*rst)+ (bst*bst));
            }
            NormalDistribution nd = new NormalDistribution();

            double perr = nd.cumulativeProbability(zval);

            winner.setText("Blue Alliance: " + perr*100);
        }
    }
}
