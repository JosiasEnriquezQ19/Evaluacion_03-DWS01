package com.idat.eva03.mapper;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.idat.eva03.dto.PublicacionesDTO;
import com.idat.eva03.model.Publicaciones;


@Mapper(componentModel = "spring")
public interface IPublicacionesMapper {


    //dto a entity
    @Mapping(target = "autor.id", source = "autorId")
    Publicaciones toEntity(PublicacionesDTO publicacionDTO);

    //entity a dto
    @Mapping(target = "autorId", source = "autor.id")
    PublicacionesDTO toDto(Publicaciones publicacion);

    //lista dto a lista entity
    List<Publicaciones> toEntityList(List<PublicacionesDTO> publicacionesDTO);

    //lista entity a lista dto
    List<PublicacionesDTO> toDtoList(List<Publicaciones> publicaciones);
    
}
