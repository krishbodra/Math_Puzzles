package com.example.mathpuzzles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayingClass extends AppCompatActivity {

    TextView tvTitle;
    TextView answerText;
    TextView btnSubmit;
    TextView btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
    ImageView imageView, btnclear, btnSkip;

    int levelIndex;

      String[] answerArray = {"10","25","6","14","128","7","50","1025","100","3","212","3011","14","16","1","2","44","45","625","1","13","47","50","34","6","41","16","126","82","14","7","132","34","48","42","288","45","4","111","47","27","87","22","253","12","48","178","1","6","10","2","20","7","511","143547","84","11","27","3","5","39","31","10","130","22","3","14","42","164045","11","481","86","84","13","8"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_class);

        levelIndex = getIntent().getIntExtra("levelIndex", 0);
        Log.d("_JB_", "onCreate:  level Index --->  " + (levelIndex + 1));

        tvTitle = findViewById(R.id.tvTitle);
        imageView = findViewById(R.id.imageView);
        answerText = findViewById(R.id.answerText);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnclear = findViewById(R.id.btnclear);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);

        btnSkip = findViewById(R.id.btnSkip);

        tvTitle.setText("Puzzle " + (levelIndex + 1));
        answerText.setText("");


        int resourcId = getResources().getIdentifier("p" + (levelIndex + 1), "drawable", getPackageName());

        imageView.setImageResource(resourcId);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = answerText.getText().toString();
                answerText.setText(s + "1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = answerText.getText().toString();
                answerText.setText(s + "2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = answerText.getText().toString();
                answerText.setText(s + "3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = answerText.getText().toString();
                answerText.setText(s + "4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = answerText.getText().toString();
                answerText.setText(s + "5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = answerText.getText().toString();
                answerText.setText(s + "6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = answerText.getText().toString();
                answerText.setText(s + "7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = answerText.getText().toString();
                answerText.setText(s + "8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = answerText.getText().toString();
                answerText.setText(s + "9");
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = answerText.getText().toString();
                answerText.setText(s + "0");
            }
        });
        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* String s = answerText.getText().toString();*/
                answerText.setText("");
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = MainActivity.sharedPreferences.getString("status_" + levelIndex, "pending");
                if(status.equals("pending")) {
                    MainActivity.editor.putString("status_" + levelIndex, "skip");
                    MainActivity.editor.commit();
                }


                int lastLevel = MainActivity.sharedPreferences.getInt("lastLevel", 0);
                if (lastLevel <= levelIndex) {
                    MainActivity.editor.putInt("lastLevel", levelIndex + 1);
                    MainActivity.editor.commit();
                }

                Intent intent = new Intent(PlayingClass.this, PlayingClass.class);
                intent.putExtra("levelIndex", levelIndex + 1);
                startActivity(intent);
                finish();

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = answerText.getText().toString();
                String rightAnswer = answerArray[levelIndex];

                if (rightAnswer.equals(answer)) {
                    Toast.makeText(PlayingClass.this, "Right Answer", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PlayingClass.this, WinPage.class);
                    intent.putExtra("winIndex", levelIndex);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(PlayingClass.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}