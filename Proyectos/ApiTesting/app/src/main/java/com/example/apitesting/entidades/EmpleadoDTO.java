package com.example.apitesting.entidades;

public class EmpleadoDTO {
    private int empno, departamento;
    private String nombre, puesto, departamentoNombre, departamentoUbicacion;

    public int getEmpno() {
        return empno;
    }

    public int getDepartamento() {
        return departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public String getDepartamentoNombre() {
        return departamentoNombre;
    }

    public String getDepartamentoUbicacion() {
        return departamentoUbicacion;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public void setDepartamentoNombre(String departamentoNombre) {
        this.departamentoNombre = departamentoNombre;
    }

    public void setDepartamentoUbicacion(String departamentoUbicacion) {
        this.departamentoUbicacion = departamentoUbicacion;
    }

    @Override
    public String toString() {
        return "EmpleadoDTO{" +
                "empno=" + empno +
                ", departamento=" + departamento +
                ", nombre='" + nombre + '\'' +
                ", puesto='" + puesto + '\'' +
                ", departamentoNombre='" + departamentoNombre + '\'' +
                ", departamentoUbicacion='" + departamentoUbicacion + '\'' +
                '}';
    }
}
