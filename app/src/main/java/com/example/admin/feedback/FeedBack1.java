package com.example.admin.feedback;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import java.security.spec.ECField;

public class FeedBack1 extends AppCompatActivity {

    private RatingBar ratingBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back1);
        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar);
    }

    public void gotoFeedback2Page(View view) {
        if (ratingBar1.getRating() != 0.0) {
            OfflineStoreHelper offlineStoreHelper = OfflineStoreHelper.getInstance(this);
            offlineStoreHelper.getRatingFromApp("Q1", Integer.parseInt(String.valueOf(Math.round(ratingBar1.getRating()))));

            Intent nextPage = new Intent(FeedBack1.this, FeedBack2.class);
            startActivity(nextPage);
        }
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Not Rated");
            builder.setMessage("Please Give a Rating.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder.create().show();
        }
    }
}
