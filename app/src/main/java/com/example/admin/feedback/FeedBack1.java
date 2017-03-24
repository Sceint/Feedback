package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack1 extends AppCompatActivity {

    private RatingBar ratingBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back1);
        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback2Page(View view){
        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
        connectDatabase.addData("Q01",ratingBar1.getRating());
        Intent nextPage=new Intent(FeedBack1.this,FeedBack2.class);
        startActivity(nextPage);
    }
}
