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

import com.proyectperfulandia.proyectoperfulandia.entidades.Usuarios;
import com.proyectperfulandia.proyectoperfulandia.servicios.UsuarioServices;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestController {
    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping
    public List<Usuarios> listar(){
        return usuarioServices.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Usuarios> usuariosOptional = usuarioServices.findById(id);
        if(usuariosOptional.isPresent()){
            return ResponseEntity.ok(usuariosOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Usuarios> crear(@RequestBody Usuarios unUsuarios){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServices.save(unUsuarios));
    }
    
    @PutMapping("/{id_usuario}")
    public ResponseEntity<?> modificarUsuarios(@PathVariable Long id_usuario, @RequestBody Usuarios unUsuarios) {
        Optional <Usuarios> usuariosOptional = usuarioServices.findById(id_usuario);
        if(usuariosOptional.isPresent()){
            Usuarios usuariosExiste = usuariosOptional.get();
            usuariosExiste.setNombre_usuario(unUsuarios.getNombre_usuario());
            usuariosExiste.setApellido_usuario(unUsuarios.getApellido_usuario());
            usuariosExiste.setCorreo_usuario(unUsuarios.getCorreo_usuario());
            usuariosExiste.setEdad_usuario(unUsuarios.getEdad_usuario());
            Usuarios usuarioModificado = usuarioServices.save(usuariosExiste);
            return ResponseEntity.ok(usuarioModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id){
        Usuarios unusuarios = new Usuarios();
        unusuarios.setId_usuario(id);
        Optional<Usuarios> usuariosOptional = usuarioServices.delete(unusuarios);
        if(usuariosOptional.isPresent()){
            return ResponseEntity.ok(usuariosOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
