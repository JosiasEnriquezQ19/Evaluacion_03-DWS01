package com.idat.eva03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.eva03.mapper.IAutoresMapper;
import com.idat.eva03.model.Autores;
import com.idat.eva03.repository.AutorRepository;
import com.idat.eva03.util.EstadoAutor;
import com.idat.eva03.dto.AutoresDTO;

@Service
public class AutorService {
    // Inyectar AutorRepository para operaciones CRUD
    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private IAutoresMapper autoresMapper;

    // Listar todos los autores (devuelve lista de DTO)
    public List<AutoresDTO> findAllAutores() {
        List<Autores> autores = autorRepository.findAll();
        return autoresMapper.toDTOList(autores);
    }

    // Guardar un nuevo autor (recibe y devuelve DTO)
    public AutoresDTO saveAutor(AutoresDTO autorDTO) {
        Autores autor = autoresMapper.toEntity(autorDTO);
        Autores guardado = autorRepository.save(autor);
        return autoresMapper.toDTO(guardado);
    }

    // Buscar autor por ID (devuelve DTO)
    public AutoresDTO findById(Long id) {
        Autores autor = autorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        return autoresMapper.toDTO(autor);
    }

    // Cambiar estado del autor
    public void cambiarEstado(long id, String nuevoEstado){
        Autores autor = autorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        autor.setEstado(EstadoAutor.valueOf(nuevoEstado));
        autorRepository.save(autor);
    }

    // Eliminar autor por ID
    public void deleteAutor(long id) {
        autorRepository.deleteById(id);
    }

    // Actualizar autor existente (recibe y devuelve DTO)
    public AutoresDTO updateAutor(long id, AutoresDTO autorDTO) {
        Autores existente = autorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        existente.setNombre(autorDTO.getNombre());
        existente.setCorreo(autorDTO.getCorreo());
        existente.setEstado(EstadoAutor.valueOf(autorDTO.getEstado()));
        Autores actualizado = autorRepository.save(existente);
        return autoresMapper.toDTO(actualizado);
    }
}
