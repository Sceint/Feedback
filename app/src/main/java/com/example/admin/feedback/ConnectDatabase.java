package com.example.admin.feedback;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by sceint on 3/23/17.
 */

public class ConnectDatabase {

    HttpURLConnection httpURLConnection;

    void connect(){
        String login_url = "http://192.168.2.4/android_login.php";

        try {
            URL url = new URL(login_url);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    void disconnect(){
        httpURLConnection.disconnect();
    }

    void getData(){

    }
}
