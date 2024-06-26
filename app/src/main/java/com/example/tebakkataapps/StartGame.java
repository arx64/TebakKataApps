package com.example.tebakkataapps;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StartGame extends AppCompatActivity {

    private TextView timeRemaining;
    private TextView textView;
    Button btnBenar, btnPas;
    private int tebakanBenar;
    private int tebakanSalah;
    private int currentIndex;
    private List<String> kataList;
    private List<String> remainingWords;
    private static final long START_TIME_IN_MILLIS = 300000; // 5 minutes in milliseconds

    @Override
    public void onCreate(Bundle savedInstanceState) {
        tebakanBenar = 0;
        tebakanSalah = 5; // Initial value, will be updated later
        currentIndex = 0; // Initialize current index

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_startgame);

        btnBenar = findViewById(R.id.rightGuess);
        btnPas = findViewById(R.id.passGuess);
        timeRemaining = findViewById(R.id.timeRemaining);
        textView = findViewById(R.id.textViewTebakan);

        Intent intent = getIntent();

        // Ambil data dari Intent dan simpan dalam ArrayList
        String kata1Text = intent.getStringExtra("kata1");
        String kata2Text = intent.getStringExtra("kata2");
        String kata3Text = intent.getStringExtra("kata3");
        String kata4Text = intent.getStringExtra("kata4");
        String kata5Text = intent.getStringExtra("kata5");

        kataList = new ArrayList<>(Arrays.asList(kata1Text, kata2Text, kata3Text, kata4Text, kata5Text));
        remainingWords = new ArrayList<>(kataList);

        // Tampilkan kata pertama
        if (!remainingWords.isEmpty()) {
            textView.setText(remainingWords.get(currentIndex));
        }

        btnBenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!remainingWords.isEmpty()) {
                    remainingWords.remove(currentIndex);
                    tebakanBenar++;
                    if (currentIndex >= remainingWords.size()) {
                        currentIndex = 0; // Reset to the start of the list
                    }
                    updateTebakan();
                }
            }
        });

        btnPas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!remainingWords.isEmpty()) {
                    currentIndex++;
                    if (currentIndex >= remainingWords.size()) {
                        currentIndex = 0; // Reset to the start of the list
                    }
                    updateTebakan();
                }
            }
        });

        // Start the countdown timer
        startTimer();
    }

    private void updateTebakan() {
        tebakanSalah = 5 - tebakanBenar; // Update tebakanSalah setiap kali tebakanBenar berubah
        if (!remainingWords.isEmpty()) {
            textView.setText(remainingWords.get(currentIndex));
        } else {
            textView.setText("Selesai!");
            Intent scoreGame = new Intent(StartGame.this, scoreGame.class);
            scoreGame.putExtra("TebakanBenar", tebakanBenar);
            scoreGame.putExtra("TebakanSalah", tebakanSalah);
            // Cetak nilai tebakanBenar untuk memverifikasi hasilnya
            Log.d("MainActivity", "Nilai tebakanBenar: " + tebakanBenar);
            Log.d("MainActivity", "Nilai tebakanSalah: " + tebakanSalah);
            startActivity(scoreGame);
        }
        Log.d("MainActivity", "Nilai tebakanBenar: " + tebakanBenar);
        Log.d("MainActivity", "Nilai tebakanSalah: " + tebakanSalah);
    }

    private void startTimer() {
        new CountDownTimer(START_TIME_IN_MILLIS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
                timeRemaining.setText(timeLeftFormatted);
            }

            @Override
            public void onFinish() {
                timeRemaining.setText("00:00");
                Intent scoreGame = new Intent(StartGame.this, scoreGame.class);
                scoreGame.putExtra("TebakanBenar", tebakanBenar);
                scoreGame.putExtra("TebakanSalah", tebakanSalah);
                // Cetak nilai tebakanBenar untuk memverifikasi hasilnya
                Log.d("MainActivity", "Nilai tebakanBenar: " + tebakanBenar);
                Log.d("MainActivity", "Nilai tebakanSalah: " + tebakanSalah);
                startActivity(scoreGame);
            }
        }.start();
    }
}
