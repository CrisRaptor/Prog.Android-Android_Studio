package com.example.t5ejercicio8;

public class Data {
    private boolean check;
    private int imagen;
    private String nombre;

    public Data(boolean check, int imagen, String nombre) {
        this.check = check;
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public boolean isCheck() {
        return check;
    }

    public int getImagen() {
        return imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
