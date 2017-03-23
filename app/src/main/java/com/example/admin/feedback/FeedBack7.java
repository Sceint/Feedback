package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedBack7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back7);
    }
    public void gotoFeedback8Page(View view){
        Intent nextPage=new Intent(FeedBack7.this,FeedBack8.class);
        startActivity(nextPage);
    }
}
