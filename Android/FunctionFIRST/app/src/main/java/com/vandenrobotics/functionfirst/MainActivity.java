package com.vandenrobotics.functionfirst;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.apache.http.Header;
import org.json.*;

import com.loopj.android.http.*;
import com.vandenrobotics.functionfirst.events.*;

import android.widget.ArrayAdapter;
import android.widget.TextView;

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

    private ArrayList<JSONObject> tbaEventList;
    private ArrayList<String> tbaMissingList = new ArrayList<>();
    private ArrayList<JSONObject> downloadEventList;
    private ArrayList<String> downloadMissingList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tbaMissingList.add("Could not access The Blue Alliance");
        tbaMissingList.add("Try connecting to the internet");
        tbaMissingList.add("Or dismiss this dialog and try again");

        downloadMissingList.add("Could not find any downloaded events");
        downloadMissingList.add("Try selecting an event to download");
        downloadMissingList.add("Available events are listed below");
    }

    /**
     * activityScout function, which loads up the scout activity for a specific event
     * accesses TheBlueAlliance API to gain a list of events, and puts them inside a list
     * contained within a dialog.  The user chooses and event and the app downloads all
     * relevant data for that event.
     * --> team list, match schedule (if available), and team pictures
     */
    public void activityScout(View view) throws JSONException {

        /*  check the status of the internet connection
        --> on no connection, load a list of competitions that have already been downloaded
        --> on no downloaded competitions, prompt the user to enable internet in order to download a comp
        --> on internet connection, list downloaded competitions first, then all competitions by date
        --> uses The Blue Alliance API to grab data for all events
        */
        tbaEventList = null;

        if(isOnline()) {
            TheBlueAllianceRestClient.get(this, "events/", TheBlueAllianceRestClient.GET_HEADER, null, new JsonHttpResponseHandler() {
                // no need to pass a year to the API, as it will default to the current year, which is always what we want
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray events) {
                    // handle the incoming JSONArray of events and create a dialog with a list view
                    try {
                        tbaEventList = JSONArrayParser(events);
                        sortJSONArray(tbaEventList, "start_date");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        final Dialog event_chooser = new Dialog(this);
        event_chooser.setContentView(R.layout.event_chooser);
        event_chooser.setTitle("Select a FRC Event");

        try {
            EventListView tbaList = (EventListView) event_chooser.findViewById(R.id.tbaEventListView);
            EventArrayAdapter eventAdapter = new EventArrayAdapter(tbaEventList, this);
            tbaList.setAdapter(eventAdapter);
        } catch (NullPointerException e) {
            e.printStackTrace();
            ListView tbaList = (ListView) event_chooser.findViewById(R.id.tbaEventListView);
            ArrayAdapter<String> eventAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, tbaMissingList);
            tbaList.setAdapter(eventAdapter);
        }

        try{
            ListView downloadList = (ListView) event_chooser.findViewById(R.id.downloadEventListView);
            ArrayAdapter<JSONObject> downloadAdapter = new ArrayAdapter<JSONObject>(this,android.R.layout.simple_list_item_2,android.R.id.text1,downloadEventList){
                @Override
                public View getView(int position, View convertView, ViewGroup parent){
                    View view = super.getView(position, convertView, parent);
                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    try {
                        text1.setText(downloadEventList.get(position).getString("name"));
                        text2.setText("Start Date: " + downloadEventList.get(position).getString("start_date"));
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                    return view;
                }
            };
            downloadList.setAdapter(downloadAdapter);
        } catch (NullPointerException e) {
            e.printStackTrace();
            ListView downloadList = (ListView) event_chooser.findViewById(R.id.downloadEventListView);
            ArrayAdapter<String> downloadAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,downloadMissingList);
            downloadList.setAdapter(downloadAdapter);
        }

        event_chooser.show();
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

    private boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
