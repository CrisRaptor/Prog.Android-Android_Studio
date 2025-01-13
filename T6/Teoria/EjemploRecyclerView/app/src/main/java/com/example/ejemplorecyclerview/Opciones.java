package com.example.ejemplorecyclerview;

public class Opciones {

    private String Titulo, subtitulo;
    private int icono;

    public Opciones(String titulo, String subtitulo, int icono) {
        Titulo = titulo;
        this.subtitulo = subtitulo;
        this.icono = icono;
    }

    public String getTitulo() {
        return Titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public int getIcono() {
        return icono;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }
}
