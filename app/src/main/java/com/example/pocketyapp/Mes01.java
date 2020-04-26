package com.example.pocketyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Mes01 extends AppCompatActivity {

    Button btn_volver, btn_addIngresos, btn_addGastos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes01);

        btn_volver = findViewById(R.id.btn_volver);

        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mes01.this, MainMenu.class);
                startActivity(intent);
            }
        });

        btn_addIngresos = findViewById(R.id.addIngresos);

        btn_addIngresos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mes01.this, AddIngreso.class);
                startActivity(intent);
            }
        });

        /*btn_addGastos = findViewById(R.id.addGastos);

        btn_addGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mes01.this, AddGasto.class);
                startActivity(intent);
            }
        });*/
    }
}
