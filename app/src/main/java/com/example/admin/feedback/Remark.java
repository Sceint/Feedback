package com.example.admin.feedback;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Remark extends AppCompatActivity {

    EditText remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remark);
        remark = (EditText) findViewById(R.id.remark);
        ConnectDatabase.getInstance().getStatus(this);
    }

    public void gotoFinishPage(View view) {
        ConnectDatabase connectDatabase = ConnectDatabase.getInstance();
        connectDatabase.getRemark(remark.getText().toString());
        connectDatabase.uploadRemark();
        Intent nextPage = new Intent(Remark.this, Welcome.class);
        startActivity(nextPage);
    }
}
