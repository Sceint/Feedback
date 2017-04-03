package com.example.admin.feedback;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.spec.ECField;
import java.util.Map;
import java.util.TreeMap;

public class OfflineStoreHelper extends SQLiteOpenHelper {

    static OfflineStoreHelper offlineStoreHelper;

    private static final String DATABASE_NAME = "LocalDB.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME1 = "parentDetails";
    public static final String TABLE_NAME2 = "ratingDetails";
    public static final String DETAILS_BRANCH = "Branch";
    public static final String DETAILS_YEAR = "Year";
    public static final String DETAILS_SECTION = "Section";
    public static final String DETAILS_NAME = "Name";
    public static final String DETAILS_NUMBER = "Number";
    public static final String DETAILS_OCCUPATION = "Occupation";
    public static final String DETAILS_REMARK = "Remark";

    private Map<String, Integer> rating;
    String id;

    public OfflineStoreHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static OfflineStoreHelper getInstance(Context context){
        if(offlineStoreHelper == null)
            offlineStoreHelper = new OfflineStoreHelper(context);
        return offlineStoreHelper;
    }

    public static void clear(){
        offlineStoreHelper = null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME1 + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + DETAILS_BRANCH +
                " VARCHAR(3)," + DETAILS_YEAR + " VARCHAR(3)," + DETAILS_SECTION + " VARCHAR(1)," +
                DETAILS_NAME + " TEXT," + DETAILS_NUMBER + " BIGINT, " + DETAILS_OCCUPATION + " TEXT, " + DETAILS_REMARK + " TEXT)");
        db.execSQL("CREATE TABLE " + TABLE_NAME2 + "(_id INTEGER PRIMARY KEY, Q1 INTEGER, Q2 INTEGER, Q3 INTEGER, Q4 INTEGER, Q5 INTEGER, Q6 INTEGER," +
                " Q7 INTEGER, Q8 INTEGER, Q9 INTEGER, Q10 INTEGER, Q11 INTEGER, Q12 INTEGER, Q13 INTEGER, Q14 INTEGER, Q15 INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    public boolean insertParentData(String branch, String year, String section, String name,
                              String number, String occupation) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DETAILS_BRANCH, branch);
        contentValues.put(DETAILS_YEAR, year);
        contentValues.put(DETAILS_SECTION, section);
        contentValues.put(DETAILS_NAME, name);
        contentValues.put(DETAILS_NUMBER, number);
        contentValues.put(DETAILS_OCCUPATION, occupation);
        db.insert(TABLE_NAME1, null, contentValues);
        id = getId(branch, year, section, name, number, occupation);
        return true;
    }

    public String getId(String branch, String year, String section, String name,
                        String number, String occupation) {
        Cursor res;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            res = db.rawQuery("SELECT _id FROM " + TABLE_NAME1 + " WHERE " + DETAILS_BRANCH +
                    " = \'" + branch + "\' AND " + DETAILS_YEAR + " = \'" + year + "\' AND " + DETAILS_SECTION + " = \'" + section + "\' AND " +
                    DETAILS_NAME + " = \'" + name + "\' AND " + DETAILS_NUMBER + " = \'" + number + "\' AND " + DETAILS_OCCUPATION + " = \'" + occupation + "\'", null);
            if (res.getColumnCount() == 1){
                res.moveToNext();
                return res.getString(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "No id";
    }

    public Cursor getAllParentData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME1, null);
    }

    void getRatingFromApp(String q, Integer r){
        if(rating == null)
            rating = new TreeMap<>();
        rating.put(q, r);
    }

    public boolean insertRatingData() {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("_id", id);
        for(Map.Entry<String, Integer> entry : rating.entrySet())
            contentValues.put(entry.getKey(), entry.getValue());
        db.insert(TABLE_NAME2, null, contentValues);
        return true;
    }

    public Cursor getAllRatingData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME2, null);
    }

    public boolean insertRemark(String remark){
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("Remark", remark);
            db.update(TABLE_NAME1, contentValues, "_id = ?", new String[]{id});
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
