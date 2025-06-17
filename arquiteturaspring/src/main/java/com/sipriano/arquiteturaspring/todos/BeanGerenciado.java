package com.sipriano.arquiteturaspring.todos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

//Escopo Singleton é o padrão(usa a mesma instancia pra tudo)
//Escopo Prototype pra cada usuário, uma instancia
//Escopo Request só existe durante a requisição, não guarda estado
//Escopo Session dura enquanto a sessão do usuário durar, guarda estado durante sessão(logado)
//Escopo Application igual session(usuário), mas pra aplicação(todos usuários)
//@Scope("Singleton")
//@Scope(BeanDefinition.SCOPE_SINGLETON)//Prototype também vai estar aqui
//@Scope(WebApplicationContext.SCOPE_APPLICATION)//As outras vão estar aqui, pois são web
//@Lazy(value = false)//só instancia se eu quando eu usar, pode ter erro e não apontar na inicialização
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
