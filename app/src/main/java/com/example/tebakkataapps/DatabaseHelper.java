package com.example.tebakkataapps;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 2;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS personHistory ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, "
                + "datePlay TEXT NOT NULL, "
                + "scoreBenar INTEGER, "
                + "scoreSalah INTEGER, "
                + "scoreTotal INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE personHistory ADD COLUMN scoreBenar INTEGER;");
            db.execSQL("ALTER TABLE personHistory ADD COLUMN scoreSalah INTEGER;");
            db.execSQL("ALTER TABLE personHistory ADD COLUMN scoreTotal INTEGER;");
        }
    }

    // Metode untuk memperbarui skor
    public void updateScores(String name, String datePlay, int scoreBenar, int scoreSalah, int scoreTotal) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("scoreBenar", scoreBenar);
        values.put("scoreSalah", scoreSalah);
        values.put("scoreTotal", scoreTotal);
        db.update("personHistory", values, "name = ? AND datePlay = ?", new String[]{name, datePlay});
    }
}
