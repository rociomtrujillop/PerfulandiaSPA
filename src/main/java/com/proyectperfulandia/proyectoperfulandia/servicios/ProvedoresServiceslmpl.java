package com.proyectperfulandia.proyectoperfulandia.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectperfulandia.proyectoperfulandia.entidades.Provedores;
import com.proyectperfulandia.proyectoperfulandia.repositorios.ProvedoresRepository;

@Service
public class ProvedoresServiceslmpl implements ProvedoresServices{

    @Autowired
    private ProvedoresRepository provedoresRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Provedores> findByAll() {
        return (List<Provedores>) provedoresRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Provedores> findById(Long id){
        Optional<Provedores> provedoresOptional = provedoresRepository.findById(id);
        return provedoresOptional;
    }

    @Override
    public Provedores save(Provedores unProvedores){
        return provedoresRepository.save(unProvedores);
    }

    @Override
    public Optional<Provedores> delete(Provedores unProvedores){
        Optional<Provedores> provedoresOptional = provedoresRepository.findById(unProvedores.getId_provedor());
        provedoresOptional.ifPresent(provedorDb ->{
            provedoresRepository.delete(unProvedores);
        });
        return provedoresOptional;
    }
}
