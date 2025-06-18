package com.proyectperfulandia.proyectoperfulandia.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{rut}")
    public ResponseEntity<?> verDetalle(@PathVariable String rut) {
        Optional<Usuarios> usuariosOptional = usuarioServices.findById(rut);
        if(usuariosOptional.isPresent()) {
            return ResponseEntity.ok(usuariosOptional.get());
        }
    return ResponseEntity.notFound().build();
}

    @PutMapping("/{rut}")
    public ResponseEntity<?> modificarUsuarios(@PathVariable String rut, @RequestBody Usuarios unUsuarios) {
    Optional<Usuarios> usuariosOptional = usuarioServices.findById(rut);
    if(usuariosOptional.isPresent()) {
        Usuarios usuariosExiste = usuariosOptional.get();
        usuariosExiste.setNombreUsuario(unUsuarios.getNombreUsuario());
        usuariosExiste.setApellidoUsuario(unUsuarios.getApellidoUsuario());
        usuariosExiste.setCorreoUsuario(unUsuarios.getCorreoUsuario());
        usuariosExiste.setEdadUsuario(unUsuarios.getEdadUsuario());
        Usuarios usuarioModificado = usuarioServices.save(usuariosExiste);
        return ResponseEntity.ok(usuarioModificado);
    }
    return ResponseEntity.notFound().build();
}

    @DeleteMapping("/{rut}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable String rut) {
        Optional<Usuarios> usuariosOptional = usuarioServices.delete(rut);
        if(usuariosOptional.isPresent()) {
        return ResponseEntity.ok(usuariosOptional.get());
    }
        return ResponseEntity.notFound().build();
    }
}
