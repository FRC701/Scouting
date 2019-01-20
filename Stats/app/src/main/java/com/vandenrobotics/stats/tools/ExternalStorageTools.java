package com.vandenrobotics.stats.tools;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.FileChannel;


public class ExternalStorageTools {

    private static final File BASE_DIR = Environment.getExternalStorageDirectory();
    private static final File DATA_DIR = Environment.getDataDirectory();
    private static final String TAG = ExternalStorageTools.class.getSimpleName();


    public static void readDatabaseFromES(int device){
        try {
            if (isExternalStorageReadable()) {
                String currentDBPath = "//data//com.vandenrobotics.saga2018//databases//ScoutingData.db";
                String backupDBPath = "ScoutingData"+device+".db";
//                File currentDB = new File(DATA_DIR, currentDBPath);
                Log.d(TAG, "Can get from database");
                File sd = Environment.getExternalStorageDirectory();
                File data  = Environment.getDataDirectory();

                File  backupDB= new File(data, currentDBPath);
                File currentDB  = new File(sd, backupDBPath);


                if (currentDB.exists()) {
//                    String stringArray[] = currentDBPath.split(":");
//                    FileInputStream fis = null;
//                    OutputStream os = null;
//                    String selectedFile = Environment.getExternalStorageDirectory() + "/" + stringArray[1];
//                    File file = new File(selectedFile);
//                    fis = new FileInputStream(file);
//                    os = new FileOutputStream(currentDB);
//                    byte[] buffer = new byte[1024];
//                    int length= fis.read(buffer);
//                    while (length > 0) {
//                        os.write(buffer, 0, length);
//                    }
//                    os.flush();
//                    os.close();`
//                    fis.close();

                    if (sd.canWrite()) {

                        FileChannel src = new FileInputStream(currentDB).getChannel();
                        FileChannel dst = new FileOutputStream(backupDB).getChannel();
                        dst.transferFrom(src, 0, src.size());
                        src.close();
                        dst.close();

                    }


                }
            }
            else
            {
                Log.d(TAG, "Can not write database");

            }
        } catch (Exception e) {
        }
    }

    public static void writeImage(Bitmap image, int team_number) {
        if (isExternalStorageWritable()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createFile("ScoutData/Images", team_number + ".png"));
                image.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static File readImage(int team_number) {
        return createFile("ScoutData/Images", team_number + ".png");
    }

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
        if(!f.exists())
            try {
                f.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        return f;
    }

    public static void deleteFiles(String dir){
        deleteDirectory(new File(BASE_DIR.getAbsolutePath()+"/ScoutData/"+dir));
    }

    public static boolean deleteDirectory(File path) {
        if( path.exists() ) {
            File[] files = path.listFiles();
            if (files == null) {
                return true;
            }
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return( path.delete() );
    }

}
