package com.frutyflow.frutyflowv1.repository;

import com.frutyflow.frutyflowv1.model.Factura;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FacturaRepositorio extends CrudRepository<Factura, Integer> {

 //@Query(SELECT f.idfactura, f.fecha, f.iddireccion, f.total, p.observaciones,pr.nombre,pr.precio
   //      FROM factura f
     //    INNER JOIN productosporfactura p
       //  ON f.idfactura = p.idfactura
         //INNER JOIN producto pr
       //  ON p.idproducto = pr.idproducto
        // ORDER BY 1; nativeQuery = true)
// Collection<Integer> facturacompleta (@Param("idproductos") String "idproductos");
}
