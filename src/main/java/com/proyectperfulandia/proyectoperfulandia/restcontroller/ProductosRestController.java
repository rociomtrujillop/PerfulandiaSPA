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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/productos")
public class ProductosRestController {
    @Autowired
    private ProductosServices productosServices;
    
    @Operation(summary = "Obtener lista de Productos", description = "Devuelve todos los Productos disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de Productos retornada correctamente", 
                 content = @Content(mediaType = "application/json",
                 schema = @Schema(implementation = Productos.class)))
    @GetMapping
    public List<Productos> listar(){
        return productosServices.findByAll();
    }

     @Operation(summary = "Obtener Productos por id", description = "Obtiene el detalle de un producto especifico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Productos.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Productos> productosOptional = productosServices.findById(id);
        if(productosOptional.isPresent()){
            return ResponseEntity.ok(productosOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo producto", description = "Crea un producto con los datos Ingresados")
    @ApiResponse(responseCode = "201", description = "producto creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Productos.class)))
    @PostMapping
    public ResponseEntity<Productos> crear(@RequestBody Productos unProductos){
        return ResponseEntity.status(HttpStatus.CREATED).body(productosServices.save(unProductos));
    }

    @Operation(summary = "Modificar un Producto existente", description = "Modifica los datos de un Producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto modificado correctamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Productos.class))),
    @ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)
    })
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

    @Operation(summary = "Eliminar un Producto", description = "Elimina un Producto de la base de datos según su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
                     description = "Producto eliminado correctamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Productos.class))),
        @ApiResponse(responseCode = "404",
                     description = "Producto no encontrado", content = @Content)
    })
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
