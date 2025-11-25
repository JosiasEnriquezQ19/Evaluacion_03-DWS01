package com.idat.eva03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.eva03.dto.PublicacionesDTO;
import com.idat.eva03.mapper.IPublicacionesMapper;
import com.idat.eva03.repository.PublicacionRepository;


@Service
public class PublicacionService {
    // Inyectar PublicacionRepository para operaciones CRUD
    @Autowired
    private PublicacionRepository publicacionRepository;
    // Inyectar AutorService para validar existencia de autor
    @Autowired
    private AutorService autorService;

    @Autowired
    private IPublicacionesMapper publicacionesMapper;

    // Listar todas las publicaciones
    public List<PublicacionesDTO> findAllPublicaciones() {
        return publicacionesMapper.toDtoList(publicacionRepository.findAll());
    }

    // Guardar una nueva publicación (recibe y devuelve DTO)
    public PublicacionesDTO savePublicacion(PublicacionesDTO publicacion) {
        // Validar que el autor exista antes de guardar la publicación
        if (publicacion.getAutorId() == null) {
            throw new RuntimeException("El ID del autor es obligatorio");
        }
        autorService.findById(publicacion.getAutorId()); // Lanza excepción si no existe
        // Validar longitud mínima y máxima del título de la publicación
        String titulo = publicacion.getTitulo();
        if (titulo == null || titulo.length() < 5 || titulo.length() > 150) {
            throw new RuntimeException("El título debe tener entre 5 y 150 caracteres");
        }
        var guardada = publicacionRepository.save(publicacionesMapper.toEntity(publicacion));
        return publicacionesMapper.toDto(guardada);
    }

    // Buscar publicaciones por ID de autor
    public List<PublicacionesDTO> findByAutorId(Long idAutor) {
        return publicacionesMapper.toDtoList(publicacionRepository.findByAutorId(idAutor));
    }

    // Buscar publicaciones por estado
    public List<PublicacionesDTO> findByEstado(String estado) {
        return publicacionesMapper.toDtoList(publicacionRepository.findByEstado(estado));
    }

    // Cambiar estado de una publicación
    public void cambiarEstadoPublicacion(long id, String nuevoEstado) {
        PublicacionesDTO publicacion = publicacionesMapper.toDto(publicacionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Publicación no encontrada")));
        
        String estadoAnterior = publicacion.getEstado();
        publicacion.setEstado(nuevoEstado);
        
        // Manejar fechas automáticamente
        if ("PUBLICADO".equals(nuevoEstado) && !"PUBLICADO".equals(estadoAnterior)) {
            // Si se está publicando por primera vez, asignar fecha de publicación
            if (publicacion.getFechaPublicacion() == null) {
                publicacion.setFechaPublicacion(java.time.LocalDate.now());
            }
        } else if ("BORRADOR".equals(nuevoEstado) && "PUBLICADO".equals(estadoAnterior)) {
            // Si se vuelve a borrador desde publicado, mantener la fecha de publicación
            // No se elimina la fecha para mantener el historial
        }
        
        // Siempre actualizar fecha de modificación
        publicacion.setFechaModificacion(java.time.LocalDateTime.now());
        
        publicacionRepository.save(publicacionesMapper.toEntity(publicacion));
    }

    // Actualizar publicación existente (recibe y devuelve DTO)
    public PublicacionesDTO editarPublicacion(Long id, PublicacionesDTO publicacion) {
        // Verificar que la publicación existe
        if (!publicacionRepository.existsById(id)) {
            throw new RuntimeException("Publicación no encontrada con ID: " + id);
        }
        // Validar título si se proporciona
        String titulo = publicacion.getTitulo();
        if (titulo != null && (titulo.length() < 5 || titulo.length() > 150)) {
            throw new RuntimeException("El título debe tener entre 5 y 150 caracteres");
        }
        publicacion.setId(id); // Asegurar que el ID sea correcto
        var actualizada = publicacionRepository.save(publicacionesMapper.toEntity(publicacion));
        return publicacionesMapper.toDto(actualizada);
    }

    // Buscar publicación por ID (devuelve DTO con autor)
    public PublicacionesDTO findById(Long id) {
        return publicacionesMapper.toDto(publicacionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Publicación no encontrada")));
    }

    // Buscar publicaciones por título (contiene)
    public List<PublicacionesDTO> buscarPorTitulo(String titulo) {
        return publicacionesMapper.toDtoList(publicacionRepository.findByTituloContaining(titulo));
    }

    
    // Eliminar publicación por ID
    public void deletePublicacion(Long id) {
        if (!publicacionRepository.existsById(id)) {
            throw new RuntimeException("Publicación no encontrada con ID: " + id);
        }
        publicacionRepository.deleteById(id);
    }

}
