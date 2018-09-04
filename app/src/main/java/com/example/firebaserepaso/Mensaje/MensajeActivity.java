package com.example.firebaserepaso.Mensaje;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.firebaserepaso.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MensajeActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mdatabaseReference;


    EditText edt_mensaje;
    Button btn_guardar_firebase;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensaje);

        mfirebaseDatabase= FirebaseDatabase.getInstance();
        mdatabaseReference= mfirebaseDatabase.getReference().child("mensajeria");

        username="Anónino";

        edt_mensaje=findViewById(R.id.mensaje);
        btn_guardar_firebase=findViewById(R.id.btn_guardar_firebase);

        btn_guardar_firebase.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String mensaje=edt_mensaje.getText().toString();

        FriendlyMessage friendlyMessage= new FriendlyMessage(mensaje, username);
        //instanceando la clase

        //para grabar  en firebase es

        mdatabaseReference.push().setValue(friendlyMessage);
        edt_mensaje.setText(""); // limpia el mensaje anterior




    }
}
