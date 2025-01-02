package com.peliculas.peliculas.services;

import java.util.List;

import com.peliculas.peliculas.entities.Pelicula;

public interface IpeliculaService {
    public void save(Pelicula pelicula);

    public Pelicula findById(Long id);

    public void delete(Long id);

    public List<Pelicula> findAll();
}
