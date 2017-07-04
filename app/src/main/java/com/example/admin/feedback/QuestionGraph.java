package com.example.admin.feedback;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class QuestionGraph extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private Spinner spinner;
    private static List<String> questions;
    private static int qNo = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_graph);
        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        spinner = (Spinner) findViewById(R.id.spinner_question);
        updateQuestions();
    }

    void updateQuestions() {
        questions = new ArrayList<>();
        questions.add("Select Question");
        OfflineStoreHelper offlineStoreHelper = OfflineStoreHelper.getInstance(this);
        Cursor cursor = offlineStoreHelper.getQuestions();
        int columnCount = cursor.getColumnCount();
        while (cursor.moveToNext()) {
            for (int i = 1; i < columnCount; i++)
                questions.add(cursor.getString(1));
        }
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, questions);
        spinner.setAdapter(adapter);
    }

    private void addDrawerItems() {
        String[] osArray = {"Take Feedback", "Upload Data", "Graphs", "Question Graph", "Update Question"};
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent nextPage;
        if (position == 0) {
            nextPage = new Intent(this, MainActivity.class);
        } else if (position == 1) {
            nextPage = new Intent(this, UploadData.class);
        } else if (position == 2) {
            nextPage = new Intent(this, RatingDisplay.class);
            nextPage.putExtra("value", "Averages");
        } else if (position == 3) {
            nextPage = new Intent(this, QuestionGraph.class);
        } else {
            nextPage = new Intent(this, UpdateQuestion.class);
        }
        nextPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(nextPage);
        finish();
    }

    public void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Select");
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Activate the navigation drawer toggle
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    public static String getQuestion() {
        return questions.get(qNo);
    }

    public void generateGraph(View v) {
        Intent intent = new Intent(QuestionGraph.this, RatingDisplay.class);
        qNo = spinner.getSelectedItemPosition();
        if (qNo == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Select Question");
            builder.setMessage("Please Select a Question from the Drop Down")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            builder.create().show();
        } else {
            intent.putExtra("value", "Q" + qNo);
            startActivity(intent);
        }
    }
}
