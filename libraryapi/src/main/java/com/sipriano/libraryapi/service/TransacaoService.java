package com.sipriano.libraryapi.service;

import com.sipriano.libraryapi.model.Autor;
import com.sipriano.libraryapi.model.GeneroLivro;
import com.sipriano.libraryapi.model.Livro;
import com.sipriano.libraryapi.repository.AutorRepository;
import com.sipriano.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransacaoService {

    @Autowired
    AutorRepository autorRepository;
    @Autowired
    LivroRepository livroRepository;

    @Transactional
    public void executar() {
        //Salva o autor
        Autor autor = new Autor();
        autor.setNome("Chaves");
        autor.setNacionalidade("Europeu");
        autor.setDataNascimento(LocalDate.of(1880, 2, 10));

        autorRepository.saveAndFlush(autor);

        //Salva o livro
        Livro livro = new Livro();
        livro.setIsbn("5468-4632");
        livro.setPreco(BigDecimal.valueOf(150));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Livro do Chaves");
        livro.setDataPublicacao(LocalDate.of(1925, 10, 2));
        livro.setAutor(autor);

        livroRepository.saveAndFlush(livro);

        if (autor.getNome().equals("Chaves")) {
            throw new RuntimeException("Rollback");
        }
    }

}
