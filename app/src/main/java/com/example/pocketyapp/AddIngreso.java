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


public class AddIngreso extends AppCompatActivity {

    Button btcancel, btguardar;
    EditText etNombre, etCantidad;
    FirebaseAuth mAuth;
    DatabaseReference reff;
    Ingreso ingreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingreso);

        etNombre = findViewById(R.id.editText_NombreIngreso);
        etNombre.requestFocus();
        etCantidad = findViewById(R.id.editText_CantidadIngreso);
        btguardar = findViewById(R.id.button_guardarIngreso);
        btcancel = findViewById(R.id.button_cancelar);

        ingreso=new Ingreso();
        mAuth = FirebaseAuth.getInstance();
        reff= FirebaseDatabase.getInstance().getReference();

        btguardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int cantidadIngreso = Integer.parseInt(etCantidad.getText().toString().trim());
                ingreso.setName(etNombre.getText().toString().trim());
                ingreso.setQuantity(cantidadIngreso);
                String id = mAuth.getCurrentUser().getUid();

                reff.child("Cuentas").child(id).child("Ingresos").push().setValue(ingreso);
                Toast.makeText(AddIngreso.this, "Ingreso añadido correctamente", Toast.LENGTH_LONG).show();
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

}
