package com.example.mentalmath;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Роман on 18.08.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "results.db";
    private static final int SHEMA = 1;
    public static final String TABLE = "results";
    public static final String TYPE = "type";
    public static final String DATE = "date";
    public static final String TIME = "time";
    private static DataBaseHelper instance = null;

    public static DataBaseHelper getInstance(Context ctxt) {
        if (instance == null) {
            instance = new DataBaseHelper(ctxt.getApplicationContext());
        }
        return instance;
    }

    private DataBaseHelper(Context ctxt) {
        super(ctxt, DATABASE_NAME, null, SHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE results(type TEXT, date DATE, time REAL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new RuntimeException("unexpected callback");
    }
}
