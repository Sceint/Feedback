package com.example.admin.feedback;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack8 extends AppCompatActivity {

    RatingBar ratingBar8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back8);
        ratingBar8 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback9Page(View view){
        OfflineStoreHelper offlineStoreHelper = OfflineStoreHelper.getInstance(this);
        offlineStoreHelper.getRatingFromApp("Q8", Integer.parseInt(String.valueOf(Math.round(ratingBar8.getRating()))));

//        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
//        connectDatabase.addData("Q08",ratingBar8.getRating());
        Intent nextPage=new Intent(FeedBack8.this,FeedBack9.class);
        startActivity(nextPage);
    }
}
