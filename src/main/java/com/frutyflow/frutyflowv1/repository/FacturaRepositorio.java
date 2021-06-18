package com.frutyflow.frutyflowv1.repository;

import com.frutyflow.frutyflowv1.model.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;

public interface FacturaRepositorio extends CrudRepository<Factura, Integer> {

@Query(value = "SELECT f.idfactura, f.fecha, d.descripcion, u.nombre, u.documento, pr.nombre, p.observaciones,  pr.precio, f.total " +
        "FROM Factura f INNER JOIN ProductosPorFactura p ON f.idfactura = p.idfactura INNER JOIN Producto pr ON p.idproducto = pr.idproducto " +
        "INNER JOIN Direccion d ON d.iddireccion = f.iddireccion INNER JOIN Usuario u ON u.idusuario = d.idusuario", nativeQuery = true)
Iterable<Factura> getFacturaPorId (@Param("idfactura") int idfactura);

    @Query(value = "SELECT f.idfactura, f.fecha, d.descripcion, u.nombre, u.documento, pr.nombre, p.observaciones,  pr.precio, f.total " +
            "FROM Factura f INNER JOIN ProductosPorFactura p ON f.idfactura = p.idfactura INNER JOIN Producto pr ON p.idproducto = pr.idproducto " +
            "INNER JOIN Direccion d ON d.iddireccion = f.iddireccion INNER JOIN Usuario u ON u.idusuario = d.idusuario", nativeQuery = true)
    Iterable<Factura> getFacturaPorTotal (@Param("total") String total);

    @Query(value = "SELECT f.idfactura, f.fecha, d.descripcion, u.nombre, u.documento, pr.nombre, p.observaciones,  pr.precio, f.total " +
            "FROM Factura f INNER JOIN ProductosPorFactura p ON f.idfactura = p.idfactura INNER JOIN Producto pr ON p.idproducto = pr.idproducto " +
            "INNER JOIN Direccion d ON d.iddireccion = f.iddireccion INNER JOIN Usuario u ON u.idusuario = d.idusuario", nativeQuery = true)
    Iterable<Factura> getFacturaPorFecha (@Param("fecha") Date fecha);
}

