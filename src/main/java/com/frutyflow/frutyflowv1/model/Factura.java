package com.frutyflow.frutyflowv1.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Factura {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idfactura;

    private Date fecha;

    @OneToOne
    @JoinColumn (name = "iddireccion", referencedColumnName = "iddireccion")
    private Direccion iddireccion;

    private double total;

    public int getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(int idfactura) {
        this.idfactura = idfactura;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Direccion getIddireccion() {
        return iddireccion;
    }

    public void setIddireccion(Direccion iddireccion) {
        this.iddireccion = iddireccion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
