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

public class VerIngresos extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    ArrayList<Ingreso> arrayList;
    ElementoIngresosAdapter adapter;
    ListView listaIngresos;
    Button btnVolver;
    TextView tvMovimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_movimiento);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        listaIngresos = findViewById(R.id.list_movimientos);
        arrayList = new ArrayList<>();
        adapter = new ElementoIngresosAdapter(this, R.layout.movimiento, arrayList);
        listaIngresos.setAdapter(adapter);

        String id = mAuth.getCurrentUser().getUid();
        final String TAG = "MyActivity";
        Query Ingresosquery = mDatabase.child("Cuentas").child(id).child("Movimientos").child("Ingresos");
        Ingresosquery.keepSynced(true);
        Ingresosquery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG,"onChildAdded:" + dataSnapshot.getKey());
                Ingreso ingreso = dataSnapshot.getValue(Ingreso.class);
                arrayList.add(ingreso);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Ingreso ingreso = dataSnapshot.getValue(Ingreso.class);
                arrayList.remove(ingreso);
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
                Intent intent = new Intent(VerIngresos.this, MainMenu.class);
                startActivity(intent);
            }
        });

        //Actualizar título
        tvMovimientos = findViewById(R.id.tvMovimientos);
        tvMovimientos.setText("Tus ingresos");




    }
}
