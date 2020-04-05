package com.example.pocketyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AddObjetivo extends AppCompatActivity {

    Button btcancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_objetivo);
        

        btcancel = findViewById(R.id.button_cancelar);
        btcancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMainMenuView();
            }

        });

    }

    private void openMainMenuView() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    /*public void onBtnClick(){
        btguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = et.getText().toString();
                arrayList.add(result);
                adapter.notifyDataSetChanged();

            }
        });
    }*/
}
