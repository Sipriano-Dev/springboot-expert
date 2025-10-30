package com.sipriano.libraryapi.service;

import com.sipriano.libraryapi.model.Autor;
import com.sipriano.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository repository) {
        this.autorRepository = repository;
    }

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

}
