package com.peliculas.peliculas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peliculas.peliculas.dao.IGeneroRepository;
import com.peliculas.peliculas.entities.Genero;
@Service
public class GeneroService implements IGeneroService{
    //es como ponerle new IGenroRepository xd
    @Autowired 
    private IGeneroRepository gr;

    @Override
    public void save(Genero genero) {
        gr.save(genero);
    }

    @Override
    public Genero findById(Long id) {
        return gr.findById(id).orElse(null);//esto por que retorna un Optional
    }

    @Override
    public void delete(Long id) {
        gr.deleteById(id);
    }

    @Override
    public List<Genero> findAll() {
        return (List<Genero>) gr.findAll();
    }

}
