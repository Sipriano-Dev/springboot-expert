package com.sipriano.libraryapi.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErroResposta(int status, String msg, List<ErroCampo> erros) {

    public static ErroResposta respostaPadrao(String msg) {
        return new ErroResposta(HttpStatus.BAD_REQUEST.value(), msg, List.of());
    }

    public static ErroResposta conflito(String msg) {
        return new ErroResposta(HttpStatus.CONFLICT.value(), msg, List.of());
    }

}
