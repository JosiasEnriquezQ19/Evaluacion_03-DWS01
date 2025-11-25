package com.idat.eva03.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.idat.eva03.dto.AutoresDTO;
import com.idat.eva03.model.Autores;

@Mapper(componentModel = "spring")
public interface IAutoresMapper {
    
    //entity a dto
    AutoresDTO toDTO(Autores autor);

    //dto a entity
    @Mapping(target = "publicaciones", ignore = true)
    Autores toEntity(AutoresDTO autorDTO);

    //lista entity a lista dto
    List<AutoresDTO> toDTOList(List<Autores> autores);

    //lista dto a lista entity
    List<Autores> toEntityList(List<AutoresDTO> autoresDTO);
    
}
