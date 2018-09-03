package com.example.firebaserepaso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_usuario_correo, edt_contrasenia;
    Button btn_registrar, btn_ingresar;

    String usuario_correo, contrasenia;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_contrasenia= findViewById(R.id.edt_contrasenia);
        edt_usuario_correo=findViewById(R.id.edt_usuario_correo);
        btn_registrar=findViewById(R.id.btn_registrar);
        btn_ingresar=findViewById(R.id.btn_ingresar);

        btn_ingresar.setOnClickListener(this);
        btn_registrar.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        usuario_correo= edt_usuario_correo.getText().toString();
        contrasenia=edt_contrasenia.getText().toString();

        switch(view.getId())
        {

            case R.id.btn_registrar:
                registerUser();

                break;

            case R.id.btn_ingresar:
                logIn();
                break;
        }


    }

    private void logIn() {
    }

    private void registerUser() {


        if(TextUtils.isEmpty(usuario_correo))
        {
            Toast.makeText(this, "ingrese el usuario", Toast.LENGTH_SHORT).show();

        }

        if(TextUtils.isEmpty(contrasenia))
        {
            Toast.makeText(this, "ingrese la contraseña", Toast.LENGTH_SHORT).show();

        }
    }
}
