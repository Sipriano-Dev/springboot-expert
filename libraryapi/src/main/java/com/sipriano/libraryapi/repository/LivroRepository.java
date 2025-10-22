package com.sipriano.libraryapi.repository;

import com.sipriano.libraryapi.model.Autor;
import com.sipriano.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //Query methods
    //select * from livro where id_autor = id
    List<Livro> findByAutor(Autor autor);
}
