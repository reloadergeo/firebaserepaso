package com.example.firebaserepaso.Google;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebaserepaso.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainGoogleActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    TextView nameTextView, emailTextView, idTextView;
    Button log_out;

    private GoogleApiClient googleApiClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_google);


        nameTextView=findViewById(R.id.nameTextView);
        emailTextView=findViewById(R.id.emailTextView);
        idTextView=findViewById(R.id.idTextView);
        log_out=findViewById(R.id.log_out);
        log_out.setOnClickListener(this);

        GoogleSignInOptions gson=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient= new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gson)
                .build();




    }


    @Override
    protected void onStart() {


        super.onStart();
        //el on start se ejecuta antes que el on create
        //un inicio  silencioso

        OptionalPendingResult<GoogleSignInResult> opr= Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        //va verificar  si esta logeado o no

        if(opr.isDone())
        //si esta  hecho?
        {
            GoogleSignInResult result=opr.get();
            handlerSingInResult(result);

        }else
        {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handlerSingInResult(googleSignInResult);
                }
            });
        }

    }

    private void handlerSingInResult(GoogleSignInResult result) {

        if(result.isSuccess())
        {
            //si el resultado es  exitoso aquie  obtengo los valores de  google nombre id etc..
            GoogleSignInAccount googleSignInAccount=result.getSignInAccount();
                     nameTextView.setText(googleSignInAccount.getDisplayName());
                     emailTextView.setText(googleSignInAccount.getEmail());
                     idTextView.setText(googleSignInAccount.getId());
        }
        else
        {

            goLogInScreen();

        }
    }

    private void goLogInScreen() {

        Intent i= new Intent(MainGoogleActivity.this, LoginGoogleActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

    }


    @Override
    public void onClick(View v) {

        //Deslogeo

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {

                if(status.isSuccess())
                {
                    goLogInScreen();

                }else
                {
                    Toast.makeText(getApplicationContext(),"no se pudo cerrar sesion", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
