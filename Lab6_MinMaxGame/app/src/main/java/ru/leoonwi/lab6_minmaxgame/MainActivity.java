package ru.leoonwi.lab6_minmaxgame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private Random random;
    private int trueNumber;

    private TextView mainText;
    private TextView dialogText;
    private EditText formNum;
    private Button btnCheck;
    private Button btnRestart;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClickListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        random = new Random();
        trueNumber = random.nextInt(101);
    }

    public void onClickListener() {
        btnCheck = findViewById(R.id.button);
        btnRestart = findViewById(R.id.btn_Restart);
        btnExit = findViewById(R.id.btn_Exit);

        btnCheck.setOnClickListener(v -> {
            mainText = findViewById(R.id.mainText);
            dialogText = findViewById(R.id.dialogText);
            formNum = findViewById(R.id.formNum);
            if (!formNum.getText().toString().isEmpty()) {
                int number = Integer.parseInt(formNum.getText().toString());
                if (number >= 0 && number <= 100) {
                    if (number == trueNumber) {
                        mainText.setText(R.string.win);
                        dialogText.setVisibility(View.INVISIBLE);
                        formNum.setVisibility(View.INVISIBLE);
                        btnCheck.setVisibility(View.INVISIBLE);
                        btnRestart.setVisibility(View.VISIBLE);
                    } else {
                        if (number > trueNumber) {
                            dialogText.setText(R.string.tipLess);
                        } else {
                            dialogText.setText(R.string.tipLarger);
                        }
                    }
                } else {
                    dialogText.setText(R.string.inputError);
                }
            } else {
                dialogText.setText(R.string.inputError);
            }
        });

        btnRestart.setOnClickListener(v -> {
            formNum.setText("");
            recreate();
        });

        btnExit.setOnClickListener(v -> finish());
    }

}