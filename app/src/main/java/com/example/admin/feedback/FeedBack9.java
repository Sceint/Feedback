package com.example.admin.feedback;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack9 extends AppCompatActivity {

    RatingBar ratingBar9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back9);
        ratingBar9 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback10Page(View view){
        OfflineStoreHelper offlineStoreHelper = OfflineStoreHelper.getInstance(this);
        offlineStoreHelper.getRatingFromApp("Q9", Integer.parseInt(String.valueOf(Math.round(ratingBar9.getRating()))));

//        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
//        connectDatabase.addData("Q09",ratingBar9.getRating());
        Intent nextPage=new Intent(FeedBack9.this,FeedBack10.class);
        startActivity(nextPage);
    }
}
