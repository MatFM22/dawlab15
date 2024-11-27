package com.cjava.demo;

import com.cjava.demo.model.documents.Curso;
import com.cjava.demo.model.documents.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@SpringBootApplication
public class S11DevEjemplo01Application implements CommandLineRunner {

    @Autowired
    private ReactiveMongoTemplate template;

    public static void main(String[] args) {
        SpringApplication.run(S11DevEjemplo01Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Eliminar colecciones existentes
        template.dropCollection("cursos").subscribe();
        template.dropCollection("alumnos").subscribe();

        // Insertar datos iniciales para cursos
        template.insert(new Curso("c01", "Java 17", 4)).subscribe();
        template.insert(new Curso("c02", "Spring Boot", 5)).subscribe();
        template.insert(new Curso("c03", "Jenkins", 3)).subscribe();
        template.insert(new Curso("c04", "Kubernetes", 4)).subscribe();
        template.insert(new Curso("c05", "Kafka", 3)).subscribe();
        template.insert(new Curso("c06", "Microservicios", 5)).subscribe();
        template.insert(new Curso("c07", "Angular", 4)).subscribe();
        template.insert(new Curso("c08", "TypeScript", 4)).subscribe();
        template.insert(new Curso("c09", "HTML", 4)).subscribe();
        template.insert(new Curso("c10", "CSS", 1)).subscribe();

        // Insertar datos iniciales para alumnos
        template.insert(new Alumno("a01", "Juan", "Pérez", 20)).subscribe();
        template.insert(new Alumno("a02", "Ana", "López", 22)).subscribe();
        template.insert(new Alumno("a03", "Carlos", "Gómez", 19)).subscribe();
        template.insert(new Alumno("a04", "María", "Martínez", 21)).subscribe();
        template.insert(new Alumno("a05", "Luis", "Ramírez", 23)).subscribe();
    }
}
