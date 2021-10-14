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
import com.ekoo321.nehoshopping.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {


    Button kayitOl;
    EditText isim,email,sifre;
    TextView girisYap;


    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        auth = FirebaseAuth.getInstance();
        database =FirebaseDatabase.getInstance();

        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        kayitOl = findViewById(R.id.login_btn);
        isim = findViewById(R.id.isim);
        email = findViewById(R.id.email_reg);
        sifre = findViewById(R.id.sifre_reg);
        girisYap = findViewById(R.id.giris_yap);

        girisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });

        kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));

                createUser();

                progressBar.setVisibility(View.VISIBLE);
            }
        });


    }

    private void createUser() {

        String userName = isim.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = sifre.getText().toString();

        if (TextUtils.isEmpty(userName)){
            Toast.makeText(this, "İsim Yok", Toast.LENGTH_SHORT).show();
            return;
        }

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

        //create user

        auth.createUserWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            UserModel userModel = new UserModel(userName,userEmail,userPassword);
                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Kullanıcılar").child(id).setValue(userModel);
                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(RegistrationActivity.this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegistrationActivity.this, "Hata:" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}