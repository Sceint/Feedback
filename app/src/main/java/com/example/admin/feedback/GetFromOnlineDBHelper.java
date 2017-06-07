package com.example.admin.feedback;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

class GetFromOnlineDBHelper extends AsyncTask<Void, Void, List<String>> {

    private WaitForResponse waitForResponse;
    private String data, invoke_url;

    GetFromOnlineDBHelper(String url, String data, WaitForResponse waitForResponse) {
        this.waitForResponse = waitForResponse;
        this.data = data;
        invoke_url = url;
    }

    @Override
    protected List<String> doInBackground(Void... v) {
        try {
            String login_url = "https://sceint.000webhostapp.com/";
            URL url = new URL(login_url + invoke_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write(URLEncoder.encode("value", "UTF-8") + "=" + URLEncoder.encode(data, "UTF-8"));
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String s;
            List<String> collect = new ArrayList<>();
            while ((s = bufferedReader.readLine()) != null)
                collect.add(s);
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return collect;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<String> list) {
        waitForResponse.processFinish(list, data);
    }
}