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

}