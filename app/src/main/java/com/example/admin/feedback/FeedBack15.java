package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FeedBack15 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back15);
    }
    public void gotoFinishPage(View view){
        Intent nextPage=new Intent(FeedBack15.this,Welcome.class);
        startActivity(nextPage);
    }
}
