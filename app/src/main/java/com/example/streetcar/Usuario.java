package com.example.streetcar;

public class Usuario {
    String data;
    String hora;
    String marca;
    String modelo;
    String placa;

    public Usuario(){

    }

    public Usuario(String data, String hora, String marca, String modelo, String placa) {
        this.data = data;
        this.hora = hora;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
