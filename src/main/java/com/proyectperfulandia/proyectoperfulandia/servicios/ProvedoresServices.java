package com.proyectperfulandia.proyectoperfulandia.servicios;

import java.util.List;
import java.util.Optional;

import com.proyectperfulandia.proyectoperfulandia.entidades.Provedores;



public interface ProvedoresServices {

    List<Provedores> findByAll();

    Optional<Provedores> findById(Long id);

    Provedores save(Provedores unProvedores);
    
    Optional<Provedores> delete(Provedores unProvedores);
}
