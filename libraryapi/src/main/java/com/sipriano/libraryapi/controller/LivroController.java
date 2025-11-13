package com.sipriano.libraryapi.controller;

import com.sipriano.libraryapi.controller.dto.CadastroLivroDTO;
import com.sipriano.libraryapi.controller.dto.ErroResposta;
import com.sipriano.libraryapi.controller.dto.ResultadoPesquisaLivroDTO;
import com.sipriano.libraryapi.controller.mappers.LivroMapper;
import com.sipriano.libraryapi.exceptions.RegistroDuplicadoException;
import com.sipriano.libraryapi.model.Livro;
import com.sipriano.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO livroDTO) {

        Livro livro = mapper.toEntity(livroDTO);
        service.salvar(livro);
        var url = gerarHeaderLocation(livro.getId());
        // retorna codigo created com header url no ResponseEntity
        return ResponseEntity.created(url).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterDetalhes(@PathVariable String id) {
        return service.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    var dto = mapper.toDTO(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

}
