package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack10 extends AppCompatActivity {

    RatingBar ratingBar10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back10);
        ratingBar10 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback11Page(View view){
        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
        connectDatabase.addData("Q10",ratingBar10.getRating());
        Intent nextPage=new Intent(FeedBack10.this,FeedBack11.class);
        startActivity(nextPage);
    }
}
