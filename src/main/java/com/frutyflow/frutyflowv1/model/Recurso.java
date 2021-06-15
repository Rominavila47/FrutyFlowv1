package com.frutyflow.frutyflowv1.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.tool.schema.JdbcMetadaAccessStrategy;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.util.Set;

@Entity
public class Recurso {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idrecurso;

    private String nombre;

    @JsonBackReference
    @JsonIgnoreProperties("idrol")
    @ManyToMany(mappedBy = "recurso_rol")
    private Set<Rol> recurso_rol;


    public int getIdrecurso() {
        return idrecurso;
    }

    public void setIdrecurso(int idrecurso) {
        this.idrecurso = idrecurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Rol> getRecurso_rol() {
        return recurso_rol;
    }

    public void setRecurso_rol(Set<Rol> recurso_rol) {
        this.recurso_rol = recurso_rol;
    }
}
