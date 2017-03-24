package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack11 extends AppCompatActivity {

    RatingBar ratingBar11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back11);
        ratingBar11 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback12Page(View view){
        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
        connectDatabase.addData("Q11",ratingBar11.getRating());
        Intent nextPage=new Intent(FeedBack11.this,FeedBack12.class);
        startActivity(nextPage);
    }
}
