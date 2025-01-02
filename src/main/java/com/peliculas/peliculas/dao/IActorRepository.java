package com.peliculas.peliculas.dao;

import org.springframework.data.repository.CrudRepository;

import com.peliculas.peliculas.entities.Actor;

public interface IActorRepository extends CrudRepository<Actor,Long> {
    
}
