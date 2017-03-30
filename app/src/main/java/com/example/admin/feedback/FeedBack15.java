package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack15 extends AppCompatActivity {

    RatingBar ratingBar15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back15);
        ratingBar15 = (RatingBar) findViewById(R.id.ratingBar);
    }

    public void gotoRemarkPage(View view) {
        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
        connectDatabase.addData("Q15", ratingBar15.getRating());
        connectDatabase.pushFeedbackData();
        Intent nextPage = new Intent(FeedBack15.this, Remark.class);
        startActivity(nextPage);
    }
}
