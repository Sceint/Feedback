package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedBack6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back6);
    }
    public void gotoFeedback7Page(View view){
        Intent nextPage=new Intent(FeedBack6.this,FeedBack7.class);
        startActivity(nextPage);
    }
}
