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

    private TextView timeRemaining, textView;
    Button btnBenar, btnPas;
    private int tebakanBenar, tebakanSalah, currentIndex;
    String namabaru, tanggalbaru, nama, tanggal;
    private List<String> kataList, remainingWords;
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
        nama = intent.getStringExtra("nama");
        tanggal = intent.getStringExtra("tanggal");

        namabaru = nama;
        tanggalbaru = tanggal;
        Log.d("MainActivity", "Nama: " + nama);
        Log.d("MainActivity", "Tanggal: " + tanggal);

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

        // Deklarasikan variabel di awal metode
//        String nama = "";
//        String tanggal = "";

        if (!remainingWords.isEmpty()) {
            textView.setText(remainingWords.get(currentIndex));
        } else {
            textView.setText("Selesai!");
//            Intent intent = getIntent();
//
//            // Ambil data dari Intent dan simpan dalam variabel yang dideklarasikan di awal metode
//            nama = intent.getStringExtra("nama");
//            tanggal = intent.getStringExtra("tanggal");

            Intent scoreGame = new Intent(StartGame.this, ScoreGame.class);

            scoreGame.putExtra("nama", nama);
            scoreGame.putExtra("tanggal", tanggal);
            scoreGame.putExtra("TebakanBenar", tebakanBenar);
            scoreGame.putExtra("TebakanSalah", tebakanSalah);
            // Cetak nilai tebakanBenar untuk memverifikasi hasilnya
            Log.d("MainActivity", "Nama: " + nama);
            Log.d("MainActivity", "Tanggal: " + tanggal);
            Log.d("MainActivity", "Nilai tebakanBenar: " + tebakanBenar);
            Log.d("MainActivity", "Nilai tebakanSalah: " + tebakanSalah);
            startActivity(scoreGame);
        }
        // Cetak nilai tebakanBenar untuk memverifikasi hasilnya
        Log.d("MainActivity", "Nama: " + nama);
        Log.d("MainActivity", "Tanggal: " + tanggal);
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

//                Intent intent = getIntent();
////                // Ambil data dari Intent dan simpan
//                String nama = intent.getStringExtra("nama");
//                String tanggal = intent.getStringExtra("tanggal");

                Intent scoreGame = new Intent(StartGame.this, ScoreGame.class);
                scoreGame.putExtra("nama", namabaru);
                scoreGame.putExtra("tanggal", tanggalbaru);
                scoreGame.putExtra("TebakanBenar", tebakanBenar);
                scoreGame.putExtra("TebakanSalah", tebakanSalah);
                // Cetak nilai tebakanBenar untuk memverifikasi hasilnya
                Log.d("MainActivity", "Nama: " + namabaru);
                Log.d("MainActivity", "Tanggal: " + tanggalbaru);
                Log.d("MainActivity", "Nilai tebakanBenar: " + tebakanBenar);
                Log.d("MainActivity", "Nilai tebakanSalah: " + tebakanSalah);
                startActivity(scoreGame);
            }
        }.start();
    }
}
