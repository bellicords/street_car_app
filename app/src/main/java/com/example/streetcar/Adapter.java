package com.example.streetcar;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {


    Context context;
    ArrayList<Usuario> userArrayList;
    private OnItemClickListener listener;

    public interface  OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public Adapter(Context context, ArrayList<Usuario> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v, listener);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Usuario usuario = userArrayList.get(position);
        holder.hora.setText(usuario.hora);
        holder.data.setText(usuario.data);
        holder.marca.setText(usuario.marca);
        holder.modelo.setText(usuario.modelo);
        holder.placa.setText(usuario.placa);
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView data, hora, marca, modelo, placa;
        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            data = itemView.findViewById(R.id.ConsultData);
            hora = itemView.findViewById(R.id.ConsultHora);
            marca = itemView.findViewById(R.id.ConsultMarca);
            modelo = itemView.findViewById(R.id.ConsultModelo);
            placa = itemView.findViewById(R.id.ConsultPlaca);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        //listener.onItemClick(());
                    }
                }
            });
        }
    }

}
