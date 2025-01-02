package com.peliculas.peliculas.services;

import java.util.List;

import com.peliculas.peliculas.entities.Actor;

public interface IActorService {
    public List<Actor> findAll();
    public List<Actor> findAllById(List<Long> id);
}
