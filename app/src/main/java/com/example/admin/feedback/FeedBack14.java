package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack14 extends AppCompatActivity {

    RatingBar ratingBar14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back14);
        ratingBar14 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback15Page(View view){
        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
        connectDatabase.addData("Q14",ratingBar14.getRating());
        Intent nextPage=new Intent(FeedBack14.this,FeedBack15.class);
        startActivity(nextPage);
    }
}
