package com.peliculas.peliculas.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.peliculas.peliculas.dao.IActorRepository;
import com.peliculas.peliculas.entities.Actor;
import com.peliculas.peliculas.entities.Pelicula;
import com.peliculas.peliculas.services.IGeneroService;
import com.peliculas.peliculas.services.IpeliculaService;

import jakarta.validation.Valid;


@Controller
public class PeliculasController {
    private IpeliculaService service;
    private IGeneroService generoService;
    private IActorRepository actorService;

    public PeliculasController(IpeliculaService service, IGeneroService generoService, IActorRepository actorService) {
        this.service = service;
        this.generoService=generoService;
        this.actorService=actorService;
    }

    @GetMapping("pelicula")
    public String crear(Model model)
    {
        Pelicula peli=new Pelicula();
        model.addAttribute("pelicula",peli);
        model.addAttribute("generos", generoService.findAll());
        model.addAttribute("actores", actorService.findAll());
        model.addAttribute("titulo","Nuva pelicula");
        return "pelicula";
    }

    @GetMapping("/pelicula/{id}")
    public String editar(@PathVariable(name="id") Long id,Model model)
    {
        Pelicula peli=new Pelicula();
        model.addAttribute("pelicula",peli);
        model.addAttribute("generos", generoService.findAll());
        model.addAttribute("actores", actorService.findAll());
        model.addAttribute("titulo","Editar pelicula");
        return "pelicula";
    }
    //Fragment es una vista parcial que se puede reutilizar en otra vista xd
    //que es un end point

    @PostMapping("/pelicula")
    public String guardar(@Valid Pelicula pelicula, BindingResult br,@ModelAttribute(name = "ids") String ids, Model model)
    {

        if(br.hasErrors())
        {
            model.addAttribute("titulo","Nuva pelicula");
            model.addAttribute("generos", generoService.findAll());
            model.addAttribute("actores", actorService.findAll());
            return "pelicula";
        }
        List<Long> idsProta=Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
        List<Actor> protagonistas=(List<Actor>) actorService.findAllById(idsProta);
        pelicula.setProtagonistas(protagonistas);
        service.save(pelicula);
        return "redirect:home";
    }

    @GetMapping({"/","/home","/index"})
    public String home(Model model)
    {
        model.addAttribute("peliculas", service.findAll());
        model.addAttribute("msj", "El sistema esta en modo prueba :3");
        model.addAttribute("tipoMsj", "warning");
        return "home";
    }
}