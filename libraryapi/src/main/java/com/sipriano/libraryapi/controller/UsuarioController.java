package com.sipriano.libraryapi.controller;

import com.sipriano.libraryapi.controller.dto.UsuarioDTO;
import com.sipriano.libraryapi.controller.mappers.UsuarioMapper;
import com.sipriano.libraryapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody UsuarioDTO dto) {
        var usuario = mapper.toEntity(dto);
        usuarioService.salvar(usuario);
    }

}
