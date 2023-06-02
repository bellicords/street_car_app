package com.example.streetcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    CardView btnHome;
    CardView btnAgendar;
    CardView btnPerfil;
    CardView btnServicos;
    CardView btnConfig;
    CardView btnSair;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btnHome = findViewById(R.id.btnHome);
        btnAgendar = findViewById(R.id.btnAgendar);
        btnPerfil = findViewById(R.id.btnPerfil);
        btnServicos = findViewById(R.id.btnServicos);
        btnConfig = findViewById(R.id.btnConfig);
        btnSair = findViewById(R.id.btnExit);
        mAuth = FirebaseAuth.getInstance();
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Serviço Indisponível");
            }
        });
        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookingActivity.class);
                startActivity(intent);
            }
        });
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        btnServicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Serviço Indisponível");
            }
        });
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Serviço Indisponível");
            }
        });
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
        //btnLogOut = findViewById(R.id.btnLogOut);
    }
    private void showToast(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser usuario = mAuth.getCurrentUser();
        if(usuario == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
}
