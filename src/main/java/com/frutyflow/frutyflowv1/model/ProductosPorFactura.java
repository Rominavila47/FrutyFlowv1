package com.frutyflow.frutyflowv1.model;

import javax.persistence.*;

@Entity
public class ProductosPorFactura {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int idproductosporfactura;

    @ManyToOne
    @JoinColumn (name = "idfactura", referencedColumnName = "idfactura")
    private Factura idfactura;

    @ManyToOne
    @JoinColumn (name = "idproducto", referencedColumnName = "idproducto")
    private Producto idproducto;

    private String observaciones;

    public int getIdproductosporfactura (){
        return idproductosporfactura;
    }

    public void setIdproductosporfactura (int idproductosporfactura){
        this.idproductosporfactura = idproductosporfactura;
    }

    public Factura getIdfactura (){
        return idfactura;
    }

    public void setIdfactura (Factura idfactura){
        this.idfactura = idfactura;
    }

    public Producto getIdproducto (){
        return idproducto;
    }

    public void setIdproducto (Producto idproducto){
        this.idproducto = idproducto;
    }

    public String getObservaciones (){
        return observaciones;
    }

    public void setObservaciones (String observaciones){
        this.observaciones = observaciones;
    }
}
