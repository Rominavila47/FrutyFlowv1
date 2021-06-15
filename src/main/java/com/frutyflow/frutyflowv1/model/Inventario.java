package com.frutyflow.frutyflowv1.model;

import javax.persistence.*;

@Entity
public class Inventario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idinventario;

    private String nombre;

    private double cantdisponible;

    private String unidad;

    private double precio;

    @ManyToOne
    @JoinColumn (name = "idproveedor", referencedColumnName = "idproveedor")
    private Proveedor proveedor;

    public int getIdinventario() {
        return idinventario;
    }

    public void setIdinventario(int idinventario) {
        this.idinventario = idinventario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantdisponible() {
        return cantdisponible;
    }

    public void setCantdisponible(double cantdisponible) {
        this.cantdisponible = cantdisponible;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}

