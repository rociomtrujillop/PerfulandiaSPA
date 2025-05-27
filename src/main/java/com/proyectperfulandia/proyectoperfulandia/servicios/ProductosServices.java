package com.proyectperfulandia.proyectoperfulandia.servicios;

import java.util.List;
import java.util.Optional;

import com.proyectperfulandia.proyectoperfulandia.entidades.Productos;

public interface ProductosServices {

    List<Productos> findByAll();

    Optional<Productos> findById(Long id);

    Productos save(Productos unProductos);
    
    Optional<Productos> delete(Productos unProductos);
}
