package com.sipriano.arquiteturaspring.montadora.configuration;

import com.sipriano.arquiteturaspring.montadora.Motor;
import com.sipriano.arquiteturaspring.montadora.TipoMotor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MontadoraConfiguration {

    @Bean(name = "motorAspirado")
    public Motor motorAspirado() {
        var motor = new Motor();
        motor.setCavalos(120);
        motor.setCilindros(4);
        motor.setModelo("XPTO-0");
        motor.setLitragem(2.0);
        motor.setTipo(TipoMotor.ASPIRADO);

        return motor;
    }

    @Primary
    @Bean(name = "motorTurbo")
    public Motor motorTurbo() {
        var motor = new Motor();
        motor.setCavalos(160);
        motor.setCilindros(6);
        motor.setModelo("HTSS-0");
        motor.setLitragem(2.4);
        motor.setTipo(TipoMotor.TURBO);

        return motor;
    }

    @Bean(name = "motorEletrico")
    public Motor motorEletrico() {
        var motor = new Motor();
        motor.setCavalos(100);
        motor.setCilindros(3);
        motor.setModelo("XHT-1");
        motor.setLitragem(1.5);
        motor.setTipo(TipoMotor.ELETRICO);

        return motor;
    }


}
