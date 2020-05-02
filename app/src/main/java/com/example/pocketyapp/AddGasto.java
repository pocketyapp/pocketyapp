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

public class AddGasto extends AppCompatActivity {
    private EditText mEtDesGasto, mEtCantGasto, mEtFechaGasto;
    private Button mBtnSaveGasto, mBtnCancelar;
    FirebaseAuth mAuth;
    DatabaseReference reff;
    Gasto gasto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gasto);

        mEtDesGasto = findViewById(R.id.etTipoGasto);
        mEtDesGasto.requestFocus();
        mEtCantGasto = findViewById(R.id.etCantGasto);
        mEtFechaGasto = findViewById(R.id.etFechaGasto);
        mBtnSaveGasto = findViewById(R.id.btnSaveGasto);
        mBtnCancelar = findViewById(R.id.btnCancelar);

        gasto=new Gasto();
        mAuth = FirebaseAuth.getInstance();
        reff = FirebaseDatabase.getInstance().getReference();

        mBtnSaveGasto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int cantidadGasto = Integer.parseInt(mEtCantGasto.getText().toString().trim());
                gasto.setDescripción(mEtDesGasto.getText().toString().trim());
                gasto.setCantidad(cantidadGasto);
                gasto.setFecha(mEtFechaGasto.getText().toString().trim());
                String id = mAuth.getCurrentUser().getUid();

                reff.child("Cuentas").child(id).child("Movimientos").child("Gastos").push().setValue(gasto);
                Toast.makeText(AddGasto.this, "Gasto añadido correctamente", Toast.LENGTH_LONG).show();

                resetFields();
            }

        });

        mBtnCancelar = findViewById(R.id.btnCancelar);

        mBtnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddGasto.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }

    private void resetFields(){
        EditText temp;
        final int[] myEditTexts = new int[]{
                R.id.etTipoGasto,
                R.id.etCantGasto,
                R.id.etFechaGasto
        };

        for(int i =0; i<myEditTexts.length; i++){
            temp = findViewById(myEditTexts[i]);
            temp.setText(null);
        }
    }
}
