package com.vandenrobotics.functionfirst.scout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.vandenrobotics.functionfirst.R;
import com.vandenrobotics.functionfirst.tools.JSONTools;
import com.vandenrobotics.functionfirst.tools.TheBlueAllianceRestClient;
import com.vandenrobotics.functionfirst.tools.EventArrayAdapter;
import com.vandenrobotics.functionfirst.views.EventListView;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventListActivity extends Activity {

    private ArrayList<JSONObject> tbaEvents = new ArrayList<>();
    private ArrayList<JSONObject> downloadedEvents = new ArrayList<>();

    private EventListView tbaListView;
    private EventArrayAdapter tbaAdapter;

    private ListView downloadedListView;
    private ArrayAdapter<JSONObject> downloadedAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        // reset all Blue Alliance Events so that the list does not appear when we do not have internet connection
        tbaEvents = new ArrayList<>();

        // access all downloaded events and the corresponding json documents, putting them into an ArrayList

        // sort the loaded list by start_date
        downloadedEvents = JSONTools.sortJSONArray(downloadedEvents, "start_date", "name");

        // check online status to see if we can load the Blue Alliance Data, otherwise load the dialog without it
        if (isOnline()) {
            TheBlueAllianceRestClient.get(this, "events/", new JsonHttpResponseHandler() {
                // no need to pass a year to the API, as it will default to the current year, which is always what we want
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray events) {
                    // handle the incoming JSONArray of events and create a dialog with a list view
                    try {
                        tbaEvents = JSONTools.parseJSONArray(events);
                        tbaEvents = JSONTools.sortJSONArray(tbaEvents, "start_date", "name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    loadEventList();
                }
            });
        } else {
            loadEventList();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_settings:
                return true;
            case R.id.action_sort:
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadEventList() {

        tbaListView = (EventListView) findViewById(R.id.tbaEventListView);
        tbaAdapter = new EventArrayAdapter(tbaEvents, this);
        tbaListView.setAdapter(tbaAdapter);

        downloadedListView = (ListView) findViewById(R.id.downloadEventListView);
        downloadedAdapter = new ArrayAdapter<JSONObject>(this, android.R.layout.simple_list_item_2, android.R.id.text1, downloadedEvents){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                try {
                    text1.setText(downloadedEvents.get(position).getString("name"));
                    text2.setText("Start Date: " + downloadedEvents.get(position).getString("start_date"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return view;
            }
        };
        downloadedListView.setAdapter(downloadedAdapter);

        // download event and run now feature
        tbaListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                JSONObject event = (JSONObject) adapter.getItemAtPosition(position);

                // perform TheBlueAllianceRestClient get on that value, and run the saving of data and pictures, etc. to a competition file
                downloadEvent(event);
            }
        });

        // download event for later feature
        tbaListView.setOnItemLongClickListener(new ListView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View v, final int position, long arg3) {
                JSONObject event = (JSONObject) adapter.getItemAtPosition(position);

                // download the event but do not load it immediately (downloading for later)
                downloadEvent(event);
                return false;
            }
        });

        // load downloaded event feature
        downloadedListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                JSONObject event = (JSONObject) adapter.getItemAtPosition(position);
                // search the directory for all data, load data, and load the scout activity with that information
            }
        });

        // delete downloaded event feature
        downloadedListView.setOnItemLongClickListener(new ListView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View v, final int position, long arg3) {
                AlertDialog.Builder messageConfirmDelete = new AlertDialog.Builder(EventListActivity.this);
                messageConfirmDelete.setTitle(R.string.text_titleConfirmDelete);
                messageConfirmDelete.setMessage(R.string.text_messageConfirmDelete)
                        .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // delete the event and all releative data
                                downloadedEvents.remove(position);
                                downloadedEvents = JSONTools.sortJSONArray(downloadedEvents, "start_date", "name");
                                downloadedAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // pass through and close the dialog
                            }
                        })
                        .show();
                return false;
            }
        });
    }

    private void downloadEvent(JSONObject event){
        try {
            boolean newEvent = true;
            for (int i = 0; i < downloadedEvents.size(); i++) {
                if (downloadedEvents.get(i).getString("name").equals(event.getString("name"))) {
                    newEvent = false;
                    break;
                }
            }
            if (newEvent) {
                downloadedEvents.add(event);
                downloadedEvents = JSONTools.sortJSONArray(downloadedEvents, "start_date", "name");
                downloadedAdapter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean isOnline(){
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
