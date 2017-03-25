package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void gotoRegisterPage(View view){
        ConnectDatabase.getInstance();
        Intent nextPage=new Intent(MainActivity.this,RegisterPage.class);
        startActivity(nextPage);
    }
}
