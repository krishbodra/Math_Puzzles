package com.example.mathpuzzles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class SelectPuzzle extends AppCompatActivity {
    RecyclerView recyclerView;
    //    String[] statusArray = new String[10];
    ArrayList<String> statusArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_puzzle);

        for (int i = 0; i < 75; i++) {
            String status = MainActivity.sharedPreferences.getString("status_"+i, "pending");
            statusArray.add(status);
        }

        Log.d("_TAG_", "onCreate: status ==> "+statusArray);

        PuzzleAdapter puzzleAdapter = new PuzzleAdapter(this, statusArray);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, RecyclerView.VERTICAL, false));

        recyclerView.setAdapter(puzzleAdapter);

    }
}