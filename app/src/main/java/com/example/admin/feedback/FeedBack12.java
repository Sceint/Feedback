package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack12 extends AppCompatActivity {

    RatingBar ratingBar12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back12);
        ratingBar12 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback13Page(View view){
        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
        connectDatabase.addData("Q12",ratingBar12.getRating());
        Intent nextPage=new Intent(FeedBack12.this,FeedBack13.class);
        startActivity(nextPage);
    }
}
