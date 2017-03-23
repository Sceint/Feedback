package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedBack2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back2);
    }
    public void gotoFeedback3Page(View view){
        Intent nextPage=new Intent(FeedBack2.this,FeedBack3.class);
        startActivity(nextPage);
    }
    public void gotoFeedback1Page(View view){
        Intent nextPage=new Intent(FeedBack2.this,FeedBack1.class);
        startActivity(nextPage);
    }
}
