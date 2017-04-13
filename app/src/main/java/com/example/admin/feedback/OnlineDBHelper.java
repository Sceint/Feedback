package com.example.admin.feedback;

import android.os.AsyncTask;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class OnlineDBHelper {

    void uploadParentData(JSONArray jsonArray) {

        class WriteData extends AsyncTask<JSONArray, Void, Void> {
            @Override
            protected Void doInBackground(JSONArray... resultArray) {
                try {
                    String login_url = "http://sceint.5gbfree.com/JSON2DBParent.php";

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    bufferedWriter.write(resultArray[0].toString());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String s;
                    StringBuilder sb = new StringBuilder("");
                    while ((s = bufferedReader.readLine()) != null)
                        sb.append(s);
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void s) {
            }
        }
        WriteData writeData = new WriteData();
        writeData.execute(jsonArray);
    }

    void uploadRatingData(JSONArray jsonArray) {

        class WriteData extends AsyncTask<JSONArray, Void, Void> {
            @Override
            protected Void doInBackground(JSONArray... resultArray) {
                try {
                    String login_url = "http://sceint.5gbfree.com/JSON2DBRating.php";

                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    bufferedWriter.write(resultArray[0].toString());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String s;
                    StringBuilder sb = new StringBuilder("");
                    while ((s = bufferedReader.readLine()) != null)
                        sb.append(s);
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void s) {
                    UploadData.updateProgress();
            }
        }
        WriteData writeData = new WriteData();
        writeData.execute(jsonArray);
    }
}
