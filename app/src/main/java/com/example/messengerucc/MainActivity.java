package com.example.messengerucc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

     GoogleSignInOptions gso;
     GoogleSignInClient gsc;

     ImageView googlebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = findViewById(R.id.username);
        TextView password = findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin")  && password.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this, "Sesion Exitosa", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this, "usuario incorrecto", Toast.LENGTH_SHORT).show();
            }
        });


        googlebtn = findViewById(R.id.google_btn);

gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
gsc = GoogleSignIn.getClient(this, gso);

googlebtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        signIn();
    }
});

    }
    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigateTovamosahome();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Hubo un error", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void navigateTovamosahome() {
        finish();
        Intent intent = new Intent(MainActivity.this, vamosahome.class);
        startActivity(intent);

    }

}