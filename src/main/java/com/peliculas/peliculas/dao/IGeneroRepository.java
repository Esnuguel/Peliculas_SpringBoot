package com.peliculas.peliculas.dao;

import org.springframework.data.repository.CrudRepository;

import com.peliculas.peliculas.entities.Genero;
//El frmaework de curd repocitory realiza las implementaciones automaticamnete :3
public interface IGeneroRepository extends CrudRepository<Genero,Long> {
    
}
