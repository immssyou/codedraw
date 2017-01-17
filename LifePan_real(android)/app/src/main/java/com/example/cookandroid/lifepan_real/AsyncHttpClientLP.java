package com.example.cookandroid.lifepan_real;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by immss_000 on 2015-11-17.
 */
public class AsyncHttpClientLP
{

     private static final String BASE_URL
             = "http://175.207.52.44:8080/";
             /*= "http://192.168.0.44:8082/";*/
 /*   private static final String BASE_URL = "http://175.207.52.44:8082/";*/

    private static AsyncHttpClient client = new AsyncHttpClient();

/*    public static AsyncHttpClient getInstance() {x`
        return AsyncHttpClientLP.client;
    }*/

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.setTimeout(70000);
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
