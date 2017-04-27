package com.example.admin.feedback;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Map;
import java.util.TreeMap;

public class OfflineStoreHelper extends SQLiteOpenHelper {

    static OfflineStoreHelper offlineStoreHelper;

    private static final String DATABASE_NAME = "LocalDB.db";
    private static final int DATABASE_VERSION = 5;
    private static final String TABLE_NAME1 = "parentDetails";
    private static final String TABLE_NAME2 = "ratingDetails";
    private static final String DETAILS_BRANCH = "Branch";
    private static final String DETAILS_YEAR = "Year";
    private static final String DETAILS_SECTION = "Section";
    private static final String DETAILS_NAME = "Name";
    private static final String DETAILS_NUMBER = "Number";
    private static final String DETAILS_OCCUPATION = "Occupation";
    private static final String DETAILS_REMARK = "Remark";
    private static final String DETAILS_DEVICE_ID = "DeviceID";

    private Map<String, Integer> rating;
    String id, branch, deviceID;

    public OfflineStoreHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static OfflineStoreHelper getInstance(Context context) {
        if (offlineStoreHelper == null)
            offlineStoreHelper = new OfflineStoreHelper(context);
        return offlineStoreHelper;
    }

    static void clear() {
        offlineStoreHelper = null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME1 + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + DETAILS_BRANCH +
                " VARCHAR(3)," + DETAILS_SECTION + " VARCHAR(1)," + DETAILS_YEAR + " VARCHAR(3)," +
                DETAILS_NAME + " TEXT," + DETAILS_NUMBER + " BIGINT, " + DETAILS_OCCUPATION + " TEXT, "
                + DETAILS_REMARK + " TEXT, " + DETAILS_DEVICE_ID + " VARCHAR(25)) ");
        db.execSQL("CREATE TABLE " + TABLE_NAME2 + "(_id INTEGER, Branch VARCHAR(3), Q1 INTEGER, Q2 INTEGER, " +
                "Q3 INTEGER, Q4 INTEGER, Q5 INTEGER, Q6 INTEGER, Q7 INTEGER, Q8 INTEGER, Q9 INTEGER, " +
                "Q10 INTEGER, Q11 INTEGER, Q12 INTEGER, Q13 INTEGER, Q14 INTEGER, Q15 INTEGER, DateCreated DATETIME DEFAULT CURRENT_TIMESTAMP, "
                + DETAILS_DEVICE_ID + " VARCHAR(25))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    boolean insertParentData(String branch, String year, String section, String name,
                             String number, String occupation, String deviceID) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DETAILS_BRANCH, branch);
        contentValues.put(DETAILS_YEAR, year);
        contentValues.put(DETAILS_SECTION, section);
        contentValues.put(DETAILS_NAME, name);
        contentValues.put(DETAILS_NUMBER, number);
        contentValues.put(DETAILS_OCCUPATION, occupation);
        db.insert(TABLE_NAME1, null, contentValues);
        try {
            id = getLatestId();
            this.deviceID = id + "-" + deviceID;
            db.execSQL("UPDATE `" + TABLE_NAME1 + "` SET `DeviceID`=\'" + this.deviceID + "\' WHERE `_id`=\'" + id + "\'");
            this.branch = branch;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    private String getLatestId() {
        Cursor res;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            res = db.rawQuery("SELECT last_insert_rowid()", null);
            if (res.getColumnCount() == 1) {
                res.moveToNext();
                return res.getString(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "No id";
    }

    Cursor getAllParentData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME1, null);
    }

    void getRatingFromApp(String q, Integer r) {
        if (rating == null)
            rating = new TreeMap<>();
        rating.put(q, r);
    }

    boolean insertRatingData() {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", id);
        contentValues.put(DETAILS_DEVICE_ID, deviceID);
        contentValues.put("Branch", branch);
        for (Map.Entry<String, Integer> entry : rating.entrySet())
            contentValues.put(entry.getKey(), entry.getValue());
        db.insert(TABLE_NAME2, null, contentValues);
        return true;
    }

    Cursor getAllRatingData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME2, null);
    }

    boolean insertRemark(String remark) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Remark", remark);
            db.update(TABLE_NAME1, contentValues, "_id = ?", new String[]{id});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}
