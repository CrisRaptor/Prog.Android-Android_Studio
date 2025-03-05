package com.example.cristianexamenbbdd.list;

public class Data {
    private int id, img;
    private String titulo;

    public Data(int img, String titulo) {
        this.img = img;
        this.titulo = titulo;
    }

    public Data(int id, int img, String titulo) {
        this.id = id;
        this.img = img;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public int getImg() {
        return img;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
