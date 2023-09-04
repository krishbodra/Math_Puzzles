package com.example.mathpuzzles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinPage extends AppCompatActivity {
    int winIndex;

    TextView textView;

    Button btnContinue, btnMainMenu, btnExit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_page);
        winIndex = getIntent().getIntExtra("winIndex", 0);

        int lastLevel = MainActivity.sharedPreferences.getInt("lastLevel", 0);

        if (lastLevel <= winIndex) {
            MainActivity.editor.putInt("lastLevel", winIndex + 1);
            MainActivity.editor.commit();
        }

        String status = MainActivity.sharedPreferences.getString("status_" + winIndex, "pending");
        if (status.equals("skip") || status.equals("pending")) {
            MainActivity.editor.putString("status_" + winIndex, "clear");
            MainActivity.editor.commit();
        }

        textView = findViewById(R.id.title);
        textView.setText("Level " + (winIndex + 1) + " Completed");

        btnContinue = findViewById(R.id.btnContinue);
        btnMainMenu = findViewById(R.id.btnMainMenu);
        btnExit = findViewById(R.id.btnExit);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WinPage.this, PlayingClass.class);
                intent.putExtra("levelIndex", winIndex + 1);
                startActivity(intent);
                finish();
            }
        });

        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WinPage.this, SelectPuzzle.class);
                startActivity(intent);
                finish();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}