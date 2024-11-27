package com.cjava.demo.controllers;

import com.cjava.demo.model.daos.CursoDao;
import com.cjava.demo.model.documents.Curso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class CursoController {

    @Autowired
    private CursoDao dao;

    private static final Logger log = LoggerFactory.getLogger(CursoController.class);

    @GetMapping({"/listar", "/"})
    public String listar(Model model) {
        Flux<Curso> cursos = dao.findAll().map(curso -> {
            curso.setNombre(curso.getNombre().toUpperCase());
            return curso;
        });

        model.addAttribute("cursos", cursos);
        model.addAttribute("titulo", "Listado de cursos");
        return "listar";
    }

    @GetMapping("/listar-datadriver")
    public String listarDatadriver(Model model) {
        Flux<Curso> cursos = dao.findAll().map(curso -> {
            curso.setNombre(curso.getNombre().toUpperCase());
            return curso;
        }).delayElements(Duration.ofSeconds(2));

        model.addAttribute("cursos", new ReactiveDataDriverContextVariable(cursos, 2));
        model.addAttribute("titulo", "Listado de cursos");
        return "listar";
    }

    @GetMapping("/listar-full")
    public String listarFull(Model model) {
        Flux<Curso> cursos = dao.findAll().map(curso -> {
            curso.setNombre(curso.getNombre().toUpperCase());
            return curso;
        }).repeat(2);

        model.addAttribute("cursos", cursos);
        model.addAttribute("titulo", "Listado de cursos");
        return "listar";
    }

    @GetMapping("/listar-chunked")
    public String listarChunked(Model model) {
        Flux<Curso> cursos = dao.findAll().map(curso -> {
            curso.setNombre(curso.getNombre().toUpperCase());
            return curso;
        }).repeat(2);

        model.addAttribute("cursos", cursos);
        model.addAttribute("titulo", "Listado de cursos");
        return "listar-chunked";
    }
}
