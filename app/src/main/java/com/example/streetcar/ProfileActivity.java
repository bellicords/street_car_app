package com.example.streetcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    ImageButton btnBack;
    AppCompatButton btnUpdate;
    RelativeLayout btnMeusAgend;
    RelativeLayout btnMeusServ;
    TextInputLayout txtNomePerfil;
    TextInputLayout txtEmailPerfil;
    TextInputLayout txtNumeroPerfil;
    TextInputLayout txtUsuarioPerfil;
    TextView lblNomePerfil;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String usuarioID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btnMeusAgend = findViewById(R.id.btnMeusAgend);
        btnBack = findViewById(R.id.btnBack);
        btnMeusServ = findViewById(R.id.btnMeusServ);
        txtNomePerfil = findViewById(R.id.txtNomePerfil);
        txtEmailPerfil = findViewById(R.id.txtEmailPerfil);
        txtNumeroPerfil = findViewById(R.id.txtTelefonePerfil);
        txtUsuarioPerfil = findViewById(R.id.txtUsuarioPerfil);
        db = FirebaseFirestore.getInstance();
        showConteudo();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnMeusAgend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ViewBookingActivity.class);
                startActivity(intent);
            }
        });
        btnMeusServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Serviço Indisponível");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("usuarios").document(usuarioID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    txtNomePerfil.getEditText().setText(documentSnapshot.getString("nome"));
                    txtEmailPerfil.getEditText().setText(documentSnapshot.getString("email"));
                    txtNumeroPerfil.getEditText().setText(documentSnapshot.getString("telefone"));
                    txtUsuarioPerfil.getEditText().setText(documentSnapshot.getString("usuario"));
                }
            }
        });
    }

    private void showConteudo() {
        Intent intent = getIntent();
        String usuario_user = intent.getStringExtra("usuario");
        String nome_user = intent.getStringExtra("nome");
        String email_user = intent.getStringExtra("email");
        String telefone_user = intent.getStringExtra("telefone");
        txtNomePerfil.getEditText().setText(nome_user);
        txtEmailPerfil.getEditText().setText(email_user);
        txtNumeroPerfil.getEditText().setText(telefone_user);
        txtUsuarioPerfil.getEditText().setText(usuario_user);
    }

    private void showToast(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }
}
