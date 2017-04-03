package com.example.admin.feedback;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack7 extends AppCompatActivity {

    RatingBar ratingBar7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back7);
        ratingBar7 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback8Page(View view){
        OfflineStoreHelper offlineStoreHelper = OfflineStoreHelper.getInstance(this);
        offlineStoreHelper.getRatingFromApp("Q7", Integer.parseInt(String.valueOf(Math.round(ratingBar7.getRating()))));

//        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
//        connectDatabase.addData("Q07",ratingBar7.getRating());
        Intent nextPage=new Intent(FeedBack7.this,FeedBack8.class);
        startActivity(nextPage);
    }
}
