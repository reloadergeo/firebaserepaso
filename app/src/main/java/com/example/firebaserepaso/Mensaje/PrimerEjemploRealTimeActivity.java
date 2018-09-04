package com.example.firebaserepaso.Mensaje;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.firebaserepaso.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PrimerEjemploRealTimeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_mensaje;
    EditText edt_mensaje;
    Button btn_modificar;

    //referencia al nodo principal
    DatabaseReference ref= FirebaseDatabase.getInstance().getReference();

    //referencia  a los datos (hijo)
    // no importa  sino esta creado, lo crea por mi sino esta.

    DatabaseReference childRef= ref.child("mensaje");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primer_ejemplo_real_time);

        tv_mensaje= findViewById(R.id.tv_mensaje);
        edt_mensaje= findViewById(R.id.edt_mensaje);
        btn_modificar= findViewById(R.id.btn_modificar);


        btn_modificar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //grabar  sobre la  base de datos

        String mensaje= edt_mensaje.getText().toString();

        childRef.setValue(mensaje);

        edt_mensaje.setText("");

    }
}
