package com.sipriano.arquiteturaspring.todos;

import org.springframework.stereotype.Component;

@Component
public class MailSender {

    public void enviar(String msg) {
        System.out.println("Enviado email: " + msg);
    }

}
