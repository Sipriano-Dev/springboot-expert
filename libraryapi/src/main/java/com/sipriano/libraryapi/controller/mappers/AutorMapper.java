package com.sipriano.libraryapi.controller.mappers;

import com.sipriano.libraryapi.controller.dto.AutorDTO;
import com.sipriano.libraryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {

//  @Mapping(source = "nome", target = "nomeAutor")
    Autor toEntity(AutorDTO autorDTO);

    AutorDTO toDTO(Autor autor);

}



