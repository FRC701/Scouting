package com.vandenrobotics.functionfirst.events;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.vandenrobotics.functionfirst.R;

/**
 * Created by Programming701-A on 2/6/2015.
 */
public class EventListDialog extends Activity {

    private ListView downloadedEventList;
    private EventListView tbaEventList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_chooser);


    }
}
