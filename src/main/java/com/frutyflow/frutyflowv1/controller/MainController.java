package com.frutyflow.frutyflowv1.controller;

import com.frutyflow.frutyflowv1.model.*;
import com.frutyflow.frutyflowv1.repository.*;
import org.apache.catalina.filters.ExpiresFilter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping (path = "/frutyflow/v1")
public class MainController {

    @Autowired
    private ProductosPorFacturaRepositorio productosPorFacturaRepositorio;

    @Autowired
    private RecursoRepositorio recursoRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private RecetaRepositorio recetaRepositorio;

    @Autowired
    private InventarioRepositorio inventarioRepositorio;

    //INVENTARIO


    // RECETA
    @DeleteMapping(path = "usuario/{idusuario}/receta/eliminar/{idreceta}")
    public @ResponseBody
    String eliminarRecetaPorId (@PathVariable ("idusuario")String idusuario,
                                  @PathVariable ("idreceta")int idreceta){
        Collection<Rol> rolesPorUsuario = rolRepositorio.getRolesPorUsuario(idusuario);
        for (Rol r : rolesPorUsuario) {
            if (r.getNombre().equals("ADMINISTRADOR")) {
                recetaRepositorio.deleteById(idreceta);
                return "La Receta con id = " + idreceta + " fue eliminado";
            }
        }
        return "Usuario no autorizado para esta función";
    }
}