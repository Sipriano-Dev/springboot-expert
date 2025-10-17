package com.sipriano.libraryapi.repository;

import com.sipriano.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {

        Autor autor = new Autor();
        autor.setNome("Andressa");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1986, 10, 14));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);

    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("d8282fcd-8f57-4147-b223-e3ed91dec5f6");
        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setNome("Gabrielle");
            autorEncontrado.setDataNascimento(LocalDate.of(1995, 10, 11));
            repository.save(autorEncontrado);
        }
    }

}
