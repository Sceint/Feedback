package com.example.admin.feedback;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
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
        name.setInputType(8192);
        mobileNo = (EditText) findViewById(R.id.age);
        spinner1 = (Spinner) findViewById(R.id.branch);
        spinner2 = (Spinner) findViewById(R.id.section);
        spinner3 = (Spinner) findViewById(R.id.occupation);
        spinner4 = (Spinner) findViewById(R.id.year);
        ArrayAdapter myAdapter = ArrayAdapter.createFromResource(this, R.array.branch, android.R.layout.simple_spinner_item);
        spinner1.setAdapter(myAdapter);
        spinner1.setOnItemSelectedListener(this);
    }

    boolean validationCheck() {
        return checkBranch() && checkSection() && checkYear() && nameCheck() && numberCheck() && checkOccupation();
    }

    boolean checkBranch() {
        if (!spinner1.getSelectedItem().toString().equals("SELECT"))
            return true;
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Branch");
            builder.setMessage("Please select a Branch")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder.create().show();
            return false;
        }
    }

    boolean checkSection() {
        if (!spinner2.getSelectedItem().toString().equals("SELECT"))
            return true;
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Section");
            builder.setMessage("Please select a Section")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder.create().show();
            return false;
        }
    }

    boolean checkYear() {
        if (!spinner4.getSelectedItem().toString().equals("SELECT"))
            return true;
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Year");
            builder.setMessage("Please select a Year")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder.create().show();
            return false;
        }
    }

    boolean checkOccupation() {
        if (!spinner3.getSelectedItem().toString().equals("SELECT"))
            return true;
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Occupation");
            builder.setMessage("Please select a Occupation")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder.create().show();
            return false;
        }
    }

    boolean nameCheck() {
        String check = name.getText().toString();
        if (check.matches("[A-Za-z]+(\\s[A-Za-z]+){0,2}") || check.length() == 0)
            return true;
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Name...!!");
            builder.setMessage("Enter a valid Name..!!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder.create().show();
            return false;
        }
    }

    boolean numberCheck() {
        String num = mobileNo.getText().toString();
        if (num.length() == 0)
            return true;
        else if (num.length() == 10 && (num.startsWith("7") || num.startsWith("8") || num.startsWith("9")))
            return true;
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Number...!!");
            builder.setMessage("Enter a valid 10 digit number")
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

    public String getDeviceUniqueID(Activity activity) {
        return Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public void gotoFeedback1Page(View view) {
        if (validationCheck()) {
            OfflineStoreHelper offlineStoreHelper = OfflineStoreHelper.getInstance(this);
            offlineStoreHelper.insertParentData(spinner1.getSelectedItem().toString(), spinner2.getSelectedItem().toString(),
                    spinner4.getSelectedItem().toString(), name.getText().toString(), mobileNo.getText().toString(),
                    spinner3.getSelectedItem().toString(), getDeviceUniqueID(this));

            Intent nextPage = new Intent(RegisterPage.this, FeedBack1.class);
            startActivity(nextPage);
        }
    }
}
