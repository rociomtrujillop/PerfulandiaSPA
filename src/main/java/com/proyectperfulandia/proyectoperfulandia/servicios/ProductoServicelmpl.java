package com.proyectperfulandia.proyectoperfulandia.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectperfulandia.proyectoperfulandia.entidades.Productos;
import com.proyectperfulandia.proyectoperfulandia.repositorios.ProductoRepository;

@Service
public class ProductoServicelmpl implements ProductosServices{

    @Autowired
    private ProductoRepository productoRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Productos> findByAll() {
        return (List<Productos>) productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Productos> findById(Long id) {
        Optional<Productos> productosOptional = productoRepository.findById(id);
        return productosOptional;
    }

    @Override
    public Productos save(Productos unProductos) {
        return productoRepository.save(unProductos);
    }

    @Override
    public Optional<Productos> delete(Productos unProductos) {
        Optional<Productos> productosOptional = productoRepository.findById(unProductos.getId_producto());
        productosOptional.ifPresent(productoDb ->{
            productoRepository.delete(unProductos);
        });
        return productosOptional;
    }

}
