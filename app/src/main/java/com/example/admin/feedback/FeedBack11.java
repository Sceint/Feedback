package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedBack11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back11);
    }
    public void gotoFeedback12Page(View view){
        Intent nextPage=new Intent(FeedBack11.this,FeedBack12.class);
        startActivity(nextPage);
    }
}
