package com.example.kostra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class DbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 15;
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
        // db.execSQL("DROP TABLE UserInformation");

        db.execSQL(
                "create table " + TABLE_Users + "(id INTEGER PRIMARY KEY, firstname TEXT, lastname TEXT, diagnose TEXT, note TEXT,img BLOB) "
        );


        db.execSQL(
                "create table " + TABLE_History + "(id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, date2 TEXT, volume INTEGER, speed INTEGER ) "
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);


        db.execSQL(
                "create table " + TABLE_Users + "(id INTEGER PRIMARY KEY, firstname TEXT, lastname TEXT, diagnose TEXT, note TEXT,img BLOB) "
        );


        db.execSQL("DROP TABLE History");
        db.execSQL(
                "create table " + TABLE_History + "(id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, date2 TEXT, volume INTEGER, speed INTEGER ) "
        );
        //  onCreate(db);
    }


    void insertUserDetails(int id, String firstname, String lastname, String diagnose, String note) {
        SQLiteDatabase db = this.getWritableDatabase();


        //String sql = "INSERT INTO UserInformation (firstname,lastname, diagnose,note) VALUES ( ?, ?,?,?) WHERE id LIKE 1";

        String sql = "UPDATE UserInformation SET firstname = ? , "
                + "lastname = ?, "
                + "diagnose = ?, "
                + "note = ? "
                + "WHERE id = ?";
        SQLiteStatement statement = db.compileStatement(sql);

        statement.bindString(1, firstname);

        statement.bindString(2, lastname);
        statement.bindString(3, diagnose);
        statement.bindString(4, note);
        statement.bindLong(5, id);
        long rowId = statement.executeUpdateDelete();


        db.close();
    }


    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Integer> listID = this.GetIDList();
        int count = listID.size();

///        int pom = listID.get(listID.size() - 1);

//        if (count > 1) {

        db.execSQL("DELETE FROM UserInformation WHERE id >1");
        //      }

        db.close();

    }


//    public long getRecordsCount() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        long count = DatabaseUtils.queryNumEntries(db, "UserInformation");
//        db.close();
//        return count;
//    }


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

        String query = "SELECT  firstname  FROM  UserInformation where id like 1";
        Cursor cursor = db.rawQuery(query, null);
        String name = "";

        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("firstname"));

        }
        return name;
    }


    public String GetUserLastname() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT lastname  FROM  UserInformation where id like 1";
        Cursor cursor = db.rawQuery(query, null);
        String name = "";

        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("lastname"));

        }
        return name;
    }

    public String GetUserDiagnose() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT diagnose  FROM  UserInformation where id like 1";
        Cursor cursor = db.rawQuery(query, null);
        String name = "";

        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("diagnose"));

        }
        return name;
    }


    public String GetUserNote() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT note  FROM  UserInformation where id like 1";
        Cursor cursor = db.rawQuery(query, null);
        String name = "";

        while (cursor.moveToNext()) {
            name = cursor.getString(cursor.getColumnIndex("note"));

        }
        return name;
    }


    public void insertBitmap(Bitmap bm) {


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 80, out);
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
        SQLiteDatabase db = this.getReadableDatabase();
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


    void insertToHistory(String date1, String date2, int volume, int speed) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("date", date1);
        contentValues.put("date2", date2);
        contentValues.put("volume", volume);
        contentValues.put("speed", speed);


        long result = db.insert(TABLE_History, null, contentValues);
        db.close();
    }


    public List GetDate() {

        List<String> list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT   date  FROM History ";
        Cursor cursor = db.rawQuery(query, null);
        String j = " ";

        while (cursor.moveToNext()) {

            j = cursor.getString(cursor.getColumnIndex("date"));

            list.add(j);
        }

        return list;

    }


    public List GetTime() {

        List<String> list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT  date2 FROM History ";
        Cursor cursor = db.rawQuery(query, null);
        String j = " ";
        while (cursor.moveToNext()) {

            j = cursor.getString(cursor.getColumnIndex("date2"));
            list.add(j);
        }
        return list;
    }


    public List GetVolume() {

        List<Integer> list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT  volume FROM History ";
        Cursor cursor = db.rawQuery(query, null);
        int j = 0;
        while (cursor.moveToNext()) {

            j = (int) cursor.getLong(cursor.getColumnIndex("volume"));
            list.add(j);
        }
        return list;
    }


    public List GetSpeed() {

        List<Integer> list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT  speed FROM History ";
        Cursor cursor = db.rawQuery(query, null);
        int j = 0;
        while (cursor.moveToNext()) {

            j = (int) cursor.getLong(cursor.getColumnIndex("speed"));
            list.add(j);
        }
        return list;
    }

    public void deleteAllMeasurements() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from History WHERE id =1");
        db.close();
    }


    public List GetIDList() {

        List<Integer> list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT  id  FROM UserInformation ";
        Cursor cursor = db.rawQuery(query, null);
        int j = 0;
        while (cursor.moveToNext()) {

            j = (int) cursor.getLong(cursor.getColumnIndex("id"));
            list.add(j);
        }
        return list;
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from  UserInformation where id='" + id + "'");
        db.close();
    }

    public int GetSpeed(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT  speed FROM History WHERE id =" + id;
        Cursor cursor = db.rawQuery(query, null);
        int j = 0;
        while (cursor.moveToNext()) {

            j = (int) cursor.getLong(cursor.getColumnIndex("speed"));

        }
        db.close();
        return j;

    }


    public int GetVolume(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT volume FROM History WHERE id =" + id;
        Cursor cursor = db.rawQuery(query, null);
        int j = 0;
        while (cursor.moveToNext()) {

            j = (int) cursor.getLong(cursor.getColumnIndex("volume"));

        }

        db.close();
        return j;
    }


    public String GetDate(int id) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT   date  FROM History WHERE id =" + id;
        Cursor cursor = db.rawQuery(query, null);
        String j = " ";

        while (cursor.moveToNext()) {

            j = cursor.getString(cursor.getColumnIndex("date"));


        }

        return j;

    }


}









