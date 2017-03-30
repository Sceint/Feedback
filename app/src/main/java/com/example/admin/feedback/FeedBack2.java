package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack2 extends AppCompatActivity {

    private RatingBar ratingBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back2);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback3Page(View view){
        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
        connectDatabase.addData("Q02",ratingBar2.getRating());
        Intent nextPage=new Intent(FeedBack2.this,FeedBack3.class);
        startActivity(nextPage);
    }
}
