package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedBack10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back10);
    }
    public void gotoFeedback11Page(View view){
        Intent nextPage=new Intent(FeedBack10.this,FeedBack11.class);
        startActivity(nextPage);
    }
}
