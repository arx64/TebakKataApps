package com.example.tebakkataapps;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ScoreGame extends AppCompatActivity {

    private static final String TAG = "ScoreGame";
    DatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scoreresult);

        // Inisialisasi dbHelper
        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();

        String namaUser = intent.getStringExtra("nama");
        String tanggalUser = intent.getStringExtra("tanggal");
        int scoreBenar = intent.getIntExtra("TebakanBenar", 0);
        int scoreSalah = intent.getIntExtra("TebakanSalah", 0);
        int scoreTotal = scoreBenar * 20;

        Log.d(TAG, "namaUser: " + namaUser);
        Log.d(TAG, "tanggalUser: " + tanggalUser);
        Log.d(TAG, "scoreBenar: " + scoreBenar);
        Log.d(TAG, "scoreSalah: " + scoreSalah);
        Log.d(TAG, "scoreTotal: " + scoreTotal);

        // Memasukkan data skor ke database
        dbHelper.updateScores(namaUser, tanggalUser, scoreBenar, scoreSalah, scoreTotal);

//        TextView textTitle = findViewById(R.id.textViewTitle);
        TextView textScoreBenar = findViewById(R.id.textViewCorrectScore);
        TextView textScoreSalah = findViewById(R.id.textViewIncorrectScore);
        TextView textScore = findViewById(R.id.textViewScore);
        Button buttonMenu = findViewById(R.id.buttonMenu);

//        if (textTitle == null) {
//            Log.e(TAG, "textTitle is null");
//        } else {
//            textTitle.setText(namaUser);
//        }

        textScoreBenar.setText(String.valueOf(scoreBenar));
        textScoreSalah.setText(String.valueOf(scoreSalah));
        textScore.setText(String.valueOf(scoreTotal));

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Main = new Intent(ScoreGame.this, MainActivity.class);
                Main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(Main);
            }
        });
    }
}
