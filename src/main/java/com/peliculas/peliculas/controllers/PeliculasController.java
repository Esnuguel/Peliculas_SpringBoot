package com.peliculas.peliculas.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peliculas.peliculas.dao.IActorRepository;
import com.peliculas.peliculas.entities.Actor;
import com.peliculas.peliculas.entities.Pelicula;
import com.peliculas.peliculas.services.IArchivoService;
import com.peliculas.peliculas.services.IGeneroService;
import com.peliculas.peliculas.services.IpeliculaService;

import jakarta.validation.Valid;


@Controller
public class PeliculasController {
    private IpeliculaService service;
    private IGeneroService generoService;
    private IActorRepository actorService;
    private IArchivoService archivoService;


    public PeliculasController(IpeliculaService service, IGeneroService generoService, IActorRepository actorService, IArchivoService archivoService) {
        this.service = service;
        this.generoService=generoService;
        this.actorService=actorService;
        this.archivoService=archivoService;
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
        Pelicula peli=service.findById(id);
        String ids= "";

        for(Actor actor: peli.getProtagonistas())
        {
            if("".equals(ids))
            {
                ids=actor.getId().toString();
            }else{
                ids+= "," +actor.getId().toString();
            }
        }

        model.addAttribute("pelicula",peli);
        model.addAttribute("ids",ids);
        model.addAttribute("generos", generoService.findAll());
        model.addAttribute("actores", actorService.findAll());
        model.addAttribute("titulo","Editar pelicula");
        return "pelicula";
    }
    //Fragment es una vista parcial que se puede reutilizar en otra vista xd
    //que es un end point

    @PostMapping("/pelicula")
    public String guardar(@Valid Pelicula pelicula, BindingResult br,@ModelAttribute(name = "ids") String ids, Model model, 
    @RequestParam("archivo") MultipartFile imagen)
    {

        if(br.hasErrors())
        {
            //model.addAttribute("titulo","Nuva pelicula");
            model.addAttribute("generos", generoService.findAll());
            model.addAttribute("actores", actorService.findAll());
            return "pelicula";
        }
        if(!imagen.isEmpty())
        {
            String archivo=pelicula.getNombre() + getExtencion(imagen.getOriginalFilename());
            pelicula.setImagen(archivo);
            try {
                archivoService.guardar(archivo, imagen.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            } 

        }else{
            pelicula.setImagen("Confundido.jpg");
        }
        if(ids!=null && !"".equals(ids))
        {
            List<Long> idsProta=Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
            List<Actor> protagonistas=(List<Actor>) actorService.findAllById(idsProta);
            pelicula.setProtagonistas(protagonistas);
        }

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

    private String getExtencion(String archivo)
    {
        return archivo.substring(archivo.lastIndexOf("."));
    }
    @GetMapping({"/listado"})
    public String listado (Model model, @RequestParam(required = false) String msj,@RequestParam(required = false) String tipoMsj )
    {
        model.addAttribute("titulo","Listado de peliculas");
        model.addAttribute("peliculas", service.findAll());
        if(!"".equals(tipoMsj) && !"".equals(msj))
        {
            model.addAttribute("msj", msj);
            model.addAttribute("tipoMsj", tipoMsj);
        }

        return "listado";
    }

    @GetMapping("/pelicula/{id}/delete")
    public String eliminar(@PathVariable(name="id") Long id,Model model, RedirectAttributes redirectAtt)
    {
        service.delete(id);
        redirectAtt.addAttribute("msj","la pelicula fue eliminada correctamete");
        redirectAtt.addAttribute("tipoMsj","success");
        return "redirect:/listado";
    }
}
