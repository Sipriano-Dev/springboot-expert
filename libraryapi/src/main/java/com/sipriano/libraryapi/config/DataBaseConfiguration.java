package com.sipriano.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    //Não recomendado para produção, é simples
//    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);
        return ds;
    }

    @Bean
    public DataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);

        //Poder aumentar se tiver um fluxo mto grander de usuários
        //Máximo de conexões liberadas
        config.setMaximumPoolSize(10);
        //Tamanho inicial do pool
        config.setMinimumIdle(1);
        config.setPoolName("library-db-pool");
        //padrão é 30m em milisegundos, aqui coloquei 10m
        config.setMaxLifetime(600000);
        //tempo q vai tentar conectar antes de dar timeout e tentar outra conexão(1m)
        config.setConnectionTimeout(60000);
        //teste simples q retorna 1 pra saber se a conexão esta funcionando
        config.setConnectionTestQuery("select 1");


        return new HikariDataSource(config);
    }

}
