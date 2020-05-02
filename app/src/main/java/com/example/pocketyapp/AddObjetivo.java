package com.example.pocketyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AddObjetivo extends AppCompatActivity {

    Button btcancel, btguardar;
    EditText etNombre, etCantidad;
    FirebaseAuth mAuth;
    DatabaseReference reff;
    Objetivo objetivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_objetivo);

        etNombre = findViewById(R.id.editText_NombreObjetivo);
        etNombre.requestFocus();
        etCantidad = findViewById(R.id.editText_CantidadObjetivo);
        btguardar = findViewById(R.id.button_guardarObjetivo);
        btcancel = findViewById(R.id.button_cancelar);

        objetivo=new Objetivo();
        mAuth = FirebaseAuth.getInstance();
        reff= FirebaseDatabase.getInstance().getReference();

        btguardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int cantidadObjetivo = Integer.parseInt(etCantidad.getText().toString().trim());
                objetivo.setName(etNombre.getText().toString().trim());
                objetivo.setQuantity(cantidadObjetivo);
                String id = mAuth.getCurrentUser().getUid();

                reff.child("Cuentas").child(id).child("Objetivos").push().setValue(objetivo);
                Toast.makeText(AddObjetivo.this, "Objetivo añadido correctamente", Toast.LENGTH_LONG).show();

                resetFields();
            }

        });

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

    private void resetFields(){
        EditText temp;
        final int[] myEditTexts = new int[]{
                R.id.editText_NombreObjetivo,
                R.id.editText_CantidadObjetivo
        };

        for(int i =0; i<myEditTexts.length; i++){
            temp = findViewById(myEditTexts[i]);
            temp.setText(null);
        }
    }


}
