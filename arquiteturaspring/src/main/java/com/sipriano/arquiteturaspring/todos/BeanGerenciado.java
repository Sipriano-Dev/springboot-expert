package com.sipriano.arquiteturaspring.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanGerenciado {

    //Injeção forma 3 = menos recomendado, nem obrigatoria dependencia,nem opcional
    @Autowired
    private TodoValidator validator;

    //Injeção forma 1 = mais recomendada pelo spring team
    @Autowired//não obrigatório no construtor
    public BeanGerenciado(TodoValidator validator) {
        this.validator = validator;
    }

    public void utilizar() {
        var todo = new TodoEntity();
        validator.validar(todo);
    }

    //Injeção forma 2 = menos comum, segundo recomendado, permite não usar ou alterar
    @Autowired
    public void setValidator(TodoValidator validator) {
        this.validator = validator;
    }

}
