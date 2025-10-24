package com.sipriano.libraryapi.repository;

import com.sipriano.libraryapi.model.Autor;
import com.sipriano.libraryapi.model.GeneroLivro;
import com.sipriano.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
        livro.setIsbn("5468-5223");
        livro.setPreco(BigDecimal.valueOf(120));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("O pote de ouro");
        livro.setDataPublicacao(LocalDate.of(1957, 10, 2));

        Autor autor = autorRepository
                .findById(UUID.fromString("d8282fcd-8f57-4147-b223-e3ed91dec5f6"))
                .orElse(null);
        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("5468-5223");
        livro.setPreco(BigDecimal.valueOf(120));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("O pote de ouro");
        livro.setDataPublicacao(LocalDate.of(1922, 10, 2));

        Autor autor = new Autor();
        autor.setNome("Alfred do batman");
        autor.setNacionalidade("Europeu");
        autor.setDataNascimento(LocalDate.of(1880, 2, 10));

        livro.setAutor(autor);

        repository.save(livro);
    }

    @Test
    void salvarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("5468-4632");
        livro.setPreco(BigDecimal.valueOf(150));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("O pote de ouro");
        livro.setDataPublicacao(LocalDate.of(1925, 10, 2));

        Autor autor = new Autor();
        autor.setNome("Alfred do batman");
        autor.setNacionalidade("Europeu");
        autor.setDataNascimento(LocalDate.of(1880, 2, 10));
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

    @Test
    @Transactional
    void buscarLivroTest() {
        UUID id = UUID.fromString("c75dba82-151d-490c-8224-e4925bd46615");
        Livro livro = repository.findById(id).orElse(null);

        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());

        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisaPorTituloTest() {
        List<Livro> lista = repository.findByTitulo("Horse in around");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorIsbnTest() {
        List<Livro> lista = repository.findByIsbn("8568-2464");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloEPreco() {
        List<Livro> lista = repository.findByTituloAndPreco("Ufo Around", BigDecimal.valueOf(100));
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL() {
        List<Livro> list = repository.listarTodosOrdenadoPorTituloEPreco();
//        var list = repository.listarTodosOrdenadoPorTituloEPreco();
        list.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros() {
        List<Autor> list = repository.listarAutoresDosLivros();
        list.forEach(System.out::println);
    }

    @Test
    void listarTitulosNaoRepetidosDosLivros() {
        List<String> list = repository.listarNomesDiferentesLivros();
        list.forEach(System.out::println);
    }

    @Test
    void listarGenerosAutoresBrasileiros() {
        var list = repository.listarGenerosAutoresBrasileiros();
        list.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParam() {
        var lista = repository.findByGenero(GeneroLivro.FICCAO);
        lista.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest() {
        repository.deleteByGenero(GeneroLivro.BIOGRAFIA);
    }

    @Test
    void updateDataPublicacaoTest() {
        repository.updateDataPublicacao(LocalDate.of(2024, 10, 11));
    }


}