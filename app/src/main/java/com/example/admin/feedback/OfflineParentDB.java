//package com.example.admin.feedback;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class OfflineParentDB extends SQLiteOpenHelper {
//    private static final String DATABASE_NAME = "LocalDB.db";
//    private static final int DATABASE_VERSION = 1;
//    public static final String TABLE_NAME = "parentDetails";
//    public static final String DETAILS_BRANCH = "Branch";
//    public static final String DETAILS_YEAR = "Year";
//    public static final String DETAILS_SECTION = "Section";
//    public static final String DETAILS_NAME = "Name";
//    public static final String DETAILS_NUMBER = "Number";
//    public static final String DETAILS_OCCUPATION = "Occupation";
//    public static final String DETAILS_REMARK = "Remark";
//
//    public OfflineParentDB(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT," + DETAILS_BRANCH +
//                " VARCHAR(3)," + DETAILS_YEAR + " VARCHAR(3)," + DETAILS_SECTION + " VARCHAR(1)," +
//                DETAILS_NAME + " TEXT," + DETAILS_NUMBER + " BIGINT, " + DETAILS_OCCUPATION + " TEXT, " + DETAILS_REMARK + " TEXT)");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }
//
//    public boolean insertData(String branch, String year, String section, String name,
//                              String number, String occupation) {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(DETAILS_BRANCH, branch);
//        contentValues.put(DETAILS_YEAR, year);
//        contentValues.put(DETAILS_SECTION, section);
//        contentValues.put(DETAILS_NAME, name);
//        contentValues.put(DETAILS_NUMBER, number);
//        contentValues.put(DETAILS_OCCUPATION, occupation);
//        db.insert(TABLE_NAME, null, contentValues);
//        return true;
//    }
//
//    public String getId(String branch, String year, String section, String name,
//                        String number, String occupation) {
//        Cursor res;
//        try {
//            SQLiteDatabase db = this.getReadableDatabase();
//            res = db.rawQuery("SELECT _id FROM " + TABLE_NAME + " WHERE " + DETAILS_BRANCH +
//                    " = \'" + branch + "\' AND " + DETAILS_YEAR + " = \'" + year + "\' AND " + DETAILS_SECTION + " = \'" + section + "\' AND " +
//                    DETAILS_NAME + " = \'" + name + "\' AND " + DETAILS_NUMBER + " = \'" + number + "\' AND " + DETAILS_OCCUPATION + " = \'" + occupation + "\'", null);
//            if (res.getColumnCount() == 1){
//                res.moveToNext();
//                return res.getString(0);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "No id";
//    }
//
//    public Cursor getAllData() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
//    }
//}
