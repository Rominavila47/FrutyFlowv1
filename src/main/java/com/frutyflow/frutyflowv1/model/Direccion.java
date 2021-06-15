package com.frutyflow.frutyflowv1.model;

import javax.persistence.*;

@Entity
public class Direccion {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int iddireccion;

    private String descripcion;

    @ManyToOne
    @JoinColumn (name = "idusuario", referencedColumnName = "idusuario")
    private Usuario usuario;

    public int getIddireccion (){
        return iddireccion;
    }

    public void setIddireccion (int iddireccion){
        this.iddireccion = iddireccion;
    }

    public String getDescripcion (){
        return descripcion;
    }

    public void setDescripcion (String descripcion){
        this.descripcion = descripcion;
    }

    public Usuario getUsuario (){
        return usuario;
    }

    public void setUsuario (Usuario usuario){
        this.usuario = usuario;
    }
}
