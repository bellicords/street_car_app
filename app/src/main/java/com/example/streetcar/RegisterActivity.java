package com.example.streetcar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.common.collect.BiMap;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    TextInputLayout btnNome;
    TextInputLayout btnUserName;
    TextInputLayout btnEmailCad;
    TextInputLayout btnNumero;
    TextInputLayout btnSenhaCad;
    AppCompatButton btnCadastrar;
    Button btnLoginR;
    String[] mensagens = {"Preencha todos os Campos", "Cadastro realizado com Sucesso", "Número de Telefone Inválido"};
    String usuarioID;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btnLoginR = findViewById(R.id.btnLoginR);
        btnNome = findViewById(R.id.btnNome);
        btnUserName = findViewById(R.id.btnUserName);
        btnEmailCad = findViewById(R.id.btnEmailCad);
        btnNumero = findViewById(R.id.btnNumero);
        btnSenhaCad = findViewById(R.id.btnSenhaCad);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = btnNome.getEditText().getText().toString();
                String usuario = btnUserName.getEditText().getText().toString();
                String email = btnEmailCad.getEditText().getText().toString();
                String telefone = btnNumero.getEditText().getText().toString();
                String senha = btnSenhaCad.getEditText().getText().toString();
                if(nome.isEmpty() || usuario.isEmpty() || email.isEmpty() || telefone.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else if(telefone.length() < 11){
                    Snackbar snackbar = Snackbar.make(v, mensagens[2], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    CadastrarUsuario(v);
                }
            }
        });
        btnLoginR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CadastrarUsuario(View v) {
        String nome = btnNome.getEditText().getText().toString();
        String usuario = btnUserName.getEditText().getText().toString();
        String email = btnEmailCad.getEditText().getText().toString();
        String telefone = btnNumero.getEditText().getText().toString();
        String senha = btnSenhaCad.getEditText().getText().toString();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    SalvarDados();
                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 2500);
                }else{
                    String erro;
                    try {
                        throw task.getException();
                    }catch(FirebaseAuthWeakPasswordException e){
                        erro = "Senha deve ter no mínimo 6 caracteres";
                        Log.d("ERRO", e.getMessage());
                    }catch(FirebaseAuthUserCollisionException e) {
                        erro = "Esta conta já foi cadastrada";
                        Log.d("ERRO", e.getMessage());
                    }catch(FirebaseAuthInvalidCredentialsException e) {
                        erro = "E-mail inválido";
                        Log.d("ERRO", e.getMessage());
                    }catch(Exception e){
                        erro = "Erro ao cadastrar Usuário";
                        Log.d("ERRO", e.getMessage());
                    }
                    Snackbar snackbar = Snackbar.make(v, erro, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });
    }
    private void SalvarDados() {
        String nome = btnNome.getEditText().getText().toString();
        String usuario = btnUserName.getEditText().getText().toString();
        String email = btnEmailCad.getEditText().getText().toString();
        String telefone = btnNumero.getEditText().getText().toString();
        String senha = btnSenhaCad.getEditText().getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String,Object> usuarios = new HashMap<>();
        usuarios.put("nome", nome);
        usuarios.put("usuario", usuario);
        usuarios.put("email", email);
        usuarios.put("telefone", telefone);
        usuarios.put("senha", senha);
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("usuarios").document(usuarioID);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db", "Sucesso ao Salvar os Dados");
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db", "Erro ao Salvar os Dados" + e.toString());
            }
        });
    }
}
