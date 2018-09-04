package com.example.firebaserepaso.Mensaje;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.firebaserepaso.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    protected void onStart() {
        super.onStart();

        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //cada vez  que  yo hague una modificacion en el nodo

                String mensaje=dataSnapshot.getValue(String.class);
                //dataSnapshot la instancia de ese  valor del nodo, y  le paso el tipo de valor.
                //el dataSnapashoot siempre esta escuchando lo que pasa en firebase, si  yo entro a la consola  y  modifico desde la consola  se actualiza en tiempo real
                tv_mensaje.setText(mensaje);

                //en tiempo real manda el dato a firebase y lo que aparece en firebase lo muestra al textview
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });
    }

    @Override
    public void onClick(View v) {

        //grabar  sobre la  base de datos

        String mensaje= edt_mensaje.getText().toString();

        childRef.setValue(mensaje);

        edt_mensaje.setText("");

    }
}
