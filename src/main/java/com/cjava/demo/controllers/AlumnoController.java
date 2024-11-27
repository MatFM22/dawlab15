package com.cjava.demo.controllers;

import com.cjava.demo.model.daos.AlumnoDao;
import com.cjava.demo.model.documents.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

@Controller
public class AlumnoController {

    @Autowired
    private AlumnoDao dao;

    @GetMapping("/alumnos")
    public String listar(Model model) {
        Flux<Alumno> alumnos = dao.findAll();

        model.addAttribute("alumnos", alumnos);
        model.addAttribute("titulo", "Listado de alumnos");
        return "alumnos";
    }
}
