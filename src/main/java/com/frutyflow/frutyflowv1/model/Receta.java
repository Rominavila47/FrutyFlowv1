package com.frutyflow.frutyflowv1.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Receta {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idreceta;

    @ManyToOne
    @JoinColumn (name = "idproducto", referencedColumnName = "idproducto")
    private Producto idproducto;

    @ManyToOne
    @JoinColumn (name = "idinventario", referencedColumnName = "idinventario")
    private Inventario idinventario;

    private double cantidad;

    private String unidad;

    public int getIdreceta (){
        return idreceta;
    }

    public void setIdreceta (int idreceta){
        this.idreceta = idreceta;
    }

    public Producto getIdproducto (){
        return idproducto;
    }

    public void setIdproducto (Producto idproducto){
        this.idproducto = idproducto;
    }

    public Inventario getIdinventario (){
        return idinventario;
    }

    public void setIdinventario (Inventario idinventario){
        this.idinventario = idinventario;
    }

    public double getCantidad (){
        return cantidad;
    }

    public void setCantidad (double cantidad){
        this.cantidad = cantidad;
    }

    public String getUnidad (){
        return unidad;
    }

    public void setUnidad (String unidad){
        this.unidad = unidad;
    }
}
