package com.example.pocketyapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;



public class MainMenu extends AppCompatActivity {

        Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        bt = findViewById(R.id.button_addObjective);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openaddObjetivoView();
            }
        });

    }

    private void openaddObjetivoView() {
        Intent intent = new Intent(this, AddObjetivo.class);
        startActivity(intent);
    }
}
