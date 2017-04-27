package com.example.admin.feedback;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

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

    void uploadParentData(JSONArray jsonArray, Context context) {
        uploadData(jsonArray, "JSON2DBParent.php", context);
    }

    void uploadRatingData(JSONArray jsonArray, Context context) {
        uploadData(jsonArray, "JSON2DBRating.php", context);
    }

    void uploadData(JSONArray jsonArray, final String url, final Context context) {
        class WriteData extends AsyncTask<JSONArray, Void, String> {
            String login_url = "https://sceint.000webhostapp.com/";

            @Override
            protected String doInBackground(JSONArray... resultArray) {
                try {
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
                    return sb.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (!s.equals("Successful")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Cannot Upload");
                    builder.setMessage("Data could not be uploaded due to Network error.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    builder.create().show();
                }
            }

            @Override
            protected void onPreExecute() {
                login_url += url;
            }
        }
        WriteData writeData = new WriteData();
        writeData.execute(jsonArray);
    }
}
