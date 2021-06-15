package com.frutyflow.frutyflowv1.repository;

import com.frutyflow.frutyflowv1.model.Direccion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface DireccionRepositorio extends CrudRepository<Direccion, Integer> {

    @Query (value = "select d.* from direccion as d inner join usuario as u " +
            "on d.idusuario = u.idusuario where d.idusuario = :idusuario", nativeQuery = true)
    Collection <Direccion> getDireccionesPorUsuario (@Param("idusuario") String idusuario);

}
