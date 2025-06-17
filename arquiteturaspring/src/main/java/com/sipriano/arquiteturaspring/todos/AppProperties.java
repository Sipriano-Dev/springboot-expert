package com.sipriano.arquiteturaspring.todos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//Essa classe serve pra pegar as variaveis do application properties e passa pra qui, tem que
// habilitar a configuração @EnableConfigurationProperties na applicação
@Configuration
@ConfigurationProperties(prefix = "app.config")
public class AppProperties {

    private String variavel;
    private Integer valor1;

    public String getVariavel() {
        return variavel;
    }

    public void setVariavel(String variavel) {
        this.variavel = variavel;
    }

    public Integer getValor1() {
        return valor1;
    }

    public void setValor1(Integer valor1) {
        this.valor1 = valor1;
    }
}
