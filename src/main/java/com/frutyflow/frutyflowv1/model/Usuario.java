package com.frutyflow.frutyflowv1.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idusuario;

    private String nombre;

    private String documento;

    private Date fechadenacimiento;

    private String genero;

    private String telefono;

    private String mail;

    private String contraseña;

    @ManyToOne
    @JoinColumn (name = "idrol", referencedColumnName = "idrol")
    private Rol rol;

    public int getIdusuario() {return idusuario;}

    public void setIdusuario(int idusuario) {this.idusuario = idusuario;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getFechadenacimiento() {
        return fechadenacimiento;
    }

    public void setFechadenacimiento(Date fechadenacimiento) {
        this.fechadenacimiento = fechadenacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }


}
