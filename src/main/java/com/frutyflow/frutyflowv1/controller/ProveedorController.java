package com.frutyflow.frutyflowv1.controller;

import com.frutyflow.frutyflowv1.model.GeneralResponse;
import com.frutyflow.frutyflowv1.model.Proveedor;
import com.frutyflow.frutyflowv1.model.Rol;
import com.frutyflow.frutyflowv1.repository.ProveedorRepositorio;
import com.frutyflow.frutyflowv1.repository.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping (path = "/frutyflow/v1")
public class ProveedorController {
    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @GetMapping (path = "proveedor/all")
    public @ResponseBody
    Iterable<Proveedor> getAllProveedores (){
        return proveedorRepositorio.findAll(); }

    @GetMapping (path = "proveedor/{idproveedor}")
    public @ResponseBody
    Optional<Proveedor> getProveedorPorId (@PathVariable ("idproveedor") int idproveedor){
        return proveedorRepositorio.findById(idproveedor);}

    @GetMapping (path = "proveedor/nombre/{nombre}")
    public @ResponseBody
    Iterable<Proveedor>getProveedorPorNombre(@PathVariable("nombre") String nombre){
        return proveedorRepositorio.getProveedorPorNombre(nombre); }


    @PostMapping (path = "usuario/{idusuario}/proveedor/crear")
    public ResponseEntity<GeneralResponse> crearProveedor (@PathVariable ("idusuario")String idusuario, @RequestBody Proveedor crearProveedor){
        GeneralResponse response = new GeneralResponse();
        try{
            Collection<Rol> rolesPorUsuario = rolRepositorio.getRolesPorUsuario(idusuario);
            for (Rol r : rolesPorUsuario) {
                if (r.getNombre().equals("ADMINISTRADOR") || r.getNombre().equals("EMPLEADO")) {
                    proveedorRepositorio.save(crearProveedor);
                    response.setCodigo(HttpStatus.OK.value());
                    response.setMensaje("El Proveedor = " + crearProveedor.getNombre() + " fue creado");
                    return ResponseEntity.ok(response);
                }
            }
            response.setCodigo(HttpStatus.UNAUTHORIZED.value());
            response.setMensaje("Usuario no autorizado para esta función");
            return ResponseEntity.ok(response);

        }catch (Exception e){
            response.setCodigo(HttpStatus.CONFLICT.value());
            response.setMensaje(HttpStatus.CONFLICT.getReasonPhrase() + " - " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping (path = "usuario/{idusuario}/proveedor/actualizar")
    public ResponseEntity<GeneralResponse> actualizarProveedor (@PathVariable ("idusuario")String idusuario, @RequestBody Proveedor actualizarProveedor){
        GeneralResponse response = new GeneralResponse();
        try{
            Collection<Rol> rolesPorUsuario = rolRepositorio.getRolesPorUsuario(idusuario);
            for (Rol r : rolesPorUsuario) {
                if (r.getNombre().equals("ADMINISTRADOR") || r.getNombre().equals("EMPLEADO")) {
                    proveedorRepositorio.save(actualizarProveedor);
                    response.setCodigo(HttpStatus.OK.value());
                    response.setMensaje("El Proveedor = " + actualizarProveedor.getNombre() + " fue actualizado");
                    return ResponseEntity.ok(response);
                }
            }
            response.setCodigo(HttpStatus.UNAUTHORIZED.value());
            response.setMensaje("Usuario no autorizado para esta función");
            return ResponseEntity.ok(response);

        }catch (Exception e){
            response.setCodigo(HttpStatus.CONFLICT.value());
            response.setMensaje(HttpStatus.CONFLICT.getReasonPhrase() + " - " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
    @DeleteMapping(path = "usuario/{idusuario}/proveedor/eliminar/{idproveedor}")
    public @ResponseBody
    String eliminarProveedorPorId (@PathVariable ("idusuario")String idusuario,
                                   @PathVariable ("idproveedor")int idproveedor){
        Collection<Rol> rolesPorUsuario = rolRepositorio.getRolesPorUsuario(idusuario);
        for (Rol r : rolesPorUsuario) {
            if (r.getNombre().equals("ADMINISTRADOR")) {
                proveedorRepositorio.deleteById(idproveedor);
                return "El Proveedor con id = " + idproveedor + " fue eliminado";
            }
        }
        return "Usuario no autorizado para esta función";
    }

}
