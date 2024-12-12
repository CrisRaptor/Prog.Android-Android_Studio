package com.example.desmontajedelordenador.clases;

public class Contenido {
    private String titulo;
    private String introduccion;
    private int imagen;
    private String descripcion;

    public Contenido(String titulo, String introduccion, int imagen, String descripcion) {
        this.titulo = titulo;
        this.introduccion = introduccion;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIntroduccion() {
        return introduccion;
    }

    public int getImagen() {
        return imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIntroduccion(String introduccion) {
        this.introduccion = introduccion;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
