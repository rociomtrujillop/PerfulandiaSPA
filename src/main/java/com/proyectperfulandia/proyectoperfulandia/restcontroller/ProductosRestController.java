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

import com.proyectperfulandia.proyectoperfulandia.entidades.Productos;
import com.proyectperfulandia.proyectoperfulandia.servicios.ProductosServices;

@RestController
@RequestMapping("api/productos")
public class ProductosRestController {
    @Autowired
    private ProductosServices productosServices;
    
    @GetMapping
    public List<Productos> listar(){
        return productosServices.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Productos> productosOptional = productosServices.findById(id);
        if(productosOptional.isPresent()){
            return ResponseEntity.ok(productosOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Productos> crear(@RequestBody Productos unProductos){
        return ResponseEntity.status(HttpStatus.CREATED).body(productosServices.save(unProductos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarProducto(@PathVariable Long id, @RequestBody Productos unProductos){
        Optional <Productos> productosOptional = productosServices.findById(id);
        if(productosOptional.isPresent()){
            Productos productoExiste = productosOptional.get();
            productoExiste.setNombre_Producto(unProductos.getNombre_Producto());
            productoExiste.setDescripcion_Producto(unProductos.getDescripcion_Producto());
            productoExiste.setCantidad(unProductos.getCantidad());
            productoExiste.setPrecio(unProductos.getPrecio());
            Productos productoModificado = productosServices.save(productoExiste);
            return ResponseEntity.ok(productoModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Long id){
        Productos unProductos = new Productos();
        unProductos.setId_producto(id);
        Optional<Productos> productosOptional = productosServices.delete(unProductos);
        if(productosOptional.isPresent()){
            return ResponseEntity.ok(productosOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
