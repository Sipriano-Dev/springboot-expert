package com.sipriano.libraryapi.controller;

import com.sipriano.libraryapi.controller.dto.AutorDTO;
import com.sipriano.libraryapi.controller.dto.ErroResposta;
import com.sipriano.libraryapi.controller.mappers.AutorMapper;
import com.sipriano.libraryapi.exceptions.OperacaoNaoPermitidaException;
import com.sipriano.libraryapi.exceptions.RegistroDuplicadoException;
import com.sipriano.libraryapi.model.Autor;
import com.sipriano.libraryapi.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
//host::8080/autores
//http://localhost/8080/autores
@RequiredArgsConstructor
public class AutorController implements GenericController {

    private final AutorService autorService;
    private final AutorMapper mapper;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid AutorDTO autorDTO) {
        try {
            Autor autor = mapper.toEntity(autorDTO);
            autorService.salvar(autor);
            URI location = gerarHeaderLocation(autor.getId());
            return ResponseEntity.created(location).build();
        } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

        return autorService
                .obterPorId(idAutor) //retorna um optional
                .map(autor -> { //map do optional q vai trazer um autor ou nada
                    AutorDTO autorDTO = mapper.toDTO(autor); //trazendo o autor ele vai retorna ele
                    return ResponseEntity.ok(autorDTO);
                }).orElseGet(() -> ResponseEntity.notFound().build() ); //se não trouxer o autor vai trazer isso

//        if (autorOptional.isPresent()) {
//            var autor = autorOptional.get();
//            AutorDTO autorDTO = mapper.toDTO(autor);
//            return ResponseEntity.ok(autorDTO);
//        }
//
//        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable String id) {
        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = autorService.obterPorId(idAutor);
            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            autorService.deletar(autorOptional.get());
            return ResponseEntity.noContent().build();
        } catch (OperacaoNaoPermitidaException e) {
            var erroResposta = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> pesquisar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {
        List<Autor> lista = autorService.pesquisaByExample(nome, nacionalidade);
        List<AutorDTO> listaDTO = lista
                .stream()
                .map(mapper::toDTO)
//              .map(autor -> mapper.toDTO(autor))
                .toList(); //collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable String id, @RequestBody @Valid AutorDTO autorDTO) {
        try {
            UUID idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = autorService.obterPorId(idAutor);
            if (autorOptional.isPresent()) {
                var autor = autorOptional.get();
                autor.setNome(autorDTO.nome());
                autor.setDataNascimento(autorDTO.dataNascimento());
                autor.setNacionalidade(autorDTO.nacionalidade());
                autorService.atualizar(autor);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }


}
