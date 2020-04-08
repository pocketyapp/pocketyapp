package com.example.pocketyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {

    private EditText usuC;
    private EditText emaC;
    private EditText contraC;
    private EditText contraC2;

    private Button btnRegister;

    private String usuario = "";
    private String email = "";
    private String contraseña = "";
    private String contraseña2 = "";


    FirebaseAuth mAuth;
    DatabaseReference mDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        usuC = (EditText) findViewById(R.id.txt_usuarioCuenta);
        emaC = (EditText) findViewById(R.id.txt_emailCuenta);
        contraC = (EditText) findViewById(R.id.txt_contraseñaCuenta);
        contraC2 = (EditText) findViewById(R.id.txt_contraseñaCuenta2);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = usuC.getText().toString();
                email = emaC.getText().toString();
                contraseña = contraC.getText().toString();
                contraseña2 = contraC2.getText().toString();


                if (!usuario.isEmpty() && !email.isEmpty() && !contraseña.isEmpty() && !contraseña2.isEmpty()) {

                    if (contraseña.length() >= 6) {

                        if(contraseña.equals(contraseña2)) {
                            registerUser();
                        }
                        else {
                            Toast.makeText(Register.this, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Register.this, "La contraseña debe tener minimo 6 caracteres", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Register.this, "Debe completar los campos vacios", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Map<String, Object> map = new HashMap<>();
                    map.put("usuario", usuario);
                    map.put("email", email);
                    map.put("contraseña", contraseña);


                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("Cuentas").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                startActivity(new Intent(Register.this, Completo.class));
                                finish();
                            } else {
                                Toast.makeText(Register.this, "No se pudieron crear los datos correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(Register.this, "No se pudo registrar este usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String getContraseña2() {
        return contraseña2;
    }

    public void setContraseña2(String contraseña2) {
        this.contraseña2 = contraseña2;
    }
}