package com.proyectperfulandia.proyectoperfulandia.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectperfulandia.proyectoperfulandia.entidades.Proveedores;
import com.proyectperfulandia.proyectoperfulandia.servicios.ProveedoresServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/proveedores")
public class ProveedoresRestController {

     @Autowired
    private ProveedoresServices proveedoresServices;

    @GetMapping
    public List<Proveedores> listar(){
        return proveedoresServices.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Proveedores> proveedoresOptional = proveedoresServices.findById(id);
        if(proveedoresOptional.isPresent()){
            return ResponseEntity.ok(proveedoresOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Proveedores> crear(@RequestBody Proveedores unProveedores){
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedoresServices.save(unProveedores));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarProvedor(@PathVariable Long id, @RequestBody Proveedores unProveedores){
        Optional <Proveedores> proveedoresOptional = proveedoresServices.findById(id);
        if(proveedoresOptional.isPresent()){
            Proveedores proveedoresExiste = proveedoresOptional.get();
            proveedoresExiste.setNombre_Proveedor(unProveedores.getNombre_Proveedor());
            proveedoresExiste.setProducto_Proveedor(unProveedores.getProducto_Proveedor());
            proveedoresExiste.setUbicacion_Proveedor(unProveedores.getUbicacion_Proveedor());
            Proveedores provedoresModificado = proveedoresServices.save(proveedoresExiste);
            return ResponseEntity.ok(provedoresModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminiarProveedor(@PathVariable Long id){
        Proveedores unProveedor = new Proveedores();
        unProveedor.setId_proveedor(id);
        Optional<Proveedores> proveedorOptional = proveedoresServices.delete(unProveedor);
        if(proveedorOptional.isPresent()){
            return ResponseEntity.ok(proveedorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
