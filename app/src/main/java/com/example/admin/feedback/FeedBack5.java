package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedBack5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back5);
    }
    public void gotoFeedback6Page(View view){
        Intent nextPage=new Intent(FeedBack5.this,FeedBack6.class);
        startActivity(nextPage);
    }
    public void gotoFeedback4Page(View view){
        Intent nextPage=new Intent(FeedBack5.this,FeedBack4.class);
        startActivity(nextPage);
    }
}
