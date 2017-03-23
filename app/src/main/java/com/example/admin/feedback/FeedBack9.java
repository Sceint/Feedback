package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedBack9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back9);
    }
    public void gotoFeedback10Page(View view){
        Intent nextPage=new Intent(FeedBack9.this,FeedBack10.class);
        startActivity(nextPage);
    }
}
