package com.example.admin.feedback;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack2 extends AppCompatActivity {

    private RatingBar ratingBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back2);
        ratingBar2 = (RatingBar) findViewById(R.id.ratingBar);
    }

    public void gotoFeedback3Page(View view) {
        try {
            if (ratingBar2.getRating() != 0.0) {
                OfflineStoreHelper offlineStoreHelper = OfflineStoreHelper.getInstance(this);
                offlineStoreHelper.getRatingFromApp("Q2", Integer.parseInt(String.valueOf(Math.round(ratingBar2.getRating()))));

                Intent nextPage = new Intent(FeedBack2.this, FeedBack3.class);
                startActivity(nextPage);
            } else {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
