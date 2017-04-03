package com.example.admin.feedback;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack15 extends AppCompatActivity {

    RatingBar ratingBar15;
    OfflineRatingDataDB offlineRatingDataDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back15);
        ratingBar15 = (RatingBar) findViewById(R.id.ratingBar);
        offlineRatingDataDB = new OfflineRatingDataDB(this);
    }

    public void gotoRemarkPage(View view) {
        OfflineStoreHelper offlineStoreHelper = OfflineStoreHelper.getInstance(this);
        offlineStoreHelper.getRatingFromApp("Q15", Integer.parseInt(String.valueOf(Math.round(ratingBar15.getRating()))));
        offlineStoreHelper.insertRatingData();

//        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
//        connectDatabase.addData("Q15", ratingBar15.getRating());
//        connectDatabase.pushFeedbackData();
        Intent nextPage = new Intent(FeedBack15.this, Remark.class);
        startActivity(nextPage);
    }
}
