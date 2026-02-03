package com.aluracursos.screenmatch;

import com.aluracursos.screenmatch.model.Categoria;
import com.aluracursos.screenmatch.principal.Principal;
import com.aluracursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	/*
	Anotación que indica a nuestra clase que realice una inyección de dependencia y posteriormente indicamos de donde
	se recibirá esa dependencia con los métodos deseados. Esto solo se puede hacer en una clase creada por Spring y es
	por eso que lo hacemos en está, para posteriormente enviarle este repositorio a la clase en donde queremos realizar
	el tratamiento correspondiente.
	 */
	@Autowired
	private SerieRepository repository;
	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.muestraElMenu();




	}
}
