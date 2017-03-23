package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Spinner mySpin=(Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> myAdapter=new ArrayAdapter<String>(RegisterPage.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.branch));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpin.setAdapter(myAdapter);


    }
    public void gotoFeedback1Page(View view){
        Intent nextPage=new Intent(RegisterPage.this,FeedBack1.class);
        startActivity(nextPage);
    }

}
