package com.cjava.demo.controllers;

import com.cjava.demo.model.daos.AlumnoDao;
import com.cjava.demo.model.documents.Alumno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoRestController {

    @Autowired
    private AlumnoDao dao;

    private static final Logger log = LoggerFactory.getLogger(AlumnoRestController.class);

    @GetMapping
    public Flux<Alumno> listar() {
        return dao.findAll()
                .doOnNext(alumno -> log.info(alumno.getNombre()));
    }

    @PostMapping
    public Mono<Alumno> guardar(@RequestBody Alumno alumno) {
        return dao.save(alumno)
                .doOnNext(savedAlumno -> log.info("Guardado: " + savedAlumno.getNombre()));
    }
}
