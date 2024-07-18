package com.example.tebakkataapps;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class History extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ListView listView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView = findViewById(R.id.listViewHistory);
        dbHelper = new DatabaseHelper(this);

        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                "_id",
                "name",
                "datePlay",
                "scoreBenar",
                "scoreSalah",
                "scoreTotal"
        };

        Cursor cursor = db.query(
                "personHistory",
                projection,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<DataModel> dataModels = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String datePlay = cursor.getString(cursor.getColumnIndexOrThrow("datePlay"));
            int scoreBenar = cursor.getInt(cursor.getColumnIndexOrThrow("scoreBenar"));
            int scoreSalah = cursor.getInt(cursor.getColumnIndexOrThrow("scoreSalah"));
            int scoreTotal = cursor.getInt(cursor.getColumnIndexOrThrow("scoreTotal"));

            dataModels.add(new DataModel(name, datePlay, scoreBenar, scoreSalah, scoreTotal));
        }

        adapter = new CustomAdapter(this, dataModels);
        listView.setAdapter(adapter);

        cursor.close();
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
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
    }
}
