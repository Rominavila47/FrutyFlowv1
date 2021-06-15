package com.frutyflow.frutyflowv1.controller;

import com.frutyflow.frutyflowv1.model.*;
import com.frutyflow.frutyflowv1.repository.*;
import org.apache.catalina.filters.ExpiresFilter;
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
    private DireccionRepositorio direccionRepositorio;

    @Autowired
    private FacturaRepositorio facturaRepositorio;

    @Autowired
    private InventarioRepositorio inventarioRepositorio;

    @Autowired
    private ProductosPorFacturaRepositorio productosPorFacturaRepositorio;

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    @Autowired
    private RecetaRepositorio recetaRepositorio;

    @Autowired
    private RecursoRepositorio recursoRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    //DIRECCION
    @GetMapping (path = "direccion/usuario/{idusuario}")
    public @ResponseBody
    Collection<Direccion> getallDireccionesPorUsuario (@PathVariable ("idusuario") String idusuario){
        return direccionRepositorio.getDireccionesPorUsuario(idusuario);
    }

    @DeleteMapping(path = "direccion/eliminar/{iddireccion}")
    public @ResponseBody
    Iterable <Direccion> eliminarDireccionPorId (@PathVariable ("iddireccion")int iddireccion){
        direccionRepositorio.deleteById(iddireccion);
        return direccionRepositorio.findAll();}

    //Roles
    @GetMapping (path = "roles/all/usuario/{idusuario}")
    public @ResponseBody
    Collection<Rol> getallRolesPorUsuario (@PathVariable ("idusuario") String idusuario){
        return rolRepositorio.getRolesPorUsuario(idusuario);
    }
    // USUARIO
    @GetMapping (path = "usuario/all")
    public @ResponseBody
    Iterable<Usuario> getallusuarios (){
        return usuarioRepositorio.findAll();
    }
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

    //PRODUCTO
    @GetMapping (path = "producto/all")
    public @ResponseBody
    Iterable<Producto> getallproductos (){ return productoRepositorio.findAll(); }

    @PostMapping(path = "producto/crear", consumes = "application/json", produces = "application/json")
    public Producto crearProducto (@RequestBody Producto nuevoProducto) {
        return productoRepositorio.save(nuevoProducto);}

    @PutMapping (path = "producto/actualizar")
    public Producto actualizarProducto (@RequestBody Producto actualizarProducto){
        return productoRepositorio.save(actualizarProducto);}


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

    //FACTURA
    @GetMapping (path = "factura/all")
    public @ResponseBody
    Iterable<Factura> getallfacturas (){
        return facturaRepositorio.findAll();
    }

    @GetMapping (path = "factura/{idfactura}")
    public @ResponseBody
    Optional<Factura> getfacturaById (@PathVariable ("idfactura") int idfactura){
        return facturaRepositorio.findById(idfactura);}

    @PostMapping(path = "factura/create", consumes = "application/json", produces = "application/json")
    public Factura createfactura (@RequestBody Factura newfactura) {
        return facturaRepositorio.save(newfactura);}

    @PutMapping (path = "factura/update")
    public Factura updatefactura (@RequestBody Factura updatefactura){
        return facturaRepositorio.save(updatefactura);}

    @DeleteMapping(path = "usuario/{idusuario}/factura/eliminar/{idfactura}")
    public @ResponseBody
    ResponseEntity<GeneralResponse> eliminarFacturaPorId (@PathVariable ("idusuario")String idusuario,
                                         @PathVariable ("idfactura")int idfactura){
        GeneralResponse response = new GeneralResponse();
        try{
            Collection<Rol> rolesPorUsuario = rolRepositorio.getRolesPorUsuario(idusuario);
            for (Rol r : rolesPorUsuario) {
                if (r.getNombre().equals("ADMINISTRADOR")) {
                    facturaRepositorio.deleteById(idfactura);
                    response.setCodigo(HttpStatus.OK.value());
                    response.setMensaje("La Factura con id = " + idfactura + " fue eliminada");
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

    //INVENTARIO

    //PROVEEDOR
    @GetMapping (path = "proveedor/all")
    public @ResponseBody
    Iterable<Proveedor> getallproveedores (){
        return proveedorRepositorio.findAll();
    }


    //RECETA
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