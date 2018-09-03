package com.example.firebaserepaso.Google;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.firebaserepaso.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class LoginGoogleActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    SignInButton sign_in;

    private GoogleApiClient googleApiClient;

    private static final int SING_IN_CODE=777;

    GoogleSignInOptions gson=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_google);

        googleApiClient= new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gson)
                .build();

        sign_in=findViewById(R.id.btn_google_sign_in);


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                //abre la pantalla de  google logeo

                startActivityForResult(i,SING_IN_CODE);

                //llamo al activity result y  re escribo el metodo on Activity Result para ver la  respuesta
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);

        GoogleSignInResult result= Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        //me trae  el resultado  de las pantallas de verificacion de google cuando te pide logearte

        handlerSingResult(result);

    }

    private void handlerSingResult(GoogleSignInResult result) {

        //si el  logeo que tuve es true

        if(result.isSuccess())
        {
            //abro la pantalla que  deseo  abrir cuando logeo bien con su clave
            
            goMainScreen();
        }
        else
        {
            Toast.makeText(this, " no se pudo  iniciar",Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScreen() {

        Intent i= new Intent(LoginGoogleActivity.this, MainGoogleActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        //si le doy atras  se quedan pantallas para borra las pantallas que creo al logear por google

        startActivity(i);

    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
