package com.sipriano.libraryapi.controller;

import com.sipriano.libraryapi.controller.dto.AutorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores")
//host::8080/autores
//http://localhost/8080/autores
public class AutorController {

    @PostMapping
    public ResponseEntity salvar(@RequestBody AutorDTO autor) {
        return new ResponseEntity("Autor Salvo com sucesso" + autor, HttpStatus.CREATED);
    }

}
