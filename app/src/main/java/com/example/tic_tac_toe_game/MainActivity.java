package com.example.tic_tac_toe_game;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.tic_tac_toe_game.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private List<int[]> combinationList = new ArrayList<>();
    private int[] boxPos = {0, 0, 0, 0, 0, 0, 0, 0, 0};   // 9 zero need
    private int playerTurn = 1;
    private int totalSelectedBox = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.c2));

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        String getP1 = getIntent().getStringExtra("p1Name");
        String getP2 = getIntent().getStringExtra("p2Name");

        binding.p1NameL.setText(getP1);
        binding.p2NameL.setText(getP2);


        setOnclick(binding.img1,0);
        setOnclick(binding.img2,1);
        setOnclick(binding.img3,2);
        setOnclick(binding.img4,3);
        setOnclick(binding.img5,4);
        setOnclick(binding.img6,5);
        setOnclick(binding.img7,6);
        setOnclick(binding.img8,7);
        setOnclick(binding.img9,8);


        combinationList.add(new int[] {0,1,2});
        combinationList.add(new int[] {3,4,5});
        combinationList.add(new int[] {6,7,8});
        combinationList.add(new int[] {0,3,6});
        combinationList.add(new int[] {1,4,7});
        combinationList.add(new int[] {2,5,8});
        combinationList.add(new int[] {0,4,8});
        combinationList.add(new int[] {2,4,6});

    }

    private void setOnclick(View view , int boxPos){
        view.setOnClickListener(v -> {
            if (isBoxEmpty(boxPos)){
                actionPro((ImageView) v,boxPos);
            }
        });
    }
    public boolean isBoxEmpty(int pos){
        return boxPos[pos] == 0;
    }


    private void actionPro(ImageView imageView, int selectedBox) {
        boxPos[selectedBox] = playerTurn;

        if (playerTurn==1){
        imageView.setImageResource(R.drawable.x);

        if (checkResult()) {
            Result result = new Result(MainActivity.this, binding.p1NameL.getText().toString()
                    + " is The Winner!", MainActivity.this);
            result.setCancelable(true);
            result.show();
        } else if (totalSelectedBox == 9) {
            Result result = new Result(MainActivity.this, "Match Draw!", MainActivity.this);
            result.setCancelable(true);
            result.show();
        } else {
            changePlayerTurn();
            totalSelectedBox++;
        }
        }else {
            imageView.setImageResource(R.drawable.o);

            if (checkResult()) {
                Result result = new Result(MainActivity.this, binding.p2NameL.getText().toString()
                        + " is The Winner!", MainActivity.this);
                result.setCancelable(true);
                result.show();
            } else if (totalSelectedBox == 9) {
                Result result = new Result(MainActivity.this, "Match Draw!", MainActivity.this);
                result.setCancelable(true);
                result.show();
            } else {
                changePlayerTurn();
                totalSelectedBox++;
            }

        }
    }


    private void changePlayerTurn() {
        playerTurn = (playerTurn == 1) ? 2 : 1;
        if (playerTurn == 1) {
            binding.p1Layout.setBackgroundResource(R.drawable.white_box);
            binding.p2Layout.setBackgroundResource(R.drawable.black_border);
        } else {
            binding.p1Layout.setBackgroundResource(R.drawable.black_border);
            binding.p2Layout.setBackgroundResource(R.drawable.white_box);
        }
    }

    private boolean checkResult() {

        for (int[] combination : combinationList) {
            if (boxPos[combination[0]] == playerTurn && boxPos[combination[1]] == playerTurn && boxPos[combination[2]] == playerTurn) {
                return true;
            }
        }

        return false;
    }


    public void  restartMatch(){
        boxPos = new int[] {0,0,0,0,0,0,0,0,0};
        playerTurn = 1;
        totalSelectedBox = 1;

        binding.img1.setImageResource(R.drawable.white_box);
        binding.img2.setImageResource(R.drawable.white_box);
        binding.img3.setImageResource(R.drawable.white_box);
        binding.img4.setImageResource(R.drawable.white_box);
        binding.img5.setImageResource(R.drawable.white_box);
        binding.img6.setImageResource(R.drawable.white_box);
        binding.img7.setImageResource(R.drawable.white_box);
        binding.img8.setImageResource(R.drawable.white_box);
        binding.img9.setImageResource(R.drawable.white_box);


    }




}