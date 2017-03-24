package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack4 extends AppCompatActivity {

    RatingBar ratingBar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back4);
        ratingBar4 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback5Page(View view){
        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
        connectDatabase.addData("Q04",ratingBar4.getRating());
        Intent nextPage=new Intent(FeedBack4.this,FeedBack5.class);
        startActivity(nextPage);
    }
}
