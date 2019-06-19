package com.example.kostra;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import java.io.ByteArrayOutputStream;


public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 10;
    private static final String DB_NAME = "meranie";
    private static final String TABLE_Users = "UserInformation";
    private static final String TABLE_History = "History";


//    private static final String KEY_ID = "id";
//    private static final String KEY_FIRSTNAME = "firstname";
//    private static final String KEY_LASTNAME = "lastname";
//    private static final String KEY_DIAGNOSE = "diagnose";
//    private static final String KEY_NOTE = "note";
//
//    private static final String KEY_IMAGE = "img";
    ///  public static final String CREATE_TABLE2 = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+ "(id INTEGER PRIMARY KEY AUTOINCREMENT, img BLOB NOT NULL, description TEXT NULL)";


    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(
                "create table " + TABLE_Users + "(id INTEGER PRIMARY KEY, firstname TEXT, lastname TEXT, diagnose TEXT, note TEXT,img BLOB) "
        );


        db.execSQL(
                "create table " + TABLE_History + "(id INTEGER PRIMARY KEY, date TEXT, date2 TEXT, volume INTEGER, speed INTEGER ) "
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE UserInformation");

        onCreate(db);
    }


    void insertUserDetails(int id, String firstname, String lastname, String diagnose, String note) {
        SQLiteDatabase db = this.getWritableDatabase();


//        ContentValues cValues = new ContentValues();
//        cValues.put("id", id);
//        cValues.put("firstname", firstname);
//        cValues.put("lastname", lastname);
//        cValues.put("diagnose", diagnose);
//        cValues.put("note", note);
//        db.insert(TABLE_Users, null, cValues);

        String sql = "INSERT INTO UserInformation (id,firstname,lastname, diagnose,note) VALUES (?, ?, ?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindLong(1, id);
        statement.bindString(2, firstname);

        statement.bindString(3, lastname);
        statement.bindString(4, diagnose);
        statement.bindString(5, note);
        long rowId = statement.executeInsert();
        db.close();
    }


    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from UserInformation WHERE id >1");
        db.close();
    }


    public int GetUserID() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  id  FROM  UserInformation";
        Cursor cursor = db.rawQuery(query, null);
        int name = 0;

        while (cursor.moveToNext()) {
            name = (int) cursor.getLong(cursor.getColumnIndex("id"));

        }
        return name;
    }


    public String GetUserName() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT  firstname  FROM  UserInformation";
        Cursor cursor = db.rawQuery(query, null);
        String name = "";

        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("firstname"));

        }
        return name;
    }


    public String GetUserLastname() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT lastname  FROM  UserInformation";
        Cursor cursor = db.rawQuery(query, null);
        String name = "";

        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("lastname"));

        }
        return name;
    }

    public String GetUserDiagnose() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT diagnose  FROM  UserInformation";
        Cursor cursor = db.rawQuery(query, null);
        String name = "";

        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("diagnose"));

        }
        return name;
    }


    public String GetUserNote() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT note  FROM  UserInformation";
        Cursor cursor = db.rawQuery(query, null);
        String name = "";

        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("note"));

        }
        return name;
    }


    public void insertBitmap(Bitmap bm) {


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, out);
        byte[] buffer = out.toByteArray();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();

        try {

            String sql = "INSERT INTO UserInformation (img) VALUES(?)";
            SQLiteStatement insertStmt = db.compileStatement(sql);
            insertStmt.clearBindings();
            insertStmt.bindBlob(1, buffer);
            insertStmt.executeInsert();
            db.execSQL(sql);

            Log.i("Insert", "text");
            db.setTransactionSuccessful();

        } catch (SQLiteException e) {
            e.printStackTrace();

        } finally {
            db.endTransaction();
            db.close();
        }
    }

    public Bitmap getBitmap() {
        SQLiteDatabase db = this.getWritableDatabase();
        String qu = "select img  from UserInformation";

        Cursor cur = db.rawQuery(qu, null);

        if (cur.moveToFirst()) {
            byte[] imgByte = cur.getBlob(0);
            cur.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();
        }
        db.close();
        return null;
    }


    public void inserToHistory(int id, String date, String date2, int volume, int speed) {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "INSERT INTO  History  (id,date,date2, volume, speed) VALUES (?, ?, ?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindLong(1, id);
        statement.bindString(2, date);
        statement.bindString(3, date2);
        statement.bindLong(4, volume);
        statement.bindLong(5, speed);
        long rowId = statement.executeInsert();
        db.close();


    }

}









