package com.example.firebaserepaso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edt_usuario, edt_contrasenia;
    Button btn_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_contrasenia= findViewById(R.id.edt_contrasenia);
        edt_usuario=findViewById(R.id.edt_usuario_correo);
        btn_registrar=findViewById(R.id.btn_registrar);

    }
}
