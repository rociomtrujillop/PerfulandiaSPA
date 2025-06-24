package com.proyectperfulandia.proyectoperfulandia.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyectperfulandia.proyectoperfulandia.entidades.Usuarios;
import com.proyectperfulandia.proyectoperfulandia.servicios.UsuariosServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/usuarios")
public class UsuariosRestController {
    @Autowired
    private UsuariosServices usuarioServices;

    @Operation(summary = "Obtener la lista de usuarios", description = "Devuelve los usuarios en la lista")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente", 
                 content = @Content(mediaType = "application/json",
                 schema = @Schema(implementation = Usuarios.class)))
    @GetMapping
    public List<Usuarios> listar(){
        return usuarioServices.findByAll();
    }

    @Operation(summary = "Obtener usuarios por Rut", description = "Obtiene el detalle de un usuario especifico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuarios.class))),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{rut}")
    public ResponseEntity<?> verDetalle(@PathVariable String rut) {
        Optional<Usuarios> usuariosOptional = usuarioServices.findById(rut);
        if(usuariosOptional.isPresent()) {
            return ResponseEntity.ok(usuariosOptional.get());
        }
    return ResponseEntity.notFound().build();
}

     @Operation(summary = "Actualiza un usuario existente", description = "Actualiza los datos de un usuario existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuarios.class))),
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
    })
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

    @Operation(summary = "Crea un nuevo usuario", description = "Crea un usuario con los datos entregados")
    @ApiResponse(responseCode = "201", description = "Usuario creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuarios.class)))
    @PostMapping
    public ResponseEntity<Usuarios> crear(@RequestBody Usuarios unUsuarios) {
        Usuarios nuevoUsuario = usuarioServices.save(unUsuarios);
        return ResponseEntity.ok(nuevoUsuario);
    }

      @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario segun su rut")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente",
                     content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuarios.class))),
        @ApiResponse(responseCode = "404",
                     description = "Usuario no encontrado", content = @Content)
    })
    @DeleteMapping("/{rut}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable String rut) {
        Optional<Usuarios> usuariosOptional = usuarioServices.delete(rut);
        if(usuariosOptional.isPresent()) {
        return ResponseEntity.ok(usuariosOptional.get());
    }
        return ResponseEntity.notFound().build();
    }
}
