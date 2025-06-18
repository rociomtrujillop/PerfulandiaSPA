package com.proyectperfulandia.proyectoperfulandia.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectperfulandia.proyectoperfulandia.entidades.Usuarios;
import com.proyectperfulandia.proyectoperfulandia.repositorios.UsuarioRepository;

@Service
public class UsuariosServicelmpl implements UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuarios> findByAll() {
        return (List<Usuarios>) usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuarios> findById(String RUT_usuario) {
        return usuarioRepository.findById(RUT_usuario);
    }

    @Override
    public Usuarios save(Usuarios unUsuarios) {
        return usuarioRepository.save(unUsuarios);
    }

    @Override
    public Optional<Usuarios> delete(String rut) {
        Optional<Usuarios> usuariosOptional = usuarioRepository.findById(rut);
        usuariosOptional.ifPresent(usuarioRepository::delete);
        return usuariosOptional;
    }
}
