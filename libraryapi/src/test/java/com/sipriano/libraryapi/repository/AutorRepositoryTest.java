package com.sipriano.libraryapi.repository;

import com.sipriano.libraryapi.model.Autor;
import com.sipriano.libraryapi.model.GeneroLivro;
import com.sipriano.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

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

    @Test
    void salvarAutorComLivroTest() {
        Autor autor = new Autor();
        autor.setNome("Anderson");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1988, 10, 14));

        Livro livro = new Livro();
        livro.setIsbn("4853-3848");
        livro.setPreco(BigDecimal.valueOf(90));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Horse in around");
        livro.setDataPublicacao(LocalDate.of(2020, 10, 14));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("4853-3678");
        livro2.setPreco(BigDecimal.valueOf(80));
        livro2.setGenero(GeneroLivro.FICCAO);
        livro2.setTitulo("Horse in around 2");
        livro2.setDataPublicacao(LocalDate.of(2022, 10, 14));
        livro2.setAutor(autor);


        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        //livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor() {
        var id = UUID.fromString("0cfa0b61-aeae-437e-a2a6-ac0f78641b4a");
        var autor = repository.findById(id).orElse(null);
        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }


}
