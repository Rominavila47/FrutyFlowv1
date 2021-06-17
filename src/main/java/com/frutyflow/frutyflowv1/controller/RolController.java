package com.frutyflow.frutyflowv1.controller;

import com.frutyflow.frutyflowv1.model.Rol;
import com.frutyflow.frutyflowv1.repository.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping (path = "/frutyflow/v1")
public class RolController {

    @Autowired
    private RolRepositorio rolRepositorio;

    @GetMapping (path = "roles/all/usuario/{idusuario}")
    public @ResponseBody
    Collection<Rol> getAllRolesPorUsuario (@PathVariable ("idusuario") String idusuario){
        return rolRepositorio.getRolesPorUsuario(idusuario); }
}
