package com.idat.eva03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idat.eva03.model.Publicaciones;


public interface PublicacionRepository extends JpaRepository<Publicaciones, Long> {
    
    // Buscar publicaciones por ID de autor
    List<Publicaciones> findByAutorId(Long idAutor);

    // Buscar publicaciones por estado
    List<Publicaciones> findByEstado(String estado);

    // Buscar publicaciones por título (contiene)
    List<Publicaciones> findByTituloContaining(String titulo);
}
