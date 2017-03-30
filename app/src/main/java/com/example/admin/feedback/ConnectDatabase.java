package com.example.admin.feedback;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
    private StringBuilder stringBuilder, parentData, remarkData;
    private String status = "Failed",id = "";

    private ConnectDatabase() {

    }

    static void clear(){
        connectDatabase = null;
    }

    static ConnectDatabase getInstance() {
        if (connectDatabase == null)
            connectDatabase = new ConnectDatabase();
        return connectDatabase;
    }

    void getStatus(Context context) {
        final Toast toast = Toast.makeText(context, status, Toast.LENGTH_SHORT);
        Thread thread=  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        wait(2000);
                    }
                }
                catch(InterruptedException ignored){
                }
                toast.setText(status);
                toast.show();
                status = "";
            }
        };
        thread.start();
    }

    void addData(String s, Float f) {
        try {
            if (stringBuilder == null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(URLEncoder.encode("data", "UTF-8")).append("=").append(URLEncoder.encode("ratingData", "UTF-8"))
                        .append("&").append(URLEncoder.encode("Q00", "UTF-8")).append("=").append(URLEncoder.encode(id , "UTF-8"))
                        .append("&").append(URLEncoder.encode(s, "UTF-8")).append("=").append(URLEncoder.encode(String.valueOf(Math.round(f)), "UTF-8"));
            }
            else if (stringBuilder.indexOf(s) != -1) {
                stringBuilder.replace(stringBuilder.indexOf(s), stringBuilder.indexOf(s) + 5,
                        URLEncoder.encode(s, "UTF-8") + "=" + URLEncoder.encode(String.valueOf(Math.round(f)), "UTF-8"));
            }
            else {
                stringBuilder.append("&").append(URLEncoder.encode(s, "UTF-8")).append("=").append(URLEncoder.encode(String.valueOf(Math.round(f)), "UTF-8"));
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    void getParentData(String... strings) {
        try {
            parentData = new StringBuilder();
            parentData.append(URLEncoder.encode("data", "UTF-8")).append("=").append(URLEncoder.encode("parentData", "UTF-8"));
            for (int i = 0; i < strings.length; i++) {
                parentData.append("&").append(URLEncoder.encode("par" + i, "UTF-8")).append("=").append(URLEncoder.encode(strings[i], "UTF-8"));
            }
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    void pushParentData() {
        class WriteData extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    String login_url = "http://192.168.2.4/AndroidPHP/parentDataUpload.php";

                    URL url = new URL(login_url);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    bufferedWriter.write(parentData.toString());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String s;
                    while ((s = bufferedReader.readLine()) != null)
                        id += s;
                    if(!id.equals(""))
                        status = "Successful";
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        WriteData writeData = new WriteData();
        writeData.execute();
    }

    void pushFeedbackData() {
        class WriteData extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    String login_url = "http://192.168.2.4/AndroidPHP/feedbackUpload.php";

                    URL url = new URL(login_url);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    bufferedWriter.write(stringBuilder.toString());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String s;
                    status = "";
                    while ((s = bufferedReader.readLine()) != null)
                        status += s;
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        WriteData writeData = new WriteData();
        writeData.execute();
    }

    void getRemark(String string){
        try {
            remarkData = new StringBuilder();
            remarkData.append(URLEncoder.encode("data", "UTF-8")).append("=").append(URLEncoder.encode("remarkData", "UTF-8"))
                    .append("&").append(URLEncoder.encode("Id", "UTF-8")).append("=").append(URLEncoder.encode(id , "UTF-8"))
                    .append("&").append(URLEncoder.encode("remark", "UTF-8")).append("=").append(URLEncoder.encode(string, "UTF-8"));
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    void uploadRemark(){
        class WriteData extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    String login_url = "http://192.168.2.4/AndroidPHP/remarkUpload.php";

                    URL url = new URL(login_url);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    bufferedWriter.write(remarkData.toString());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String s;
                    status = "";
                    while ((s = bufferedReader.readLine()) != null)
                        status += s;
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        WriteData writeData = new WriteData();
        writeData.execute();
    }
}
