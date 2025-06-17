package com.sipriano.arquiteturaspring.todos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExemploValue {

    //Uma forma de pegar uma variavel de properties
    @Value("${app.config.variavel}")
    private String variavel;

    public void imprimirVariavel() {
        System.out.println(variavel);
    }

}
