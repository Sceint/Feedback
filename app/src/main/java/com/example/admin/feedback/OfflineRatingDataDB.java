//package com.example.admin.feedback;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.Map;
//import java.util.TreeMap;
//
//public class OfflineRatingDataDB extends SQLiteOpenHelper {
//
//    static OfflineRatingDataDB offlineRatingDataDB;
//
//    private static final String DATABASE_NAME = "LocalDB.db";
//    private static final int DATABASE_VERSION = 1;
//    public static final String TABLE_NAME = "ratingDetails";
//
//    private Map<String, Integer> rating;
//
//    OfflineRatingDataDB(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    public static OfflineRatingDataDB getInstance(Context context){
//        if(offlineRatingDataDB == null)
//            offlineRatingDataDB = new OfflineRatingDataDB(context);
//        return offlineRatingDataDB;
//    }
//
//    public static void clear(){
//        offlineRatingDataDB = null;
//    }
//
//    void getRatingFromApp(String q, Integer r){
//        if(rating == null)
//            rating = new TreeMap<>();
//        rating.put(q, r);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        try {
//            db.execSQL("CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY, Q1 INTEGER, Q2 INTEGER, Q3 INTEGER, Q4 INTEGER, Q5 INTEGER, Q6 INTEGER," +
//                    " Q7 INTEGER, Q8 INTEGER, Q9 INTEGER, Q10 INTEGER, Q11 INTEGER, Q12 INTEGER, Q13 INTEGER, Q14 INTEGER, Q15 INTEGER)");
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }
//
//    public boolean insertData(String id) {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("_id", id);
//        for(Map.Entry<String, Integer> entry : rating.entrySet())
//            contentValues.put(entry.getKey(), entry.getValue());
//        db.insert(TABLE_NAME, null, contentValues);
//        return true;
//    }
//
//    public Cursor getAllData() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//    }
//}
