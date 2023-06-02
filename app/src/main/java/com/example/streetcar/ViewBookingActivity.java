package com.example.streetcar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ViewBookingActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageButton btnExclui;
    ArrayList<Usuario> userArrayList;
    Adapter Adapter;
    FirebaseFirestore db;
    DocumentReference df;
    String usuarioID;
    String agendamentosID;
    ImageButton btnBackB;
    TextView empty;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<Usuario>();
        Adapter = new Adapter(ViewBookingActivity.this, userArrayList);
        recyclerView.setAdapter(Adapter);
        /*Adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                userArrayList.remove(position);
                Adapter.notifyItemRemoved(position);
            }
        });*/
        EventChangeListener();
        empty = findViewById(R.id.txtConsultAgend);
        btnBackB = findViewById(R.id.btnBackB);
        btnBackB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewBookingActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void EventChangeListener() {
        usuarioID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        agendamentosID = db.collection("usuarios").document(usuarioID).collection("agendamentos").getId();
        db.collection("usuarios").document(usuarioID).collection(agendamentosID).orderBy("marca", Query.Direction.ASCENDING)
            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                    if(error != null){
                        Log.e("Firestone Error", error.getMessage());
                        return;
                    }
                    for(DocumentChange dc : value.getDocumentChanges()){
                        if(dc.getType() == DocumentChange.Type.ADDED){
                            userArrayList.add(dc.getDocument().toObject(Usuario.class));
                        }
                        Adapter.notifyDataSetChanged();
                    }
                }
            });
    }
}
