package com.peliculas.peliculas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.peliculas.entities.Genero;
import com.peliculas.peliculas.services.IGeneroService;

@RestController //Utilizada para obtener objetos jsono xml
public class GeneroController {

    private IGeneroService service;

    public GeneroController(IGeneroService service)
    {
        this.service=service;
    }

    @PostMapping("genero")
    public Long guardar(@RequestParam String nombre)
    {
        Genero genero=new Genero();
        genero.setNombre(nombre);
        service.save(genero);
        return genero.getId();
    }

    @GetMapping("genero/{id}")
    public String buscarPorId(@PathVariable(name="id") Long id)
    {
        return service.findById(id).getNombre();
    }

    @GetMapping("ejemplo")
    public String xd(@ModelAttribute(name="xd") String xd)
    {
        return xd;
    }

}
