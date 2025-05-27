package com.proyectperfulandia.proyectoperfulandia.servicios;

import java.util.List;
import java.util.Optional;

import com.proyectperfulandia.proyectoperfulandia.entidades.Usuarios;


public interface UsuarioServices {
    List<Usuarios> findByAll();

    Optional<Usuarios> findById(Long id);

    Usuarios save(Usuarios unUsuarios);
    
    Optional<Usuarios> delete(Usuarios unUsuarios);
}
