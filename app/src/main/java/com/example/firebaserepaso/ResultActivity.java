package com.example.firebaserepaso;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edt_usuario_correo, edt_contrasenia;

    private static  final String TAG="edson";
    Button btn_registrar, btn_ingresar;

    String usuario_correo, contrasenia;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mAuth=FirebaseAuth.getInstance();
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

        mAuth.signInWithEmailAndPassword(usuario_correo,contrasenia)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       Intent intent= new Intent(ResultActivity.this, MainActivity.class);

                       // el usuario  que esta  ingresando

                       FirebaseUser user= mAuth.getCurrentUser();

                       String email=user.getEmail();
                       String numTelf= user.getPhoneNumber();
                       String nombre= user.getDisplayName();
                       String provedorid=user.getProviderId();


                       intent.putExtra("email",email);
                       intent.putExtra("numTelf",numTelf);
                       intent.putExtra("nombre",nombre);
                       intent.putExtra("proveedor",provedorid);

                       Log.d(TAG,user.getEmail().toString());

                       startActivity(intent);
                   }
                   else
                   {
                       Log.d(TAG, "no autorizado");
                   }
                    }
                });
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

        mAuth.createUserWithEmailAndPassword(usuario_correo,contrasenia).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    Log.d(TAG,"creado exitosamente");
                }
                else
                {
                    Log.d(TAG,"hubo un error");
                }
            }
        });
    }
}
