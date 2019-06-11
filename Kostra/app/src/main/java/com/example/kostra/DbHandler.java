package com.example.kostra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "meranie";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_DIAGNOSE = "diagnose";
    private static final String KEY_NOTE = "note";
    String KEY_IMAGE = "image";

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LASTNAME + " TEXT," + KEY_FIRSTNAME + " TEXT,"
                + KEY_DIAGNOSE + " TEXT,"
                + KEY_NOTE + " TEXT" + KEY_IMAGE + "TEXT" + ")";
        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE TABLE_Users");

        // Create tables again
        // onCreate(db);
    }


    void insertUserDetails(int id, String lastname, String firstname, String diagnose, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_ID, id);
        cValues.put(KEY_FIRSTNAME, firstname);
        cValues.put(KEY_LASTNAME, lastname);
        cValues.put(KEY_DIAGNOSE, diagnose);
        cValues.put(KEY_NOTE, note);
        long newRowId = db.insert(TABLE_Users, null, cValues);
        db.close();
    }

    public ArrayList<HashMap<String, String>> GetUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id,firstname,lastname,diagnose,note  FROM " + TABLE_Users;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> user = new HashMap<>();
            user.put("id", cursor.getString(cursor.getColumnIndex(KEY_ID)));
            user.put("firstname", cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME)));
            user.put("lastname", cursor.getString(cursor.getColumnIndex(KEY_LASTNAME)));
            user.put("diagnose", cursor.getString(cursor.getColumnIndex(KEY_DIAGNOSE)));
            user.put("note", cursor.getString(cursor.getColumnIndex(KEY_NOTE)));


            userList.add(user);
        }
        return userList;
    }


    public void addEntry(Bitmap image) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_IMAGE, image.getNinePatchChunk());
        database.insert(TABLE_Users, null, cv);
    }


}
