package com.example.admin.feedback;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

public class FeedBack3 extends AppCompatActivity {

    private RatingBar ratingBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back3);
        ratingBar3 = (RatingBar) findViewById(R.id.ratingBar);
    }
    public void gotoFeedback4Page(View view){
        if (ratingBar3.getRating() != 0.0) {
            OfflineStoreHelper offlineStoreHelper = OfflineStoreHelper.getInstance(this);
            offlineStoreHelper.getRatingFromApp("Q3", Integer.parseInt(String.valueOf(Math.round(ratingBar3.getRating()))));

            Intent nextPage = new Intent(FeedBack3.this, FeedBack4.class);
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
