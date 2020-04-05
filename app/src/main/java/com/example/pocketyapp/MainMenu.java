package com.example.pocketyapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainMenu extends AppCompatActivity {

    EditText et;
    Button bt;
    ListView lv;
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

        et = (EditText) findViewById(R.id.editText);



    }

    private void openaddObjetivoView() {
        Intent intent = new Intent(this, AddObjetivo.class);
        startActivity(intent);
    }
}
