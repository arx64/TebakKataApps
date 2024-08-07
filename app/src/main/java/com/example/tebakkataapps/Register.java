package com.example.tebakkataapps;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Register extends AppCompatActivity {
    Button btnLanjut;
    EditText nama, tanggal;
    String namaText, tanggalText;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        btnLanjut = findViewById(R.id.btnLanjut);
        nama = findViewById(R.id.etNama);
        tanggal = findViewById(R.id.etTanggal);

        dbHelper = new DatabaseHelper(this);

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    namaText = nama.getText().toString();
                    tanggalText = tanggal.getText().toString();

                    // Insert data into the database
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("name", namaText);
                    values.put("datePlay", tanggalText);
                    db.insert("personHistory", null, values);

                    Intent AyoMain = new Intent(Register.this, AyoMain.class);
                    AyoMain.putExtra("nama", namaText);
                    AyoMain.putExtra("tanggal", tanggalText);
                    Log.d("[DEBUG]", namaText);
                    Log.d("[DEBUG]", tanggalText);
                    startActivity(AyoMain);
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean validateInputs() {
        boolean isValid = true;

        if (TextUtils.isEmpty(nama.getText().toString().trim())) {
            nama.setError("Nama harus diisi");
            isValid = false;
        }

        if (TextUtils.isEmpty(tanggal.getText().toString().trim())) {
            tanggal.setError("Tanggal harus diisi");
            isValid = false;
        }

        return isValid;
    }

}
