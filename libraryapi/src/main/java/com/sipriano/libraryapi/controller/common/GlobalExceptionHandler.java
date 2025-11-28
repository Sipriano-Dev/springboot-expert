package com.sipriano.libraryapi.controller.common;

import com.sipriano.libraryapi.controller.dto.ErroCampo;
import com.sipriano.libraryapi.controller.dto.ErroResposta;
import com.sipriano.libraryapi.exceptions.CampoInvalidoException;
import com.sipriano.libraryapi.exceptions.OperacaoNaoPermitidaException;
import com.sipriano.libraryapi.exceptions.RegistroDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<ErroCampo> listaErros = e.getFieldErrors()
                .stream()
                .map(error -> new ErroCampo(error.getField(), error.getDefaultMessage()))
                .toList();
        return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", listaErros);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(RegistroDuplicadoException.class)
    public ErroResposta handleRegistroDuplicadoException(RegistroDuplicadoException e) {

        return ErroResposta.conflito(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    public ErroResposta handleOperacaoNaoPermitidaException(OperacaoNaoPermitidaException e) {

        return ErroResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(CampoInvalidoException .class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handleCampoInvalidoException(CampoInvalidoException e) {
        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação",
                List.of(new ErroCampo(e.getCampo(), e.getMessage())));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta handleErrosNaoTratado(RuntimeException e) {
        return new ErroResposta(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro inesperado, entre em contato com a Administração.",
                List.of());
    }


}
