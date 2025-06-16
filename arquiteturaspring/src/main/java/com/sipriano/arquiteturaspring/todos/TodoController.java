package com.sipriano.arquiteturaspring.todos;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public TodoEntity salvar(@RequestBody TodoEntity novoTodo) {
        try {
            return this.todoService.salvar(novoTodo);
        } catch (IllegalArgumentException e) {
            var msgErro = e.getMessage();
            throw new ResponseStatusException(HttpStatus.CONFLICT, msgErro);
        }
    }

    @PutMapping("/{id}")
    public void atualizarStatus(
            @PathVariable("id") Integer id,
            @RequestBody TodoEntity todo) {
        todo.setId(id);
        todoService.atualizarStatus(todo);
    }

    @GetMapping("/{id}")
    public TodoEntity buscar(@PathVariable("id") Integer id) {
        return todoService.buscarPorId(id);
    }

}
