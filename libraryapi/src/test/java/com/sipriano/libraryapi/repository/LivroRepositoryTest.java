package com.sipriano.libraryapi.repository;

import com.sipriano.libraryapi.model.Autor;
import com.sipriano.libraryapi.model.GeneroLivro;
import com.sipriano.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;
    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("8568-2464");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Ufo Around");
        livro.setDataPublicacao(LocalDate.of(1980, 10, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("d8282fcd-8f57-4147-b223-e3ed91dec5f6"))
                .orElse(null);
        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("9582-4356");
        livro.setPreco(BigDecimal.valueOf(120));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Ufo Around Again");
        livro.setDataPublicacao(LocalDate.of(1982, 10, 2));

        Autor autor = new Autor();
        autor.setNome("Felipe");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(2007, 2, 10));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("3643-2463");
        livro.setPreco(BigDecimal.valueOf(99));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Is there any ufo");
        livro.setDataPublicacao(LocalDate.of(1988, 8, 14));

        Autor autor = new Autor();
        autor.setNome("Anderson");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1988, 10, 14));
        autorRepository.save(autor);

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro() {
        UUID id = UUID.fromString("054e9152-79b6-4b9b-80db-debd82588c85");
        var livroPraAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("d8282fcd-8f57-4147-b223-e3ed91dec5f6");
        Autor novoAutor = autorRepository.findById(idAutor).orElse(null);

        livroPraAtualizar.setAutor(novoAutor);

        repository.save(livroPraAtualizar);
    }

    @Test
    void deletar() {
        var id = UUID.fromString("8612e764-0604-4999-b313-e2ce23accf1c");
        repository.deleteById(id);
    }

    @Test
    void deletarCascade() {
        var id = UUID.fromString("304a6c27-80c7-4875-9512-adad2df5f499");
        repository.deleteById(id);
    }



}