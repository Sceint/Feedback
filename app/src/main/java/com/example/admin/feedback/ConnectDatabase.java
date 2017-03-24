package com.example.admin.feedback;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by sceint on 3/23/17.
 */

class ConnectDatabase extends Activity{

    private HttpURLConnection httpURLConnection;
    private static ConnectDatabase connectDatabase;
    private StringBuilder stringBuilder;

    private ConnectDatabase() {

    }

    static ConnectDatabase getInstance() {
        if (connectDatabase == null)
            connectDatabase = new ConnectDatabase();
        return connectDatabase;
    }

    void connect() {
        String login_url = "http://192.168.2.4/database.php";

        try {
            URL url = new URL(login_url);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
        } catch (Exception e) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setMessage("Exception:Can't connect to database");
            e.printStackTrace();
        }
    }

    void disconnect() {
        httpURLConnection.disconnect();
    }

    void addData(String s, Float f) {
        try {
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(URLEncoder.encode(s, "UTF-8")).append("=").append(URLEncoder.encode(String.valueOf(f), "UTF-8"));
            } else if (stringBuilder.indexOf(s) != -1) {
                stringBuilder.replace(stringBuilder.indexOf(s), stringBuilder.indexOf(s) + 7,
                        URLEncoder.encode(s, "UTF-8") + "=" + URLEncoder.encode(String.valueOf(f), "UTF-8"));
            } else {
                stringBuilder.append("&").append(URLEncoder.encode(s, "UTF-8")).append("=").append(URLEncoder.encode(String.valueOf(f), "UTF-8"));
            }
        } catch (UnsupportedEncodingException e) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setMessage("Exception:addData");
            e.printStackTrace();
        }
    }

//    void seeData() {
//        class SeeData extends AsyncTask<Void, Void, Void>{
//            @Override
//            protected Void doInBackground(Void... params) {
//                try {
//                    InputStream inputStream = httpURLConnection.getInputStream();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
//                    StringBuilder str = new StringBuilder();
//                    String s = "";
//                    while ((s = bufferedReader.readLine()) != null)
//                        str.append(s);
//                    bufferedReader.close();
//                    inputStream.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//        }
//        SeeData seeData = new SeeData();
//        seeData.execute();
//    }

    void pushData() {
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
