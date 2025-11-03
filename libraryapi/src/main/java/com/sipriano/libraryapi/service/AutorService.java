package com.sipriano.libraryapi.service;

import com.sipriano.libraryapi.model.Autor;
import com.sipriano.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository repository) {
        this.autorRepository = repository;
    }

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID idAutor) {
        return autorRepository.findById(idAutor);
    }
}
