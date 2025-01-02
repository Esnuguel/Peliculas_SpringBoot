package com.peliculas.peliculas.dao;
import org.springframework.data.repository.CrudRepository;
import com.peliculas.peliculas.entities.Pelicula;

public interface IPeliculaRepository extends CrudRepository<Pelicula,Long> {

}
