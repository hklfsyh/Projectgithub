package com.example.sakabookujicoba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

class dbHelper extends SQLiteOpenHelper {

    //deklarasi database
    private Context context;
    private static final String DATABASE_NAME = "Rentenir.DB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "cashflow";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CASH = "cf_cash";
    private static final String COLUMN_DATE = "cf_date";
    private static final String COLUMN_KETE = "cf_kete";
    private static final String COLUMN_DESC = "cf_desc";


    dbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    //create / membuat table di database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,cf_cash INTEGER," + COLUMN_DATE + " TEXT," + COLUMN_KETE + " TEXT," + COLUMN_DESC + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //menambahkan data ke database
    void addCF(int cash, String date, String kete, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CASH, cash);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_KETE, kete);
        cv.put(COLUMN_DESC, desc);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Add Success", Toast.LENGTH_SHORT).show();
        }
    }

    //membaca data yang ada dalam database
    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //update data dalam database
    void updateData(String row_id, String cash, String date, String kete, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CASH, cash);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_KETE, kete);
        cv.put(COLUMN_DESC, desc);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "failed to update.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "succsess to update", Toast.LENGTH_SHORT).show();
        }

    }

    //delete data dalam database
    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Delete failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Delete Succsess", Toast.LENGTH_SHORT).show();
        }
    }

    //menjumlahkan saldo masuk
    Cursor sumMasuk(){
        String query = "SELECT SUM(cf_cash) FROM cashflow WHERE cf_desc = 'MASUK' ";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //menjumlahkan saldo keluar
    Cursor sumKeluar(){
        String query = "SELECT SUM(cf_cash) FROM cashflow WHERE cf_desc = 'KELUAR' ";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


}
