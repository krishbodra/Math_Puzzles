package com.example.mathpuzzles;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PuzzleAdapter extends RecyclerView.Adapter<PuzzleAdapter.PuzzleHolderClass> {
    SelectPuzzle puzzle;
    ArrayList<String> statusArray;

    public PuzzleAdapter(SelectPuzzle selectPuzzle, ArrayList<String> statusArray) {
        puzzle = selectPuzzle;
        this.statusArray = statusArray;
    }

    @NonNull
    @Override
    public PuzzleHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(puzzle);
        View view = layoutInflater.inflate(R.layout.levels, parent, false);
        PuzzleHolderClass holderClass = new PuzzleHolderClass(view);
        return holderClass;
    }

    @Override
    @SuppressLint("RecyclerView")
    public void onBindViewHolder(@NonNull PuzzleHolderClass holder, int position) {

        int lastLevel = MainActivity.sharedPreferences.getInt("lastLevel", 0);

        String status = statusArray.get(position);

        if (lastLevel == position) {
            holder.levelText.setText("" + (position + 1));
            holder.tickView.setVisibility(View.GONE);
            holder.lockView.setVisibility(View.GONE);
        } else if (status.equals("pending")) {
            holder.lockView.setVisibility(View.VISIBLE);
            holder.tickView.setVisibility(View.GONE);
            holder.levelText.setText("");
        } else if (status.equals("skip")) {
            holder.levelText.setText("" + (position + 1));
            holder.tickView.setVisibility(View.GONE);
            holder.lockView.setVisibility(View.GONE);
        } else {
            holder.levelText.setText("" + (position + 1));
            holder.tickView.setVisibility(View.VISIBLE);
            holder.lockView.setVisibility(View.GONE);
        }

        holder.levelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!status.equals("pending") || lastLevel == position) {

                    Intent intent = new Intent(puzzle, PlayingClass.class);
                    intent.putExtra("levelIndex", position);
                    puzzle.startActivity(intent);
                    puzzle.finish();

                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return 75;
    }

    class PuzzleHolderClass extends RecyclerView.ViewHolder {

        ImageView tickView, lockView;
        TextView levelText;

        public PuzzleHolderClass(@NonNull View itemView) {
            super(itemView);

            tickView = itemView.findViewById(R.id.tickView);
            lockView = itemView.findViewById(R.id.lockView);
            levelText = itemView.findViewById(R.id.levelText);

        }
    }

}
