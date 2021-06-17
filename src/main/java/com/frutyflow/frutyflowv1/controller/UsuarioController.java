package com.frutyflow.frutyflowv1.controller;

import com.frutyflow.frutyflowv1.model.Usuario;
import com.frutyflow.frutyflowv1.repository.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping (path = "/frutyflow/v1")
public class UsuarioController {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping (path = "usuario/all")
    public @ResponseBody
    Iterable<Usuario> getAllUsuarios (){return usuarioRepositorio.findAll(); }

    @GetMapping (path = "usuario/{idusuario}")
    public @ResponseBody
    Optional<Usuario> getUsuarioPorId (@PathVariable ("idusuario") int idusuario){
        return usuarioRepositorio.findById(idusuario);}

    @PostMapping(path = "usuario/crear", consumes = "application/json", produces = "application/json")
    public Usuario crearUsuario (@RequestBody Usuario nuevoUsuario) {
        return usuarioRepositorio.save(nuevoUsuario);}

    @PutMapping (path = "usuario/actualizar")
    public Usuario actualizarUsuario (@RequestBody Usuario actualizarUsuario){
        return usuarioRepositorio.save(actualizarUsuario);}

    @DeleteMapping(path = "usuario/eliminar/{idusuario}")
    public @ResponseBody
    Iterable <Usuario> eliminarUsuarioPorId (@PathVariable ("idusuario")int idusuario){
        usuarioRepositorio.deleteById(idusuario);
        return usuarioRepositorio.findAll();}

}
