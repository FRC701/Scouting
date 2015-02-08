package com.vandenrobotics.functionfirst.tools;

import android.media.Image;
import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Programming701-A on 2/7/2015.
 */
public class ExternalStorageTools {

    private static final File BASE_DIR = Environment.getExternalStorageDirectory();
    /*
    // saves image to the main image directory, naming it by team number and deleting duplicates
    public static void saveImage(Image image, int teamNumber){

    }

    // loads an image for a team (from Images directory)
    public static Image loadImage(int teamNumber){

    }
    */

    // writes all events currently downloaded to the file (as a JSONDocument)
    public static void writeEvents(ArrayList<JSONObject> events) throws JSONException {
        JSONArray downloadedEvents = new JSONArray(events);
        if(isExternalStorageWritable()){
            try {
                FileWriter fileWriter = new FileWriter(createFile("ScoutData","event.txt"));
                fileWriter.write(downloadedEvents.toString());
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /*
    // reads the JSONDocument and returns a JSONArray of events (BASE_DIR)
    public static ArrayList<JSONObject> readEvents(){

    }

    // writes all teams at an event the file under directory BASE_DIR/event (as a JSONDocument)
    public static void writeTeams(JSONArray teams, String event){

    }

    // reads the JSONDocument and returns a JSONArray of teams (from a certain event (BASE_DIR/event)
    public static JSONArray readTeams(String event){

    }

    // writes the device number to the event directory
    public static void writeDevice(int device, String event){

    }

    // reads the device number from the event directory
    public static int readDevice(String event){

    }

    // writes the currentMatch to the event / device number directory
    public static void writeMatch(int match, String event, int device){

    }

    // reads the currentMatch from the event / device number directory
    public static int readMatch(String event, int device){

    }

    // creates a text-file matchlist out of the JSONArray
    public static void writeMatchList(JSONArray matches, String event){

    }

    // reads the text-file matchlist and creates a matchlist to return
    public static MatchList readMatchList(String event){

    }

    // writes a JSONDocument data file out of MatchData to the event/device directory
    public static void writeData(MatchData matchData, String event, int device){

    }

    // reads the JSONDocument data file in to the device and into a MatchData value
    public static MatchData readData(String event, int device){

    }
    */

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }

    public static File createDirectory(String dir){
        File f = new File(BASE_DIR.getAbsolutePath() + "/" + dir);
        if(!f.exists())
            f.mkdirs();
        return f;
    }

    public static File createFile(String dir, String filename){
        File path = createDirectory(dir);
        File f = new File(path, filename);
        return f;
    }

}
