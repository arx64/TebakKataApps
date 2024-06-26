package com.example.tebakkataapps;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnMain, btnCaraMain;
    ImageView soundOn, profileImage;
    boolean isSoundOn = true;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnMain = findViewById(R.id.buttonAyoMain);
        btnCaraMain = findViewById(R.id.buttonCaraMain);
        profileImage = findViewById(R.id.profileImage);
        soundOn = findViewById(R.id.soundOn);

        // Initialize MediaPlayer with sound file from raw resource
        mediaPlayer = MediaPlayer.create(this, R.raw.sound); // sound.mp3 should be in res/raw folder
        mediaPlayer.setLooping(true); // Set looping to true

        // Check initial sound state and play if sound is on
        if (isSoundOn) {
            mediaPlayer.start();
        }

        // function listener for btnMain (Ayo Main)
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Main = new Intent(MainActivity.this, AyoMain.class);
                startActivity(Main);
            }
        });

        // function listener for ImageView (Profile)
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Main = new Intent(MainActivity.this, ProfileImage.class);
                startActivity(Main);
            }
        });

        // function listener for btnCaraMain (Cara Main)
        btnCaraMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Main = new Intent(MainActivity.this, CaraMain.class);
                startActivity(Main);
            }
        });

        // function listener for soundOn ImageView
        soundOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSound();
            }
        });

        // Initialize soundOn ImageView based on isSoundOn state
        updateSoundOnImage();
    }

    // function to make sound in application on/off
    private void toggleSound() {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        if (isSoundOn) {
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        } else {
            audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
            mediaPlayer.start();
        }
        isSoundOn = !isSoundOn;
        updateSoundOnImage();
    }

    // function to update soundOn ImageView based on isSoundOn state
    private void updateSoundOnImage() {
        if (isSoundOn) {
            soundOn.setImageResource(R.drawable.ic_lock_silent_mode_off);
        } else {
            soundOn.setImageResource(R.drawable.ic_lock_silent_mode);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
