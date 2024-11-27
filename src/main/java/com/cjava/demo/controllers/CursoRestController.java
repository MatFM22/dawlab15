package com.cjava.demo.controllers;

import com.cjava.demo.model.daos.CursoDao;
import com.cjava.demo.model.documents.Curso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/cursos")
public class CursoRestController {

    @Autowired
    private CursoDao dao;

    private static final Logger log = LoggerFactory.getLogger(CursoRestController.class);

    @GetMapping
    public Flux<Curso> index() {
        return dao.findAll()
                .map(curso -> {
                    curso.setNombre(curso.getNombre().toUpperCase());
                    return curso;
                })
                .doOnNext(curso -> log.info(curso.getNombre()));
    }
}
