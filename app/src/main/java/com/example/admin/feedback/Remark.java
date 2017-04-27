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
        remark.setInputType(16384);
    }

    public void gotoFinishPage(View view) {
        OfflineStoreHelper offlineStoreHelper = OfflineStoreHelper.getInstance(this);
        offlineStoreHelper.insertRemark(remark.getText().toString());
        Intent nextPage = new Intent(Remark.this, Welcome.class);
        startActivity(nextPage);
    }
}
