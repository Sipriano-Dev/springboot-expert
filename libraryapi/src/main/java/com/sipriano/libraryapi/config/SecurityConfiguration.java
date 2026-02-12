package com.sipriano.libraryapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)//Desabilita proteção web, pra poder usar front externo
                .formLogin(Customizer.withDefaults())//Autentica pelo browser
                .httpBasic(Customizer.withDefaults())//Atutentica por outra aplicação http
                .authorizeHttpRequests(authorize -> {
                    //Qualquer requisição tem que estar autênticada
                    authorize.anyRequest().authenticated();
                })
                .build();
    }

}
