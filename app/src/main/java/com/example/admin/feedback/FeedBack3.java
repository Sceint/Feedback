package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack3 extends AppCompatActivity {

    private RatingBar ratingBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back3);
        ratingBar3 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback4Page(View view){
        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
        connectDatabase.addData("Q03",ratingBar3.getRating());
        Intent nextPage=new Intent(FeedBack3.this,FeedBack4.class);
        startActivity(nextPage);
    }
}
