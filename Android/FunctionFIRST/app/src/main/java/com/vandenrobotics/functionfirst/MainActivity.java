package com.vandenrobotics.functionfirst;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import org.apache.http.Header;
import org.json.*;

import com.loopj.android.http.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * MainActivity screen which handles the main menus that allow the
 * user to navigate through to the rest of the application
 * Includes --> Scout Activity for a certain event (as chosen by user)
 *          --> About Message
 */
public class MainActivity extends Activity {

    private ArrayList<JSONObject> eventList;

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
        TheBlueAllianceRestClient.get(this, "events/", TheBlueAllianceRestClient.GET_HEADER, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray events) {
                // handle the incoming JSONArray of events and create a dialog with a list view
                try {
                    eventList = JSONArrayParser(events);
                    sortJSONArray(eventList, "start_date");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        if(eventList!=null) {
            System.out.println(eventList.size());
            for(int i = 0; i < eventList.size(); i++){
                System.out.println(eventList.get(i).getString("name") + "\n\t" + eventList.get(i).getString("start_date"));
            }
        }
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

    private ArrayList<JSONObject> JSONArrayParser(JSONArray jsonArray) throws JSONException
    {
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++)
            jsonObjects.add(jsonArray.getJSONObject(i));
        return jsonObjects;
    }

    private void sortJSONArray(ArrayList<JSONObject> jsonObjects, final String sortParam){
        Collections.sort(jsonObjects, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject a, JSONObject b) {
                try{
                    return a.getString(sortParam).compareTo(b.getString(sortParam));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return -1;
            }
        });
    }
}
