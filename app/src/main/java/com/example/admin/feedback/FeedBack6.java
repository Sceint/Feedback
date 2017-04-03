package com.example.admin.feedback;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack6 extends AppCompatActivity {

    RatingBar ratingBar6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back6);
        ratingBar6 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback7Page(View view){
        OfflineStoreHelper offlineStoreHelper = OfflineStoreHelper.getInstance(this);
        offlineStoreHelper.getRatingFromApp("Q6", Integer.parseInt(String.valueOf(Math.round(ratingBar6.getRating()))));

//        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
//        connectDatabase.addData("Q06",ratingBar6.getRating());
        Intent nextPage=new Intent(FeedBack6.this,FeedBack7.class);
        startActivity(nextPage);
    }
}
