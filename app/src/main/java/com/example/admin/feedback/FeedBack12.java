package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedBack12 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back12);
    }
    public void gotoFeedback13Page(View view){
        Intent nextPage=new Intent(FeedBack12.this,FeedBack13.class);
        startActivity(nextPage);
    }
}
