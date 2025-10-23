package com.sipriano.libraryapi.repository;

import com.sipriano.libraryapi.model.Autor;
import com.sipriano.libraryapi.model.GeneroLivro;
import com.sipriano.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 */

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query methods
    //select * from livro where id_autor = id
    List<Livro> findByAutor(Autor autor);

    //select * from livro where titulo = titulo
    List<Livro> findByTitulo(String titulo);

    //select * from livro where isbn = ?
    List<Livro> findByIsbn(String isbn);

    //select * from livro where titulo = ? and preco = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    //select * from livro where titulo = ? or isbn = ?
    List<Livro> findByTituloOrIsbnOrderByTitulo(String titulo, String isbn);

    //select * from livro where dataPublicacao between ? and ?
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    //JPQL
    //referencia a entidade e propriedades da entidade, não do banco
    //select l.* from livro as l order by l.titulo. l.preco
    @Query("SELECT l FROM Livro AS l ORDER BY l.titulo")
    List<Livro> listarTodosOrdenadoPorTituloEPreco();

    /**
     * select a.* from livro l
     * join autor as a on a.id = l.id_autor
     */
    @Query("SELECT a FROM Livro l JOIN l.autor a ")
    List<Autor> listarAutoresDosLivros();

    //Select distinct l.titulo from livro l
    @Query("SELECT DISTINCT l.titulo FROM Livro l")
    List<String> listarNomesDiferentesLivros();

    @Query("""
            SELECT DISTINCT l.genero
            FROM Livro l
            JOIN l.autor a
            WHERE a.nacionalidade = 'Brasileira'
            ORDER BY l.genero
            """)
    List<String> listarGenerosAutoresBrasileiros();

    //Named Parameters -> Parametros nomeados
    //O Positional parameters vc coloca o ?1 no lugar do :genero
    @Query("SELECT l FROM Livro l WHERE l.genero = :genero ORDER BY l.preco" )
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro);



}
