package com.idat.eva03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.idat.eva03.model.Autores;
import java.util.Optional;



public interface AutorRepository extends JpaRepository<Autores, Long> {
    // Buscar autor por correo (útil para validación de correo único)
    Optional<Autores> findByCorreo(String correo);
}
