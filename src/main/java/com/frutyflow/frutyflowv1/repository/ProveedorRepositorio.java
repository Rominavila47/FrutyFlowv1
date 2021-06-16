package com.frutyflow.frutyflowv1.repository;

import com.frutyflow.frutyflowv1.model.Producto;
import com.frutyflow.frutyflowv1.model.Proveedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProveedorRepositorio extends CrudRepository<Proveedor, Integer> {

    @Query (value = "select * from Proveedor where nombre like %:nombre%", nativeQuery = true)
    Iterable<Proveedor> getProveedorPorNombre (@Param ("nombre") String nombre);
}
