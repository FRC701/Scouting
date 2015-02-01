package com.vandenrobotics.functionfirst;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.*;
import com.loopj.android.http.*;


/**
 * MainActivity screen which handles the main menus that allow the
 * user to navigate through to the rest of the application
 * Includes --> Scout Activity for a certain event (as chosen by user)
 *          --> About Message
 */
public class MainActivity extends Activity {

    private static Header[] GET_HEADER = {new BasicHeader("X-TBA-App-Id", "frc701:scouting-system:dev_v01")};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * activityScout function, which loads up the scout activity for a specific event
     * accesses TheBlueAlliance API to gain a list of events, and puts them inside a list
     * contained within a dialog.  The user chooses and event and the app downloads all
     * relevant data for that event.
     * --> team list, match schedule (if available), and team pictures
     */
    public void activityScout(View view) throws JSONException {

        // no need to pass a year to the API, as it will default to the current year, which is always what we want
        TheBlueAllianceRestClient.get(this, "events/", GET_HEADER, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                //if the reponse is JSONObject instead of expected JSONArray
                System.out.println("SYSTEM SUCCESS - JSON OBJECT");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray events) {
                System.out.println("SYSTEM SUCCESS - JSON ARRAY");
            }
        });
        //startActivity(new Intent(this, com.vandenrobotics.functionfirst.scout.ScoutActivity.class));
    }

    public void messageAbout(View view) {
        AlertDialog.Builder messageAbout = new AlertDialog.Builder(this);
        messageAbout.setMessage(R.string.text_messageAbout)
                .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id){
                        // pass through and simply close the dialog
                    }
                })
                .show();
    }
}
