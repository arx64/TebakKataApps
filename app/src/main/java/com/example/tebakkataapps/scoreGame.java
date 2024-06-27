package com.example.tebakkataapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class scoreGame extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scoreresult);


        Intent intent = getIntent();
        int scoreBenar = intent.getIntExtra("TebakanBenar", 0);
        int scoreSalah = intent.getIntExtra("TebakanSalah", 0);
        int scoreTotal = scoreBenar * 20;

        TextView textScoreBenar = (TextView) findViewById(R.id.textViewCorrectScore);
        TextView textScoreSalah = (TextView) findViewById(R.id.textViewIncorrectScore);
        TextView textScore = (TextView) findViewById(R.id.textViewScore);
        Button buttonMenu = (Button) findViewById(R.id.buttonMenu);

        // Set nilai integer ke TextView sebagai String
        textScoreBenar.setText(String.valueOf(scoreBenar));
        textScoreSalah.setText(String.valueOf(scoreSalah));
        textScore.setText(String.valueOf(scoreTotal));

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Main = new Intent(scoreGame.this, MainActivity.class);
                Main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(Main);
            }
        });
    }
    }