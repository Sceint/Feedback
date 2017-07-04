package com.example.admin.feedback;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RatingDisplay extends AppCompatActivity implements AdapterView.OnItemClickListener, WaitForResponse {

    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private GraphView graphView;
    private ProgressDialog progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_display);
        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        graphView = (GraphView) findViewById(R.id.graph);

        addDrawerItems();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Generating Graph...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(true);
        progressBar.show();

        displayGraph();
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

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    void displayGraph() {
        Intent intent = getIntent();
        String value = intent.getStringExtra("value");
        if (value.equals("Averages")) {
            if (isOnline()) onlineGraph("getAverage.php", "average");
            else offlineGraph("average");
        } else {
            if (isOnline()) onlineGraph("getRating.php", value);
            else offlineGraph(value);
        }
    }

    void onlineGraph(String url, String data) {
        new GetFromOnlineDBHelper(url, data, this).execute();
    }

    @Override
    public void processFinish(List<String> ratings, String data) {
        if (ratings == null || ratings.size() == 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Error").setMessage("Internet Issue or No data to Generate Graph")
                    .setCancelable(false)
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    }).create().show();
        } else {
            int count = 5;
            DataPoint[] dataPoints;

            if (data.equals("average")) {
                dataPoints = new DataPoint[15];
                for (int i = 0; i < 15; i++)
                    dataPoints[i] = new DataPoint(i + 1, Double.valueOf(ratings.get(i)));
            } else {
                dataPoints = new DataPoint[5];
                List<Integer> l = new ArrayList<>();
                for (String s : ratings) {
                    String[] arr = s.split("-");
                    dataPoints[Integer.parseInt(arr[0]) - 1] =
                            new DataPoint(Integer.parseInt(arr[0]), Double.valueOf(arr[1]));
                    l.add(Integer.parseInt(arr[0]) - 1);
                }
                for (int i = 0; i < 5; i++)
                    if (!l.contains(i))
                        dataPoints[i] = new DataPoint(i + 1, 0);
            }
            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);
            series.setSpacing(30);
            series.setAnimated(true);
            series.setDrawValuesOnTop(true);
            series.setValuesOnTopColor(Color.rgb(0, 0, 0));

            graphView.addSeries(series);
            graphView.getViewport().setXAxisBoundsManual(true);
            graphView.getViewport().setMinX(0);
            graphView.getViewport().setMaxX(dataPoints.length + 1);
            graphView.getViewport().setYAxisBoundsManual(true);
            graphView.getViewport().setMinY(0);
            graphView.setTitleTextSize(45);
            if (data.equals("average")) {
                graphView.setTitle("Online Data Graph");
                graphView.getViewport().setMaxY(5);
            } else {
                graphView.setTitle(QuestionGraph.getQuestion());
                graphView.getViewport().setMaxY(count);
            }
            graphView.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        }
        progressBar.hide();
    }

    void offlineGraph(String data) {
        try {
            int count = 0;
            boolean flag = false;
            DataPoint[] dataPoints;
            OfflineStoreHelper offlineStoreHelper = OfflineStoreHelper.getInstance(this);
            if (data.equals("average")) {
                dataPoints = new DataPoint[15];
                JSONArray outer = offlineStoreHelper.getAverages();
                if (outer.length() == 0)
                    flag = true;
                JSONObject inner = outer.getJSONObject(0);
                for (int i = 0; i < 15; i++)
                    dataPoints[i] = new DataPoint(i + 1, inner.getDouble("AVG(Q" + (i + 1) + ")"));
            } else {
                dataPoints = new DataPoint[5];
                JSONArray outer = offlineStoreHelper.getRatingOfQuestion(data);
                if (outer.length() == 0) {
                    flag = true;
                }
                List<Integer> l = new ArrayList<>();
                for (int i = 0; i < outer.length(); i++) {
                    JSONObject inner = outer.getJSONObject(i);
                    l.add(inner.getInt(data) - 1);
                    count += inner.getInt("COUNT(*)");
                    dataPoints[inner.getInt(data) - 1] = new DataPoint(inner.getInt(data), inner.getInt("COUNT(*)"));
                }
                for (int i = 0; i < 5; i++)
                    if (!l.contains(i))
                        dataPoints[i] = new DataPoint(i + 1, 0);
            }
            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);
            series.setSpacing(30);
            series.setAnimated(true);
            series.setDrawValuesOnTop(true);
            series.setValuesOnTopColor(Color.rgb(0, 0, 0));

            graphView.addSeries(series);
            graphView.getViewport().setXAxisBoundsManual(true);
            graphView.getViewport().setMinX(0);
            graphView.getViewport().setMaxX(dataPoints.length + 1);
            graphView.getViewport().setYAxisBoundsManual(true);
            graphView.getViewport().setMinY(0);
            graphView.setTitleTextSize(45);
            if (data.equals("average")) {
                graphView.setTitle("Offline Data Graph");
                graphView.getViewport().setMaxY(5);
            } else {
                graphView.setTitle(QuestionGraph.getQuestion());
                graphView.getViewport().setMaxY(count);
            }
            graphView.getViewport().setScalable(true); // enables horizontal zooming and scrolling
            progressBar.hide();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
