package com.example.pocketyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class VerGastos extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    ArrayList<Gasto> arrayList;
    ElementoGastosAdapter adapter;
    ListView listaGastos;
    Button btnVolver;
    TextView tvMovimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_movimiento);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        listaGastos = findViewById(R.id.list_movimientos);
        arrayList = new ArrayList<>();
        adapter = new ElementoGastosAdapter(this, R.layout.movimiento,arrayList);
        listaGastos.setAdapter(adapter);

        String id = mAuth.getCurrentUser().getUid();
        final String TAG = "MyActivity";
        Query Gastosquery = mDatabase.child("Cuentas").child(id).child("Movimientos").child("Gastos");
        Gastosquery.keepSynced(true);
        Gastosquery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG,"onChildAdded:" + dataSnapshot.getKey());
                Gasto gasto = dataSnapshot.getValue(Gasto.class);
                arrayList.add(gasto);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Gasto gasto = dataSnapshot.getValue(Gasto.class);
                arrayList.remove(gasto);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //Botón Volver al menú
        btnVolver = findViewById(R.id.btnVolver);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerGastos.this, MainMenu.class);
                startActivity(intent);
            }
        });

        //Actualizar título
        tvMovimientos = findViewById(R.id.tvMovimientos);
        tvMovimientos.setText("Tus gastos");


    }
}
