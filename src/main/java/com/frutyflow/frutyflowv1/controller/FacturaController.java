package com.frutyflow.frutyflowv1.controller;

import com.frutyflow.frutyflowv1.model.Factura;
import com.frutyflow.frutyflowv1.model.GeneralResponse;
import com.frutyflow.frutyflowv1.model.Rol;
import com.frutyflow.frutyflowv1.repository.FacturaRepositorio;
import com.frutyflow.frutyflowv1.repository.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping (path = "/frutyflow/v1")
public class FacturaController {

    @Autowired
    private FacturaRepositorio facturaRepositorio;

    @Autowired
    private RolRepositorio rolRepositorio;

    @GetMapping (path = "factura/all")
    public @ResponseBody
    Iterable<Factura> getAllFacturas (){ return facturaRepositorio.findAll(); }

    @GetMapping (path = "factura/{idfactura}")
    public @ResponseBody
    Iterable<Factura> getFacturaPorId (@PathVariable ("idfactura") int idfactura){
        return facturaRepositorio.getFacturaPorId(idfactura);}

    @GetMapping (path = "factura/total/{total}")
    public @ResponseBody
    Iterable<Factura> getFacturaPorTotal (@PathVariable ("total") String total){
        return facturaRepositorio.getFacturaPorTotal(total);}

    @GetMapping (path = "factura/fecha/{fecha}")
    public @ResponseBody
    Iterable<Factura> getFacturaPorFecha (@PathVariable ("fecha") Date fecha){
        return facturaRepositorio.getFacturaPorFecha(fecha);}

    @PostMapping(path = "factura/crear", consumes = "application/json", produces = "application/json")
    public Factura crearFactura (@RequestBody Factura newFactura) {
        return facturaRepositorio.save(newFactura);}

    @PutMapping (path = "factura/actualizar")
    public Factura actualizarFactura (@RequestBody Factura actualizarFactura){
        return facturaRepositorio.save(actualizarFactura);}

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

}
