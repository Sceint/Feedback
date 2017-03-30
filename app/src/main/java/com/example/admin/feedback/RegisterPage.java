package com.example.admin.feedback;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterPage extends AppCompatActivity implements OnItemSelectedListener {

    EditText name, mobileNo;
    Spinner spinner1, spinner2, spinner3, spinner4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        name = (EditText) findViewById(R.id.name);
        mobileNo = (EditText) findViewById(R.id.age);

        spinner1 = (Spinner) findViewById(R.id.branch);
        spinner2 = (Spinner) findViewById(R.id.section);
        spinner3 = (Spinner) findViewById(R.id.occupation);
        spinner4 = (Spinner) findViewById(R.id.year);
        ArrayAdapter myAdapter = ArrayAdapter.createFromResource(this, R.array.branch, android.R.layout.simple_spinner_item);
        spinner1.setAdapter(myAdapter);
        spinner1.setOnItemSelectedListener(this);
    }

    boolean nameCheck() {
        String check = name.getText().toString();
        if(check.length() > 25){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Name...!!");
            builder.setMessage("Maximum Length = 25 Characters")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }
        if(check.matches("[A-Za-z]*(\\s[A-Za-z]*){0,2}") || check.equals(""))
            return true;
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Name...!!");
            builder.setMessage("Only Alphabet are allowed..!!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }
    }

    boolean numberCheck(){
        if(mobileNo.getText().toString().length() == 0 || mobileNo.getText().toString().length() == 10)
            return true;
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Number...!!");
            builder.setMessage("Should contain 10 digits")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            return false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        if (spinner1.getSelectedItem().equals("CSE")) {
            ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                    R.array.section1, android.R.layout.simple_spinner_item);
            spinner2.setAdapter(adapter);
        } else if (spinner1.getSelectedItem().equals("ECE")) {
            ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                    R.array.section3, android.R.layout.simple_spinner_item);
            spinner2.setAdapter(adapter);
        } else {
            ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                    R.array.section2, android.R.layout.simple_spinner_item);
            spinner2.setAdapter(adapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void gotoFeedback1Page(View view) {
        if (nameCheck() && numberCheck()) {
            ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
            connectDatabase.getParentData(spinner1.getSelectedItem().toString(), spinner2.getSelectedItem().toString(),
                    spinner4.getSelectedItem().toString(), name.getText().toString(), mobileNo.getText().toString(), spinner3.getSelectedItem().toString());
            connectDatabase.pushParentData();
            ConnectDatabase.getInstance().getStatus(this);
            Intent nextPage = new Intent(RegisterPage.this, FeedBack1.class);
            startActivity(nextPage);
        }
    }

}
