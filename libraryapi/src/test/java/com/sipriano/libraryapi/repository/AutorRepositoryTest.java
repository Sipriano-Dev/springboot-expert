package com.sipriano.libraryapi.repository;

import com.sipriano.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTest() {

        Autor autor = new Autor();
        autor.setNome("Felipe");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2007, 2, 10));

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

    @Test
    public void listarTest() {
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest() {
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("395bc7a3-18b5-4dc9-9921-f55f8bf434c5");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("3f629f71-1549-44f8-b090-57b0ef3efe34");
        Optional<Autor> possivelAutor = repository.findById(id);
        possivelAutor.ifPresent(autor -> repository.delete(autor));

    }

}
