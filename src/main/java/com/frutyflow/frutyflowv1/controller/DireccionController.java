package com.frutyflow.frutyflowv1.controller;

import com.frutyflow.frutyflowv1.model.Direccion;
import com.frutyflow.frutyflowv1.repository.DireccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping (path = "/frutyflow/v1")
public class DireccionController {

    @Autowired
    private DireccionRepositorio direccionRepositorio;

    @GetMapping (path = "direccion/usuario/{idusuario}")
    public @ResponseBody
    Collection<Direccion> getAllDireccionesPorUsuario (@PathVariable ("idusuario") String idusuario){
        return direccionRepositorio.getDireccionesPorUsuario(idusuario); }

    @DeleteMapping(path = "direccion/eliminar/{iddireccion}")
    public @ResponseBody
    Iterable <Direccion> eliminarDireccionPorId (@PathVariable ("iddireccion")int iddireccion){
        direccionRepositorio.deleteById(iddireccion);
        return direccionRepositorio.findAll();}
}
