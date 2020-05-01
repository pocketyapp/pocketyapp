package com.example.pocketyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddIngreso extends AppCompatActivity {
    private EditText mEtDesIngreso, mEtCantIngreso, mEtFechaIngreso;
    private Button mBtnSaveIngreso, mBtnCancelar;
    FirebaseAuth mAuth;
    DatabaseReference reff;
    Ingreso ingreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingreso);

        mEtDesIngreso = findViewById(R.id.etTipoIngreso);
        mEtDesIngreso.requestFocus();
        mEtCantIngreso = findViewById(R.id.etCantIngreso);
        mEtFechaIngreso = findViewById(R.id.etFechaIngreso);
        mBtnSaveIngreso = findViewById(R.id.btnSaveIngreso);
        mBtnCancelar = findViewById(R.id.btnCancelar);

        ingreso=new Ingreso();
        mAuth = FirebaseAuth.getInstance();
        reff = FirebaseDatabase.getInstance().getReference();

        mBtnSaveIngreso.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int cantidadIngreso = Integer.parseInt(mEtCantIngreso.getText().toString().trim());
                ingreso.setDescripción(mEtDesIngreso.getText().toString().trim());
                ingreso.setCantidad(cantidadIngreso);
                ingreso.setFecha(mEtFechaIngreso.getText().toString().trim());
                String id = mAuth.getCurrentUser().getUid();

                reff.child("Cuentas").child(id).child("Movimientos").child("Ingresos").push().setValue(ingreso);
                Toast.makeText(AddIngreso.this, "Ingreso añadido correctamente", Toast.LENGTH_LONG).show();
            }

        });

        mBtnCancelar = findViewById(R.id.btnCancelar);

        mBtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddIngreso.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }
}
