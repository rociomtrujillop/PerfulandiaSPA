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

import com.proyectperfulandia.proyectoperfulandia.entidades.Provedores;
import com.proyectperfulandia.proyectoperfulandia.servicios.ProvedoresServices;

@RestController
@RequestMapping("api/provedores")
public class ProvedoresRestController {

     @Autowired
    private ProvedoresServices provedoresServices;

    @GetMapping
    public List<Provedores> listar(){
        return provedoresServices.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Provedores> provedoresOptional = provedoresServices.findById(id);
        if(provedoresOptional.isPresent()){
            return ResponseEntity.ok(provedoresOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Provedores> crear(@RequestBody Provedores unProvedores){
        return ResponseEntity.status(HttpStatus.CREATED).body(provedoresServices.save(unProvedores));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarProvedor(@PathVariable Long id, @RequestBody Provedores unProvedores){
        Optional <Provedores> provedoresOptional = provedoresServices.findById(id);
        if(provedoresOptional.isPresent()){
            Provedores provedoresExiste = provedoresOptional.get();
            provedoresExiste.setNombre_Provedor(unProvedores.getNombre_Provedor());
            provedoresExiste.setProducto_Provedor(unProvedores.getProducto_Provedor());
            provedoresExiste.setUbicacion_Provedor(unProvedores.getUbicacion_Provedor());
            Provedores provedoresModificado = provedoresServices.save(provedoresExiste);
            return ResponseEntity.ok(provedoresModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminiarProvedor(@PathVariable Long id){
        Provedores unProvedor = new Provedores();
        unProvedor.setId_provedor(id);
        Optional<Provedores> provedorOptional = provedoresServices.delete(unProvedor);
        if(provedorOptional.isPresent()){
            return ResponseEntity.ok(provedorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
