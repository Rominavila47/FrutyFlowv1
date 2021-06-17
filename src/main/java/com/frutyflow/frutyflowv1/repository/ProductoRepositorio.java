package com.frutyflow.frutyflowv1.repository;

import com.frutyflow.frutyflowv1.model.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductoRepositorio extends CrudRepository<Producto, Integer> {

    @Query(value = "select * from Producto where nombre like %:nombre%", nativeQuery = true)
    Iterable<Producto> getProductoPorNombre (@Param("nombre") String nombre);

    @Query(value = "select * from Producto where tipo_producto like %:tipo_producto%", nativeQuery = true)
    Iterable<Producto> getProductoPorTipo (@Param("tipo_producto") String tipo_producto);

}
