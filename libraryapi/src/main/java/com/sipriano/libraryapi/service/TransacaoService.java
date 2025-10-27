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
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    AutorRepository autorRepository;
    @Autowired
    LivroRepository livroRepository;

    /// livro (titulo,..., nome_arquivo) -> id.png
    @Transactional
    public void salvarLivroComFoto(){
        // salva o livro
        // repository.save(livro);

        // pega o id do livro = livro.getId();
        // var id = livro.getId();

        // salvar foto do livro -> bucket na nuvem
        // bucketService.salvar(livro.getFoto(), id + ".png");

        // atualizar o nome arquivo que foi salvo
        // livro.setNomeArquivoFoto(id + ".png");
    }

    @Transactional
    public void atualizacaoSemAtualizar() {
        var livro = livroRepository
                .findById(UUID.fromString("0eafbea5-6ccb-47c2-864c-4c0d0c9c52f4"))
                .orElse(null);

        livro.setDataPublicacao(LocalDate.of(1923, 10, 2));
        //livroRepository.save(livro);
    }


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
