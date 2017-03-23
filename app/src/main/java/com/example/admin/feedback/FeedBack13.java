package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedBack13 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back13);
    }
    public void gotoFeedback14Page(View view){
        Intent nextPage=new Intent(FeedBack13.this,FeedBack14.class);
        startActivity(nextPage);
    }
}
