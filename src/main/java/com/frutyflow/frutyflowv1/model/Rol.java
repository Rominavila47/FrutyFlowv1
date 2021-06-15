package com.frutyflow.frutyflowv1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Rol {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idrol;

    private String nombre;

    @JsonManagedReference
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable (name = "recursos_rol", joinColumns = @JoinColumn(name = "idrol"),
            inverseJoinColumns = @JoinColumn (name = "idrecurso"))
    private Set<Recurso> recurso_rol;

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Recurso> getRecurso_rol() {
        return recurso_rol;
    }

    public void setRecurso_rol(Set<Recurso> recurso_rol) {
        this.recurso_rol = recurso_rol;
    }
}
