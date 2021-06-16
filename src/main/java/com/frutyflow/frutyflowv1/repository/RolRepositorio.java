package com.frutyflow.frutyflowv1.repository;

import com.frutyflow.frutyflowv1.model.Rol;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface RolRepositorio extends CrudRepository<Rol, Integer> {

    @Query(value = "select r.*, u.idusuario from Rol as r " +
            "inner join Usuario as u on r.idrol = u.idrol where u.idusuario = :idusuario", nativeQuery = true)
    Collection<Rol> getRolesPorUsuario (@Param("idusuario") String idusuario);
}
