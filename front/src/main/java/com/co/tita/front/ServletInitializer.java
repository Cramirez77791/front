package com.co.tita.front;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackages = "com.co.tita.front.*") //'package base' a partir del cual Spring buscará clases de tipo 'Bean' para convertir en 'Dependencias'.
@EntityScan(basePackages = "com.co.tita.front.*") //Para que Spring cree los objetos 'Entity' y sus correspondientes 'Tablas' en la B.D.
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FrontApplication.class);
	}

}
