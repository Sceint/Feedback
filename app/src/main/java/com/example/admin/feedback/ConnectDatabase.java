package com.example.admin.feedback;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StreamCorruptedException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by sceint on 3/23/17.
 */

class ConnectDatabase {

    private HttpURLConnection httpURLConnection;
    private static ConnectDatabase connectDatabase;
    private StringBuilder stringBuilder,status;

    private ConnectDatabase() {

    }

    static ConnectDatabase getInstance() {
        if (connectDatabase == null)
            connectDatabase = new ConnectDatabase();
        return connectDatabase;
    }

    void getStatus(Context context){
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
        alertDialog.setMessage(status);
        alertDialog.show();
    }

    void connect() {
        String login_url = "http://192.168.2.7/database.php";

        try {
            URL url = new URL(login_url);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void addData(String s, Float f) {
        try {
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(URLEncoder.encode("data", "UTF-8")).append("=").append(URLEncoder.encode("ratingData", "UTF-8"))
                        .append("&").append(URLEncoder.encode(s, "UTF-8")).append("=").append(URLEncoder.encode(String.valueOf(Math.round(f)), "UTF-8"));
            } else if (stringBuilder.indexOf(s) != -1) {
                stringBuilder.replace(stringBuilder.indexOf(s), stringBuilder.indexOf(s) + 5,
                        URLEncoder.encode(s, "UTF-8") + "=" + URLEncoder.encode(String.valueOf(Math.round(f)), "UTF-8"));
            } else {
                stringBuilder.append("&").append(URLEncoder.encode(s, "UTF-8")).append("=").append(URLEncoder.encode(String.valueOf(Math.round(f)), "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    void pushParentData(){

    }

    void pushFeedbackData() {
        class WriteData extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    bufferedWriter.write(stringBuilder.toString());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    status = new StringBuilder();
                    String s;
                    while((s=bufferedReader.readLine()) != null)
                        status.append(s);
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

        }
        WriteData writeData = new WriteData();
        writeData.execute();
    }
}
