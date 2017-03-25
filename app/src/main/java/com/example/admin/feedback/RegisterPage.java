package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterPage extends AppCompatActivity implements OnItemSelectedListener{

    EditText name,age;
    Spinner spinner1,spinner2,spinner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);

        spinner1 = (Spinner) findViewById(R.id.branch);
        spinner2 = (Spinner) findViewById(R.id.section);
        spinner3 = (Spinner) findViewById(R.id.occupation);
        ArrayAdapter myAdapter = ArrayAdapter.createFromResource(this,R.array.branch,android.R.layout.simple_spinner_item);
        spinner1.setAdapter(myAdapter);
        spinner1.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        if (spinner1.getSelectedItem().equals("CSE")) {
            ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                    R.array.section1, android.R.layout.simple_spinner_item);
            spinner2.setAdapter(adapter);
        }
        else if(spinner1.getSelectedItem().equals("ECE")){
            ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                    R.array.section3, android.R.layout.simple_spinner_item);
            spinner2.setAdapter(adapter);
        }
        else{
            ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                    R.array.section2, android.R.layout.simple_spinner_item);
            spinner2.setAdapter(adapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void gotoFeedback1Page(View view){
        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
        connectDatabase.getParentData(spinner1.getSelectedItem().toString(),spinner2.getSelectedItem().toString() ,
                name.getText().toString(), age.getText().toString(), spinner3.getSelectedItem().toString());
        connectDatabase.pushParentData();
        Intent nextPage=new Intent(RegisterPage.this,FeedBack1.class);
        startActivity(nextPage);
    }

}
