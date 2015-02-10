package com.vandenrobotics.functionfirst.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vandenrobotics.functionfirst.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


/**
 * Created by Programming701-A on 2/9/2015.
 */
public class ImageTools {

    private static final String BASE_URL = "http://www.chiefdelphi.com/media/img/";

    // download an image from online and turn it into an Image
    public static void downloadImage(String url, String teamnumber){
        // grab an image from the url given, and turn it into a bitmap
        new ImageDownloader().execute(url, teamnumber);
    }

    // access the external storage to grab an image and load it into the provided image view under context
    public static void placeImage(Context context, int teamnumber, ImageView imageView){
        Picasso.with(context)
               .load(ExternalStorageTools.readImage(teamnumber))
               .placeholder(R.drawable.nopic)
               .error(R.drawable.nopic)
               .into(imageView);
    }

    private static String getAbsoluteUrl(String relativeUrl){
        return BASE_URL + relativeUrl;
    }

    private static class ImageDownloader extends AsyncTask<String, Void, Bitmap>{

        private static int team_number;
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap image = null;
            team_number = Integer.parseInt(params[1]);
            try{
                InputStream inputStream = (InputStream) new URL(getAbsoluteUrl(params[0])).getContent();
                image = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
            return image;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ExternalStorageTools.writeImage(bitmap, team_number);
        }
    }
}
