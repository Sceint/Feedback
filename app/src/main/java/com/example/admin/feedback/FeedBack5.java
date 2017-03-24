package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack5 extends AppCompatActivity {

    RatingBar ratingBar5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back5);
        ratingBar5 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback6Page(View view){
        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
        connectDatabase.addData("Q05",ratingBar5.getRating());
        Intent nextPage=new Intent(FeedBack5.this,FeedBack6.class);
        startActivity(nextPage);
    }
}
