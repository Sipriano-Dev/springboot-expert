package com.sipriano.libraryapi.validator;

import com.sipriano.libraryapi.exceptions.RegistroDuplicadoException;
import com.sipriano.libraryapi.model.Autor;
import com.sipriano.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {

    private AutorRepository autorRepository;

    public AutorValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void validar(Autor autor) {
        if (existeAutorCadastrado(autor)) {
            throw  new RegistroDuplicadoException("Autor já cadastrado!");
        }
    }

    private boolean existeAutorCadastrado(Autor autor) {
        Optional<Autor> autorEncontrado = autorRepository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade()
        );

        //Significa q é um cadastro pois não tem id
        if (autor.getId() == null) {
            return autorEncontrado.isPresent();
        }

        //Não é null, tem id, logo vai seguir a lógica abaixo
        return autorEncontrado.isPresent() && !autor.getId().equals(autorEncontrado.get().getId());
    }

}
