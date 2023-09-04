package com.example.mathpuzzles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView bcontinue, bpuzzeles, bexit;

    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MathPuzzle", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        bcontinue = findViewById(R.id.bcontinue);
        bpuzzeles = findViewById(R.id.bpuzzeles);
        bexit = findViewById(R.id.bexit);

        bcontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int level = sharedPreferences.getInt("lastLevel", 0);
                Log.d("_TAG_", "onClick:   level = " + level);

                Intent intent = new Intent(MainActivity.this, PlayingClass.class);
                intent.putExtra("levelIndex", level);
                startActivity(intent);
            }
        });

        bpuzzeles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SelectPuzzle.class));
            }
        });
        bexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}