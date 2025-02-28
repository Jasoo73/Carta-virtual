package com.example.mirestaurante;

public class Producto {
    public String titulo;
    public String descripcion;
    public Integer image;

    public String precio;

    public Producto() {

    }
    public Producto(String titulo, String descripcion, Integer image) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.image = image;
    }
}

