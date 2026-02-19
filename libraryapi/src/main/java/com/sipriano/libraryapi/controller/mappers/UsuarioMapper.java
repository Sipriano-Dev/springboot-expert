package com.sipriano.libraryapi.controller.mappers;

import com.sipriano.libraryapi.controller.dto.UsuarioDTO;
import com.sipriano.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

}
