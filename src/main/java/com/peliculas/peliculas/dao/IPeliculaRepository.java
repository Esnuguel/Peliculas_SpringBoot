package com.peliculas.peliculas.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.peliculas.peliculas.entities.Pelicula;

public interface IPeliculaRepository extends JpaRepository<Pelicula,Long> {

}
