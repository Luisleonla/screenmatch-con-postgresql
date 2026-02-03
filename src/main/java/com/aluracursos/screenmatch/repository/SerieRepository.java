package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.model.Episodio;
import com.aluracursos.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/*
Interface para realizar nuestro repositorio de métodos u operaciones CRUD (Create, Read, Update, Delate) que son las
acciones básicas para trabajar con el programa de persistencia de datos.
En cuanto a la interface que esta extendiendo nuestra interface, nos pide pasar como argumentos uno del tipo generico
<T> para saber cual es la clase que se desea mapear y <ID> para saber el tipo de datos que se usara para el valor de
ID
 */
public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainsIgnoreCase(String nombreSerie);
    List<Serie> findTop5ByOrderByEvaluacionDesc(); //Desc refiere a que busque las series por valor de evaluación de
    //mayor a menor
    List<Serie>  findByGenero(Categoria categoria);
    /*
    Primera opción de query methods para crear métodos de acciones, en la cual se encadenan los métodos
     */
    //List<Serie> findByTotalTemporadasLessThanEqualAndEvaluacionGreaterThanEqual(Integer numeroTemporadas, Double evaluacionMinima);
/*
Segunda opción, natives querys, en las cual se usa la anotación @Query y se pasa como argumento las acciones que se
desean en lenguaje sql. Importante incluir el argumento native Query = true para que esta línea sea considerada en el
siguiente método creado.
Las querys nativas brindan un mayor control de busquedas al momento de trabajar con BDD pero pueden llegar a tener
problemas de portabilidad al momento de migrar a otra BDD.
 */
    //@Query(value = "SELECT * FROM series WHERE series.total_temporadas <= 6 AND series.evaluacion >= 9", nativeQuery = true)
/*
Tercera opción, querys avanzadas jpql. En esta opción la query puede leer los argumentos de tipo variable que escribimos.
Para declararlo correctamente debemos usar los comandos sql en conjunto con los nombres de nuestras clases y atributos
usados en la aplicación. En principio, en lugar de * (todos), lo sustituiremos por una variable "s" e instanciaremos
nuestra clase Serie en lugar de la tabla series. Al especificar los atributos que deseamos considerar en la busqueda,
los instanciaremos con el nombre dado en cada clase "s.totalTemporadas" y "s.evaluacion". Y para diferencias el valor
del atrinuto que deseamos comparar en la tabla y el valor pasado por el usuario, se debe usar ":" antecediendo a la
variable que pase el usuario para que jpl lo logrue diferenciar.
JPQL tiene la ventaja de ser de una lectura mas accesible, realiza automaticamente la portabilidad de comandos cuando
cambiamos de BDD pero tenemos menos control sobre los comandos.
 */
    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.evaluacion >= :evaluacion")
    List<Serie> seriesPorTemporadaYEvaluacion(Integer totalTemporadas, Double evaluacion);
/*
En la siguiente jpql query buscamos que se elijan los episodos relacionados (JOIN) entre la tabla serie y episodios
en los casos (WHERE) en donde coincidan un titulo de la BDD episodios, con el nombre del episoido mandado por el
usuario. Importante notar el comando ILIKE, utilizado para que no tome en cuenta la diferencia entre mayúsculas y
minusculas.
Hablando mas sobre LIKE, es un comando sql que nos permite buscar coincidencias deéndiendo de las reglas que nosotros
le demos, en el siguiente ejemplo lo podríamos transcribir como "Selecciona solo las columnas titulo de la tabla
eplisodios en donde tititulo sea 'empiece con A'
SELECT titulo FROM episodios WHERE titulo LIKE 'A%'
Si en lugar de 'A%' le pasamos '%A%', le estaremos diciendo que encuentre la soincidencias que contengan A en cualquier
parte del titulo
 */

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:nombreEpisodio%")
    List<Episodio> episodiosPorNombre(String nombreEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.evaluacion DESC LIMIT 5")
    List<Episodio> top5Episodios(Serie serie);
}
