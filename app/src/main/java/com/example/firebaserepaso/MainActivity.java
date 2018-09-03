package com.example.firebaserepaso;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  {

TextView txt_email, nombre, provedor, telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_email=findViewById(R.id.idemail);
        telefono=findViewById(R.id.idtelefono);
        nombre=findViewById(R.id.idnombre);
        provedor=findViewById(R.id.idprovedor);

        String email=getIntent().getStringExtra("email");
        String telf=getIntent().getStringExtra("numTelf");
        String nom=getIntent().getStringExtra("nombre");
        String prov=getIntent().getStringExtra("proveedor");

        txt_email.setText(email);
        telefono.setText(telf);
        nombre.setText(nom);
        provedor.setText(prov);




    }


}

