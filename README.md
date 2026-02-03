![Programação-Java_ Persistencia de datos y consultas con Spring Data JPA](https://github.com/genesysR-dev/2066-java-persitencia-de-datos-y-consultas-con-Spring-JPA/assets/91544872/e0e3a9f8-afc7-4e7b-be83-469351ef2d70)

# ScreenMatch

Proyecto desarrollado durante el segundo curso de la formación Avanzando con Java de Alura

## 🔨 Objetivos del proyecto

1. Avanzar en el proyecto Screenmatch, iniciado en el primer curso de la formación, creando un menú con varias opciones;
   + Se realiza una modificación al menú para que el usuario pueda elegir entre buscar una sere, episodios, mostrar las busquedas realizadas o terminar el programa y añadiendo un switch case para ejecutar el método correspondiente
1. Modelar las abstracciones de la aplicación a través de clases, enums, atributos y métodos;
   + Se crea un record DatosSerie para establecer los valores iniciales que nos arroja la API sin ningún tratamiento.
   + Se realiza a su vez una clase Serie que la que procesará los datos extraidos de la API y les dará el tratamiento de acuerdo a las reglas del negocio que establescamos.
   + Para este ejercicio se desea implementar un ordenamiento a partir de las cátegorias, para lo cual se crea un enum Categoria que basicamente va a guardar todas las categorias como constantes al ser valores que no deben modificarse y a los cualse se les asigna un valor asociado. En otras palabras, la instancia será la categoría en español que usaremos en nuestro programa y el identificador será la forma en la que se realizará el match con la información obtenida de la API.
     + Al asignar un valor asociado a las instancias del Enum, se debe crear un nuevo argumento y un constructor que reciba el valor asociado y realice el match con nuestra instancia para retornar el valor exacto.
1. Consumir la API del ChatGPT(Opcional;
1. Utilizar Spring Data JPA para persistir datos en la base de datos;
1. Conocer varios tipos de bases de datos y utilizar PostgreSQL;
1. Trabajar con varios tipos de consultas a la base de datos;
1. Profundizar en la interfaz JPA Repository.
