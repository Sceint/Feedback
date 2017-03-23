package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedBack8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back8);
    }
    public void gotoFeedback9Page(View view){
        Intent nextPage=new Intent(FeedBack8.this,FeedBack9.class);
        startActivity(nextPage);
    }
}
