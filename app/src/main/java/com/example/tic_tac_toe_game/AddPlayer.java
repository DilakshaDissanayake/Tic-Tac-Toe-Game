package com.example.tic_tac_toe_game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPlayer extends AppCompatActivity {

    EditText p1,p2;
    Button btnStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_player);

        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        btnStartGame = findViewById(R.id.btnStartGame);

        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.c2));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnStartGame.setOnClickListener(v -> {
            String getP1 = p1.getText().toString();
            String getP2 = p2.getText().toString();

            if (getP1.isEmpty()|| getP2.isEmpty()){
                Toast.makeText(AddPlayer.this, "Please Enter Players Name", Toast.LENGTH_SHORT).show();
            }else {
                Intent i = new Intent(AddPlayer.this, MainActivity.class);
                i.putExtra("p1Name",getP1);
                i.putExtra("p2Name",getP2);
                startActivity(i);
            }

        });
    }
}