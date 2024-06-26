package com.example.tebakkataapps;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AyoMain extends AppCompatActivity {
    Button startGame;
    EditText kata1, kata2, kata3, kata4, kata5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beforestartgame);

        startGame = findViewById(R.id.startGame);
        kata1 = findViewById(R.id.tebakKata1);
        kata2 = findViewById(R.id.tebakKata2);
        kata3 = findViewById(R.id.tebakKata3);
        kata4 = findViewById(R.id.tebakKata4);
        kata5 = findViewById(R.id.tebakKata5);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    showConfirmationDialog();
                }
            }
        });
    }

    private boolean validateInputs() {
        if (TextUtils.isEmpty(kata1.getText().toString().trim())) {
            kata1.setError("Kata 1 harus diisi");
            return false;
        }

        if (TextUtils.isEmpty(kata2.getText().toString().trim())) {
            kata2.setError("Kata 2 harus diisi");
            return false;
        }

        if (TextUtils.isEmpty(kata3.getText().toString().trim())) {
            kata3.setError("Kata 3 harus diisi");
            return false;
        }

        if (TextUtils.isEmpty(kata4.getText().toString().trim())) {
            kata4.setError("Kata 4 harus diisi");
            return false;
        }

        if (TextUtils.isEmpty(kata5.getText().toString().trim())) {
            kata5.setError("Kata 5 harus diisi");
            return false;
        }

        return true;
    }

    private void showConfirmationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah anda ingin memulai permainan?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startGame();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void startGame() {
        String kata1Text = kata1.getText().toString();
        String kata2Text = kata2.getText().toString();
        String kata3Text = kata3.getText().toString();
        String kata4Text = kata4.getText().toString();
        String kata5Text = kata5.getText().toString();

        Intent mulaiGame = new Intent(AyoMain.this, StartGame.class);
        mulaiGame.putExtra("kata1", kata1Text);
        mulaiGame.putExtra("kata2", kata2Text);
        mulaiGame.putExtra("kata3", kata3Text);
        mulaiGame.putExtra("kata4", kata4Text);
        mulaiGame.putExtra("kata5", kata5Text);
        startActivity(mulaiGame);
    }
}
