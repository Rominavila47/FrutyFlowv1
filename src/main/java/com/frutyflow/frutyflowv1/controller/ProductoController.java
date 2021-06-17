package com.frutyflow.frutyflowv1.controller;

import com.frutyflow.frutyflowv1.model.GeneralResponse;
import com.frutyflow.frutyflowv1.model.Producto;
import com.frutyflow.frutyflowv1.model.Rol;
import com.frutyflow.frutyflowv1.repository.ProductoRepositorio;
import com.frutyflow.frutyflowv1.repository.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping (path = "/frutyflow/v1")
public class ProductoController {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @GetMapping (path = "producto/all")
    public @ResponseBody
    Iterable<Producto> getallproductos (){ return productoRepositorio.findAll(); }

    @GetMapping (path = "producto/{nombre}")
    public @ResponseBody
    Iterable<Producto>getProductoPorNombre(@PathVariable ("nombre") String nombre){
        return productoRepositorio.getProductoPorNombre(nombre); }

    @GetMapping (path = "producto/tipo/{tipo_producto}")
    public @ResponseBody
    Iterable<Producto>getProductoPorTipo(@PathVariable ("tipo_producto") String tipo_producto){
        return productoRepositorio.getProductoPorTipo(tipo_producto); }

    @PostMapping(path = "usuario/{idusuario}/producto/crear", consumes = "application/json", produces = "application/json")
    public ResponseEntity<GeneralResponse> crearProducto (@PathVariable ("idusuario")String idusuario,
                                                          @RequestBody Producto nuevoProducto){
        GeneralResponse response = new GeneralResponse();
        try{
            Collection<Rol> rolesPorUsuario = rolRepositorio.getRolesPorUsuario(idusuario);
            for (Rol r : rolesPorUsuario) {
                if (r.getNombre().equals("ADMINISTRADOR")) {
                    productoRepositorio.save(nuevoProducto);
                    response.setCodigo(HttpStatus.OK.value());
                    response.setMensaje("El Producto = " + nuevoProducto.getNombre() + " fue creado");
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

    @PutMapping (path = "usuario/{idusuario}/producto/actualizar")
    public ResponseEntity<GeneralResponse> actualizarProducto (@PathVariable ("idusuario")String idusuario, @RequestBody Producto actualizarProducto){
        GeneralResponse response = new GeneralResponse();
        try{
            Collection<Rol> rolesPorUsuario = rolRepositorio.getRolesPorUsuario(idusuario);
            for (Rol r : rolesPorUsuario) {
                if (r.getNombre().equals("ADMINISTRADOR")) {
                    productoRepositorio.save(actualizarProducto);
                    response.setCodigo(HttpStatus.OK.value());
                    response.setMensaje("El Producto = " + actualizarProducto.getNombre() + " fue actualizado");
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

    @DeleteMapping(path = "usuario/{idusuario}/producto/eliminar/{idproducto}")
    public @ResponseBody
    String eliminarProductoPorId (@PathVariable ("idusuario")String idusuario,
                                  @PathVariable ("idproducto")int idproducto){
        Collection<Rol> rolesPorUsuario = rolRepositorio.getRolesPorUsuario(idusuario);
        for (Rol r : rolesPorUsuario) {
            if (r.getNombre().equals("ADMINISTRADOR")) {
                productoRepositorio.deleteById(idproducto);
                return "El Producto con id = " + idproducto + " fue eliminado";
            }
        }
        return "Usuario no autorizado para esta función";
    }

}
