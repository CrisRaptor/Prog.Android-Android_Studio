package com.example.apitesting.entidades;

public class Empleado {
    int id;
    String nombre, puesto;
    Departamento depno;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public Departamento getDepno() {
        return depno;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setDepno(Departamento depno) {
        this.depno = depno;
    }
}
