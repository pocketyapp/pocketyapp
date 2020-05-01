package com.example.pocketyapp;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class MainMenu extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener*/ {

        private Button btnCerrarsesion, btn_addIngresos, btn_addGastos;
        TextView infoUsuario;
        TextView infoEmail;
        TextView infoTotIngresos;
        TextView infoTotGastos;

        private FirebaseAuth mAuth;
        private DatabaseReference mDatabase;

        Button bt;

        ArrayList<Objetivo> arrayList;
        ElementoObjetivosAdapter adapter;
        ListView listaObjetivos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnCerrarsesion = findViewById(R.id.btnCerrarsesion);
        infoUsuario = findViewById(R.id.infoUsuario);
        infoEmail = findViewById(R.id.infoEmail);
        btn_addIngresos = findViewById(R.id.addIngresos);
        btn_addGastos = findViewById(R.id.addGastos);
        infoTotIngresos = findViewById(R.id.tvTotIngresos);
        infoTotGastos = findViewById(R.id.tvTotGastos);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Botón añadir objetivo
        bt = findViewById(R.id.button_addObjective);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openaddObjetivoView();
            }
        });

        //Botón añadir ingresos
        btn_addIngresos = findViewById(R.id.addIngresos);

        btn_addIngresos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AddIngreso.class);
                startActivity(intent);
            }
        });

        //Botón añadir gastos
        btn_addGastos = findViewById(R.id.addGastos);

        btn_addGastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AddGasto.class);
                startActivity(intent);
            }
        });

        btnCerrarsesion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(MainMenu.this, Login.class));
                finish();
            }
        });

        getUserinfo();

        getIngresoTotal();
        getGastoTotal();

        //LISTA DE OBJETIVOS

        listaObjetivos = findViewById(R.id.list_objectives);
        arrayList = new ArrayList<>();
        adapter = new ElementoObjetivosAdapter(this, R.layout.objetivo, arrayList);
        listaObjetivos.setAdapter(adapter);

        String id = mAuth.getCurrentUser().getUid();
        final String TAG = "MyActivity";
        Query Objectivesquery = mDatabase.child("Cuentas").child(id).child("Objetivos");
        Objectivesquery.keepSynced(true);
        Objectivesquery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG,"onChildAdded:" + dataSnapshot.getKey());
                Objetivo objetivo = dataSnapshot.getValue(Objetivo.class);
                arrayList.add(objetivo);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Objetivo objetivo = dataSnapshot.getValue(Objetivo.class);
                arrayList.remove(objetivo);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //FIN LISTA OBJETIVOS

        //Creamos Spinner meses
        /*Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.meses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);*/
    }

    private void openaddObjetivoView() {
        Intent intent = new Intent(this, AddObjetivo.class);
        startActivity(intent);
    }

    private void getIngresoTotal(){
        String id = mAuth.getCurrentUser().getUid();
        Query Ingresosquery = mDatabase.child("Cuentas").child(id).child("Movimientos").child("Ingresos");
        Ingresosquery.keepSynced(true);
        Ingresosquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int total = 0;
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Map<String,Object> map = (Map<String,Object>) ds.getValue();
                    Object cantidad = map.get("cantidad");
                    int pValue = Integer.parseInt(String.valueOf(cantidad));
                    total += pValue;
                    infoTotIngresos.setText(String.valueOf(total));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getGastoTotal(){
        String id = mAuth.getCurrentUser().getUid();
        Query Gastosquery = mDatabase.child("Cuentas").child(id).child("Movimientos").child("Gastos");
        Gastosquery.keepSynced(true);
        Gastosquery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int total = 0;
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    Map<String,Object> map = (Map<String,Object>) ds.getValue();
                    Object cantidad = map.get("cantidad");
                    int pValue = Integer.parseInt(String.valueOf(cantidad));
                    total += pValue;
                    infoTotGastos.setText(String.valueOf(total));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void getUserinfo() {
        String id = mAuth.getCurrentUser().getUid();
        mDatabase.child("Cuentas").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String usuario = dataSnapshot.child("usuario").getValue().toString();
                    String email = dataSnapshot.child("email").getValue().toString();

                    infoUsuario.setText(usuario);
                    infoEmail.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Metodos del Spinner meses
    /*@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_LONG).show();

        if (parent.getItemAtPosition(position).equals("Enero 2020")){
                Intent intent = new Intent(MainMenu.this, Mes01.class);
                startActivity(intent);
            }
    }*/

    /*@Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/
}
