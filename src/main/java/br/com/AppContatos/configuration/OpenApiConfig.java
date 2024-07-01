package br.com.AppContatos.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.components(
						new Components()).info(new Info()
							.title("Aplicação de Cadastro")
							.description("Essa aplicação faz controle de cadastro de pessoas e seus contatos")
							.contact(new Contact()
										.name("Anderson Carlos de Morais Silva")
										.email("ander_son12@hotmail.com")
										.url("http://localhost:8080"))
							.version("Versão 0.0.1-SNAPSHOT")
						);				
	}
	
}