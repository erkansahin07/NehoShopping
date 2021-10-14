package com.ekoo321.nehoshopping.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ekoo321.nehoshopping.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    Button girisYap;
    EditText email,sifre;
    TextView kayitOl;

    FirebaseAuth auth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();


        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        girisYap = findViewById(R.id.login_btn);
        email = findViewById(R.id.email_login);
        sifre = findViewById(R.id.sifre_login);
        kayitOl = findViewById(R.id.kayit_ol);

        kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));

            }
        });


        kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser();
                progressBar.setVisibility(View.VISIBLE);

            }
        });

    }

    private void loginUser() {

        String userEmail = email.getText().toString();
        String userPassword = sifre.getText().toString();


        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Email Yok", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Şifre Yok", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userPassword.length()<6){
            Toast.makeText(this, "6 karakterden büyük bir şifre giriniz.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Login Users

        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);
                        }else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Hata:"+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}