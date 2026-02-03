package com.aluracursos.screenmatch.model;

import com.aluracursos.screenmatch.service.ConsultaGemini;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;
@Entity //Asigna la clase como un entidad para ser mapeada
@Table(name="series") //Anotación para crear el nombre de la tablea, en caso contrario usará la de la clase por default
public class Serie {
    @Id //EStablece la siguiente variable como la variable Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Configuramos el Id sea autoincremental
    private Long Id;
    @Column(unique = true)
    private String titulo;
    private Integer totalTemporadas;
    private Double evaluacion;
    private String poster;
    @Enumerated(EnumType.STRING) //Indicamos que tenemos un atributo del tipo enum y lo determinamos como String que toma en cuenta
                            //solo el valor y no su indice como si lo hace Ordinal
    private Categoria genero;
    private String actores;
    private String sinopsis;
    //@Transient //Anotación para indicar que es un atributo que debe tomar en cuenta pero que no debe mapear en este momento
    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //cascade es la configuración necesaria para indicar
    //que en cuanto alla una modificación en serie, también se debe dar en temporada.
    //fetchType.EAGER por su lado, es una forma "ansiosa" de especificar que deseamos traer los datos de nuestro
    //atributo independientemente de que sean llamados o no.
    private List<Episodio> episodios;

    public Serie(){}
    public Serie(DatosSerie datosSerie){
        this.titulo = datosSerie.titulo();
        this.totalTemporadas = datosSerie.totalTemporadas();
        this.evaluacion = OptionalDouble.of(Double.valueOf(datosSerie.evaluacion())).orElse(0.0);
        this.poster = datosSerie.poster();
        this.genero = Categoria.fromString(datosSerie.genero().split(",")[0].trim());
        this.actores = datosSerie.actores();
        this.sinopsis = datosSerie.sinopsis(); //ConsultaGemini.obtenerTraduccion(datosSerie.sinopsis());
    }

    @Override
    public String toString() {
        return  "genero=" + genero +
                ", titulo='" + titulo + '\'' +
                ", totalTemporadas=" + totalTemporadas +
                ", evaluacion=" + evaluacion +
                ", poster='" + poster + '\'' +
                ", actores='" + actores + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", episodios='" + episodios + '\'';
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }
}
