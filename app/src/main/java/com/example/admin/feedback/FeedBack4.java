package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedBack4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back4);
    }
    public void gotoFeedback5Page(View view){
        Intent nextPage=new Intent(FeedBack4.this,FeedBack5.class);
        startActivity(nextPage);
    }
    public void gotoFeedback3Page(View view){
        Intent nextPage=new Intent(FeedBack4.this,FeedBack3.class);
        startActivity(nextPage);
    }
}
