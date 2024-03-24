package ru.leoonwi.lab7;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {
    private Button btnPlay;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        onClickListener();
    }

    private void onClickListener() {
        btnPlay = findViewById(R.id.btn_Play);
        btnExit = findViewById(R.id.btn_Exit);

        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenuActivity.this, GameActivity.class);
            startActivity(intent);
        });

        btnExit.setOnClickListener(v -> finish());
    }
}