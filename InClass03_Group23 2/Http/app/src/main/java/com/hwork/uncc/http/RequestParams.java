package com.hwork.uncc.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by Rishi on 24/09/17.
 */

public class RequestParams {
    String method, baseurl;
    HashMap<String, String> params = new HashMap<String, String>();

    public RequestParams(String method, String baseurl) {
        this.method = method;
        this.baseurl = baseurl;
    }

    public void addParams(String key, String value) {
        params.put(key, value);
    }

    public String getParams() {
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            try {
                String value = URLEncoder.encode(params.get(key), "UTF-8");
                if (sb.length() > 0) {
                    sb.append("&");
                }
                    sb.append(key + "=" + value);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
public String getEncodedURL()
{
    return this.baseurl+"?"+getParams();
}
public HttpURLConnection setupConnection() {
    if (method.equals("GET")) {
        URL url = null;
        try {
            url = new URL(getEncodedURL());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            return con;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    else
    {
        try {
           URL url = new URL(this.baseurl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setDoOutput(true);
            OutputStreamWriter out=new OutputStreamWriter(con.getOutputStream());
            out.write(getParams());
            out.flush();
            return con;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    return null;
}
}
