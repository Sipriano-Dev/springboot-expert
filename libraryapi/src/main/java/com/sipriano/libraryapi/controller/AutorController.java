package com.sipriano.libraryapi.controller;

import com.sipriano.libraryapi.controller.dto.AutorDTO;
import com.sipriano.libraryapi.controller.mappers.AutorMapper;
import com.sipriano.libraryapi.model.Autor;
import com.sipriano.libraryapi.model.Usuario;
import com.sipriano.libraryapi.security.SecurityService;
import com.sipriano.libraryapi.service.AutorService;
import com.sipriano.libraryapi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
@Tag(name = "Autores")
@Slf4j
public class AutorController implements GenericController {

    private final AutorService autorService;
    private final AutorMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Salvar", description = "Cadastrar novo autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Erro de validação"),
            @ApiResponse(responseCode = "409", description = "Autor já cadastrado")
    })
    public ResponseEntity<Void> salvar(@RequestBody @Valid AutorDTO autorDTO) {

        Autor autor = mapper.toEntity(autorDTO);
        autorService.salvar(autor);
        URI location = gerarHeaderLocation(autor.getId());
        return ResponseEntity.created(location).build();

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    @Operation(summary = "Obter detalhes", description = "Retorna os dados do autor pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor encontrado"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    })
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

        return autorService
                .obterPorId(idAutor) //retorna um optional
                .map(autor -> { //map do optional q vai trazer um autor ou nada
                    AutorDTO autorDTO = mapper.toDTO(autor); //trazendo o autor ele vai retorna ele
                    return ResponseEntity.ok(autorDTO);
                }).orElseGet(() -> ResponseEntity.notFound().build()); //se não trouxer o autor vai trazer isso

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Deletar", description = "Deleta um autor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado"),
            @ApiResponse(responseCode = "400", description = "Autor possui livro cadastrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable String id) {

        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);
        if (autorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        autorService.deletar(autorOptional.get());
        return ResponseEntity.noContent().build();

    }

    @GetMapping
    @PreAuthorize("hasAnyRole('OPERADOR', 'GERENTE')")
    @Operation(summary = "Pesquisar", description = "Realiza pesquisa de autores por parametros.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso")
    })
    public ResponseEntity<List<AutorDTO>> pesquisar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {

        log.trace("Pesquisa Autores");
        log.debug("Pesquisa Autores");
        log.info("Pesquisa Autores");
        log.warn("Pesquisa Autores");
        log.error("Pesquisa Autores");

        List<Autor> lista = autorService.pesquisaByExample(nome, nacionalidade);
        List<AutorDTO> listaDTO = lista
                .stream()
                .map(mapper::toDTO)
//              .map(autor -> mapper.toDTO(autor))
                .toList(); //collect(Collectors.toList());
        return ResponseEntity.ok(listaDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('GERENTE')")
    @Operation(summary = "Atualizar", description = "Atualiza um autor existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado"),
            @ApiResponse(responseCode = "409", description = "Autor já cadastrado")
    })
    public ResponseEntity<Void> atualizar(@PathVariable String id, @RequestBody @Valid AutorDTO autorDTO) {

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

    }


}
