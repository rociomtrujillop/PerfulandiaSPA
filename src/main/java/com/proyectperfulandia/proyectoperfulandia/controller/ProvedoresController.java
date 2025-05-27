package com.proyectperfulandia.proyectoperfulandia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyectperfulandia.proyectoperfulandia.entidades.Provedores;
import com.proyectperfulandia.proyectoperfulandia.repositorios.ProvedoresRepository;

@Controller
public class ProvedoresController {

    @Autowired
    private ProvedoresRepository provedoresRepository;
    @GetMapping("/provedores")
    public String provedores(Model model){
        List<Provedores> provedor = (List<Provedores>) provedoresRepository.findAll();
        model.addAttribute("provedores", provedor);
        return "listarprovedores";
    }
}
