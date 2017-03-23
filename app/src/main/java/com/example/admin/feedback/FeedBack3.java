package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedBack3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back3);
    }
    public void gotoFeedback4Page(View view){
        Intent nextPage=new Intent(FeedBack3.this,FeedBack4.class);
        startActivity(nextPage);
    }
    public void gotoFeedback2Page(View view){
        Intent nextPage=new Intent(FeedBack3.this,FeedBack2.class);
        startActivity(nextPage);
    }
}
