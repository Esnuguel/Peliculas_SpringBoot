package com.peliculas.peliculas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.peliculas.peliculas.dao.IPeliculaRepository;
import com.peliculas.peliculas.entities.Pelicula;
@Service
public class PeliculaService implements IpeliculaService{

    @Autowired
    private IPeliculaRepository repo;

    @Override
    public void save(Pelicula pelicula) {
        repo.save(pelicula);
    }

    @Override
    public Pelicula findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<Pelicula> findAll() {
        return (List<Pelicula>) repo.findAll();
    }

    @Override
    public Page<Pelicula> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

}
