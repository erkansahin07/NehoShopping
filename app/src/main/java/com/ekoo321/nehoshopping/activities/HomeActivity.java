package com.ekoo321.nehoshopping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ekoo321.nehoshopping.MainActivity;
import com.ekoo321.nehoshopping.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        if (auth.getCurrentUser() != null) {
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(HomeActivity.this, MainActivity.class));

            Toast.makeText(this, "Lütfen Bekleyin", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

        public void giris(View view) {

            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }

        public void kayit(View view) {

            startActivity(new Intent(HomeActivity.this, RegistrationActivity.class));
        }

    }