package com.example.pocketyapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddIngreso extends AppCompatActivity {
    private EditText mEtDesIngreso, mEtCantIngreso;
    private Button mBtnSaveIngreso, mBtnCancelar;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingreso);

        mEtDesIngreso = (EditText)findViewById(R.id.etTipoIngreso);
        mEtCantIngreso = (EditText)findViewById(R.id.etCantIngreso);
        mBtnSaveIngreso = (Button)findViewById(R.id.btnSaveIngreso);
        mBtnCancelar = (Button)findViewById(R.id.btnCancelar);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mBtnSaveIngreso.setOnClickListener(new View.OnClickListener()   {
            @Override
            public void onClick(View v) {
                String desIngreso = mEtDesIngreso.getText().toString();
                String cantIngreso = mEtCantIngreso.getText().toString();
                mDatabase.child("desingreso").setValue(desIngreso);
                mDatabase.child("canIngreso").setValue(cantIngreso);
            }
        });
    }
}
