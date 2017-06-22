package com.example.admin.feedback;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class FeedbackPage extends AppCompatActivity {

    RatingBar ratingBar1;
    TextView textView, textView2;
    static OfflineStoreHelper offlineStoreHelper;
    static int qCount = 1;
    static SparseArray<String> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_page);
        ratingBar1 = (RatingBar) findViewById(R.id.ratingBar);
        textView = (TextView) findViewById(R.id.question);
        textView2 = (TextView) findViewById(R.id.qNo);

        offlineStoreHelper = OfflineStoreHelper.getInstance(this);
        updateQuestion();
        updateView();
    }

    void updateView() {
        textView.setText(questions.get(qCount));
        textView2.setText("Q" + qCount + "/" + questions.size() + ":");
    }

    static void updateList(Cursor cursor) {
        questions = new SparseArray<>();
        int columnCount = cursor.getColumnCount();
        while (cursor.moveToNext()) {
            for (int i = 1; i < columnCount; i++)
                questions.put(Integer.parseInt(cursor.getString(0)), cursor.getString(1));
        }
    }

    private static void updateQuestion() {
        if (qCount == 1)
            updateList(offlineStoreHelper.getQuestions());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (qCount != 1)
            qCount--;
    }

    public void nextQuestion(View view) {
        if (ratingBar1.getRating() != 0.0) {
            offlineStoreHelper.getRatingFromApp("Q" + qCount, Integer.parseInt(String.valueOf(Math.round(ratingBar1.getRating()))));
            qCount++;
            Intent nextPage;
            if (qCount == 16) {
                nextPage = new Intent(FeedbackPage.this, Remark.class);
            } else {
                nextPage = new Intent(FeedbackPage.this, FeedbackPage.class);
            }
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
    }
}
