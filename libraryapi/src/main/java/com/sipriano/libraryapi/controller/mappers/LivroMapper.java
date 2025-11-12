package com.sipriano.libraryapi.controller.mappers;

import com.sipriano.libraryapi.controller.dto.CadastroLivroDTO;
import com.sipriano.libraryapi.model.Livro;
import com.sipriano.libraryapi.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    @Autowired
    AutorRepository repository;

    @Mapping(target = "autor", expression = "java( repository.findById(dto.idAutor()).orElse(null) )")
    public abstract Livro toEntity(CadastroLivroDTO dto);




}
