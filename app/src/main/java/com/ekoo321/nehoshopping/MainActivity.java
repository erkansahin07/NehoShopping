package com.ekoo321.nehoshopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.ekoo321.nehoshopping.activities.LoginActivity;
import com.ekoo321.nehoshopping.activities.RegistrationActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        auth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);
        if (auth.getCurrentUser()!=null){
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Lütfen Bekleyin", Toast.LENGTH_SHORT).show();
        }
    }

    public void giris(View view) {

        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

    public void kayit(View view) {

        startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
    }
}