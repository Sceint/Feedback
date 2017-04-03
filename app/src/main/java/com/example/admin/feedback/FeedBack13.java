package com.example.admin.feedback;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack13 extends AppCompatActivity {

    RatingBar ratingBar13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back13);
        ratingBar13 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback14Page(View view){
        OfflineStoreHelper offlineStoreHelper = OfflineStoreHelper.getInstance(this);
        offlineStoreHelper.getRatingFromApp("Q13", Integer.parseInt(String.valueOf(Math.round(ratingBar13.getRating()))));

//        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
//        connectDatabase.addData("Q13",ratingBar13.getRating());
        Intent nextPage=new Intent(FeedBack13.this,FeedBack14.class);
        startActivity(nextPage);
    }
}
