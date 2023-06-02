package com.example.streetcar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BookingActivity extends AppCompatActivity {
    EditText data;
    EditText hora;
    AppCompatButton btnAgendar;
    TextInputLayout btnModeloCarro;
    TextInputLayout btnMarca;
    TextInputLayout btnPlaca;
    TextInputLayout btnAno;
    ImageButton btnBackA;
    String[] mensagens = {"Preencha todos os campos", "O ano é Inválido", "A placa é Inválida", "A marca é Inválida", "O modelo é Inválido"};
    String usuarioID;
    String agendamentosID;
    String Data;
    int hour, minute;
    TextView txtConsultAgend;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btnBackA = findViewById(R.id.btnBackA);
        btnModeloCarro = findViewById(R.id.btModeloCarro);
        btnMarca = findViewById(R.id.btnMarca);
        btnPlaca = findViewById(R.id.btnPlaca);
        btnAno = findViewById(R.id.btnAno);
        btnAgendar = findViewById(R.id.btnAgendar);
        data = findViewById(R.id.btnData);
        hora = findViewById(R.id.btnHora);
        txtConsultAgend = findViewById(R.id.txtConsultAgend);
        hora.setInputType(InputType.TYPE_NULL);
        data.setInputType(InputType.TYPE_NULL);
        btnAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modelo = btnModeloCarro.getEditText().getText().toString();
                String marca = btnMarca.getEditText().getText().toString();
                String placa = btnPlaca.getEditText().getText().toString();
                String ano = btnAno.getEditText().getText().toString();
                if(modelo.isEmpty() || marca.isEmpty() || placa.isEmpty() || ano.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, mensagens[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else if(ano.length() < 4) {
                    Snackbar snackbar = Snackbar.make(v, mensagens[1], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else if(placa.length() < 7){
                    Snackbar snackbar = Snackbar.make(v, mensagens[2], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else if(marca.length() < 3){
                    Snackbar snackbar = Snackbar.make(v, mensagens[3], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }else{
                    CadastrarAgend();
                }
            }
        });
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                c.set(Calendar.DAY_OF_MONTH, mDay);
                DatePickerDialog dpd = new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                Calendar cal = Calendar.getInstance();
                                cal.set(Calendar.MONTH, monthOfYear);
                                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                cal.set(Calendar.YEAR, year);
                                if(cal.before(c)) {
                                    Toast.makeText(BookingActivity.this, "Data Inválida", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                StringBuilder date = new StringBuilder();
                                date.append((dayOfMonth<10?"0":"")).append(dayOfMonth).append("/").append((monthOfYear + 1) < 10 ? "0" : "").append((monthOfYear+1)).append("/").append(year);
                                data.setText(date.toString());
                                Data = data.toString();
                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(c.getTimeInMillis());
                dpd.show();
            }
        });
        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
                        if(hourOfDay > 17 || hourOfDay < 9){
                            Snackbar snackbar = Snackbar.make(v, "Horários Válidos: entre as 09:00 e as 17:00", Snackbar.LENGTH_LONG);
                            snackbar.setBackgroundTint(Color.WHITE);
                            snackbar.setTextColor(Color.BLACK);
                            snackbar.show();
                        }else {
                            hour = hourOfDay;
                            minute = minuteOfDay;
                            hora.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                        }
                    }
                };
                TimePickerDialog timePickerDialog = new TimePickerDialog(BookingActivity.this, onTimeSetListener, hour, minute, false);
                timePickerDialog.show();
            }
        });
        btnBackA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CadastrarAgend() {
        String modelo = btnModeloCarro.getEditText().getText().toString();
        String marca = btnMarca.getEditText().getText().toString();
        String placa = btnPlaca.getEditText().getText().toString();
        String ano = btnAno.getEditText().getText().toString();
        String Date = data.getText().toString();
        String Hora = hora.getText().toString();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String,Object> veiculo = new HashMap<>();
        veiculo.put("modelo", modelo);
        veiculo.put("marca", marca);
        veiculo.put("placa", placa);
        veiculo.put("ano", ano);
        veiculo.put("data", Date);
        veiculo.put("hora", Hora);
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        agendamentosID = db.collection("usuarios").document(usuarioID).collection("agendamentos").getId();
        Log.d("db", agendamentosID);
        DocumentReference documentReference = db.collection("usuarios").document(usuarioID).collection("agendamentos").document();
        documentReference.set(veiculo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("db", "Sucesso ao Salvar os Dados");
                Intent intent = new Intent(BookingActivity.this, ConfirmActivity.class);
                startActivity(intent);
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