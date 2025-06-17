package com.sipriano.arquiteturaspring;

import com.sipriano.arquiteturaspring.todos.AppProperties;
import com.sipriano.arquiteturaspring.todos.ExemploValue;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {

	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);

		builder.profiles("producao", "homologação");
		//builder.bannerMode(Banner.Mode.OFF);
		//builder.lazyInitialization(true);//todos os beans serão lazy
		builder.run(args);

		//Contexto da aplicação inicia depois
		ApplicationContext applicationContext = builder.context();
		//var produtoRepository = applicationContext.getBean("produtoRepository");

		Environment environment = applicationContext.getEnvironment();
		String propertyName = environment.getProperty("spring.application.name");
		System.out.println(propertyName);

		ExemploValue exemploValue = applicationContext.getBean(ExemploValue.class);
		System.out.print("Valor da variável: ");
		exemploValue.imprimirVariavel();

		AppProperties appProperties = applicationContext.getBean(AppProperties.class);
		System.out.println(appProperties.getValor1());


	}

}
