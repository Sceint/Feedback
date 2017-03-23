package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedBack14 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back14);
    }
    public void gotoFeedback15Page(View view){
        Intent nextPage=new Intent(FeedBack14.this,FeedBack15.class);
        startActivity(nextPage);
    }
}
