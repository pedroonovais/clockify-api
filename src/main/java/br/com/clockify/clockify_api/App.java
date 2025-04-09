package br.com.clockify.clockify_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
// @EnableCaching
@OpenAPIDefinition(
	info = @Info(
		title = "Clockify API",
		version = "v1",
		description = "API para controle de horas trabalhadas em projetos",
		contact = @Contact(
			name = "Pedro Novais",
			email = "phenrique101007@gmail.com",
			url = "https://github.com/pedroonovais"
		)
	)
)
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
