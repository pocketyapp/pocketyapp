package com.example.pocketyapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    private EditText emailC;
    private EditText contraC;
    private Button btnLogin;

    private String email = "";
    private String contraseña = "";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailC = (EditText) findViewById(R.id.txt_emailCuenta);
        contraC = (EditText) findViewById(R.id.txt_contraseñaCuenta);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailC.getText().toString();
                contraseña = contraC.getText().toString();

                if (!email.isEmpty() && !contraseña.isEmpty()) {
                    loginUser();
                }
                else {
                    Toast.makeText(Login.this, "Completa los campos vacios.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void abrirRegistro(View view){
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);

    }

    private void loginUser() {
        mAuth.signInWithEmailAndPassword(email, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(Login.this, MainMenu.class));
                    finish();
                }
                else {
                    Toast.makeText(Login.this, "No se pudo iniciar sesion, compruebe los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null ) {
            startActivity(new Intent(Login.this, MainMenu.class));
            finish();
        }
    }
}
