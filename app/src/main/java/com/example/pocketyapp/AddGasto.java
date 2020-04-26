package com.example.pocketyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddGasto extends AppCompatActivity {
    private EditText mEtDesGasto, mEtCantGasto;
    private Button mBtnSaveGasto, mBtnCancelar;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gasto);

        mEtDesGasto = (EditText)findViewById(R.id.etTipoGasto);
        mEtCantGasto = (EditText)findViewById(R.id.etCantGasto);
        mBtnSaveGasto = (Button)findViewById(R.id.btnSaveGasto);
        mBtnCancelar = (Button)findViewById(R.id.btnCancelar);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mBtnSaveGasto.setOnClickListener(new View.OnClickListener()   {
            @Override
            public void onClick(View v) {
                String desGasto = mEtDesGasto.getText().toString();
                String cantGasto = mEtCantGasto.getText().toString();
                mDatabase.child("desgasto").setValue(desGasto);
                mDatabase.child("canGastp").setValue(cantGasto);
            }
        });

        //Botón cancelar
        mBtnCancelar = findViewById(R.id.btnCancelar);

        mBtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddGasto.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }
}
