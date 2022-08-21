package com.project.praktik5;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE = "db_names";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "names";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "name";
    private static final String CREATE_TABLE_NAMES = "CREATE TABLE " + TABLE_NAME
            + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_FIRSTNAME + " TEXT);";
    public static final String TAG = DatabaseHelper.class.getName();

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
        Log.d(TAG, "DatabaseHelper: "+ CREATE_TABLE_NAMES);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_NAMES);
        Log.d(TAG, "onCreate: SUCCESS");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long AddName(String name){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FIRSTNAME, name);
        return db.insert(TABLE_NAME, null, contentValues);
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllNama(){
        ArrayList<String> namaArrayList = new ArrayList<String>();
        String name="";
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if(c.moveToFirst()){
            do{
                name = c.getString(c.getColumnIndex(KEY_FIRSTNAME));
                namaArrayList.add(name);
            } while (c.moveToNext());
            Log.d("array", namaArrayList.toString());
        }
        return namaArrayList;
    }
}
