package com.sipriano.libraryapi.controller;

import com.sipriano.libraryapi.controller.dto.CadastroLivroDTO;
import com.sipriano.libraryapi.controller.dto.ErroResposta;
import com.sipriano.libraryapi.controller.mappers.LivroMapper;
import com.sipriano.libraryapi.exceptions.RegistroDuplicadoException;
import com.sipriano.libraryapi.model.Livro;
import com.sipriano.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid CadastroLivroDTO livroDTO) {
        try {
            Livro livro = mapper.toEntity(livroDTO);
            service.salvar(livro);
            // criar url pra acesso dos dados do livro
            // retorna codigo created com header location no ResponseEntity
            return ResponseEntity.ok(livro);
        } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

}
