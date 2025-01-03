package com.peliculas.peliculas.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="peliculas")
public class Pelicula implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "El nombre no debe ser vacio") //Verifica que no sea nulo y que no este vacio
    private String nombre;
    @Column(name="fecha_estreno")
    @Temporal(TemporalType.DATE) //ESTO ES SOLO PARA QUE GUARDE FECHA Y HORA :3
    @DateTimeFormat(pattern = "yyyy-MM-dd") //Formato de la fecha
    @NotNull(message = "El campo fecha de estreno no debe estar vacio") //SOLO QUE NO SEA NULL
    private Date fechaEstreno;
    //Esto es para mi primer proyecto en spring y el video dice que es one to one pero realmente es one to many
    @NotNull()
    @ManyToOne
    @JoinColumn(name="gen_id") //Nombre de la columna que tenga el id del genero
    private Genero genero;
    //Así viene en el video pero yo lo normalizare luego quiza....
    @ManyToMany
    private List<Actor> protagonistas;

    private String imagen;

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getFechaEstreno() {
        return fechaEstreno;
    }
    public void setFechaEstreno(Date fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }
    public Genero getGenero() {
        return genero;
    }
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    public List<Actor> getProtagonistas() {
        return protagonistas;
    }
    public void setProtagonistas(List<Actor> protagonistas) {
        this.protagonistas = protagonistas;
    }
    

    
}
