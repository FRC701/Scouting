package com.vandenrobotics.functionfirst;

import android.content.Context;

import com.loopj.android.http.*;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/**
 * Created by Programming701-A on 2/1/2015.
 */
public class TheBlueAllianceRestClient {

    public static Header[] GET_HEADER = {new BasicHeader("X-TBA-App-Id", "frc701:scouting-system:dev_v01")};
    private static final String BASE_URL = "http://www.thebluealliance.com/api/v2/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(Context context, String url, Header[] headers, RequestParams params, AsyncHttpResponseHandler responseHandler){
        client.get(context, getAbsoluteUrl(url), headers, params, responseHandler);
    }

    public static String getAbsoluteUrl(String relativeUrl){
        return BASE_URL + relativeUrl;
    }
}
